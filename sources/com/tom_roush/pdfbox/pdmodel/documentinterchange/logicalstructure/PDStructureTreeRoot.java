package com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDStructureElementNameTreeNode;
import com.tom_roush.pdfbox.pdmodel.common.COSDictionaryMap;
import com.tom_roush.pdfbox.pdmodel.common.PDNameTreeNode;
import com.tom_roush.pdfbox.pdmodel.common.PDNumberTreeNode;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class PDStructureTreeRoot extends PDStructureNode {
    private static final String TYPE = "StructTreeRoot";

    public PDStructureTreeRoot() {
        super(TYPE);
    }

    public PDStructureTreeRoot(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    @Deprecated
    public COSArray getKArray() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.K);
        if (dictionaryObject instanceof COSDictionary) {
            COSBase dictionaryObject2 = ((COSDictionary) dictionaryObject).getDictionaryObject(COSName.K);
            if (dictionaryObject2 instanceof COSArray) {
                return (COSArray) dictionaryObject2;
            }
            return null;
        } else if (dictionaryObject instanceof COSArray) {
            return (COSArray) dictionaryObject;
        } else {
            return null;
        }
    }

    public COSBase getK() {
        return getCOSObject().getDictionaryObject(COSName.K);
    }

    public void setK(COSBase cOSBase) {
        getCOSObject().setItem(COSName.K, cOSBase);
    }

    public PDNameTreeNode<PDStructureElement> getIDTree() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.ID_TREE);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDStructureElementNameTreeNode((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setIDTree(PDNameTreeNode<PDStructureElement> pDNameTreeNode) {
        getCOSObject().setItem(COSName.ID_TREE, pDNameTreeNode);
    }

    public PDNumberTreeNode getParentTree() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.PARENT_TREE);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDNumberTreeNode((COSDictionary) dictionaryObject, PDParentTreeValue.class);
        }
        return null;
    }

    public void setParentTree(PDNumberTreeNode pDNumberTreeNode) {
        getCOSObject().setItem(COSName.PARENT_TREE, pDNumberTreeNode);
    }

    public int getParentTreeNextKey() {
        return getCOSObject().getInt(COSName.PARENT_TREE_NEXT_KEY);
    }

    public void setParentTreeNextKey(int i) {
        getCOSObject().setInt(COSName.PARENT_TREE_NEXT_KEY, i);
    }

    public Map<String, Object> getRoleMap() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.ROLE_MAP);
        if (dictionaryObject instanceof COSDictionary) {
            try {
                return COSDictionaryMap.convertBasicTypesToMap((COSDictionary) dictionaryObject);
            } catch (IOException e) {
                Log.e("PdfBox-Android", e.getMessage(), e);
            }
        }
        return new HashMap();
    }

    public void setRoleMap(Map<String, String> map) {
        COSDictionary cOSDictionary = new COSDictionary();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            cOSDictionary.setName(entry.getKey(), entry.getValue());
        }
        getCOSObject().setItem(COSName.ROLE_MAP, (COSBase) cOSDictionary);
    }
}
