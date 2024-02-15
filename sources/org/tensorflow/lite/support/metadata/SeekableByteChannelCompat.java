package org.tensorflow.lite.support.metadata;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;

/* loaded from: classes4.dex */
interface SeekableByteChannelCompat extends Channel {
    long position() throws IOException;

    SeekableByteChannelCompat position(long j) throws IOException;

    int read(ByteBuffer byteBuffer) throws IOException;

    long size() throws IOException;

    SeekableByteChannelCompat truncate(long j) throws IOException;

    int write(ByteBuffer byteBuffer) throws IOException;
}
