package org.opencv.core;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes4.dex */
public class RotatedRect {
    public double angle;
    public Point center;
    public Size size;

    public RotatedRect() {
        this.center = new Point();
        this.size = new Size();
        this.angle = 0.0d;
    }

    public RotatedRect(Point point, Size size, double d) {
        this.center = point.clone();
        this.size = size.clone();
        this.angle = d;
    }

    public RotatedRect(double[] dArr) {
        this();
        set(dArr);
    }

    public void set(double[] dArr) {
        if (dArr != null) {
            this.center.x = dArr.length > 0 ? dArr[0] : 0.0d;
            this.center.y = dArr.length > 1 ? dArr[1] : 0.0d;
            this.size.width = dArr.length > 2 ? dArr[2] : 0.0d;
            this.size.height = dArr.length > 3 ? dArr[3] : 0.0d;
            this.angle = dArr.length > 4 ? dArr[4] : 0.0d;
            return;
        }
        this.center.x = 0.0d;
        this.center.x = 0.0d;
        this.size.width = 0.0d;
        this.size.height = 0.0d;
        this.angle = 0.0d;
    }

    public void points(Point[] pointArr) {
        double d = (this.angle * 3.141592653589793d) / 180.0d;
        double cos = Math.cos(d) * 0.5d;
        double sin = Math.sin(d) * 0.5d;
        pointArr[0] = new Point((this.center.x - (this.size.height * sin)) - (this.size.width * cos), (this.center.y + (this.size.height * cos)) - (this.size.width * sin));
        pointArr[1] = new Point((this.center.x + (this.size.height * sin)) - (this.size.width * cos), (this.center.y - (cos * this.size.height)) - (sin * this.size.width));
        pointArr[2] = new Point((this.center.x * 2.0d) - pointArr[0].x, (this.center.y * 2.0d) - pointArr[0].y);
        pointArr[3] = new Point((this.center.x * 2.0d) - pointArr[1].x, (this.center.y * 2.0d) - pointArr[1].y);
    }

    public Rect boundingRect() {
        Point[] pointArr = new Point[4];
        points(pointArr);
        Rect rect = new Rect((int) Math.floor(Math.min(Math.min(Math.min(pointArr[0].x, pointArr[1].x), pointArr[2].x), pointArr[3].x)), (int) Math.floor(Math.min(Math.min(Math.min(pointArr[0].y, pointArr[1].y), pointArr[2].y), pointArr[3].y)), (int) Math.ceil(Math.max(Math.max(Math.max(pointArr[0].x, pointArr[1].x), pointArr[2].x), pointArr[3].x)), (int) Math.ceil(Math.max(Math.max(Math.max(pointArr[0].y, pointArr[1].y), pointArr[2].y), pointArr[3].y)));
        rect.width -= rect.x - 1;
        rect.height -= rect.y - 1;
        return rect;
    }

    public RotatedRect clone() {
        return new RotatedRect(this.center, this.size, this.angle);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.center.x);
        long doubleToLongBits2 = Double.doubleToLongBits(this.center.y);
        int i = ((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        long doubleToLongBits3 = Double.doubleToLongBits(this.size.width);
        int i2 = (i * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)));
        long doubleToLongBits4 = Double.doubleToLongBits(this.size.height);
        int i3 = (i2 * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)));
        long doubleToLongBits5 = Double.doubleToLongBits(this.angle);
        return (i3 * 31) + ((int) ((doubleToLongBits5 >>> 32) ^ doubleToLongBits5));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RotatedRect) {
            RotatedRect rotatedRect = (RotatedRect) obj;
            return this.center.equals(rotatedRect.center) && this.size.equals(rotatedRect.size) && this.angle == rotatedRect.angle;
        }
        return false;
    }

    public String toString() {
        return "{ " + this.center + StringUtils.SPACE + this.size + " * " + this.angle + " }";
    }
}
