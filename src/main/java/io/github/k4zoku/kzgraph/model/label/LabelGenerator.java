package io.github.k4zoku.kzgraph.model.label;

public interface LabelGenerator {

    String getLabel(int i);

    int getMaxIndex();

    String[] getLabels();

    int getLabelIndex(String label);

}
