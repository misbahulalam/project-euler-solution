package project.euler.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static boolean isPrime(int n, LinkedHashSet<Integer> primesBeforeInOrder) {
        int sqRoot = (int) Math.sqrt(n);
        for (int p : primesBeforeInOrder) {
            if (n % p == 0) return false;
            if (p > sqRoot) return true;
        }
        return true;
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
        return (int) (Math.log10(number) + 1);
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

}
