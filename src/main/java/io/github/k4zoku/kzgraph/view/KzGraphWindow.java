package io.github.k4zoku.kzgraph.view;

import io.github.k4zoku.kzgraph.resources.Icons;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class KzGraphWindow extends JFrame implements Runnable {

    private static final long serialVersionUID = -307187192468595122L;

    public KzGraphWindow(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
        initLayout();
    }

    private void initComponents() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Icons.APP_ICON));
        setJMenuBar(new KzGraphMenuBar());
    }

    private void initLayout() {
        JPanel drawingPanel = new JPanel();
        drawingPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "Draw graph yourself",
                TitledBorder.LEFT,
                TitledBorder.ABOVE_TOP)
        );
        KzGraphDrawingPanel drawingPanelInstance = new KzGraphDrawingPanel();
        GroupLayout layout = new GroupLayout(drawingPanel);
        drawingPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(drawingPanelInstance)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(drawingPanelInstance)
        );
        setLayout(new BorderLayout());
        add(new KzGraphControlPanel(), BorderLayout.WEST);
        add(drawingPanel, BorderLayout.CENTER);
    }

    @Override
    public void run() {
        setVisible(true);
    }
}
