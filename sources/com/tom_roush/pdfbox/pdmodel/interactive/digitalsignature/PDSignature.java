package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

/* loaded from: classes3.dex */
public class PDSignature implements COSObjectable {
    private final COSDictionary dictionary;
    public static final COSName FILTER_ADOBE_PPKLITE = COSName.ADOBE_PPKLITE;
    public static final COSName FILTER_ENTRUST_PPKEF = COSName.ENTRUST_PPKEF;
    public static final COSName FILTER_CICI_SIGNIT = COSName.CICI_SIGNIT;
    public static final COSName FILTER_VERISIGN_PPKVS = COSName.VERISIGN_PPKVS;
    public static final COSName SUBFILTER_ADBE_X509_RSA_SHA1 = COSName.ADBE_X509_RSA_SHA1;
    public static final COSName SUBFILTER_ADBE_PKCS7_DETACHED = COSName.ADBE_PKCS7_DETACHED;
    public static final COSName SUBFILTER_ETSI_CADES_DETACHED = COSName.getPDFName("ETSI.CAdES.detached");
    public static final COSName SUBFILTER_ADBE_PKCS7_SHA1 = COSName.ADBE_PKCS7_SHA1;

    public PDSignature() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.SIG);
    }

    public PDSignature(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public void setType(COSName cOSName) {
        this.dictionary.setItem(COSName.TYPE, (COSBase) cOSName);
    }

    public void setFilter(COSName cOSName) {
        this.dictionary.setItem(COSName.FILTER, (COSBase) cOSName);
    }

    public void setSubFilter(COSName cOSName) {
        this.dictionary.setItem(COSName.SUB_FILTER, (COSBase) cOSName);
    }

    public void setName(String str) {
        this.dictionary.setString(COSName.NAME, str);
    }

    public void setLocation(String str) {
        this.dictionary.setString(COSName.LOCATION, str);
    }

    public void setReason(String str) {
        this.dictionary.setString(COSName.REASON, str);
    }

    public void setContactInfo(String str) {
        this.dictionary.setString(COSName.CONTACT_INFO, str);
    }

    public void setSignDate(Calendar calendar) {
        this.dictionary.setDate(COSName.M, calendar);
    }

    public String getFilter() {
        return this.dictionary.getNameAsString(COSName.FILTER);
    }

    public String getSubFilter() {
        return this.dictionary.getNameAsString(COSName.SUB_FILTER);
    }

    public String getName() {
        return this.dictionary.getString(COSName.NAME);
    }

    public String getLocation() {
        return this.dictionary.getString(COSName.LOCATION);
    }

    public String getReason() {
        return this.dictionary.getString(COSName.REASON);
    }

    public String getContactInfo() {
        return this.dictionary.getString(COSName.CONTACT_INFO);
    }

    public Calendar getSignDate() {
        return this.dictionary.getDate(COSName.M);
    }

    public void setByteRange(int[] iArr) {
        if (iArr.length != 4) {
            return;
        }
        COSArray cOSArray = new COSArray();
        for (int i : iArr) {
            cOSArray.add((COSBase) COSInteger.get(i));
        }
        this.dictionary.setItem(COSName.BYTERANGE, (COSBase) cOSArray);
        cOSArray.setDirect(true);
    }

    public int[] getByteRange() {
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.BYTERANGE);
        if (cOSArray == null) {
            return new int[0];
        }
        int size = cOSArray.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = cOSArray.getInt(i);
        }
        return iArr;
    }

    public byte[] getContents() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.CONTENTS);
        return dictionaryObject instanceof COSString ? ((COSString) dictionaryObject).getBytes() : new byte[0];
    }

    public byte[] getContents(InputStream inputStream) throws IOException {
        int[] byteRange = getByteRange();
        int i = byteRange[0] + byteRange[1] + 1;
        return getConvertedContents(new COSFilterInputStream(inputStream, new int[]{i, byteRange[2] - i}));
    }

    public byte[] getContents(byte[] bArr) throws IOException {
        int[] byteRange = getByteRange();
        int i = byteRange[0] + byteRange[1] + 1;
        return getConvertedContents(new ByteArrayInputStream(bArr, i, byteRange[2] - i));
    }

    private byte[] getConvertedContents(InputStream inputStream) throws IOException {
        int i;
        int i2;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byte b = bArr[0];
                if (b == 60 || b == 40) {
                    i = read - 1;
                    i2 = 1;
                } else {
                    i2 = 0;
                    i = read;
                }
                byte b2 = bArr[read - 1];
                if (b2 == 62 || b2 == 41) {
                    i--;
                }
                byteArrayOutputStream.write(bArr, i2, i);
            } else {
                inputStream.close();
                return COSString.parseHex(byteArrayOutputStream.toString("ISO-8859-1")).getBytes();
            }
        }
    }

    public void setContents(byte[] bArr) {
        COSString cOSString = new COSString(bArr);
        cOSString.setForceHexForm(true);
        this.dictionary.setItem(COSName.CONTENTS, (COSBase) cOSString);
    }

    public byte[] getSignedContent(InputStream inputStream) throws IOException {
        COSFilterInputStream cOSFilterInputStream;
        COSFilterInputStream cOSFilterInputStream2 = null;
        try {
            cOSFilterInputStream = new COSFilterInputStream(inputStream, getByteRange());
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] byteArray = cOSFilterInputStream.toByteArray();
            cOSFilterInputStream.close();
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
            cOSFilterInputStream2 = cOSFilterInputStream;
            if (cOSFilterInputStream2 != null) {
                cOSFilterInputStream2.close();
            }
            throw th;
        }
    }

    public byte[] getSignedContent(byte[] bArr) throws IOException {
        COSFilterInputStream cOSFilterInputStream;
        COSFilterInputStream cOSFilterInputStream2 = null;
        try {
            cOSFilterInputStream = new COSFilterInputStream(bArr, getByteRange());
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] byteArray = cOSFilterInputStream.toByteArray();
            cOSFilterInputStream.close();
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
            cOSFilterInputStream2 = cOSFilterInputStream;
            if (cOSFilterInputStream2 != null) {
                cOSFilterInputStream2.close();
            }
            throw th;
        }
    }

    public PDPropBuild getPropBuild() {
        COSDictionary cOSDictionary = this.dictionary.getCOSDictionary(COSName.PROP_BUILD);
        if (cOSDictionary != null) {
            return new PDPropBuild(cOSDictionary);
        }
        return null;
    }

    public void setPropBuild(PDPropBuild pDPropBuild) {
        this.dictionary.setItem(COSName.PROP_BUILD, pDPropBuild);
    }
}
