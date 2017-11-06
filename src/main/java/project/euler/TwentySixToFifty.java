package project.euler;

import org.apache.commons.lang3.math.Fraction;
import project.euler.util.ArrayUtils;
import project.euler.util.NumberUtils;
import project.euler.util.ResourcesUtils;

import java.util.*;
import java.util.stream.IntStream;

public class TwentySixToFifty {

    public static void main(String... args) {
//        twentySix();
//        twentySeven();
//        twentyEight();
//        twentyNine();
//        thirty();
//        thirtyOne();
//        thirtyTwo();
//        thirtyThree();
//        thirtyFour();
//        thirtyFive();
//        thirtySix();
//        thirtySeven();
//        thirtyEight();
//        thirtyNine();
//        forty();
//        fortyOne();
//        fortyTwo();
//        fortyThree();
//        fortyFour();
//        fortyFive();
//        fortySix();
    }

    private static void twentySix() {
        int maxLength = -0, denominatorHavingMaxLength = -1;
        for (int i = 2; i < 1000; i++) {
            int cycleLength = getReciprocalCycleLength(1, i);
            if (cycleLength > maxLength) {
                maxLength = cycleLength;
                denominatorHavingMaxLength = i;
            }
        }
        System.out.println(denominatorHavingMaxLength);
    }

    private static int getReciprocalCycleLength(int numerator, int denominator) {
        int n = numerator % denominator;
        List<Integer> numerators = new ArrayList<>();
        while (n > 0 && !numerators.contains(n)) {
            numerators.add(n);
            n = (n * 10) % denominator;
        }

        if (n == 0) {
            return 0;
        } else {
            return numerators.size() - numerators.indexOf(n);
        }
    }

    private static void twentySeven() {
        int aMaxVal = 999;
        int bMaxVal = 1000;
        int nMaxVal = bMaxVal - 1;
        int maxEqVal = quadraticEquationResult(nMaxVal, aMaxVal, bMaxVal);
        LinkedHashSet<Integer> primes = NumberUtils.primesUpTo(maxEqVal);

        int aValForMaxPrimeSeq = Integer.MIN_VALUE, bValForMaxPrimeSeq = Integer.MIN_VALUE, nValForMaxPrimeSeq = Integer.MIN_VALUE;

        for (int a = -1 * aMaxVal; a <= aMaxVal; a++) {
            for (int b = -1 * bMaxVal; b <= bMaxVal; b++) {
                int n = 0;
                while (primes.contains(quadraticEquationResult(n, a, b))) {
                    n++;
                }

                if (n - 1 > nValForMaxPrimeSeq) {
                    nValForMaxPrimeSeq = n - 1;
                    aValForMaxPrimeSeq = a;
                    bValForMaxPrimeSeq = b;
                }
            }
        }
//        System.out.println("a = " + aValForMaxPrimeSeq + " b = " + bValForMaxPrimeSeq + " n = " + nValForMaxPrimeSeq);
        System.out.println("ab = " + aValForMaxPrimeSeq * bValForMaxPrimeSeq);
    }

    private static int quadraticEquationResult(int n, int a, int b) {
        return n * n + a * n + b;
    }

    private static void twentyEight() {
        long sum = 1;
        int last = 1;
        for (int x = 2; x <= 1000; x += 2) {
            sum += 4 * last + 10 * x;
            last = last + 4 * x;
        }
        System.out.println(sum);
    }

    private static void twentyNine() {
        Set<Double> powers = new HashSet<>();
        for (int a = 2; a <= 100; a++) {
            for (int b = 2; b <= 100; b++) {
                powers.add(Math.pow(a, b));
            }
        }
        System.out.println(powers.size());
    }

    private static void thirty() {
//        Max sum of two fifth power numbers is 2 * 9 ^ 5 = 118098 > 99999 (grater than any 5 digit number)
//        Max sum of six fifth power numbers is 6 * 9 ^ 5 = 354294 < 999999 (Less than max 6 digit number)
//        So, we have to look-up numbers in between 11 and 999999
        int sum = 0;
        for (int i = 11; i < 999999; i++) {
            if (sumOfPowerOfAllDigits(i, 5) == i) {
                sum += i;
            }
        }
        System.out.println(sum);
    }

