package app.util.algorithm;

import app.util.algorithm.impl.BubbleSortAlgorithm;

import java.util.Objects;

public abstract class SortAlgorithm {

    public abstract void sort(SortingCollection collection);

    public abstract String getName();

    public abstract void setName(String name);

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SortAlgorithm that = (SortAlgorithm) o;

        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

}
