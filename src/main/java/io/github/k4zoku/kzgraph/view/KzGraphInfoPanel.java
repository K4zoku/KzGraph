package io.github.k4zoku.kzgraph.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class KzGraphInfoPanel extends JPanel {

    private final ButtonGroup buttonGroup;
    private final JRadioButton matrixRadioButton;
    private final JRadioButton listRadioButton;
    private final JTextArea infoTextArea;
    private final JScrollPane infoScrollPane;

    public KzGraphInfoPanel() {
        super();
        this.matrixRadioButton = new JRadioButton("Adjacency Matrix");
        this.listRadioButton = new JRadioButton("Adjacency List");
        this.buttonGroup = new ButtonGroup();
        this.infoTextArea = new JTextArea();
        this.infoScrollPane = new JScrollPane(this.infoTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "Graph's Info",
                TitledBorder.LEFT,
                TitledBorder.ABOVE_TOP
        ));
        this.initComponents();
        this.initLayout();
    }

    private void initComponents() {
        this.buttonGroup.add(matrixRadioButton);
        this.buttonGroup.add(listRadioButton);
        this.matrixRadioButton.setSelected(true);

        this.infoTextArea.setEditable(true);
        this.infoTextArea.setLineWrap(false);
        this.infoTextArea.setWrapStyleWord(false);
        this.infoTextArea.setText("");
    }

    private void initLayout() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(matrixRadioButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(listRadioButton)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(infoScrollPane)
                                )
                                .addContainerGap()
                        )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(matrixRadioButton)
                                        .addComponent(listRadioButton)
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(infoScrollPane)
                                .addContainerGap()
                        )
        );
    }

    public String getInfoText() {
        return this.infoTextArea.getText();
    }

    public void setInfoText(String text) {
        this.infoTextArea.setText(text);
    }

    public boolean isMatrix() {
        return this.matrixRadioButton.isSelected();
    }

    public boolean isList() {
        return this.listRadioButton.isSelected();
    }
}
