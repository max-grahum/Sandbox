package pkg2dsandbox.Elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import pkg2dsandbox.Action;
import pkg2dsandbox.SandboxPanel;

public abstract class Liquid extends Element {

    public Liquid(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        int re = this.colour.getRed();
        int gr = this.colour.getGreen();
        int bl = this.colour.getBlue();
        int reDarken = (int) (this.pressure * re / (SandboxPanel.GRID_HEIGHT * 2));
        int grDarken = (int) (this.pressure * gr / (SandboxPanel.GRID_HEIGHT * 2));
        int blDarken = (int) (this.pressure * bl / (SandboxPanel.GRID_HEIGHT * 2));

        g.setColor(new Color(re - reDarken, gr - grDarken, bl - blDarken));
        g.fillRect((x * SandboxPanel.RESOLUTION), (y * SandboxPanel.RESOLUTION) + 1, SandboxPanel.RESOLUTION - 2, SandboxPanel.RESOLUTION - 2);
        g.setColor(Color.WHITE);
        g.drawString("" + Math.floor(this.pressure + 0.5), x * SandboxPanel.RESOLUTION, y * SandboxPanel.RESOLUTION + SandboxPanel.RESOLUTION);
    }

    @Override
    public Action update() {

        if (this.y - 1 >= 0) {
            if (!(SandboxPanel.next[this.x][this.y - 1] instanceof Fixed)) {
                SandboxPanel.next[this.x][this.y].pressure = SandboxPanel.next[this.x][this.y].density + SandboxPanel.next[this.x][this.y - 1].pressure;
            }
        }

        int randomNum = new Random().nextInt(2) * 2 - 1;

        if (this.y + 1 < SandboxPanel.GRID_HEIGHT) {
            //vertical
            if (SandboxPanel.grid[this.x][this.y + 1] instanceof Gas
                    || (SandboxPanel.grid[this.x][this.y + 1] instanceof Liquid
                    && SandboxPanel.grid[this.x][this.y + 1].density < SandboxPanel.grid[this.x][this.y].density)) {
                return new Action(this.x, this.y, this.x, this.y + 1);
            }

            //1 diagonal
            if (this.x + randomNum < SandboxPanel.GRID_WIDTH
                    && this.x + randomNum >= 0
                    && (SandboxPanel.grid[this.x + randomNum][this.y] instanceof Gas
                    || (SandboxPanel.grid[this.x + randomNum][this.y] instanceof Liquid
                    && SandboxPanel.grid[this.x + randomNum][this.y].density < SandboxPanel.grid[this.x][this.y].density))
                    && (SandboxPanel.grid[this.x + randomNum][this.y + 1] instanceof Gas
                    || (SandboxPanel.grid[this.x + randomNum][this.y + 1] instanceof Liquid
                    && SandboxPanel.grid[this.x + randomNum][this.y + 1].density < SandboxPanel.grid[this.x][this.y].density))) {
                return new Action(this.x, this.y, this.x + randomNum, this.y + 1);
            }
            if (this.x - randomNum < SandboxPanel.GRID_WIDTH
                    && this.x - randomNum >= 0
                    && (SandboxPanel.grid[this.x - randomNum][this.y] instanceof Gas
                    || (SandboxPanel.grid[this.x - randomNum][this.y] instanceof Liquid
                    && SandboxPanel.grid[this.x - randomNum][this.y].density < SandboxPanel.grid[this.x][this.y].density))
                    && (SandboxPanel.grid[this.x - randomNum][this.y + 1] instanceof Gas
                    || (SandboxPanel.grid[this.x - randomNum][this.y + 1] instanceof Liquid
                    && SandboxPanel.grid[this.x - randomNum][this.y + 1].density < SandboxPanel.grid[this.x][this.y].density))) {
                return new Action(this.x, this.y, this.x - randomNum, this.y + 1);
            }
        }

        //1 horizontal
        if (this.x + randomNum < SandboxPanel.GRID_WIDTH
                && this.x + randomNum >= 0
                && (SandboxPanel.grid[this.x + randomNum][this.y] instanceof Gas
                || (SandboxPanel.grid[this.x + randomNum][this.y] instanceof Liquid
                && SandboxPanel.grid[this.x + randomNum][this.y].density < SandboxPanel.grid[this.x][this.y].density))) {
            return new Action(this.x, this.y, this.x + randomNum, this.y);
        }
        if (this.x - randomNum < SandboxPanel.GRID_WIDTH
                && this.x - randomNum >= 0
                && (SandboxPanel.grid[this.x - randomNum][this.y] instanceof Gas
                || (SandboxPanel.grid[this.x - randomNum][this.y] instanceof Liquid
                && SandboxPanel.grid[this.x - randomNum][this.y].density < SandboxPanel.grid[this.x][this.y].density))) {
            return new Action(this.x, this.y, this.x - randomNum, this.y);
        }

        return null;
    }

}
