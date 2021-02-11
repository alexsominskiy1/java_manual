package validators.builtin_annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;

import validators.ValidatorAnnotation;

@Retention(RUNTIME)
@Target(FIELD)
@ValidatorAnnotation(valueType = LocalDate.class)
public @interface LocalDateRange {
	String from();
	String to();
	String message() default  "Value must be not null and in range ";
}
