package pkg2dsandbox;

public class Action {

    public int x;
    public int y;
    public Dirrection d;

    public Action(int x, int y, Dirrection d) {
        this.x = x;
        this.y = y;
        this.d = d;

        this.Act();
    }

    public void Act() {

    }

    public enum Dirrection {
        NOOP,
        STAY,
        MOVEN,
        MOVENE,
        MOVEE,
        MOVESE,
        MOVES,
        MOVESW,
        MOVEW,
        MOVENW
    }
}
