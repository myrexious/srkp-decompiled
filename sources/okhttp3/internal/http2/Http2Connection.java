package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Http2Reader;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

/* loaded from: classes2.dex */
public final class Http2Connection implements Closeable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int AWAIT_PING = 3;
    static final int DEGRADED_PING = 2;
    static final long DEGRADED_PONG_TIMEOUT_NS = 1000000000;
    static final int INTERVAL_PING = 1;
    static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
    private static final ExecutorService listenerExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Http2Connection", true));
    long bytesLeftInWriteWindow;
    final boolean client;
    final Set<Integer> currentPushRequests;
    final String hostname;
    int lastGoodStreamId;
    final Listener listener;
    int nextStreamId;
    final Settings peerSettings;
    private final ExecutorService pushExecutor;
    final PushObserver pushObserver;
    final ReaderRunnable readerRunnable;
    private boolean shutdown;
    final Socket socket;
    final Http2Writer writer;
    private final ScheduledExecutorService writerExecutor;
    final Map<Integer, Http2Stream> streams = new LinkedHashMap();
    private long intervalPingsSent = 0;
    private long intervalPongsReceived = 0;
    private long degradedPingsSent = 0;
    private long degradedPongsReceived = 0;
    private long awaitPingsSent = 0;
    private long awaitPongsReceived = 0;
    private long degradedPongDeadlineNs = 0;
    long unacknowledgedBytesRead = 0;
    Settings okHttpSettings = new Settings();

    /* loaded from: classes2.dex */
    public static abstract class Listener {
        public static final Listener REFUSE_INCOMING_STREAMS = new Listener() { // from class: okhttp3.internal.http2.Http2Connection.Listener.1
            @Override // okhttp3.internal.http2.Http2Connection.Listener
            public void onStream(Http2Stream http2Stream) throws IOException {
                http2Stream.close(ErrorCode.REFUSED_STREAM);
            }
        };

        public void onSettings(Http2Connection http2Connection) {
        }

        public abstract void onStream(Http2Stream http2Stream) throws IOException;
    }

    boolean pushedStream(int i) {
        return i != 0 && (i & 1) == 0;
    }

    static /* synthetic */ long access$108(Http2Connection http2Connection) {
        long j = http2Connection.intervalPongsReceived;
        http2Connection.intervalPongsReceived = 1 + j;
        return j;
    }

    static /* synthetic */ long access$208(Http2Connection http2Connection) {
        long j = http2Connection.intervalPingsSent;
        http2Connection.intervalPingsSent = 1 + j;
        return j;
    }

    static /* synthetic */ long access$608(Http2Connection http2Connection) {
        long j = http2Connection.degradedPongsReceived;
        http2Connection.degradedPongsReceived = 1 + j;
        return j;
    }

    static /* synthetic */ long access$708(Http2Connection http2Connection) {
        long j = http2Connection.awaitPongsReceived;
        http2Connection.awaitPongsReceived = 1 + j;
        return j;
    }

    Http2Connection(Builder builder) {
        Settings settings = new Settings();
        this.peerSettings = settings;
        this.currentPushRequests = new LinkedHashSet();
        this.pushObserver = builder.pushObserver;
        boolean z = builder.client;
        this.client = z;
        this.listener = builder.listener;
        this.nextStreamId = builder.client ? 1 : 2;
        if (builder.client) {
            this.nextStreamId += 2;
        }
        if (builder.client) {
            this.okHttpSettings.set(7, 16777216);
        }
        String str = builder.hostname;
        this.hostname = str;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(Util.format("OkHttp %s Writer", str), false));
        this.writerExecutor = scheduledThreadPoolExecutor;
        if (builder.pingIntervalMillis != 0) {
            scheduledThreadPoolExecutor.scheduleAtFixedRate(new IntervalPingRunnable(), builder.pingIntervalMillis, builder.pingIntervalMillis, TimeUnit.MILLISECONDS);
        }
        this.pushExecutor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory(Util.format("OkHttp %s Push Observer", str), true));
        settings.set(7, 65535);
        settings.set(5, 16384);
        this.bytesLeftInWriteWindow = settings.getInitialWindowSize();
        this.socket = builder.socket;
        this.writer = new Http2Writer(builder.sink, z);
        this.readerRunnable = new ReaderRunnable(new Http2Reader(builder.source, z));
    }

    public Protocol getProtocol() {
        return Protocol.HTTP_2;
    }

    public synchronized int openStreamCount() {
        return this.streams.size();
    }

    synchronized Http2Stream getStream(int i) {
        return this.streams.get(Integer.valueOf(i));
    }

    public synchronized Http2Stream removeStream(int i) {
        Http2Stream remove;
        remove = this.streams.remove(Integer.valueOf(i));
        notifyAll();
        return remove;
    }

    public synchronized int maxConcurrentStreams() {
        return this.peerSettings.getMaxConcurrentStreams(Integer.MAX_VALUE);
    }

    public synchronized void updateConnectionFlowControl(long j) {
        long j2 = this.unacknowledgedBytesRead + j;
        this.unacknowledgedBytesRead = j2;
        if (j2 >= this.okHttpSettings.getInitialWindowSize() / 2) {
            writeWindowUpdateLater(0, this.unacknowledgedBytesRead);
            this.unacknowledgedBytesRead = 0L;
        }
    }

    public Http2Stream pushStream(int i, List<Header> list, boolean z) throws IOException {
        if (this.client) {
            throw new IllegalStateException("Client cannot push requests.");
        }
        return newStream(i, list, z);
    }

    public Http2Stream newStream(List<Header> list, boolean z) throws IOException {
        return newStream(0, list, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x0041 A[Catch: all -> 0x0073, TryCatch #1 {, blocks: (B:47:0x0006, B:67:0x004d, B:71:0x005c, B:68:0x0053, B:70:0x0057, B:75:0x0065, B:76:0x006c, B:48:0x0007, B:50:0x000e, B:51:0x0013, B:53:0x0017, B:55:0x0029, B:57:0x0031, B:62:0x003b, B:64:0x0041, B:65:0x004a, B:77:0x006d, B:78:0x0072), top: B:85:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private okhttp3.internal.http2.Http2Stream newStream(int r11, java.util.List<okhttp3.internal.http2.Header> r12, boolean r13) throws java.io.IOException {
        /*
            r10 = this;
            r6 = r13 ^ 1
            r4 = 0
            okhttp3.internal.http2.Http2Writer r7 = r10.writer
            monitor-enter(r7)
            monitor-enter(r10)     // Catch: java.lang.Throwable -> L76
            int r0 = r10.nextStreamId     // Catch: java.lang.Throwable -> L73
            r1 = 1073741823(0x3fffffff, float:1.9999999)
            if (r0 <= r1) goto L13
            okhttp3.internal.http2.ErrorCode r0 = okhttp3.internal.http2.ErrorCode.REFUSED_STREAM     // Catch: java.lang.Throwable -> L73
            r10.shutdown(r0)     // Catch: java.lang.Throwable -> L73
        L13:
            boolean r0 = r10.shutdown     // Catch: java.lang.Throwable -> L73
            if (r0 != 0) goto L6d
            int r8 = r10.nextStreamId     // Catch: java.lang.Throwable -> L73
            int r0 = r8 + 2
            r10.nextStreamId = r0     // Catch: java.lang.Throwable -> L73
            okhttp3.internal.http2.Http2Stream r9 = new okhttp3.internal.http2.Http2Stream     // Catch: java.lang.Throwable -> L73
            r5 = 0
            r0 = r9
            r1 = r8
            r2 = r10
            r3 = r6
            r0.<init>(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L73
            if (r13 == 0) goto L3a
            long r0 = r10.bytesLeftInWriteWindow     // Catch: java.lang.Throwable -> L73
            r2 = 0
            int r13 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r13 == 0) goto L3a
            long r0 = r9.bytesLeftInWriteWindow     // Catch: java.lang.Throwable -> L73
            int r13 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r13 != 0) goto L38
            goto L3a
        L38:
            r13 = 0
            goto L3b
        L3a:
            r13 = 1
        L3b:
            boolean r0 = r9.isOpen()     // Catch: java.lang.Throwable -> L73
            if (r0 == 0) goto L4a
            java.util.Map<java.lang.Integer, okhttp3.internal.http2.Http2Stream> r0 = r10.streams     // Catch: java.lang.Throwable -> L73
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L73
            r0.put(r1, r9)     // Catch: java.lang.Throwable -> L73
        L4a:
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L73
            if (r11 != 0) goto L53
            okhttp3.internal.http2.Http2Writer r0 = r10.writer     // Catch: java.lang.Throwable -> L76
            r0.synStream(r6, r8, r11, r12)     // Catch: java.lang.Throwable -> L76
            goto L5c
        L53:
            boolean r0 = r10.client     // Catch: java.lang.Throwable -> L76
            if (r0 != 0) goto L65
            okhttp3.internal.http2.Http2Writer r0 = r10.writer     // Catch: java.lang.Throwable -> L76
            r0.pushPromise(r11, r8, r12)     // Catch: java.lang.Throwable -> L76
        L5c:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L76
            if (r13 == 0) goto L64
            okhttp3.internal.http2.Http2Writer r11 = r10.writer
            r11.flush()
        L64:
            return r9
        L65:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> L76
            java.lang.String r12 = "client streams shouldn't have associated stream IDs"
            r11.<init>(r12)     // Catch: java.lang.Throwable -> L76
            throw r11     // Catch: java.lang.Throwable -> L76
        L6d:
            okhttp3.internal.http2.ConnectionShutdownException r11 = new okhttp3.internal.http2.ConnectionShutdownException     // Catch: java.lang.Throwable -> L73
            r11.<init>()     // Catch: java.lang.Throwable -> L73
            throw r11     // Catch: java.lang.Throwable -> L73
        L73:
            r11 = move-exception
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L73
            throw r11     // Catch: java.lang.Throwable -> L76
        L76:
            r11 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L76
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.newStream(int, java.util.List, boolean):okhttp3.internal.http2.Http2Stream");
    }

    public void writeSynReply(int i, boolean z, List<Header> list) throws IOException {
        this.writer.synReply(z, i, list);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x002f, code lost:
        throw new java.io.IOException("stream closed");
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0030, code lost:
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r4), r8.writer.maxDataLength());
        r6 = r2;
        r8.bytesLeftInWriteWindow -= r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void writeData(int r9, boolean r10, okio.Buffer r11, long r12) throws java.io.IOException {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto Ld
            okhttp3.internal.http2.Http2Writer r12 = r8.writer
            r12.data(r10, r9, r11, r3)
            return
        Ld:
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 <= 0) goto L67
            monitor-enter(r8)
        L12:
            long r4 = r8.bytesLeftInWriteWindow     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 > 0) goto L30
            java.util.Map<java.lang.Integer, okhttp3.internal.http2.Http2Stream> r2 = r8.streams     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            boolean r2 = r2.containsKey(r4)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            if (r2 == 0) goto L28
            r8.wait()     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            goto L12
        L28:
            java.io.IOException r9 = new java.io.IOException     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            throw r9     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
        L30:
            long r4 = java.lang.Math.min(r12, r4)     // Catch: java.lang.Throwable -> L56
            int r2 = (int) r4     // Catch: java.lang.Throwable -> L56
            okhttp3.internal.http2.Http2Writer r4 = r8.writer     // Catch: java.lang.Throwable -> L56
            int r4 = r4.maxDataLength()     // Catch: java.lang.Throwable -> L56
            int r2 = java.lang.Math.min(r2, r4)     // Catch: java.lang.Throwable -> L56
            long r4 = r8.bytesLeftInWriteWindow     // Catch: java.lang.Throwable -> L56
            long r6 = (long) r2     // Catch: java.lang.Throwable -> L56
            long r4 = r4 - r6
            r8.bytesLeftInWriteWindow = r4     // Catch: java.lang.Throwable -> L56
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
            long r12 = r12 - r6
            okhttp3.internal.http2.Http2Writer r4 = r8.writer
            if (r10 == 0) goto L51
            int r5 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r5 != 0) goto L51
            r5 = 1
            goto L52
        L51:
            r5 = r3
        L52:
            r4.data(r5, r9, r11, r2)
            goto Ld
        L56:
            r9 = move-exception
            goto L65
        L58:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L56
            r9.interrupt()     // Catch: java.lang.Throwable -> L56
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L56
            r9.<init>()     // Catch: java.lang.Throwable -> L56
            throw r9     // Catch: java.lang.Throwable -> L56
        L65:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
            throw r9
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.writeData(int, boolean, okio.Buffer, long):void");
    }

    public void writeSynResetLater(final int i, final ErrorCode errorCode) {
        try {
            this.writerExecutor.execute(new NamedRunnable("OkHttp %s stream %d", new Object[]{this.hostname, Integer.valueOf(i)}) { // from class: okhttp3.internal.http2.Http2Connection.1
                @Override // okhttp3.internal.NamedRunnable
                public void execute() {
                    try {
                        Http2Connection.this.writeSynReset(i, errorCode);
                    } catch (IOException unused) {
                        Http2Connection.this.failConnection();
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }

    public void writeSynReset(int i, ErrorCode errorCode) throws IOException {
        this.writer.rstStream(i, errorCode);
    }

    public void writeWindowUpdateLater(final int i, final long j) {
        try {
            this.writerExecutor.execute(new NamedRunnable("OkHttp Window Update %s stream %d", new Object[]{this.hostname, Integer.valueOf(i)}) { // from class: okhttp3.internal.http2.Http2Connection.2
                @Override // okhttp3.internal.NamedRunnable
                public void execute() {
                    try {
                        Http2Connection.this.writer.windowUpdate(i, j);
                    } catch (IOException unused) {
                        Http2Connection.this.failConnection();
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }

    /* loaded from: classes2.dex */
    final class PingRunnable extends NamedRunnable {
        final int payload1;
        final int payload2;
        final boolean reply;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        PingRunnable(boolean z, int i, int i2) {
            super("OkHttp %s ping %08x%08x", r3.hostname, Integer.valueOf(i), Integer.valueOf(i2));
            Http2Connection.this = r3;
            this.reply = z;
            this.payload1 = i;
            this.payload2 = i2;
        }

        @Override // okhttp3.internal.NamedRunnable
        public void execute() {
            Http2Connection.this.writePing(this.reply, this.payload1, this.payload2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class IntervalPingRunnable extends NamedRunnable {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        IntervalPingRunnable() {
            super("OkHttp %s ping", r3.hostname);
            Http2Connection.this = r3;
        }

        @Override // okhttp3.internal.NamedRunnable
        public void execute() {
            boolean z;
            synchronized (Http2Connection.this) {
                if (Http2Connection.this.intervalPongsReceived < Http2Connection.this.intervalPingsSent) {
                    z = true;
                } else {
                    Http2Connection.access$208(Http2Connection.this);
                    z = false;
                }
            }
            if (z) {
                Http2Connection.this.failConnection();
            } else {
                Http2Connection.this.writePing(false, 1, 0);
            }
        }
    }

    void writePing(boolean z, int i, int i2) {
        try {
            this.writer.ping(z, i, i2);
        } catch (IOException unused) {
            failConnection();
        }
    }

    void writePingAndAwaitPong() throws InterruptedException {
        writePing();
        awaitPong();
    }

    void writePing() {
        synchronized (this) {
            this.awaitPingsSent++;
        }
        writePing(false, 3, 1330343787);
    }

    synchronized void awaitPong() throws InterruptedException {
        while (this.awaitPongsReceived < this.awaitPingsSent) {
            wait();
        }
    }

    public void flush() throws IOException {
        this.writer.flush();
    }

    public void shutdown(ErrorCode errorCode) throws IOException {
        synchronized (this.writer) {
            synchronized (this) {
                if (this.shutdown) {
                    return;
                }
                this.shutdown = true;
                this.writer.goAway(this.lastGoodStreamId, errorCode, Util.EMPTY_BYTE_ARRAY);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    void close(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        Http2Stream[] http2StreamArr = null;
        try {
            shutdown(errorCode);
            e = null;
        } catch (IOException e) {
            e = e;
        }
        synchronized (this) {
            if (!this.streams.isEmpty()) {
                http2StreamArr = (Http2Stream[]) this.streams.values().toArray(new Http2Stream[this.streams.size()]);
                this.streams.clear();
            }
        }
        if (http2StreamArr != null) {
            for (Http2Stream http2Stream : http2StreamArr) {
                try {
                    http2Stream.close(errorCode2);
                } catch (IOException e2) {
                    if (e != null) {
                        e = e2;
                    }
                }
            }
        }
        try {
            this.writer.close();
        } catch (IOException e3) {
            if (e == null) {
                e = e3;
            }
        }
        try {
            this.socket.close();
        } catch (IOException e4) {
            e = e4;
        }
        this.writerExecutor.shutdown();
        this.pushExecutor.shutdown();
        if (e != null) {
            throw e;
        }
    }

    public void failConnection() {
        try {
            close(ErrorCode.PROTOCOL_ERROR, ErrorCode.PROTOCOL_ERROR);
        } catch (IOException unused) {
        }
    }

    public void start() throws IOException {
        start(true);
    }

    void start(boolean z) throws IOException {
        if (z) {
            this.writer.connectionPreface();
            this.writer.settings(this.okHttpSettings);
            int initialWindowSize = this.okHttpSettings.getInitialWindowSize();
            if (initialWindowSize != 65535) {
                this.writer.windowUpdate(0, initialWindowSize - 65535);
            }
        }
        new Thread(this.readerRunnable).start();
    }

    public void setSettings(Settings settings) throws IOException {
        synchronized (this.writer) {
            synchronized (this) {
                if (this.shutdown) {
                    throw new ConnectionShutdownException();
                }
                this.okHttpSettings.merge(settings);
            }
            this.writer.settings(settings);
        }
    }

    public synchronized boolean isHealthy(long j) {
        if (this.shutdown) {
            return false;
        }
        if (this.degradedPongsReceived < this.degradedPingsSent) {
            if (j >= this.degradedPongDeadlineNs) {
                return false;
            }
        }
        return true;
    }

    public void sendDegradedPingLater() {
        synchronized (this) {
            long j = this.degradedPongsReceived;
            long j2 = this.degradedPingsSent;
            if (j < j2) {
                return;
            }
            this.degradedPingsSent = j2 + 1;
            this.degradedPongDeadlineNs = System.nanoTime() + DEGRADED_PONG_TIMEOUT_NS;
            try {
                this.writerExecutor.execute(new NamedRunnable("OkHttp %s ping", this.hostname) { // from class: okhttp3.internal.http2.Http2Connection.3
                    @Override // okhttp3.internal.NamedRunnable
                    public void execute() {
                        Http2Connection.this.writePing(false, 2, 0);
                    }
                });
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        boolean client;
        String hostname;
        int pingIntervalMillis;
        BufferedSink sink;
        Socket socket;
        BufferedSource source;
        Listener listener = Listener.REFUSE_INCOMING_STREAMS;
        PushObserver pushObserver = PushObserver.CANCEL;

        public Builder(boolean z) {
            this.client = z;
        }

        public Builder socket(Socket socket) throws IOException {
            return socket(socket, ((InetSocketAddress) socket.getRemoteSocketAddress()).getHostName(), Okio.buffer(Okio.source(socket)), Okio.buffer(Okio.sink(socket)));
        }

        public Builder socket(Socket socket, String str, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.socket = socket;
            this.hostname = str;
            this.source = bufferedSource;
            this.sink = bufferedSink;
            return this;
        }

        public Builder listener(Listener listener) {
            this.listener = listener;
            return this;
        }

        public Builder pushObserver(PushObserver pushObserver) {
            this.pushObserver = pushObserver;
            return this;
        }

        public Builder pingIntervalMillis(int i) {
            this.pingIntervalMillis = i;
            return this;
        }

        public Http2Connection build() {
            return new Http2Connection(this);
        }
    }

    /* loaded from: classes2.dex */
    public class ReaderRunnable extends NamedRunnable implements Http2Reader.Handler {
        final Http2Reader reader;

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void ackSettings() {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void alternateService(int i, String str, ByteString byteString, String str2, int i2, long j) {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void priority(int i, int i2, int i3, boolean z) {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ReaderRunnable(Http2Reader http2Reader) {
            super("OkHttp %s", r3.hostname);
            Http2Connection.this = r3;
            this.reader = http2Reader;
        }

        @Override // okhttp3.internal.NamedRunnable
        protected void execute() {
            Http2Connection http2Connection;
            ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            try {
                try {
                    try {
                        this.reader.readConnectionPreface(this);
                        while (this.reader.nextFrame(false, this)) {
                        }
                        errorCode = ErrorCode.NO_ERROR;
                        errorCode2 = ErrorCode.CANCEL;
                        http2Connection = Http2Connection.this;
                    } catch (IOException unused) {
                        errorCode = ErrorCode.PROTOCOL_ERROR;
                        errorCode2 = ErrorCode.PROTOCOL_ERROR;
                        http2Connection = Http2Connection.this;
                    }
                    http2Connection.close(errorCode, errorCode2);
                } catch (IOException unused2) {
                }
                Util.closeQuietly(this.reader);
            } catch (Throwable th) {
                try {
                    Http2Connection.this.close(errorCode, errorCode2);
                } catch (IOException unused3) {
                }
                Util.closeQuietly(this.reader);
                throw th;
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void data(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException {
            if (Http2Connection.this.pushedStream(i)) {
                Http2Connection.this.pushDataLater(i, bufferedSource, i2, z);
                return;
            }
            Http2Stream stream = Http2Connection.this.getStream(i);
            if (stream == null) {
                Http2Connection.this.writeSynResetLater(i, ErrorCode.PROTOCOL_ERROR);
                long j = i2;
                Http2Connection.this.updateConnectionFlowControl(j);
                bufferedSource.skip(j);
                return;
            }
            stream.receiveData(bufferedSource, i2);
            if (z) {
                stream.receiveFin();
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void headers(boolean z, int i, int i2, List<Header> list) {
            if (Http2Connection.this.pushedStream(i)) {
                Http2Connection.this.pushHeadersLater(i, list, z);
                return;
            }
            synchronized (Http2Connection.this) {
                Http2Stream stream = Http2Connection.this.getStream(i);
                if (stream == null) {
                    if (Http2Connection.this.shutdown) {
                        return;
                    }
                    if (i <= Http2Connection.this.lastGoodStreamId) {
                        return;
                    }
                    if (i % 2 == Http2Connection.this.nextStreamId % 2) {
                        return;
                    }
                    final Http2Stream http2Stream = new Http2Stream(i, Http2Connection.this, false, z, Util.toHeaders(list));
                    Http2Connection.this.lastGoodStreamId = i;
                    Http2Connection.this.streams.put(Integer.valueOf(i), http2Stream);
                    Http2Connection.listenerExecutor.execute(new NamedRunnable("OkHttp %s stream %d", new Object[]{Http2Connection.this.hostname, Integer.valueOf(i)}) { // from class: okhttp3.internal.http2.Http2Connection.ReaderRunnable.1
                        @Override // okhttp3.internal.NamedRunnable
                        public void execute() {
                            try {
                                Http2Connection.this.listener.onStream(http2Stream);
                            } catch (IOException e) {
                                Platform.get().log(4, "Http2Connection.Listener failure for " + Http2Connection.this.hostname, e);
                                try {
                                    http2Stream.close(ErrorCode.PROTOCOL_ERROR);
                                } catch (IOException unused) {
                                }
                            }
                        }
                    });
                    return;
                }
                stream.receiveHeaders(list);
                if (z) {
                    stream.receiveFin();
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void rstStream(int i, ErrorCode errorCode) {
            if (Http2Connection.this.pushedStream(i)) {
                Http2Connection.this.pushResetLater(i, errorCode);
                return;
            }
            Http2Stream removeStream = Http2Connection.this.removeStream(i);
            if (removeStream != null) {
                removeStream.receiveRstStream(errorCode);
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void settings(final boolean z, final Settings settings) {
            try {
                Http2Connection.this.writerExecutor.execute(new NamedRunnable("OkHttp %s ACK Settings", new Object[]{Http2Connection.this.hostname}) { // from class: okhttp3.internal.http2.Http2Connection.ReaderRunnable.2
                    @Override // okhttp3.internal.NamedRunnable
                    public void execute() {
                        ReaderRunnable.this.applyAndAckSettings(z, settings);
                    }
                });
            } catch (RejectedExecutionException unused) {
            }
        }

        void applyAndAckSettings(boolean z, Settings settings) {
            Http2Stream[] http2StreamArr;
            long j;
            synchronized (Http2Connection.this.writer) {
                synchronized (Http2Connection.this) {
                    int initialWindowSize = Http2Connection.this.peerSettings.getInitialWindowSize();
                    if (z) {
                        Http2Connection.this.peerSettings.clear();
                    }
                    Http2Connection.this.peerSettings.merge(settings);
                    int initialWindowSize2 = Http2Connection.this.peerSettings.getInitialWindowSize();
                    http2StreamArr = null;
                    if (initialWindowSize2 == -1 || initialWindowSize2 == initialWindowSize) {
                        j = 0;
                    } else {
                        j = initialWindowSize2 - initialWindowSize;
                        if (!Http2Connection.this.streams.isEmpty()) {
                            http2StreamArr = (Http2Stream[]) Http2Connection.this.streams.values().toArray(new Http2Stream[Http2Connection.this.streams.size()]);
                        }
                    }
                }
                try {
                    Http2Connection.this.writer.applyAndAckSettings(Http2Connection.this.peerSettings);
                } catch (IOException unused) {
                    Http2Connection.this.failConnection();
                }
            }
            if (http2StreamArr != null) {
                for (Http2Stream http2Stream : http2StreamArr) {
                    synchronized (http2Stream) {
                        http2Stream.addBytesToWriteWindow(j);
                    }
                }
            }
            Http2Connection.listenerExecutor.execute(new NamedRunnable("OkHttp %s settings", Http2Connection.this.hostname) { // from class: okhttp3.internal.http2.Http2Connection.ReaderRunnable.3
                @Override // okhttp3.internal.NamedRunnable
                public void execute() {
                    Http2Connection.this.listener.onSettings(Http2Connection.this);
                }
            });
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void ping(boolean z, int i, int i2) {
            if (!z) {
                try {
                    Http2Connection.this.writerExecutor.execute(new PingRunnable(true, i, i2));
                    return;
                } catch (RejectedExecutionException unused) {
                    return;
                }
            }
            synchronized (Http2Connection.this) {
                try {
                    if (i == 1) {
                        Http2Connection.access$108(Http2Connection.this);
                    } else if (i == 2) {
                        Http2Connection.access$608(Http2Connection.this);
                    } else if (i == 3) {
                        Http2Connection.access$708(Http2Connection.this);
                        Http2Connection.this.notifyAll();
                    }
                } finally {
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void goAway(int i, ErrorCode errorCode, ByteString byteString) {
            Http2Stream[] http2StreamArr;
            byteString.size();
            synchronized (Http2Connection.this) {
                http2StreamArr = (Http2Stream[]) Http2Connection.this.streams.values().toArray(new Http2Stream[Http2Connection.this.streams.size()]);
                Http2Connection.this.shutdown = true;
            }
            for (Http2Stream http2Stream : http2StreamArr) {
                if (http2Stream.getId() > i && http2Stream.isLocallyInitiated()) {
                    http2Stream.receiveRstStream(ErrorCode.REFUSED_STREAM);
                    Http2Connection.this.removeStream(http2Stream.getId());
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void windowUpdate(int i, long j) {
            if (i == 0) {
                synchronized (Http2Connection.this) {
                    Http2Connection.this.bytesLeftInWriteWindow += j;
                    Http2Connection.this.notifyAll();
                }
                return;
            }
            Http2Stream stream = Http2Connection.this.getStream(i);
            if (stream != null) {
                synchronized (stream) {
                    stream.addBytesToWriteWindow(j);
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void pushPromise(int i, int i2, List<Header> list) {
            Http2Connection.this.pushRequestLater(i2, list);
        }
    }

    void pushRequestLater(final int i, final List<Header> list) {
        synchronized (this) {
            if (this.currentPushRequests.contains(Integer.valueOf(i))) {
                writeSynResetLater(i, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.currentPushRequests.add(Integer.valueOf(i));
            try {
                pushExecutorExecute(new NamedRunnable("OkHttp %s Push Request[%s]", new Object[]{this.hostname, Integer.valueOf(i)}) { // from class: okhttp3.internal.http2.Http2Connection.4
                    @Override // okhttp3.internal.NamedRunnable
                    public void execute() {
                        if (Http2Connection.this.pushObserver.onRequest(i, list)) {
                            try {
                                Http2Connection.this.writer.rstStream(i, ErrorCode.CANCEL);
                                synchronized (Http2Connection.this) {
                                    Http2Connection.this.currentPushRequests.remove(Integer.valueOf(i));
                                }
                            } catch (IOException unused) {
                            }
                        }
                    }
                });
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    void pushHeadersLater(final int i, final List<Header> list, final boolean z) {
        try {
            pushExecutorExecute(new NamedRunnable("OkHttp %s Push Headers[%s]", new Object[]{this.hostname, Integer.valueOf(i)}) { // from class: okhttp3.internal.http2.Http2Connection.5
                @Override // okhttp3.internal.NamedRunnable
                public void execute() {
                    boolean onHeaders = Http2Connection.this.pushObserver.onHeaders(i, list, z);
                    if (onHeaders) {
                        try {
                            Http2Connection.this.writer.rstStream(i, ErrorCode.CANCEL);
                        } catch (IOException unused) {
                            return;
                        }
                    }
                    if (onHeaders || z) {
                        synchronized (Http2Connection.this) {
                            Http2Connection.this.currentPushRequests.remove(Integer.valueOf(i));
                        }
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }

    void pushDataLater(final int i, BufferedSource bufferedSource, final int i2, final boolean z) throws IOException {
        final Buffer buffer = new Buffer();
        long j = i2;
        bufferedSource.require(j);
        bufferedSource.read(buffer, j);
        if (buffer.size() != j) {
            throw new IOException(buffer.size() + " != " + i2);
        }
        pushExecutorExecute(new NamedRunnable("OkHttp %s Push Data[%s]", new Object[]{this.hostname, Integer.valueOf(i)}) { // from class: okhttp3.internal.http2.Http2Connection.6
            @Override // okhttp3.internal.NamedRunnable
            public void execute() {
                try {
                    boolean onData = Http2Connection.this.pushObserver.onData(i, buffer, i2, z);
                    if (onData) {
                        Http2Connection.this.writer.rstStream(i, ErrorCode.CANCEL);
                    }
                    if (onData || z) {
                        synchronized (Http2Connection.this) {
                            Http2Connection.this.currentPushRequests.remove(Integer.valueOf(i));
                        }
                    }
                } catch (IOException unused) {
                }
            }
        });
    }

    void pushResetLater(final int i, final ErrorCode errorCode) {
        pushExecutorExecute(new NamedRunnable("OkHttp %s Push Reset[%s]", new Object[]{this.hostname, Integer.valueOf(i)}) { // from class: okhttp3.internal.http2.Http2Connection.7
            @Override // okhttp3.internal.NamedRunnable
            public void execute() {
                Http2Connection.this.pushObserver.onReset(i, errorCode);
                synchronized (Http2Connection.this) {
                    Http2Connection.this.currentPushRequests.remove(Integer.valueOf(i));
                }
            }
        });
    }

    private synchronized void pushExecutorExecute(NamedRunnable namedRunnable) {
        if (!this.shutdown) {
            this.pushExecutor.execute(namedRunnable);
        }
    }
}
