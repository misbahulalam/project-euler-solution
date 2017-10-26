package project.euler.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:misbah@cefalo.com">Misbahul Alam Chowdhury</a> on Oct 25, 2017
 * @since 1.0
 */
public class ArrayUtils extends org.apache.commons.lang3.ArrayUtils {

    public static void updateToNextLexPermutation(int[] input) {
        for (int i = input.length - 2; i >= 0; i--) {
            for (int j = input.length - 1; j > i; j--) {
                if (input[j] > input[i]) {
                    swapPosition(input, i, j);
                    sortSubArray(input, i + 1);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Value " + Arrays.toString(input) + " have the highest lexicographic value.");
    }

    private static void sortSubArray(int[] arr, int startPosition) {
        if (startPosition == arr.length - 1) return;

        for (int i = startPosition; i <= arr.length - 2; i++) {
            for (int j = i + 1; j <= arr.length - 1; j++) {
                if (arr[i] > arr[j]) {
                    swapPosition(arr, i, j);
                }
            }
        }
    }

    private static void swapPosition(int[] arr, int p1, int p2) {
        int tmp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = tmp;
    }

    public static Set<Integer> toSet(int[] numbers) {
        return Arrays.stream(toObject(numbers)).collect(Collectors.toSet());
    }

}
