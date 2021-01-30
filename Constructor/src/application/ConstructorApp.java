package application;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class ConstructorApp {

	public static void main(String[] args) throws Exception {
		
		Class<Foo> clazz = Foo.class;
		
		System.out.println("--All--");
	
		Constructor<?>[] declared = clazz.getDeclaredConstructors();
		for(Constructor<?> constructor : declared)System.out.println(constructor);
		
		System.out.println("--Public--");

		Constructor<?>[] own = clazz.getConstructors();
		for(Constructor<?> constructor : own)System.out.println(constructor);
		
		System.out.println("--All--");
		Constructor<Foo> fooPrivateConstructor = clazz.getDeclaredConstructor(String.class);
		System.out.println(fooPrivateConstructor);
	
		System.out.println("--Public--");
		Constructor<Foo> fooPublicConstructor = clazz.getConstructor(int.class);
		System.out.println(fooPublicConstructor);
		
		
		Constructor<Foo> fooConstructor = Foo.class.getConstructor(int.class, String.class);
		System.out.println(Arrays.toString(fooConstructor.getParameterTypes()));
		System.out.println(fooConstructor.getDeclaringClass());
		
		System.out.println((Foo)Foo.class.getConstructor().newInstance());
		System.out.println((Foo)Foo.class.getConstructor(int.class, String.class).newInstance(3, "hello"));
		
		Constructor<Foo> privateConstructor = Foo.class.getDeclaredConstructor(String.class);
		privateConstructor.setAccessible(true);
		privateConstructor.newInstance("hello");
		
	}
}
