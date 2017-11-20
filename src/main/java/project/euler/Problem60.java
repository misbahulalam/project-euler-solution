package project.euler;

import project.euler.util.NumberUtils;

import java.util.Arrays;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Nov 20, 2017
 */
public class Problem60 {

    private int[] primes;

    public static void main(String[] args) {
        new Problem60().printSolution();
    }

    public void printSolution() {
        primes = NumberUtils.primesUpToAsArray(100000);

        outer:
        for (int i1 = 0; ; i1++) {
            for (int i2 = 0; i2 < i1; i2++) {
                if (!isTwoConcatArePrimes(primes[i1], primes[i2])) continue;
                for (int i3 = 0; i3 < i2; i3++) {
                    if (!isTwoConcatArePrimes(primes[i1], primes[i2], primes[i3])) continue;
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (!isTwoConcatArePrimes(primes[i1], primes[i2], primes[i3], primes[i4])) continue;
                        for (int i5 = 0; i5 < i4; i5++) {
                            if (isTwoConcatArePrimes(primes[i1], primes[i2], primes[i3], primes[i4], primes[i5])) {
                                System.out.println(primes[i1] + primes[i2] + primes[i3] + primes[i4] + primes[i5]);
                                break outer;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isTwoConcatArePrimes(int... numbers) {
        for (int i = 0; i <= numbers.length - 2; i++) {
            for (int j = i + 1; j <= numbers.length - 1; j++) {
                int v1 = Integer.parseInt("" + numbers[i] + numbers[j]);
                int v2 = Integer.parseInt("" + numbers[j] + numbers[i]);
                if (!isPrime(v1) || !isPrime(v2)) return false;
            }
        }
        return true;
    }

    private boolean isPrime(int number) {
        if (number > maxCalculatedPrime()) {
            primes = NumberUtils.primesUpToAsArray(Math.max(number, maxCalculatedPrime() * 10));
        }

        return Arrays.binarySearch(primes, number) >= 0;
    }

    private int maxCalculatedPrime() {
        return primes[primes.length - 1];
    }

}
