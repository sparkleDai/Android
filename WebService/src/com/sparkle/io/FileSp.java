package com.sparkle.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileSp {

	@SuppressWarnings("finally")
	public static byte[] read(String fileName) {
		if (fileName == null || fileName.length() <= 0) {
			return null;
		}

		byte[] buffer = null;
		
		try {
			FileInputStream fin = new FileInputStream(fileName);
			int length = fin.available();

			buffer = new byte[length];

			fin.read(buffer);

			fin.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		finally
		{
			return buffer;
		}
	}
	
	public static boolean isExist(String filePath)
	{
		File file=new File(filePath);
	
		return file.exists();
	}
}
