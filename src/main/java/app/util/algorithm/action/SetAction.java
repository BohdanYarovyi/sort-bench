package app.util.algorithm.action;

import app.model.Bar;

import java.util.List;

public record SetAction(
        Integer index,
        Integer value
) implements Action {

    @Override
    public void perform(List<Bar> elements) {
        elements.set(index, new Bar(value));
        elements.get(index).setSet();
    }

}