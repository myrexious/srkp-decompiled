package org.apache.commons.imaging.internal;

import java.io.IOException;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.bytesource.ByteSource;

/* loaded from: classes2.dex */
public class Util {
    private Util() {
    }

    public static ImageParser<?> getImageParser(final ImageFormat imageFormat) {
        return getImageParser(new Predicate() { // from class: org.apache.commons.imaging.internal.Util$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean canAcceptType;
                canAcceptType = ((ImageParser) obj).canAcceptType(ImageFormat.this);
                return canAcceptType;
            }
        }, new Supplier() { // from class: org.apache.commons.imaging.internal.Util$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Util.lambda$getImageParser$1(ImageFormat.this);
            }
        });
    }

    public static /* synthetic */ RuntimeException lambda$getImageParser$1(ImageFormat imageFormat) {
        return new IllegalArgumentException("Unknown Format: " + imageFormat);
    }

    public static ImageParser<?> getImageParser(final String str) {
        return getImageParser(new Predicate() { // from class: org.apache.commons.imaging.internal.Util$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean canAcceptExtension;
                canAcceptExtension = ((ImageParser) obj).canAcceptExtension(str);
                return canAcceptExtension;
            }
        }, new Supplier() { // from class: org.apache.commons.imaging.internal.Util$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Util.lambda$getImageParser$3(str);
            }
        });
    }

    public static /* synthetic */ RuntimeException lambda$getImageParser$3(String str) {
        return new IllegalArgumentException("Unknown Extension: " + str);
    }

    private static ImageParser<?> getImageParser(Predicate<ImageParser<?>> predicate, Supplier<? extends RuntimeException> supplier) {
        return ImageParser.getAllImageParsers().stream().filter(predicate).findFirst().orElseThrow(supplier);
    }

    public static ImageParser<?> getImageParser(ByteSource byteSource) throws IOException {
        ImageFormat guessFormat = Imaging.guessFormat(byteSource);
        if (!guessFormat.equals(ImageFormats.UNKNOWN)) {
            return getImageParser(guessFormat);
        }
        String fileName = byteSource.getFileName();
        if (fileName != null) {
            return getImageParser(fileName);
        }
        throw new IllegalArgumentException("Can't parse this format.");
    }
}
