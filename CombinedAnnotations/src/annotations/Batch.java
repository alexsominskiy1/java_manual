package annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@A
@B
@C
@Retention(RUNTIME)
@Target(TYPE)
public @interface Batch {}
