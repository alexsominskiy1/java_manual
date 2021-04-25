package entities;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import config.EntitiesConfig;

public class Author implements Comparable<Author>{
	
	private static final int NUM_AUTHORS = EntitiesConfig.AUTHOR_FIRST_NAMES.length * EntitiesConfig.AUTHOR_LAST_NAMES.length;
	
	private String firstName;
	private String lastName;
	
	public Author(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
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
		return firstName + " " + lastName;
	}
	
	public static Author randomAuthor() {
		String randomFirstName = RandomLib.randomStringOfArray(EntitiesConfig.AUTHOR_FIRST_NAMES);
		String randomLastName = RandomLib.randomStringOfArray(EntitiesConfig.AUTHOR_LAST_NAMES);
		return new Author(randomFirstName, randomLastName);
	}
	
	public static final Comparator<Author> authorFirstNameComparator = Comparator.comparing(Author :: getFirstName);
	public static final Comparator<Author> authorLastNameComparator = Comparator.comparing(Author :: getLastName);
	public static final Comparator<Author> authorComparator = authorLastNameComparator
																.thenComparing(authorFirstNameComparator);
	
	public int compareTo(Author other) {
		return authorComparator.compare(this, other);
	}
	
	public static TreeSet<Author> randomAuthors(int maxNumAuthors){
		TreeSet<Author> randomAuthors = new TreeSet<>();
		int numAuthors = Integer.min(RandomLib.random.nextInt(Integer.max(1, maxNumAuthors)) + 1, NUM_AUTHORS);
		while (randomAuthors.size() < numAuthors) randomAuthors.add(randomAuthor());
		return randomAuthors;
	}
	
	public static int authorsComparator(TreeSet<Author> a1, TreeSet<Author> a2) {
		
		if (a1 == a2) return 0;
		
		Iterator<Author> it1 = a1.iterator();
		Iterator<Author> it2 = a2.iterator();
		
		int comparision = 0;
		while (it1.hasNext() && it2.hasNext() && comparision == 0) comparision = authorComparator.compare(it1.next(), it2.next());
		
		return comparision == 0 ? a1.size() - a2.size() : comparision;
	}
}
