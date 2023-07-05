package pkg2dsandbox.Elements;

import java.awt.Color;

public class Oil extends Liquid {

    public Oil(int x, int y) {
        super(x, y);
        this.name = "oil";
        this.colour = Color.ORANGE;
        this.density = 0.75;
    }

    @Override
    public Water clone() {
        Water returnWater = new Water(this.x, this.y);
        returnWater.density = this.density;
        returnWater.colour = this.colour;
        return returnWater;
    }

}
