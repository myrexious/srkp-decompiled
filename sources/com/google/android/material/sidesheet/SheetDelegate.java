package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes3.dex */
public abstract class SheetDelegate {
    public abstract int calculateInnerMargin(ViewGroup.MarginLayoutParams marginLayoutParams);

    public abstract float calculateSlideOffset(int i);

    public abstract int calculateTargetStateOnViewReleased(View view, float f, float f2);

    public abstract int getExpandedOffset();

    public abstract int getHiddenOffset();

    public abstract <V extends View> int getOuterEdge(V v);

    public abstract int getSheetEdge();

    public abstract boolean isSettling(View view, int i, boolean z);

    public abstract boolean shouldHide(View view, float f);

    public abstract void updateCoplanarSiblingLayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2);
}
