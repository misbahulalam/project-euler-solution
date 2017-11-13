package project.euler.card;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Nov 13, 2017
 */

public class PokerHand {

    private Card[] cards;

    @Getter
    private PokerRank rank;

    public PokerHand(Card c1, Card c2, Card c3, Card c4, Card c5) {
        this.cards = new Card[]{c1, c2, c3, c4, c5};
        Arrays.sort(cards);
        this.rank = new PokerRank(cards);
    }

    public PokerHand(String c1, String c2, String c3, String c4, String c5) {
        this(new Card(c1), new Card(c2), new Card(c3), new Card(c4), new Card(c5));
    }



    private Card[] cardsOfSuit(Suit s) {
        return Arrays.stream(cards).filter(c -> c.getSuit().equals(s)).toArray(Card[]::new);
    }

    @Override
    public String toString() {
        return "Hand: rankType: " + rank.getType() + " rankSuit: " + rank.getSuit() + " rankVal: " + rank.getVal() + " rankVal2: " + rank.getVal2();
    }

    public boolean isWiner(PokerHand otherHand) {
        return this.rank.compareTo(otherHand.rank) > 0;
    }

}
