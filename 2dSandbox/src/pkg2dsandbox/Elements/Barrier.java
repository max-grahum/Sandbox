package pkg2dsandbox.Elements;

import java.awt.Color;

public class Barrier extends Fixed {

    public Barrier(int x, int y) {
        super(x, y);
        this.name = "barrier";
        this.colour = Color.BLACK;
    }

    public Barrier clone() {
        Barrier returnBarrier = new Barrier(this.x, this.y);
        returnBarrier.colour = this.colour;
        return returnBarrier;
    }

}
