package ro.teamnet.presentation.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks instance methods that they can be invoked by
 * reflection by {@link ro.teamnet.presentation.reflection.ReflectionRunner}
 *
 * @author Alexandru Burghelea
 * @since 26.09.2012
 */

@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Runnable {
}