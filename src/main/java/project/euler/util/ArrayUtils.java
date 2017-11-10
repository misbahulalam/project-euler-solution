package project.euler.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Oct 25, 2017
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

    public static void updateToPreviousLexPermutation(int[] input) {
        for (int i = input.length - 2; i >= 0; i--) {
            for (int j = input.length - 1; j > i; j--) {
                if (input[j] < input[i]) {
                    swapPosition(input, i, j);
                    sortSubArrayDesc(input, i + 1);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Value " + Arrays.toString(input) + " have the lowest lexicographic value.");
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

    private static void sortSubArrayDesc(int[] arr, int startPosition) {
        if (startPosition == arr.length - 1) return;

        for (int i = startPosition; i <= arr.length - 2; i++) {
            for (int j = i + 1; j <= arr.length - 1; j++) {
                if (arr[i] < arr[j]) {
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

    public static int[] arrayMinus(int[] original, int[] minus) {
        int nullFlag = Integer.MIN_VALUE;
        int[] copy = Arrays.copyOf(original, original.length);

        for (int m : minus) {
            for (int i = 0; i < copy.length; i++) {
                if (copy[i] == m) {
                    copy[i] = nullFlag;
                    break;
                }
            }
        }

        int[] arr = new int[copy.length - minus.length];
        int index = 0;
        for(int x : copy) {
            if (x != nullFlag) {
                arr[index++] = x;
            }
        }

        return arr;
    }

    public static int[] concat(int[] first, int[] last) {
        int[] result = new int[first.length + last.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(last, 0, result, first.length, last.length);
        return result;
    }

}
