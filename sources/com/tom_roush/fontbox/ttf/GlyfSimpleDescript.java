package com.tom_roush.fontbox.ttf;

import android.util.Log;
import java.io.IOException;

/* loaded from: classes3.dex */
public class GlyfSimpleDescript extends GlyfDescript {
    private int[] endPtsOfContours;
    private byte[] flags;
    private final int pointCount;
    private short[] xCoordinates;
    private short[] yCoordinates;

    @Override // com.tom_roush.fontbox.ttf.GlyphDescription
    public boolean isComposite() {
        return false;
    }

    public GlyfSimpleDescript() throws IOException {
        super((short) 0, null);
        this.pointCount = 0;
    }

    public GlyfSimpleDescript(short s, TTFDataStream tTFDataStream, short s2) throws IOException {
        super(s, tTFDataStream);
        if (s == 0) {
            this.pointCount = 0;
            return;
        }
        int[] readUnsignedShortArray = tTFDataStream.readUnsignedShortArray(s);
        this.endPtsOfContours = readUnsignedShortArray;
        int i = readUnsignedShortArray[s - 1];
        if (s == 1 && i == 65535) {
            this.pointCount = 0;
            return;
        }
        int i2 = i + 1;
        this.pointCount = i2;
        this.flags = new byte[i2];
        this.xCoordinates = new short[i2];
        this.yCoordinates = new short[i2];
        readInstructions(tTFDataStream, tTFDataStream.readUnsignedShort());
        readFlags(i2, tTFDataStream);
        readCoords(i2, tTFDataStream, s2);
    }

    @Override // com.tom_roush.fontbox.ttf.GlyphDescription
    public int getEndPtOfContours(int i) {
        return this.endPtsOfContours[i];
    }

    @Override // com.tom_roush.fontbox.ttf.GlyphDescription
    public byte getFlags(int i) {
        return this.flags[i];
    }

    @Override // com.tom_roush.fontbox.ttf.GlyphDescription
    public short getXCoordinate(int i) {
        return this.xCoordinates[i];
    }

    @Override // com.tom_roush.fontbox.ttf.GlyphDescription
    public short getYCoordinate(int i) {
        return this.yCoordinates[i];
    }

    @Override // com.tom_roush.fontbox.ttf.GlyphDescription
    public int getPointCount() {
        return this.pointCount;
    }

    private void readCoords(int i, TTFDataStream tTFDataStream, short s) throws IOException {
        short readSignedShort;
        int readUnsignedByte;
        short readSignedShort2;
        int readUnsignedByte2;
        for (int i2 = 0; i2 < i; i2++) {
            byte b = this.flags[i2];
            if ((b & 16) != 0) {
                if ((b & 2) != 0) {
                    readSignedShort2 = (short) tTFDataStream.readUnsignedByte();
                } else {
                    this.xCoordinates[i2] = s;
                }
            } else if ((b & 2) != 0) {
                readUnsignedByte2 = s - ((short) tTFDataStream.readUnsignedByte());
                s = (short) readUnsignedByte2;
                this.xCoordinates[i2] = s;
            } else {
                readSignedShort2 = tTFDataStream.readSignedShort();
            }
            readUnsignedByte2 = s + readSignedShort2;
            s = (short) readUnsignedByte2;
            this.xCoordinates[i2] = s;
        }
        short s2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            byte b2 = this.flags[i3];
            if ((b2 & 32) != 0) {
                if ((b2 & 4) != 0) {
                    readSignedShort = (short) tTFDataStream.readUnsignedByte();
                } else {
                    this.yCoordinates[i3] = s2;
                }
            } else if ((b2 & 4) != 0) {
                readUnsignedByte = s2 - ((short) tTFDataStream.readUnsignedByte());
                s2 = (short) readUnsignedByte;
                this.yCoordinates[i3] = s2;
            } else {
                readSignedShort = tTFDataStream.readSignedShort();
            }
            readUnsignedByte = s2 + readSignedShort;
            s2 = (short) readUnsignedByte;
            this.yCoordinates[i3] = s2;
        }
    }

    private void readFlags(int i, TTFDataStream tTFDataStream) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            this.flags[i2] = (byte) tTFDataStream.readUnsignedByte();
            if ((this.flags[i2] & 8) != 0) {
                int readUnsignedByte = tTFDataStream.readUnsignedByte();
                for (int i3 = 1; i3 <= readUnsignedByte; i3++) {
                    int i4 = i2 + i3;
                    byte[] bArr = this.flags;
                    if (i4 >= bArr.length) {
                        Log.e("PdfBox-Android", "repeat count (" + readUnsignedByte + ") higher than remaining space");
                        return;
                    }
                    bArr[i4] = bArr[i2];
                }
                i2 += readUnsignedByte;
            }
            i2++;
        }
    }
}
