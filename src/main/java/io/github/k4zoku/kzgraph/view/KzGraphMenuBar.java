package io.github.k4zoku.kzgraph.view;

import io.github.k4zoku.kzgraph.resources.Icons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class KzGraphMenuBar extends JMenuBar {

    private final JMenu fileMenu;
    private final JMenuItem openItem;
    private final JMenuItem saveItem;
    private final JMenu saveAsMenu;
    private final JMenuItem saveAsMatrixItem;
    private final JMenuItem saveAsListItem;
    private final JMenuItem exportItem;
    private final JMenuItem exitItem;

    private final JMenu editMenu;
    private final JMenuItem undoItem;
    private final JMenuItem redoItem;
    private final JMenuItem clearItem;
    private final JMenuItem deleteItem;
    private final JMenuItem addVertexItem;
    private final JMenuItem addEdgeItem;

    private final JMenu algorithmMenu;
    private final JMenu traversalMenu;
    private final JMenuItem bfsItem;
    private final JMenuItem dfsItem;
    private final JMenu minimumSpanningTreeMenu;
    private final JMenuItem primItem;
    private final JMenuItem kruskalItem;
    private final JMenu shortestPathMenu;
    private final JMenuItem dijkstraItem;

    public KzGraphMenuBar() {
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        saveAsMenu = new JMenu("Save As");
        saveAsMatrixItem = new JMenuItem("Matrix");
        saveAsListItem = new JMenuItem("List");
        exportItem = new JMenuItem("Export");
        exitItem = new JMenuItem("Exit");
        editMenu = new JMenu("Edit");
        undoItem = new JMenuItem("Undo");
        redoItem = new JMenuItem("Redo");
        clearItem = new JMenuItem("Clear");
        deleteItem = new JMenuItem("Delete");
        addVertexItem = new JMenuItem("Vertex");
        addEdgeItem = new JMenuItem("Edge");
        algorithmMenu = new JMenu("Algorithm");
        traversalMenu = new JMenu("Traversal");
        bfsItem = new JMenuItem("BFS");
        dfsItem = new JMenuItem("DFS");
        minimumSpanningTreeMenu = new JMenu("Minimum Spanning Tree");
        primItem = new JMenuItem("Prim Algorithm");
        kruskalItem = new JMenuItem("Kruskal Algorithm");
        shortestPathMenu = new JMenu("Shortest Path");
        dijkstraItem = new JMenuItem("Dijkstra Algorithm");
        initComponents();
    }

    private void initFileMenu() {
        fileMenu.setMnemonic('F');

        openItem.setMnemonic('O');
        openItem.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_MASK));
        openItem.setToolTipText("Open a graph file");
        openItem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Icons.OPEN_ICON)));
        fileMenu.add(openItem);

        saveItem.setMnemonic('S');
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
        saveItem.setToolTipText("Save the current graph");
        saveItem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Icons.SAVE_ICON)));
        fileMenu.add(saveItem);

        saveAsMatrixItem.setMnemonic('M');
        saveAsMatrixItem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        saveAsMatrixItem.setToolTipText("Save the current graph as a matrix");
        saveAsMatrixItem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Icons.MATRIX_ICON)));
        saveAsMenu.add(saveAsMatrixItem);

        saveAsListItem.setMnemonic('L');
        saveAsListItem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
        saveAsListItem.setToolTipText("Save the current graph as a list");
        saveAsListItem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Icons.LIST_ICON)));
        saveAsMenu.add(saveAsListItem);

        saveAsMenu.setMnemonic('A');
        saveAsMenu.setToolTipText("Save the current graph as a matrix or a list");
        saveAsMenu.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Icons.SAVE_AS_ICON)));
        fileMenu.add(saveAsMenu);

        exportItem.setMnemonic('E');
        exportItem.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_MASK));
        exportItem.setToolTipText("Export the current graph as a picture");
        exportItem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Icons.EXPORT_AS_IMAGE_ICON)));
        fileMenu.add(exportItem);

        fileMenu.addSeparator();

        exitItem.setMnemonic('X');
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
        exitItem.setToolTipText("Exit the program");
        exitItem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Icons.EXIT_ICON)));
        fileMenu.add(exitItem);
    }

    private void initEditMenu() {
        editMenu.setMnemonic('E');

        undoItem.setMnemonic('U');
        undoItem.setAccelerator(KeyStroke.getKeyStroke('Z', InputEvent.CTRL_MASK));
        undoItem.setToolTipText("Undo the last action");
//        undoItem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Icons.UNDO_ICON)));
        editMenu.add(undoItem);

        redoItem.setMnemonic('R');
        redoItem.setAccelerator(KeyStroke.getKeyStroke('Y', InputEvent.CTRL_MASK));
        redoItem.setToolTipText("Redo the last action");
//        redoItem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Icons.REDO_ICON)));
        editMenu.add(redoItem);

        editMenu.addSeparator();

        addVertexItem.setMnemonic('V');
        addVertexItem.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        addVertexItem.setToolTipText("Add a new vertex");
        editMenu.add(addVertexItem);

        addEdgeItem.setMnemonic('E');
        addEdgeItem.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        addEdgeItem.setToolTipText("Add a new edge");
        editMenu.add(addEdgeItem);

        editMenu.addSeparator();

        clearItem.setMnemonic('C');
        clearItem.setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.CTRL_MASK));
        clearItem.setToolTipText("Clear the current graph");
        clearItem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Icons.CLEAR_ICON)));
        editMenu.add(clearItem);

        deleteItem.setMnemonic('D');
        deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        deleteItem.setToolTipText("Delete the selected vertex");
