package org.apache.commons.imaging.palette;

import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes2.dex */
public class ColorCountComparator implements Comparator<ColorCount>, Serializable {
    private static final long serialVersionUID = 1;
    private final ColorComponent colorComponent;

    public ColorCountComparator(ColorComponent colorComponent) {
        this.colorComponent = colorComponent;
    }

    /* renamed from: org.apache.commons.imaging.palette.ColorCountComparator$1 */
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

    @Override // java.util.Comparator
    public int compare(ColorCount colorCount, ColorCount colorCount2) {
        int i;
        int i2;
        int i3 = AnonymousClass1.$SwitchMap$org$apache$commons$imaging$palette$ColorComponent[this.colorComponent.ordinal()];
        if (i3 == 1) {
            i = colorCount.alpha;
            i2 = colorCount2.alpha;
        } else if (i3 == 2) {
            i = colorCount.red;
            i2 = colorCount2.red;
        } else if (i3 == 3) {
            i = colorCount.green;
            i2 = colorCount2.green;
        } else if (i3 != 4) {
            return 0;
        } else {
            i = colorCount.blue;
            i2 = colorCount2.blue;
        }
        return i - i2;
    }
}
