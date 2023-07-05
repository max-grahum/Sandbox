package pkg2dsandbox.Elements;

import java.util.Random;
import pkg2dsandbox.Action;
import pkg2dsandbox.SandboxPanel;

public abstract class Solid extends Element {

    public Solid(int x, int y) {
        super(x, y);
    }

    @Override
    public Action update() {

        if (this.y - 1 >= 0) {
            if (!(SandboxPanel.grid[this.x][this.y - 1] instanceof Fixed)) {
                this.pressure = SandboxPanel.grid[this.x][this.y - 1].pressure + this.density;
            }
        }

        if (this.y + 1 < SandboxPanel.GRID_HEIGHT) {
            if (SandboxPanel.grid[this.x][this.y + 1] instanceof Liquid
                    || SandboxPanel.grid[this.x][this.y + 1] instanceof Gas) {
                return Action.MOVES;
            }

            int randomNum = new Random().nextInt(2) * 2 - 1;
            if (this.x + randomNum < SandboxPanel.GRID_WIDTH
                    && this.x + randomNum >= 0
                    && (SandboxPanel.grid[this.x + randomNum][this.y + 1] instanceof Liquid
                    || SandboxPanel.grid[this.x + randomNum][this.y + 1] instanceof Gas)
                    && (SandboxPanel.grid[this.x + randomNum][this.y] instanceof Liquid
                    || SandboxPanel.grid[this.x + randomNum][this.y] instanceof Gas)) {
                if (randomNum == -1) {
                    return Action.MOVESW;
                } else {
                    return Action.MOVESE;
                }
            }
            if (this.x - randomNum < SandboxPanel.GRID_WIDTH
                    && this.x - randomNum >= 0
                    && (SandboxPanel.grid[this.x - randomNum][this.y + 1] instanceof Liquid
                    || SandboxPanel.grid[this.x - randomNum][this.y + 1] instanceof Gas)
                    && (SandboxPanel.grid[this.x - randomNum][this.y] instanceof Liquid
                    || SandboxPanel.grid[this.x - randomNum][this.y] instanceof Gas)) {
                if (randomNum == -1) {
                    return Action.MOVESE;
                } else {
                    return Action.MOVESW;
                }
            }
        }

        return Action.STAY;
    }

}
