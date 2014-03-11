
package com.sparkle.webservice;

import android.util.Log;

public class MyLog {
	protected String tag;

	public MyLog(String tag) {
		this.tag = tag;
	}

	public void l(int level, String str, boolean sysOnly) {
		synchronized (MyLog.class) {
			str = str.trim();

			Log.println(level, tag, str);

		}
	}

	public void l(int level, String str) {
		l(level, str, false);
	}

	public void e(String s) {
		l(Log.ERROR, s, false);
	}

	public void w(String s) {
		l(Log.WARN, s, false);
	}

	public void i(String s) {
		l(Log.INFO, s, false);
	}

	public void d(String s) {
		l(Log.DEBUG, s, false);
	}
}
