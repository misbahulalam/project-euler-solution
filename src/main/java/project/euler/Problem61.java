package project.euler;

import lombok.Getter;
import project.euler.util.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Nov 21, 2017
 */
public class Problem61 {


    public static void main(String[] args) {
        new Problem61().solve();
    }

    private int[] triangles;
    private int[] squares;
    private int[] pentagonals;
    private int[] hexagonals;
    private int[] heptagonals;
    private int[] octagonals;

    private List<FigurateNumber> figurateNumbers = new ArrayList<>();

    public void solve() {
        int nMin = 1000, nMax = 9999;
        triangles = NumberUtils.triangleNumbersBetween(nMin, nMax);
        squares = NumberUtils.squareNumbersBetween(nMin, nMax);
        pentagonals = NumberUtils.pentagonalNumbersBetween(nMin, nMax);
        hexagonals = NumberUtils.hexagonalNumbersBetween(nMin, nMax);
        heptagonals = NumberUtils.heptagonalNumbersBetween(nMin, nMax);
        octagonals = NumberUtils.octagonalNumbersBetween(nMin, nMax);


        Arrays.stream(triangles).forEach(x -> figurateNumbers.add(new FigurateNumber(x, 3)));
        Arrays.stream(squares).forEach(x -> figurateNumbers.add(new FigurateNumber(x, 4)));
        Arrays.stream(pentagonals).forEach(x -> figurateNumbers.add(new FigurateNumber(x, 5)));
        Arrays.stream(hexagonals).forEach(x -> figurateNumbers.add(new FigurateNumber(x, 6)));
        Arrays.stream(heptagonals).forEach(x -> figurateNumbers.add(new FigurateNumber(x, 7)));
        Arrays.stream(octagonals).forEach(x -> figurateNumbers.add(new FigurateNumber(x, 8)));

        FigurateNumber[] result = new FigurateNumber[0];
        fillWithCyclicalFigurateNumbers(result);

    }

    private void fillWithCyclicalFigurateNumbers(FigurateNumber[] result) {

    }

    class FigurateNumber {

        @Getter
        private int number;

        @Getter
        private int type;

        public FigurateNumber(int number, int type) {
            this.number = number;
            this.type = type;
        }

        public int getFirstTwoDigits() {
            return number / (int) Math.pow(10, NumberUtils.digitCount(number) - 2);
        }

        public int getLastTwoDigits() {
            return number % 100;
        }

    }

}
