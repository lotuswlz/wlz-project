package time;

import java.util.Calendar;
import java.util.TimeZone;

public class TestTime {

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		TimeZone tz = TimeZone.getDefault();
		System.out.println(TimeZone.getTimeZone("AU").getRawOffset()/3600000);	
		System.out.println(tz.getDisplayName());
		
//		String[] arr = TimeZone.getAvailableIDs();
//		System.out.println("" + arr.length);
//		for(int i = 0; i < arr.length; i++){
//			System.out.println(arr[i]);
//		}
	}
}
