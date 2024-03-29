package com.jjoe64.graphview.compat;

import android.annotation.TargetApi;
import android.os.Build;
import android.widget.OverScroller;

public class OverScrollerCompat {
    private OverScrollerCompat() {
    }

    @TargetApi(14)
    public static float getCurrVelocity(OverScroller overScroller) {
        if (Build.VERSION.SDK_INT >= 14) {
            return overScroller.getCurrVelocity();
        }
        return 0.0f;
    }
}
