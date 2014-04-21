[1;33mdiff --git a/AndroidManifest.xml b/AndroidManifest.xml[m
[1;33mindex bd8c122..90dc950 100644[m
[1;33m--- a/AndroidManifest.xml[m
[1;33m+++ b/AndroidManifest.xml[m
[1;35m@@ -32,6 +32,7 @@[m
 	<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />[m
 	<uses-permission android:name="android.permission.WAKE_LOCK" />[m
 	<uses-permission android:name="android.permission.CHANGE_WIFI_MULTICAST_STATE" />[m
[36m+[m	[36m<uses-permission android:name="android.permission.ACCESS_SUPERUSER"/>[m
 [m
     <application[m
         android:name="android.HLMPConnect.HLMPApplication"[m
[1;33mdiff --git a/src/hlmp b/src/hlmp[m
[1;33m--- a/src/hlmp[m
[1;33m+++ b/src/hlmp[m
[1;35m@@ -1 +1 @@[m
[31m-Subproject commit 3d6bec1076a81a13c1a7b299803d4f99aaf32efc[m
[36m+[m[36mSubproject commit 3d6bec1076a81a13c1a7b299803d4f99aaf32efc-dirty[m
