package app.util.algorithm;

import app.util.algorithm.action.*;
import com.maybyes.sortbench.lib.SortingCollection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SortingCollectionImpl implements SortingCollection {
    private final List<Integer> elements;

    private final Queue<Action> actions;

    public SortingCollectionImpl(List<Integer> initialElements) {
        this.elements = new ArrayList<>(initialElements);
        this.actions = new LinkedList<>();
    }

    @Override
    public int compare(int index1, int index2) {
        Integer element1 = elements.get(index1);
        Integer element2 = elements.get(index2);
        actions.add(new CompareAction(index1, index2));

        return element1 - element2;
    }

    @Override
    public Integer peek(int index) {
        actions.add(new PeekAction(index));

        return elements.get(index);
    }

    @Override
    public void swap(int index1, int index2) {
        Integer temp = elements.get(index1);
        elements.set(index1, elements.get(index2));
        elements.set(index2, temp);

        actions.add(new SwapAction(index1, index2));
    }

    @Override
    public void set(int index, int value) {
        elements.set(index, value);

        actions.add(new SetAction(index, value));
    }

    @Override
    public int getSize() {
        return elements.size();
    }

    public Queue<Action> getActions() {
        return new LinkedList<>(actions);
    }

}


