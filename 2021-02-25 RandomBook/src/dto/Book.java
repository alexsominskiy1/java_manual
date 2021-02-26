package dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static config.BookConfig.*;
import static randomLib.RandomLib.*;

public class Book implements Comparable<Book>{
	
	public static Comparator<Book> authorsComparator = (book1, book2) -> Author.compareAuthorsSets(book1.getAuthors(), book2.getAuthors());
	public static Comparator<Book> titleComparator = (book1, book2) -> book1.getTitle().compareTo(book2.getTitle());
	public static Comparator<Book> publishingDateComparator = (book1, book2) -> book1.getPublishingDate().compareTo(book2.getPublishingDate());
	public static Comparator<Book> picturesComparator = 
			                                   (book1, book2) -> book1.isPictures() == book2.isPictures() ? 0 : book1.isPictures() ? 1 : -1;
	public static Comparator<Book> priceComparator = (book1, book2) -> Double.compare(book1.getPrice(), book2.getPrice());
	
	public static Comparator<Book> defaultComparator = authorsComparator
									    .thenComparing(titleComparator)
									    .thenComparing(publishingDateComparator)
									    .thenComparing(priceComparator)
									    .thenComparing(picturesComparator);
													 
	
	private long isbn;     			// 13 digits
	private Set<Author> authors;
	private String title;
	private LocalDate publishingDate;
	private boolean pictures;
	private double price;		   // 2 digits after dot
	
	public Book() {}

	public Book(long isbn, Set<Author> authors, String title, LocalDate publisingDate, boolean pictures, double price) {
		super();
		this.isbn = isbn;
		this.authors = authors;
		this.title = title;
		this.publishingDate = publisingDate;
		this.pictures = pictures;
		this.price = price;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(LocalDate publishingDate) {
		this.publishingDate = publishingDate;
	}

	public boolean isPictures() {
		return pictures;
	}

	public void setPictures(boolean pictures) {
		this.pictures = pictures;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (isbn ^ (isbn >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (isbn != other.isbn)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return isbn + ", " + 
	           authors + ", " + 
			   title + ", " + 
	           publishingDate + ", " + 
			   pictures + ", " + 
	           price;
	}
	
	public static Book randomBook() {
		long randomIsbn = ThreadLocalRandom.current().nextLong(1000000000000L, 9999999999999L);
		while (randomIsbn < 1000000000000L) randomIsbn = randomIsbn*10;
		Set<Author> randomAuthors = Author.randomAuthorsSet();
		String randomTitle = randomStringOfArray(TITLES);
		LocalDate randomPublishingDate = randomLocalDate(MIN_PUBLISHING_DATE, MAX_PUBLISHING_DATE);
		boolean randomPictures = randomBoolean(PICTURES_PROBABILITY);
		double randomPrice = ThreadLocalRandom.current().nextDouble(MIN_PRICE, MAX_PRICE);
		randomPrice = Math.round(randomPrice*100.)/100.;
		
		return new Book (randomIsbn, randomAuthors, randomTitle, randomPublishingDate, randomPictures, randomPrice);
	}

	@Override
	public int compareTo(Book other) {
		return Book.defaultComparator.compare(this, other);
	}
	
	
	
	
	
	

}
