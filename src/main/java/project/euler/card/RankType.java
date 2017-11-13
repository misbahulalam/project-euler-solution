package project.euler.card;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Nov 13, 2017
 */
public enum RankType {

    HighCard(1),
    OnePair(2),
    TwoPairs(3),
    ThreeOfAKind(4),
    Straight(5),
    Flush(6),
    FullHouse(7),
    FourOfAKind(8),
    StraightFlush(9),
    RoyalFlush(10);

    int weight;

    RankType(int weight) {
        this.weight = weight;
    }

}
