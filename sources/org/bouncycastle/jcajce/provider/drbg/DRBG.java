package org.bouncycastle.jcajce.provider.drbg;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.security.Security;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.EntropySourceProvider;
import org.bouncycastle.crypto.prng.SP800SecureRandom;
import org.bouncycastle.crypto.prng.SP800SecureRandomBuilder;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.ClassUtil;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Properties;
import org.bouncycastle.util.Strings;

/* loaded from: classes2.dex */
public class DRBG {
    private static final String PREFIX = "org.bouncycastle.jcajce.provider.drbg.DRBG";
    private static EntropyDaemon entropyDaemon;
    private static Thread entropyThread;
    private static final String[][] initialEntropySourceNames = {new String[]{"sun.security.provider.Sun", "sun.security.provider.SecureRandom"}, new String[]{"org.apache.harmony.security.provider.crypto.CryptoProvider", "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl"}, new String[]{"com.android.org.conscrypt.OpenSSLProvider", "com.android.org.conscrypt.OpenSSLRandom"}, new String[]{"org.conscrypt.OpenSSLProvider", "org.conscrypt.OpenSSLRandom"}};

    /* loaded from: classes2.dex */
    public static class CoreSecureRandom extends SecureRandom {
        CoreSecureRandom(Object[] objArr) {
            super((SecureRandomSpi) objArr[1], (Provider) objArr[0]);
        }
    }

    /* loaded from: classes2.dex */
    public static class Default extends SecureRandomSpi {
        private static final SecureRandom random = DRBG.createBaseRandom(true);

        @Override // java.security.SecureRandomSpi
        protected byte[] engineGenerateSeed(int i) {
            return random.generateSeed(i);
        }

        @Override // java.security.SecureRandomSpi
        protected void engineNextBytes(byte[] bArr) {
            random.nextBytes(bArr);
        }

        @Override // java.security.SecureRandomSpi
        protected void engineSetSeed(byte[] bArr) {
            random.setSeed(bArr);
        }
    }

    /* loaded from: classes2.dex */
    public static class EntropyDaemon implements Runnable {
        private final ConcurrentLinkedDeque<Runnable> tasks;

        private EntropyDaemon() {
            this.tasks = new ConcurrentLinkedDeque<>();
        }

