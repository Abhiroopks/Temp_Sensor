package com.jjoe64.graphview.series;

import java.io.Serializable;
import java.util.Date;

public class DataPoint implements DataPointInterface, Serializable {
    private static final long serialVersionUID = 1428263322645L;

    /* renamed from: x */
    private double f30x;

    /* renamed from: y */
    private double f31y;

    public DataPoint(double x, double y) {
        this.f30x = x;
        this.f31y = y;
    }

    public DataPoint(Date x, double y) {
        this.f30x = (double) x.getTime();
        this.f31y = y;
    }

    public double getX() {
        return this.f30x;
    }

    public double getY() {
        return this.f31y;
    }

    public String toString() {
        return "[" + this.f30x + "/" + this.f31y + "]";
    }
}
