package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSBoolean;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDActionEmbeddedGoTo extends PDAction {
    public static final String SUB_TYPE = "GoToE";

    public PDActionEmbeddedGoTo() {
        setSubType(SUB_TYPE);
    }

    public PDActionEmbeddedGoTo(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public PDDestination getDestination() throws IOException {
        return PDDestination.create(getCOSObject().getDictionaryObject(COSName.D));
    }

    public void setDestination(PDDestination pDDestination) {
        if (pDDestination instanceof PDPageDestination) {
            COSArray cOSObject = ((PDPageDestination) pDDestination).getCOSObject();
            if (cOSObject.size() >= 1 && !(cOSObject.getObject(0) instanceof COSInteger)) {
                throw new IllegalArgumentException("Destination of a GoToE action must be an integer");
            }
        }
        getCOSObject().setItem(COSName.D, pDDestination);
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(getCOSObject().getDictionaryObject(COSName.F));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        getCOSObject().setItem(COSName.F, pDFileSpecification);
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

    /* renamed from: com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionEmbeddedGoTo$1 */
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

    public PDTargetDirectory getTargetDirectory() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.T);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDTargetDirectory((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setTargetDirectory(PDTargetDirectory pDTargetDirectory) {
        getCOSObject().setItem(COSName.T, pDTargetDirectory);
    }
}
