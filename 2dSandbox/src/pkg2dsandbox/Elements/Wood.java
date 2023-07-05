package pkg2dsandbox.Elements;

import java.awt.Color;

public class Wood extends Fixed {

    public Wood(int x, int y) {
        super(x, y);
        this.name = "wood";
        this.colour = new Color(154, 106, 63);
    }

    @Override
    public Wood clone() {
        Wood returnWood = new Wood(this.x, this.y);
        returnWood.name = this.name;
        returnWood.colour = this.colour;

        return returnWood;
    }

}
