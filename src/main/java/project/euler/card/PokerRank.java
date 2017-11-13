package project.euler.card;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Nov 13, 2017
 */
public class PokerRank implements Comparable<PokerRank> {

    private Card[] cards;

    @Getter
    private RankType type;

    @Getter
    private Optional<Value> val = Optional.empty();

    @Getter
    private Optional<Value> val2 = Optional.empty();

    @Getter
    private Optional<Suit> suit = Optional.empty();

    public PokerRank(Card[] cards) {
        this.cards = cards;
        checkForRoyalFlush();
        if (type == null) checkForStraightFlush();
        if (type == null) checkForFourOfAKind();
        if (type == null) checkForFullHouse();
        if (type == null) checkForFlush();
        if (type == null) checkForStraight();
        if (type == null) checkForThreeOfAKind();
        if (type == null) checkForTwoPairs();
        if (type == null) checkForOnePair();
        if (type == null) checkForHighCard();
    }

    //Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
    private void checkForRoyalFlush() {
        Suit s = cards[0].getSuit();
        for (Card card : cards) {
            if (!s.equals(card.getSuit())) return;
            if (card.getNumVal() < Value.TEN.getNumVal()) return;
        }
        this.type = RankType.RoyalFlush;
        this.suit = Optional.of(s);
    }

    //Straight Flush: All cards are consecutive values of same suit.
    private void checkForStraightFlush() {
        Suit s = cards[0].getSuit();
        Value min = cards[0].getValue();
        Value max = cards[0].getValue();

        for (Card card : cards) {
            if (!s.equals(card.getSuit())) return;

            if (card.getNumVal() < min.getNumVal()) min = card.getValue();
            if (card.getNumVal() > max.getNumVal()) max = card.getValue();
            if (max.getNumVal() - min.getNumVal() > 4) return;
        }
        this.type = RankType.StraightFlush;
        this.val = Optional.of(max);
        this.suit = Optional.of(s);
    }

    //Four of a Kind: Four cards of the same value.
    private void checkForFourOfAKind() {
        Map<Value, Integer> valCount = countCardsByValue();
        if (valCount.size() != 2) return;

        valCount.entrySet().stream().filter(val -> val.getValue() == 4).forEach(val -> {
            this.type = RankType.FourOfAKind;
            this.val = Optional.of(val.getKey());
        });
    }

    //Full House: Three of a kind and a pair.
    private void checkForFullHouse() {
        Map<Value, Integer> valCounts = countCardsByValue();
        if (valCounts.size() != 2) return;

        Value v3 = null, v2 = null;

        for (Map.Entry<Value, Integer> entry : valCounts.entrySet()) {
            if (entry.getValue() == 3) {
                v3 = entry.getKey();
            } else if (entry.getValue() == 2) {
                v2 = entry.getKey();
            }
        }

        if (v3 != null && v2 != null) {
            this.type = RankType.FullHouse;
            this.val = Optional.of(v3);
            this.val2 = Optional.of(v2);
        }
    }

    //Flush: All cards of the same suit.
    private void checkForFlush() {
        Suit s0 = cards[0].getSuit();
        long count = Arrays.stream(cards).map(Card::getSuit).filter(s -> s.equals(s0)).count();
        if (count == 5) {
            type = RankType.Flush;
            suit = Optional.of(s0);
        }
    }

    //Straight: All cards are consecutive values.
    private void checkForStraight() {
        Map<Value, Integer> valueCounts = countCardsByValue();
        if (valueCounts.size() != 5) return;

        int min = 15;
        int max = 0;
        for (Card card : cards) {
            min = Math.min(min, card.getNumVal());
            max = Math.max(max, card.getNumVal());
        }

        if (max - min == 4) {
            this.type = RankType.Straight;
        }
    }

    //Three of a Kind: Three cards of the same value.
    private void checkForThreeOfAKind() {
        Map<Value, Integer> valueCounts = countCardsByValue();
        valueCounts.entrySet().stream().filter(e -> e.getValue() == 3).map(Map.Entry::getKey).findFirst()
                .ifPresent(value -> {
                    this.type = RankType.ThreeOfAKind;
                    this.val = Optional.of(value);
                });
    }

    //Two Pairs: Two different pairs. Val is confusing here.
    private void checkForTwoPairs() {
        Map<Value, Integer> valueCounts = countCardsByValue();
        Value[] pairs = valueCounts.entrySet().stream().filter(e -> e.getValue() == 2).map(Map.Entry::getKey).toArray(Value[]::new);
        if (pairs.length == 2) {
            this.type = RankType.TwoPairs;
            if (pairs[0].getNumVal() > pairs[1].getNumVal()) {
                this.val = Optional.of(pairs[0]);
                this.val2 = Optional.of(pairs[1]);
            } else {
                this.val = Optional.of(pairs[1]);
                this.val2 = Optional.of(pairs[0]);
            }
        }
    }

    //One Pair: Two cards of the same value.
    private void checkForOnePair() {
        Map<Value, Integer> valueCounts = countCardsByValue();
        Value[] pairs = valueCounts.entrySet().stream().filter(e -> e.getValue() == 2).map(Map.Entry::getKey).toArray(Value[]::new);
        if (pairs.length == 1) {
            this.type = RankType.OnePair;
            this.val = Optional.of(pairs[0]);
        }
    }

    //High Card: Highest value card.
    private void checkForHighCard() {
        this.type = RankType.HighCard;
        this.val = Optional.of(highestValuedCard().getValue());
    }

    private Card highestValuedCard() {
        int highIndex = 0;
        for (int i = 1; i < 5; i++) {
            if (cards[i].getNumVal() > cards[highIndex].getNumVal()) {
                highIndex = i;
            }
        }

        return cards[highIndex];
    }

    private Map<Value, Integer> countCardsByValue() {
        Map<Value, Integer> valCount = new HashMap<>();
        for (Card card : cards) {
            Value value = card.getValue();
            if (valCount.containsKey(value)) {
                valCount.put(value, valCount.get(value) + 1);
            } else {
                valCount.put(value, 1);
            }
        }
        return valCount;
    }

    @Override
    public int compareTo(PokerRank o) {
        if (this.type != o.type) return this.type.weight - o.type.weight;

        if (this.val.isPresent()) {
            if (this.val.get() != o.val.get()) return this.val.get().getNumVal() - o.val.get().getNumVal();

            if (this.val2.isPresent() && this.val2.get() != o.val2.get()) return this.val2.get().getNumVal() - o.val2.get().getNumVal();
        }

        int[] thisValues = Arrays.stream(this.cards).mapToInt(Card::getNumVal).sorted().toArray();
        int[] otherValues = Arrays.stream(o.cards).mapToInt(Card::getNumVal).sorted().toArray();
        for (int i = 4; i >= 0; i--) {
            if (thisValues[i] != otherValues[i]) return thisValues[i] - otherValues[i];
        }

        return 0;
    }
}
