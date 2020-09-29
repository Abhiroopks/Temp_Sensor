package com.jjoe64.graphview;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.p000v4.view.ViewCompat;
import android.util.TypedValue;
import com.jjoe64.graphview.Viewport;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class GridLabelRenderer {
    private final GraphView mGraphView;
    private String mHorizontalAxisTitle;
    private boolean mHumanRoundingX;
    private boolean mHumanRoundingY;
    protected boolean mIsAdjusted;
    private LabelFormatter mLabelFormatter;
    private Integer mLabelHorizontalHeight;
    private boolean mLabelHorizontalHeightFixed;
    private Integer mLabelHorizontalWidth;
    private Integer mLabelVerticalHeight;
    private Integer mLabelVerticalSecondScaleHeight;
    private Integer mLabelVerticalSecondScaleWidth;
    private Integer mLabelVerticalWidth;
    private boolean mLabelVerticalWidthFixed;
    private int mNumHorizontalLabels;
    private int mNumVerticalLabels;
    private Paint mPaintAxisTitle;
    private Paint mPaintLabel;
    private Paint mPaintLine;
    private Map<Integer, Double> mStepsHorizontal;
    private Map<Integer, Double> mStepsVertical;
    private Map<Integer, Double> mStepsVerticalSecondScale;
    protected Styles mStyles = new Styles();
    private String mVerticalAxisTitle;

    public enum VerticalLabelsVAlign {
        ABOVE,
        MID,
        BELOW
    }

    public final class Styles {
        public int gridColor;
        GridStyle gridStyle;
        public boolean highlightZeroLines;
        public int horizontalAxisTitleColor;
        public float horizontalAxisTitleTextSize;
        public float horizontalLabelsAngle;
        public int horizontalLabelsColor;
        boolean horizontalLabelsVisible;
        int labelsSpace;
        public int padding;
        public float textSize;
        public int verticalAxisTitleColor;
        public float verticalAxisTitleTextSize;
        public Paint.Align verticalLabelsAlign;
        public int verticalLabelsColor;
        public Paint.Align verticalLabelsSecondScaleAlign;
        public int verticalLabelsSecondScaleColor;
        VerticalLabelsVAlign verticalLabelsVAlign = VerticalLabelsVAlign.MID;
        boolean verticalLabelsVisible;

        public Styles() {
        }
    }

    public enum GridStyle {
        BOTH,
        VERTICAL,
        HORIZONTAL,
        NONE;

        public boolean drawVertical() {
            return this == BOTH || (this == VERTICAL && this != NONE);
        }

        public boolean drawHorizontal() {
            return this == BOTH || (this == HORIZONTAL && this != NONE);
        }
    }

    public void setSecondScaleLabelVerticalWidth(Integer newWidth) {
        this.mLabelVerticalSecondScaleWidth = newWidth;
    }

    public GridLabelRenderer(GraphView graphView) {
        this.mGraphView = graphView;
        setLabelFormatter(new DefaultLabelFormatter());
        resetStyles();
        this.mNumVerticalLabels = 5;
        this.mNumHorizontalLabels = 5;
        this.mHumanRoundingX = true;
        this.mHumanRoundingY = true;
    }

    public void resetStyles() {
        int color2;
        int color1;
        int color12;
        TypedValue typedValue = new TypedValue();
        this.mGraphView.getContext().getTheme().resolveAttribute(16842818, typedValue, true);
        int size2 = 20;
        try {
            TypedArray array = this.mGraphView.getContext().obtainStyledAttributes(typedValue.data, new int[]{16842806, 16842808, 16842901, 16843327});
            color12 = array.getColor(0, ViewCompat.MEASURED_STATE_MASK);
            color1 = array.getColor(1, -7829368);
            color2 = array.getDimensionPixelSize(2, 20);
            int size22 = array.getDimensionPixelSize(3, 20);
            array.recycle();
            size2 = size22;
        } catch (Exception e) {
            color12 = -16777216;
            color1 = -7829368;
            color2 = 20;
        }
        this.mStyles.verticalLabelsColor = color12;
        this.mStyles.verticalLabelsSecondScaleColor = color12;
        this.mStyles.horizontalLabelsColor = color12;
        this.mStyles.gridColor = color1;
        this.mStyles.textSize = (float) color2;
        this.mStyles.padding = size2;
        this.mStyles.labelsSpace = ((int) this.mStyles.textSize) / 5;
        this.mStyles.verticalLabelsAlign = Paint.Align.RIGHT;
        this.mStyles.verticalLabelsSecondScaleAlign = Paint.Align.LEFT;
        this.mStyles.highlightZeroLines = true;
        this.mStyles.verticalAxisTitleColor = this.mStyles.verticalLabelsColor;
        this.mStyles.horizontalAxisTitleColor = this.mStyles.horizontalLabelsColor;
        this.mStyles.verticalAxisTitleTextSize = this.mStyles.textSize;
        this.mStyles.horizontalAxisTitleTextSize = this.mStyles.textSize;
        this.mStyles.horizontalLabelsVisible = true;
        this.mStyles.verticalLabelsVisible = true;
        this.mStyles.horizontalLabelsAngle = 0.0f;
        this.mStyles.gridStyle = GridStyle.BOTH;
        reloadStyles();
    }

    public void reloadStyles() {
        this.mPaintLine = new Paint();
        this.mPaintLine.setColor(this.mStyles.gridColor);
        this.mPaintLine.setStrokeWidth(0.0f);
        this.mPaintLabel = new Paint();
        this.mPaintLabel.setTextSize(getTextSize());
        this.mPaintLabel.setAntiAlias(true);
        this.mPaintAxisTitle = new Paint();
        this.mPaintAxisTitle.setTextSize(getTextSize());
        this.mPaintAxisTitle.setTextAlign(Paint.Align.CENTER);
    }

    public boolean isHumanRoundingX() {
        return this.mHumanRoundingX;
    }

    public boolean isHumanRoundingY() {
        return this.mHumanRoundingY;
    }

    public void setHumanRounding(boolean humanRoundingX, boolean humanRoundingY) {
        this.mHumanRoundingX = humanRoundingX;
        this.mHumanRoundingY = humanRoundingY;
    }

    public void setHumanRounding(boolean humanRoundingBoth) {
        this.mHumanRoundingX = humanRoundingBoth;
        this.mHumanRoundingY = humanRoundingBoth;
    }

    public float getTextSize() {
        return this.mStyles.textSize;
    }

    public int getVerticalLabelsColor() {
        return this.mStyles.verticalLabelsColor;
    }

    public Paint.Align getVerticalLabelsAlign() {
        return this.mStyles.verticalLabelsAlign;
    }

    public int getHorizontalLabelsColor() {
        return this.mStyles.horizontalLabelsColor;
    }

    public float getHorizontalLabelsAngle() {
        return this.mStyles.horizontalLabelsAngle;
    }

    public void invalidate(boolean keepLabelsSize, boolean keepViewport) {
        if (!keepViewport) {
            this.mIsAdjusted = false;
        }
        if (!keepLabelsSize) {
            if (!this.mLabelVerticalWidthFixed) {
                this.mLabelVerticalWidth = null;
            }
            this.mLabelVerticalHeight = null;
            this.mLabelVerticalSecondScaleWidth = null;
            this.mLabelVerticalSecondScaleHeight = null;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x016d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean adjustVerticalSecondScale() {
        /*
            r31 = this;
            r0 = r31
            java.lang.Integer r1 = r0.mLabelHorizontalHeight
            r2 = 0
            if (r1 != 0) goto L_0x0008
            return r2
        L_0x0008:
            com.jjoe64.graphview.GraphView r1 = r0.mGraphView
            com.jjoe64.graphview.SecondScale r1 = r1.mSecondScale
            r3 = 1
            if (r1 != 0) goto L_0x0010
            return r3
        L_0x0010:
            com.jjoe64.graphview.GraphView r1 = r0.mGraphView
            com.jjoe64.graphview.SecondScale r1 = r1.mSecondScale
            double r4 = r1.getMinY(r2)
            com.jjoe64.graphview.GraphView r1 = r0.mGraphView
            com.jjoe64.graphview.SecondScale r1 = r1.mSecondScale
            double r6 = r1.getMaxY(r2)
            int r1 = r0.mNumVerticalLabels
            com.jjoe64.graphview.GraphView r8 = r0.mGraphView
            com.jjoe64.graphview.SecondScale r8 = r8.mSecondScale
            boolean r8 = r8.isYAxisBoundsManual()
            if (r8 == 0) goto L_0x0177
            double r8 = r6 - r4
            int r10 = r1 + -1
            double r10 = (double) r10
            double r8 = r8 / r10
            r10 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r12 = r8 * r10
            long r12 = java.lang.Math.round(r12)
            double r12 = (double) r12
            double r12 = r12 / r10
            java.util.Map<java.lang.Integer, java.lang.Double> r8 = r0.mStepsVerticalSecondScale
            if (r8 == 0) goto L_0x00b8
            java.util.Map<java.lang.Integer, java.lang.Double> r8 = r0.mStepsVerticalSecondScale
            int r8 = r8.size()
            if (r8 <= r3) goto L_0x00b8
            r8 = 0
            r10 = 0
            r14 = 0
            java.util.Map<java.lang.Integer, java.lang.Double> r15 = r0.mStepsVerticalSecondScale
            java.util.Collection r15 = r15.values()
            java.util.Iterator r15 = r15.iterator()
        L_0x005b:
            boolean r16 = r15.hasNext()
            if (r16 == 0) goto L_0x0079
            java.lang.Object r16 = r15.next()
            r2 = r16
            java.lang.Double r2 = (java.lang.Double) r2
            if (r14 != 0) goto L_0x0074
            double r8 = r2.doubleValue()
            int r14 = r14 + 1
            r2 = 0
            goto L_0x005b
        L_0x0074:
            double r10 = r2.doubleValue()
        L_0x0079:
            double r15 = r10 - r8
            r18 = 0
            int r2 = (r15 > r18 ? 1 : (r15 == r18 ? 0 : -1))
            if (r2 <= 0) goto L_0x00b8
            r18 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            int r2 = (r15 > r12 ? 1 : (r15 == r12 ? 0 : -1))
            r20 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r2 <= 0) goto L_0x008c
            double r18 = r15 / r20
            goto L_0x0092
        L_0x008c:
            int r2 = (r15 > r12 ? 1 : (r15 == r12 ? 0 : -1))
            if (r2 >= 0) goto L_0x0092
            double r18 = r15 * r20
        L_0x0092:
            double r20 = r6 - r4
            r22 = r4
            double r3 = r20 / r15
            int r2 = (int) r3
            double r3 = r6 - r22
            double r3 = r3 / r18
            int r3 = (int) r3
            if (r2 > r1) goto L_0x00a8
            if (r3 > r1) goto L_0x00a8
            if (r3 <= r2) goto L_0x00a6
            r4 = 1
            goto L_0x00a7
        L_0x00a6:
            r4 = 0
        L_0x00a7:
            goto L_0x00a9
        L_0x00a8:
            r4 = 1
        L_0x00a9:
            r20 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            int r5 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r5 == 0) goto L_0x00b6
            if (r4 == 0) goto L_0x00b6
            if (r3 > r1) goto L_0x00b6
            r12 = r18
            goto L_0x00ba
        L_0x00b6:
            r12 = r15
            goto L_0x00ba
        L_0x00b8:
            r22 = r4
        L_0x00ba:
            com.jjoe64.graphview.GraphView r2 = r0.mGraphView
            com.jjoe64.graphview.SecondScale r2 = r2.getSecondScale()
            double r2 = r2.mReferenceY
            double r4 = r22 - r2
            double r4 = r4 / r12
            double r4 = java.lang.Math.floor(r4)
            double r8 = r4 * r12
            double r8 = r8 + r2
            com.jjoe64.graphview.GraphView r2 = r0.mGraphView
            com.jjoe64.graphview.SecondScale r2 = r2.getSecondScale()
            com.jjoe64.graphview.RectD r2 = r2.mCurrentViewport
            double r2 = r2.height()
            r10 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            double r2 = r2 * r10
            double r2 = r2 / r12
            int r2 = (int) r2
            r3 = 2
            int r2 = r2 + r3
            int r1 = java.lang.Math.max(r2, r3)
            java.util.Map<java.lang.Integer, java.lang.Double> r2 = r0.mStepsVerticalSecondScale
            if (r2 == 0) goto L_0x00ed
            java.util.Map<java.lang.Integer, java.lang.Double> r2 = r0.mStepsVerticalSecondScale
            r2.clear()
            goto L_0x00f4
        L_0x00ed:
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>(r1)
            r0.mStepsVerticalSecondScale = r2
        L_0x00f4:
            com.jjoe64.graphview.GraphView r2 = r0.mGraphView
            int r2 = r2.getGraphContentHeight()
            double r14 = (double) r2
            com.jjoe64.graphview.GraphView r3 = r0.mGraphView
            com.jjoe64.graphview.SecondScale r3 = r3.getSecondScale()
            com.jjoe64.graphview.RectD r3 = r3.mCurrentViewport
            double r18 = r3.height()
            double r14 = r14 / r18
            double r14 = r14 * r10
            r17 = 0
        L_0x010c:
            r3 = r17
            if (r3 >= r1) goto L_0x016d
            double r10 = (double) r3
            double r10 = r10 * r12
            double r10 = r10 + r8
            r24 = r1
            com.jjoe64.graphview.GraphView r1 = r0.mGraphView
            com.jjoe64.graphview.SecondScale r1 = r1.getSecondScale()
            com.jjoe64.graphview.RectD r1 = r1.mCurrentViewport
            r25 = r2
            double r1 = r1.top
            int r1 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x012b
        L_0x0126:
            r26 = r4
            r28 = r6
            goto L_0x0160
        L_0x012b:
            double r1 = (double) r3
            double r1 = r1 * r12
            double r1 = r1 + r8
            com.jjoe64.graphview.GraphView r10 = r0.mGraphView
            com.jjoe64.graphview.SecondScale r10 = r10.getSecondScale()
            com.jjoe64.graphview.RectD r10 = r10.mCurrentViewport
            double r10 = r10.bottom
            int r1 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r1 >= 0) goto L_0x013d
            goto L_0x0126
        L_0x013d:
            double r1 = (double) r3
            double r1 = r1 * r12
            double r1 = r1 + r8
            com.jjoe64.graphview.GraphView r10 = r0.mGraphView
            com.jjoe64.graphview.SecondScale r10 = r10.getSecondScale()
            com.jjoe64.graphview.RectD r10 = r10.mCurrentViewport
            double r10 = r10.bottom
            double r10 = r1 - r10
            r26 = r4
            double r4 = r10 * r14
            r28 = r6
            java.util.Map<java.lang.Integer, java.lang.Double> r6 = r0.mStepsVerticalSecondScale
            int r7 = (int) r4
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Double r0 = java.lang.Double.valueOf(r1)
            r6.put(r7, r0)
        L_0x0160:
            int r17 = r3 + 1
            r1 = r24
            r2 = r25
            r4 = r26
            r6 = r28
            r0 = r31
            goto L_0x010c
        L_0x016d:
            r24 = r1
            r25 = r2
            r26 = r4
            r28 = r6
            r0 = 1
            return r0
        L_0x0177:
            r22 = r4
            r28 = r6
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "Not yet implemented"
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jjoe64.graphview.GridLabelRenderer.adjustVerticalSecondScale():boolean");
    }

    /* access modifiers changed from: protected */
    public boolean adjustVertical(boolean changeBounds) {
        double maxY;
        boolean shouldChange;
        GridLabelRenderer gridLabelRenderer = this;
        boolean z = changeBounds;
        if (gridLabelRenderer.mLabelHorizontalHeight == null) {
            return false;
        }
        double minY = gridLabelRenderer.mGraphView.getViewport().getMinY(false);
        double maxY2 = gridLabelRenderer.mGraphView.getViewport().getMaxY(false);
        if (minY == maxY2) {
            return false;
        }
        int numVerticalLabels = gridLabelRenderer.mNumVerticalLabels;
        double exactSteps = ((double) Math.round(((maxY2 - minY) / ((double) (numVerticalLabels - 1))) * 1000000.0d)) / 1000000.0d;
        if (exactSteps == 0.0d) {
            exactSteps = 1.0E-7d;
            maxY2 = minY + (((double) (numVerticalLabels - 1)) * 1.0E-7d);
        }
        if (isHumanRoundingY()) {
            exactSteps = gridLabelRenderer.humanRound(exactSteps, z);
        } else if (gridLabelRenderer.mStepsVertical != null && gridLabelRenderer.mStepsVertical.size() > 1) {
            double d1 = 0.0d;
            double d2 = 0.0d;
            int i = 0;
            Iterator<Double> it = gridLabelRenderer.mStepsVertical.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Double v = it.next();
                if (i != 0) {
                    d2 = v.doubleValue();
                    break;
                }
                d1 = v.doubleValue();
                i++;
            }
            double oldSteps = d2 - d1;
            if (oldSteps > 0.0d) {
                double newSteps = Double.NaN;
                if (oldSteps > exactSteps) {
                    newSteps = oldSteps / 2.0d;
                } else if (oldSteps < exactSteps) {
                    newSteps = oldSteps * 2.0d;
                }
                int i2 = i;
                int numStepsOld = (int) ((maxY2 - minY) / oldSteps);
                int numStepsNew = (int) ((maxY2 - minY) / newSteps);
                if (numStepsOld > numVerticalLabels || numStepsNew > numVerticalLabels) {
                    shouldChange = true;
                } else {
                    shouldChange = numStepsNew > numStepsOld;
                }
                if (newSteps == Double.NaN || !shouldChange || numStepsNew > numVerticalLabels) {
                    exactSteps = oldSteps;
                } else {
                    exactSteps = newSteps;
                }
            }
        }
        double newMinY = gridLabelRenderer.mGraphView.getViewport().getReferenceY();
        double newMinY2 = (Math.floor((minY - newMinY) / exactSteps) * exactSteps) + newMinY;
        if (z) {
            gridLabelRenderer.mGraphView.getViewport().setMinY(newMinY2);
            gridLabelRenderer.mGraphView.getViewport().setMaxY(Math.max(maxY2, (((double) (numVerticalLabels - 1)) * exactSteps) + newMinY2));
            gridLabelRenderer.mGraphView.getViewport().mYAxisBoundsStatus = Viewport.AxisBoundsStatus.AUTO_ADJUSTED;
        }
        int numVerticalLabels2 = ((int) ((gridLabelRenderer.mGraphView.getViewport().mCurrentViewport.height() * -1.0d) / exactSteps)) + 2;
        if (gridLabelRenderer.mStepsVertical != null) {
            gridLabelRenderer.mStepsVertical.clear();
        } else {
            gridLabelRenderer.mStepsVertical = new LinkedHashMap(numVerticalLabels2);
        }
        int height = gridLabelRenderer.mGraphView.getGraphContentHeight();
        double pixelPerData = (((double) height) / gridLabelRenderer.mGraphView.getViewport().mCurrentViewport.height()) * -1.0d;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 < numVerticalLabels2) {
                int height2 = height;
                int numVerticalLabels3 = numVerticalLabels2;
                double minY2 = minY;
                if ((((double) i4) * exactSteps) + newMinY2 <= gridLabelRenderer.mGraphView.getViewport().mCurrentViewport.top && (((double) i4) * exactSteps) + newMinY2 >= gridLabelRenderer.mGraphView.getViewport().mCurrentViewport.bottom) {
                    double dataPointPos = (((double) i4) * exactSteps) + newMinY2;
                    double relativeToCurrentViewport = dataPointPos - gridLabelRenderer.mGraphView.getViewport().mCurrentViewport.bottom;
                    maxY = maxY2;
                    double d = relativeToCurrentViewport;
                    gridLabelRenderer.mStepsVertical.put(Integer.valueOf((int) (relativeToCurrentViewport * pixelPerData)), Double.valueOf(dataPointPos));
                } else {
                    maxY = maxY2;
                }
                i3 = i4 + 1;
                numVerticalLabels2 = numVerticalLabels3;
                height = height2;
                minY = minY2;
                maxY2 = maxY;
                gridLabelRenderer = this;
            } else {
                int i5 = numVerticalLabels2;
                double d3 = minY;
                double d4 = maxY2;
                return true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean adjustHorizontal(boolean changeBounds) {
        double maxX;
        boolean shouldChange;
        GridLabelRenderer gridLabelRenderer = this;
        if (gridLabelRenderer.mLabelVerticalWidth == null) {
            return false;
        }
        double minX = gridLabelRenderer.mGraphView.getViewport().getMinX(false);
        double maxX2 = gridLabelRenderer.mGraphView.getViewport().getMaxX(false);
        if (minX == maxX2) {
            return false;
        }
        int numHorizontalLabels = gridLabelRenderer.mNumHorizontalLabels;
        double exactSteps = ((double) Math.round(((maxX2 - minX) / ((double) (numHorizontalLabels - 1))) * 1000000.0d)) / 1000000.0d;
        if (exactSteps == 0.0d) {
            exactSteps = 1.0E-7d;
            maxX2 = minX + (((double) (numHorizontalLabels - 1)) * 1.0E-7d);
        }
        if (isHumanRoundingX()) {
            exactSteps = gridLabelRenderer.humanRound(exactSteps, false);
        } else if (gridLabelRenderer.mStepsHorizontal != null && gridLabelRenderer.mStepsHorizontal.size() > 1) {
            double d1 = 0.0d;
            double d2 = 0.0d;
            int i = 0;
            Iterator<Double> it = gridLabelRenderer.mStepsHorizontal.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Double v = it.next();
                if (i != 0) {
                    d2 = v.doubleValue();
                    break;
                }
                d1 = v.doubleValue();
                i++;
            }
            double oldSteps = d2 - d1;
            if (oldSteps > 0.0d) {
                double newSteps = Double.NaN;
                if (oldSteps > exactSteps) {
                    newSteps = oldSteps / 2.0d;
                } else if (oldSteps < exactSteps) {
                    newSteps = oldSteps * 2.0d;
                }
                int i2 = i;
                int numStepsOld = (int) ((maxX2 - minX) / oldSteps);
                int numStepsNew = (int) ((maxX2 - minX) / newSteps);
                if (numStepsOld > numHorizontalLabels || numStepsNew > numHorizontalLabels) {
                    shouldChange = true;
                } else {
                    shouldChange = numStepsNew > numStepsOld;
                }
                if (newSteps == Double.NaN || !shouldChange || numStepsNew > numHorizontalLabels) {
                    exactSteps = oldSteps;
                } else {
                    exactSteps = newSteps;
                }
            }
        }
        double newMinX = gridLabelRenderer.mGraphView.getViewport().getReferenceX();
        double newMinX2 = (Math.floor((minX - newMinX) / exactSteps) * exactSteps) + newMinX;
        if (changeBounds) {
            gridLabelRenderer.mGraphView.getViewport().setMinX(newMinX2);
            int i3 = numHorizontalLabels;
            gridLabelRenderer.mGraphView.getViewport().setMaxX((((double) (numHorizontalLabels - 1)) * exactSteps) + newMinX2);
            gridLabelRenderer.mGraphView.getViewport().mXAxisBoundsStatus = Viewport.AxisBoundsStatus.AUTO_ADJUSTED;
        }
        int numHorizontalLabels2 = ((int) (gridLabelRenderer.mGraphView.getViewport().mCurrentViewport.width() / exactSteps)) + 1;
        if (gridLabelRenderer.mStepsHorizontal != null) {
            gridLabelRenderer.mStepsHorizontal.clear();
        } else {
            gridLabelRenderer.mStepsHorizontal = new LinkedHashMap(numHorizontalLabels2);
        }
        int width = gridLabelRenderer.mGraphView.getGraphContentWidth();
        double pixelPerData = ((double) width) / gridLabelRenderer.mGraphView.getViewport().mCurrentViewport.width();
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 < numHorizontalLabels2) {
                int numHorizontalLabels3 = numHorizontalLabels2;
                int width2 = width;
                double minX2 = minX;
                if ((((double) i5) * exactSteps) + newMinX2 < gridLabelRenderer.mGraphView.getViewport().mCurrentViewport.left) {
                    maxX = maxX2;
                } else {
                    double dataPointPos = (((double) i5) * exactSteps) + newMinX2;
                    double relativeToCurrentViewport = dataPointPos - gridLabelRenderer.mGraphView.getViewport().mCurrentViewport.left;
                    maxX = maxX2;
                    double d = relativeToCurrentViewport;
                    gridLabelRenderer.mStepsHorizontal.put(Integer.valueOf((int) (relativeToCurrentViewport * pixelPerData)), Double.valueOf(dataPointPos));
                }
                i4 = i5 + 1;
                numHorizontalLabels2 = numHorizontalLabels3;
                width = width2;
                minX = minX2;
                maxX2 = maxX;
                gridLabelRenderer = this;
            } else {
                int i6 = width;
                double d3 = minX;
                double d4 = maxX2;
                return true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void adjustSteps() {
        this.mIsAdjusted = adjustVertical(!Viewport.AxisBoundsStatus.FIX.equals(this.mGraphView.getViewport().mYAxisBoundsStatus));
        this.mIsAdjusted &= adjustVerticalSecondScale();
        this.mIsAdjusted &= adjustHorizontal(!Viewport.AxisBoundsStatus.FIX.equals(this.mGraphView.getViewport().mXAxisBoundsStatus));
    }

    /* access modifiers changed from: protected */
    public void calcLabelVerticalSize(Canvas canvas) {
        String testLabel = this.mLabelFormatter.formatLabel(this.mGraphView.getViewport().getMaxY(false), false);
        if (testLabel == null) {
            testLabel = "";
        }
        Rect textBounds = new Rect();
        this.mPaintLabel.getTextBounds(testLabel, 0, testLabel.length(), textBounds);
        this.mLabelVerticalWidth = Integer.valueOf(textBounds.width());
        this.mLabelVerticalHeight = Integer.valueOf(textBounds.height());
        String testLabel2 = this.mLabelFormatter.formatLabel(this.mGraphView.getViewport().getMinY(false), false);
        if (testLabel2 == null) {
            testLabel2 = "";
        }
        this.mPaintLabel.getTextBounds(testLabel2, 0, testLabel2.length(), textBounds);
        this.mLabelVerticalWidth = Integer.valueOf(Math.max(this.mLabelVerticalWidth.intValue(), textBounds.width()));
        this.mLabelVerticalWidth = Integer.valueOf(this.mLabelVerticalWidth.intValue() + 6);
        this.mLabelVerticalWidth = Integer.valueOf(this.mLabelVerticalWidth.intValue() + this.mStyles.labelsSpace);
        int lines = 1;
        for (byte c : testLabel2.getBytes()) {
            if (c == 10) {
                lines++;
            }
        }
        this.mLabelVerticalHeight = Integer.valueOf(this.mLabelVerticalHeight.intValue() * lines);
    }

    /* access modifiers changed from: protected */
    public void calcLabelVerticalSecondScaleSize(Canvas canvas) {
        if (this.mGraphView.mSecondScale == null) {
            this.mLabelVerticalSecondScaleWidth = 0;
            this.mLabelVerticalSecondScaleHeight = 0;
            return;
        }
        String testLabel = this.mGraphView.mSecondScale.getLabelFormatter().formatLabel(((this.mGraphView.mSecondScale.getMaxY(false) - this.mGraphView.mSecondScale.getMinY(false)) * 0.783d) + this.mGraphView.mSecondScale.getMinY(false), false);
        Rect textBounds = new Rect();
        this.mPaintLabel.getTextBounds(testLabel, 0, testLabel.length(), textBounds);
        this.mLabelVerticalSecondScaleWidth = Integer.valueOf(textBounds.width());
        this.mLabelVerticalSecondScaleHeight = Integer.valueOf(textBounds.height());
        int lines = 1;
        for (byte c : testLabel.getBytes()) {
            if (c == 10) {
                lines++;
            }
        }
        this.mLabelVerticalSecondScaleHeight = Integer.valueOf(this.mLabelVerticalSecondScaleHeight.intValue() * lines);
    }

    /* access modifiers changed from: protected */
    public void calcLabelHorizontalSize(Canvas canvas) {
        String testLabel = this.mLabelFormatter.formatLabel(((this.mGraphView.getViewport().getMaxX(false) - this.mGraphView.getViewport().getMinX(false)) * 0.783d) + this.mGraphView.getViewport().getMinX(false), true);
        if (testLabel == null) {
            testLabel = "";
        }
        Rect textBounds = new Rect();
        this.mPaintLabel.getTextBounds(testLabel, 0, testLabel.length(), textBounds);
        this.mLabelHorizontalWidth = Integer.valueOf(textBounds.width());
        if (!this.mLabelHorizontalHeightFixed) {
            this.mLabelHorizontalHeight = Integer.valueOf(textBounds.height());
            int lines = 1;
            for (byte c : testLabel.getBytes()) {
                if (c == 10) {
                    lines++;
                }
            }
            this.mLabelHorizontalHeight = Integer.valueOf(this.mLabelHorizontalHeight.intValue() * lines);
            this.mLabelHorizontalHeight = Integer.valueOf((int) Math.max((float) this.mLabelHorizontalHeight.intValue(), this.mStyles.textSize));
        }
        if (this.mStyles.horizontalLabelsAngle > 0.0f && this.mStyles.horizontalLabelsAngle <= 180.0f) {
            this.mLabelHorizontalHeight = Integer.valueOf(((int) Math.round(Math.abs(((double) this.mLabelHorizontalHeight.intValue()) * Math.cos(Math.toRadians((double) this.mStyles.horizontalLabelsAngle))))) + ((int) Math.round(Math.abs(((double) this.mLabelHorizontalWidth.intValue()) * Math.sin(Math.toRadians((double) this.mStyles.horizontalLabelsAngle))))));
            this.mLabelHorizontalWidth = Integer.valueOf(((int) Math.round(Math.abs(((double) this.mLabelHorizontalHeight.intValue()) * Math.sin(Math.toRadians((double) this.mStyles.horizontalLabelsAngle))))) + ((int) Math.round(Math.abs(((double) this.mLabelHorizontalWidth.intValue()) * Math.cos(Math.toRadians((double) this.mStyles.horizontalLabelsAngle))))));
        }
        this.mLabelHorizontalHeight = Integer.valueOf(this.mLabelHorizontalHeight.intValue() + this.mStyles.labelsSpace);
    }

    public void draw(Canvas canvas) {
        boolean labelSizeChanged = false;
        if (this.mLabelHorizontalWidth == null) {
            calcLabelHorizontalSize(canvas);
            labelSizeChanged = true;
        }
        if (this.mLabelVerticalWidth == null) {
            calcLabelVerticalSize(canvas);
            labelSizeChanged = true;
        }
        if (this.mLabelVerticalSecondScaleWidth == null) {
            calcLabelVerticalSecondScaleSize(canvas);
            labelSizeChanged = true;
        }
        if (labelSizeChanged) {
            this.mGraphView.drawGraphElements(canvas);
            return;
        }
        if (!this.mIsAdjusted) {
            adjustSteps();
        }
        if (this.mIsAdjusted) {
            drawVerticalSteps(canvas);
            drawVerticalStepsSecondScale(canvas);
            drawHorizontalSteps(canvas);
            drawHorizontalAxisTitle(canvas);
            drawVerticalAxisTitle(canvas);
            if (this.mGraphView.mSecondScale != null) {
                this.mGraphView.mSecondScale.drawVerticalAxisTitle(canvas);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawHorizontalAxisTitle(Canvas canvas) {
        if (this.mHorizontalAxisTitle != null && this.mHorizontalAxisTitle.length() > 0) {
            this.mPaintAxisTitle.setColor(getHorizontalAxisTitleColor());
            this.mPaintAxisTitle.setTextSize(getHorizontalAxisTitleTextSize());
            canvas.drawText(this.mHorizontalAxisTitle, (float) (canvas.getWidth() / 2), (float) (canvas.getHeight() - this.mStyles.padding), this.mPaintAxisTitle);
        }
    }

    /* access modifiers changed from: protected */
    public void drawVerticalAxisTitle(Canvas canvas) {
        if (this.mVerticalAxisTitle != null && this.mVerticalAxisTitle.length() > 0) {
            this.mPaintAxisTitle.setColor(getVerticalAxisTitleColor());
            this.mPaintAxisTitle.setTextSize(getVerticalAxisTitleTextSize());
            float x = (float) getVerticalAxisTitleWidth();
            float y = (float) (canvas.getHeight() / 2);
            canvas.save();
            canvas.rotate(-90.0f, x, y);
            canvas.drawText(this.mVerticalAxisTitle, x, y, this.mPaintAxisTitle);
            canvas.restore();
        }
    }

    public int getHorizontalAxisTitleHeight() {
        if (this.mHorizontalAxisTitle == null || this.mHorizontalAxisTitle.length() <= 0) {
            return 0;
        }
        return (int) getHorizontalAxisTitleTextSize();
    }

    public int getVerticalAxisTitleWidth() {
        if (this.mVerticalAxisTitle == null || this.mVerticalAxisTitle.length() <= 0) {
            return 0;
        }
        return (int) getVerticalAxisTitleTextSize();
    }

    /* access modifiers changed from: protected */
    public void drawHorizontalSteps(Canvas canvas) {
        Canvas canvas2 = canvas;
        this.mPaintLabel.setColor(getHorizontalLabelsColor());
        int i = 0;
        for (Map.Entry<Integer, Double> e : this.mStepsHorizontal.entrySet()) {
            if (this.mStyles.highlightZeroLines) {
                if (e.getValue().doubleValue() == 0.0d) {
                    this.mPaintLine.setStrokeWidth(5.0f);
                } else {
                    this.mPaintLine.setStrokeWidth(0.0f);
                }
            }
            if (this.mStyles.gridStyle.drawVertical() && e.getKey().intValue() <= this.mGraphView.getGraphContentWidth()) {
                canvas2.drawLine((float) (this.mGraphView.getGraphContentLeft() + e.getKey().intValue()), (float) this.mGraphView.getGraphContentTop(), (float) (this.mGraphView.getGraphContentLeft() + e.getKey().intValue()), (float) (this.mGraphView.getGraphContentTop() + this.mGraphView.getGraphContentHeight()), this.mPaintLine);
            }
            if (isHorizontalLabelsVisible()) {
                float f = 90.0f;
                int i2 = 1;
                if (this.mStyles.horizontalLabelsAngle <= 0.0f || this.mStyles.horizontalLabelsAngle > 180.0f) {
                    this.mPaintLabel.setTextAlign(Paint.Align.CENTER);
                    if (i == this.mStepsHorizontal.size() - 1) {
                        this.mPaintLabel.setTextAlign(Paint.Align.RIGHT);
                    }
                    if (i == 0) {
                        this.mPaintLabel.setTextAlign(Paint.Align.LEFT);
                    }
                } else if (this.mStyles.horizontalLabelsAngle < 90.0f) {
                    this.mPaintLabel.setTextAlign(Paint.Align.RIGHT);
                } else if (this.mStyles.horizontalLabelsAngle <= 180.0f) {
                    this.mPaintLabel.setTextAlign(Paint.Align.LEFT);
                }
                String label = this.mLabelFormatter.formatLabel(e.getValue().doubleValue(), true);
                if (label == null) {
                    label = "";
                }
                String[] lines = label.split("\n");
                int labelWidthAdj = 0;
                int li = 0;
                if (this.mStyles.horizontalLabelsAngle > 0.0f && this.mStyles.horizontalLabelsAngle <= 180.0f) {
                    Rect textBounds = new Rect();
                    this.mPaintLabel.getTextBounds(lines[0], 0, lines[0].length(), textBounds);
                    Rect rect = textBounds;
                    labelWidthAdj = (int) Math.abs(((double) textBounds.width()) * Math.cos(Math.toRadians((double) this.mStyles.horizontalLabelsAngle)));
                }
                while (true) {
                    int li2 = li;
                    if (li2 >= lines.length) {
                        break;
                    }
                    float y = (((float) ((canvas.getHeight() - this.mStyles.padding) - getHorizontalAxisTitleHeight())) - ((((float) ((lines.length - li2) - i2)) * getTextSize()) * 1.1f)) + ((float) this.mStyles.labelsSpace);
                    float x = (float) (this.mGraphView.getGraphContentLeft() + e.getKey().intValue());
                    if (this.mStyles.horizontalLabelsAngle <= 0.0f || this.mStyles.horizontalLabelsAngle >= f) {
                        if (this.mStyles.horizontalLabelsAngle > 0.0f) {
                            if (this.mStyles.horizontalLabelsAngle <= 180.0f) {
                                canvas.save();
                                canvas2.rotate(this.mStyles.horizontalLabelsAngle - 180.0f, x - ((float) labelWidthAdj), y);
                                canvas2.drawText(lines[li2], x - ((float) labelWidthAdj), y, this.mPaintLabel);
                                canvas.restore();
                            }
                        }
                        canvas2.drawText(lines[li2], x, y, this.mPaintLabel);
                    } else {
                        canvas.save();
                        canvas2.rotate(this.mStyles.horizontalLabelsAngle, ((float) labelWidthAdj) + x, y);
                        canvas2.drawText(lines[li2], ((float) labelWidthAdj) + x, y, this.mPaintLabel);
                        canvas.restore();
                    }
                    li = li2 + 1;
                    f = 90.0f;
                    i2 = 1;
                }
            }
            i++;
        }
    }

    /* access modifiers changed from: protected */
    public void drawVerticalStepsSecondScale(Canvas canvas) {
        if (this.mGraphView.mSecondScale != null) {
            float startLeft = (float) (this.mGraphView.getGraphContentLeft() + this.mGraphView.getGraphContentWidth());
            this.mPaintLabel.setColor(getVerticalLabelsSecondScaleColor());
            this.mPaintLabel.setTextAlign(getVerticalLabelsSecondScaleAlign());
            for (Map.Entry<Integer, Double> e : this.mStepsVerticalSecondScale.entrySet()) {
                float posY = (float) ((this.mGraphView.getGraphContentTop() + this.mGraphView.getGraphContentHeight()) - e.getKey().intValue());
                int labelsWidth = this.mLabelVerticalSecondScaleWidth.intValue();
                int labelsOffset = (int) startLeft;
                if (getVerticalLabelsSecondScaleAlign() == Paint.Align.RIGHT) {
                    labelsOffset += labelsWidth;
                } else if (getVerticalLabelsSecondScaleAlign() == Paint.Align.CENTER) {
                    labelsOffset += labelsWidth / 2;
                }
                int li = 0;
                String[] lines = this.mGraphView.mSecondScale.mLabelFormatter.formatLabel(e.getValue().doubleValue(), false).split("\n");
                float y = posY + (((((float) lines.length) * getTextSize()) * 1.1f) / 2.0f);
                while (true) {
                    int li2 = li;
                    if (li2 < lines.length) {
                        canvas.drawText(lines[li2], (float) labelsOffset, y - ((((float) ((lines.length - li2) - 1)) * getTextSize()) * 1.1f), this.mPaintLabel);
                        li = li2 + 1;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawVerticalSteps(Canvas canvas) {
        GridLabelRenderer gridLabelRenderer = this;
        float startLeft = (float) gridLabelRenderer.mGraphView.getGraphContentLeft();
        gridLabelRenderer.mPaintLabel.setColor(getVerticalLabelsColor());
        gridLabelRenderer.mPaintLabel.setTextAlign(getVerticalLabelsAlign());
        int numberOfLine = gridLabelRenderer.mStepsVertical.size();
        int currentLine = 1;
        for (Map.Entry<Integer, Double> e : gridLabelRenderer.mStepsVertical.entrySet()) {
            float posY = (float) ((gridLabelRenderer.mGraphView.getGraphContentTop() + gridLabelRenderer.mGraphView.getGraphContentHeight()) - e.getKey().intValue());
            if (gridLabelRenderer.mStyles.highlightZeroLines) {
                if (e.getValue().doubleValue() == 0.0d) {
                    gridLabelRenderer.mPaintLine.setStrokeWidth(5.0f);
                } else {
                    gridLabelRenderer.mPaintLine.setStrokeWidth(0.0f);
                }
            }
            if (gridLabelRenderer.mStyles.gridStyle.drawHorizontal()) {
                canvas.drawLine(startLeft, posY, startLeft + ((float) gridLabelRenderer.mGraphView.getGraphContentWidth()), posY, gridLabelRenderer.mPaintLine);
            }
            boolean isDrawLabel = true;
            if ((gridLabelRenderer.mStyles.verticalLabelsVAlign == VerticalLabelsVAlign.ABOVE && currentLine == 1) || (gridLabelRenderer.mStyles.verticalLabelsVAlign == VerticalLabelsVAlign.BELOW && currentLine == numberOfLine)) {
                isDrawLabel = false;
            }
            if (isVerticalLabelsVisible() && isDrawLabel) {
                int labelsWidth = gridLabelRenderer.mLabelVerticalWidth.intValue();
                int labelsOffset = 0;
                if (getVerticalLabelsAlign() == Paint.Align.RIGHT) {
                    labelsOffset = labelsWidth - gridLabelRenderer.mStyles.labelsSpace;
                } else if (getVerticalLabelsAlign() == Paint.Align.CENTER) {
                    labelsOffset = labelsWidth / 2;
                }
                int labelsOffset2 = labelsOffset + gridLabelRenderer.mStyles.padding + getVerticalAxisTitleWidth();
                float y = posY;
                String label = gridLabelRenderer.mLabelFormatter.formatLabel(e.getValue().doubleValue(), false);
                if (label == null) {
                    label = "";
                }
                String[] lines = label.split("\n");
                float f = 1.1f;
                switch (gridLabelRenderer.mStyles.verticalLabelsVAlign) {
                    case MID:
                        y += ((((float) lines.length) * getTextSize()) * 1.1f) / 2.0f;
                        break;
                    case ABOVE:
                        y -= 5.0f;
                        break;
                    case BELOW:
                        y += (((float) lines.length) * getTextSize() * 1.1f) + 5.0f;
                        break;
                }
                int li = 0;
                while (true) {
                    int li2 = li;
                    if (li2 < lines.length) {
                        boolean isDrawLabel2 = isDrawLabel;
                        Paint paint = gridLabelRenderer.mPaintLabel;
                        canvas.drawText(lines[li2], (float) labelsOffset2, y - ((((float) ((lines.length - li2) - 1)) * getTextSize()) * f), paint);
                        li = li2 + 1;
                        startLeft = startLeft;
                        isDrawLabel = isDrawLabel2;
                        gridLabelRenderer = this;
                        f = 1.1f;
                    }
                }
            }
            Canvas canvas2 = canvas;
            boolean z = isDrawLabel;
            currentLine++;
            startLeft = startLeft;
            gridLabelRenderer = this;
        }
        Canvas canvas3 = canvas;
        float f2 = startLeft;
    }

    /* access modifiers changed from: protected */
    public double humanRound(double in, boolean roundAlwaysUp) {
        int ten = 0;
        while (Math.abs(in) >= 10.0d) {
            in /= 10.0d;
            ten++;
        }
        while (Math.abs(in) < 1.0d) {
            in *= 10.0d;
            ten--;
        }
        if (roundAlwaysUp) {
            if (in != 1.0d) {
                if (in <= 2.0d) {
                    in = 2.0d;
                } else if (in <= 5.0d) {
                    in = 5.0d;
                } else if (in < 10.0d) {
                    in = 10.0d;
                }
            }
        } else if (in != 1.0d) {
            if (in <= 4.9d) {
                in = 2.0d;
            } else if (in <= 9.9d) {
                in = 5.0d;
            } else if (in < 15.0d) {
                in = 10.0d;
            }
        }
        return Math.pow(10.0d, (double) ten) * in;
    }

    public Styles getStyles() {
        return this.mStyles;
    }

    public int getLabelVerticalWidth() {
        if (this.mStyles.verticalLabelsVAlign == VerticalLabelsVAlign.ABOVE || this.mStyles.verticalLabelsVAlign == VerticalLabelsVAlign.BELOW || this.mLabelVerticalWidth == null || !isVerticalLabelsVisible()) {
            return 0;
        }
        return this.mLabelVerticalWidth.intValue();
    }

    public void setLabelVerticalWidth(Integer width) {
        this.mLabelVerticalWidth = width;
        this.mLabelVerticalWidthFixed = this.mLabelVerticalWidth != null;
    }

    public int getLabelHorizontalHeight() {
        if (this.mLabelHorizontalHeight == null || !isHorizontalLabelsVisible()) {
            return 0;
        }
        return this.mLabelHorizontalHeight.intValue();
    }

    public void setLabelHorizontalHeight(Integer height) {
        this.mLabelHorizontalHeight = height;
        this.mLabelHorizontalHeightFixed = this.mLabelHorizontalHeight != null;
    }

    public int getGridColor() {
        return this.mStyles.gridColor;
    }

    public boolean isHighlightZeroLines() {
        return this.mStyles.highlightZeroLines;
    }

    public int getPadding() {
        return this.mStyles.padding;
    }

    public void setTextSize(float textSize) {
        this.mStyles.textSize = textSize;
        reloadStyles();
    }

    public void setVerticalLabelsAlign(Paint.Align verticalLabelsAlign) {
        this.mStyles.verticalLabelsAlign = verticalLabelsAlign;
    }

    public void setVerticalLabelsColor(int verticalLabelsColor) {
        this.mStyles.verticalLabelsColor = verticalLabelsColor;
    }

    public void setHorizontalLabelsColor(int horizontalLabelsColor) {
        this.mStyles.horizontalLabelsColor = horizontalLabelsColor;
    }

    public void setHorizontalLabelsAngle(int horizontalLabelsAngle) {
        this.mStyles.horizontalLabelsAngle = (float) horizontalLabelsAngle;
    }

    public void setGridColor(int gridColor) {
        this.mStyles.gridColor = gridColor;
        reloadStyles();
    }

    public void setHighlightZeroLines(boolean highlightZeroLines) {
        this.mStyles.highlightZeroLines = highlightZeroLines;
    }

    public void setPadding(int padding) {
        this.mStyles.padding = padding;
    }

    public LabelFormatter getLabelFormatter() {
        return this.mLabelFormatter;
    }

    public void setLabelFormatter(LabelFormatter mLabelFormatter2) {
        this.mLabelFormatter = mLabelFormatter2;
        mLabelFormatter2.setViewport(this.mGraphView.getViewport());
    }

    public String getHorizontalAxisTitle() {
        return this.mHorizontalAxisTitle;
    }

    public void setHorizontalAxisTitle(String mHorizontalAxisTitle2) {
        this.mHorizontalAxisTitle = mHorizontalAxisTitle2;
    }

    public String getVerticalAxisTitle() {
        return this.mVerticalAxisTitle;
    }

    public void setVerticalAxisTitle(String mVerticalAxisTitle2) {
        this.mVerticalAxisTitle = mVerticalAxisTitle2;
    }

    public float getVerticalAxisTitleTextSize() {
        return this.mStyles.verticalAxisTitleTextSize;
    }

    public void setVerticalAxisTitleTextSize(float verticalAxisTitleTextSize) {
        this.mStyles.verticalAxisTitleTextSize = verticalAxisTitleTextSize;
    }

    public int getVerticalAxisTitleColor() {
        return this.mStyles.verticalAxisTitleColor;
    }

    public void setVerticalAxisTitleColor(int verticalAxisTitleColor) {
        this.mStyles.verticalAxisTitleColor = verticalAxisTitleColor;
    }

    public float getHorizontalAxisTitleTextSize() {
        return this.mStyles.horizontalAxisTitleTextSize;
    }

    public void setHorizontalAxisTitleTextSize(float horizontalAxisTitleTextSize) {
        this.mStyles.horizontalAxisTitleTextSize = horizontalAxisTitleTextSize;
    }

    public int getHorizontalAxisTitleColor() {
        return this.mStyles.horizontalAxisTitleColor;
    }

    public void setHorizontalAxisTitleColor(int horizontalAxisTitleColor) {
        this.mStyles.horizontalAxisTitleColor = horizontalAxisTitleColor;
    }

    public Paint.Align getVerticalLabelsSecondScaleAlign() {
        return this.mStyles.verticalLabelsSecondScaleAlign;
    }

    public void setVerticalLabelsSecondScaleAlign(Paint.Align verticalLabelsSecondScaleAlign) {
        this.mStyles.verticalLabelsSecondScaleAlign = verticalLabelsSecondScaleAlign;
    }

    public int getVerticalLabelsSecondScaleColor() {
        return this.mStyles.verticalLabelsSecondScaleColor;
    }

    public void setVerticalLabelsSecondScaleColor(int verticalLabelsSecondScaleColor) {
        this.mStyles.verticalLabelsSecondScaleColor = verticalLabelsSecondScaleColor;
    }

    public int getLabelVerticalSecondScaleWidth() {
        if (this.mLabelVerticalSecondScaleWidth == null) {
            return 0;
        }
        return this.mLabelVerticalSecondScaleWidth.intValue();
    }

    public boolean isHorizontalLabelsVisible() {
        return this.mStyles.horizontalLabelsVisible;
    }

    public void setHorizontalLabelsVisible(boolean horizontalTitleVisible) {
        this.mStyles.horizontalLabelsVisible = horizontalTitleVisible;
    }

    public boolean isVerticalLabelsVisible() {
        return this.mStyles.verticalLabelsVisible;
    }

    public void setVerticalLabelsVisible(boolean verticalTitleVisible) {
        this.mStyles.verticalLabelsVisible = verticalTitleVisible;
    }

    public int getNumVerticalLabels() {
        return this.mNumVerticalLabels;
    }

    public void setNumVerticalLabels(int mNumVerticalLabels2) {
        this.mNumVerticalLabels = mNumVerticalLabels2;
    }

    public int getNumHorizontalLabels() {
        return this.mNumHorizontalLabels;
    }

    public void setNumHorizontalLabels(int mNumHorizontalLabels2) {
        this.mNumHorizontalLabels = mNumHorizontalLabels2;
    }

    public GridStyle getGridStyle() {
        return this.mStyles.gridStyle;
    }

    public void setGridStyle(GridStyle gridStyle) {
        this.mStyles.gridStyle = gridStyle;
    }

    public int getLabelsSpace() {
        return this.mStyles.labelsSpace;
    }

    public void setLabelsSpace(int labelsSpace) {
        this.mStyles.labelsSpace = labelsSpace;
    }

    public void setVerticalLabelsVAlign(VerticalLabelsVAlign align) {
        this.mStyles.verticalLabelsVAlign = align;
    }

    public VerticalLabelsVAlign getVerticalLabelsVAlign() {
        return this.mStyles.verticalLabelsVAlign;
    }
}
