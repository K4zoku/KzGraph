package io.github.k4zoku.kzgraph.view.graph;

import io.github.k4zoku.kzgraph.model.graph.Edge;
import io.github.k4zoku.kzgraph.view.Utils;
import io.github.k4zoku.kzgraph.view.interfaces.Bounding;
import io.github.k4zoku.kzgraph.view.interfaces.Drawable;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.text.DecimalFormat;

public class EdgeView implements Drawable, Bounding {

    private final VertexView source;
    private final VertexView destination;

    @Getter
    private final Edge edge;

    @Getter
    @Setter
    private Color lineColor = Color.BLACK;

    @Getter
    @Setter
    private Color backgroundColor = Color.WHITE;

    @Getter
    @Setter
    private Color textColor = Color.BLACK;

    @Getter
    @Setter
    private DecimalFormat weightFormat = new DecimalFormat("#");

    @Getter
    @Setter
    private int lineWidth = 1;

    @Setter
    @Getter
    private int verticalPadding = 5;

    @Setter
    @Getter
    private int horizontalPadding = 10;

    @Getter
    private Rectangle bounds;

    public EdgeView(Edge edge, VertexView source, VertexView destination) {
        this.edge = edge;
        this.source = source;
        this.destination = destination;
        this.bounds = new Rectangle(source.getCenterX(), source.getCenterY(), destination.getCenterX(), destination.getCenterY());
    }

    public void draw(Graphics graphics, DisplayType displayType) {
        setBackgroundColor(displayType.getBackgroundColor());
        setLineColor(displayType.getBorderColor());
        setTextColor(displayType.getForegroundColor());
        draw(graphics);
    }

    @Override
    public void draw(Graphics graphics) {
        final Graphics2D g = (Graphics2D) graphics;
        this.bounds = new Rectangle(source.getCenterX(), source.getCenterY(), destination.getCenterX(), destination.getCenterY());
        g.setColor(getLineColor());
        g.setStroke(new BasicStroke(getLineWidth()));
        g.drawLine(source.getCenterX(), source.getCenterY(), destination.getCenterX(), destination.getCenterY());
        g.setColor(getBackgroundColor());
        int x = (source.getCenterX() + destination.getCenterX()) / 2;
        int y = (source.getCenterY() + destination.getCenterY()) / 2;
        String weight = getWeightFormat().format(edge.getWeight());
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(weight);
        int textHeight = fm.getHeight();
        Rectangle textBounds = new Rectangle(x - textWidth / 2 - getHorizontalPadding(), y - textHeight / 2 - getVerticalPadding(), textWidth + getHorizontalPadding() * 2, textHeight + getVerticalPadding() * 2);
        this.bounds = this.bounds.union(textBounds);
        g.fillRoundRect(textBounds.x, textBounds.y, textBounds.width, textBounds.height, 10, 10);
        g.setColor(getTextColor());
        Utils.drawCenteredString(weight, textBounds, g);
    }

    public boolean contains(int x, int y) {
        return this.bounds.contains(x, y);
    }
}
