package com.maybyes.sortbench.app.model;

import lombok.Getter;
import lombok.Setter;
import com.maybyes.sortbench.abstraction.SortAlgorithm;

import java.util.Objects;

@Getter
@Setter
public class AlgorithmListItem implements Comparable<AlgorithmListItem> {
    public final static String BUILTIN_ALGORITHM_MARKER = "(Default)";

    private final SortAlgorithm algorithm;

    private String name;

    public AlgorithmListItem(SortAlgorithm algorithm) {
        this.algorithm = algorithm;
        this.name = algorithm.getName();
    }

    public void markAsBuiltin() {
        name = BUILTIN_ALGORITHM_MARKER + " " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AlgorithmListItem that = (AlgorithmListItem) o;

        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(AlgorithmListItem other) {
        if (name.startsWith(BUILTIN_ALGORITHM_MARKER)) return 1;

        return name.compareTo(other.name);
    }

}
