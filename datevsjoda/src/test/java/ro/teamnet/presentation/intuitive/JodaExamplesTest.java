package ro.teamnet.presentation.intuitive;

import org.junit.Test;
import ro.teamnet.presentation.reflection.ReflectionRunner;

/**
 * @author Alexandru Burghelea
 * @since 27.09.2012
 */
public class JodaExamplesTest {

    @Test
    public void testRunAll() throws Exception {
        JodaExamples jodaExamples = new JodaExamples();
        ReflectionRunner.executeRunnables(jodaExamples, true);
    }
}
