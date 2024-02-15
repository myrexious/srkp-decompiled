package org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;

/* loaded from: classes2.dex */
public class PhotometricInterpreterFloat extends PhotometricInterpreter {
    float maxFound;
    float minFound;
    int nFound;
    ArrayList<PaletteEntry> rangePaletteEntries;
    ArrayList<PaletteEntry> singleValuePaletteEntries;
    double sumFound;
    int xMax;
    int xMin;
    int yMax;
    int yMin;

    public PhotometricInterpreterFloat(float f, float f2) {
        super(1, new int[]{32}, 0, 32, 32);
        this.rangePaletteEntries = new ArrayList<>();
        this.singleValuePaletteEntries = new ArrayList<>();
        this.minFound = Float.POSITIVE_INFINITY;
        this.maxFound = Float.NEGATIVE_INFINITY;
        if (f2 > f) {
            this.rangePaletteEntries.add(new PaletteEntryForRange(f, f2, Color.black, Color.white));
        } else {
            this.rangePaletteEntries.add(new PaletteEntryForRange(f2, f, Color.white, Color.black));
        }
    }

    public PhotometricInterpreterFloat(List<PaletteEntry> list) {
        super(1, new int[]{32}, 0, 32, 32);
        this.rangePaletteEntries = new ArrayList<>();
        this.singleValuePaletteEntries = new ArrayList<>();
        this.minFound = Float.POSITIVE_INFINITY;
        this.maxFound = Float.NEGATIVE_INFINITY;
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Palette entries list must be non-null and non-empty");
        }
        for (PaletteEntry paletteEntry : list) {
            if (paletteEntry.coversSingleEntry()) {
                this.singleValuePaletteEntries.add(paletteEntry);
            } else {
                this.rangePaletteEntries.add(paletteEntry);
            }
        }
        Comparator<? super PaletteEntry> comparator = new Comparator() { // from class: org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PhotometricInterpreterFloat$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return PhotometricInterpreterFloat.lambda$new$0((PaletteEntry) obj, (PaletteEntry) obj2);
            }
        };
        this.rangePaletteEntries.sort(comparator);
        this.singleValuePaletteEntries.sort(comparator);
    }

    public static /* synthetic */ int lambda$new$0(PaletteEntry paletteEntry, PaletteEntry paletteEntry2) {
        if (paletteEntry.getLowerBound() == paletteEntry2.getLowerBound()) {
            return Double.compare(paletteEntry.getUpperBound(), paletteEntry2.getUpperBound());
        }
        return Double.compare(paletteEntry.getLowerBound(), paletteEntry2.getLowerBound());
    }

    @Override // org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter
    public void interpretPixel(ImageBuilder imageBuilder, int[] iArr, int i, int i2) throws ImageReadException, IOException {
        float intBitsToFloat = Float.intBitsToFloat(iArr[0]);
        Iterator<PaletteEntry> it = this.singleValuePaletteEntries.iterator();
        while (it.hasNext()) {
            PaletteEntry next = it.next();
            if (next.isCovered(intBitsToFloat)) {
                imageBuilder.setRGB(i, i2, next.getARGB(intBitsToFloat));
                return;
            }
        }
        if (Float.isNaN(intBitsToFloat)) {
            return;
        }
        if (intBitsToFloat < this.minFound) {
            this.minFound = intBitsToFloat;
            this.xMin = i;
            this.yMin = i2;
        }
        if (intBitsToFloat > this.maxFound) {
            this.maxFound = intBitsToFloat;
            this.xMax = i;
            this.yMax = i2;
        }
        this.nFound++;
        this.sumFound += intBitsToFloat;
        Iterator<PaletteEntry> it2 = this.singleValuePaletteEntries.iterator();
        while (it2.hasNext()) {
            PaletteEntry next2 = it2.next();
            if (next2.isCovered(intBitsToFloat)) {
                imageBuilder.setRGB(i, i2, next2.getARGB(intBitsToFloat));
                return;
            }
        }
        Iterator<PaletteEntry> it3 = this.rangePaletteEntries.iterator();
        while (it3.hasNext()) {
            PaletteEntry next3 = it3.next();
            if (next3.isCovered(intBitsToFloat)) {
                imageBuilder.setRGB(i, i2, next3.getARGB(intBitsToFloat));
                return;
            }
        }
    }

    public float getMinFound() {
        return this.minFound;
    }

    public int[] getMaxXY() {
        return new int[]{this.xMax, this.yMax};
    }

    public float getMaxFound() {
        return this.maxFound;
    }

    public int[] getMinXY() {
        return new int[]{this.xMin, this.yMin};
    }

    public float getMeanFound() {
        int i = this.nFound;
        if (i == 0) {
            return 0.0f;
        }
        return (float) (this.sumFound / i);
    }

    public int mapValueToARGB(float f) {
        Iterator<PaletteEntry> it = this.singleValuePaletteEntries.iterator();
        while (it.hasNext()) {
            PaletteEntry next = it.next();
            if (next.isCovered(f)) {
                return next.getARGB(f);
            }
        }
        if (Float.isNaN(f)) {
            return 0;
        }
        Iterator<PaletteEntry> it2 = this.rangePaletteEntries.iterator();
        while (it2.hasNext()) {
            PaletteEntry next2 = it2.next();
            if (next2.isCovered(f)) {
                return next2.getARGB(f);
            }
        }
        return 0;
    }
}
