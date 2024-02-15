package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class PDSeedValue implements COSObjectable {
    public static final int FLAG_ADD_REV_INFO = 32;
    public static final int FLAG_DIGEST_METHOD = 64;
    public static final int FLAG_FILTER = 1;
    public static final int FLAG_LEGAL_ATTESTATION = 16;
    public static final int FLAG_REASON = 8;
    public static final int FLAG_SUBFILTER = 2;
    public static final int FLAG_V = 4;
    private final COSDictionary dictionary;

    public PDSeedValue() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.SV);
        cOSDictionary.setDirect(true);
    }

    public PDSeedValue(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
        cOSDictionary.setDirect(true);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public boolean isFilterRequired() {
        return getCOSObject().getFlag(COSName.FF, 1);
    }

    public void setFilterRequired(boolean z) {
        getCOSObject().setFlag(COSName.FF, 1, z);
    }

    public boolean isSubFilterRequired() {
        return getCOSObject().getFlag(COSName.FF, 2);
    }

    public void setSubFilterRequired(boolean z) {
        getCOSObject().setFlag(COSName.FF, 2, z);
    }

    public boolean isDigestMethodRequired() {
        return getCOSObject().getFlag(COSName.FF, 64);
    }

    public void setDigestMethodRequired(boolean z) {
        getCOSObject().setFlag(COSName.FF, 64, z);
    }

    public boolean isVRequired() {
        return getCOSObject().getFlag(COSName.FF, 4);
    }

    public void setVRequired(boolean z) {
        getCOSObject().setFlag(COSName.FF, 4, z);
    }

    public boolean isReasonRequired() {
        return getCOSObject().getFlag(COSName.FF, 8);
    }

    public void setReasonRequired(boolean z) {
        getCOSObject().setFlag(COSName.FF, 8, z);
    }

    public boolean isLegalAttestationRequired() {
        return getCOSObject().getFlag(COSName.FF, 16);
    }

    public void setLegalAttestationRequired(boolean z) {
        getCOSObject().setFlag(COSName.FF, 16, z);
    }

    public boolean isAddRevInfoRequired() {
        return getCOSObject().getFlag(COSName.FF, 32);
    }

    public void setAddRevInfoRequired(boolean z) {
        getCOSObject().setFlag(COSName.FF, 32, z);
    }

    public String getFilter() {
        return this.dictionary.getNameAsString(COSName.FILTER);
    }

    public void setFilter(COSName cOSName) {
        this.dictionary.setItem(COSName.FILTER, (COSBase) cOSName);
    }

    public List<String> getSubFilter() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.SUB_FILTER);
        if (cOSArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < cOSArray.size(); i++) {
                String name = cOSArray.getName(i);
                if (name != null) {
                    arrayList.add(name);
                }
            }
            return new COSArrayList(arrayList, cOSArray);
        }
        return null;
    }

    public void setSubFilter(List<COSName> list) {
        this.dictionary.setItem(COSName.SUB_FILTER, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public List<String> getDigestMethod() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.DIGEST_METHOD);
        if (cOSArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < cOSArray.size(); i++) {
                String name = cOSArray.getName(i);
                if (name != null) {
                    arrayList.add(name);
                }
            }
            return new COSArrayList(arrayList, cOSArray);
        }
        return null;
    }

    public void setDigestMethod(List<COSName> list) {
        for (COSName cOSName : list) {
            if (!cOSName.equals(COSName.DIGEST_SHA1) && !cOSName.equals(COSName.DIGEST_SHA256) && !cOSName.equals(COSName.DIGEST_SHA384) && !cOSName.equals(COSName.DIGEST_SHA512) && !cOSName.equals(COSName.DIGEST_RIPEMD160)) {
                throw new IllegalArgumentException("Specified digest " + cOSName.getName() + " isn't allowed.");
            }
        }
        this.dictionary.setItem(COSName.DIGEST_METHOD, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public float getV() {
        return this.dictionary.getFloat(COSName.V);
    }

    public void setV(float f) {
        this.dictionary.setFloat(COSName.V, f);
    }

    public List<String> getReasons() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.REASONS);
        if (cOSArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < cOSArray.size(); i++) {
                String string = cOSArray.getString(i);
                if (string != null) {
                    arrayList.add(string);
                }
            }
            return new COSArrayList(arrayList, cOSArray);
        }
        return null;
    }

    @Deprecated
    public void setReasonsd(List<String> list) {
        setReasons(list);
    }

    public void setReasons(List<String> list) {
        this.dictionary.setItem(COSName.REASONS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public PDSeedValueMDP getMDP() {
        COSDictionary cOSDictionary = this.dictionary.getCOSDictionary(COSName.MDP);
        if (cOSDictionary != null) {
            return new PDSeedValueMDP(cOSDictionary);
        }
        return null;
    }

    public void setMPD(PDSeedValueMDP pDSeedValueMDP) {
        if (pDSeedValueMDP != null) {
            this.dictionary.setItem(COSName.MDP, (COSBase) pDSeedValueMDP.getCOSObject());
        }
    }

    public PDSeedValueCertificate getSeedValueCertificate() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.CERT);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDSeedValueCertificate((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setSeedValueCertificate(PDSeedValueCertificate pDSeedValueCertificate) {
        this.dictionary.setItem(COSName.CERT, pDSeedValueCertificate);
    }

    public PDSeedValueTimeStamp getTimeStamp() {
        COSDictionary cOSDictionary = this.dictionary.getCOSDictionary(COSName.TIME_STAMP);
        if (cOSDictionary != null) {
            return new PDSeedValueTimeStamp(cOSDictionary);
        }
        return null;
    }

    public void setTimeStamp(PDSeedValueTimeStamp pDSeedValueTimeStamp) {
        if (pDSeedValueTimeStamp != null) {
            this.dictionary.setItem(COSName.TIME_STAMP, (COSBase) pDSeedValueTimeStamp.getCOSObject());
        }
    }

    public List<String> getLegalAttestation() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.LEGAL_ATTESTATION);
        if (cOSArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < cOSArray.size(); i++) {
                String string = cOSArray.getString(i);
                if (string != null) {
                    arrayList.add(string);
                }
            }
            return new COSArrayList(arrayList, cOSArray);
        }
        return null;
    }

    public void setLegalAttestation(List<String> list) {
        this.dictionary.setItem(COSName.LEGAL_ATTESTATION, (COSBase) COSArrayList.converterToCOSArray(list));
    }
}
