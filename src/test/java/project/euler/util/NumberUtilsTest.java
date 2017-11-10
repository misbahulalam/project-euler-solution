package project.euler.util;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static project.euler.util.NumberUtils.digits;
import static project.euler.util.NumberUtils.isPandigital;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Oct 30, 2017
 * @since 1.0
 */
public class NumberUtilsTest {

    @Test
    public void isPandigitalTests() {
        assertTrue(isPandigital(12, 1, 2));
        assertFalse(isPandigital(102, 1, 3));
        assertTrue(isPandigital(102, 0, 2));
        assertFalse(isPandigital(182, 1, 3));
        assertTrue(isPandigital(1406357289, 0, 9));
    }

    @Test
    public void digitsTests() {
        assertArrayEquals(new int[] {1, 2}, digits(12));
        assertArrayEquals(new int[] {0}, digits(0));
        assertArrayEquals(new int[] {0, 1, 2}, digits(12, 3));
        assertArrayEquals(new int[] {0, 0, 1}, digits(1, 3));
        assertArrayEquals(new int[] {0}, digits(0, 1));
        assertArrayEquals(new int[] {0, 0}, digits(0, 2));
    }

}