//        deleteItem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Icons.DELETE_ICON)));
        editMenu.add(deleteItem);

    }

    private void initAlgorithmMenu() {
        algorithmMenu.setMnemonic('A');

        traversalMenu.setMnemonic('T');
        traversalMenu.setToolTipText("Traversal algorithms");
//        traversalMenu.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Icons.TRAVERSAL_ICON)));

        bfsItem.setMnemonic('B');
        bfsItem.setAccelerator(KeyStroke.getKeyStroke('B', InputEvent.CTRL_MASK));
        bfsItem.setToolTipText("Breadth-first search");
        traversalMenu.add(bfsItem);

        dfsItem.setMnemonic('D');
        dfsItem.setAccelerator(KeyStroke.getKeyStroke('D', InputEvent.CTRL_MASK));
        dfsItem.setToolTipText("Depth-first search");
        traversalMenu.add(dfsItem);

        algorithmMenu.add(traversalMenu);

        shortestPathMenu.setMnemonic('S');

        dijkstraItem.setMnemonic('J');
        dijkstraItem.setAccelerator(KeyStroke.getKeyStroke('J', InputEvent.CTRL_MASK));
        dijkstraItem.setToolTipText("Dijkstra's algorithm");
        shortestPathMenu.add(dijkstraItem);

        algorithmMenu.add(shortestPathMenu);

        minimumSpanningTreeMenu.setMnemonic('M');

        primItem.setMnemonic('P');
        primItem.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_MASK));
        primItem.setToolTipText("Prim's algorithm");
        minimumSpanningTreeMenu.add(primItem);

        kruskalItem.setMnemonic('K');
        kruskalItem.setAccelerator(KeyStroke.getKeyStroke('K', InputEvent.CTRL_MASK));
        kruskalItem.setToolTipText("Kruskal's algorithm");
        minimumSpanningTreeMenu.add(kruskalItem);

        algorithmMenu.add(minimumSpanningTreeMenu);
    }

    private void initComponents() {
        initFileMenu();
        add(fileMenu);
        initEditMenu();
        add(editMenu);
        initAlgorithmMenu();
        add(algorithmMenu);
    }


}
