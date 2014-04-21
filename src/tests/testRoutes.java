package tests;

import static org.junit.Assert.*;
import hlmp.CommLayer.Configuration;
import hlmp.CommLayer.NetUser;
import hlmp.CommLayer.NetUserList;
import hlmp.CommLayer.Router;
import hlmp.CommLayer.Constants.MessageMetaType;
import hlmp.CommLayer.Exceptions.ArgumentOutOfRangeException;
import hlmp.CommLayer.Interfaces.RouterMessageErrorHandlerI;
import hlmp.CommLayer.Messages.Message;
import hlmp.CommLayer.Messages.MessageFactory;
import hlmp.NetLayer.NetData;
import hlmp.NetLayer.NetHandler;
import hlmp.SubProtocol.Chat.Messages.ChatMessage;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class testRoutes {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_send_message() throws ArgumentOutOfRangeException, InterruptedException, UnknownHostException {
		Configuration configuration = new Configuration();
		RouterMessageErrorHandlerI routerInformation = null;
		
		HashMap<Integer, Message> messageTypes= new HashMap<Integer, Message>();
		messageTypes.put(hlmp.SubProtocol.Chat.Types.CHATMESSAGE, new ChatMessage());
		NetData netData= new NetData();
		ArrayList<NetUser> netUserList = new ArrayList<NetUser>();
		netUserList.add(new NetUser(new UUID(1L, 0L), "user1", null, null, configuration.getNetData()));
		netUserList.add(new NetUser(new UUID(2L,0L),"user2",null, null, configuration.getNetData()));
		netUserList.add(new NetUser(new UUID(3L,0L),"user3",null, null, configuration.getNetData()));
		
		netUserList.get(0).setNeighborhoodIds(new UUID[]{netUserList.get(1).getId()});
		netUserList.get(1).setNeighborhoodIds(new UUID[]{netUserList.get(0).getId(), netUserList.get(2).getId()});
		netUserList.get(2).setNeighborhoodIds(new UUID[]{netUserList.get(1).getId()});
		
		ChatMessage mess = new ChatMessage(netUserList.get(0), "lalalall");
		mess.setTargetNetUser(netUserList.get(2));
		
		int waitForAck = 3;
		Router router = new Router(routerInformation, waitForAck, messageTypes);
		router.queueMessageToSend(mess);
		NetHandler netHandler = new NetHandler(configuration.getNetData(), null, null);
		
		
		byte[] ipAddr1 = new byte[]{127, 0, 0, 1};
		InetAddress addr1 = InetAddress.getByAddress(ipAddr1);
		byte[] ipAddr2 = new byte[]{127, 0, 0, 2};
		InetAddress addr2 = InetAddress.getByAddress(ipAddr2);
		byte[] ipAddr3 = new byte[]{127, 0, 0, 3};
		InetAddress addr3 = InetAddress.getByAddress(ipAddr3);
		
		
		
		NetUserList userList = new NetUserList();
		userList.add(addr1, netUserList.get(0));
		userList.add(addr2, netUserList.get(1));
		userList.add(addr3, netUserList.get(2));
		
		
		router.updateRouterTable(netHandler, netUserList.get(0), userList);
		router.proccessNotSentMessage();
	}

}
