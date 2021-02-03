package application;

import java.lang.reflect.InvocationTargetException;

import dto.Book;

public class BuilderApp {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		
		System.out.println();
		
		Book book1 = Book.builder()
						.withAuthor("Goete")
						.withTitle("Faust")
						.build();
		
		System.out.println(book1);
		
		Book.Builder bookBuilder = Book.builder()
									.withAuthor("Marx")
									.withTitle("Kapital");
		// 100500 lines of code
		
		System.out.println();
		
		
		Book book2 = bookBuilder
						.withYear(1919)
						.build();
		
		System.out.println(book2);
															
	}

}
