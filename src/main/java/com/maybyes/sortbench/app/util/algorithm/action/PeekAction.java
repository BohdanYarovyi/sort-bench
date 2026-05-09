package com.maybyes.sortbench.app.util.algorithm.action;

import com.maybyes.sortbench.app.model.Bar;

import java.util.List;

public record PeekAction(
        Integer index
) implements Action {

    @Override
    public void perform(List<Bar> elements) {
        elements.get(index).setPeeked();
    }

}