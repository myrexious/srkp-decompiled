package org.apache.commons.imaging.formats.tiff;

import java.util.Comparator;
import java.util.function.ToLongFunction;

/* loaded from: classes2.dex */
public abstract class TiffElement {
    public static final Comparator<TiffElement> COMPARATOR = Comparator.comparingLong(new ToLongFunction() { // from class: org.apache.commons.imaging.formats.tiff.TiffElement$$ExternalSyntheticLambda0
        @Override // java.util.function.ToLongFunction
        public final long applyAsLong(Object obj) {
            long j;
            j = ((TiffElement) obj).offset;
            return j;
        }
    });
    public final int length;
    public final long offset;

    public abstract String getElementDescription();

    public TiffElement(long j, int i) {
        this.offset = j;
        this.length = i;
    }

    /* loaded from: classes2.dex */
    public static abstract class DataElement extends TiffElement {
        private final byte[] data;

        public DataElement(long j, int i, byte[] bArr) {
            super(j, i);
            this.data = bArr;
        }

        public byte[] getData() {
            return (byte[]) this.data.clone();
        }

        public int getDataLength() {
            return this.data.length;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Stub extends TiffElement {
        public Stub(long j, int i) {
            super(j, i);
        }

        @Override // org.apache.commons.imaging.formats.tiff.TiffElement
        public String getElementDescription() {
            return "Element, offset: " + this.offset + ", length: " + this.length + ", last: " + (this.offset + this.length);
        }
    }
}
