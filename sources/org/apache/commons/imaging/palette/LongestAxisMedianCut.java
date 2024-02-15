package org.apache.commons.imaging.palette;

import java.util.Comparator;
import java.util.List;
import org.apache.commons.imaging.ImageWriteException;

/* loaded from: classes2.dex */
public class LongestAxisMedianCut implements MedianCut {
    private static final Comparator<ColorGroup> COMPARATOR = new Comparator() { // from class: org.apache.commons.imaging.palette.LongestAxisMedianCut$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return LongestAxisMedianCut.lambda$static$0((ColorGroup) obj, (ColorGroup) obj2);
        }
    };

    public static /* synthetic */ int lambda$static$0(ColorGroup colorGroup, ColorGroup colorGroup2) {
        int i;
        int i2;
        if (colorGroup.maxDiff == colorGroup2.maxDiff) {
            i = colorGroup2.diffTotal;
            i2 = colorGroup.diffTotal;
        } else {
            i = colorGroup2.maxDiff;
            i2 = colorGroup.maxDiff;
        }
        return i - i2;
    }

    @Override // org.apache.commons.imaging.palette.MedianCut
    public boolean performNextMedianCut(List<ColorGroup> list, boolean z) throws ImageWriteException {
        list.sort(COMPARATOR);
        ColorGroup colorGroup = list.get(0);
        if (colorGroup.maxDiff == 0) {
            return false;
        }
        if (!z && colorGroup.alphaDiff > colorGroup.redDiff && colorGroup.alphaDiff > colorGroup.greenDiff && colorGroup.alphaDiff > colorGroup.blueDiff) {
            doCut(colorGroup, ColorComponent.ALPHA, list, z);
            return true;
        } else if (colorGroup.redDiff > colorGroup.greenDiff && colorGroup.redDiff > colorGroup.blueDiff) {
            doCut(colorGroup, ColorComponent.RED, list, z);
            return true;
        } else if (colorGroup.greenDiff > colorGroup.blueDiff) {
            doCut(colorGroup, ColorComponent.GREEN, list, z);
            return true;
        } else {
            doCut(colorGroup, ColorComponent.BLUE, list, z);
            return true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0047, code lost:
        if (java.lang.Math.abs(r1 - r5) < java.lang.Math.abs(r4 - r1)) goto L10;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void doCut(org.apache.commons.imaging.palette.ColorGroup r9, org.apache.commons.imaging.palette.ColorComponent r10, java.util.List<org.apache.commons.imaging.palette.ColorGroup> r11, boolean r12) throws org.apache.commons.imaging.ImageWriteException {
        /*
            r8 = this;
            java.util.List r0 = r9.getColorCounts()
            org.apache.commons.imaging.palette.ColorCountComparator r1 = new org.apache.commons.imaging.palette.ColorCountComparator
            r1.<init>(r10)
            r0.sort(r1)
            int r1 = r9.totalPoints
            double r1 = (double) r1
            r3 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r1 = r1 / r3
            long r1 = java.lang.Math.round(r1)
            int r1 = (int) r1
            r2 = 0
            r3 = r2
            r4 = r3
        L1a:
            r5 = r4
            int r6 = r0.size()
            if (r3 >= r6) goto L30
            java.lang.Object r6 = r0.get(r3)
            org.apache.commons.imaging.palette.ColorCount r6 = (org.apache.commons.imaging.palette.ColorCount) r6
            int r6 = r6.count
            int r4 = r4 + r6
            if (r4 < r1) goto L2d
            goto L30
        L2d:
            int r3 = r3 + 1
            goto L1a
        L30:
            int r6 = r0.size()
            r7 = 1
            int r6 = r6 - r7
            if (r3 != r6) goto L3b
        L38:
            int r3 = r3 + (-1)
            goto L4a
        L3b:
            if (r3 <= 0) goto L4a
            int r4 = r4 - r1
            int r4 = java.lang.Math.abs(r4)
            int r1 = r1 - r5
            int r1 = java.lang.Math.abs(r1)
            if (r1 >= r4) goto L4a
            goto L38
        L4a:
            r11.remove(r9)
            java.util.ArrayList r1 = new java.util.ArrayList
            int r4 = r3 + 1
            java.util.List r2 = r0.subList(r2, r4)
            r1.<init>(r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            int r5 = r0.size()
            java.util.List r4 = r0.subList(r4, r5)
            r2.<init>(r4)
            org.apache.commons.imaging.palette.ColorGroup r4 = new org.apache.commons.imaging.palette.ColorGroup
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r1)
            r4.<init>(r5, r12)
            r11.add(r4)
            org.apache.commons.imaging.palette.ColorGroup r1 = new org.apache.commons.imaging.palette.ColorGroup
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r2)
            r1.<init>(r5, r12)
            r11.add(r1)
            java.lang.Object r11 = r0.get(r3)
            org.apache.commons.imaging.palette.ColorCount r11 = (org.apache.commons.imaging.palette.ColorCount) r11
            int[] r12 = org.apache.commons.imaging.palette.LongestAxisMedianCut.AnonymousClass1.$SwitchMap$org$apache$commons$imaging$palette$ColorComponent
            int r0 = r10.ordinal()
            r12 = r12[r0]
            if (r12 == r7) goto La9
            r0 = 2
            if (r12 == r0) goto La6
            r0 = 3
            if (r12 == r0) goto La3
            r0 = 4
            if (r12 != r0) goto L9b
            int r11 = r11.blue
            goto Lab
        L9b:
            java.lang.Error r9 = new java.lang.Error
            java.lang.String r10 = "Bad mode."
            r9.<init>(r10)
            throw r9
        La3:
            int r11 = r11.green
            goto Lab
        La6:
            int r11 = r11.red
            goto Lab
        La9:
            int r11 = r11.alpha
        Lab:
            org.apache.commons.imaging.palette.ColorGroupCut r12 = new org.apache.commons.imaging.palette.ColorGroupCut
            r12.<init>(r4, r1, r10, r11)
            r9.cut = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.palette.LongestAxisMedianCut.doCut(org.apache.commons.imaging.palette.ColorGroup, org.apache.commons.imaging.palette.ColorComponent, java.util.List, boolean):void");
    }

    /* renamed from: org.apache.commons.imaging.palette.LongestAxisMedianCut$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
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
