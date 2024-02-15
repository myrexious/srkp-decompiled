package com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.outline;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes3.dex */
class PDOutlineItemIterator implements Iterator<PDOutlineItem> {
    private PDOutlineItem currentItem;
    private final PDOutlineItem startingItem;

    public PDOutlineItemIterator(PDOutlineItem pDOutlineItem) {
        this.startingItem = pDOutlineItem;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.startingItem == null) {
            return false;
        }
        PDOutlineItem pDOutlineItem = this.currentItem;
        if (pDOutlineItem == null) {
            return true;
        }
        PDOutlineItem nextSibling = pDOutlineItem.getNextSibling();
        return (nextSibling == null || this.startingItem.equals(nextSibling)) ? false : true;
    }

    @Override // java.util.Iterator
    public PDOutlineItem next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        PDOutlineItem pDOutlineItem = this.currentItem;
        if (pDOutlineItem == null) {
            this.currentItem = this.startingItem;
        } else {
            this.currentItem = pDOutlineItem.getNextSibling();
        }
        return this.currentItem;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
