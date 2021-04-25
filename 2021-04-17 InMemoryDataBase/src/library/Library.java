package library;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import entities.Author;
import entities.Book;
import entities.Publisher;
import multyvaluemap.MultiValueHashMap;
import multyvaluemap.MultiValueTreeMap;

public class Library {
	
	private HashMap<Integer, Book> idMap = new HashMap<>();
	
	private MultiValueHashMap<Author, Book> authorMap = new MultiValueHashMap<>();
	private MultiValueHashMap<String, Book> titleMap = new MultiValueHashMap<>();
	private MultiValueTreeMap<LocalDate, Book> publishingDateMap = new MultiValueTreeMap<>();
	private MultiValueHashMap<Publisher, Book> publisherMap = new MultiValueHashMap<>();
	private MultiValueTreeMap<Double, Book> priceMap = new MultiValueTreeMap<>();
	
	public static Library of(int numBooks) {
		Library library = new Library();
		while (library.size() < numBooks) library.addBook(Book.randomBook());
		return library;
	}
	
	public static Library of(Iterable<Book> data) {
		Library library = new Library();
		for (Book book : data) library.addBook(book);
		return library;
	}
	
	public boolean addBook(Book book) {
		if(idMap.get(book.getId()) != null) return false;
		
		// one to one
		
		idMap.put(book.getId(), book);
		
		// one to many
		
		titleMap.add(book.getTitle(), book);
		publishingDateMap.add(book.getPublishingDate(), book);
		publisherMap.add(book.getPublisher(), book);
		priceMap.add(book.getPrice(), book);
		
		// many to many
		
		for (Author author : book.getAuthors())authorMap.add(author,  book);
		
		return true;
	}
	
	public boolean removeBook(int id) {
		
		Book book = idMap.get(id);
		if (book == null) return false;
		
		// one to one
		
		idMap.remove(id);
		
		// one to many
		
		titleMap.remove(book.getTitle(), book);titleMap.remove(book.getTitle(), book);
		publishingDateMap.remove(book.getPublishingDate(), book);
		publisherMap.remove(book.getPublisher(), book);
		priceMap.remove(book.getPrice(), book);
		
		// many to many
		
		for (Author author : book.getAuthors())authorMap.remove(author,  book);
		
		return true;
	}
	
	public boolean removeBooks(Iterable<Integer> ids) {
		boolean result = false;
		for (Integer id : ids) result = removeBook(id) || result;
		return true;
	}

	public boolean replace(Book oldBook, Book newBook) {
		boolean removeResult = removeBook(oldBook.getId());
		return removeResult && addBook(newBook);
	}
	
	// get keys 
	
	public TreeSet<Author> getAuthors(){
		return authorMap.getKeys();
	}
	
	public TreeSet<String> getTitles(){
		return titleMap.getKeys();
	}
	
	public TreeSet<Publisher> getPublishers(){
		return publisherMap.getKeys();
	}
	
	// get books
	
	public Book getBookById(int id) {
		return idMap.get(id);
	}
	
	public Iterable<Book> getAllBooks() {
		return new HashSet<Book>(idMap.values());
	}
	
	public Iterable<Book> getBooksByAuthor(Author author) {
		return authorMap.get(author);
	}
	
	public Iterable<Book> getBooksByAuthor(String firstName, String lastName){
		return getBooksByAuthor(new Author(firstName, lastName));
	}
	
	public Iterable<Book> getBooksByAuthors(TreeSet<Author> authors){
		return authorMap.getByKeys(authors);
	}
	
	public Iterable<Book> getBooksByTitle(String title){
		return titleMap.get(title);
	}
	
	public Iterable<Book> getBooksByPublisher(Publisher publisher){
		return publisherMap.get(publisher);
	}
	
	public Iterable<Book> getBooksByPublisher(String country, String name){
		return getBooksByPublisher(new Publisher(country, name));
	}
	
	public Iterable<Book> getBookByPublisherCountry(String country){
		TreeSet<Publisher> publishers = getPublishers();
		publishers.removeIf(publisher -> !publisher.getCountry().equals(country));
		return publisherMap.getByKeys(publishers);
	}
	
	// remove 
		
	public boolean removeAuthor(Author author) {
		HashSet<Integer> ids = new HashSet<>();
		for(Book book : authorMap.get(author))ids.add(book.getId());
		return removeBooks(ids);
	}
	
	// sorted
	
	public Iterable<Book> getAllBooksSorted(){
		return new TreeSet<Book>(idMap.values());
	}
	
	public Iterable<Book> getAllBooksSortedByPublishingDate(){
		return publishingDateMap.getSorted();
	}
	
	public Iterable<Book> getAllBooksSortedByPrice(){
		return priceMap.getSorted();
	}
	
	public Iterable<Book> getAllBooksSortedByTitle(){
		return titleMap.getSorted();
	}
	
	// extremums
	
	public Iterable<Book> getCheapestBooks(){
		return priceMap.getMin();
	}
	
	public Iterable<Book> getMostExpensiveBooks(){
		return priceMap.getMax();
	}
	
	public Iterable<Book> getOldestBooks(){
		return publishingDateMap.getMin();
	}
	
	public Iterable<Book> getNewestBooks(){
		return publishingDateMap.getMax();
	}
	
	// ranges
	
	public Iterable<Book> getBooksInPublishingDateRange(LocalDate from, LocalDate to){
		return publishingDateMap.getInRange(from, to);
	}
	
	public Iterable<Book> getBooksInPriceRange(double from, double to){
		return priceMap.getInRange(from, to);
	}
	
	// size
	
	public int size() {
		return idMap.size();
	}

}
