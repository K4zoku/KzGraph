package io.github.k4zoku.kzgraph.model.graph.simple;

import io.github.k4zoku.kzgraph.model.graph.Edge;
import io.github.k4zoku.kzgraph.model.graph.Vertex;

public abstract class SimpleEdge implements Edge {
    private final Vertex source;
    private final Vertex target;
    private double weight;

    protected SimpleEdge(Vertex source, Vertex target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    protected SimpleEdge(Vertex source, Vertex target) {
        this(source, target, 1.0);
    }

    @Override
    public Vertex getSource() {
        return source;
    }

    @Override
    public Vertex getDestination() {
        return target;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public boolean isWeighted() {
        return Double.isNaN(this.weight);
    }


}
