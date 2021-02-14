package validators.builtin_annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import validators.ValidatorAnnotation;

@Retention(RUNTIME)
@Target(FIELD)
@ValidatorAnnotation(valueType = String.class)
public @interface PatternString {
	public String pattern();
	public String message() default "String must be not null and match pattern ";
}
