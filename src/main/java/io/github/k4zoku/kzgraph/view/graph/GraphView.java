package io.github.k4zoku.kzgraph.view.graph;

import io.github.k4zoku.kzgraph.model.graph.Edge;
import io.github.k4zoku.kzgraph.model.graph.Graph;
import io.github.k4zoku.kzgraph.model.graph.Vertex;
import io.github.k4zoku.kzgraph.view.interfaces.Bounding;
import io.github.k4zoku.kzgraph.view.interfaces.Drawable;
import lombok.Getter;

import java.awt.*;
import java.util.Queue;
import java.util.*;

public class GraphView implements Drawable {

    private final Map<VertexView, DisplayType> vertexViews = new LinkedHashMap<>();
    private final Map<EdgeView, DisplayType> edgeViews = new LinkedHashMap<>();

    private final Queue<Bounding> updateQueue = new LinkedList<>();

    @Getter
    private final Graph graph;

    public GraphView(Graph graph) {
        this.graph = graph;
    }

    public void addVertex(Vertex vertex, int x, int y) {
        VertexView vertexView = new VertexView(vertex, x, y);
        this.graph.addVertex(vertex);
        this.vertexViews.put(vertexView, DisplayType.NORMAL);
        this.updateQueue.add(vertexView);
    }

    public void addEdge(VertexView from, VertexView to) {
        this.graph.addEdge(from.getVertex(), to.getVertex());
        Edge edge = this.graph.getEdge(from.getVertex(), to.getVertex());
        EdgeView edgeView = new EdgeView(edge, from, to);
        this.edgeViews.put(edgeView, DisplayType.NORMAL);
        this.updateQueue.add(edgeView);
    }

    public Optional<VertexView> getVertexAt(int x, int y) {
        return this.vertexViews.keySet().stream()
                .filter(vertexView -> vertexView.contains(x, y))
                .findFirst();
    }

    public Optional<EdgeView> getEdgeAt(int x, int y) {
        return this.edgeViews.keySet().stream()
                .filter(edgeView -> edgeView.contains(x, y))
                .findFirst();
    }

    public void select(int x, int y) {
        VertexView vertexView = getVertexAt(x, y).orElse(null);
        if (vertexView != null) {
            select(vertexView);
        } else {
            getEdgeAt(x, y).ifPresent(this::select);
        }
    }

    public void select(VertexView vertexView) {
        this.vertexViews.put(vertexView, DisplayType.SELECTED);
    }

    public void select(EdgeView edgeView) {
        this.edgeViews.put(edgeView, DisplayType.SELECTED);
    }

    public boolean isSelected(VertexView vertexView) {
        return this.vertexViews.get(vertexView) == DisplayType.SELECTED;
    }

    public boolean isSelected(EdgeView edgeView) {
        return this.edgeViews.get(edgeView) == DisplayType.SELECTED;
    }

    public void highlight(int x, int y) {
        VertexView vertexView = getVertexAt(x, y).orElse(null);
        if (vertexView != null) {
            highlight(vertexView);
        } else {
            getEdgeAt(x, y).ifPresent(this::highlight);
        }
    }

    public void highlight(VertexView vertexView) {
        this.vertexViews.put(vertexView, DisplayType.HIGHLIGHTED);
    }

    public void highlight(EdgeView edgeView) {
        this.edgeViews.put(edgeView, DisplayType.HIGHLIGHTED);
    }

    public boolean isHighlighted(VertexView vertexView) {
        return this.vertexViews.get(vertexView) == DisplayType.HIGHLIGHTED;
    }

    public boolean isHighlighted(EdgeView edgeView) {
        return this.edgeViews.get(edgeView) == DisplayType.HIGHLIGHTED;
    }

    public void normal(VertexView vertexView) {
        this.vertexViews.put(vertexView, DisplayType.NORMAL);
    }

    public void normal(EdgeView edgeView) {
        this.edgeViews.put(edgeView, DisplayType.NORMAL);
    }

    public void normal() {
        this.vertexViews.clear();
        this.edgeViews.clear();
    }

    @Override
    public void draw(Graphics graphics) {
        this.vertexViews.forEach((vertexView, displayType) -> vertexView.draw(graphics, displayType));
        this.edgeViews.forEach((edgeView, displayType) -> edgeView.draw(graphics, displayType));
    }
}
