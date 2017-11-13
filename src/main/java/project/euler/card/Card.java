package project.euler.card;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Nov 13, 2017
 */
@Getter
public class Card implements Comparable<Card> {

    private Value value;
    private Suit suit;

    public Card(String face) {
        this.value = Value.ofChar(face.charAt(0)).get();
        this.suit = Suit.valueOf(Character.toString(face.charAt(1)));
    }

    @Override
    public int compareTo(@NonNull Card o) {
        return getNumVal() - o.getNumVal();
    }

    public int getNumVal() {
        return value.getNumVal();
    }

    @Override
    public String toString() {
        return value.getFace() + "" + suit ;
    }
}
