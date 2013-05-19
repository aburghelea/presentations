package ro.teamnet.presentation.reflection;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * @author Alexandru Burghelea
 * @since 26.09.2012
 */

@SuppressWarnings("UnusedDeclaration")
class ClassWithNoPublicMethodsAndNoRunnables {
    private void one() {
        throw new RuntimeException("One: Nu trebuia sa fiu appelat");
    }

    void two() {
        throw new RuntimeException("Two: Nu trebuia sa fiu appelat");
    }

    protected void three() {
        throw new RuntimeException("Three: Nu trebuia sa fiu appelat");
    }
}

class ClassWithPublicMethodAndRunnable {
    @Runnable
    public void one() {
    }
}


@SuppressWarnings("UnusedDeclaration")
class ClassWithPublicMethodAndNoRunnable {
    public void one() {
        throw new RuntimeException("One: Nu trebuia sa fiu appelat");
    }
}

class ClassWithProtectedMethodAndRunnable {
    @Runnable
    protected void one() {
    }
}


@SuppressWarnings("UnusedDeclaration")
class ClassWithProtectedMethodAndNoRunnable {
    public void one() {
        throw new RuntimeException("All good");
    }
}

class ClassWithNoPublicMethodsAndAllRunnables {
    @Runnable
    private void one() {
    }

    @Runnable
    void two() {
    }

    @Runnable
    protected void three() {
    }
}

@RunWith(BlockJUnit4ClassRunner.class)
public class ReflectionRunnerTest {
    @Test
    public void testClassWithNoPublicMethodsAndNoRunnables() throws Exception {
        int status = 0;
        String message = null;

        try {
            ReflectionRunner.executeRunnables(new ClassWithNoPublicMethodsAndNoRunnables());
        } catch (Exception e) {
            status = 1;
            message = e.getMessage();
        }
        Assert.assertEquals(message, 0, status);
    }

    @Test
    public void testClassWithPublicMethodAndRunnable() throws Exception {
        int status = 0;
        String message = null;
        try {
            ReflectionRunner.executeRunnables(new ClassWithPublicMethodAndRunnable());
        } catch (Exception e) {
            status = 1;
            message = e.getMessage();
        }
        Assert.assertEquals(message, 0, status);
    }

    @Test
    public void testClassWithPublicMethodAndNoRunnable() throws Exception {
        int status = 0;
        String message = null;
        try {
            ReflectionRunner.executeRunnables(new ClassWithPublicMethodAndNoRunnable());
        } catch (Exception e) {
            status = 1;
            message = e.getMessage();
        }
        Assert.assertEquals(message, 0, status);
    }

    @Test
    public void testClassWithProtectedMethodAndRunnable() throws Exception {
        int status = 0;
        String message = null;
        try {
            ReflectionRunner.executeRunnables(new ClassWithProtectedMethodAndRunnable());
        } catch (Exception e) {
            status = 1;
            message = e.getMessage();
        }
        Assert.assertEquals(message, 0, status);
    }

    @Test
    public void testClassWithProtectedMethodAndNoRunnable() throws Exception {
        int status = 0;
        String message = null;
        try {
            ReflectionRunner.executeRunnables(new ClassWithProtectedMethodAndNoRunnable());
        } catch (Exception e) {
            status = 1;
            message = e.getMessage();
        }

        Assert.assertEquals(message, 0, status);
    }

    @Test
    public void testClassWithNoPublicMethodsAndAllRunnables() throws Exception {
        int status = 0;
        String message = null;
        try {
            ReflectionRunner.executeRunnables(new ClassWithNoPublicMethodsAndAllRunnables());
        } catch (Exception e) {
            status = 1;
            message = e.getMessage();
        }

        Assert.assertEquals(message, 0, status);
    }
}