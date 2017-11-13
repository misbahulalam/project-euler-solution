package project.euler;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Nov 06, 2017
 * @since 1.0
 */
public class PentagonPair {

    private static Set<Long> pentagonalsByPosition = new HashSet<>();
    private static long maxEntry = -1;

    int j;
    int k;
    long pj;
    long pk;

    public PentagonPair(int j, int k) {
        this.j = j;
        this.k = k;
        setPentagonalValues();
    }

    public void next() {
        j++;
        k++;
        setPentagonalValues();
    }

    private void setPentagonalValues() {
        this.pj = pentagonValue(j);
        this.pk = pentagonValue(k);
    }

    public long getPentagonalSum() {
        return pj + pk;
    }

    public long getPentagonalDifference() {
        return pk - pj;
    }

    public boolean isPentagonalSumPentagonal() {
        return isPentagonal(getPentagonalSum());
    }

    public boolean isPentagonalDifferencePentagonal() {
        return isPentagonal(getPentagonalDifference());
    }

    public boolean isBothPentagonalSumAndDiffPentagonal() {
        return isPentagonalDifferencePentagonal() && isPentagonalSumPentagonal();
    }

    /**
     * Apart of deciding if pentagonal, this method may add pentagonal values to the list provided.
     *
     * @param value to check if pentagonal.
     * @return if pentagonal of not.
     */
    private boolean isPentagonal(long value) {
        while (pentagonalsByPosition.isEmpty() || value > maxEntry) {
            long p = pentagonValue(pentagonalsByPosition.size() + 1);
            pentagonalsByPosition.add(p);
            maxEntry = p;
        }
        return pentagonalsByPosition.contains(value);
    }

    private long pentagonValue(int n) {
        long ln = (long) n;
        return ln * (3 * ln - 1) / 2;
    }

}
