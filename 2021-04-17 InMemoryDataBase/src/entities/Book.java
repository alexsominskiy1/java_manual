package entities;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

import config.EntitiesConfig;

public class Book implements Comparable<Book> {
	
	private int id;    // 9 digits
	private TreeSet<Author> authors;
	private String title;
	private LocalDate publishingDate;
	private Publisher publisher;
	private double price;  // two digits after dot
	
	public Book(int id, TreeSet<Author> authors, String title, LocalDate publishingDate, Publisher publisher,
			double price) {
		super();
		this.id = id;
		this.authors = authors;
		this.title = title;
		this.publishingDate = publishingDate;
		this.publisher = publisher;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public TreeSet<Author> getAuthors() {
		return authors;
	}
	public String getTitle() {
		return title;
	}
	public LocalDate getPublishingDate() {
		return publishingDate;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public double getPrice() {
		return price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((publishingDate == null) ? 0 : publishingDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (publishingDate == null) {
			if (other.publishingDate != null)
				return false;
		} else if (!publishingDate.equals(other.publishingDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + "; " + authors + "; " + title + "; " + publishingDate + "; " + publisher + "; " + price;
	}
	
	public static Book randomBook() {
		int randomId = ThreadLocalRandom.current().nextInt(100000000, 999999999);
		TreeSet<Author> randomAuthors = Author.randomAuthors(EntitiesConfig.BOOK_MAX_NUM_AUTHORS);
		String randomTitle = RandomLib.randomStringOfArray(EntitiesConfig.BOOK_TITLES);
		LocalDate randomPublishingDate = 
				RandomLib.randomLocalDate(EntitiesConfig.BOOK_PUBLISHING_DATE_START, EntitiesConfig.BOOK_PUBLISHING_DATE_END);
		Publisher randomPublisher = Publisher.randomPublisher();
		double randomPrice = 
				EntitiesConfig.BOOK_MIN_PRICE + Math.random()*(EntitiesConfig.BOOK_MAX_PRICE - EntitiesConfig.BOOK_MIN_PRICE);
		randomPrice = ((int)(randomPrice*100.))/100.;
		
		return new Book(randomId, randomAuthors, randomTitle, randomPublishingDate, randomPublisher, randomPrice);
	}
	
	public static final Comparator<Book> bookAutorsComparator = (b1, b2) -> Author.authorsComparator(b1.getAuthors(), b2.getAuthors());
	public static final Comparator<Book> bookTitleComparator = Comparator.comparing(Book :: getTitle);
	public static final Comparator<Book> bookDateComparator = Comparator.comparing(Book :: getPublishingDate);
	public static final Comparator<Book> bookPublisherComparator = Comparator.comparing(Book :: getPublisher);
	public static final Comparator<Book> bookPriceComparator = Comparator.comparingDouble(Book :: getPrice);
	
	public static final Comparator<Book> bookComparator = bookAutorsComparator
													.thenComparing(bookTitleComparator)
													.thenComparing(bookPublisherComparator)
													.thenComparing(bookDateComparator)
													.thenComparing(bookPriceComparator);
	public int compareTo(Book other) {
		return bookComparator.compare(this, other);
	}
}
