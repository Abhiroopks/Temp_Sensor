package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;

class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem system, int orientation) {
        ConstraintWidget[] chainsArray;
        int chainsSize;
        int offset;
        if (orientation == 0) {
            offset = 0;
            chainsSize = constraintWidgetContainer.mHorizontalChainsSize;
            chainsArray = constraintWidgetContainer.mHorizontalChainsArray;
        } else {
            offset = 2;
            chainsSize = constraintWidgetContainer.mVerticalChainsSize;
            chainsArray = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i = 0; i < chainsSize; i++) {
            ConstraintWidget first = chainsArray[i];
            if (!constraintWidgetContainer.optimizeFor(4)) {
                applyChainConstraints(constraintWidgetContainer, system, orientation, offset, first);
            } else if (!Optimizer.applyChainOptimized(constraintWidgetContainer, system, orientation, offset, first)) {
                applyChainConstraints(constraintWidgetContainer, system, orientation, offset, first);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v0, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v1, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v3, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v6, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v1, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v7, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v8, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v9, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r47v0, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v2, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v3, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v4, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v6, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v7, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v1, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v39, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r47v1, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v14, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v0, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v1, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v2, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v3, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v10, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v16, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v11, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v12, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v104, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v36, resolved type: android.support.constraint.solver.widgets.ConstraintWidget[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v74, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v13, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v13, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v4, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v51, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v86, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v52, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v53, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v54, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v20, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v21, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v101, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v63, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v8, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v104, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v127, resolved type: android.support.constraint.solver.widgets.ConstraintWidget} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:277:0x05bf A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:281:0x05d1  */
    /* JADX WARNING: Removed duplicated region for block: B:282:0x05d6  */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x05dd  */
    /* JADX WARNING: Removed duplicated region for block: B:286:0x05e2  */
    /* JADX WARNING: Removed duplicated region for block: B:289:0x05e8  */
    /* JADX WARNING: Removed duplicated region for block: B:292:0x05f6 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:317:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r59, android.support.constraint.solver.LinearSystem r60, int r61, int r62, android.support.constraint.solver.widgets.ConstraintWidget r63) {
        /*
            r0 = r59
            r10 = r60
            r12 = r63
            r1 = r12
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r13 = r0.mListDimensionBehaviors
            r13 = r13[r61]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r14 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r15 = r1
            if (r13 != r14) goto L_0x001a
            r13 = 1
            goto L_0x001b
        L_0x001a:
            r13 = 0
        L_0x001b:
            r14 = 0
            r17 = 0
            r18 = 0
            r19 = r12
            if (r61 != 0) goto L_0x0071
            boolean r20 = r59.isRtl()
            if (r20 == 0) goto L_0x0071
            r58 = r15
            r15 = r2
            r2 = r58
        L_0x002f:
            if (r5 != 0) goto L_0x0068
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r2.mListAnchors
            int r20 = r62 + 1
            r1 = r1[r20]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x0058
            r22 = r3
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r1.mOwner
            r23 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r3.mListAnchors
            r1 = r1[r62]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x0056
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r3.mListAnchors
            r1 = r1[r62]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r1.mOwner
            if (r1 == r2) goto L_0x0054
            goto L_0x0056
        L_0x0054:
            r15 = r3
            goto L_0x005e
        L_0x0056:
            r1 = 0
            goto L_0x005d
        L_0x0058:
            r23 = r1
            r22 = r3
            r1 = 0
        L_0x005d:
            r15 = r1
        L_0x005e:
            if (r15 == 0) goto L_0x0063
            r1 = r15
            r2 = r1
            goto L_0x0065
        L_0x0063:
            r1 = 1
            r5 = r1
        L_0x0065:
            r3 = r22
            goto L_0x002f
        L_0x0068:
            r22 = r3
            r19 = r2
            r1 = r12
            r2 = 0
            r5 = 0
            r15 = r1
            goto L_0x0073
        L_0x0071:
            r22 = r3
        L_0x0073:
            r1 = r5
            r5 = r19
            if (r61 != 0) goto L_0x00a2
            int r3 = r5.mHorizontalChainStyle
            if (r3 != 0) goto L_0x007e
            r3 = 1
            goto L_0x007f
        L_0x007e:
            r3 = 0
        L_0x007f:
            int r14 = r5.mHorizontalChainStyle
            r25 = r1
            r1 = 1
            if (r14 != r1) goto L_0x0088
            r1 = 1
            goto L_0x0089
        L_0x0088:
            r1 = 0
        L_0x0089:
            int r14 = r5.mHorizontalChainStyle
            r26 = r1
            r1 = 2
            if (r14 != r1) goto L_0x0092
            r1 = 1
            goto L_0x0093
        L_0x0092:
            r1 = 0
        L_0x0093:
            r18 = r1
            r19 = r3
            r14 = r4
            r20 = r7
            r17 = r8
            r8 = r9
            r1 = r15
            r9 = r22
        L_0x00a0:
            r15 = r6
            goto L_0x00ce
        L_0x00a2:
            r25 = r1
            int r1 = r5.mVerticalChainStyle
            if (r1 != 0) goto L_0x00aa
            r1 = 1
            goto L_0x00ab
        L_0x00aa:
            r1 = 0
        L_0x00ab:
            r3 = r1
            int r1 = r5.mVerticalChainStyle
            r14 = 1
            if (r1 != r14) goto L_0x00b3
            r1 = r14
            goto L_0x00b4
        L_0x00b3:
            r1 = 0
        L_0x00b4:
            int r14 = r5.mVerticalChainStyle
            r28 = r1
            r1 = 2
            if (r14 != r1) goto L_0x00bd
            r1 = 1
            goto L_0x00be
        L_0x00bd:
            r1 = 0
        L_0x00be:
            r18 = r1
            r19 = r3
            r14 = r4
            r20 = r7
            r17 = r8
            r8 = r9
            r1 = r15
            r9 = r22
            r26 = r28
            goto L_0x00a0
        L_0x00ce:
            r21 = 0
            if (r25 != 0) goto L_0x01c3
            android.support.constraint.solver.widgets.ConstraintWidget[] r4 = r1.mListNextVisibleWidget
            r4[r61] = r21
            int r4 = r1.getVisibility()
            r6 = 8
            if (r4 == r6) goto L_0x00e9
            if (r14 == 0) goto L_0x00e4
            android.support.constraint.solver.widgets.ConstraintWidget[] r4 = r14.mListNextVisibleWidget
            r4[r61] = r1
        L_0x00e4:
            if (r9 != 0) goto L_0x00e7
            r9 = r1
        L_0x00e7:
            r4 = r1
            r14 = r4
        L_0x00e9:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r1.mListAnchors
            r4 = r4[r62]
            r22 = 1
            int r23 = r4.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r4.mTarget
            if (r3 == 0) goto L_0x0107
            if (r1 == r12) goto L_0x0107
            int r3 = r1.getVisibility()
            if (r3 == r6) goto L_0x0107
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r4.mTarget
            int r3 = r3.getMargin()
            int r23 = r23 + r3
        L_0x0107:
            r3 = r23
            if (r18 == 0) goto L_0x0111
            if (r1 == r12) goto L_0x0111
            if (r1 == r9) goto L_0x0111
            r22 = 6
        L_0x0111:
            r6 = r22
            if (r1 != r9) goto L_0x0124
            android.support.constraint.solver.SolverVariable r7 = r4.mSolverVariable
            r37 = r2
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r4.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            r38 = r9
            r9 = 5
            r10.addGreaterThan(r7, r2, r3, r9)
            goto L_0x0132
        L_0x0124:
            r37 = r2
            r38 = r9
            android.support.constraint.solver.SolverVariable r2 = r4.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r4.mTarget
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            r9 = 6
            r10.addGreaterThan(r2, r7, r3, r9)
        L_0x0132:
            android.support.constraint.solver.SolverVariable r2 = r4.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r4.mTarget
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            r10.addEquality(r2, r7, r3, r6)
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r1.mListNextMatchConstraintsWidget
            r2[r61] = r21
            int r2 = r1.getVisibility()
            r7 = 8
            if (r2 == r7) goto L_0x017d
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r1.mListDimensionBehaviors
            r2 = r2[r61]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r7) goto L_0x017d
            int r15 = r15 + 1
            float[] r2 = r1.mWeight
            r2 = r2[r61]
            float r20 = r20 + r2
            if (r17 != 0) goto L_0x015c
            r17 = r1
            goto L_0x0160
        L_0x015c:
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r8.mListNextMatchConstraintsWidget
            r2[r61] = r1
        L_0x0160:
            r2 = r1
            if (r13 == 0) goto L_0x0179
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r1.mListAnchors
            int r8 = r62 + 1
            r7 = r7[r8]
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r1.mListAnchors
            r8 = r8[r62]
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            r39 = r2
            r2 = 6
            r9 = 0
            r10.addGreaterThan(r7, r8, r9, r2)
            goto L_0x017b
        L_0x0179:
            r39 = r2
        L_0x017b:
            r8 = r39
        L_0x017d:
            if (r13 == 0) goto L_0x0193
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r1.mListAnchors
            r2 = r2[r62]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r0.mListAnchors
            r7 = r7[r62]
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            r40 = r3
            r3 = 6
            r9 = 0
            r10.addGreaterThan(r2, r7, r9, r3)
            goto L_0x0196
        L_0x0193:
            r40 = r3
            r9 = 0
        L_0x0196:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r1.mListAnchors
            int r3 = r62 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x01b6
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r3.mListAnchors
            r7 = r7[r62]
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            if (r7 == 0) goto L_0x01b4
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r3.mListAnchors
            r7 = r7[r62]
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            if (r7 == r1) goto L_0x01b7
        L_0x01b4:
            r3 = 0
            goto L_0x01b7
        L_0x01b6:
            r3 = 0
        L_0x01b7:
            if (r3 == 0) goto L_0x01bb
            r1 = r3
            goto L_0x01be
        L_0x01bb:
            r2 = 1
            r25 = r2
        L_0x01be:
            r2 = r3
            r9 = r38
            goto L_0x00ce
        L_0x01c3:
            r37 = r2
            r2 = 0
            r7 = r1
            if (r14 == 0) goto L_0x01f1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r7.mListAnchors
            int r4 = r62 + 1
            r3 = r3[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x01f1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r14.mListAnchors
            int r4 = r62 + 1
            r3 = r3[r4]
            android.support.constraint.solver.SolverVariable r4 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r7.mListAnchors
            int r16 = r62 + 1
            r6 = r6[r16]
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            int r2 = r3.getMargin()
            int r2 = -r2
            r41 = r8
            r8 = 5
            r10.addLowerThan(r4, r6, r2, r8)
            goto L_0x01f4
        L_0x01f1:
            r41 = r8
            r8 = 5
        L_0x01f4:
            if (r13 == 0) goto L_0x0214
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r0.mListAnchors
            int r3 = r62 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r7.mListAnchors
            int r4 = r62 + 1
            r3 = r3[r4]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r7.mListAnchors
            int r6 = r62 + 1
            r4 = r4[r6]
            int r4 = r4.getMargin()
            r6 = 6
            r10.addGreaterThan(r2, r3, r4, r6)
        L_0x0214:
            if (r15 <= 0) goto L_0x02a5
            r1 = r17
            r2 = r37
        L_0x021a:
            if (r1 == 0) goto L_0x029c
            android.support.constraint.solver.widgets.ConstraintWidget[] r3 = r1.mListNextMatchConstraintsWidget
            r2 = r3[r61]
            if (r2 == 0) goto L_0x028c
            float[] r3 = r1.mWeight
            r3 = r3[r61]
            float[] r4 = r2.mWeight
            r4 = r4[r61]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r1.mListAnchors
            r6 = r6[r62]
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r1.mListAnchors
            int r16 = r62 + 1
            r8 = r8[r16]
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r2.mListAnchors
            r0 = r0[r62]
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            r42 = r13
            android.support.constraint.solver.widgets.ConstraintAnchor[] r13 = r2.mListAnchors
            int r16 = r62 + 1
            r13 = r13[r16]
            android.support.constraint.solver.SolverVariable r13 = r13.mSolverVariable
            if (r61 != 0) goto L_0x0258
            r43 = r5
            int r5 = r1.mMatchConstraintDefaultWidth
            r44 = r5
            int r5 = r2.mMatchConstraintDefaultWidth
            r45 = r1
            r1 = r5
            r5 = r44
            goto L_0x0260
        L_0x0258:
            r43 = r5
            int r5 = r1.mMatchConstraintDefaultHeight
            r45 = r1
            int r1 = r2.mMatchConstraintDefaultHeight
        L_0x0260:
            r11 = 3
            if (r5 == 0) goto L_0x0265
            if (r5 != r11) goto L_0x026a
        L_0x0265:
            if (r1 == 0) goto L_0x026c
            if (r1 != r11) goto L_0x026a
            goto L_0x026c
        L_0x026a:
            r11 = 0
            goto L_0x026d
        L_0x026c:
            r11 = 1
        L_0x026d:
            if (r11 == 0) goto L_0x0292
            r46 = r1
            android.support.constraint.solver.ArrayRow r1 = r60.createRow()
            r28 = r1
            r29 = r3
            r30 = r20
            r31 = r4
            r32 = r6
            r33 = r8
            r34 = r0
            r35 = r13
            r28.createRowEqualMatchDimensions(r29, r30, r31, r32, r33, r34, r35)
            r10.addConstraint(r1)
            goto L_0x0292
        L_0x028c:
            r45 = r1
            r43 = r5
            r42 = r13
        L_0x0292:
            r1 = r2
            r13 = r42
            r5 = r43
            r0 = r59
            r8 = 5
            goto L_0x021a
        L_0x029c:
            r45 = r1
            r43 = r5
            r42 = r13
            r37 = r2
            goto L_0x02ab
        L_0x02a5:
            r43 = r5
            r42 = r13
            r45 = r1
        L_0x02ab:
            if (r9 == 0) goto L_0x0354
            if (r9 == r14) goto L_0x02bb
            if (r18 == 0) goto L_0x02b2
            goto L_0x02bb
        L_0x02b2:
            r47 = r7
            r0 = r9
            r29 = r41
            r28 = r43
            goto L_0x035b
        L_0x02bb:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r62]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r7.mListAnchors
            int r2 = r62 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r12.mListAnchors
            r2 = r2[r62]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x02d6
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r12.mListAnchors
            r2 = r2[r62]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x02d8
        L_0x02d6:
            r2 = r21
        L_0x02d8:
            r11 = r2
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r7.mListAnchors
            int r3 = r62 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x02ee
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r7.mListAnchors
            int r3 = r62 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x02f0
        L_0x02ee:
            r2 = r21
        L_0x02f0:
            r13 = r2
            if (r9 != r14) goto L_0x02fd
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r9.mListAnchors
            r0 = r2[r62]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r9.mListAnchors
            int r3 = r62 + 1
            r1 = r2[r3]
        L_0x02fd:
            r8 = r1
            if (r11 == 0) goto L_0x0347
            if (r13 == 0) goto L_0x0347
            r1 = 1056964608(0x3f000000, float:0.5)
            if (r61 != 0) goto L_0x030d
            r5 = r43
            float r1 = r5.mHorizontalBiasPercent
        L_0x030a:
            r22 = r1
            goto L_0x0312
        L_0x030d:
            r5 = r43
            float r1 = r5.mVerticalBiasPercent
            goto L_0x030a
        L_0x0312:
            int r23 = r0.getMargin()
            if (r14 != 0) goto L_0x0319
            r14 = r7
        L_0x0319:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r14.mListAnchors
            int r2 = r62 + 1
            r1 = r1[r2]
            int r24 = r1.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r6 = r8.mSolverVariable
            r27 = 5
            r1 = r10
            r3 = r11
            r4 = r23
            r28 = r5
            r5 = r22
            r29 = r6
            r6 = r13
            r47 = r7
            r7 = r29
            r30 = r8
            r29 = r41
            r8 = r24
            r48 = r0
            r0 = r9
            r9 = r27
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x034e
        L_0x0347:
            r47 = r7
            r0 = r9
            r29 = r41
            r28 = r43
        L_0x034e:
            r11 = r45
            r22 = r47
            goto L_0x05bd
        L_0x0354:
            r47 = r7
            r0 = r9
            r29 = r41
            r28 = r43
        L_0x035b:
            if (r19 == 0) goto L_0x0468
            if (r0 == 0) goto L_0x0468
            r1 = r0
            r11 = r1
        L_0x0361:
            r13 = r1
            if (r11 == 0) goto L_0x0462
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r11.mListNextVisibleWidget
            r9 = r1[r61]
            if (r9 != 0) goto L_0x0373
            if (r11 != r14) goto L_0x036d
            goto L_0x0373
        L_0x036d:
            r36 = r9
            r51 = r47
            goto L_0x0459
        L_0x0373:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            r8 = r1[r62]
            android.support.constraint.solver.SolverVariable r7 = r8.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r8.mTarget
            if (r1 == 0) goto L_0x0382
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r8.mTarget
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            goto L_0x0384
        L_0x0382:
            r1 = r21
        L_0x0384:
            if (r13 == r11) goto L_0x0391
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r62 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r1 = r2.mSolverVariable
        L_0x038e:
            r22 = r1
            goto L_0x03aa
        L_0x0391:
            if (r11 != r0) goto L_0x038e
            if (r13 != r11) goto L_0x038e
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r12.mListAnchors
            r2 = r2[r62]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x03a6
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r12.mListAnchors
            r2 = r2[r62]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x03a8
        L_0x03a6:
            r2 = r21
        L_0x03a8:
            r1 = r2
            goto L_0x038e
        L_0x03aa:
            r1 = 0
            r2 = 0
            r3 = 0
            int r4 = r8.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r11.mListAnchors
            int r6 = r62 + 1
            r5 = r5[r6]
            int r5 = r5.getMargin()
            if (r9 == 0) goto L_0x03d7
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r9.mListAnchors
            r1 = r6[r62]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r1.mTarget
            if (r6 == 0) goto L_0x03cc
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r1.mTarget
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            goto L_0x03ce
        L_0x03cc:
            r6 = r21
        L_0x03ce:
            r3 = r6
            r23 = r2
            r24 = r3
            r6 = r47
            r3 = r1
            goto L_0x03f7
        L_0x03d7:
            r49 = r1
            r6 = r47
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r6.mListAnchors
            int r23 = r62 + 1
            r1 = r1[r23]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x03e7
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
        L_0x03e7:
            r50 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            int r23 = r62 + 1
            r1 = r1[r23]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            r24 = r1
            r23 = r2
            r3 = r50
        L_0x03f7:
            if (r3 == 0) goto L_0x03fe
            int r1 = r3.getMargin()
            int r5 = r5 + r1
        L_0x03fe:
            r27 = r5
            if (r13 == 0) goto L_0x040d
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            int r2 = r62 + 1
            r1 = r1[r2]
            int r1 = r1.getMargin()
            int r4 = r4 + r1
        L_0x040d:
            r30 = r4
            if (r7 == 0) goto L_0x0455
            if (r22 == 0) goto L_0x0455
            if (r23 == 0) goto L_0x0455
            if (r24 == 0) goto L_0x0455
            r1 = r30
            if (r11 != r0) goto L_0x0423
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r0.mListAnchors
            r2 = r2[r62]
            int r1 = r2.getMargin()
        L_0x0423:
            r31 = r1
            r1 = r27
            if (r11 != r14) goto L_0x0433
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            int r4 = r62 + 1
            r2 = r2[r4]
            int r1 = r2.getMargin()
        L_0x0433:
            r32 = r1
            r5 = 1056964608(0x3f000000, float:0.5)
            r33 = 4
            r1 = r10
            r2 = r7
            r50 = r3
            r3 = r22
            r4 = r31
            r51 = r6
            r6 = r23
            r34 = r7
            r7 = r24
            r35 = r8
            r8 = r32
            r36 = r9
            r9 = r33
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x0459
        L_0x0455:
            r51 = r6
            r36 = r9
        L_0x0459:
            r1 = r11
            r11 = r36
            r37 = r36
            r47 = r51
            goto L_0x0361
        L_0x0462:
            r51 = r47
            r22 = r51
            goto L_0x05bd
        L_0x0468:
            r51 = r47
            if (r26 == 0) goto L_0x05b9
            if (r0 == 0) goto L_0x05b9
            r1 = r0
            r11 = r1
        L_0x0470:
            r13 = r1
            if (r11 == 0) goto L_0x052f
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r11.mListNextVisibleWidget
            r1 = r1[r61]
            if (r11 == r0) goto L_0x0528
            if (r11 == r14) goto L_0x0528
            if (r1 == 0) goto L_0x0528
            if (r1 != r14) goto L_0x0480
            r1 = 0
        L_0x0480:
            r9 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            r8 = r1[r62]
            android.support.constraint.solver.SolverVariable r7 = r8.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r8.mTarget
            if (r1 == 0) goto L_0x0490
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r8.mTarget
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            goto L_0x0492
        L_0x0490:
            r1 = r21
        L_0x0492:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r62 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r6 = r2.mSolverVariable
            r1 = 0
            r2 = 0
            r3 = 0
            int r4 = r8.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r11.mListAnchors
            int r22 = r62 + 1
            r5 = r5[r22]
            int r5 = r5.getMargin()
            if (r9 == 0) goto L_0x04c6
            r52 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r9.mListAnchors
            r1 = r1[r62]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            r53 = r2
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            if (r2 == 0) goto L_0x04c0
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r1.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x04c2
        L_0x04c0:
            r2 = r21
        L_0x04c2:
            r3 = r1
            r22 = r2
            goto L_0x04e4
        L_0x04c6:
            r52 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            int r22 = r62 + 1
            r1 = r1[r22]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x04d4
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
        L_0x04d4:
            r54 = r1
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            int r22 = r62 + 1
            r1 = r1[r22]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            r22 = r1
            r53 = r2
            r3 = r54
        L_0x04e4:
            if (r3 == 0) goto L_0x04eb
            int r1 = r3.getMargin()
            int r5 = r5 + r1
        L_0x04eb:
            r23 = r5
            if (r13 == 0) goto L_0x04fa
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            int r2 = r62 + 1
            r1 = r1[r2]
            int r1 = r1.getMargin()
            int r4 = r4 + r1
        L_0x04fa:
            r24 = r4
            if (r7 == 0) goto L_0x0523
            if (r6 == 0) goto L_0x0523
            if (r53 == 0) goto L_0x0523
            if (r22 == 0) goto L_0x0523
            r5 = 1056964608(0x3f000000, float:0.5)
            r27 = 4
            r1 = r10
            r2 = r7
            r54 = r3
            r3 = r6
            r4 = r24
            r30 = r6
            r6 = r53
            r31 = r7
            r7 = r22
            r32 = r8
            r8 = r23
            r33 = r9
            r9 = r27
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x0525
        L_0x0523:
            r33 = r9
        L_0x0525:
            r37 = r33
            goto L_0x052a
        L_0x0528:
            r37 = r1
        L_0x052a:
            r1 = r11
            r11 = r37
            goto L_0x0470
        L_0x052f:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r0.mListAnchors
            r9 = r1[r62]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r62]
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r14.mListAnchors
            int r2 = r62 + 1
            r7 = r1[r2]
            r6 = r51
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r6.mListAnchors
            int r2 = r62 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r1.mTarget
            if (r8 == 0) goto L_0x0599
            if (r0 == r14) goto L_0x0564
            android.support.constraint.solver.SolverVariable r1 = r9.mSolverVariable
            android.support.constraint.solver.SolverVariable r2 = r8.mSolverVariable
            int r3 = r9.getMargin()
            r4 = 5
            r10.addEquality(r1, r2, r3, r4)
            r56 = r5
            r22 = r6
            r57 = r7
            r23 = r8
            r24 = r9
            goto L_0x05a3
        L_0x0564:
            r4 = 5
            if (r5 == 0) goto L_0x0599
            android.support.constraint.solver.SolverVariable r2 = r9.mSolverVariable
            android.support.constraint.solver.SolverVariable r3 = r8.mSolverVariable
            int r22 = r9.getMargin()
            r23 = 1056964608(0x3f000000, float:0.5)
            android.support.constraint.solver.SolverVariable r1 = r7.mSolverVariable
            r55 = r8
            android.support.constraint.solver.SolverVariable r8 = r5.mSolverVariable
            int r24 = r7.getMargin()
            r27 = 5
            r30 = r1
            r1 = r10
            r4 = r22
            r56 = r5
            r5 = r23
            r22 = r6
            r6 = r30
            r57 = r7
            r7 = r8
            r23 = r55
            r8 = r24
            r24 = r9
            r9 = r27
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x05a3
        L_0x0599:
            r56 = r5
            r22 = r6
            r57 = r7
            r23 = r8
            r24 = r9
        L_0x05a3:
            r1 = r56
            if (r1 == 0) goto L_0x05bd
            if (r0 == r14) goto L_0x05bd
            r2 = r57
            android.support.constraint.solver.SolverVariable r3 = r2.mSolverVariable
            android.support.constraint.solver.SolverVariable r4 = r1.mSolverVariable
            int r5 = r2.getMargin()
            int r5 = -r5
            r6 = 5
            r10.addEquality(r3, r4, r5, r6)
            goto L_0x05bd
        L_0x05b9:
            r22 = r51
            r11 = r45
        L_0x05bd:
            if (r19 != 0) goto L_0x05c1
            if (r26 == 0) goto L_0x0625
        L_0x05c1:
            if (r0 == 0) goto L_0x0625
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r0.mListAnchors
            r1 = r1[r62]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            int r3 = r62 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            if (r3 == 0) goto L_0x05d6
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x05d8
        L_0x05d6:
            r3 = r21
        L_0x05d8:
            r13 = r3
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r2.mTarget
            if (r3 == 0) goto L_0x05e2
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r2.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x05e4
        L_0x05e2:
            r3 = r21
        L_0x05e4:
            r21 = r3
            if (r0 != r14) goto L_0x05f2
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            r1 = r3[r62]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            int r4 = r62 + 1
            r2 = r3[r4]
        L_0x05f2:
            r9 = r1
            r8 = r2
            if (r13 == 0) goto L_0x0625
            if (r21 == 0) goto L_0x0625
            r23 = 1056964608(0x3f000000, float:0.5)
            int r24 = r9.getMargin()
            if (r14 != 0) goto L_0x0602
            r14 = r22
        L_0x0602:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r14.mListAnchors
            int r2 = r62 + 1
            r1 = r1[r2]
            int r27 = r1.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r9.mSolverVariable
            android.support.constraint.solver.SolverVariable r7 = r8.mSolverVariable
            r30 = 5
            r1 = r10
            r3 = r13
            r4 = r24
            r5 = r23
            r6 = r21
            r31 = r8
            r8 = r27
            r32 = r9
            r9 = r30
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
        L_0x0625:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ConstraintWidget):void");
    }
}
