package pkg2dsandbox.Elements;

import java.awt.Color;
import java.util.Random;
import pkg2dsandbox.*;

public class Sand extends Solid {

    public Sand(int x, int y) {
        super(x, y);
        this.name = "sand";
        this.density = 1.52;
        Random rand = new Random();
        this.colour = new Color(212 + (rand.nextInt(30) - 15), 189 + (rand.nextInt(30) - 15), 19 + (rand.nextInt(30) - 15));
    }

    @Override
    public Sand clone() {
        Sand returnSand = new Sand(this.x, this.y);
        returnSand.density = this.density;
        returnSand.colour = this.colour;
        return returnSand;
    }

}
