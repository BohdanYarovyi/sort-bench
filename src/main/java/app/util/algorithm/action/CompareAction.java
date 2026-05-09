package app.util.algorithm.action;

import app.model.Bar;

import java.util.List;

public record CompareAction(
        int index1,
        int index2
) implements Action {

    @Override
    public void perform(List<Bar> elements) {
        Bar bar1 = elements.get(index1);
        Bar bar2 = elements.get(index2);

        bar1.setCompared();
        bar2.setCompared();
    }

}