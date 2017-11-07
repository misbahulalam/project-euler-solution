package project.euler;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author <a href="mailto:misbah@cefalo.com">Misbahul Alam Chowdhury</a> on Nov 07, 2017
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class Pair<X, Y> {

    private X x;
    private Y y;

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}
