package pkg2dsandbox.Elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import pkg2dsandbox.Action;
import pkg2dsandbox.SandboxPanel;

public abstract class Element {

    public int x;
    public int y;

    //basics
    public String name;
    public Color colour;

    //fluid dynamics
    public double density;
    public double pressure;
    public int antiViscousity; //1-3
    public double friction;
    public boolean inMotion;

    //thermodynamics
    public double tempurature;
    public double conductivity;
    public double ignitionPoint;
    public double meltingPoint;
    public double boilingPoint;
    public double freezingPoint;

    public Element(int x, int y) {
        this.x = x;
        this.y = y;

        this.inMotion = false;
        this.tempurature = 20; //room temp
    }

    public void draw(Graphics g) {
        g.setColor(this.colour);
        g.fillRect((this.x * SandboxPanel.RESOLUTION), (this.y * SandboxPanel.RESOLUTION) + 1, SandboxPanel.RESOLUTION - 2, SandboxPanel.RESOLUTION - 2);
    }

    public abstract Action update();

    public abstract Element clone();

}
