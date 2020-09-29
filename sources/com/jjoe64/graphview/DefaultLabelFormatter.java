package com.jjoe64.graphview;

import java.text.NumberFormat;

public class DefaultLabelFormatter implements LabelFormatter {
    protected NumberFormat[] mNumberFormatter = new NumberFormat[2];
    protected Viewport mViewport;

    public DefaultLabelFormatter() {
    }

    public DefaultLabelFormatter(NumberFormat xFormat, NumberFormat yFormat) {
        this.mNumberFormatter[0] = yFormat;
        this.mNumberFormatter[1] = xFormat;
    }

    public void setViewport(Viewport viewport) {
        this.mViewport = viewport;
    }

    public String formatLabel(double value, boolean isValueX) {
        int i = isValueX;
        if (this.mNumberFormatter[i] == null) {
            this.mNumberFormatter[i] = NumberFormat.getNumberInstance();
            double highestvalue = isValueX ? this.mViewport.getMaxX(false) : this.mViewport.getMaxY(false);
            double lowestvalue = isValueX ? this.mViewport.getMinX(false) : this.mViewport.getMinY(false);
            if (highestvalue - lowestvalue < 0.1d) {
                this.mNumberFormatter[i].setMaximumFractionDigits(6);
            } else if (highestvalue - lowestvalue < 1.0d) {
                this.mNumberFormatter[i].setMaximumFractionDigits(4);
            } else if (highestvalue - lowestvalue < 20.0d) {
                this.mNumberFormatter[i].setMaximumFractionDigits(3);
            } else if (highestvalue - lowestvalue < 100.0d) {
                this.mNumberFormatter[i].setMaximumFractionDigits(1);
            } else {
                this.mNumberFormatter[i].setMaximumFractionDigits(0);
            }
        }
        return this.mNumberFormatter[i].format(value);
    }
}
