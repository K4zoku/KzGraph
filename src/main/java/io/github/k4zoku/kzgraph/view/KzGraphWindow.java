package io.github.k4zoku.kzgraph.view;

import io.github.k4zoku.kzgraph.resources.Icons;

import javax.swing.*;
import java.awt.*;

public class KzGraphWindow extends JFrame implements Runnable {

    private static final long serialVersionUID = -307187192468595122L;

    public KzGraphWindow(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 600);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Icons.APP_ICON));
        JMenuBar menuBar = new KzGraphMenuBar();
        setJMenuBar(menuBar);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Graph"));
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void run() {
        setVisible(true);
    }
}
