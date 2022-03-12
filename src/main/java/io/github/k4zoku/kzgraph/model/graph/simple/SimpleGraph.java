package io.github.k4zoku.kzgraph.model.graph.simple;

import io.github.k4zoku.kzgraph.annotations.Unused;
import io.github.k4zoku.kzgraph.model.graph.Edge;
import io.github.k4zoku.kzgraph.model.graph.Graph;
import io.github.k4zoku.kzgraph.model.graph.GraphSerializationMode;
import io.github.k4zoku.kzgraph.model.graph.Vertex;
import io.github.k4zoku.kzgraph.model.serialization.HumanReadableSerializer;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class SimpleGraph implements Graph, HumanReadableSerializer {

    protected final Map<Vertex, Map<Vertex, Set<Edge>>> adjacencyMatrix;

    protected GraphSerializationMode serializeMode = GraphSerializationMode.ADJACENCY_MATRIX;

    protected DecimalFormat weightFormat = new DecimalFormat("#");

    protected SimpleGraph(Map<Vertex, Map<Vertex, Set<Edge>>> adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    protected SimpleGraph() {
        this(new LinkedHashMap<>());
    }

    protected SimpleGraph(Reader reader) throws ParseException, IOException {
        this();
        this.deserialize(reader);
    }

    @Override
    public void addVertex(Vertex vertex) {
        this.adjacencyMatrix.putIfAbsent(vertex, new LinkedHashMap<>());
    }

    @Override
    public Set<Vertex> getVertices() {
        return Collections.unmodifiableSet(this.adjacencyMatrix.keySet());
    }

    @Override
    public Set<Edge> getEdges() {
        return Collections.unmodifiableSet(this.adjacencyMatrix.values().stream()
                .flatMap(map -> map.values().stream())
                .flatMap(Set::stream)
                .collect(Collectors.toSet()));
    }

    @Override
    public Set<Edge> getEdges(Vertex source, Vertex destination) {
        return this.adjacencyMatrix.get(source).get(destination);
    }

    @Override
    public Set<Vertex> getNeighbors(Vertex vertex) {
        return Collections.unmodifiableSet(this.adjacencyMatrix.get(vertex).keySet());
    }

    @Override
    public Set<Edge> getEdges(Vertex vertex) {
        return Collections.unmodifiableSet(this.adjacencyMatrix.get(vertex).values().stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet()));
    }

    @Override
    public void clear() {
        this.adjacencyMatrix.clear();
    }

    @Override
    public void serialize(Writer writer) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getVertices().size());
        if (this.serializeMode == GraphSerializationMode.ADJACENCY_LIST) {
            builder.append(" ").append(this.getEdges().size()).append("\n");
            writer.write(builder.toString());
            this.writeList(writer);
        } else {
            builder.append("\n");
            writer.write(builder.toString());
            if (this.serializeMode == GraphSerializationMode.LABELED_ADJACENCY_MATRIX) {
                this.writeLabels(writer);
            }
            this.writeMatrix(writer);
        }
    }

    protected void writeLabels(Writer writer) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (Vertex vertex : this.getVertices()) {
            builder.append(vertex.getLabel()).append(" ");
        }
        writer.write(builder.toString().trim() + "\n");
    }

    protected void writeMatrix(Writer writer) throws IOException {
        StringBuilder sb = new StringBuilder();
        Set<Vertex> vertices = this.getVertices();
        for (Vertex source : vertices) {
            for (Vertex destination : vertices) {
                Set<Edge> edges = this.getEdges(source, destination);
                sb.append(weightFormat.format(
                                edges.stream()
                                        .min(Comparator.comparing(Edge::getWeight))
                                        .map(Edge::getWeight).orElse(0.0)
                        )
                ).append(" ");
            }

        }
        writer.write(sb.toString());
    }

    protected void writeList(Writer writer) throws IOException {
        StringBuilder sb = new StringBuilder();
        this.adjacencyMatrix.forEach((source, map) ->
                map.forEach((destination, edges) ->
                        edges.forEach(edge ->
                                sb.append(source.getLabel()).append(" ")
                                        .append(destination.getLabel()).append(" ")
                                        .append(weightFormat.format(edge.getWeight()))
                                        .append("\n")
                        )
                )
        );
        writer.write(sb.toString());
    }

    @Override
    public void deserialize(Reader reader) throws ParseException, IOException {
        Scanner scanner = new Scanner(reader);
        if (scanner.hasNextInt()) {
            int numberOfVertices = scanner.nextInt();
            switch (serializeMode) {
                case ADJACENCY_MATRIX:
                    readMatrix(reader, numberOfVertices, false);
                    break;
                case ADJACENCY_LIST:
                    if (scanner.hasNextInt()) {
                        int numberOfEdges = scanner.nextInt();
                        readList(reader, numberOfVertices, numberOfEdges);
                    } else {
                        throw new ParseException("Expected number of edges", 0);
                    }
                    break;
                case LABELED_ADJACENCY_MATRIX:
                    readMatrix(reader, numberOfVertices, true);
                    break;
            }
        } else {
            throw new ParseException("Expected number of vertices", 0);
        }
    }

    protected void readList(Reader reader, @Unused int numberOfVertices, int numberOfEdges) throws ParseException, IOException {
        Scanner scanner = new Scanner(reader);
        for (int i = 0; i < numberOfEdges; i++) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split("\\s+");
                if (tokens.length == 3) {
                    Vertex source = new SimpleVertex(tokens[0]);
                    Vertex destination = new SimpleVertex(tokens[1]);
                    double weight;
                    try {
                        weight = Double.parseDouble(tokens[2]);
                    } catch (NumberFormatException e) {
                        throw new ParseException("Expected weight", i);
                    }
                    this.addEdge(source, destination, weight);
                } else {
                    throw new ParseException("Expected 3 tokens", i);
                }
            } else {
                throw new ParseException("Expected " + numberOfEdges + " edges", i);
            }
        }
    }

    protected void readMatrix(Reader reader, int numberOfVertices, boolean labeled) throws ParseException {
        Scanner scanner = new Scanner(reader);
        for (int i = 0; i < numberOfVertices; i++) {
            String vertexName;
            if (labeled) {
                if (scanner.hasNext()) {
                    vertexName = scanner.next();
                } else {
                    throw new ParseException("Expected vertex name", i);
                }
            } else {
                vertexName = String.valueOf(i);
            }
            Vertex vertex = new SimpleVertex(vertexName);
            this.addVertex(vertex);
        }
        List<Vertex> vertices = new ArrayList<>(getVertices());
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                if (scanner.hasNextDouble()) {
                    double weight = scanner.nextDouble();
                    if (weight != 0) {
                        Vertex source = vertices.get(i);
                        Vertex destination = vertices.get(j);
                        this.addEdge(source, destination, weight);
                    }
                } else {
                    throw new ParseException("Expected weight", i * numberOfVertices + j);
                }
            }
        }
    }
}
