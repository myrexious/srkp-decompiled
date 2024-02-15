package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSBoolean;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDActionRemoteGoTo extends PDAction {
    public static final String SUB_TYPE = "GoToR";

    public PDActionRemoteGoTo() {
        setSubType(SUB_TYPE);
    }

    public PDActionRemoteGoTo(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    @Deprecated
    public String getS() {
        return this.action.getNameAsString(COSName.S);
    }

    @Deprecated
    public void setS(String str) {
        this.action.setName(COSName.S, str);
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(this.action.getDictionaryObject(COSName.F));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        this.action.setItem(COSName.F, pDFileSpecification);
    }

    public COSBase getD() {
        return this.action.getDictionaryObject(COSName.D);
    }

    public void setD(COSBase cOSBase) {
        this.action.setItem(COSName.D, cOSBase);
    }

    @Deprecated
    public boolean shouldOpenInNewWindow() {
        return this.action.getBoolean(COSName.NEW_WINDOW, true);
    }

    @Deprecated
    public void setOpenInNewWindow(boolean z) {
        this.action.setBoolean(COSName.NEW_WINDOW, z);
    }

    public OpenMode getOpenInNewWindow() {
        if (getCOSObject().getDictionaryObject(COSName.NEW_WINDOW) instanceof COSBoolean) {
            return ((COSBoolean) getCOSObject().getDictionaryObject(COSName.NEW_WINDOW)).getValue() ? OpenMode.NEW_WINDOW : OpenMode.SAME_WINDOW;
        }
        return OpenMode.USER_PREFERENCE;
    }

    public void setOpenInNewWindow(OpenMode openMode) {
        if (openMode == null) {
            getCOSObject().removeItem(COSName.NEW_WINDOW);
            return;
        }
        int i = AnonymousClass1.$SwitchMap$com$tom_roush$pdfbox$pdmodel$interactive$action$OpenMode[openMode.ordinal()];
        if (i == 1) {
            getCOSObject().removeItem(COSName.NEW_WINDOW);
        } else if (i == 2) {
            getCOSObject().setBoolean(COSName.NEW_WINDOW, false);
        } else if (i != 3) {
        } else {
            getCOSObject().setBoolean(COSName.NEW_WINDOW, true);
        }
    }

    /* renamed from: com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionRemoteGoTo$1 */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$tom_roush$pdfbox$pdmodel$interactive$action$OpenMode;

        static {
            int[] iArr = new int[OpenMode.values().length];
            $SwitchMap$com$tom_roush$pdfbox$pdmodel$interactive$action$OpenMode = iArr;
            try {
                iArr[OpenMode.USER_PREFERENCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tom_roush$pdfbox$pdmodel$interactive$action$OpenMode[OpenMode.SAME_WINDOW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tom_roush$pdfbox$pdmodel$interactive$action$OpenMode[OpenMode.NEW_WINDOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
