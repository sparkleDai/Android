
package com.sparkle.webservice;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

public class UiUpdater {
	protected static MyLog myLog = new MyLog("UiUpdater");
	protected static List<Handler> clients = new ArrayList<Handler>();
	
	public static void registerClient(Handler client) {
		if(!clients.contains(client)) {
			clients.add(client);
		}
	}
	
	public static void unregisterClient(Handler client) {
		while(clients.contains(client)) {
			clients.remove(client);
		}
	}
	
	public static void updateClients() {
		
		for (Handler client : clients) {
			client.sendEmptyMessage(MESSAGES.UPDATE_UI);
		}
	}
}
