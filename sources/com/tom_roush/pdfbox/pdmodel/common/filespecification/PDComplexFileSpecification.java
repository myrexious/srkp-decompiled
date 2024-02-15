package com.tom_roush.pdfbox.pdmodel.common.filespecification;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;

/* loaded from: classes3.dex */
public class PDComplexFileSpecification extends PDFileSpecification {
    private COSDictionary efDictionary;
    private final COSDictionary fs;

    public PDComplexFileSpecification() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.fs = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.FILESPEC);
    }

    public PDComplexFileSpecification(COSDictionary cOSDictionary) {
        if (cOSDictionary == null) {
            COSDictionary cOSDictionary2 = new COSDictionary();
            this.fs = cOSDictionary2;
            cOSDictionary2.setItem(COSName.TYPE, (COSBase) COSName.FILESPEC);
            return;
        }
        this.fs = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.fs;
    }

    private COSDictionary getEFDictionary() {
        COSDictionary cOSDictionary;
        if (this.efDictionary == null && (cOSDictionary = this.fs) != null) {
            this.efDictionary = cOSDictionary.getCOSDictionary(COSName.EF);
        }
        return this.efDictionary;
    }

    private COSBase getObjectFromEFDictionary(COSName cOSName) {
        COSDictionary eFDictionary = getEFDictionary();
        if (eFDictionary != null) {
            return eFDictionary.getDictionaryObject(cOSName);
        }
        return null;
    }

    public String getFilename() {
        String fileUnicode = getFileUnicode();
        if (fileUnicode == null) {
            fileUnicode = getFileDos();
        }
        if (fileUnicode == null) {
            fileUnicode = getFileMac();
        }
        if (fileUnicode == null) {
            fileUnicode = getFileUnix();
        }
        return fileUnicode == null ? getFile() : fileUnicode;
    }

    public String getFileUnicode() {
        return this.fs.getString(COSName.UF);
    }

    public void setFileUnicode(String str) {
        this.fs.setString(COSName.UF, str);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification
    public String getFile() {
        return this.fs.getString(COSName.F);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification
    public void setFile(String str) {
        this.fs.setString(COSName.F, str);
    }

    public String getFileDos() {
        return this.fs.getString(COSName.DOS);
    }

    @Deprecated
    public void setFileDos(String str) {
        this.fs.setString(COSName.DOS, str);
    }

    public String getFileMac() {
        return this.fs.getString(COSName.MAC);
    }

    @Deprecated
    public void setFileMac(String str) {
        this.fs.setString(COSName.MAC, str);
    }

    public String getFileUnix() {
        return this.fs.getString(COSName.UNIX);
    }

    @Deprecated
    public void setFileUnix(String str) {
        this.fs.setString(COSName.UNIX, str);
    }

    public void setVolatile(boolean z) {
        this.fs.setBoolean(COSName.V, z);
    }

    public boolean isVolatile() {
        return this.fs.getBoolean(COSName.V, false);
    }

    public PDEmbeddedFile getEmbeddedFile() {
        COSBase objectFromEFDictionary = getObjectFromEFDictionary(COSName.F);
        if (objectFromEFDictionary instanceof COSStream) {
            return new PDEmbeddedFile((COSStream) objectFromEFDictionary);
        }
        return null;
    }

    public void setEmbeddedFile(PDEmbeddedFile pDEmbeddedFile) {
        COSDictionary eFDictionary = getEFDictionary();
        if (eFDictionary == null && pDEmbeddedFile != null) {
            eFDictionary = new COSDictionary();
            this.fs.setItem(COSName.EF, (COSBase) eFDictionary);
        }
        if (eFDictionary != null) {
            eFDictionary.setItem(COSName.F, pDEmbeddedFile);
        }
    }

    public PDEmbeddedFile getEmbeddedFileDos() {
        COSBase objectFromEFDictionary = getObjectFromEFDictionary(COSName.DOS);
        if (objectFromEFDictionary instanceof COSStream) {
            return new PDEmbeddedFile((COSStream) objectFromEFDictionary);
        }
        return null;
    }

    @Deprecated
    public void setEmbeddedFileDos(PDEmbeddedFile pDEmbeddedFile) {
        COSDictionary eFDictionary = getEFDictionary();
        if (eFDictionary == null && pDEmbeddedFile != null) {
            eFDictionary = new COSDictionary();
            this.fs.setItem(COSName.EF, (COSBase) eFDictionary);
        }
        if (eFDictionary != null) {
            eFDictionary.setItem(COSName.DOS, pDEmbeddedFile);
        }
    }

    public PDEmbeddedFile getEmbeddedFileMac() {
        COSBase objectFromEFDictionary = getObjectFromEFDictionary(COSName.MAC);
        if (objectFromEFDictionary instanceof COSStream) {
            return new PDEmbeddedFile((COSStream) objectFromEFDictionary);
        }
        return null;
    }

    @Deprecated
    public void setEmbeddedFileMac(PDEmbeddedFile pDEmbeddedFile) {
        COSDictionary eFDictionary = getEFDictionary();
        if (eFDictionary == null && pDEmbeddedFile != null) {
            eFDictionary = new COSDictionary();
            this.fs.setItem(COSName.EF, (COSBase) eFDictionary);
        }
        if (eFDictionary != null) {
            eFDictionary.setItem(COSName.MAC, pDEmbeddedFile);
        }
    }

    public PDEmbeddedFile getEmbeddedFileUnix() {
        COSBase objectFromEFDictionary = getObjectFromEFDictionary(COSName.UNIX);
        if (objectFromEFDictionary instanceof COSStream) {
            return new PDEmbeddedFile((COSStream) objectFromEFDictionary);
        }
        return null;
    }

    @Deprecated
    public void setEmbeddedFileUnix(PDEmbeddedFile pDEmbeddedFile) {
        COSDictionary eFDictionary = getEFDictionary();
        if (eFDictionary == null && pDEmbeddedFile != null) {
            eFDictionary = new COSDictionary();
            this.fs.setItem(COSName.EF, (COSBase) eFDictionary);
        }
        if (eFDictionary != null) {
            eFDictionary.setItem(COSName.UNIX, pDEmbeddedFile);
        }
    }

    public PDEmbeddedFile getEmbeddedFileUnicode() {
        COSBase objectFromEFDictionary = getObjectFromEFDictionary(COSName.UF);
        if (objectFromEFDictionary instanceof COSStream) {
            return new PDEmbeddedFile((COSStream) objectFromEFDictionary);
        }
        return null;
    }

    public void setEmbeddedFileUnicode(PDEmbeddedFile pDEmbeddedFile) {
        COSDictionary eFDictionary = getEFDictionary();
        if (eFDictionary == null && pDEmbeddedFile != null) {
            eFDictionary = new COSDictionary();
            this.fs.setItem(COSName.EF, (COSBase) eFDictionary);
        }
        if (eFDictionary != null) {
            eFDictionary.setItem(COSName.UF, pDEmbeddedFile);
        }
    }

    public void setFileDescription(String str) {
        this.fs.setString(COSName.DESC, str);
    }

    public String getFileDescription() {
        return this.fs.getString(COSName.DESC);
    }
}
