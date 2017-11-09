package project.euler;

import project.euler.util.NumberUtils;
import project.euler.util.ResourcesUtils;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Oct 25, 2017
 * @since 1.0
 */
public class FiftyOneToSeventyFive {

    public static void main(String... args) {
        sixtySeven();
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
