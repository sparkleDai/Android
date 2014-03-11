package com.sparkle.kits;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IP {
	public static InetAddress intToInet(int value) {
		byte[] bytes = new byte[4];
		for (int i = 0; i < 4; i++) {
			bytes[i] = fetchByteOfInt(value, i);
		}
		try {
			return InetAddress.getByAddress(bytes);
		} catch (UnknownHostException e) {
			// This only happens if the byte array has a bad length
			return null;
		}
	}

	public static byte fetchByteOfInt(int value, int which) {
		int shift = which * 8;
		return (byte) (value >> shift);
	}
}
