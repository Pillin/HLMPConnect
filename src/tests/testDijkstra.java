package tests;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.UUID;

import hlmp.CommLayer.Dijkstra;
import hlmp.CommLayer.NetUser;
import hlmp.CommLayer.Exceptions.ArgumentOutOfRangeException;
import hlmp.NetLayer.NetData;



public class testDijkstra extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

//	public void join_users(NetUser u1, NetUser u2){
//		
//		u1.setNeighborhoodIds(new UUID[]{u2.getId()});
//		
//	}

	public void test_create_Dijkstra() throws ArgumentOutOfRangeException {
//		
		//int totalNodeCount = 3; 
		NetData netData= new NetData();
		ArrayList<NetUser> netUserList = new ArrayList<NetUser>();
		netUserList.add(new NetUser(new UUID(1L, 0L), "user1", null, null, netData));
		netUserList.add(new NetUser(new UUID(2L,0L),"user2",null, null, netData));
		netUserList.add(new NetUser(new UUID(3L,0L),"user3",null, null, netData));
		
		netUserList.get(0).setNeighborhoodIds(new UUID[]{netUserList.get(1).getId()});
		netUserList.get(1).setNeighborhoodIds(new UUID[]{netUserList.get(0).getId(), netUserList.get(2).getId()});
		netUserList.get(2).setNeighborhoodIds(new UUID[]{netUserList.get(1).getId()});
		

		Dijkstra dijkstra = new Dijkstra(netUserList, netData);
		int[] lala =  dijkstra.GetMinimumPath(1, 2);
		for ( int la : lala){
			
		}
		//hacer ASSERT
	}

}
