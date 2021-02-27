package dto;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import application.RandomLib;
import config.CarConfig;

public class Car {
	
	private String model;
	private LocalDate productionDate;
	private double engine;
	private boolean airConditioner;
	
	public Car() {
		super();
	}

	public Car(String model, LocalDate productionDate, double engine, boolean airConditioner) {
		super();
		this.model = model;
		this.productionDate = productionDate;
		this.engine = engine;
		this.airConditioner = airConditioner;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public LocalDate getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(LocalDate productionDate) {
		this.productionDate = productionDate;
	}

	public double getEngine() {
		return engine;
	}

	public void setEngine(double engine) {
		this.engine = engine;
	}

	public boolean isAirConditioner() {
		return airConditioner;
	}

	public void setAirConditioner(boolean airConditioner) {
		this.airConditioner = airConditioner;
	}

	@Override
	public String toString() {
		return "Car [model=" + model + ", productionDate=" + productionDate + ", engine=" + engine + ", airConditioner="
				+ airConditioner + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (airConditioner ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(engine);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((productionDate == null) ? 0 : productionDate.hashCode());
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
		Car other = (Car) obj;
		if (airConditioner != other.airConditioner)
			return false;
		if (Double.doubleToLongBits(engine) != Double.doubleToLongBits(other.engine))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (productionDate == null) {
			if (other.productionDate != null)
				return false;
		} else if (!productionDate.equals(other.productionDate))
			return false;
		return true;
	}
	
	public static Car randomCar() {
		
		String randomModel = RandomLib.randomStringOfArray(CarConfig.MODELS);
		LocalDate randomProductionDate = RandomLib.randomLocalDate(CarConfig.MIN_PRODUCTION_DATE,CarConfig.MAX_PRODUCTION_DATE);
		double randomEngine = ThreadLocalRandom.current().nextDouble(CarConfig.MIN_ENGINE, CarConfig.MAX_ENGINE);
		randomEngine = (Math.round(randomEngine*10.))/10.;
		boolean randomAirConditioner = RandomLib.randomBoolean(CarConfig.AIR_CONDITIONER_PROBABILITY);
		
		return new Car(randomModel, randomProductionDate, randomEngine, randomAirConditioner);
	}
	
}
