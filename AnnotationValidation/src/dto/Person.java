package dto;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
//import custom_validators.custom_annotations.LegalMarriage;
import custom_validators.custom_annotations.MonthForbidden;
import validators.Validate;
import validators.ValidationException;
import validators.Validators;
import validators.builtin_annotations.DoubleRange;
import validators.builtin_annotations.LocalDateRange;
import validators.builtin_annotations.PatternString;

@Person.LegalMarriage(marriageLegalAge = 18)
public class Person {
	
	@Retention(RUNTIME)
	@Target(TYPE)
	public static @interface LegalMarriage {
		int marriageLegalAge();
		String message() default "Too young to be married. Legal marriage age is ";
	}
	
	private final Class<Person> pClass = Person.class;
	
	@Validate
	@PatternString(pattern="^[A-Z]\\w*")
	private String name;
	
	@Validate
	@LocalDateRange(from = "1901-01-01", to="2021-01-01")
	@MonthForbidden(month=Month.FEBRUARY)
	private LocalDate birthDate;
	
	@Validate
	@DoubleRange(min=40., max=100.)
	private double weight;
	
	private boolean married;
	
	public Person(String name, LocalDate birthDate, double weight, boolean married) {

		setName(name);
		setBirthDate(birthDate);
		setWeight(weight);
		setMarried(married);
		
		LegalMarriage ann = Person.class.getAnnotation(LegalMarriage.class);
		if (ann != null) {
			int age = Period.between(this.birthDate, LocalDate.now()).getYears();
			if(this.married && age < ann.marriageLegalAge())
				throw new ValidationException("\n  Wrong data: " + this + ". " + ann.message() + ann.marriageLegalAge());
		}	
	}
	
	public String getName() {
		return name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public double getWeight() {
		return weight;
	}
	public boolean isMarried() {
		return married;
	}	
	
	public void setName(String name){	
		Validators.validateField(pClass, "name", name);
		this.name = name;
	}

	public void setBirthDate(LocalDate birthDate) {
		Validators.validateField(pClass, "birthDate", birthDate);
		this.birthDate = birthDate;
	}

	public void setWeight(double weight) {
		Validators.validateField(pClass, "weight", weight);
		this.weight = weight;
	}

	public void setMarried(boolean married) {
		Validators.validateField(pClass, "weight", weight);
		this.married = married;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", birthDate=" + birthDate + ", weight=" + weight + ", married=" + married + "]";
	}
}
