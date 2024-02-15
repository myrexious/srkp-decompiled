package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class PDSeedValueCertificate implements COSObjectable {
    public static final int FLAG_ISSUER = 2;
    public static final int FLAG_KEY_USAGE = 32;
    public static final int FLAG_OID = 4;
    public static final int FLAG_SUBJECT = 1;
    public static final int FLAG_SUBJECT_DN = 8;
    public static final int FLAG_URL = 64;
    private final COSDictionary dictionary;

    public PDSeedValueCertificate() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.SV_CERT);
        cOSDictionary.setDirect(true);
    }

    public PDSeedValueCertificate(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
        cOSDictionary.setDirect(true);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public boolean isSubjectRequired() {
        return this.dictionary.getFlag(COSName.FF, 1);
    }

    public void setSubjectRequired(boolean z) {
        this.dictionary.setFlag(COSName.FF, 1, z);
    }

    public boolean isIssuerRequired() {
        return this.dictionary.getFlag(COSName.FF, 2);
    }

    public void setIssuerRequired(boolean z) {
        this.dictionary.setFlag(COSName.FF, 2, z);
    }

    public boolean isOIDRequired() {
        return this.dictionary.getFlag(COSName.FF, 4);
    }

    public void setOIDRequired(boolean z) {
        this.dictionary.setFlag(COSName.FF, 4, z);
    }

    public boolean isSubjectDNRequired() {
        return this.dictionary.getFlag(COSName.FF, 8);
    }

    public void setSubjectDNRequired(boolean z) {
        this.dictionary.setFlag(COSName.FF, 8, z);
    }

    public boolean isKeyUsageRequired() {
        return this.dictionary.getFlag(COSName.FF, 32);
    }

    public void setKeyUsageRequired(boolean z) {
        this.dictionary.setFlag(COSName.FF, 32, z);
    }

    public boolean isURLRequired() {
        return this.dictionary.getFlag(COSName.FF, 64);
    }

    public void setURLRequired(boolean z) {
        this.dictionary.setFlag(COSName.FF, 64, z);
    }

    public List<byte[]> getSubject() {
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.SUBJECT);
        if (cOSArray != null) {
            return getListOfByteArraysFromCOSArray(cOSArray);
        }
        return null;
    }

    public void setSubject(List<byte[]> list) {
        this.dictionary.setItem(COSName.SUBJECT, (COSBase) convertListOfByteArraysToCOSArray(list));
    }

    public void addSubject(byte[] bArr) {
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.SUBJECT);
        if (cOSArray == null) {
            cOSArray = new COSArray();
        }
        cOSArray.add((COSBase) new COSString(bArr));
        this.dictionary.setItem(COSName.SUBJECT, (COSBase) cOSArray);
    }

    public void removeSubject(byte[] bArr) {
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.SUBJECT);
        if (cOSArray != null) {
            cOSArray.remove(new COSString(bArr));
        }
    }

    public List<Map<String, String>> getSubjectDN() {
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.SUBJECT_DN);
        if (cOSArray != null) {
            List<? extends COSBase> list = cOSArray.toList();
            LinkedList linkedList = new LinkedList();
            for (COSBase cOSBase : list) {
                if (cOSBase instanceof COSDictionary) {
                    COSDictionary cOSDictionary = (COSDictionary) cOSBase;
                    HashMap hashMap = new HashMap();
                    for (COSName cOSName : cOSDictionary.keySet()) {
                        hashMap.put(cOSName.getName(), cOSDictionary.getString(cOSName));
                    }
                    linkedList.add(hashMap);
                }
            }
            return linkedList;
        }
        return null;
    }

    public void setSubjectDN(List<Map<String, String>> list) {
        LinkedList linkedList = new LinkedList();
        for (Map<String, String> map : list) {
            COSDictionary cOSDictionary = new COSDictionary();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                cOSDictionary.setItem(entry.getKey(), (COSBase) new COSString(entry.getValue()));
            }
            linkedList.add(cOSDictionary);
        }
        this.dictionary.setItem(COSName.SUBJECT_DN, (COSBase) COSArrayList.converterToCOSArray(linkedList));
    }

    public List<String> getKeyUsage() {
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.KEY_USAGE);
        if (cOSArray != null) {
            LinkedList linkedList = new LinkedList();
            Iterator<COSBase> it = cOSArray.iterator();
            while (it.hasNext()) {
                COSBase next = it.next();
                if (next instanceof COSString) {
                    linkedList.add(((COSString) next).getString());
                }
            }
            return linkedList;
        }
        return null;
    }

    public void setKeyUsage(List<String> list) {
        this.dictionary.setItem(COSName.KEY_USAGE, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public void addKeyUsage(String str) {
        for (int i = 0; i < str.length(); i++) {
            if ("01X".indexOf(str.charAt(i)) == -1) {
                throw new IllegalArgumentException("characters can only be 0, 1, X");
            }
        }
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.KEY_USAGE);
        if (cOSArray == null) {
            cOSArray = new COSArray();
        }
        cOSArray.add((COSBase) new COSString(str));
        this.dictionary.setItem(COSName.KEY_USAGE, (COSBase) cOSArray);
    }

    public void addKeyUsage(char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9) {
        addKeyUsage("" + c + c2 + c3 + c4 + c5 + c6 + c7 + c8 + c9);
    }

    public void removeKeyUsage(String str) {
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.KEY_USAGE);
        if (cOSArray != null) {
            cOSArray.remove(new COSString(str));
        }
    }

    public List<byte[]> getIssuer() {
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.ISSUER);
        if (cOSArray != null) {
            return getListOfByteArraysFromCOSArray(cOSArray);
        }
        return null;
    }

    public void setIssuer(List<byte[]> list) {
        this.dictionary.setItem(COSName.ISSUER, (COSBase) convertListOfByteArraysToCOSArray(list));
    }

    public void addIssuer(byte[] bArr) {
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.ISSUER);
        if (cOSArray == null) {
            cOSArray = new COSArray();
        }
        cOSArray.add((COSBase) new COSString(bArr));
        this.dictionary.setItem(COSName.ISSUER, (COSBase) cOSArray);
    }

    public void removeIssuer(byte[] bArr) {
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.ISSUER);
        if (cOSArray != null) {
            cOSArray.remove(new COSString(bArr));
        }
    }

    public List<byte[]> getOID() {
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.OID);
        if (cOSArray != null) {
            return getListOfByteArraysFromCOSArray(cOSArray);
        }
        return null;
    }

    public void setOID(List<byte[]> list) {
        this.dictionary.setItem(COSName.OID, (COSBase) convertListOfByteArraysToCOSArray(list));
    }

    public void addOID(byte[] bArr) {
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.OID);
        if (cOSArray == null) {
            cOSArray = new COSArray();
        }
        cOSArray.add((COSBase) new COSString(bArr));
        this.dictionary.setItem(COSName.OID, (COSBase) cOSArray);
    }

    public void removeOID(byte[] bArr) {
        COSArray cOSArray = this.dictionary.getCOSArray(COSName.OID);
        if (cOSArray != null) {
            cOSArray.remove(new COSString(bArr));
        }
    }

    public String getURL() {
        return this.dictionary.getString(COSName.URL);
    }

    public void setURL(String str) {
        this.dictionary.setString(COSName.URL, str);
    }

    public String getURLType() {
        return this.dictionary.getNameAsString(COSName.URL_TYPE);
    }

    public void setURLType(String str) {
        this.dictionary.setName(COSName.URL_TYPE, str);
    }

    private static List<byte[]> getListOfByteArraysFromCOSArray(COSArray cOSArray) {
        LinkedList linkedList = new LinkedList();
        Iterator<COSBase> it = cOSArray.iterator();
        while (it.hasNext()) {
            COSBase next = it.next();
            if (next instanceof COSString) {
                linkedList.add(((COSString) next).getBytes());
            }
        }
        return linkedList;
    }

    private static COSArray convertListOfByteArraysToCOSArray(List<byte[]> list) {
        COSArray cOSArray = new COSArray();
        for (byte[] bArr : list) {
            cOSArray.add((COSBase) new COSString(bArr));
        }
        return cOSArray;
    }
}
