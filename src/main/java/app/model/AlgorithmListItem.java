package app.model;

import app.util.algorithm.SortAlgorithm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlgorithmListItem {
    private final SortAlgorithm algorithm;
    private String name;

    public AlgorithmListItem(SortAlgorithm algorithm) {
        this.algorithm = algorithm;
        this.name = algorithm.getName();
    }

    @Override
    public String toString() {
        return getName();
    }

}
