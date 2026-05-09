package com.maybyes.sortbench.app.model;

import lombok.Getter;
import lombok.Setter;
import com.maybyes.sortbench.abstraction.SortAlgorithm;

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
