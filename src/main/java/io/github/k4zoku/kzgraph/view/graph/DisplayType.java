package io.github.k4zoku.kzgraph.view.graph;

import java.awt.*;

public enum DisplayType {
    NORMAL,
    SELECTED,
    HIGHLIGHTED,
    ;

    public Color getBackgroundColor() {
        switch (this) {
            case NORMAL:
                return Color.WHITE;
            case SELECTED:
                return Color.YELLOW;
            case HIGHLIGHTED:
                return Color.CYAN;
        }
        return Color.WHITE;
    }

    public Color getForegroundColor() {
        switch (this) {
            case NORMAL:
                return Color.BLACK;
            case SELECTED:
                return Color.YELLOW;
            case HIGHLIGHTED:
                return Color.GREEN;
        }
        return Color.BLACK;
    }


    public Color getBorderColor() {
        switch (this) {
            case NORMAL:
                return Color.BLACK;
            case SELECTED:
                return Color.RED;
            case HIGHLIGHTED:
                return Color.GREEN;
        }
        return Color.BLACK;
    }
}
