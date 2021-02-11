package dto;

import java.time.LocalDate;

public class Person {
	
	private long id;
	private String name;
	private LocalDate birthday;
	private byte accessCode;
	private short privateCode;
	private char group;
	private int age;
	private float weight;
	private double height;
	private boolean married;
	
	public static class Builder{
		Person newPerson;
		
		public Builder() {
			this.newPerson = new Person();
		}
		
		public Builder withId(long id) {
			newPerson.id = id;
			return this;
		}
		
		public Builder withName(String name) {
			newPerson.name = name;
			return this;
		}
		
		// the same for each field
	
		public Person build() {
			return newPerson;
		}
	}
	
	// getters, setters, hash, equals and everything you want
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birthday=" + birthday + ", accessCode=" + accessCode
				+ ", privateCode=" + privateCode + ", group=" + group + ", age=" + age + ", weight=" + weight
				+ ", height=" + height + ", married=" + married + "]";
	}
	
	

}
