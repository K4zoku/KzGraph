package io.github.k4zoku.kzgraph.model.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public interface Graph {

    void addVertex(Vertex vertex);

    void addEdge(Edge edge);

    void addEdge(Vertex source, Vertex destination);

    void addEdge(Vertex source, Vertex destination, double weight);

    void removeVertex(Vertex vertex);

    void removeEdge(Edge edge);

    Set<Vertex> getVertices();

    default Set<Vertex> getNeighbors(Vertex vertex) {
        return getEdges().stream()
                .filter(edge -> edge.getSource().equals(vertex) || edge.getDestination().equals(vertex))
                .map(edge -> edge.getSource().equals(vertex) ? edge.getDestination() : edge.getSource())
                .collect(Collectors.toSet());
    }

    Set<Edge> getEdges();

    default Set<Edge> getEdges(Vertex vertex) {
        return getEdges().stream()
                .filter(edge -> edge.getSource().equals(vertex) || edge.getDestination().equals(vertex))
                .collect(Collectors.toSet());
    }

    default Set<Edge> getEdges(Vertex vertex, Vertex target) {
        return getEdges().stream()
                .filter(edge -> edge.getSource().equals(vertex) && edge.getDestination().equals(target))
                .collect(Collectors.toSet());
    }

    default Edge getEdge(Vertex source, Vertex target) {
        return getEdges().stream()
                .parallel()
                .filter(edge -> edge.getSource().equals(source) && edge.getDestination().equals(target))
                .findAny()
                .orElse(null);
    }

    void clear();

    default boolean isEmpty() {
        return getVertices().isEmpty();
    }

    default double[][] toAdjacencyMatrix() {
        Set<Vertex> vertices = getVertices();
        int size = vertices.size();
        double[][] matrix = new double[size][size];
        Map<Vertex, Integer> vertexToIndex = new HashMap<>(size);
        int index = 0;
        for (Vertex vertex : vertices) {
            vertexToIndex.put(vertex, index++);
        }
        for (Edge edge : getEdges()) {
            int source = vertexToIndex.get(edge.getSource());
            int destination = vertexToIndex.get(edge.getDestination());
            matrix[source][destination] = edge.getWeight();
        }
        return matrix;
    }

}
