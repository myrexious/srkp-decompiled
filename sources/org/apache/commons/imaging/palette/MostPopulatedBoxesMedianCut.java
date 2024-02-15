package org.apache.commons.imaging.palette;

/* loaded from: classes2.dex */
public class MostPopulatedBoxesMedianCut implements MedianCut {
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0092, code lost:
        if (java.lang.Math.abs(r14 - r17) < java.lang.Math.abs(r16 - r14)) goto L40;
     */
    /* JADX WARN: Removed duplicated region for block: B:118:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x00e6 A[SYNTHETIC] */
    @Override // org.apache.commons.imaging.palette.MedianCut
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean performNextMedianCut(java.util.List<org.apache.commons.imaging.palette.ColorGroup> r19, boolean r20) throws org.apache.commons.imaging.ImageWriteException {
        /*
            Method dump skipped, instructions count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.palette.MostPopulatedBoxesMedianCut.performNextMedianCut(java.util.List, boolean):boolean");
    }

    /* renamed from: org.apache.commons.imaging.palette.MostPopulatedBoxesMedianCut$1 */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$imaging$palette$ColorComponent;

        static {
            int[] iArr = new int[ColorComponent.values().length];
            $SwitchMap$org$apache$commons$imaging$palette$ColorComponent = iArr;
            try {
                iArr[ColorComponent.ALPHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$palette$ColorComponent[ColorComponent.RED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$palette$ColorComponent[ColorComponent.GREEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$palette$ColorComponent[ColorComponent.BLUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
