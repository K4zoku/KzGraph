package io.github.k4zoku.kzgraph.model.graph;

import org.jetbrains.annotations.NotNull;

public interface Edge {

    Vertex getSource();

    Vertex getDestination();

    double getWeight();

    void setWeight(double weight);

    boolean isDirected();

    boolean isWeighted();

    default boolean isUndirected() {
        return !isDirected();
    }

    default boolean equals(@NotNull Edge edge) {
        boolean result = isDirected() == edge.isDirected() && isWeighted() == edge.isWeighted();
        if (result) {
            result = getSource().equals(edge.getSource()) && getDestination().equals(edge.getDestination());
            if (isUndirected() && edge.isUndirected()) {
                result = result || getSource().equals(edge.getDestination()) && getDestination().equals(edge.getSource());
            }
            if (isWeighted() && edge.isWeighted()) {
                result = result && getWeight() == edge.getWeight();
            }
        }
        return result;
    }
}
