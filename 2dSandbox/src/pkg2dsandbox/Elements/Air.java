package pkg2dsandbox.Elements;

import java.awt.Color;

public class Air extends Gas {

    public Air(int x, int y) {
        super(x, y);
        this.name = "air";
        this.colour = Color.WHITE;
        this.density = 0.001225;
        this.pressure = this.density;
    }

    public Air clone() {
        Air returnAir = new Air(this.x, this.y);
        returnAir.density = this.density;
        returnAir.pressure = this.pressure;
        return returnAir;
    }

}
