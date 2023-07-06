package pkg2dsandbox.Elements;

import java.awt.Graphics;
import pkg2dsandbox.Action;

public abstract class Fixed extends Element {

    public Fixed(int x, int y) {
        super(x, y);
        this.density = -1; //no density, it should never move
    }

    @Override
    public Action update() {
        return null;
    }

}
