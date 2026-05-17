package com.maybyes.sortbench.app.util;

import com.maybyes.sortbench.app.model.Bar;

import java.util.List;

public class BarUtil {

    public static void setIdleBars(List<Bar> barList) {
        barList.forEach(Bar::setIdle);
    }

}
