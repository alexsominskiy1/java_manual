package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JSONparsers {

	private static HashMap<Class<?>, Method> parsersMap = new HashMap<>();
	static {
		try {
			parsersMap.put(byte.class, Byte.class.getMethod("parseByte", String.class));
			parsersMap.put(short.class, Short.class.getMethod("parseShort", String.class));
			parsersMap.put(char.class, JSONparsers.class.getDeclaredMethod("parseChar", String.class));
			parsersMap.put(int.class, Integer.class.getMethod("parseInt", String.class));
			parsersMap.put(long.class, Long.class.getMethod("parseLong", String.class));
			parsersMap.put(float.class, Float.class.getMethod("parseFloat", String.class));
			parsersMap.put(double.class, Double.class.getMethod("parseDouble", String.class));
			parsersMap.put(boolean.class, Boolean.class.getMethod("parseBoolean", String.class));
			parsersMap.put(String.class, JSONparsers.class.getDeclaredMethod("parseString", String.class));
			parsersMap.put(LocalDate.class, LocalDate.class.getMethod("parse", CharSequence.class));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@SuppressWarnings("unused")
	private static char parseChar(String c) {
		return c.charAt(0);
	}	
	@SuppressWarnings("unused")
	private static String parseString(String str) {
		return str;
	}
	
	public static List<Object> parseData(Reader inputStream, Class<?> clazz) throws JSONparsersException  {
		
		HashMap<Field, Method> settersMap = null;
		try {
			settersMap = getClassSetters(clazz);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new JSONparsersException(e);
		}
		
		ArrayList<Object> result = new ArrayList<>();
		try (
			BufferedReader bufferedReader = new BufferedReader(inputStream);
		){
			while(true) {
				String line = bufferedReader.readLine();
				if(line == null || line.length() == 0) break;
				try {
					Object target = clazz.getConstructor().newInstance();
					parseJSONline(line, target, settersMap);
					result.add(target);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| NoSuchMethodException | SecurityException | InvocationTargetException e) {	
					throw new JSONparsersException(e);
				}
			}
		} catch (IOException e) {e.printStackTrace();}
		return result;
	}

	private static void parseJSONline(String line, Object target, HashMap<Field, Method> settersMap) 
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, 
			       NoSuchMethodException, SecurityException, InvocationTargetException {
		
		HashMap<String, String> jsonMap = getJsonMap(line);
		
		for(Field field : settersMap.keySet()) {
			String fieldValue = jsonMap.get(field.getName());
			if (fieldValue != null)
				try {
					settersMap.get(field).invoke(target, parsersMap.get(field.getType()).invoke(null, fieldValue));
				}
				catch (InvocationTargetException e) {
					System.err.println("Wrong data format for field "+field.getName()+". Default value set.");
					System.err.println(line);
				}
			else {
				System.err.println("No data specified for field "+field.getName()+". Default value set.");
				System.err.println(line);
			}
		}		
	}
	
	private static HashMap<Field, Method> getClassSetters(Class<?> clazz) throws NoSuchMethodException, SecurityException{
		
		HashMap<Field, Method> settersMap = new HashMap<>();
		String fieldName = null;
		String setterName = null;
		
		for(Field field : clazz.getDeclaredFields()) {
			fieldName = field.getName();
			setterName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
			settersMap.put(field, clazz.getMethod(setterName, field.getType()));
		}
		
		return settersMap;
	}
	
	private static HashMap<String,String> getJsonMap(String line){
		
		HashMap<String, String> jsonMap = new HashMap<>();
		
		String[] entries = line.substring(1, line.length()-1).split(",");
		for (String str : entries) {
			String[] entry = str.split(": ");
			jsonMap.put(entry[0].trim(), entry[1].trim());
		}
		return jsonMap;		
	}

	
	
	
}
