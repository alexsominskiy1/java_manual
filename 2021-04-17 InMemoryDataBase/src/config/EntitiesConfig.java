package config;

import java.time.LocalDate;

public class EntitiesConfig {
	
	public static final String[] PUBLISHER_COUNTRIES = {"USA", "China", "Russia", "France", "Germany"};
	public static final String[] PUBLISHER_NAMES = {"Golden Star", "Silver Moon", "Black night", "Green way", "Red light"};
	
	public static final String[] AUTHOR_FIRST_NAMES = {"John", "Mary", "Robert", "Jenny", "William"};
	public static final String[] AUTHOR_LAST_NAMES = {"Smith", "Brown", "Fisher", "Buttler", "Tailor"};
	public static final int BOOK_MAX_NUM_AUTHORS = 3;
	
	public static final String[] BOOK_TITLES = {"Red and Black", "Bread and Milk", "Blood and Water", "Earth and Moon", "Eagle and Snake",
			"Ice and Fire", "Salt and Pepper", "Shark and Whale", "Cat and Dog", "Love and Hate"};
			
	public static final LocalDate BOOK_PUBLISHING_DATE_START = LocalDate.of(1980,1,1);
	public static final LocalDate BOOK_PUBLISHING_DATE_END = LocalDate.of(2021,1,1);
	
	public static final double BOOK_MIN_PRICE = 100.00;
	public static final double BOOK_MAX_PRICE = 1000.00;
}
