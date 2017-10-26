package project.euler.util;

import java.util.Arrays;

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

    public static int[] digits(int number) {
        int log = (int) Math.log10(number);
        int[] digits = new int[log + 1];
        int index = log;
        while(number > 0) {
            digits[index--] = number % 10;
            number /= 10;
        }
        return digits;
    }

}
