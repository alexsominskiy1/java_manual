package application;

import entities.Author;
import entities.Book;
import entities.Publisher;
import library.Library;

public class InMemoryDataBaseApp {

	public static void main(String[] args) {
		
		Library lib = Library.of(30);
		
		for(Book book : lib.getAllBooksSorted()) System.out.println(book);
		System.out.println(lib.size());
		lib.removeAuthor(new Author("Mary", "Smith"));
		for(Book book : lib.getAllBooksSortedByPublishingDate()) System.out.println(book);
		System.out.println(lib.size());
		for(Book book : lib.getAllBooksSortedByTitle())System.out.println(book);
	}

}
