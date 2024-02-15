package org.apache.commons.imaging.formats.png;

import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes2.dex */
class PngCrc {
    private final long[] crc_table = new long[256];
    private boolean crc_table_computed;

    public final long finish_partial_crc(long j) {
        return j ^ BodyPartID.bodyIdMax;
    }

    private void make_crc_table() {
        for (int i = 0; i < 256; i++) {
            long j = i;
            for (int i2 = 0; i2 < 8; i2++) {
                j = (1 & j) != 0 ? (j >> 1) ^ 3988292384L : j >> 1;
            }
            this.crc_table[i] = j;
        }
        this.crc_table_computed = true;
    }

    private long update_crc(long j, byte[] bArr) {
        if (!this.crc_table_computed) {
            make_crc_table();
        }
        for (byte b : bArr) {
            j = (j >> 8) ^ this.crc_table[(int) ((b ^ j) & 255)];
        }
        return j;
    }

    public final int crc(byte[] bArr, int i) {
        return (int) (update_crc(BodyPartID.bodyIdMax, bArr) ^ BodyPartID.bodyIdMax);
    }

    public final long start_partial_crc(byte[] bArr, int i) {
        return update_crc(BodyPartID.bodyIdMax, bArr);
    }

    public final long continue_partial_crc(long j, byte[] bArr, int i) {
        return update_crc(j, bArr);
    }
}
