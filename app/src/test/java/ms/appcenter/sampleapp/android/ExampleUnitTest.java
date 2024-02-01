package ms.appcenter.sampleapp.android;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkFibonacciUnitTest() {
        int[] fibonacciSample = {1, 1, 2, 3, 5, 8, 13, 21, 34,55 };
        Fibonacci fib = new Fibonacci();

        for(int i = 0; i <= 9; i++) {
            assertEquals(fibonacciSample[i], fib.getNthElement(i + 1));
        }
    }
}