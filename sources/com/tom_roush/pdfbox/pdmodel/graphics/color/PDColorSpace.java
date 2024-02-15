package com.tom_roush.pdfbox.pdmodel.graphics.color;

import android.graphics.Bitmap;
import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.pdmodel.MissingResourceException;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.io.IOException;

/* loaded from: classes3.dex */
public abstract class PDColorSpace implements COSObjectable {
    protected COSArray array;

    public abstract float[] getDefaultDecode(int i);

    public abstract PDColor getInitialColor();

    public abstract String getName();

    public abstract int getNumberOfComponents();

    public abstract float[] toRGB(float[] fArr) throws IOException;

    public abstract Bitmap toRGBImage(Bitmap bitmap) throws IOException;

    public static PDColorSpace create(COSBase cOSBase) throws IOException {
        return create(cOSBase, null);
    }

    public static PDColorSpace create(COSBase cOSBase, PDResources pDResources) throws IOException {
        return create(cOSBase, pDResources, false);
    }

    public static PDColorSpace create(COSBase cOSBase, PDResources pDResources, boolean z) throws IOException {
        COSName cOSName;
        if (cOSBase instanceof COSObject) {
            return createFromCOSObject((COSObject) cOSBase, pDResources);
        }
        if (cOSBase instanceof COSName) {
            COSName cOSName2 = (COSName) cOSBase;
            if (pDResources != null) {
                if (cOSName2.equals(COSName.DEVICECMYK) && pDResources.hasColorSpace(COSName.DEFAULT_CMYK)) {
                    cOSName = COSName.DEFAULT_CMYK;
                } else if (cOSName2.equals(COSName.DEVICERGB) && pDResources.hasColorSpace(COSName.DEFAULT_RGB)) {
                    cOSName = COSName.DEFAULT_RGB;
                } else {
                    cOSName = (cOSName2.equals(COSName.DEVICEGRAY) && pDResources.hasColorSpace(COSName.DEFAULT_GRAY)) ? COSName.DEFAULT_GRAY : null;
                }
                if (pDResources.hasColorSpace(cOSName) && !z) {
                    return pDResources.getColorSpace(cOSName, true);
                }
            }
            if (cOSName2 == COSName.DEVICECMYK) {
                Log.e("PdfBox-Android", "Unsupported color space kind: " + cOSName2 + ". Will try DeviceRGB instead");
                return PDDeviceRGB.INSTANCE;
            } else if (cOSName2 == COSName.DEVICERGB) {
                return PDDeviceRGB.INSTANCE;
            } else {
                if (cOSName2 == COSName.DEVICEGRAY) {
                    return PDDeviceGray.INSTANCE;
                }
                if (cOSName2 == COSName.PATTERN) {
                    Log.e("PdfBox-Android", "Unsupported color space kind: " + cOSName2 + ". Will try DeviceRGB instead");
                    return PDDeviceRGB.INSTANCE;
                } else if (pDResources != null) {
                    if (!pDResources.hasColorSpace(cOSName2)) {
                        throw new MissingResourceException("Missing color space: " + cOSName2.getName());
                    }
                    return pDResources.getColorSpace(cOSName2);
                } else {
                    throw new MissingResourceException("Unknown color space: " + cOSName2.getName());
                }
            }
        } else if (cOSBase instanceof COSArray) {
            COSArray cOSArray = (COSArray) cOSBase;
            if (cOSArray.size() == 0) {
                throw new IOException("Colorspace array is empty");
            }
            COSBase object = cOSArray.getObject(0);
            if (!(object instanceof COSName)) {
                throw new IOException("First element in colorspace array must be a name");
            }
            COSName cOSName3 = (COSName) object;
            if (cOSName3 == COSName.CALGRAY) {
                Log.e("PdfBox-Android", "Unsupported color space kind: " + cOSName3 + ". Will try DeviceRGB instead");
                return PDDeviceRGB.INSTANCE;
            } else if (cOSName3 == COSName.CALRGB) {
                Log.e("PdfBox-Android", "Unsupported color space kind: " + cOSName3 + ". Will try DeviceRGB instead");
                return PDDeviceRGB.INSTANCE;
            } else if (cOSName3 == COSName.DEVICEN) {
                Log.e("PdfBox-Android", "Unsupported color space kind: " + cOSName3 + ". Will try DeviceRGB instead");
                return PDDeviceRGB.INSTANCE;
            } else if (cOSName3 == COSName.INDEXED) {
                Log.e("PdfBox-Android", "Unsupported color space kind: " + cOSName3 + ". Will try DeviceRGB instead");
                return PDDeviceRGB.INSTANCE;
            } else if (cOSName3 == COSName.SEPARATION) {
                Log.e("PdfBox-Android", "Unsupported color space kind: " + cOSName3 + ". Will try DeviceRGB instead");
                return PDDeviceRGB.INSTANCE;
            } else if (cOSName3 == COSName.ICCBASED) {
                Log.e("PdfBox-Android", "Unsupported color space kind: " + cOSName3 + ". Will try DeviceRGB instead");
                return PDDeviceRGB.INSTANCE;
            } else if (cOSName3 == COSName.LAB) {
                Log.e("PdfBox-Android", "Unsupported color space kind: " + cOSName3 + ". Will try DeviceRGB instead");
                return PDDeviceRGB.INSTANCE;
            } else if (cOSName3 == COSName.PATTERN) {
                cOSArray.size();
                Log.e("PdfBox-Android", "Unsupported color space kind: " + cOSName3 + ". Will try DeviceRGB instead");
                return PDDeviceRGB.INSTANCE;
            } else if (cOSName3 == COSName.DEVICECMYK || cOSName3 == COSName.DEVICERGB || cOSName3 == COSName.DEVICEGRAY) {
                return create(cOSName3, pDResources, z);
            } else {
                throw new IOException("Invalid color space kind: " + cOSName3);
            }
        } else {
            if (cOSBase instanceof COSDictionary) {
                COSDictionary cOSDictionary = (COSDictionary) cOSBase;
                if (cOSDictionary.containsKey(COSName.COLORSPACE)) {
                    COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.COLORSPACE);
                    if (dictionaryObject == cOSBase) {
                        throw new IOException("Recursion in colorspace: " + cOSDictionary.getItem(COSName.COLORSPACE) + " points to itself");
                    }
                    return create(dictionaryObject, pDResources, z);
                }
            }
            throw new IOException("Expected a name or array but got: " + cOSBase);
        }
    }

    private static PDColorSpace createFromCOSObject(COSObject cOSObject, PDResources pDResources) throws IOException {
        PDColorSpace colorSpace;
        if (pDResources == null || pDResources.getResourceCache() == null || (colorSpace = pDResources.getResourceCache().getColorSpace(cOSObject)) == null) {
            PDColorSpace create = create(cOSObject.getObject(), pDResources);
            if (pDResources != null && pDResources.getResourceCache() != null && create != null) {
                pDResources.getResourceCache().put(cOSObject, create);
            }
            return create;
        }
        return colorSpace;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.array;
    }
}
