package dto;


import java.lang.reflect.Field;
import java.util.function.Function;

public class MuteString {
	
	public static void muteString(String subj, Function<String,String> mute) { 
		
		try {
			Field stringValue = String.class.getDeclaredField("value");
			stringValue.setAccessible(true);
			byte[] oldValue = (byte[]) stringValue.get(subj);
			String mutatedString = mute.apply(new String(oldValue));
			stringValue.set(subj, mutatedString.getBytes());
		} 
		catch (Exception e){/* nop */}
	}

}
