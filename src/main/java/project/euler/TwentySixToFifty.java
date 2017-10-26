package project.euler;

import project.euler.util.ArrayUtils;
import project.euler.util.NumberUtils;

import java.util.*;
import java.util.stream.Collectors;

public class TwentySixToFifty {

    public static void main(String... args) {
//        twentySix();
//        twentySeven();
//        twentyEight();
//        twentyNine();
//        thirty();
//        thirtyOne();
//        thirtyTwo();
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
        LinkedHashSet<Integer> primes = primesUpTo(maxEqVal);

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

    private static LinkedHashSet<Integer> primesUpTo(int n) {
        LinkedHashSet<Integer> primes = new LinkedHashSet<>();
        primes.add(2);
        int trying = 3;
        while (trying <= n) {
            if (isPrime(trying, primes)) {
                primes.add(trying);
            }
            trying += 2;
        }
        return primes;
    }

    private static boolean isPrime(int n, LinkedHashSet<Integer> orderedPrimes) {
        int sqRoot = (int) Math.sqrt(n);
        for (int p : orderedPrimes) {
            if (n % p == 0) return false;
            if (p > sqRoot) return true;
        }
        return true;
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
        for (int x : possibleValues(digits, 1)) {
            int[] remainingDigits = arrayMinus(digits, NumberUtils.digits(x));
            for (int y : possibleValues(remainingDigits, 4)) {
                int[] allowedResultDigits= arrayMinus(remainingDigits, NumberUtils.digits(y));
                int product = x * y;
                if (numberConsistOfDigits(product, allowedResultDigits)) {
//                    System.out.println(x + " x " + y + " = " + product);
                    products.add(product);
                }
            }
        }

        //For length(x) = 2, length(y) = 3
        for (int x : possibleValues(digits, 2)) {
            int[] remainingDigits = arrayMinus(digits, NumberUtils.digits(x));
            for (int y : possibleValues(remainingDigits, 3)) {
                int[] allowedResultDigits= arrayMinus(remainingDigits, NumberUtils.digits(y));
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
        if ((int)(Math.log10(number) + 1) != digits.length) return false;

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

    private static Set<Integer> possibleValues(int[] digits, int length) {
        if (length == 1) {
            return ArrayUtils.toSet(digits);
        } else {
            Set<Integer> possibilities = new HashSet<>();
            Set<Integer> firstDigits = possibleValues(digits, length - 1);
            for (Integer fds : firstDigits) {
                int[] remainingDigits = arrayMinus(digits, NumberUtils.digits(fds));
                Set<Integer> lastDigits = possibleValues(remainingDigits, 1);
                possibilities.addAll(lastDigits.stream().map(ld -> fds * 10 + ld).collect(Collectors.toList()));
            }
            return possibilities;
        }
    }

    private static int[] arrayMinus(int[] original, int[] minus) {
        int[] arr = new int[original.length - minus.length];
        int index = 0;
        for (int v : original) {
            if (!ArrayUtils.contains(minus, v)) {
                arr[index++] = v;
            }
        }
        return arr;
    }

}
