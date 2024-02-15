package org.opencv.features2d;

/* loaded from: classes4.dex */
public class BFMatcher extends DescriptorMatcher {
    private static native long BFMatcher_0(int i, boolean z);

    private static native long BFMatcher_1();

    private static native long create_0(int i, boolean z);

    private static native long create_1();

    private static native void delete(long j);

    protected BFMatcher(long j) {
        super(j);
    }

    public static BFMatcher __fromPtr__(long j) {
        return new BFMatcher(j);
    }

    public BFMatcher(int i, boolean z) {
        super(BFMatcher_0(i, z));
    }

    public BFMatcher() {
        super(BFMatcher_1());
    }

    public static BFMatcher create(int i, boolean z) {
        return __fromPtr__(create_0(i, z));
    }

    public static BFMatcher create() {
        return __fromPtr__(create_1());
    }

    @Override // org.opencv.features2d.DescriptorMatcher, org.opencv.core.Algorithm
    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
