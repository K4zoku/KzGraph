package io.github.k4zoku.kzgraph.view;

import java.awt.*;

public class Utils {
    private Utils() {
        // no instance
    }

    public static void drawCenteredString(String text, Rectangle bounds, Graphics graphics) {
        FontMetrics metrics = graphics.getFontMetrics();
        int x = bounds.x + (bounds.width - metrics.stringWidth(text)) / 2;
        int y = bounds.y + ((bounds.height - metrics.getHeight()) / 2) + metrics.getAscent();
        graphics.drawString(text, x, y);
    }
}
