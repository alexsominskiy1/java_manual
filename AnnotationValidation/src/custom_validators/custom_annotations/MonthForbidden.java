package custom_validators.custom_annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.Month;

import validators.ValidatorAnnotation;

@Retention(RUNTIME)
@Target(FIELD)
@ValidatorAnnotation(className="custom_validators.CustomValidators", 
                     method = "monthForbidden", 
                     valueType = LocalDate.class)
public @interface MonthForbidden {
	
	Month month();
	String message() default "Born at ";
}
