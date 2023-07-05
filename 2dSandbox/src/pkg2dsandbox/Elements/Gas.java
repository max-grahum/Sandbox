package pkg2dsandbox.Elements;

import pkg2dsandbox.Action;
import pkg2dsandbox.SandboxPanel;

public abstract class Gas extends Element {

    public Gas(int x, int y) {
        super(x, y);
    }

    @Override
    public Action update() {
        return Action.STAY;
    }

}
