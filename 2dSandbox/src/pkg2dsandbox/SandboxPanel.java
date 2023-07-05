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

    public static String selected = "barrier";

    private Random rand;

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

        this.rand = new Random();
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

                if (i - 1 >= 0 && j - 1 >= 0) {
                    if (this.actionRequests.get(i - 1).get(j - 1).equals(Action.MOVESE)) {
                        this.actionBuffer.add(Action.MOVESE);
                    }
                }
                if (j - 1 >= 0) {
                    if (this.actionRequests.get(i).get(j - 1).equals(Action.MOVES)) {
                        this.actionBuffer.add(Action.MOVES);
                    }
                }
                if (i + 1 < GRID_WIDTH && j - 1 >= 0) {
                    if (this.actionRequests.get(i + 1).get(j - 1).equals(Action.MOVESW)) {
                        this.actionBuffer.add(Action.MOVESW);
                    }
                }
                if (i + 1 < GRID_WIDTH) {
                    if (this.actionRequests.get(i + 1).get(j).equals(Action.MOVEW)) {
                        this.actionBuffer.add(Action.MOVEW);
                    }
                }
                if (i + 1 < GRID_WIDTH && j + 1 < GRID_HEIGHT) {
                    if (this.actionRequests.get(i + 1).get(j + 1).equals(Action.MOVENW)) {
                        this.actionBuffer.add(Action.MOVENW);
                    }
                }
                if (j + 1 < GRID_HEIGHT) {
                    if (this.actionRequests.get(i).get(j + 1).equals(Action.MOVEN)) {
                        this.actionBuffer.add(Action.MOVEN);
                    }
                }
                if (i - 1 >= 0 && j + 1 < GRID_HEIGHT) {
                    if (this.actionRequests.get(i - 1).get(j + 1).equals(Action.MOVENE)) {
                        this.actionBuffer.add(Action.MOVENE);
                    }
                }
                if (i - 1 >= 0) {
                    if (this.actionRequests.get(i - 1).get(j).equals(Action.MOVEE)) {
                        this.actionBuffer.add(Action.MOVEE);
                    }
                }
                if (this.actionBuffer.size() <= 0) {
                    this.actionBuffer.add(Action.NOOP);
                }

                int randomNum = new Random().nextInt(this.actionBuffer.size());
                switch (this.actionBuffer.get(randomNum)) {
                    case MOVES:
                        this.next[i][j - 1] = this.grid[i][j].clone();
                        this.next[i][j - 1].y--;
                        this.next[i][j] = this.grid[i][j - 1].clone();
                        this.next[i][j].y++;
                        break;

                    case MOVESE:
                        this.next[i - 1][j - 1] = this.grid[i][j].clone();
                        this.next[i - 1][j - 1].y--;
                        this.next[i - 1][j - 1].x--;
                        this.next[i][j] = this.grid[i - 1][j - 1].clone();
                        this.next[i][j].y++;
                        this.next[i][j].x++;
                        break;

                    case MOVESW:
                        this.next[i + 1][j - 1] = this.grid[i][j].clone();
                        this.next[i + 1][j - 1].y--;
                        this.next[i + 1][j - 1].x++;
                        this.next[i][j] = this.grid[i + 1][j - 1].clone();
                        this.next[i][j].y++;
                        this.next[i][j].x--;
                        break;

                    case MOVEE:
                        this.next[i - 1][j] = this.grid[i][j].clone();
                        this.next[i - 1][j].x--;
                        this.next[i][j] = this.grid[i - 1][j].clone();
                        this.next[i][j].x++;
                        break;

                    case MOVEW:
                        this.next[i + 1][j] = this.grid[i][j].clone();
                        this.next[i + 1][j].x++;
                        this.next[i][j] = this.grid[i + 1][j].clone();
                        this.next[i][j].x--;
                        break;

                    case MOVEN:
                        this.next[i][j + 1] = this.grid[i][j].clone();
                        this.next[i][j + 1].y++;
                        this.next[i][j] = this.grid[i][j + 1].clone();
                        this.next[i][j].y--;
                        break;

                    case MOVENE:
                        this.next[i - 1][j + 1] = this.grid[i][j].clone();
                        this.next[i - 1][j + 1].y++;
                        this.next[i - 1][j + 1].x--;
                        this.next[i][j] = this.grid[i - 1][j + 1].clone();
                        this.next[i][j].y--;
                        this.next[i][j].x++;
                        break;

                    case MOVENW:
                        this.next[i + 1][j + 1] = this.grid[i][j].clone();
                        this.next[i + 1][j + 1].y++;
                        this.next[i + 1][j + 1].x++;
                        this.next[i][j] = this.grid[i + 1][j + 1].clone();
                        this.next[i][j].y--;
                        this.next[i][j].x--;
                        break;

                    case STAY:
                        break;
                }

            }
        }

        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                this.grid[i][j] = this.next[i][j];
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