        void addTask(Runnable runnable) {
            this.tasks.add(runnable);
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                Runnable pollFirst = this.tasks.pollFirst();
                if (pollFirst != null) {
                    try {
                        pollFirst.run();
                    } catch (Throwable unused) {
                    }
                } else {
                    try {
                        Thread.sleep(5000L);
                    } catch (InterruptedException unused2) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class HybridEntropySource implements EntropySource {
        private final byte[] additionalInput;
        private final int bytesRequired;
        private final SP800SecureRandom drbg;
        private final SignallingEntropySource entropySource;
        private final AtomicInteger samples;
        private final AtomicBoolean seedAvailable;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public class SignallingEntropySource implements IncrementalEntropySource {
            private final int byteLength;
            private final EntropyDaemon entropyDaemon;
            private final IncrementalEntropySource entropySource;
            private final AtomicBoolean seedAvailable;
            private final AtomicReference entropy = new AtomicReference();
            private final AtomicBoolean scheduled = new AtomicBoolean(false);
            private final long pause = DRBG.access$1000();

            /* loaded from: classes2.dex */
            public class EntropyGatherer implements Runnable {
                private final IncrementalEntropySource baseRandom;

                EntropyGatherer(IncrementalEntropySource incrementalEntropySource) {
                    SignallingEntropySource.this = r1;
                    this.baseRandom = incrementalEntropySource;
                }

                @Override // java.lang.Runnable
                public void run() {
                    SignallingEntropySource.this.entropy.set(this.baseRandom.getEntropy(SignallingEntropySource.this.pause));
                    SignallingEntropySource.this.seedAvailable.set(true);
                }
            }

            SignallingEntropySource(EntropyDaemon entropyDaemon, AtomicBoolean atomicBoolean, EntropySourceProvider entropySourceProvider, int i) {
                HybridEntropySource.this = r2;
                this.entropyDaemon = entropyDaemon;
                this.seedAvailable = atomicBoolean;
                this.entropySource = (IncrementalEntropySource) entropySourceProvider.get(i);
                this.byteLength = (i + 7) / 8;
            }

            @Override // org.bouncycastle.crypto.prng.EntropySource
            public int entropySize() {
                return this.byteLength * 8;
            }

            @Override // org.bouncycastle.crypto.prng.EntropySource
            public byte[] getEntropy() {
                return getEntropy(0L);
            }

            @Override // org.bouncycastle.jcajce.provider.drbg.DRBG.IncrementalEntropySource
            public byte[] getEntropy(long j) {
                byte[] bArr = (byte[]) this.entropy.getAndSet(null);
                if (bArr == null || bArr.length != this.byteLength) {
                    bArr = this.entropySource.getEntropy(j);
                } else {
                    this.scheduled.set(false);
                }
                schedule();
                return bArr;
            }

            @Override // org.bouncycastle.crypto.prng.EntropySource
            public boolean isPredictionResistant() {
                return true;
            }

            void schedule() {
                if (this.scheduled.getAndSet(true)) {
                    return;
                }
                this.entropyDaemon.addTask(new EntropyGatherer(this.entropySource));
            }
        }

        HybridEntropySource(EntropyDaemon entropyDaemon, int i) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            this.seedAvailable = atomicBoolean;
            this.samples = new AtomicInteger(0);
            this.additionalInput = Pack.longToBigEndian(System.currentTimeMillis());
            EntropySourceProvider access$800 = DRBG.access$800();
            this.bytesRequired = (i + 7) / 8;
            SignallingEntropySource signallingEntropySource = new SignallingEntropySource(entropyDaemon, atomicBoolean, access$800, 256);
            this.entropySource = signallingEntropySource;
            this.drbg = new SP800SecureRandomBuilder(new EntropySourceProvider() { // from class: org.bouncycastle.jcajce.provider.drbg.DRBG.HybridEntropySource.1
                @Override // org.bouncycastle.crypto.prng.EntropySourceProvider
                public EntropySource get(int i2) {
                    return HybridEntropySource.this.entropySource;
                }
            }).setPersonalizationString(Strings.toByteArray("Bouncy Castle Hybrid Entropy Source")).buildHMAC(new HMac(new SHA512Digest()), signallingEntropySource.getEntropy(), false);
        }

        @Override // org.bouncycastle.crypto.prng.EntropySource
        public int entropySize() {
            return this.bytesRequired * 8;
        }

        @Override // org.bouncycastle.crypto.prng.EntropySource
        public byte[] getEntropy() {
            byte[] bArr = new byte[this.bytesRequired];
            if (this.samples.getAndIncrement() > 20) {
                if (this.seedAvailable.getAndSet(false)) {
                    this.samples.set(0);
                    this.drbg.reseed(this.additionalInput);
                } else {
                    this.entropySource.schedule();
                }
            }
            this.drbg.nextBytes(bArr);
            return bArr;
        }

        @Override // org.bouncycastle.crypto.prng.EntropySource
        public boolean isPredictionResistant() {
            return true;
        }
    }

    /* loaded from: classes2.dex */
    public interface IncrementalEntropySource extends EntropySource {
        byte[] getEntropy(long j);
    }

    /* loaded from: classes2.dex */
    public static class IncrementalEntropySourceProvider implements EntropySourceProvider {
        private final boolean predictionResistant;
        private final SecureRandom random;

        public IncrementalEntropySourceProvider(SecureRandom secureRandom, boolean z) {
            this.random = secureRandom;
            this.predictionResistant = z;
        }

        @Override // org.bouncycastle.crypto.prng.EntropySourceProvider
        public EntropySource get(int i) {
            return new IncrementalEntropySource(i) { // from class: org.bouncycastle.jcajce.provider.drbg.DRBG.IncrementalEntropySourceProvider.1
                final int numBytes;
                final /* synthetic */ int val$bitsRequired;

                {
                    IncrementalEntropySourceProvider.this = this;
                    this.val$bitsRequired = i;
                    this.numBytes = (i + 7) / 8;
                }

                @Override // org.bouncycastle.crypto.prng.EntropySource
                public int entropySize() {
                    return this.val$bitsRequired;
                }

                @Override // org.bouncycastle.crypto.prng.EntropySource
                public byte[] getEntropy() {
                    return getEntropy(0L);
                }

                @Override // org.bouncycastle.jcajce.provider.drbg.DRBG.IncrementalEntropySource
                public byte[] getEntropy(long j) {
                    int i2;
                    int i3 = this.numBytes;
                    byte[] bArr = new byte[i3];
                    int i4 = 0;
                    while (true) {
                        i2 = this.numBytes;
                        if (i4 >= i2 / 8) {
                            break;
                        }
                        DRBG.sleep(j);
                        byte[] generateSeed = IncrementalEntropySourceProvider.this.random.generateSeed(8);
                        System.arraycopy(generateSeed, 0, bArr, i4 * 8, generateSeed.length);
                        i4++;
                    }
                    int i5 = i2 - ((i2 / 8) * 8);
                    if (i5 != 0) {
                        DRBG.sleep(j);
                        byte[] generateSeed2 = IncrementalEntropySourceProvider.this.random.generateSeed(i5);
                        System.arraycopy(generateSeed2, 0, bArr, i3 - generateSeed2.length, generateSeed2.length);
                    }
                    return bArr;
                }

                @Override // org.bouncycastle.crypto.prng.EntropySource
                public boolean isPredictionResistant() {
                    return IncrementalEntropySourceProvider.this.predictionResistant;
                }
            };
        }
    }

    /* loaded from: classes2.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("SecureRandom.DEFAULT", DRBG.PREFIX + "$Default");
            configurableProvider.addAlgorithm("SecureRandom.NONCEANDIV", DRBG.PREFIX + "$NonceAndIV");
        }
    }

    /* loaded from: classes2.dex */
    public static class NonceAndIV extends SecureRandomSpi {
        private static final SecureRandom random = DRBG.createBaseRandom(false);

        @Override // java.security.SecureRandomSpi
        protected byte[] engineGenerateSeed(int i) {
            return random.generateSeed(i);
        }

        @Override // java.security.SecureRandomSpi
        protected void engineNextBytes(byte[] bArr) {
            random.nextBytes(bArr);
        }

        @Override // java.security.SecureRandomSpi
        protected void engineSetSeed(byte[] bArr) {
            random.setSeed(bArr);
        }
    }

    /* loaded from: classes2.dex */
    public static class URLSeededEntropySourceProvider implements EntropySourceProvider {
        private final InputStream seedStream;

        URLSeededEntropySourceProvider(final URL url) {
            this.seedStream = (InputStream) AccessController.doPrivileged(new PrivilegedAction<InputStream>() { // from class: org.bouncycastle.jcajce.provider.drbg.DRBG.URLSeededEntropySourceProvider.1
                @Override // java.security.PrivilegedAction
                public InputStream run() {
                    try {
                        return url.openStream();
                    } catch (IOException unused) {
                        throw new IllegalStateException("unable to open random source");
                    }
                }
            });
        }

        public int privilegedRead(final byte[] bArr, final int i, final int i2) {
            return ((Integer) AccessController.doPrivileged(new PrivilegedAction<Integer>() { // from class: org.bouncycastle.jcajce.provider.drbg.DRBG.URLSeededEntropySourceProvider.2
                @Override // java.security.PrivilegedAction
                public Integer run() {
                    try {
                        return Integer.valueOf(URLSeededEntropySourceProvider.this.seedStream.read(bArr, i, i2));
                    } catch (IOException unused) {
                        throw new InternalError("unable to read random source");
                    }
                }
            })).intValue();
        }

        @Override // org.bouncycastle.crypto.prng.EntropySourceProvider
        public EntropySource get(int i) {
            return new IncrementalEntropySource(i) { // from class: org.bouncycastle.jcajce.provider.drbg.DRBG.URLSeededEntropySourceProvider.3
                private final int numBytes;
                final /* synthetic */ int val$bitsRequired;

                {
                    URLSeededEntropySourceProvider.this = this;
                    this.val$bitsRequired = i;
                    this.numBytes = (i + 7) / 8;
                }

                @Override // org.bouncycastle.crypto.prng.EntropySource
                public int entropySize() {
                    return this.val$bitsRequired;
                }

                @Override // org.bouncycastle.crypto.prng.EntropySource
                public byte[] getEntropy() {
                    return getEntropy(0L);
                }

                @Override // org.bouncycastle.jcajce.provider.drbg.DRBG.IncrementalEntropySource
                public byte[] getEntropy(long j) {
                    int i2 = this.numBytes;
                    byte[] bArr = new byte[i2];
                    int i3 = 0;
                    while (i3 != i2) {
                        int privilegedRead = URLSeededEntropySourceProvider.this.privilegedRead(bArr, i3, i2 - i3);
                        if (privilegedRead <= -1) {
                            break;
                        }
                        i3 += privilegedRead;
                        DRBG.sleep(j);
                    }
                    if (i3 == i2) {
                        return bArr;
                    }
                    throw new InternalError("unable to fully read random source");
                }

                @Override // org.bouncycastle.crypto.prng.EntropySource
                public boolean isPredictionResistant() {
                    return true;
                }
            };
        }
    }

    static {
        entropyDaemon = null;
        entropyThread = null;
        entropyDaemon = new EntropyDaemon();
        Thread thread = new Thread(entropyDaemon, "BC Entropy Daemon");
        entropyThread = thread;
        thread.setDaemon(true);
        entropyThread.start();
    }

    static /* synthetic */ long access$1000() {
        return getPause();
    }

    static /* synthetic */ Object[] access$400() {
        return findSource();
    }

    static /* synthetic */ EntropySourceProvider access$800() {
        return createCoreEntropySourceProvider();
    }

    public static SecureRandom createBaseRandom(boolean z) {
        if (Properties.getPropertyValue("org.bouncycastle.drbg.entropysource") == null) {
            HybridEntropySource hybridEntropySource = new HybridEntropySource(entropyDaemon, 256);
            byte[] entropy = hybridEntropySource.getEntropy();
            return new SP800SecureRandomBuilder(new EntropySourceProvider() { // from class: org.bouncycastle.jcajce.provider.drbg.DRBG.1
                @Override // org.bouncycastle.crypto.prng.EntropySourceProvider
                public EntropySource get(int i) {
                    return new HybridEntropySource(DRBG.entropyDaemon, i);
                }
            }).setPersonalizationString(z ? generateDefaultPersonalizationString(entropy) : generateNonceIVPersonalizationString(entropy)).buildHash(new SHA512Digest(), hybridEntropySource.getEntropy(), z);
        }
        EntropySourceProvider createEntropySource = createEntropySource();
        EntropySource entropySource = createEntropySource.get(128);
        byte[] entropy2 = entropySource.getEntropy();
        return new SP800SecureRandomBuilder(createEntropySource).setPersonalizationString(z ? generateDefaultPersonalizationString(entropy2) : generateNonceIVPersonalizationString(entropy2)).buildHash(new SHA512Digest(), entropySource.getEntropy(), z);
    }

    private static EntropySourceProvider createCoreEntropySourceProvider() {
        if (Security.getProperty("securerandom.source") == null) {
            return createInitialEntropySource();
        }
        try {
            return new URLSeededEntropySourceProvider(new URL(Security.getProperty("securerandom.source")));
        } catch (Exception unused) {
            return createInitialEntropySource();
        }
    }

    private static EntropySourceProvider createEntropySource() {
        final String propertyValue = Properties.getPropertyValue("org.bouncycastle.drbg.entropysource");
        return (EntropySourceProvider) AccessController.doPrivileged(new PrivilegedAction<EntropySourceProvider>() { // from class: org.bouncycastle.jcajce.provider.drbg.DRBG.4
            @Override // java.security.PrivilegedAction
            public EntropySourceProvider run() {
                try {
                    return (EntropySourceProvider) ClassUtil.loadClass(DRBG.class, propertyValue).newInstance();
                } catch (Exception e) {
                    throw new IllegalStateException("entropy source " + propertyValue + " not created: " + e.getMessage(), e);
                }
            }
        });
    }

    private static EntropySourceProvider createInitialEntropySource() {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() { // from class: org.bouncycastle.jcajce.provider.drbg.DRBG.2
            @Override // java.security.PrivilegedAction
            public Boolean run() {
                try {
                    return Boolean.valueOf(SecureRandom.class.getMethod("getInstanceStrong", new Class[0]) != null);
                } catch (Exception unused) {
                    return false;
                }
            }
        })).booleanValue() ? new IncrementalEntropySourceProvider((SecureRandom) AccessController.doPrivileged(new PrivilegedAction<SecureRandom>() { // from class: org.bouncycastle.jcajce.provider.drbg.DRBG.3
            @Override // java.security.PrivilegedAction
            public SecureRandom run() {
                try {
                    return (SecureRandom) SecureRandom.class.getMethod("getInstanceStrong", new Class[0]).invoke(null, new Object[0]);
                } catch (Exception unused) {
                    return new CoreSecureRandom(DRBG.access$400());
                }
            }
        }), true) : new IncrementalEntropySourceProvider(new CoreSecureRandom(findSource()), true);
    }

