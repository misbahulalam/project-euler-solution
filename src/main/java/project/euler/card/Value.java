package project.euler.card;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Nov 13, 2017
 */
public enum Value {

    TWO(2, '2'), THREE(3, '3'), FOUR(4, '4'), FIVE(5, '5'), SIX(6, '6'), SEVEN(7, '7'), EIGHT(8, '8'),
    NINE(9, '9'), TEN(10, 'T'), JACK(11, 'J'), QUEEN(12, 'Q'), KING(13, 'K'), ACE(14, 'A');

    @Getter
    private int numVal;

    @Getter
    private char face;

    Value(int numVal, char face) {
        this.numVal = numVal;
        this.face = face;
    }

    public static Optional<Value> ofChar(char c) {
        return Arrays.stream(values()).filter(v -> v.face == c).findFirst();
    }

}
