package project.euler;

import project.euler.util.NumberUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.IntStream;

public class OneToTwentyFive {

    public static void main(String... args) {

    }

    private static void seventeen() {
        int lettersCount = IntStream.rangeClosed(1, 1000).mapToObj(NumberUtils::numberInWord).mapToInt(String::length).sum();
        System.out.println(lettersCount);
    }


    private static void twentyFour() {
        int[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int c = 1;
        while (c++ < 1_000_000) {
            updateToNextLexPermutation(data);
        }
        System.out.println(Arrays.toString(data));

    }

    private static void updateToNextLexPermutation(int[] input) {
        for (int i = input.length - 2; i >= 0; i--) {
            for (int j = input.length - 1; j > i; j--) {
                if (input[j] > input[i]) {
                    swapPosition(input, i, j);
                    sortSubArray(input, i + 1);
                    return;
                }
            }
        }
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

    private static boolean isDescSorted(int[] arr) {
        if (arr.length == 1) return true;

        for (int i = 0; i <= arr.length - 2; i++) {
            if (arr[i] < arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static void twentyFive() {
        int order = 2;
        BigDecimal last = new BigDecimal(1), current = new BigDecimal(1), tmp;
        int digitCount = current.toPlainString().length();

        while (digitCount < 1_000) {
            tmp = current;
            current = current.add(last);
            last = tmp;
            digitCount = current.toPlainString().length();
            order++;
        }
        System.out.println(order);
    }

}
