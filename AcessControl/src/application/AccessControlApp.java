package application;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dto.Foo;
import dto.MuteString;

public class AccessControlApp {

	public static void main(String[] args) throws Exception {
		
		Foo foo = new Foo();
		Class<Foo> fooClass = Foo.class;
		
		// public
		
		Field publicField = fooClass.getDeclaredField("publicField");
		System.out.println(publicField.getInt(foo));
		
		Method publicMethod = fooClass.getDeclaredMethod("publicMethod");
		publicMethod.invoke(foo);
			
		// private
		
		Field privateField = fooClass.getDeclaredField("privateField");
		Field anotherPrivateField = fooClass.getDeclaredField("privateField");
		privateField.setAccessible(true);										
		privateField.setInt(foo, 7);					//privateField open
		//anotherPrivateField.setInt(foo, 9);				//anotherPrivateField NOT open
		System.out.println(foo);
		
		Method privateMethod = fooClass.getDeclaredMethod("privateMethod");
		privateMethod.setAccessible(true);
		privateMethod.invoke(foo);

		String subj = "hello, world!";
		System.out.println(subj);
		MuteString.muteString(subj, s -> s.toUpperCase());
		System.out.println(subj);

	}

}
