package pkg2dsandbox;

public class Action {

    public int x, x2, y, y2;

    public Action(int x, int y, int x2, int y2) {
        //start
        this.x = x;
        this.y = y;

        //end
        this.x2 = x2;
        this.y2 = y2;

    }

    public boolean destinedFor(int cx, int cy) {
        if (cx == this.x2 && cy == this.y2) {
            return true;
        }
        return false;
    }

    public void act() {
        SandboxPanel.next[this.x][this.y] = SandboxPanel.grid[this.x2][this.y2].clone();
        SandboxPanel.next[this.x][this.y].x = this.x;
        SandboxPanel.next[this.x][this.y].y = this.y;
        SandboxPanel.next[this.x2][this.y2] = SandboxPanel.grid[this.x][this.y].clone();
        SandboxPanel.next[this.x2][this.y2].x = this.x2;
        SandboxPanel.next[this.x2][this.y2].y = this.y2;
    }

    @Override
    public String toString() {
         return "(" + this.x + ", " + this.y + ")->(" + this.x2 + ", " + this.y2 + ")";
    }

}
