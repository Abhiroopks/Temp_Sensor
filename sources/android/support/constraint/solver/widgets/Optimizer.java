package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintWidget;

public class Optimizer {
    static final int FLAG_CHAIN_DANGLING = 1;
    static final int FLAG_RECOMPUTE_BOUNDS = 2;
    static final int FLAG_USE_OPTIMIZE = 0;
    public static final int OPTIMIZATION_BARRIER = 2;
    public static final int OPTIMIZATION_CHAIN = 4;
    public static final int OPTIMIZATION_DIMENSIONS = 8;
    public static final int OPTIMIZATION_DIRECT = 1;
    public static final int OPTIMIZATION_NONE = 0;
    public static final int OPTIMIZATION_RATIO = 16;
    public static final int OPTIMIZATION_STANDARD = 3;
    static boolean[] flags = new boolean[3];

    static void checkMatchParent(ConstraintWidgetContainer container, LinearSystem system, ConstraintWidget widget) {
        if (container.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && widget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int left = widget.mLeft.mMargin;
            int right = container.getWidth() - widget.mRight.mMargin;
            widget.mLeft.mSolverVariable = system.createObjectVariable(widget.mLeft);
            widget.mRight.mSolverVariable = system.createObjectVariable(widget.mRight);
            system.addEquality(widget.mLeft.mSolverVariable, left);
            system.addEquality(widget.mRight.mSolverVariable, right);
            widget.mHorizontalResolution = 2;
            widget.setHorizontalDimension(left, right);
        }
        if (container.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && widget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int top = widget.mTop.mMargin;
            int bottom = container.getHeight() - widget.mBottom.mMargin;
            widget.mTop.mSolverVariable = system.createObjectVariable(widget.mTop);
            widget.mBottom.mSolverVariable = system.createObjectVariable(widget.mBottom);
            system.addEquality(widget.mTop.mSolverVariable, top);
            system.addEquality(widget.mBottom.mSolverVariable, bottom);
            if (widget.mBaselineDistance > 0 || widget.getVisibility() == 8) {
                widget.mBaseline.mSolverVariable = system.createObjectVariable(widget.mBaseline);
                system.addEquality(widget.mBaseline.mSolverVariable, widget.mBaselineDistance + top);
            }
            widget.mVerticalResolution = 2;
            widget.setVerticalDimension(top, bottom);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x003e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean optimizableMatchConstraint(android.support.constraint.solver.widgets.ConstraintWidget r3, int r4) {
        /*
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r3.mListDimensionBehaviors
            r0 = r0[r4]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r2 = 0
            if (r0 == r1) goto L_0x000a
            return r2
        L_0x000a:
            float r0 = r3.mDimensionRatio
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x0020
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r3.mListDimensionBehaviors
            if (r4 != 0) goto L_0x0017
            goto L_0x0018
        L_0x0017:
            r1 = r2
        L_0x0018:
            r0 = r0[r1]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r0 != r1) goto L_0x001f
            return r2
        L_0x001f:
            return r2
        L_0x0020:
            if (r4 != 0) goto L_0x0030
            int r0 = r3.mMatchConstraintDefaultWidth
            if (r0 == 0) goto L_0x0027
            return r2
        L_0x0027:
            int r0 = r3.mMatchConstraintMinWidth
            if (r0 != 0) goto L_0x002f
            int r0 = r3.mMatchConstraintMaxWidth
            if (r0 == 0) goto L_0x003e
        L_0x002f:
            return r2
        L_0x0030:
            int r0 = r3.mMatchConstraintDefaultHeight
            if (r0 == 0) goto L_0x0035
            return r2
        L_0x0035:
            int r0 = r3.mMatchConstraintMinHeight
            if (r0 != 0) goto L_0x003f
            int r0 = r3.mMatchConstraintMaxHeight
            if (r0 == 0) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            return r1
        L_0x003f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Optimizer.optimizableMatchConstraint(android.support.constraint.solver.widgets.ConstraintWidget, int):boolean");
    }

    static void analyze(int optimisationLevel, ConstraintWidget widget) {
        widget.updateResolutionNodes();
        ResolutionAnchor leftNode = widget.mLeft.getResolutionNode();
        ResolutionAnchor topNode = widget.mTop.getResolutionNode();
        ResolutionAnchor rightNode = widget.mRight.getResolutionNode();
        ResolutionAnchor bottomNode = widget.mBottom.getResolutionNode();
        boolean optimiseDimensions = (optimisationLevel & 8) == 8;
        if (!(leftNode.type == 4 || rightNode.type == 4)) {
            if (widget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED) {
                if (widget.mLeft.mTarget == null && widget.mRight.mTarget == null) {
                    leftNode.setType(1);
                    rightNode.setType(1);
                    if (optimiseDimensions) {
                        rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        rightNode.dependsOn(leftNode, widget.getWidth());
                    }
                } else if (widget.mLeft.mTarget != null && widget.mRight.mTarget == null) {
                    leftNode.setType(1);
                    rightNode.setType(1);
                    if (optimiseDimensions) {
                        rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        rightNode.dependsOn(leftNode, widget.getWidth());
                    }
                } else if (widget.mLeft.mTarget == null && widget.mRight.mTarget != null) {
                    leftNode.setType(1);
                    rightNode.setType(1);
                    leftNode.dependsOn(rightNode, -widget.getWidth());
                    if (optimiseDimensions) {
                        leftNode.dependsOn(rightNode, -1, widget.getResolutionWidth());
                    } else {
                        leftNode.dependsOn(rightNode, -widget.getWidth());
                    }
                } else if (!(widget.mLeft.mTarget == null || widget.mRight.mTarget == null)) {
                    leftNode.setType(2);
                    rightNode.setType(2);
                    if (optimiseDimensions) {
                        widget.getResolutionWidth().addDependent(leftNode);
                        widget.getResolutionWidth().addDependent(rightNode);
                        leftNode.setOpposite(rightNode, -1, widget.getResolutionWidth());
                        rightNode.setOpposite(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        leftNode.setOpposite(rightNode, (float) (-widget.getWidth()));
                        rightNode.setOpposite(leftNode, (float) widget.getWidth());
                    }
                }
            } else if (widget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(widget, 0)) {
                int width = widget.getWidth();
                leftNode.setType(1);
                rightNode.setType(1);
                if (widget.mLeft.mTarget == null && widget.mRight.mTarget == null) {
                    if (optimiseDimensions) {
                        rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        rightNode.dependsOn(leftNode, width);
                    }
                } else if (widget.mLeft.mTarget == null || widget.mRight.mTarget != null) {
                    if (widget.mLeft.mTarget != null || widget.mRight.mTarget == null) {
                        if (!(widget.mLeft.mTarget == null || widget.mRight.mTarget == null)) {
                            if (optimiseDimensions) {
                                widget.getResolutionWidth().addDependent(leftNode);
                                widget.getResolutionWidth().addDependent(rightNode);
                            }
                            if (widget.mDimensionRatio == 0.0f) {
                                leftNode.setType(3);
                                rightNode.setType(3);
                                leftNode.setOpposite(rightNode, 0.0f);
                                rightNode.setOpposite(leftNode, 0.0f);
                            } else {
                                leftNode.setType(2);
                                rightNode.setType(2);
                                leftNode.setOpposite(rightNode, (float) (-width));
                                rightNode.setOpposite(leftNode, (float) width);
                                widget.setWidth(width);
                            }
                        }
                    } else if (optimiseDimensions) {
                        leftNode.dependsOn(rightNode, -1, widget.getResolutionWidth());
                    } else {
                        leftNode.dependsOn(rightNode, -width);
                    }
                } else if (optimiseDimensions) {
                    rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                } else {
                    rightNode.dependsOn(leftNode, width);
                }
            }
        }
        if (topNode.type != 4 && bottomNode.type != 4) {
            if (widget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.FIXED) {
                if (widget.mTop.mTarget == null && widget.mBottom.mTarget == null) {
                    topNode.setType(1);
                    bottomNode.setType(1);
                    if (optimiseDimensions) {
                        bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                    } else {
                        bottomNode.dependsOn(topNode, widget.getHeight());
                    }
                    if (widget.mBaseline.mTarget != null) {
                        widget.mBaseline.getResolutionNode().setType(1);
                        topNode.dependsOn(1, widget.mBaseline.getResolutionNode(), -widget.mBaselineDistance);
                    }
                } else if (widget.mTop.mTarget != null && widget.mBottom.mTarget == null) {
                    topNode.setType(1);
                    bottomNode.setType(1);
                    if (optimiseDimensions) {
                        bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                    } else {
                        bottomNode.dependsOn(topNode, widget.getHeight());
                    }
                    if (widget.mBaselineDistance > 0) {
                        widget.mBaseline.getResolutionNode().dependsOn(1, topNode, widget.mBaselineDistance);
                    }
                } else if (widget.mTop.mTarget == null && widget.mBottom.mTarget != null) {
                    topNode.setType(1);
                    bottomNode.setType(1);
                    if (optimiseDimensions) {
                        topNode.dependsOn(bottomNode, -1, widget.getResolutionHeight());
                    } else {
                        topNode.dependsOn(bottomNode, -widget.getHeight());
                    }
                    if (widget.mBaselineDistance > 0) {
                        widget.mBaseline.getResolutionNode().dependsOn(1, topNode, widget.mBaselineDistance);
                    }
                } else if (widget.mTop.mTarget != null && widget.mBottom.mTarget != null) {
                    topNode.setType(2);
                    bottomNode.setType(2);
                    if (optimiseDimensions) {
                        topNode.setOpposite(bottomNode, -1, widget.getResolutionHeight());
                        bottomNode.setOpposite(topNode, 1, widget.getResolutionHeight());
                        widget.getResolutionHeight().addDependent(topNode);
                        widget.getResolutionWidth().addDependent(bottomNode);
                    } else {
                        topNode.setOpposite(bottomNode, (float) (-widget.getHeight()));
                        bottomNode.setOpposite(topNode, (float) widget.getHeight());
                    }
                    if (widget.mBaselineDistance > 0) {
                        widget.mBaseline.getResolutionNode().dependsOn(1, topNode, widget.mBaselineDistance);
                    }
                }
            } else if (widget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(widget, 1)) {
                int height = widget.getHeight();
                topNode.setType(1);
                bottomNode.setType(1);
                if (widget.mTop.mTarget == null && widget.mBottom.mTarget == null) {
                    if (optimiseDimensions) {
                        bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                    } else {
                        bottomNode.dependsOn(topNode, height);
                    }
                } else if (widget.mTop.mTarget == null || widget.mBottom.mTarget != null) {
                    if (widget.mTop.mTarget != null || widget.mBottom.mTarget == null) {
                        if (widget.mTop.mTarget != null && widget.mBottom.mTarget != null) {
                            if (optimiseDimensions) {
                                widget.getResolutionHeight().addDependent(topNode);
                                widget.getResolutionWidth().addDependent(bottomNode);
                            }
                            if (widget.mDimensionRatio == 0.0f) {
                                topNode.setType(3);
                                bottomNode.setType(3);
                                topNode.setOpposite(bottomNode, 0.0f);
                                bottomNode.setOpposite(topNode, 0.0f);
                                return;
                            }
                            topNode.setType(2);
                            bottomNode.setType(2);
                            topNode.setOpposite(bottomNode, (float) (-height));
                            bottomNode.setOpposite(topNode, (float) height);
                            widget.setHeight(height);
                            if (widget.mBaselineDistance > 0) {
                                widget.mBaseline.getResolutionNode().dependsOn(1, topNode, widget.mBaselineDistance);
                            }
                        }
                    } else if (optimiseDimensions) {
                        topNode.dependsOn(bottomNode, -1, widget.getResolutionHeight());
                    } else {
                        topNode.dependsOn(bottomNode, -height);
                    }
                } else if (optimiseDimensions) {
                    bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                } else {
                    bottomNode.dependsOn(topNode, height);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x0184  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0189  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean applyChainOptimized(android.support.constraint.solver.widgets.ConstraintWidgetContainer r42, android.support.constraint.solver.LinearSystem r43, int r44, int r45, android.support.constraint.solver.widgets.ConstraintWidget r46) {
        /*
            r0 = r43
            r2 = r46
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = r42
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r12 = r11.mListDimensionBehaviors
            r12 = r12[r44]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r12 != r13) goto L_0x0018
            r12 = 1
            goto L_0x0019
        L_0x0018:
            r12 = 0
        L_0x0019:
            r13 = 0
            r16 = 0
            r17 = 0
            r18 = r46
            if (r44 != 0) goto L_0x0061
            boolean r19 = r42.isRtl()
            if (r19 == 0) goto L_0x0061
        L_0x0028:
            if (r6 != 0) goto L_0x0058
            android.support.constraint.solver.widgets.ConstraintAnchor[] r14 = r2.mListAnchors
            int r19 = r45 + 1
            r14 = r14[r19]
            android.support.constraint.solver.widgets.ConstraintAnchor r14 = r14.mTarget
            if (r14 == 0) goto L_0x004c
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r14.mOwner
            r20 = r4
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r3.mListAnchors
            r4 = r4[r45]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x004a
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r3.mListAnchors
            r4 = r4[r45]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            if (r4 == r2) goto L_0x004f
        L_0x004a:
            r3 = 0
            goto L_0x004f
        L_0x004c:
            r20 = r4
            r3 = 0
        L_0x004f:
            if (r3 == 0) goto L_0x0053
            r2 = r3
            goto L_0x0055
        L_0x0053:
            r4 = 1
            r6 = r4
        L_0x0055:
            r4 = r20
            goto L_0x0028
        L_0x0058:
            r20 = r4
            r18 = r2
            r2 = r46
            r3 = 0
            r6 = 0
            goto L_0x0063
        L_0x0061:
            r20 = r4
        L_0x0063:
            r4 = r3
            r3 = r2
            r2 = r18
            if (r44 != 0) goto L_0x0089
            int r14 = r2.mHorizontalChainStyle
            if (r14 != 0) goto L_0x006f
            r14 = 1
            goto L_0x0070
        L_0x006f:
            r14 = 0
        L_0x0070:
            r13 = r14
            int r14 = r2.mHorizontalChainStyle
            r22 = r3
            r3 = 1
            if (r14 != r3) goto L_0x007a
            r3 = 1
            goto L_0x007b
        L_0x007a:
            r3 = 0
        L_0x007b:
            int r14 = r2.mHorizontalChainStyle
            r23 = r3
            r3 = 2
            if (r14 != r3) goto L_0x0084
            r3 = 1
            goto L_0x0085
        L_0x0084:
            r3 = 0
        L_0x0085:
            r24 = r2
            r2 = r3
            goto L_0x00a7
        L_0x0089:
            r22 = r3
            int r3 = r2.mVerticalChainStyle
            if (r3 != 0) goto L_0x0091
            r3 = 1
            goto L_0x0092
        L_0x0091:
            r3 = 0
        L_0x0092:
            r13 = r3
            int r3 = r2.mVerticalChainStyle
            r14 = 1
            if (r3 != r14) goto L_0x009a
            r3 = 1
            goto L_0x009b
        L_0x009a:
            r3 = 0
        L_0x009b:
            int r14 = r2.mVerticalChainStyle
            r24 = r2
            r2 = 2
            if (r14 != r2) goto L_0x00a4
            r2 = 1
            goto L_0x00a5
        L_0x00a4:
            r2 = 0
        L_0x00a5:
            r23 = r3
        L_0x00a7:
            r3 = 0
            r14 = 0
            r17 = r3
            r16 = r8
            r18 = r14
            r8 = r20
            r3 = 0
            r14 = r7
            r7 = r4
            r4 = r22
        L_0x00b6:
            if (r6 != 0) goto L_0x018f
            r25 = r6
            android.support.constraint.solver.widgets.ConstraintWidget[] r6 = r4.mListNextVisibleWidget
            r19 = 0
            r6[r44] = r19
            int r6 = r4.getVisibility()
            r26 = r7
            r7 = 8
            if (r6 == r7) goto L_0x010c
            if (r5 == 0) goto L_0x00d0
            android.support.constraint.solver.widgets.ConstraintWidget[] r6 = r5.mListNextVisibleWidget
            r6[r44] = r4
        L_0x00d0:
            if (r8 != 0) goto L_0x00d3
            r8 = r4
        L_0x00d3:
            r5 = r4
            int r3 = r3 + 1
            if (r44 != 0) goto L_0x00e0
            int r6 = r4.getWidth()
            float r6 = (float) r6
            float r17 = r17 + r6
            goto L_0x00e7
        L_0x00e0:
            int r6 = r4.getHeight()
            float r6 = (float) r6
            float r17 = r17 + r6
        L_0x00e7:
            if (r4 == r8) goto L_0x00f4
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r4.mListAnchors
            r6 = r6[r45]
            int r6 = r6.getMargin()
            float r6 = (float) r6
            float r17 = r17 + r6
        L_0x00f4:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r4.mListAnchors
            r6 = r6[r45]
            int r6 = r6.getMargin()
            float r6 = (float) r6
            float r18 = r18 + r6
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r4.mListAnchors
            int r20 = r45 + 1
            r6 = r6[r20]
            int r6 = r6.getMargin()
            float r6 = (float) r6
            float r18 = r18 + r6
        L_0x010c:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r4.mListAnchors
            r6 = r6[r45]
            android.support.constraint.solver.widgets.ConstraintWidget[] r7 = r4.mListNextMatchConstraintsWidget
            r7[r44] = r19
            int r7 = r4.getVisibility()
            r27 = r3
            r3 = 8
            if (r7 == r3) goto L_0x015c
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r4.mListDimensionBehaviors
            r3 = r3[r44]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r3 != r7) goto L_0x015c
            int r14 = r14 + 1
            if (r44 != 0) goto L_0x013a
            int r3 = r4.mMatchConstraintDefaultWidth
            if (r3 == 0) goto L_0x0130
            r3 = 0
            return r3
        L_0x0130:
            r3 = 0
            int r7 = r4.mMatchConstraintMinWidth
            if (r7 != 0) goto L_0x0139
            int r7 = r4.mMatchConstraintMaxWidth
            if (r7 == 0) goto L_0x0149
        L_0x0139:
            return r3
        L_0x013a:
            r3 = 0
            int r7 = r4.mMatchConstraintDefaultHeight
            if (r7 == 0) goto L_0x0140
            return r3
        L_0x0140:
            int r3 = r4.mMatchConstraintMinHeight
            if (r3 != 0) goto L_0x015a
            int r3 = r4.mMatchConstraintMaxHeight
            if (r3 == 0) goto L_0x0149
            goto L_0x015a
        L_0x0149:
            float[] r3 = r4.mWeight
            r3 = r3[r44]
            float r16 = r16 + r3
            if (r9 != 0) goto L_0x0153
            r9 = r4
            goto L_0x0157
        L_0x0153:
            android.support.constraint.solver.widgets.ConstraintWidget[] r3 = r10.mListNextMatchConstraintsWidget
            r3[r44] = r4
        L_0x0157:
            r3 = r4
            r10 = r3
            goto L_0x015c
        L_0x015a:
            r3 = 0
            return r3
        L_0x015c:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r4.mListAnchors
            int r7 = r45 + 1
            r3 = r3[r7]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x017e
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r3.mOwner
            r28 = r3
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r7.mListAnchors
            r3 = r3[r45]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x017c
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r7.mListAnchors
            r3 = r3[r45]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            if (r3 == r4) goto L_0x0182
        L_0x017c:
            r3 = 0
            goto L_0x0181
        L_0x017e:
            r28 = r3
            r3 = 0
        L_0x0181:
            r7 = r3
        L_0x0182:
            if (r7 == 0) goto L_0x0189
            r3 = r7
            r4 = r3
            r6 = r25
            goto L_0x018b
        L_0x0189:
            r3 = 1
            r6 = r3
        L_0x018b:
            r3 = r27
            goto L_0x00b6
        L_0x018f:
            r25 = r6
            r26 = r7
            r6 = r4
            r7 = r46
            r29 = r9
            android.support.constraint.solver.widgets.ConstraintAnchor[] r9 = r7.mListAnchors
            r9 = r9[r45]
            android.support.constraint.solver.widgets.ResolutionAnchor r9 = r9.getResolutionNode()
            r30 = r10
            android.support.constraint.solver.widgets.ConstraintAnchor[] r10 = r6.mListAnchors
            int r19 = r45 + 1
            r10 = r10[r19]
            android.support.constraint.solver.widgets.ResolutionAnchor r10 = r10.getResolutionNode()
            r31 = r6
            android.support.constraint.solver.widgets.ResolutionAnchor r6 = r9.target
            if (r6 == 0) goto L_0x0488
            android.support.constraint.solver.widgets.ResolutionAnchor r6 = r10.target
            if (r6 != 0) goto L_0x01c3
            r34 = r4
            r33 = r10
            r32 = r12
            r39 = r13
            r40 = r14
            r10 = r8
            goto L_0x0493
        L_0x01c3:
            android.support.constraint.solver.widgets.ResolutionAnchor r6 = r9.target
            int r6 = r6.state
            r11 = 1
            if (r6 == r11) goto L_0x01d2
            android.support.constraint.solver.widgets.ResolutionAnchor r6 = r10.target
            int r6 = r6.state
            if (r6 == r11) goto L_0x01d2
            r6 = 0
            return r6
        L_0x01d2:
            r6 = 0
            if (r14 <= 0) goto L_0x01d8
            if (r14 == r3) goto L_0x01d8
            return r6
        L_0x01d8:
            r6 = 0
            if (r2 != 0) goto L_0x01df
            if (r13 != 0) goto L_0x01df
            if (r23 == 0) goto L_0x01f8
        L_0x01df:
            if (r8 == 0) goto L_0x01ea
            android.support.constraint.solver.widgets.ConstraintAnchor[] r11 = r8.mListAnchors
            r11 = r11[r45]
            int r11 = r11.getMargin()
            float r6 = (float) r11
        L_0x01ea:
            if (r5 == 0) goto L_0x01f8
            android.support.constraint.solver.widgets.ConstraintAnchor[] r11 = r5.mListAnchors
            int r19 = r45 + 1
            r11 = r11[r19]
            int r11 = r11.getMargin()
            float r11 = (float) r11
            float r6 = r6 + r11
        L_0x01f8:
            android.support.constraint.solver.widgets.ResolutionAnchor r11 = r9.target
            float r11 = r11.resolvedOffset
            r32 = r12
            android.support.constraint.solver.widgets.ResolutionAnchor r12 = r10.target
            float r12 = r12.resolvedOffset
            r19 = 0
            int r20 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r20 >= 0) goto L_0x020d
            float r20 = r12 - r11
            float r20 = r20 - r17
        L_0x020c:
            goto L_0x0212
        L_0x020d:
            float r20 = r11 - r12
            float r20 = r20 - r17
            goto L_0x020c
        L_0x0212:
            r21 = 1
            if (r14 <= 0) goto L_0x0319
            if (r14 != r3) goto L_0x0319
            android.support.constraint.solver.widgets.ConstraintWidget r19 = r4.getParent()
            if (r19 == 0) goto L_0x0230
            r33 = r10
            android.support.constraint.solver.widgets.ConstraintWidget r10 = r4.getParent()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r10.mListDimensionBehaviors
            r10 = r10[r44]
            r34 = r4
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r10 != r4) goto L_0x0234
            r4 = 0
            return r4
        L_0x0230:
            r34 = r4
            r33 = r10
        L_0x0234:
            float r20 = r20 + r17
            float r20 = r20 - r18
            r4 = r8
            r10 = r11
            if (r13 == 0) goto L_0x0240
            float r15 = r18 - r6
            float r20 = r20 - r15
        L_0x0240:
            if (r13 == 0) goto L_0x0268
            r35 = r12
            android.support.constraint.solver.widgets.ConstraintAnchor[] r12 = r4.mListAnchors
            int r15 = r45 + 1
            r12 = r12[r15]
            int r12 = r12.getMargin()
            float r12 = (float) r12
            float r10 = r10 + r12
            android.support.constraint.solver.widgets.ConstraintWidget[] r12 = r4.mListNextVisibleWidget
            r12 = r12[r44]
            if (r12 == 0) goto L_0x0265
            r36 = r4
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r12.mListAnchors
            r4 = r4[r45]
            int r4 = r4.getMargin()
            float r4 = (float) r4
            float r10 = r10 + r4
            r4 = r36
            goto L_0x026e
        L_0x0265:
            r36 = r4
            goto L_0x026e
        L_0x0268:
            r36 = r4
            r35 = r12
            r12 = r26
        L_0x026e:
            if (r4 == 0) goto L_0x030f
            android.support.constraint.solver.Metrics r15 = android.support.constraint.solver.LinearSystem.sMetrics
            if (r15 == 0) goto L_0x0293
            r37 = r12
            android.support.constraint.solver.Metrics r12 = android.support.constraint.solver.LinearSystem.sMetrics
            r38 = r8
            long r7 = r12.nonresolvedWidgets
            long r7 = r7 - r21
            r12.nonresolvedWidgets = r7
            android.support.constraint.solver.Metrics r7 = android.support.constraint.solver.LinearSystem.sMetrics
            r39 = r13
            long r12 = r7.resolvedWidgets
            long r12 = r12 + r21
            r7.resolvedWidgets = r12
            android.support.constraint.solver.Metrics r7 = android.support.constraint.solver.LinearSystem.sMetrics
            long r12 = r7.chainConnectionResolved
            long r12 = r12 + r21
            r7.chainConnectionResolved = r12
            goto L_0x0299
        L_0x0293:
            r38 = r8
            r37 = r12
            r39 = r13
        L_0x0299:
            android.support.constraint.solver.widgets.ConstraintWidget[] r7 = r4.mListNextVisibleWidget
            r12 = r7[r44]
            if (r12 != 0) goto L_0x02a5
            if (r4 != r5) goto L_0x02a2
            goto L_0x02a5
        L_0x02a2:
            r40 = r14
            goto L_0x0304
        L_0x02a5:
            float r7 = (float) r14
            float r7 = r20 / r7
            r8 = 0
            int r8 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
            if (r8 <= 0) goto L_0x02b5
            float[] r8 = r4.mWeight
            r8 = r8[r44]
            float r8 = r8 * r20
            float r7 = r8 / r16
        L_0x02b5:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r4.mListAnchors
            r8 = r8[r45]
            int r8 = r8.getMargin()
            float r8 = (float) r8
            float r10 = r10 + r8
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r4.mListAnchors
            r8 = r8[r45]
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r8.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r13 = r9.resolvedTarget
            r8.resolve(r13, r10)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r4.mListAnchors
            int r13 = r45 + 1
            r8 = r8[r13]
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r8.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r13 = r9.resolvedTarget
            r40 = r14
            float r14 = r10 + r7
            r8.resolve(r13, r14)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r4.mListAnchors
            r8 = r8[r45]
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r8.getResolutionNode()
            r8.addResolvedValue(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r4.mListAnchors
            int r13 = r45 + 1
            r8 = r8[r13]
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r8.getResolutionNode()
            r8.addResolvedValue(r0)
            float r10 = r10 + r7
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r4.mListAnchors
            int r13 = r45 + 1
            r8 = r8[r13]
            int r8 = r8.getMargin()
            float r8 = (float) r8
            float r10 = r10 + r8
        L_0x0304:
            r4 = r12
            r8 = r38
            r13 = r39
            r14 = r40
            r7 = r46
            goto L_0x026e
        L_0x030f:
            r38 = r8
            r37 = r12
            r39 = r13
            r40 = r14
            r7 = 1
            return r7
        L_0x0319:
            r34 = r4
            r38 = r8
            r33 = r10
            r35 = r12
            r39 = r13
            r40 = r14
            int r4 = (r20 > r17 ? 1 : (r20 == r17 ? 0 : -1))
            if (r4 >= 0) goto L_0x032b
            r4 = 0
            return r4
        L_0x032b:
            if (r2 == 0) goto L_0x03c8
            float r20 = r20 - r6
            r4 = r38
            float r7 = r46.getHorizontalBiasPercent()
            float r7 = r7 * r20
            float r7 = r7 + r11
            r20 = r7
            r7 = r26
        L_0x033c:
            if (r4 == 0) goto L_0x03c2
            android.support.constraint.solver.Metrics r8 = android.support.constraint.solver.LinearSystem.sMetrics
            if (r8 == 0) goto L_0x035a
            android.support.constraint.solver.Metrics r8 = android.support.constraint.solver.LinearSystem.sMetrics
            long r12 = r8.nonresolvedWidgets
            long r12 = r12 - r21
            r8.nonresolvedWidgets = r12
            android.support.constraint.solver.Metrics r8 = android.support.constraint.solver.LinearSystem.sMetrics
            long r12 = r8.resolvedWidgets
            long r12 = r12 + r21
            r8.resolvedWidgets = r12
            android.support.constraint.solver.Metrics r8 = android.support.constraint.solver.LinearSystem.sMetrics
            long r12 = r8.chainConnectionResolved
            long r12 = r12 + r21
            r8.chainConnectionResolved = r12
        L_0x035a:
            android.support.constraint.solver.widgets.ConstraintWidget[] r8 = r4.mListNextVisibleWidget
            r7 = r8[r44]
            if (r7 != 0) goto L_0x0362
            if (r4 != r5) goto L_0x03bf
        L_0x0362:
            r8 = 0
            if (r44 != 0) goto L_0x036b
            int r10 = r4.getWidth()
            float r8 = (float) r10
            goto L_0x0370
        L_0x036b:
            int r10 = r4.getHeight()
            float r8 = (float) r10
        L_0x0370:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r10 = r4.mListAnchors
            r10 = r10[r45]
            int r10 = r10.getMargin()
            float r10 = (float) r10
            float r10 = r20 + r10
            android.support.constraint.solver.widgets.ConstraintAnchor[] r12 = r4.mListAnchors
            r12 = r12[r45]
            android.support.constraint.solver.widgets.ResolutionAnchor r12 = r12.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r13 = r9.resolvedTarget
            r12.resolve(r13, r10)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r12 = r4.mListAnchors
            int r13 = r45 + 1
            r12 = r12[r13]
            android.support.constraint.solver.widgets.ResolutionAnchor r12 = r12.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r13 = r9.resolvedTarget
            float r14 = r10 + r8
            r12.resolve(r13, r14)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r12 = r4.mListAnchors
            r12 = r12[r45]
            android.support.constraint.solver.widgets.ResolutionAnchor r12 = r12.getResolutionNode()
            r12.addResolvedValue(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r12 = r4.mListAnchors
            int r13 = r45 + 1
            r12 = r12[r13]
            android.support.constraint.solver.widgets.ResolutionAnchor r12 = r12.getResolutionNode()
            r12.addResolvedValue(r0)
            float r10 = r10 + r8
            android.support.constraint.solver.widgets.ConstraintAnchor[] r12 = r4.mListAnchors
            int r13 = r45 + 1
            r12 = r12[r13]
            int r12 = r12.getMargin()
            float r12 = (float) r12
            float r20 = r10 + r12
        L_0x03bf:
            r4 = r7
            goto L_0x033c
        L_0x03c2:
            r26 = r7
        L_0x03c4:
            r10 = r38
            goto L_0x0486
        L_0x03c8:
            if (r39 != 0) goto L_0x03d0
            if (r23 == 0) goto L_0x03cd
            goto L_0x03d0
        L_0x03cd:
            r4 = r34
            goto L_0x03c4
        L_0x03d0:
            if (r39 == 0) goto L_0x03d5
            float r20 = r20 - r6
            goto L_0x03d9
        L_0x03d5:
            if (r23 == 0) goto L_0x03d9
            float r20 = r20 - r6
        L_0x03d9:
            r4 = r38
            int r7 = r3 + 1
            float r7 = (float) r7
            float r7 = r20 / r7
            if (r23 == 0) goto L_0x03ef
            r8 = 1
            if (r3 <= r8) goto L_0x03eb
            int r8 = r3 + -1
            float r8 = (float) r8
            float r7 = r20 / r8
            goto L_0x03ef
        L_0x03eb:
            r8 = 1073741824(0x40000000, float:2.0)
            float r7 = r20 / r8
        L_0x03ef:
            float r8 = r11 + r7
            if (r23 == 0) goto L_0x0404
            r10 = 1
            if (r3 <= r10) goto L_0x0404
            r10 = r38
            android.support.constraint.solver.widgets.ConstraintAnchor[] r12 = r10.mListAnchors
            r12 = r12[r45]
            int r12 = r12.getMargin()
            float r12 = (float) r12
            float r8 = r11 + r12
            goto L_0x0406
        L_0x0404:
            r10 = r38
        L_0x0406:
            if (r39 == 0) goto L_0x0414
            if (r10 == 0) goto L_0x0414
            android.support.constraint.solver.widgets.ConstraintAnchor[] r12 = r10.mListAnchors
            r12 = r12[r45]
            int r12 = r12.getMargin()
            float r12 = (float) r12
            float r8 = r8 + r12
        L_0x0414:
            if (r4 == 0) goto L_0x0484
            android.support.constraint.solver.Metrics r12 = android.support.constraint.solver.LinearSystem.sMetrics
            if (r12 == 0) goto L_0x0432
            android.support.constraint.solver.Metrics r12 = android.support.constraint.solver.LinearSystem.sMetrics
            long r13 = r12.nonresolvedWidgets
            long r13 = r13 - r21
            r12.nonresolvedWidgets = r13
            android.support.constraint.solver.Metrics r12 = android.support.constraint.solver.LinearSystem.sMetrics
            long r13 = r12.resolvedWidgets
            long r13 = r13 + r21
            r12.resolvedWidgets = r13
            android.support.constraint.solver.Metrics r12 = android.support.constraint.solver.LinearSystem.sMetrics
            long r13 = r12.chainConnectionResolved
            long r13 = r13 + r21
            r12.chainConnectionResolved = r13
        L_0x0432:
            android.support.constraint.solver.widgets.ConstraintWidget[] r12 = r4.mListNextVisibleWidget
            r26 = r12[r44]
            if (r26 != 0) goto L_0x043a
            if (r4 != r5) goto L_0x0481
        L_0x043a:
            r12 = 0
            if (r44 != 0) goto L_0x0443
            int r13 = r4.getWidth()
            float r12 = (float) r13
            goto L_0x0448
        L_0x0443:
            int r13 = r4.getHeight()
            float r12 = (float) r13
        L_0x0448:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r13 = r4.mListAnchors
            r13 = r13[r45]
            android.support.constraint.solver.widgets.ResolutionAnchor r13 = r13.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r14 = r9.resolvedTarget
            r13.resolve(r14, r8)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r13 = r4.mListAnchors
            int r14 = r45 + 1
            r13 = r13[r14]
            android.support.constraint.solver.widgets.ResolutionAnchor r13 = r13.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r14 = r9.resolvedTarget
            float r1 = r8 + r12
            r13.resolve(r14, r1)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r4.mListAnchors
            r1 = r1[r45]
            android.support.constraint.solver.widgets.ResolutionAnchor r1 = r1.getResolutionNode()
            r1.addResolvedValue(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r4.mListAnchors
            int r13 = r45 + 1
            r1 = r1[r13]
            android.support.constraint.solver.widgets.ResolutionAnchor r1 = r1.getResolutionNode()
            r1.addResolvedValue(r0)
            float r1 = r12 + r7
            float r8 = r8 + r1
        L_0x0481:
            r4 = r26
            goto L_0x0414
        L_0x0484:
            r20 = r8
        L_0x0486:
            r1 = 1
            return r1
        L_0x0488:
            r34 = r4
            r33 = r10
            r32 = r12
            r39 = r13
            r40 = r14
            r10 = r8
        L_0x0493:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Optimizer.applyChainOptimized(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ConstraintWidget):boolean");
    }
}
