package org.visp.imgproc;

/* loaded from: classes4.dex */
public class VpContour {
    public final long nativeObj;

    private static native long VpContour1();

    private static native long VpContour2(int i);

    private static native long VpContour3(long j);

    private static native void delete(long j);

    private static native void n_setParent(long j, long j2);

    public VpContour(long j) {
        if (j == 0) {
            throw new UnsupportedOperationException("Native object address is NULL");
        }
        this.nativeObj = j;
    }

    public VpContour() {
        this.nativeObj = VpContour1();
    }

    public VpContour(int i) {
        this.nativeObj = VpContour2(i);
    }

    public VpContour(VpContour vpContour) {
        this.nativeObj = VpContour3(vpContour.nativeObj);
    }

    public void setParent(VpContour vpContour) {
        n_setParent(this.nativeObj, vpContour.nativeObj);
    }

    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }

    /* loaded from: classes4.dex */
    public class VpContourType {
        public static final int CONTOUR_HOLE = 1;
        public static final int CONTOUR_OUTER = 0;

        public VpContourType() {
            VpContour.this = r1;
        }
    }

    /* loaded from: classes4.dex */
    public class VpContourRetrievalType {
        public static final int CONTOUR_RETR_EXTERNAL = 2;
        public static final int CONTOUR_RETR_LIST = 1;
        public static final int CONTOUR_RETR_TREE = 0;

        public VpContourRetrievalType() {
            VpContour.this = r1;
        }
    }
}
