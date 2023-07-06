package pkg2dsandbox;

import pkg2dsandbox.Elements.Element;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import pkg2dsandbox.Elements.*;

public class SandboxPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    public static final int RESOLUTION = 20;
    public static final int SCREEN_WIDTH = 500;
    public static final int SCREEN_HEIGHT = 500;

    public static final int GRID_WIDTH = SCREEN_WIDTH / RESOLUTION;
    public static final int GRID_HEIGHT = SCREEN_HEIGHT / RESOLUTION;

    private final int DELAY = 50;
    private boolean running = false;
    private Timer timer;

    private int mouseX, mouseY;
    private boolean mouseDown = false;

    public static String selected = "sand";

    public static Element[][] grid;
    public static Element[][] next;
    public static ArrayList<ArrayList<Action>> actionRequests;
    private ArrayList<Action> actionBuffer;

    SandboxPanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setVisible(true);
        this.setFocusable(true);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.grid = new Element[GRID_WIDTH][GRID_HEIGHT];
        this.next = new Element[GRID_WIDTH][GRID_HEIGHT];
        this.actionRequests = new ArrayList<>();
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                this.grid[i][j] = new Air(i, j);
            }
        }

        this.actionBuffer = new ArrayList<>();

        start();
    }

    public void start() {
        this.running = true;
        this.timer = new Timer(DELAY, this);
        this.timer.start();
    }

    public void update() {
        if (mouseDown) {
            this.grid[mouseX][mouseY] = this.getSelected(mouseX, mouseY);
        }

        actionRequests.clear();

        for (int i = 0; i < GRID_WIDTH; i++) {
            ArrayList<Action> temp = new ArrayList<>();
            for (int j = 0; j < GRID_HEIGHT; j++) {
                this.next[i][j] = this.grid[i][j].clone();

                temp.add(this.grid[i][j].update());
            }
            actionRequests.add(temp);
        }

        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {

                this.actionBuffer.clear();
                for (int xOff = -1; xOff < 2; xOff++) {
                    for (int yOff = -1; yOff < 2; yOff++) {
                        if (i + xOff >= 0 && i + xOff < this.GRID_WIDTH
                                && j + yOff >= 0 && j + yOff < this.GRID_HEIGHT) {
                            Action action = this.actionRequests.get(i + xOff).get(j + yOff);
                            if (action != null) {
                                if (action.destinedFor(i, j)) {
                                    this.actionBuffer.add(action);
                                }
                            }
                        }
                    }
                }

                if (this.actionBuffer.size() > 0) {
                    int randomNum = new Random().nextInt(this.actionBuffer.size());
                    this.actionBuffer.get(randomNum).act();
                }
            }
        }

        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                this.grid[i][j] = this.next[i][j].clone();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                this.grid[i][j].draw(g);
            }
        }

    }

    public static void select(String selected) {
        SandboxPanel.selected = selected;
    }

    public Element getSelected(int x, int y) {
        Element e = new Barrier(x, y);

        switch (this.selected) {
            case "barrier":
                e = new Barrier(x, y);
                break;
            case "wood":
                e = new Wood(x, y);
                break;
            case "sand":
                e = new Sand(x, y);
                break;
            case "water":
                e = new Water(x, y);
                break;
            case "oil":
                e = new Oil(x, y);
                break;
            case "air":
                e = new Air(x, y);
                break;
        }

        return e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.running) {
            this.update();
            this.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mouseDown = true;
        this.mouseX = e.getX() / RESOLUTION;
        this.mouseY = e.getY() / RESOLUTION;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.mouseDown = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.mouseDown = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mouseX = e.getX() / RESOLUTION;
        this.mouseY = e.getY() / RESOLUTION;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
