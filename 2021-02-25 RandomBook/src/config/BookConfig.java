package config;

import java.time.LocalDate;

public class BookConfig {
	
	public static final String[] AUTHOR_FIRST_NAMES = {
		"John", "Thomas", "Robert", "William", "George",
		"Alice", "Nancy", "Mary", "Margaret", "Elisabeth"
	};
	
	public static final String[] AUTHOR_LAST_NAMES = {
			"Smith", "Johnson", "Williams", "Brown", "Jones",
			"Morgan", "Davis", "Wilson", "Anderson", "Garcia"
	};
	
	public static final int MAX_AUTHORS_NUMBER = 3;
	
	public static final String[] TITLES = {
			"Fire and Ice", "Eagle and Snake", "Potato and Tomato", "Milk and Water", "Black and White",
			"Earth and Sky", "Love and Hate", "Black and Red", "Wood and Iron", "Day and Night"
	};
	
	public static final LocalDate MIN_PUBLISHING_DATE = LocalDate.of(1990, 1, 1);
	public static final LocalDate MAX_PUBLISHING_DATE = LocalDate.of(2020, 12, 31);
	
	public static final double PICTURES_PROBABILITY = 0.25;
	
	public static final double MIN_PRICE = 100.;
	public static final double MAX_PRICE = 1000.;
	
	
}
