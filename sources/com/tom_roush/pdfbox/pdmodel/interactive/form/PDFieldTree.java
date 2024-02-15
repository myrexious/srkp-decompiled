package com.tom_roush.pdfbox.pdmodel.interactive.form;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSDictionary;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

/* loaded from: classes3.dex */
public class PDFieldTree implements Iterable<PDField> {
    private final PDAcroForm acroForm;

    public PDFieldTree(PDAcroForm pDAcroForm) {
        if (pDAcroForm == null) {
            throw new IllegalArgumentException("root cannot be null");
        }
        this.acroForm = pDAcroForm;
    }

    @Override // java.lang.Iterable
    public Iterator<PDField> iterator() {
        return new FieldIterator(this.acroForm);
    }

    /* loaded from: classes3.dex */
    public static final class FieldIterator implements Iterator<PDField> {
        private final Queue<PDField> queue;
        private final Set<COSDictionary> set;

        private FieldIterator(PDAcroForm pDAcroForm) {
            this.queue = new ArrayDeque();
            this.set = Collections.newSetFromMap(new IdentityHashMap());
            for (PDField pDField : pDAcroForm.getFields()) {
                enqueueKids(pDField);
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override // java.util.Iterator
        public PDField next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return this.queue.poll();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void enqueueKids(PDField pDField) {
            this.queue.add(pDField);
            this.set.add(pDField.getCOSObject());
            if (pDField instanceof PDNonTerminalField) {
                for (PDField pDField2 : ((PDNonTerminalField) pDField).getChildren()) {
                    if (this.set.contains(pDField2.getCOSObject())) {
                        Log.e("PdfBox-Android", "Child of field '" + pDField.getFullyQualifiedName() + "' already exists elsewhere, ignored to avoid recursion");
                    } else {
                        enqueueKids(pDField2);
                    }
                }
            }
        }
    }
}
