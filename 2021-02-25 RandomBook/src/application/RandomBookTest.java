package application;

import java.util.stream.Stream;

import dto.Book;

public class RandomBookTest {
	
	public static void main(String[] args) {
		Stream.generate(Book::randomBook).limit(30).sorted().forEach(System.out::println);
	}

}
