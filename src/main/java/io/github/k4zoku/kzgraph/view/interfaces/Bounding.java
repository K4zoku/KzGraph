package io.github.k4zoku.kzgraph.view.interfaces;

import java.awt.*;

public interface Bounding {
    Rectangle getBounds();

    boolean contains(int x, int y);
}
