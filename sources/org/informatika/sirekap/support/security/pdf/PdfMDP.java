package org.informatika.sirekap.support.security.pdf;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PdfMDP.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J \u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016¨\u0006\f"}, d2 = {"Lorg/informatika/sirekap/support/security/pdf/PdfMDP;", "Lorg/informatika/sirekap/support/security/pdf/PdfMDPSetter;", "()V", "getPermission", "Lorg/informatika/sirekap/support/security/pdf/PdfMDPPermission;", "document", "Lcom/tom_roush/pdfbox/pdmodel/PDDocument;", "setPermission", "", "signature", "Lcom/tom_roush/pdfbox/pdmodel/interactive/digitalsignature/PDSignature;", "permission", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PdfMDP implements PdfMDPSetter {

    /* compiled from: PdfMDP.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PdfMDPPermission.values().length];
            try {
                iArr[PdfMDPPermission.AllowChange.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PdfMDPPermission.AllowSignatureOrFormChange.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PdfMDPPermission.NoChange.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final PdfMDPPermission getPermission(PDDocument document) {
        COSDictionary cOSDictionary;
        COSArray cOSArray;
        Intrinsics.checkNotNullParameter(document, "document");
        COSDictionary cOSDictionary2 = document.getDocumentCatalog().getCOSObject().getCOSDictionary(COSName.PERMS);
        if (cOSDictionary2 != null && (cOSDictionary = cOSDictionary2.getCOSDictionary(COSName.DOCMDP)) != null && (cOSArray = cOSDictionary.getCOSArray(COSName.REFERENCE)) != null) {
            Iterator<COSBase> it = cOSArray.iterator();
            while (it.hasNext()) {
                COSBase next = it.next();
                if (next instanceof COSDictionary) {
                    COSDictionary cOSDictionary3 = (COSDictionary) next;
                    if (Intrinsics.areEqual(COSName.DOCMDP, cOSDictionary3.getDictionaryObject(COSName.TRANSFORM_METHOD))) {
                        COSBase dictionaryObject = cOSDictionary3.getDictionaryObject(COSName.TRANSFORM_PARAMS);
                        if (dictionaryObject instanceof COSDictionary) {
                            int i = ((COSDictionary) dictionaryObject).getInt(COSName.P, 2);
                            if (i != 1) {
                                if (i == 3) {
                                    return PdfMDPPermission.AllowChange;
                                }
                                return PdfMDPPermission.AllowSignatureOrFormChange;
                            }
                            return PdfMDPPermission.NoChange;
                        }
                    } else {
                        continue;
                    }
                }
            }
            return PdfMDPPermission.NotExist;
        }
        return PdfMDPPermission.NotExist;
    }

    @Override // org.informatika.sirekap.support.security.pdf.PdfMDPSetter
    public void setPermission(PDDocument document, PDSignature signature, PdfMDPPermission permission) {
        Intrinsics.checkNotNullParameter(document, "document");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(permission, "permission");
        for (PDSignature pDSignature : document.getSignatureDictionaries()) {
            Intrinsics.areEqual(COSName.DOC_TIME_STAMP, pDSignature.getCOSObject().getItem(COSName.TYPE));
        }
        int i = WhenMappings.$EnumSwitchMapping$0[permission.ordinal()];
        int i2 = i != 1 ? (i == 2 || i != 3) ? 2 : 1 : 3;
        COSDictionary cOSDictionary = new COSDictionary();
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.TRANSFORM_PARAMS);
        cOSDictionary.setInt(COSName.P, i2);
        cOSDictionary.setName(COSName.V, "1.2");
        cOSDictionary.setNeedToBeUpdated(true);
        cOSDictionary.setDirect(true);
        COSDictionary cOSDictionary2 = new COSDictionary();
        cOSDictionary2.setItem(COSName.TYPE, (COSBase) COSName.SIG_REF);
        cOSDictionary2.setItem(COSName.TRANSFORM_METHOD, (COSBase) COSName.DOCMDP);
        cOSDictionary2.setItem(COSName.DIGEST_METHOD, (COSBase) COSName.getPDFName("SHA1"));
        cOSDictionary2.setItem(COSName.TRANSFORM_PARAMS, (COSBase) cOSDictionary);
        cOSDictionary2.setNeedToBeUpdated(true);
        cOSDictionary2.setDirect(true);
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) cOSDictionary2);
        cOSArray.setNeedToBeUpdated(true);
        cOSArray.setDirect(true);
        signature.getCOSObject().setItem(COSName.REFERENCE, (COSBase) cOSArray);
        COSDictionary cOSDictionary3 = new COSDictionary();
        cOSDictionary3.setItem(COSName.DOCMDP, signature);
        cOSDictionary3.setNeedToBeUpdated(true);
        COSDictionary cOSObject = document.getDocumentCatalog().getCOSObject();
        cOSObject.setItem(COSName.PERMS, (COSBase) cOSDictionary3);
        cOSObject.setNeedToBeUpdated(true);
    }
}