    private static final Object[] findSource() {
        int i = 0;
        while (true) {
            String[][] strArr = initialEntropySourceNames;
            if (i >= strArr.length) {
                return null;
            }
            String[] strArr2 = strArr[i];
            try {
                return new Object[]{Class.forName(strArr2[0]).newInstance(), Class.forName(strArr2[1]).newInstance()};
            } catch (Throwable unused) {
                i++;
            }
        }
    }

    private static byte[] generateDefaultPersonalizationString(byte[] bArr) {
        return Arrays.concatenate(Strings.toByteArray("Default"), bArr, Pack.longToBigEndian(Thread.currentThread().getId()), Pack.longToBigEndian(System.currentTimeMillis()));
    }

    private static byte[] generateNonceIVPersonalizationString(byte[] bArr) {
        return Arrays.concatenate(Strings.toByteArray("Nonce"), bArr, Pack.longToLittleEndian(Thread.currentThread().getId()), Pack.longToLittleEndian(System.currentTimeMillis()));
    }

    private static long getPause() {
        String propertyValue = Properties.getPropertyValue("org.bouncycastle.drbg.gather_pause_secs");
        if (propertyValue != null) {
            try {
                return Long.parseLong(propertyValue) * 1000;
            } catch (Exception unused) {
            }
        }
        return 5000L;
    }

    public static void sleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }
}
