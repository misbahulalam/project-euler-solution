package project.euler;

import java.util.ArrayList;
import java.util.List;

public class TwentySixToFifty {

    public static void main(String... args) {

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
        while(n > 0 && !numerators.contains(n)) {
            numerators.add(n);
            n = (n * 10) % denominator;
        }

        if (n == 0) {
            return 0;
        } else {
            return numerators.size() - numerators.indexOf(n);
        }
    }

}
