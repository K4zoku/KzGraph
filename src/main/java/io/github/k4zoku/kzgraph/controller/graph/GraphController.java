package io.github.k4zoku.kzgraph.controller.graph;

import io.github.k4zoku.kzgraph.model.graph.Graph;
import io.github.k4zoku.kzgraph.model.graph.Vertex;
import io.github.k4zoku.kzgraph.view.graph.DisplayType;

import java.util.LinkedHashMap;
import java.util.Map;

public class GraphController {
    private final Map<Vertex, DisplayType> vertexDisplayTypeMap = new LinkedHashMap<>();
    private final Map<Vertex, Boolean> vertexVisibilityMap = new LinkedHashMap<>();
    private Graph graph;


    public GraphController(Graph graph) {
        this.graph = graph;
    }

}
