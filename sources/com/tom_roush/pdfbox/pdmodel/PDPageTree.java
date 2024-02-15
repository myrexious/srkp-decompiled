package com.tom_roush.pdfbox.pdmodel;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

/* loaded from: classes3.dex */
public class PDPageTree implements COSObjectable, Iterable<PDPage> {
    private final PDDocument document;
    private final Set<COSDictionary> pageSet;
    private final COSDictionary root;

    public PDPageTree() {
        this.pageSet = new HashSet();
        COSDictionary cOSDictionary = new COSDictionary();
        this.root = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.PAGES);
        cOSDictionary.setItem(COSName.KIDS, (COSBase) new COSArray());
        cOSDictionary.setItem(COSName.COUNT, (COSBase) COSInteger.ZERO);
        this.document = null;
    }

    public PDPageTree(COSDictionary cOSDictionary) {
        this(cOSDictionary, null);
    }

    public PDPageTree(COSDictionary cOSDictionary, PDDocument pDDocument) {
        this.pageSet = new HashSet();
        if (cOSDictionary == null) {
            throw new IllegalArgumentException("page tree root cannot be null");
        }
        if (COSName.PAGE.equals(cOSDictionary.getCOSName(COSName.TYPE))) {
            COSArray cOSArray = new COSArray();
            cOSArray.add((COSBase) cOSDictionary);
            COSDictionary cOSDictionary2 = new COSDictionary();
            this.root = cOSDictionary2;
            cOSDictionary2.setItem(COSName.KIDS, (COSBase) cOSArray);
            cOSDictionary2.setInt(COSName.COUNT, 1);
        } else {
            this.root = cOSDictionary;
        }
        this.document = pDDocument;
    }

    public static COSBase getInheritableAttribute(COSDictionary cOSDictionary, COSName cOSName) {
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(cOSName);
        if (dictionaryObject != null) {
            return dictionaryObject;
        }
        COSBase dictionaryObject2 = cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.P);
        if (dictionaryObject2 instanceof COSDictionary) {
            COSDictionary cOSDictionary2 = (COSDictionary) dictionaryObject2;
            if (COSName.PAGES.equals(cOSDictionary2.getDictionaryObject(COSName.TYPE))) {
                return getInheritableAttribute(cOSDictionary2, cOSName);
            }
            return null;
        }
        return null;
    }

    @Override // java.lang.Iterable
    public Iterator<PDPage> iterator() {
        return new PageIterator(this.root);
    }

    public List<COSDictionary> getKids(COSDictionary cOSDictionary) {
        ArrayList arrayList = new ArrayList();
        COSArray cOSArray = cOSDictionary.getCOSArray(COSName.KIDS);
        if (cOSArray == null) {
            return arrayList;
        }
        int size = cOSArray.size();
        for (int i = 0; i < size; i++) {
            COSBase object = cOSArray.getObject(i);
            if (object instanceof COSDictionary) {
                arrayList.add((COSDictionary) object);
            } else {
                Log.w("PdfBox-Android", "COSDictionary expected, but got " + (object == null ? "null" : object.getClass().getSimpleName()));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class PageIterator implements Iterator<PDPage> {
        private final Queue<COSDictionary> queue;
        private Set<COSDictionary> set;

        private PageIterator(COSDictionary cOSDictionary) {
            PDPageTree.this = r1;
            this.queue = new ArrayDeque();
            this.set = new HashSet();
            enqueueKids(cOSDictionary);
            this.set = null;
        }

        private void enqueueKids(COSDictionary cOSDictionary) {
            if (PDPageTree.this.isPageTreeNode(cOSDictionary)) {
                for (COSDictionary cOSDictionary2 : PDPageTree.this.getKids(cOSDictionary)) {
                    if (this.set.contains(cOSDictionary2)) {
                        Log.e("PdfBox-Android", "This page tree node has already been visited");
                    } else {
                        if (cOSDictionary2.containsKey(COSName.KIDS)) {
                            this.set.add(cOSDictionary2);
                        }
                        enqueueKids(cOSDictionary2);
                    }
                }
            } else if (COSName.PAGE.equals(cOSDictionary.getCOSName(COSName.TYPE))) {
                this.queue.add(cOSDictionary);
            } else {
                Log.e("PdfBox-Android", "Page skipped due to an invalid or missing type " + cOSDictionary.getCOSName(COSName.TYPE));
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override // java.util.Iterator
        public PDPage next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            COSDictionary poll = this.queue.poll();
            PDPageTree.sanitizeType(poll);
            return new PDPage(poll, PDPageTree.this.document != null ? PDPageTree.this.document.getResourceCache() : null);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public PDPage get(int i) {
        COSDictionary cOSDictionary = get(i + 1, this.root, 0);
        sanitizeType(cOSDictionary);
        PDDocument pDDocument = this.document;
        return new PDPage(cOSDictionary, pDDocument != null ? pDDocument.getResourceCache() : null);
    }

    public static void sanitizeType(COSDictionary cOSDictionary) {
        COSName cOSName = cOSDictionary.getCOSName(COSName.TYPE);
        if (cOSName == null) {
            cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.PAGE);
        } else if (!COSName.PAGE.equals(cOSName)) {
            throw new IllegalStateException("Expected 'Page' but found " + cOSName);
        }
    }

    private COSDictionary get(int i, COSDictionary cOSDictionary, int i2) {
        if (i < 1) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }
        if (this.pageSet.contains(cOSDictionary)) {
            this.pageSet.clear();
            throw new IllegalStateException("Possible recursion found when searching for page " + i);
        }
        this.pageSet.add(cOSDictionary);
        if (isPageTreeNode(cOSDictionary)) {
            if (i <= cOSDictionary.getInt(COSName.COUNT, 0) + i2) {
                for (COSDictionary cOSDictionary2 : getKids(cOSDictionary)) {
                    if (isPageTreeNode(cOSDictionary2)) {
                        int i3 = cOSDictionary2.getInt(COSName.COUNT, 0) + i2;
                        if (i <= i3) {
                            return get(i, cOSDictionary2, i2);
                        }
                        i2 = i3;
                    } else {
                        i2++;
                        if (i == i2) {
                            return get(i, cOSDictionary2, i2);
                        }
                    }
                }
                throw new IllegalStateException("1-based index not found: " + i);
            } else {
                throw new IndexOutOfBoundsException("1-based index out of bounds: " + i);
            }
        }
        if (i2 == i) {
            this.pageSet.clear();
            return cOSDictionary;
        }
        throw new IllegalStateException("1-based index not found: " + i);
    }

    public boolean isPageTreeNode(COSDictionary cOSDictionary) {
        return cOSDictionary != null && (cOSDictionary.getCOSName(COSName.TYPE) == COSName.PAGES || cOSDictionary.containsKey(COSName.KIDS));
    }

    public int indexOf(PDPage pDPage) {
        SearchContext searchContext = new SearchContext(pDPage);
        if (findPage(searchContext, this.root)) {
            return searchContext.index;
        }
        return -1;
    }

    private boolean findPage(SearchContext searchContext, COSDictionary cOSDictionary) {
        for (COSDictionary cOSDictionary2 : getKids(cOSDictionary)) {
            if (searchContext.found) {
                break;
            } else if (isPageTreeNode(cOSDictionary2)) {
                findPage(searchContext, cOSDictionary2);
            } else {
                searchContext.visitPage(cOSDictionary2);
            }
        }
        return searchContext.found;
    }

    /* loaded from: classes3.dex */
    public static final class SearchContext {
        private boolean found;
        private int index;
        private final COSDictionary searched;

        private SearchContext(PDPage pDPage) {
            this.index = -1;
            this.searched = pDPage.getCOSObject();
        }

        public void visitPage(COSDictionary cOSDictionary) {
            this.index++;
            this.found = this.searched == cOSDictionary;
        }
    }

    public int getCount() {
        return this.root.getInt(COSName.COUNT, 0);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.root;
    }

    public void remove(int i) {
        remove(get(i + 1, this.root, 0));
    }

    public void remove(PDPage pDPage) {
        remove(pDPage.getCOSObject());
    }

    private void remove(COSDictionary cOSDictionary) {
        if (((COSArray) ((COSDictionary) cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.P)).getDictionaryObject(COSName.KIDS)).removeObject(cOSDictionary)) {
            do {
                cOSDictionary = (COSDictionary) cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.P);
                if (cOSDictionary != null) {
                    cOSDictionary.setInt(COSName.COUNT, cOSDictionary.getInt(COSName.COUNT) - 1);
                    continue;
                }
            } while (cOSDictionary != null);
        }
    }

    public void add(PDPage pDPage) {
        COSDictionary cOSObject = pDPage.getCOSObject();
        cOSObject.setItem(COSName.PARENT, (COSBase) this.root);
        ((COSArray) this.root.getDictionaryObject(COSName.KIDS)).add((COSBase) cOSObject);
        do {
            cOSObject = (COSDictionary) cOSObject.getDictionaryObject(COSName.PARENT, COSName.P);
            if (cOSObject != null) {
                cOSObject.setInt(COSName.COUNT, cOSObject.getInt(COSName.COUNT) + 1);
                continue;
            }
        } while (cOSObject != null);
    }

    public void insertBefore(PDPage pDPage, PDPage pDPage2) {
        COSDictionary cOSDictionary = (COSDictionary) pDPage2.getCOSObject().getDictionaryObject(COSName.PARENT);
        COSArray cOSArray = (COSArray) cOSDictionary.getDictionaryObject(COSName.KIDS);
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= cOSArray.size()) {
                break;
            } else if (((COSDictionary) cOSArray.getObject(i)).equals(pDPage2.getCOSObject())) {
                cOSArray.add(i, pDPage.getCOSObject());
                pDPage.getCOSObject().setItem(COSName.PARENT, (COSBase) cOSDictionary);
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            throw new IllegalArgumentException("attempted to insert before orphan page");
        }
        increaseParents(cOSDictionary);
    }

    public void insertAfter(PDPage pDPage, PDPage pDPage2) {
        COSDictionary cOSDictionary = (COSDictionary) pDPage2.getCOSObject().getDictionaryObject(COSName.PARENT);
        COSArray cOSArray = (COSArray) cOSDictionary.getDictionaryObject(COSName.KIDS);
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= cOSArray.size()) {
                break;
            } else if (((COSDictionary) cOSArray.getObject(i)).equals(pDPage2.getCOSObject())) {
                z = true;
                cOSArray.add(i + 1, pDPage.getCOSObject());
                pDPage.getCOSObject().setItem(COSName.PARENT, (COSBase) cOSDictionary);
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            throw new IllegalArgumentException("attempted to insert before orphan page");
        }
        increaseParents(cOSDictionary);
    }

    private void increaseParents(COSDictionary cOSDictionary) {
        do {
            cOSDictionary.setInt(COSName.COUNT, cOSDictionary.getInt(COSName.COUNT) + 1);
            cOSDictionary = (COSDictionary) cOSDictionary.getDictionaryObject(COSName.PARENT);
        } while (cOSDictionary != null);
    }
}
