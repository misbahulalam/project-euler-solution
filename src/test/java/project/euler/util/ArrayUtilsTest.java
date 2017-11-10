package project.euler.util;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Oct 30, 2017
 * @since 1.0
 */
public class ArrayUtilsTest {

    @Test
    public void updateToPreviousLexPermutationTests() {
        int[] numbers = {4, 3, 2, 1};
        ArrayUtils.updateToPreviousLexPermutation(numbers);
        assertTrue(Arrays.equals(numbers, new int[] {4, 3, 1, 2}));
    }

    @Test
    public void concatTests() {
        int[] concat = ArrayUtils.concat(new int[]{1, 2, 3}, new int[]{4, 5});
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, concat);
    }

}
