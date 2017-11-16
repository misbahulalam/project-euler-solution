package project.euler.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumberUtils {

    public static String numberInWord(int x) {
        int t = x / 1000;
        int h = (x % 1000) / 100;
        int bh = x % 100;
        return thousandInWord(t) + hundredInWord(h) + joinWord(x) + belowHundred(bh);
    }

    private static String thousandInWord(int v) {
        if (v < 0 || v > 99) throw new IllegalArgumentException();
        return v > 0 ? basicNumbers(v) + "Thousand" : "";
    }

    private static String hundredInWord(int v) {
        if (v < 0 || v > 9) throw new IllegalArgumentException();
        return v > 0 ? basicNumbers(v) + "Hundred" : "";
    }

    private static String joinWord(int number) {
        return number > 100 && number % 100 > 0 ? "And" : "";
    }

    private static String belowHundred(int v) {
        if (v < 0 || v > 99) throw new IllegalArgumentException();
        if (v <= 20 || v % 10 == 0) {
            return basicNumbers(v);
        } else {
            int d = v / 10;
            int a = v % 10;
            return basicNumbers(d * 10) + basicNumbers(a);
        }
    }

    private static String basicNumbers(int x) {
        switch (x) {
            case 0:
                return "";
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";
            case 20:
                return "Twenty";
            case 30:
                return "Thirty";
            case 40:
                return "Forty";
            case 50:
                return "Fifty";
            case 60:
                return "Sixty";
            case 70:
                return "Seventy";
            case 80:
                return "Eighty";
            case 90:
                return "Ninety";
            default:
                throw new IllegalArgumentException("Value " + x + " is not supported.");
        }
    }

    public static Integer[][] parseTo2dIntegerArray(String str) {
        return Arrays.stream(str.split("\n"))
                .map(NumberUtils::parseToIntegerArray)
                .toArray(Integer[][]::new);
    }

    private static Integer[] parseToIntegerArray(String str) {
        return Arrays.stream(str.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
    }

    public static int max(Integer... numbers) {
        int max = Integer.MIN_VALUE;
        for (int val : numbers) {
            max = Math.max(max, val);
        }
        return max;
    }

    public static long min(long... numbers) {
        long min = Long.MAX_VALUE;
        for (long val : numbers) {
            min = Math.min(min, val);
        }
        return min;
    }

    public static long factorial(long x) {
        if (x == 0 || x == 1) {
            return 1;
        } else {
            return x * factorial(x - 1);
        }
    }

    public static int[] digits(long number) {
        if (number < 10) return new int[]{(int) number};

        int log = (int) Math.log10(number);
        int[] digits = new int[log + 1];
        int index = log;
        while (number > 0) {
            digits[index--] = (int) (number % 10);
            number /= 10;
        }
        return digits;
    }

    public static int[] digits(long number, int length) {
        int digitsCount = digitCount(number);
        if (digitCount(number) > length) throw new IllegalArgumentException();

        int[] digits = new int[length];
        System.arraycopy(digits(number), 0, digits, length - digitsCount, digitsCount);
        return digits;
    }

    public static long toNumber(int[] digits) {
        long number = 0;
        for (int digit : digits) {
            number = number * 10 + digit;
        }
        return number;
    }

    public static Set<Integer> squaresUpTo(int n) {
        Set<Integer> squares = new HashSet<>();
        int i = 1;
        int square = i * i;
        while (square <= n) {
            squares.add(square);
            i++;
            square = i * i;
        }

        return squares;
    }

    public static LinkedHashSet<Integer> primesUpTo(int n) {
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

    public static Set<Integer> primesBetween(int from, int to) {
        LinkedHashSet<Integer> primesUpToSqRoot = primesUpTo((int) Math.sqrt(to));
        Set<Integer> primesBetween = new HashSet<>();
        int trying = from;
        while (trying <= to) {
            if (primesUpToSqRoot.contains(trying) || isPrime(trying, primesUpToSqRoot)) {
                primesBetween.add(trying);
            }
            trying += (trying % 2 == 0 || trying <= 2 ? 1 : 2);
        }
        return primesBetween;
    }

    public static boolean isPrime(long n, LinkedHashSet<Integer> primesBeforeInOrder) {
        if (n < 2) return false;
        int sqRoot = (int) Math.sqrt(n);
        for (int p : primesBeforeInOrder) {
            if (n % p == 0) return false;
            if (p > sqRoot) return true;
        }
        return true;
    }

    public static Set<Integer> distinctPrimeFactors(int n) {
        return primeFactors(n).keySet();
    }

    public static Map<Integer, Integer> primeFactors(int n) {
        int sqRoot = (int) Math.sqrt(n);
        Set<Integer> primesUpToSqRoot = primesUpTo(sqRoot);
        Map<Integer, Integer> factorPowerMap = new HashMap<>();

        for (int prime : primesUpToSqRoot) {
            int power = 0;
            while (n % prime == 0) {
                power++;
                n /= prime;
            }
            if (power > 0) factorPowerMap.put(prime, power);
        }
        if (n != 1) factorPowerMap.put(n, 1);

        return factorPowerMap;
    }

    public static boolean isTruncatablePrime(int target, Set<Integer> primesBefore) {
        if (target < 10) return false;

        int divider = 10;
        while (divider < target) {
            if (!primesBefore.contains(target / divider)) return false;
            if (!primesBefore.contains(target % divider)) return false;
            divider *= 10;
        }
        return true;
    }

    public static Set<Integer> combinedNumbers(int[] digits, int length) {
        if (length == 1) {
            return ArrayUtils.toSet(digits);
        } else {
            Set<Integer> combinations = new HashSet<>();
            Set<Integer> firstDigits = combinedNumbers(digits, length - 1);
            for (Integer fds : firstDigits) {
                int[] remainingDigits = ArrayUtils.arrayMinus(digits, digits(fds));
                Set<Integer> lastDigits = combinedNumbers(remainingDigits, 1);
                combinations.addAll(lastDigits.stream().map(ld -> fds * 10 + ld).collect(Collectors.toList()));
            }
            return combinations;
        }
    }

    public static Set<Set<Integer>> combinedDigits(int[] digits, int length) {
        if (length == 1) {
            return IntStream.of(digits).mapToObj(Collections::singleton).collect(Collectors.toSet());
        } else {
            Set<Set<Integer>> combinations = new HashSet<>();
            Set<Set<Integer>> firstDigits = combinedDigits(digits, length - 1);
            for (Set<Integer> fds : firstDigits) {
                IntStream remainingDigits = IntStream.of(digits).filter(d -> !fds.contains(d));

                combinations.addAll(remainingDigits.mapToObj(ld -> addItem(fds, ld)).collect(Collectors.toSet()));
            }
            return combinations;
        }
    }

    private static Set<Integer> addItem(Set<Integer> set, int newItem) {
        Set<Integer> newSet = new HashSet<>(set);
        newSet.add(newItem);
        return newSet;
    }

    public static Set<Integer> rotationNumbers(int number) {
        Set<Integer> rotations = new HashSet<>();
        int dc = digitCount(number);
        int divider = (int) Math.pow(10, dc - 1);

        while (!rotations.contains(number)) {
            rotations.add(number);
            int carry = number % divider;
            int result = number / divider;
            number = carry * 10 + result;
        }
        return rotations;
    }

    public static int digitCount(long number) {
        if (number < 0) throw new IllegalArgumentException("This method works only with zero and positive numbers.");

        return number == 0 ? 0 : (int) (Math.log10(number) + 1);
    }

    public static boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int l = chars.length;
        for (int i = 0; i < l / 2; i++) {
            if (chars[i] != chars[l - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(long x) {
        int[] digits = NumberUtils.digits(x);
        int l = digits.length;
        for (int i = 0; i < l / 2; i++) {
            if (digits[i] != digits[l - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(BigInteger x) {
        return isPalindrome(x.toString());
    }

    public static boolean isLychrel(long x, int maxIterations) {
        BigInteger v = BigInteger.valueOf(x);
        for (int i = 0; i < maxIterations; i++) {
            v = v.add(reverse(v));
            if (isPalindrome(v)) return false;
        }
        return true;
    }

    private static BigInteger reverse(BigInteger x) {
        return new BigInteger(reverse(x.toString()));
    }

    private static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static String binary(int decimal) {
        return decimalToBase(decimal, 2);
    }

    public static String octal(int decimal) {
        return decimalToBase(decimal, 8);
    }

    private static String decimalToBase(int decimal, int base) {
        char[] carries = new char[32];
        int index = 31;

        while (decimal != 0) {
            carries[index--] = (char) (decimal % base + '0');
            decimal /= base;
        }
        index++;
        return String.valueOf(carries, index, 32 - index);
    }

    public static boolean isPandigital(int number) {
        return isPandigital(number, 1, digitCount(number));
    }

    public static boolean isPandigital(long number, int min, int max) {
        int digitCount = digitCount(number);
        if (digitCount != (max - min + 1)) return false;

        int[] ordered = new int[digitCount];
        int[] digits = NumberUtils.digits(number);

        for (int digit : digits) {
            if (digit < min || digit > max || ordered[digit - min] != 0) {
                return false;
            } else {
                ordered[digit - min] = digit;
            }
        }

        return true;
    }

    public static int sumOfDigits(BigDecimal x) {
        return sumOfDigits(x.toString());
    }

    public static int sumOfDigits(BigInteger x) {
        return sumOfDigits(x.toString());
    }

    private static int sumOfDigits(String s) {
        return s.chars().map(c -> c - '0').sum();
    }

}
