package project.euler.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Oct 30, 2017
 * @since 1.0
 */
public class NumberUtilsTest {

    @Test
    public void isPandigitalTests() {
        assertTrue(NumberUtils.isPandigital(12, 2));
        assertFalse(NumberUtils.isPandigital(102, 3));
        assertFalse(NumberUtils.isPandigital(182, 3));
    }
}
