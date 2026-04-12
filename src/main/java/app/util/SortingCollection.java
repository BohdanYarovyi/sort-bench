package app.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SortingCollection {
    private final List<Integer> initialElements;
    private final List<Integer> elements;
    private final Queue<Action> actions;

    public SortingCollection(List<Integer> initialElements) {
        this.initialElements = new ArrayList<>(initialElements);
        this.elements = new ArrayList<>(initialElements);
        this.actions = new LinkedList<>();
    }

    public Integer peek(int index) {
        return elements.get(index);
    }

    public void swap(int index1, int index2) {
        Integer temp = elements.get(index1);
        elements.set(index1, elements.get(index2));
        elements.set(index2, temp);

        actions.add(new SwapAction(index1, index2));
    }

    public int getSize() {
        return elements.size();
    }

    public List<Integer> getInitialElements() {
        return new ArrayList<>(initialElements);
    }

    public List<Integer> getElements() {
        return new ArrayList<>(elements);
    }

    public Queue<Action> getActions() {
        return new LinkedList<>(actions);
    }

    public interface Action {

        <T> void perform(List<T> elements);

    }

    public static class SwapAction implements Action {
        private final int index1;
        private final int index2;

        public SwapAction(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }

        @Override
        public <T> void perform(List<T> elements) {
            T temp = elements.get(index1);
            elements.set(index1, elements.get(index2));
            elements.set(index2, temp);
        }
    }
}


