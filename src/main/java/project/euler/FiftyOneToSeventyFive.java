package project.euler;

import project.euler.card.PokerHand;
import project.euler.util.NumberUtils;
import project.euler.util.ResourcesUtils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Oct 25, 2017
 * @since 1.0
 */
public class FiftyOneToSeventyFive {

    public static void main(String... args) {
//        fiftyOne();
//        fiftyTwo();
//        fiftyThree();
//        fiftyFour();
//        fiftyFive();
//        fiftySix();
//        fiftySeven();
//        fiftyEight();
        sixtyOne();
//        sixtySeven();
    }

    private static void fiftyOne() {
        int dc = 6;  //considering 6 digits numbers
        Set<Integer> primes = NumberUtils.primesBetween((int) Math.pow(10, dc - 1), (int) Math.pow(10, dc));
        for (int ac = 1; ac < dc; ac++) { //Asterisks Count, i.e. numbers of digits to be replaced
            int lLimit = 0;
            int uLimit = (int) Math.pow(10, dc - ac);
            Set<Set<Integer>> aps = possibleAsteriskPositions(dc, ac);

            for (Set<Integer> ap : aps) {
                for (int woAsterisk = lLimit; woAsterisk < uLimit; woAsterisk++) {
                    int[] replacedPrimes = possibleNumbersReplacingAsterisks(woAsterisk, dc, ap).filter(primes::contains).toArray();
                    if (replacedPrimes.length >= 8) System.out.println(Arrays.toString(replacedPrimes));
                }

            }
        }
    }

    private static void fiftyTwo() {
        int x = 1;
        while (true) {
            int[] digits = NumberUtils.digits(x);
            Set<Integer> combinations = NumberUtils.combinedNumbers(digits, digits.length);
            boolean flag = true;
            for (int times = 2; times <= 6; times++) {
                if (!combinations.contains(x * times)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(x);
                break;
            }
            x++;
        }
    }

    private static IntStream possibleNumbersReplacingAsterisks(int withoutAsterisk, int totalDigits, Set<Integer> asteriskPositions) {
        int ac = asteriskPositions.size();      //asterisks count
        int[] digits = NumberUtils.digits(withoutAsterisk, totalDigits - ac);

        int dc = digits.length;                 //digits count
        int dp = 0;                             //digits pointer

        int value = 0;
        for (int cp = 0; cp < dc + ac; cp++) {  //cp means current position
            value *= 10;
            if (!asteriskPositions.contains(cp)) {
                value += digits[dp];
                dp++;
            }
        }
        int asteriskZeroValue = value;

        int[] asteriskWeights = new int[10];
        for (int av = 0; av <= 9; av++) {       //asterisk value, i.e. 0~9.
            for (int ap : asteriskPositions) {
                asteriskWeights[av] += av * (int) Math.pow(10, dc + ac - ap - 1);
            }
        }

        int minValue = (int) Math.pow(10, dc + ac - 1);
        return Arrays.stream(asteriskWeights).map(x -> x + asteriskZeroValue).filter(x -> x >= minValue);
    }

    private static Set<Set<Integer>> possibleAsteriskPositions(int digitsCount, int asteriskCount) {
        int[] positions = IntStream.range(0, digitsCount).toArray();
        Set<Set<Integer>> combinations = NumberUtils.combinedDigits(positions, asteriskCount);
        return combinations.stream().collect(Collectors.toSet());
    }

    private int[] nDigitNumbers(int n) {
        return IntStream.range((int) Math.pow(10, n - 1), (int) Math.pow(10, n)).toArray();
    }

    /**
     * This problem could be solved with BigInteger/BigDecimal. But solved mathematically.
     */
    private static void fiftyThree() {
        int count = 0;
        for (int n = 1; n <= 100; n++) {
            for (int r = 1; r <= n; r++) {
                if (isNumberOfCombinationsExceedOneMillion(n, r)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static boolean isNumberOfCombinationsExceedOneMillion(int n, int r) {
        if (n == r) return false;
        if (r > n - r) {
            r = n - r;
        }

        double c = 1;
        while (r > 0) {
            c = c * n / r;
            n--;
            r--;
            if (c > 1_000_000) return true;
        }
        return c > 1_000_000;
    }

    private static void fiftyFour() {
        String[][] cards = ResourcesUtils.readAs2DStringArray("p054_poker.txt", " ");
        int p1Wins = 0;
        for (String[] tenCards : cards) {
            if (new PokerHand(tenCards[0], tenCards[1], tenCards[2], tenCards[3], tenCards[4]).isWiner(new PokerHand(tenCards[5], tenCards[6], tenCards[7], tenCards[8], tenCards[9]))) {
                p1Wins++;
            }
        }
        System.out.println(p1Wins);
    }

    private static void fiftyFive() {
        long count = IntStream.range(1, 10_000).filter(x -> NumberUtils.isLychrel(x, 50)).count();
        System.out.println(count);
    }

    private static void fiftySix() {
        int max = 0;
        for (int aInt = 1; aInt < 100; aInt++) {
            BigInteger a = BigInteger.valueOf(aInt);
            for (int b = 1; b < 100; b++) {
                int sum = NumberUtils.sumOfDigits(a.pow(b));
                if (sum > max) max = sum;
            }
        }
        System.out.println(max);
    }

    private static void fiftySeven() {
        BigInteger numerator = BigInteger.valueOf(3);
        BigInteger denominator = BigInteger.valueOf(2);
        int count = 0;

        for (int i = 1; i <= 1000; i++) {
            if (NumberUtils.digitCount(numerator) > NumberUtils.digitCount(denominator)) count++;
            numerator = numerator.add(denominator).add(denominator);
            denominator = numerator.subtract(denominator);
        }
        System.out.println(count);
    }

    private static void fiftyEight() {
        int preCalculatedPrimeLimit = 1000;
        int[] primes = NumberUtils.primesUpToAsArray(preCalculatedPrimeLimit);

        int value = 1;
        int primeCount = 0;
        int size = 1;
        int diagonalNumberCount = size * 2 - 1;
        float ratio = 100f * primeCount / diagonalNumberCount;

        while (ratio == 0 || ratio >= 10) {
            size += 2;

            if (value + (size - 1) * 4 > preCalculatedPrimeLimit) {
                preCalculatedPrimeLimit *= 100;
                primes = NumberUtils.primesUpToAsArray(preCalculatedPrimeLimit);
            }

            for (int i = 1; i <= 4; i++) {
                value += size - 1;
                if (Arrays.binarySearch(primes, value) >= 0) primeCount++;
            }

            diagonalNumberCount = size * 2 - 1;
            ratio = 100f * primeCount / diagonalNumberCount;
        }

        System.out.println(size);
    }

    private static void sixtyOne() {

    }

    private static void sixtySeven() {
        String input = ResourcesUtils.readAsString("p067_triangle.txt");
        Integer[][] triangle = NumberUtils.parseTo2dIntegerArray(input);
        for (int i = 1; i < triangle.length; i++) {
            triangle[i][0] += triangle[i - 1][0];
            triangle[i][i] += triangle[i - 1][i - 1];
            for (int j = 1; j < i; j++) {
                triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }
        }
        System.out.println(NumberUtils.max(triangle[triangle.length - 1]));
    }

}
