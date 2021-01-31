package application;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import dto.Child;

public class FieldApp {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		
		Class<Child> clazz = Child.class;
		
		System.out.println("--Declared--");
		
		Field[] declared = clazz.getDeclaredFields();
		for (Field field : declared)System.out.println(field);
		
		System.out.println("--Own--");
		
		Field[] own = clazz.getFields();
		for (Field field : own)System.out.println(field);
		
		System.out.println("--public--");
		Field childPublicField = clazz.getField("childPublicField");
		System.out.println(childPublicField);
		
		System.out.println("--private--");
		Field childPrivateField = clazz.getDeclaredField("childPrivateField");
		System.out.println(childPrivateField);
		
		Field childStaticField = clazz.getField("childStaticField");
		System.out.println(childStaticField.getName());
		
		int modifiers = childStaticField.getModifiers();
		System.out.println(modifiers);
		System.out.println(Modifier.isPublic(modifiers));
		System.out.println(Modifier.isFinal(modifiers));
		System.out.println(Modifier.toString(modifiers));
	}

}
