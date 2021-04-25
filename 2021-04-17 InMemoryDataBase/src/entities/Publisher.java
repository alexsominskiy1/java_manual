package entities;

import java.util.Comparator;

import config.EntitiesConfig;

public class Publisher implements Comparable<Publisher>{
		
	private String country;
	private String name;
	
	public Publisher(String country, String name) {
		super();
		this.country = country;
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Publisher other = (Publisher) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "'"+ name + "', " + country;
	}
	
	public static Publisher randomPublisher() {
		String randomCountry = RandomLib.randomStringOfArray(EntitiesConfig.PUBLISHER_COUNTRIES);
		String randomName = RandomLib.randomStringOfArray(EntitiesConfig.PUBLISHER_NAMES);
		return new Publisher(randomCountry, randomName);
	}
	
	public static final Comparator<Publisher> publisherNameComparator = Comparator.comparing(Publisher :: getName);
	public static final Comparator<Publisher> publisherCountryComparator = Comparator.comparing(Publisher :: getCountry);
	public static final Comparator<Publisher> publisherComparator = publisherCountryComparator
																	.thenComparing(publisherNameComparator);
	
	public int compareTo(Publisher other){
		return publisherComparator.compare(this, other);
	}
}
