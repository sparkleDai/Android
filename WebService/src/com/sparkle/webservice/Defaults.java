package com.sparkle.webservice;

import java.util.HashMap;

import android.content.Context;
import android.os.Environment;

public class Defaults {

	@SuppressWarnings("serial")
	public static HashMap<String, String> Extensions = new HashMap<String, String>() {
		{
			put("htm", "text/html");
			put("html", "text/html");
			put("xml", "text/xml");
			put("txt", "text/plain");
			put("json", "text/plain");
			put("css", "text/css");
			put("ico", "image/x-icon");
			put("png", "image/png");
			put("gif", "image/gif");
			put("jpg", "image/jpg");
			put("jpeg", "image/jpeg");
			put("zip", "application/zip");
			put("rar", "application/rar");
			put("js", "text/javascript");
		}
	};

	private static String _root = Environment.getExternalStorageDirectory()
			.getAbsolutePath();
	private static String _indexPage = "index.html";
	private static int _port = 8080;
	private static String _settingsName = "WebService";
	@SuppressWarnings("deprecation")
	private static int _settingsMode = Context.MODE_WORLD_WRITEABLE;

	public static String getRoot() {
		if (_root == null || _root.length() <= 0) {
			_root = Environment.getExternalStorageDirectory().getAbsolutePath();
		}

		if (!_root.endsWith("/")) {
			_root += "/";
		}
		return _root;
	}

	public static void setRoot(String root) {
		_root = root;
	}

	public static String getIndexPage() {
		return _indexPage;
	}

	public static void setIndexPage(String indexPage) {
		_indexPage = indexPage;
	}

	public static int getPort() {
		return _port;
	}

	public static void setPort(int port) {
		_port = port;
	}

	public static String getSettingsName() {
		return _settingsName;
	}

	public static void setSettingsName(String settingsName) {
		Defaults._settingsName = settingsName;
	}

	public static int getSettingsMode() {
		return _settingsMode;
	}

	public static void setSettingsMode(int settingsMode) {
		Defaults._settingsMode = settingsMode;
	}
}
