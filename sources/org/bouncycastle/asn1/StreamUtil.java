package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/* loaded from: classes2.dex */
public class StreamUtil {
    StreamUtil() {
    }

    public static int findLimit(InputStream inputStream) {
        if (inputStream instanceof LimitedInputStream) {
            return ((LimitedInputStream) inputStream).getLimit();
        }
        if (inputStream instanceof ASN1InputStream) {
            return ((ASN1InputStream) inputStream).getLimit();
        }
        if (inputStream instanceof ByteArrayInputStream) {
            return ((ByteArrayInputStream) inputStream).available();
        }
        if (inputStream instanceof FileInputStream) {
            try {
                FileChannel channel = ((FileInputStream) inputStream).getChannel();
                long size = channel != null ? channel.size() : 2147483647L;
                if (size < 2147483647L) {
                    return (int) size;
                }
            } catch (IOException unused) {
            }
        }
        long maxMemory = Runtime.getRuntime().maxMemory();
        if (maxMemory > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) maxMemory;
    }
}