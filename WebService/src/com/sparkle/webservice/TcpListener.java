package com.sparkle.webservice;

import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;

public class TcpListener extends Thread {

	private ServerSocket _listenSocket = null;
	private MyLog _myLog = new MyLog(getClass().getName());

	public TcpListener(ServerSocket listenSocket, WebServer webServer) {
		this._listenSocket = listenSocket;
	}

	public void quit() {
		try {
			_listenSocket.close(); // if the TcpListener thread is blocked on
									// accept,
									// closing the socket will raise an
									// exception
		} catch (Exception e) {
			_myLog.l(Log.DEBUG, "Exception closing TcpListener listenSocket");
		}
	}

	public void run() {
		try {
			while (true) {

				Socket clientSocket = _listenSocket.accept();
				_myLog.l(Log.INFO, "New connection, spawned thread");
				SessionThread newSession = new SessionThread(clientSocket);
				newSession.start();				
			}
		} catch (Exception e) {
			_myLog.l(Log.DEBUG, "Exception in TcpListener");
		}
	}

}
