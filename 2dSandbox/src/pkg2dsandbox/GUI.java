package pkg2dsandbox;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.Border;
import static pkg2dsandbox.SandboxPanel.SCREEN_HEIGHT;
import static pkg2dsandbox.SandboxPanel.SCREEN_WIDTH;

public class GUI extends JPanel {

    JButton barrierBtn, woodBtn, airBtn, sandBtn, waterBtn, oilBtn;
    JPanel fixedPnl, solidPnl, liquidPnl, gasPnl, tempPnl;

    JButton fixedBtn, solidBtn, liquidBtn, gasBtn, tempBtn;
    JPanel cards;
    CardLayout cardLayout;

    GUI() {
        barrierBtn = new JButton("Barrier");
        barrierBtn.addActionListener(e -> SandboxPanel.select("barrier"));
        woodBtn = new JButton("Wood");
        woodBtn.addActionListener(e -> SandboxPanel.select("wood"));

        sandBtn = new JButton("Sand");
        sandBtn.addActionListener(e -> SandboxPanel.select("sand"));

        waterBtn = new JButton("Water");
        waterBtn.addActionListener(e -> SandboxPanel.select("water"));
        oilBtn = new JButton("Oil");
        oilBtn.addActionListener(e -> SandboxPanel.select("oil"));

        airBtn = new JButton("Air");
        airBtn.addActionListener(e -> SandboxPanel.select("air"));

        fixedPnl = new JPanel();
        fixedPnl.add(barrierBtn);
        fixedPnl.add(woodBtn);
        solidPnl = new JPanel();
        solidPnl.add(sandBtn);
        liquidPnl = new JPanel();
        liquidPnl.add(waterBtn);
        liquidPnl.add(oilBtn);
        gasPnl = new JPanel();
        gasPnl.add(airBtn);
        tempPnl = new JPanel();

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.add(fixedPnl, "Fixed");
        cards.add(solidPnl, "Solid");
        cards.add(liquidPnl, "Liquid");
        cards.add(gasPnl, "Gas");
        cards.add(tempPnl, "Tempurature");

        fixedBtn = new JButton("Fixed");
        fixedBtn.addActionListener(e -> selectFixed());
        solidBtn = new JButton("Solid");
        solidBtn.addActionListener(e -> selectSolid());
        liquidBtn = new JButton("Liquid");
        liquidBtn.addActionListener(e -> selectLiquid());
        gasBtn = new JButton("Gas");
        gasBtn.addActionListener(e -> selectGas());
        tempBtn = new JButton("Tempurature");
        tempBtn.addActionListener(e -> selectTemp());

        this.setPreferredSize(new Dimension(500, 300));
        this.setVisible(true);
        this.setFocusable(true);

        this.add(fixedBtn, BorderLayout.NORTH);
        this.add(solidBtn, BorderLayout.NORTH);
        this.add(liquidBtn, BorderLayout.NORTH);
        this.add(gasBtn, BorderLayout.NORTH);
        this.add(tempBtn, BorderLayout.NORTH);
        this.add(cards, BorderLayout.CENTER);

        selectSolid();
    }

    public void selectFixed() {
        cardLayout.show(cards, "Fixed");
    }

    public void selectSolid() {
        cardLayout.show(cards, "Solid");
    }

    public void selectLiquid() {
        cardLayout.show(cards, "Liquid");
    }

    public void selectGas() {
        cardLayout.show(cards, "Gas");
    }

    public void selectTemp() {
        cardLayout.show(cards, "Tempurature");
    }
}
