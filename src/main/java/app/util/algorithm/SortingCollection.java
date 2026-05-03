package app.util.algorithm;

import app.model.Bar;

import java.util.*;

public class SortingCollection {
    private final List<Integer> elements;

    private final Queue<Action> actions;

    public SortingCollection(List<Integer> initialElements) {
        this.elements = new ArrayList<>(initialElements);
        this.actions = new LinkedList<>();
    }

    public int compare(int index1, int index2) {
        Integer element1 = elements.get(index1);
        Integer element2 = elements.get(index2);
        actions.add(new CompareAction(index1, index2));

        return element1 - element2;
    }

    public void swap(int index1, int index2) {
        Integer temp = elements.get(index1);
        elements.set(index1, elements.get(index2));
        elements.set(index2, temp);

        actions.add(new SwapAction(index1, index2));
    }

    public void set(int index, int value) {
        elements.set(index, value);

        actions.add(new SetAction(index, value));
    }

    public Integer peek(int index) {
        actions.add(new PeekAction(index));

        return elements.get(index);
    }

    public int getSize() {
        return elements.size();
    }

    public Queue<Action> getActions() {
        return new LinkedList<>(actions);
    }

    public interface Action {

        void perform(List<Bar> elements);

    }

    private static class SwapAction implements Action {
        private final int index1;

        private final int index2;

        public SwapAction(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }

        @Override
        public void perform(List<Bar> elements) {
            Bar bar1 = elements.get(index1);
            Bar bar2 = elements.get(index2);

            bar1.setSwapped();
            bar2.setSwapped();

            elements.set(index1, bar2);
            elements.set(index2, bar1);
        }
    }

    private static class CompareAction implements Action {
        private final int index1;

        private final int index2;

        public CompareAction(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }

        @Override
        public void perform(List<Bar> elements) {
            Bar bar1 = elements.get(index1);
            Bar bar2 = elements.get(index2);

            bar1.setCompared();
            bar2.setCompared();
        }

    }

    private static class PeekAction implements Action {
        private final Integer index;

        private PeekAction(Integer index) {
            this.index = index;
        }

        @Override
        public void perform(List<Bar> elements) {
            elements.get(index).setPeeked();
        }

    }

    private static class SetAction implements Action {
        private final Integer index;
        private final Integer value;

        private SetAction(Integer index, Integer value) {
            this.index = index;
            this.value = value;
        }


        @Override
        public void perform(List<Bar> elements) {
            elements.set(index, new Bar(value));
            elements.get(index).setSet();
        }

    }

}


