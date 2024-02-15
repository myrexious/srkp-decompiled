package org.checkerframework.checker.signedness;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import kotlin.UByte;
import kotlin.UShort;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.visp.core.VpRGBa;

/* loaded from: classes2.dex */
public final class SignednessUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static byte byteFromDouble(double d) {
        return (byte) d;
    }

    public static byte byteFromFloat(float f) {
        return (byte) f;
    }

    public static int intFromDouble(double d) {
        return (int) d;
    }

    public static int intFromFloat(float f) {
        return (int) f;
    }

    public static long longFromDouble(double d) {
        return (long) d;
    }

    public static long longFromFloat(float f) {
        return f;
    }

    public static short shortFromDouble(double d) {
        return (short) d;
    }

    public static short shortFromFloat(float f) {
        return (short) f;
    }

    public static int toUnsignedInt(byte b) {
        return b & UByte.MAX_VALUE;
    }

    public static int toUnsignedInt(char c) {
        return c & VpRGBa.alphaDefault;
    }

    public static int toUnsignedInt(short s) {
        return s & UShort.MAX_VALUE;
    }

    public static long toUnsignedLong(byte b) {
        return b & 255;
    }

    public static long toUnsignedLong(char c) {
        return c & 255;
    }

    public static long toUnsignedLong(int i) {
        return i & BodyPartID.bodyIdMax;
    }

    public static long toUnsignedLong(short s) {
        return s & 65535;
    }

    public static short toUnsignedShort(byte b) {
        return (short) (b & UByte.MAX_VALUE);
    }

    public static short toUnsignedShort(char c) {
        return (short) (c & VpRGBa.alphaDefault);
    }

    private SignednessUtil() {
        throw new Error("Do not instantiate");
    }

    public static ByteBuffer wrapUnsigned(byte[] bArr) {
        return ByteBuffer.wrap(bArr);
    }

    public static ByteBuffer wrapUnsigned(byte[] bArr, int i, int i2) {
        return ByteBuffer.wrap(bArr, i, i2);
    }

    public static int getUnsignedInt(ByteBuffer byteBuffer) {
        return byteBuffer.getInt();
    }

    public static short getUnsignedShort(ByteBuffer byteBuffer) {
        return byteBuffer.getShort();
    }

    public static byte getUnsigned(ByteBuffer byteBuffer) {
        return byteBuffer.get();
    }

    public static byte getUnsigned(ByteBuffer byteBuffer, int i) {
        return byteBuffer.get(i);
    }

    public static ByteBuffer getUnsigned(ByteBuffer byteBuffer, byte[] bArr, int i, int i2) {
        return byteBuffer.get(bArr, i, i2);
    }

    public static ByteBuffer putUnsigned(ByteBuffer byteBuffer, byte b) {
        return byteBuffer.put(b);
    }

    public static ByteBuffer putUnsigned(ByteBuffer byteBuffer, int i, byte b) {
        return byteBuffer.put(i, b);
    }

    public static IntBuffer putUnsigned(IntBuffer intBuffer, int i) {
        return intBuffer.put(i);
    }

    public static IntBuffer putUnsigned(IntBuffer intBuffer, int i, int i2) {
        return intBuffer.put(i, i2);
    }

    public static IntBuffer putUnsigned(IntBuffer intBuffer, int[] iArr) {
        return intBuffer.put(iArr);
    }

    public static IntBuffer putUnsigned(IntBuffer intBuffer, int[] iArr, int i, int i2) {
        return intBuffer.put(iArr, i, i2);
    }

    public static int getUnsigned(IntBuffer intBuffer, int i) {
        return intBuffer.get(i);
    }

    public static ByteBuffer putUnsignedShort(ByteBuffer byteBuffer, short s) {
        return byteBuffer.putShort(s);
    }

    public static ByteBuffer putUnsignedShort(ByteBuffer byteBuffer, int i, short s) {
        return byteBuffer.putShort(i, s);
    }

    public static ByteBuffer putUnsignedInt(ByteBuffer byteBuffer, int i) {
        return byteBuffer.putInt(i);
    }

    public static ByteBuffer putUnsignedInt(ByteBuffer byteBuffer, int i, int i2) {
        return byteBuffer.putInt(i, i2);
    }

    public static ByteBuffer putUnsignedLong(ByteBuffer byteBuffer, int i, long j) {
        return byteBuffer.putLong(i, j);
    }

    public static byte readUnsignedByte(RandomAccessFile randomAccessFile) throws IOException {
        return randomAccessFile.readByte();
    }

    public static char readUnsignedChar(RandomAccessFile randomAccessFile) throws IOException {
        return randomAccessFile.readChar();
    }

    public static short readUnsignedShort(RandomAccessFile randomAccessFile) throws IOException {
        return randomAccessFile.readShort();
    }

    public static int readUnsignedInt(RandomAccessFile randomAccessFile) throws IOException {
        return randomAccessFile.readInt();
    }

    public static long readUnsignedLong(RandomAccessFile randomAccessFile) throws IOException {
        return randomAccessFile.readLong();
    }

    public static int readUnsigned(RandomAccessFile randomAccessFile, byte[] bArr, int i, int i2) throws IOException {
        return randomAccessFile.read(bArr, i, i2);
    }

    public static void readFullyUnsigned(RandomAccessFile randomAccessFile, byte[] bArr) throws IOException {
        randomAccessFile.readFully(bArr);
    }

    public static void writeUnsigned(RandomAccessFile randomAccessFile, byte[] bArr, int i, int i2) throws IOException {
        randomAccessFile.write(bArr, i, i2);
    }

    public static void writeUnsignedByte(RandomAccessFile randomAccessFile, byte b) throws IOException {
        randomAccessFile.writeByte(toUnsignedInt(b));
    }

    public static void writeUnsignedChar(RandomAccessFile randomAccessFile, char c) throws IOException {
        randomAccessFile.writeChar(toUnsignedInt(c));
    }

    public static void writeUnsignedShort(RandomAccessFile randomAccessFile, short s) throws IOException {
        randomAccessFile.writeShort(toUnsignedInt(s));
    }

    public static void writeUnsignedInt(RandomAccessFile randomAccessFile, int i) throws IOException {
        randomAccessFile.writeInt(i);
    }

    public static void writeUnsignedLong(RandomAccessFile randomAccessFile, long j) throws IOException {
        randomAccessFile.writeLong(j);
    }

    public static void getUnsigned(ByteBuffer byteBuffer, byte[] bArr) {
        byteBuffer.get(bArr);
    }

    public static int compareUnsigned(long j, long j2) {
        return Long.compare(j - Long.MIN_VALUE, j2 - Long.MIN_VALUE);
    }

    public static int compareUnsigned(int i, int i2) {
        return Integer.compare(i - 2147483648, i2 - 2147483648);
    }

    public static int compareUnsigned(short s, short s2) {
        return compareUnsigned(toUnsignedInt(s), toUnsignedInt(s2));
    }

    public static int compareUnsigned(byte b, byte b2) {
        return compareUnsigned(toUnsignedInt(b), toUnsignedInt(b2));
    }

    public static String toUnsignedString(long j) {
        return toUnsignedBigInteger(j).toString();
    }

    public static String toUnsignedString(long j, int i) {
        return toUnsignedBigInteger(j).toString(i);
    }

    public static String toUnsignedString(int i) {
        return Long.toString(toUnsignedLong(i));
    }

    public static String toUnsignedString(int i, int i2) {
        return Long.toString(toUnsignedLong(i), i2);
    }

    public static String toUnsignedString(short s) {
        return Long.toString(toUnsignedLong(s));
    }

    public static String toUnsignedString(short s, int i) {
        return Long.toString(toUnsignedLong(s), i);
    }

    public static String toUnsignedString(byte b) {
        return Long.toString(toUnsignedLong(b));
    }

    public static String toUnsignedString(byte b, int i) {
        return Long.toString(toUnsignedLong(b), i);
    }

    private static BigInteger toUnsignedBigInteger(long j) {
        if (j >= 0) {
            return BigInteger.valueOf(j);
        }
        return BigInteger.valueOf(toUnsignedLong((int) (j >>> 32))).shiftLeft(32).add(BigInteger.valueOf(toUnsignedLong((int) j)));
    }

    public static float toFloat(byte b) {
        return toUnsignedBigInteger(toUnsignedLong(b)).floatValue();
    }

    public static float toFloat(short s) {
        return toUnsignedBigInteger(toUnsignedLong(s)).floatValue();
    }

    public static float toFloat(int i) {
        return toUnsignedBigInteger(toUnsignedLong(i)).floatValue();
    }

    public static float toFloat(long j) {
        return toUnsignedBigInteger(j).floatValue();
    }

    public static double toDouble(byte b) {
        return toUnsignedBigInteger(toUnsignedLong(b)).doubleValue();
    }

    public static double toDouble(short s) {
        return toUnsignedBigInteger(toUnsignedLong(s)).doubleValue();
    }

    public static double toDouble(int i) {
        return toUnsignedBigInteger(toUnsignedLong(i)).doubleValue();
    }

    public static double toDouble(long j) {
        return toUnsignedBigInteger(j).doubleValue();
    }
}
