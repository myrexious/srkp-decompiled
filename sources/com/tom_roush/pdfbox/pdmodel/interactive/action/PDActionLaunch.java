package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSBoolean;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDActionLaunch extends PDAction {
    public static final String SUB_TYPE = "Launch";

    public PDActionLaunch() {
        setSubType(SUB_TYPE);
    }

    public PDActionLaunch(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(getCOSObject().getDictionaryObject(COSName.F));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        getCOSObject().setItem(COSName.F, pDFileSpecification);
    }

    public PDWindowsLaunchParams getWinLaunchParams() {
        COSDictionary cOSDictionary = (COSDictionary) this.action.getDictionaryObject("Win");
        if (cOSDictionary != null) {
            return new PDWindowsLaunchParams(cOSDictionary);
        }
        return null;
    }

    public void setWinLaunchParams(PDWindowsLaunchParams pDWindowsLaunchParams) {
        this.action.setItem("Win", pDWindowsLaunchParams);
    }

    public String getF() {
        return this.action.getString(COSName.F);
    }

    public void setF(String str) {
        this.action.setString(COSName.F, str);
    }

    public String getD() {
        return this.action.getString(COSName.D);
    }

    public void setD(String str) {
        this.action.setString(COSName.D, str);
    }

    public String getO() {
        return this.action.getString(COSName.O);
    }

    public void setO(String str) {
        this.action.setString(COSName.O, str);
    }

    public String getP() {
        return this.action.getString(COSName.P);
    }

    public void setP(String str) {
        this.action.setString(COSName.P, str);
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

    /* renamed from: com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionLaunch$1 */
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
