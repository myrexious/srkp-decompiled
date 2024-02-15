package org.apache.commons.imaging.formats.png;

import androidx.core.view.ViewCompat;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.formats.png.chunks.PngChunkPlte;
import org.apache.commons.imaging.formats.png.scanlinefilters.ScanlineFilter;
import org.apache.commons.imaging.formats.png.scanlinefilters.ScanlineFilterAverage;
import org.apache.commons.imaging.formats.png.scanlinefilters.ScanlineFilterNone;
import org.apache.commons.imaging.formats.png.scanlinefilters.ScanlineFilterPaeth;
import org.apache.commons.imaging.formats.png.scanlinefilters.ScanlineFilterSub;
import org.apache.commons.imaging.formats.png.scanlinefilters.ScanlineFilterUp;
import org.apache.commons.imaging.formats.png.transparencyfilters.TransparencyFilter;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class ScanExpediter {
    final BufferedImage bi;
    final int bitDepth;
    final int bitsPerPixel;
    final int bytesPerPixel;
    final GammaCorrection gammaCorrection;
    final int height;
    final InputStream is;
    final PngChunkPlte pngChunkPLTE;
    final PngColorType pngColorType;
    final TransparencyFilter transparencyFilter;
    final int width;

    public abstract void drive() throws ImageReadException, IOException;

    final int getPixelARGB(int i, int i2, int i3, int i4) {
        return ((i & 255) << 24) | ((i2 & 255) << 16) | ((i3 & 255) << 8) | ((i4 & 255) << 0);
    }

    public ScanExpediter(int i, int i2, InputStream inputStream, BufferedImage bufferedImage, PngColorType pngColorType, int i3, int i4, PngChunkPlte pngChunkPlte, GammaCorrection gammaCorrection, TransparencyFilter transparencyFilter) {
        this.width = i;
        this.height = i2;
        this.is = inputStream;
        this.bi = bufferedImage;
        this.pngColorType = pngColorType;
        this.bitDepth = i3;
        this.bytesPerPixel = getBitsToBytesRoundingUp(i4);
        this.bitsPerPixel = i4;
        this.pngChunkPLTE = pngChunkPlte;
        this.gammaCorrection = gammaCorrection;
        this.transparencyFilter = transparencyFilter;
    }

    public final int getBitsToBytesRoundingUp(int i) {
        return (i + 7) / 8;
    }

    final int getPixelRGB(int i, int i2, int i3) {
        return getPixelARGB(255, i, i2, i3);
    }

    public int getRGB(BitParser bitParser, int i) throws ImageReadException, IOException {
        int i2 = AnonymousClass1.$SwitchMap$org$apache$commons$imaging$formats$png$PngColorType[this.pngColorType.ordinal()];
        if (i2 == 1) {
            int sampleAsByte = bitParser.getSampleAsByte(i, 0);
            GammaCorrection gammaCorrection = this.gammaCorrection;
            if (gammaCorrection != null) {
                sampleAsByte = gammaCorrection.correctSample(sampleAsByte);
            }
            int pixelRGB = getPixelRGB(sampleAsByte, sampleAsByte, sampleAsByte);
            TransparencyFilter transparencyFilter = this.transparencyFilter;
            return transparencyFilter != null ? transparencyFilter.filter(pixelRGB, sampleAsByte) : pixelRGB;
        } else if (i2 == 2) {
            int sampleAsByte2 = bitParser.getSampleAsByte(i, 0);
            int sampleAsByte3 = bitParser.getSampleAsByte(i, 1);
            int sampleAsByte4 = bitParser.getSampleAsByte(i, 2);
            int pixelRGB2 = getPixelRGB(sampleAsByte2, sampleAsByte3, sampleAsByte4);
            TransparencyFilter transparencyFilter2 = this.transparencyFilter;
            if (transparencyFilter2 != null) {
                pixelRGB2 = transparencyFilter2.filter(pixelRGB2, -1);
            }
            GammaCorrection gammaCorrection2 = this.gammaCorrection;
            return gammaCorrection2 != null ? getPixelARGB((pixelRGB2 & ViewCompat.MEASURED_STATE_MASK) >> 24, gammaCorrection2.correctSample(sampleAsByte2), this.gammaCorrection.correctSample(sampleAsByte3), this.gammaCorrection.correctSample(sampleAsByte4)) : pixelRGB2;
        } else if (i2 == 3) {
            if (this.pngChunkPLTE == null) {
                throw new ImageReadException("A PLTE chunk is required for an indexed color type.");
            }
            int sample = bitParser.getSample(i, 0);
            int rgb = this.pngChunkPLTE.getRGB(sample);
            TransparencyFilter transparencyFilter3 = this.transparencyFilter;
            return transparencyFilter3 != null ? transparencyFilter3.filter(rgb, sample) : rgb;
        } else if (i2 == 4) {
            int sampleAsByte5 = bitParser.getSampleAsByte(i, 0);
            int sampleAsByte6 = bitParser.getSampleAsByte(i, 1);
            GammaCorrection gammaCorrection3 = this.gammaCorrection;
            if (gammaCorrection3 != null) {
                sampleAsByte5 = gammaCorrection3.correctSample(sampleAsByte5);
            }
            return getPixelARGB(sampleAsByte6, sampleAsByte5, sampleAsByte5, sampleAsByte5);
        } else if (i2 == 5) {
            int sampleAsByte7 = bitParser.getSampleAsByte(i, 0);
            int sampleAsByte8 = bitParser.getSampleAsByte(i, 1);
            int sampleAsByte9 = bitParser.getSampleAsByte(i, 2);
            int sampleAsByte10 = bitParser.getSampleAsByte(i, 3);
            GammaCorrection gammaCorrection4 = this.gammaCorrection;
            if (gammaCorrection4 != null) {
                sampleAsByte7 = gammaCorrection4.correctSample(sampleAsByte7);
                sampleAsByte8 = this.gammaCorrection.correctSample(sampleAsByte8);
                sampleAsByte9 = this.gammaCorrection.correctSample(sampleAsByte9);
            }
            return getPixelARGB(sampleAsByte10, sampleAsByte7, sampleAsByte8, sampleAsByte9);
        } else {
            throw new ImageReadException("PNG: unknown color type: " + this.pngColorType);
        }
    }

    /* renamed from: org.apache.commons.imaging.formats.png.ScanExpediter$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$imaging$formats$png$FilterType;
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$imaging$formats$png$PngColorType;

        static {
            int[] iArr = new int[FilterType.values().length];
            $SwitchMap$org$apache$commons$imaging$formats$png$FilterType = iArr;
            try {
                iArr[FilterType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$formats$png$FilterType[FilterType.SUB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$formats$png$FilterType[FilterType.UP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$formats$png$FilterType[FilterType.AVERAGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$formats$png$FilterType[FilterType.PAETH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[PngColorType.values().length];
            $SwitchMap$org$apache$commons$imaging$formats$png$PngColorType = iArr2;
            try {
                iArr2[PngColorType.GREYSCALE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$formats$png$PngColorType[PngColorType.TRUE_COLOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$formats$png$PngColorType[PngColorType.INDEXED_COLOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$formats$png$PngColorType[PngColorType.GREYSCALE_WITH_ALPHA.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$apache$commons$imaging$formats$png$PngColorType[PngColorType.TRUE_COLOR_WITH_ALPHA.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    ScanlineFilter getScanlineFilter(FilterType filterType, int i) {
        int i2 = AnonymousClass1.$SwitchMap$org$apache$commons$imaging$formats$png$FilterType[filterType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return null;
                        }
                        return new ScanlineFilterPaeth(i);
                    }
                    return new ScanlineFilterAverage(i);
                }
                return new ScanlineFilterUp();
            }
            return new ScanlineFilterSub(i);
        }
        return new ScanlineFilterNone();
    }

    byte[] unfilterScanline(FilterType filterType, byte[] bArr, byte[] bArr2, int i) throws ImageReadException, IOException {
        ScanlineFilter scanlineFilter = getScanlineFilter(filterType, i);
        byte[] bArr3 = new byte[bArr.length];
        scanlineFilter.unfilter(bArr, bArr3, bArr2);
        return bArr3;
    }

    public byte[] getNextScanline(InputStream inputStream, int i, byte[] bArr, int i2) throws ImageReadException, IOException {
        int read = inputStream.read();
        if (read < 0) {
            throw new ImageReadException("PNG: missing filter type");
        }
        if (read >= FilterType.values().length) {
            throw new ImageReadException("PNG: unknown filterType: " + read);
        }
        return unfilterScanline(FilterType.values()[read], BinaryFunctions.readBytes("scanline", inputStream, i, "PNG: missing image data"), bArr, i2);
    }
}
