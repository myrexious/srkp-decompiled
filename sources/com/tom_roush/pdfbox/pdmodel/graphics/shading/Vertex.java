package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import android.graphics.PointF;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class Vertex {
    public float[] color;
    public PointF point;

    public Vertex(PointF pointF, float[] fArr) {
        this.point = pointF;
        this.color = (float[]) fArr.clone();
    }

    public String toString() {
        float[] fArr;
        StringBuilder sb = new StringBuilder();
        for (float f : this.color) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(String.format("%3.2f", Float.valueOf(f)));
        }
        return "Vertex{ " + this.point + ", colors=[" + ((Object) sb) + "] }";
    }
}
