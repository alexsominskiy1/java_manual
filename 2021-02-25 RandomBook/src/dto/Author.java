package dto;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

import static config.BookConfig.*;
import static randomLib.RandomLib.*;

public class Author implements Comparable<Author>{
	
	private static int AUTHORS_NUMBER_UPPER_LIMIT =  AUTHOR_FIRST_NAMES.length * AUTHOR_LAST_NAMES.length;
	
	private String firstName;
	private String lastName;
	
	public Author() {}
	public Author(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		Author other = (Author) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return lastName + " " + firstName;
	}
	@Override
	public int compareTo(Author other) {
		return toString().compareTo(other.toString());
	}
	
	public static Author randomAuthor() {
		return new Author(randomStringOfArray(AUTHOR_FIRST_NAMES),
				          randomStringOfArray(AUTHOR_LAST_NAMES));
	}
	
	public static Set<Author> randomAuthorsSet(){
		int numAuthors = Integer.min(1 + ThreadLocalRandom.current().nextInt(MAX_AUTHORS_NUMBER),
				                     AUTHORS_NUMBER_UPPER_LIMIT);
		TreeSet<Author> authors = new TreeSet<>();
		while (authors.size() < numAuthors)authors.add(randomAuthor());
		return authors;		
	}
	
	public static int compareAuthorsSets(Set<Author> authors1, Set<Author> authors2) {
		Iterator<Author> it1 = authors1.iterator();
		Iterator<Author> it2 = authors2.iterator();
		while (it1.hasNext() && it2.hasNext()) {
			int comparision = it1.next().compareTo(it2.next());
			if (comparision != 0) return comparision;
		}
		return authors1.size() - authors2.size();
	}
}
