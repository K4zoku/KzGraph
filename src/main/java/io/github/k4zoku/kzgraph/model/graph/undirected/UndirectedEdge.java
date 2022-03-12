package io.github.k4zoku.kzgraph.model.graph.undirected;

import io.github.k4zoku.kzgraph.model.graph.Vertex;
import io.github.k4zoku.kzgraph.model.graph.simple.SimpleEdge;

public class UndirectedEdge extends SimpleEdge {

    public UndirectedEdge(Vertex source, Vertex target, double weight) {
        super(source, target, weight);
    }

    public UndirectedEdge(Vertex source, Vertex target) {
        super(source, target);
    }

    @Override
    public boolean isDirected() {
        return false;
    }
}
