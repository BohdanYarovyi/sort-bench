package com.maybyes.sortbench.app.util.algorithm.action;

import com.maybyes.sortbench.app.model.Bar;

import java.util.List;

public record SwapAction(
        int index1,
        int index2
) implements Action {

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