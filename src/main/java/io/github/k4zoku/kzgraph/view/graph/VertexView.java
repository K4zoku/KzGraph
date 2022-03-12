package io.github.k4zoku.kzgraph.view.graph;

import io.github.k4zoku.kzgraph.model.graph.Vertex;
import io.github.k4zoku.kzgraph.view.Utils;
import io.github.k4zoku.kzgraph.view.interfaces.Bounding;
import io.github.k4zoku.kzgraph.view.interfaces.Drawable;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class VertexView implements Drawable, Bounding {

    private static final int DEFAULT_VERTEX_RADIUS = 12;
    @Getter
    private final Vertex vertex;
    @Getter
    private Rectangle bounds;
    @Getter
    private int radius;

    @Getter
    @Setter
    private Color backgroundColor = Color.WHITE;

    @Getter
    @Setter
    private Color borderColor = Color.BLACK;

    @Getter
    @Setter
    private Color textColor = Color.BLACK;

    public VertexView(Vertex vertex, int x, int y, int radius) {
        this.vertex = vertex;
        this.radius = radius;
        final int diameter = radius * 2;
        this.bounds = new Rectangle(x - radius, y - radius, diameter, diameter);
    }

    public VertexView(Vertex vertex, int x, int y) {
        this(vertex, x, y, DEFAULT_VERTEX_RADIUS);
    }

    public void draw(Graphics graphics, DisplayType displayType) {
        setBackgroundColor(displayType.getBackgroundColor());
        setBorderColor(displayType.getBorderColor());
        setTextColor(displayType.getForegroundColor());
        draw(graphics);
    }

    public void draw(Graphics graphics) {
        final Graphics2D g2 = (Graphics2D) graphics;
        Shape circle = new Ellipse2D.Double(bounds.getX() + 1, bounds.getY() + 1, bounds.getWidth() - 2, bounds.getHeight() - 2);
        g2.setColor(getBackgroundColor());
        g2.fill(circle);
        g2.setColor(getBorderColor());
        g2.draw(circle);
        g2.setColor(getTextColor());
        Utils.drawCenteredString(vertex.getLabel(), bounds, g2);
    }

    public void setRadius(int radius) {
        this.radius = radius;
        final int diameter = radius * 2;
        this.bounds = new Rectangle(bounds.x - radius, bounds.y - radius, diameter, diameter);
    }

    public int getX() {
        return bounds.x;
    }

    public void setX(int x) {
        bounds.x = x;
    }

    public int getY() {
        return bounds.y;
    }

    public void setY(int y) {
        bounds.y = y;
    }

    public int getCenterX() {
        return bounds.x + radius;
    }

    public int getCenterY() {
        return bounds.y + radius;
    }

    public boolean contains(int x, int y) {
        return bounds.contains(x, y);
    }

    @Override
    public int hashCode() {
        return vertex.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (getClass() != o.getClass())) return false;
        VertexView that = (VertexView) o;
        return vertex.equals(that.vertex);
    }

}
