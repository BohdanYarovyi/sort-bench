package app.util.algorithm.action;

import app.model.Bar;

import java.util.List;

public record PeekAction(
        Integer index
) implements Action {

    @Override
    public void perform(List<Bar> elements) {
        elements.get(index).setPeeked();
    }

}