package pkg2dsandbox.Elements;

import java.awt.Color;

public class Water extends Liquid {

    public Water(int x, int y) {
        super(x, y);
        this.name = "water";
        this.colour = new Color(15, 50, 255);
        this.density = 1;
        this.pressure = this.density;
        this.antiViscousity = 3;
    }

    @Override
    public Water clone() {
        Water returnWater = new Water(this.x, this.y);
        returnWater.density = this.density;
        returnWater.pressure = this.pressure;
        return returnWater;
    }

}
