package config;

import java.time.LocalDate;

public class CarConfig {
	
public static final String[] MODELS = {"Ford", "Toyota", "Audi", "Fiat", "Bentley"};
	
	public static final LocalDate MIN_PRODUCTION_DATE = LocalDate.of(2000, 1, 1);   // 2000 Jan 01
	public static final LocalDate MAX_PRODUCTION_DATE = LocalDate.of(2021, 1, 1);   // 2021 Jan 01
	
	public static final double MIN_ENGINE = 1.0;
	public static final double MAX_ENGINE = 2.5;
	
	public static final double AIR_CONDITIONER_PROBABILITY = 0.8;

}
