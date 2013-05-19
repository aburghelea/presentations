package ro.teamnet.presentation.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alexandru Burghelea
 * @since 26.09.2012
 */
@SuppressWarnings("UseOfSystemOutOrSystemErr")
public final class ReflectionRunner {

    /**
     * Executes all methods annoted with {@link ro.teamnet.presentation.reflection.Runnable}
     *
     * @param instance the instance on which the {@link ro.teamnet.presentation.reflection.Runnable}
     *                 will be called
     * @param verbose  if it should print info messages
     */
    public static void executeRunnables(Object instance, boolean verbose) {
        List<String> notRun = new ArrayList<String>();
        for (Method method : instance.getClass().getDeclaredMethods()) {
            if (isRunnable(method)) {
                if (hasNoArguments(method)) {
                    runMethod(instance, method, verbose);
                } else {
                    notRun.add(method.getName() + "(" + Arrays.toString(method.getTypeParameters()) + ")");
                }
            }
        }
        if (notRun.size() > 0)
            System.err.println("Did not run the following: " + notRun);
    }

    /**
     * Executes all methods annoted with {@link ro.teamnet.presentation.reflection.Runnable}
     * Prints only error messages
     *
     * @param instance the instance on which the {@link ro.teamnet.presentation.reflection.Runnable}
     *                 will be called
     */
    public static void executeRunnables(Object instance) {
        executeRunnables(instance, false);
    }

    /**
     * Calls a specific method with no arguments
     *
     * @param instance the instance from which the method is called
     * @param method   the method to be invoked
     * @param verbose  if it should print info messages
     */
    private static void runMethod(Object instance, Method method, boolean verbose) {
        String location = instance.getClass().getName() + ":" + method.getName();
        try {
            if (verbose) {
                System.out.println("-- Trying: " + location + "() --");
            }
            method.setAccessible(true);
            method.invoke(instance);
            if (verbose) {
                System.out.println("----------------------------------------");
            }
        } catch (IllegalAccessException ignored) {
            System.err.println(location + method.getName() + "  Illegal Access " + ignored.getCause());
        } catch (InvocationTargetException ignored) {
            System.err.println(location + "Invocation Target " + ignored.getCause());
        }
    }

    /**
     * Checks if a method is annoted with {@link ro.teamnet.presentation.reflection.Runnable}
     * and is an instance method
     *
     * @param method the method to be checked
     * @return true if {@link ro.teamnet.presentation.reflection.Runnable}
     *         and is not a static method, false otherwise
     */
    private static boolean isRunnable(Method method) {
        return method.isAnnotationPresent(Runnable.class) && !Modifier.isStatic(method.getModifiers());
    }

    /**
     * Checks if the method has no arguments
     *
     * @param method the method to be checked
     * @return true if the method has no arguments, false otherwise
     */
    private static boolean hasNoArguments(Method method) {
        TypeVariable<Method>[] typeParameters = method.getTypeParameters();
        return typeParameters == null || typeParameters.length <= 0;
    }
}