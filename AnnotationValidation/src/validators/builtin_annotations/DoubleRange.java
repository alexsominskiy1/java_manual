package validators.builtin_annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import validators.ValidatorAnnotation;

@Retention(RUNTIME)
@Target(FIELD)
@ValidatorAnnotation(valueType = double.class)
public @interface DoubleRange {
	
	public double min();
	public double max();
	
	public String message() default "Value must be in range ";
}
