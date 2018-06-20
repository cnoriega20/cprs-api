package net.windows.database.seikou.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	public static String dateToString(Date date){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		// Get the date today using Calendar object.
		//Date today = Calendar.getInstance().getTime();
		// Using DateFormat format method we can create a string 
		// representation of a date with the defined format.
		String stringDate = df.format(date);
		return stringDate;

	}
	public static String dateTimeToString(Object date){
		String stringDate = " ";
		if(date instanceof Date){
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			stringDate = df.format(date);
		}
		if(date instanceof Time){
			DateFormat df = new SimpleDateFormat("hh:mm:ss");
			stringDate = df.format(date);
		}
		return stringDate;
	}
	
	public  static String bytesArraytoString(byte[] bytes){
		return new String(bytes);
	}
	
	public static String getArgumentString(Object str){
		if(str == null)
			return null;
		return  str.toString();
	}
	
	   public static byte[] toByteArray(Object obj) throws IOException {
	        byte[] bytes = null;
	        ByteArrayOutputStream bos = null;
	        ObjectOutputStream oos = null;
	        try {
	            bos = new ByteArrayOutputStream();
	            oos = new ObjectOutputStream(bos);
	            oos.writeObject(obj);
	            oos.flush();
	            bytes = bos.toByteArray();
	        } finally {
	            if (oos != null) {
	                oos.close();
	            }
	            if (bos != null) {
	                bos.close();
	            }
	        }
	        return bytes;
	    }
}
