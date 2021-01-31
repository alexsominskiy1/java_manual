package application;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import dto.Child;

public class MethodApp {

	public static void main(String[] args) throws Exception {
		
		Class<Child> clazz = Child.class;
		
		System.out.println("--Declared--");
		
		Method[] declared = clazz.getDeclaredMethods();
		for (Method method : declared)System.out.println(method);
		
		System.out.println("--Own--");
		
		Method[] own = clazz.getMethods();
		for (Method method : own)System.out.println(method);
		
		System.out.println("--public--");
		Method childPublicMethod = clazz.getMethod("childPublicMethod", int.class, String.class);
		System.out.println(childPublicMethod);
	
		System.out.println("--private--");
		Method childPrivateMethod = clazz.getDeclaredMethod("childPrivateMethod", int.class, String.class);
		System.out.println(childPrivateMethod);
		
		Method parentStaticMetod = Child.class.getMethod("parentStaticMethod", int.class, String.class);
		System.out.println(parentStaticMetod);
		
		System.out.println(parentStaticMetod.getName());
		System.out.println(parentStaticMetod.getDeclaringClass());
		System.out.println(Arrays.toString(parentStaticMetod.getParameterTypes()));
		System.out.println(parentStaticMetod.getReturnType());
		
		int mod = parentStaticMetod.getModifiers();
		System.out.println(Modifier.isPublic(mod));
		System.out.println(Modifier.isStatic(mod));
		System.out.println(Modifier.isFinal(mod));
		System.out.println(Modifier.toString(mod));
		
		Child child = new Child();
		
		Method stat = Child.class.getMethod("parentStaticMethod", int.class, String.class);
		stat.invoke(null, 33, "another string");
		
		Method cpm = Child.class.getMethod("childPublicMethod", int.class, String.class);
		String output = String.class.cast(cpm.invoke(child, 28, "some string"));
		System.out.println(output);
		
		
	

	}

}
