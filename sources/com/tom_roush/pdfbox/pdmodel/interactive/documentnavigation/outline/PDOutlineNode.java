package com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDDictionaryWrapper;
import java.util.Iterator;

/* loaded from: classes3.dex */
public abstract class PDOutlineNode extends PDDictionaryWrapper {
    public PDOutlineNode() {
    }

    public PDOutlineNode(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public PDOutlineNode getParent() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.PARENT);
        if (dictionaryObject instanceof COSDictionary) {
            COSDictionary cOSDictionary = (COSDictionary) dictionaryObject;
            if (COSName.OUTLINES.equals(cOSDictionary.getCOSName(COSName.TYPE))) {
                return new PDDocumentOutline(cOSDictionary);
            }
            return new PDOutlineItem(cOSDictionary);
        }
        return null;
    }

    public void setParent(PDOutlineNode pDOutlineNode) {
        getCOSObject().setItem(COSName.PARENT, pDOutlineNode);
    }

    public void addLast(PDOutlineItem pDOutlineItem) {
        requireSingleNode(pDOutlineItem);
        append(pDOutlineItem);
        updateParentOpenCountForAddedChild(pDOutlineItem);
    }

    public void addFirst(PDOutlineItem pDOutlineItem) {
        requireSingleNode(pDOutlineItem);
        prepend(pDOutlineItem);
        updateParentOpenCountForAddedChild(pDOutlineItem);
    }

    public void requireSingleNode(PDOutlineItem pDOutlineItem) {
        if (pDOutlineItem.getNextSibling() != null || pDOutlineItem.getPreviousSibling() != null) {
            throw new IllegalArgumentException("A single node with no siblings is required");
        }
    }

    private void append(PDOutlineItem pDOutlineItem) {
        pDOutlineItem.setParent(this);
        if (!hasChildren()) {
            setFirstChild(pDOutlineItem);
        } else {
            PDOutlineItem lastChild = getLastChild();
            lastChild.setNextSibling(pDOutlineItem);
            pDOutlineItem.setPreviousSibling(lastChild);
        }
        setLastChild(pDOutlineItem);
    }

    private void prepend(PDOutlineItem pDOutlineItem) {
        pDOutlineItem.setParent(this);
        if (!hasChildren()) {
            setLastChild(pDOutlineItem);
        } else {
            PDOutlineItem firstChild = getFirstChild();
            pDOutlineItem.setNextSibling(firstChild);
            firstChild.setPreviousSibling(pDOutlineItem);
        }
        setFirstChild(pDOutlineItem);
    }

    public void updateParentOpenCountForAddedChild(PDOutlineItem pDOutlineItem) {
        pDOutlineItem.updateParentOpenCount(pDOutlineItem.isNodeOpen() ? 1 + pDOutlineItem.getOpenCount() : 1);
    }

    public boolean hasChildren() {
        return getCOSObject().getCOSDictionary(COSName.FIRST) != null;
    }

    public PDOutlineItem getOutlineItem(COSName cOSName) {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(cOSName);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDOutlineItem((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public PDOutlineItem getFirstChild() {
        return getOutlineItem(COSName.FIRST);
    }

    public void setFirstChild(PDOutlineNode pDOutlineNode) {
        getCOSObject().setItem(COSName.FIRST, pDOutlineNode);
    }

    public PDOutlineItem getLastChild() {
        return getOutlineItem(COSName.LAST);
    }

    public void setLastChild(PDOutlineNode pDOutlineNode) {
        getCOSObject().setItem(COSName.LAST, pDOutlineNode);
    }

    public int getOpenCount() {
        return getCOSObject().getInt(COSName.COUNT, 0);
    }

    void setOpenCount(int i) {
        getCOSObject().setInt(COSName.COUNT, i);
    }

    public void openNode() {
        if (isNodeOpen()) {
            return;
        }
        switchNodeCount();
    }

    public void closeNode() {
        if (isNodeOpen()) {
            switchNodeCount();
        }
    }

    private void switchNodeCount() {
        int i = -getOpenCount();
        setOpenCount(i);
        updateParentOpenCount(i);
    }

    public boolean isNodeOpen() {
        return getOpenCount() > 0;
    }

    void updateParentOpenCount(int i) {
        PDOutlineNode parent = getParent();
        if (parent != null) {
            if (parent.isNodeOpen()) {
                parent.setOpenCount(parent.getOpenCount() + i);
                parent.updateParentOpenCount(i);
                return;
            }
            parent.setOpenCount(parent.getOpenCount() - i);
        }
    }

    public Iterable<PDOutlineItem> children() {
        return new Iterable<PDOutlineItem>() { // from class: com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode.1
            @Override // java.lang.Iterable
            public Iterator<PDOutlineItem> iterator() {
                return new PDOutlineItemIterator(PDOutlineNode.this.getFirstChild());
            }
        };
    }
}
