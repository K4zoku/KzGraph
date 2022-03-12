package io.github.k4zoku.kzgraph.view;

import javax.swing.*;

public class KzGraphControlPanel extends JPanel {

    private final JLabel instruction;
    private final KzGraphInfoPanel infoPanel;

    public KzGraphControlPanel() {
        super();
        this.instruction = new JLabel();
        this.infoPanel = new KzGraphInfoPanel();
        this.initComponents();
        this.initLayout();
    }

    private void initComponents() {
        this.instruction.setText(
                "<html>" +
                        "<b> Draws vertices: </b><br>" +
                        "<ul>" +
                        "<li> Hold ctrl and click on the <br> canvas to add a vertex </li>" +
                        "<li> Hold shift and clock on the <br> vertex that you want to <br> remove </li>" +
                        "<li> Drag the vertex to move it </li>" +
                        "</ul>" +
                        "<b> Draws edges: </b><br>" +
                        "<ul>" +
                        "<li> Create new edge: </li>" +
                        "<ol>" +
                        "<li> Select start vertex </li>" +
                        "<li> Select end vertex </li>" +
                        "<li> Input edge's weight </li>" +
                        "</ol>" +
                        "<li> Click on value of the exist <br> edge to update edge's weight</li>" +
                        "</ul>" +
                        "</html>"
        );
    }

    private void initLayout() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(this.infoPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(this.instruction, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(this.instruction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(this.infoPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
        );
    }
}
