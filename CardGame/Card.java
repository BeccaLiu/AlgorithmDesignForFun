package CardGame;

/**
 * Created by rliu on 1/8/17.
 */
public class Card implements Comparable<Card> {
    private Suits type;
    private Integer number;

    Card(Suits type, Integer num) {
        this.type = type;
        this.number = num;
    }


    @Override
    public int compareTo(Card o) {
        return 0;
    }
}
