package io.github.k4zoku.kzgraph.model.graph.undirected;

import io.github.k4zoku.kzgraph.model.graph.Edge;
import io.github.k4zoku.kzgraph.model.graph.Vertex;
import io.github.k4zoku.kzgraph.model.graph.simple.SimpleGraph;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class UndirectedGraph extends SimpleGraph {

    public UndirectedGraph(Map<Vertex, Map<Vertex, Set<Edge>>> adjacencyMatrix) {
        super(adjacencyMatrix);
    }

    public UndirectedGraph() {
        super(new LinkedHashMap<>());
    }

    @Override
    public void addVertex(Vertex vertex) {
        this.adjacencyMatrix.putIfAbsent(vertex, new LinkedHashMap<>());
    }

    @Override
    public void addEdge(Edge edge) {
        this.adjacencyMatrix
                .computeIfAbsent(edge.getSource(), v -> new LinkedHashMap<>())
                .computeIfAbsent(edge.getDestination(), v -> new LinkedHashSet<>())
                .add(edge);
        this.adjacencyMatrix
                .computeIfAbsent(edge.getDestination(), v -> new LinkedHashMap<>())
                .computeIfAbsent(edge.getSource(), v -> new LinkedHashSet<>())
                .add(edge);
    }

    @Override
    public void addEdge(Vertex source, Vertex destination) {
        Edge edge = new UndirectedEdge(source, destination);
        this.addEdge(edge);
    }

    @Override
    public void addEdge(Vertex source, Vertex destination, double weight) {
        Edge edge = new UndirectedEdge(source, destination, weight);
        this.addEdge(edge);
    }

    @Override
    public void removeVertex(Vertex vertex) {
        this.adjacencyMatrix.remove(vertex);
        this.adjacencyMatrix.forEach((v, m) -> m.remove(vertex));
    }

    @Override
    public void removeEdge(Edge edge) {
        this.adjacencyMatrix
                .computeIfAbsent(edge.getSource(), v -> new LinkedHashMap<>())
                .computeIfAbsent(edge.getDestination(), v -> new LinkedHashSet<>())
                .remove(edge);
        this.adjacencyMatrix
                .computeIfAbsent(edge.getDestination(), v -> new LinkedHashMap<>())
                .computeIfAbsent(edge.getSource(), v -> new LinkedHashSet<>())
                .remove(edge);
    }

}
