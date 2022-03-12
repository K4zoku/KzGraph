package io.github.k4zoku.kzgraph.model.graph.simple;

import io.github.k4zoku.kzgraph.model.graph.Vertex;

public class SimpleVertex implements Vertex {

    private String label;

    public SimpleVertex(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }
}