    private static int sumOfPowerOfAllDigits(int number, int power) {
        int sum = 0;
        while (number > 0) {
            int digit = number % 10;
            number = number / 10;
            sum += Math.pow(digit, power);
        }
        return sum;
    }

    private static void thirtyOne() {
        int target = 200;
        int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
        int[] times = new int[coins.length];
        for (int i = 0; i < coins.length; i++) {
            times[i] = target / coins[i];
        }

        int count = 0;
        for (int c0 = 0; c0 <= times[0]; c0++) {
            for (int c1 = 0; c1 <= times[1]; c1++) {
                for (int c2 = 0; c2 <= times[2]; c2++) {
                    for (int c3 = 0; c3 <= times[3]; c3++) {
                        for (int c4 = 0; c4 <= times[4]; c4++) {
                            for (int c5 = 0; c5 <= times[5]; c5++) {
                                for (int c6 = 0; c6 <= times[6]; c6++) {
                                    for (int c7 = 0; c7 <= times[7]; c7++) {
                                        if (c0 * coins[0] + c1 * coins[1] + c2 * coins[2] + c3 * coins[3] + c4 * coins[4] + c5 * coins[5] + c6 * coins[6] + c7 * coins[7] == target) {
                                            count++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }

    private static void thirtyTwo() {
        //Take in account that, if x * y = z then x < y. Otherwise we will take y * x once again.
        //We have only two length combination for what we can have result in valid length. These are
        //length(x) = 1 and length(y) = 4. Then length(xy) is 4~5.
        //length(x) = 2 and length(y) = 3. Then length(xy) is 4~5.
        Set<Integer> products = new HashSet<>();
        int[] digits = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        //For length(x) = 1, length(y) = 4
        for (int x : NumberUtils.combinedNumbers(digits, 1)) {
            int[] remainingDigits = ArrayUtils.arrayMinus(digits, NumberUtils.digits(x));
            for (int y : NumberUtils.combinedNumbers(remainingDigits, 4)) {
                int[] allowedResultDigits = ArrayUtils.arrayMinus(remainingDigits, NumberUtils.digits(y));
                int product = x * y;
                if (numberConsistOfDigits(product, allowedResultDigits)) {
//                    System.out.println(x + " x " + y + " = " + product);
                    products.add(product);
                }
            }
        }

        //For length(x) = 2, length(y) = 3
        for (int x : NumberUtils.combinedNumbers(digits, 2)) {
            int[] remainingDigits = ArrayUtils.arrayMinus(digits, NumberUtils.digits(x));
            for (int y : NumberUtils.combinedNumbers(remainingDigits, 3)) {
                int[] allowedResultDigits = ArrayUtils.arrayMinus(remainingDigits, NumberUtils.digits(y));
                int product = x * y;
                if (numberConsistOfDigits(product, allowedResultDigits)) {
//                    System.out.println(x + " x " + y + " = " + product);
                    products.add(product);
                }

            }
        }
        int sum = products.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }

    private static boolean numberConsistOfDigits(int number, final int[] digits) {
        if ((int) (Math.log10(number) + 1) != digits.length) return false;

        int[] copy = Arrays.copyOf(digits, digits.length);
        Arrays.sort(copy);
        int[] digitsInNumber = NumberUtils.digits(number);
        Arrays.sort(digitsInNumber);

        for (int i = 0; i < copy.length; i++) {
            if (copy[i] != digitsInNumber[i]) {
                return false;
            }
        }
        return true;
    }

    private static void thirtyThree() {
        int numerator = 1;
        int denominator = 1;

        for (float c = 1; c <= 9; c++) {
            for (float i = 1; i < 9; i++) {
                for (float j = i + 1; j <= 9; j++) {
                    float r = i / j;
                    if (r == (i * 10 + c) / (c * 10 + j) || r == (c * 10 + i) / (j * 10 + c)) {
                        numerator *= i;
                        denominator *= j;
                    }
                }
            }
        }
        Fraction reducedFraction = Fraction.getReducedFraction(numerator, denominator);
        System.out.println(reducedFraction.getDenominator());
    }

    private static void thirtyFour() {
        Map<Integer, Integer> factorials = new HashMap<>(10);
        for (int i = 0; i <= 9; i++) {
            factorials.put(i, (int) NumberUtils.factorial(i));
        }

        //9! * 7 = 2540160, a 7 digit number
        //9! * 8 = 2903040, also a 7 digit number
        //So, no need to exceed 7 digit numbers.

        int total = IntStream.range(10, (int) Math.pow(10, 7))
                .filter(x -> x == Arrays.stream(NumberUtils.digits(x)).map(factorials::get).sum())
                .sum();
        System.out.println(total);
    }

    private static void thirtyFive() {
        int circularPrimeCount = 0;
        LinkedHashSet<Integer> primes = NumberUtils.primesUpTo(1_000_000);

        for (Integer prime : primes) {
            Set<Integer> rotations = NumberUtils.rotationNumbers(prime);
            if (primes.containsAll(rotations)) circularPrimeCount++;
        }
        System.out.println(circularPrimeCount);
    }

    private static void thirtySix() {
        int sum = IntStream.range(1, 1_000_000).filter(x -> NumberUtils.isPalindrome(x) && NumberUtils.isPalindrome(NumberUtils.binary(x))).sum();
        System.out.println(sum);
    }

    private static void thirtySeven() {
        LinkedHashSet<Integer> primes = new LinkedHashSet<>();
        primes.add(2);
        int count = 0;
        int trying = 3;
        int sum = 0;
        while (count != 11) {
            if (NumberUtils.isPrime(trying, primes)) {
                primes.add(trying);

                if (NumberUtils.isTruncatablePrime(trying, primes)) {
                    sum += trying;
                    count++;
                }
            }
            trying += 2;
        }
        System.out.println(sum);
    }

    private static void thirtyEight() {
        int maxPDNumber = Integer.MIN_VALUE;
        for (int i = 1; i < 100000; i++) {
            String product = concatenatedProductBelowTenDigits(i);
            if (isPandigital(product)) {
                int productInt = Integer.parseInt(product);
                if (productInt > maxPDNumber) {
                    maxPDNumber = productInt;
                }
            }
        }
        System.out.println(maxPDNumber);
    }

    private static boolean isPandigital(String str) {
        if (str.length() != 9) return false;
        char[] chars = str.toCharArray();
        for (char d : new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'}) {
            if (!ArrayUtils.contains(chars, d)) return false;
        }
        return true;
    }

    private static String concatenatedProductBelowTenDigits(int number) {
        String[] products = new String[10];
        int multiplier = 1;
        products[0] = Integer.toString(number);
        while (products[multiplier - 1].length() < 10) {
            int m = multiplier;
            products[m] = products[m - 1] + number * (m + 1);
            multiplier++;
        }
        return products[multiplier - 2];
    }

    private static void thirtyNine() {
        int maxTriangleCount = 0;
        int pForMaxTriangleCount = 0;

        for (int p = 3; p <= 1000; p++) {
            int triangleCount = 0;
            for (int a = 1; a <= p / 3; a++) {
                for (int b = Math.max(p / 2 - a, a + 1); b <= p / 2; b++) {
                    int c = p - a - b;
                    if (a * a + b * b == c * c) {
                        triangleCount++;
                    }
                }
            }
            if (triangleCount > maxTriangleCount) {
                maxTriangleCount = triangleCount;
                pForMaxTriangleCount = p;
            }
        }
        System.out.println(pForMaxTriangleCount);
    }

    private static void forty() {
        int mul = 1;
        int length = 1;
        int target = 1;
        do {
            target++;
            if (NumberUtils.digitCount(length) != NumberUtils.digitCount(length + NumberUtils.digitCount(target))) {
                mul *= NumberUtils.digits(target)[9 - length % 10];
            }
            length += NumberUtils.digitCount(target);
        } while (length <= 1_000_000);
        System.out.println(mul);
    }

    private static void fortyOne() {
        int maxPandigital = 987654321;
        LinkedHashSet<Integer> primeFactors = NumberUtils.primesUpTo((int) Math.sqrt(maxPandigital));

        for (int size = 9; size > 0; size--) {
            int[] digits = new int[size];
            int[] lowest = new int[size];
            for (int i = 0; i < size; i++) {
                digits[i] = size - i;
                lowest[i] = i + 1;
            }

            if (NumberUtils.isPrime(NumberUtils.toNumber(digits), primeFactors)) {
                System.out.println(NumberUtils.toNumber(digits));
                return;
            }

            while (!Arrays.equals(digits, lowest)) {
                ArrayUtils.updateToPreviousLexPermutation(digits);

                long value = NumberUtils.toNumber(digits);
                if (NumberUtils.isPrime(value, primeFactors)) {
                    System.out.println(value);
                    return;
                }
            }
        }
    }

    private static void fortyTwo() {
        String fileName = "p042_words.txt";

        long count = Arrays.stream(ResourcesUtils.readAsCsvString(fileName))
                .map(s -> s.substring(1, s.length() - 1))
                .mapToInt(TwentySixToFifty::wordValue)
                .filter(TwentySixToFifty::isTriangleNumber)
                .count();
        System.out.println(count);
    }

    private static int wordValue(String s) {
        return s.chars().map(x -> x - 'A' + 1).sum();
    }

    private static boolean isTriangleNumber(int n) {
        int s = 0, a = 1;
        while (s < n) {
            s += a;
            a++;
            if (s == n) return true;
        }
        return false;
    }

    private static void fortyThree() {
        int[] digits = {1, 0, 2, 3, 4, 5, 6, 7, 8, 9};
        long sum = hasSubstringDivisibilityProperty(digits) ? NumberUtils.toNumber(digits) : 0;
        while (!Arrays.equals(digits, new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0})) {
            ArrayUtils.updateToNextLexPermutation(digits);
            if (hasSubstringDivisibilityProperty(digits)) {
                sum += NumberUtils.toNumber(digits);
            }
        }
        System.out.println(sum);
    }

    private static boolean hasSubstringDivisibilityProperty(int[] digits) {
        int[] divisors = {2, 3, 5, 7, 11, 13, 17};

        for (int i = 0; i < divisors.length; i++) {
            if ((digits[i + 1] * 100 + digits[i + 2] * 10 + digits[i + 3]) % divisors[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private static void fortyFour() {
        int position = 0;
        List<PentagonPair> pairs = new ArrayList<>();
        pairs.add(new PentagonPair(1, 2));
        while (!pairs.get(position).isBothPentagonalSumAndDiffPentagonal()) {
            pairs.get(position).next();

            if (position == (pairs.size() - 1)) {
                pairs.add(new PentagonPair(1, pairs.size() + 2));
            }

            int minDiffPairIndex = -1;
            long minPairDiff = Long.MAX_VALUE;
            for (int i = 0; i < pairs.size(); i++) {
                long pentagonalDifference = pairs.get(i).getPentagonalDifference();
                if (pentagonalDifference < minPairDiff) {
                    minDiffPairIndex = i;
                    minPairDiff = pentagonalDifference;
                }
            }
            position = minDiffPairIndex;
        }
        PentagonPair succeedPair = pairs.get(position);
        System.out.println(succeedPair.getPentagonalDifference());
    }

    private static void fortyFive() {
        int ti = 286, pi = 166, hi = 144;
        long t = triangular(ti);
        long p = pentagonal(pi);
        long h = hexagonal(hi);
        while (t != p || t != h) {
            long min = NumberUtils.min(t, p, h);
            if (t == min) t = triangular(++ti);
            if (p == min) p = pentagonal(++pi);
            if (h == min) h = hexagonal(++hi);
        }
        System.out.println(t);
    }

    private static long triangular(int x) {
        long lx = (long) x;
        return (lx * lx + lx) / 2;
    }

    private static long pentagonal(int x) {
        long lx = (long) x;
        return (3 * lx * lx - lx) / 2;
    }

    private static long hexagonal(int x) {
        long lx = (long) x;
        return 2 * lx * lx - x;
    }

    private static void fortySix() {
        int th = 10_000; //Assuming the number is within this value
        LinkedHashSet<Integer> primes = NumberUtils.primesUpTo(th);
        Set<Integer> squares = NumberUtils.squaresUpTo(th);

        int target = 9;
        while (target < th) {
            if (primes.contains(target)) {
                target += 2;
                continue;
            }

            boolean flag = false;

            for (int prime : primes) {
                if (prime == 2) continue;
                if (prime >= target) break;
                int halfDiff = (target - prime) / 2;
                if (squares.contains(halfDiff)) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                System.out.println(target);
                break;
            }
            target += 2;
        }
    }
}
