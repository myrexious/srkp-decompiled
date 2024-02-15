package com.fasterxml.jackson.databind.util.internal;

import com.fasterxml.jackson.databind.util.internal.Linked;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class LinkedDeque<E extends Linked<E>> extends AbstractCollection<E> implements Deque<E> {
    E first;
    E last;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque, java.util.Queue
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        return add((LinkedDeque<E>) ((Linked) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Deque
    public /* bridge */ /* synthetic */ void addFirst(Object obj) {
        addFirst((LinkedDeque<E>) ((Linked) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Deque
    public /* bridge */ /* synthetic */ void addLast(Object obj) {
        addLast((LinkedDeque<E>) ((Linked) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Deque, java.util.Queue
    public /* bridge */ /* synthetic */ boolean offer(Object obj) {
        return offer((LinkedDeque<E>) ((Linked) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Deque
    public /* bridge */ /* synthetic */ boolean offerFirst(Object obj) {
        return offerFirst((LinkedDeque<E>) ((Linked) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Deque
    public /* bridge */ /* synthetic */ boolean offerLast(Object obj) {
        return offerLast((LinkedDeque<E>) ((Linked) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Deque
    public /* bridge */ /* synthetic */ void push(Object obj) {
        push((LinkedDeque<E>) ((Linked) obj));
    }

    void linkFirst(E e) {
        E e2 = this.first;
        this.first = e;
        if (e2 == null) {
            this.last = e;
            return;
        }
        e2.setPrevious(e);
        e.setNext(e2);
    }

    void linkLast(E e) {
        E e2 = this.last;
        this.last = e;
        if (e2 == null) {
            this.first = e;
            return;
        }
        e2.setNext(e);
        e.setPrevious(e2);
    }

    E unlinkFirst() {
        E e = this.first;
        E e2 = (E) e.getNext();
        e.setNext(null);
        this.first = e2;
        if (e2 == null) {
            this.last = null;
        } else {
            e2.setPrevious(null);
        }
        return e;
    }

    E unlinkLast() {
        E e = this.last;
        E e2 = (E) e.getPrevious();
        e.setPrevious(null);
        this.last = e2;
        if (e2 == null) {
            this.first = null;
        } else {
            e2.setNext(null);
        }
        return e;
    }

    void unlink(E e) {
        E e2 = (E) e.getPrevious();
        E e3 = (E) e.getNext();
        if (e2 == null) {
            this.first = e3;
        } else {
            e2.setNext(e3);
            e.setPrevious(null);
        }
        if (e3 == null) {
            this.last = e2;
            return;
        }
        e3.setPrevious(e2);
        e.setNext(null);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.first == null;
    }

    void checkNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public int size() {
        int i = 0;
        for (Linked linked = this.first; linked != null; linked = linked.getNext()) {
            i++;
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [com.fasterxml.jackson.databind.util.internal.Linked] */
    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        E e = this.first;
        while (e != null) {
            ?? next = e.getNext();
            e.setPrevious(null);
            e.setNext(null);
            e = next;
        }
        this.last = null;
        this.first = null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public boolean contains(Object obj) {
        return (obj instanceof Linked) && contains((Linked) obj);
    }

    public boolean contains(Linked<?> linked) {
        return (linked.getPrevious() == null && linked.getNext() == null && linked != this.first) ? false : true;
    }

    public void moveToFront(E e) {
        if (e != this.first) {
            unlink(e);
            linkFirst(e);
        }
    }

    public void moveToBack(E e) {
        if (e != this.last) {
            unlink(e);
            linkLast(e);
        }
    }

    @Override // java.util.Deque, java.util.Queue
    public E peek() {
        return peekFirst();
    }

    @Override // java.util.Deque
    public E peekFirst() {
        return this.first;
    }

    @Override // java.util.Deque
    public E peekLast() {
        return this.last;
    }

    @Override // java.util.Deque
    public E getFirst() {
        checkNotEmpty();
        return peekFirst();
    }

    @Override // java.util.Deque
    public E getLast() {
        checkNotEmpty();
        return peekLast();
    }

    @Override // java.util.Deque, java.util.Queue
    public E element() {
        return getFirst();
    }

    public boolean offer(E e) {
        return offerLast((LinkedDeque<E>) e);
    }

    public boolean offerFirst(E e) {
        if (contains((Linked<?>) e)) {
            return false;
        }
        linkFirst(e);
        return true;
    }

    public boolean offerLast(E e) {
        if (contains((Linked<?>) e)) {
            return false;
        }
        linkLast(e);
        return true;
    }

    public boolean add(E e) {
        return offerLast((LinkedDeque<E>) e);
    }

    public void addFirst(E e) {
        if (!offerFirst((LinkedDeque<E>) e)) {
            throw new IllegalArgumentException();
        }
    }

    public void addLast(E e) {
        if (!offerLast((LinkedDeque<E>) e)) {
            throw new IllegalArgumentException();
        }
    }

    @Override // java.util.Deque, java.util.Queue
    public E poll() {
        return pollFirst();
    }

    @Override // java.util.Deque
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        return unlinkFirst();
    }

    @Override // java.util.Deque
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        return unlinkLast();
    }

    @Override // java.util.Deque, java.util.Queue
    public E remove() {
        return removeFirst();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public boolean remove(Object obj) {
        return (obj instanceof Linked) && remove((LinkedDeque<E>) ((Linked) obj));
    }

    public boolean remove(E e) {
        if (contains((Linked<?>) e)) {
            unlink(e);
            return true;
        }
        return false;
    }

    @Override // java.util.Deque
    public E removeFirst() {
        checkNotEmpty();
        return pollFirst();
    }

    @Override // java.util.Deque
    public boolean removeFirstOccurrence(Object obj) {
        return remove(obj);
    }

    @Override // java.util.Deque
    public E removeLast() {
        checkNotEmpty();
        return pollLast();
    }

    @Override // java.util.Deque
    public boolean removeLastOccurrence(Object obj) {
        return remove(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= remove(it.next());
        }
        return z;
    }

    public void push(E e) {
        addFirst((LinkedDeque<E>) e);
    }

    @Override // java.util.Deque
    public E pop() {
        return removeFirst();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Deque
    public Iterator<E> iterator() {
        return new LinkedDeque<E>.AbstractLinkedIterator(this.first) { // from class: com.fasterxml.jackson.databind.util.internal.LinkedDeque.1
            @Override // com.fasterxml.jackson.databind.util.internal.LinkedDeque.AbstractLinkedIterator
            E computeNext() {
                return (E) this.cursor.getNext();
            }
        };
    }

    @Override // java.util.Deque
    public Iterator<E> descendingIterator() {
        return new LinkedDeque<E>.AbstractLinkedIterator(this.last) { // from class: com.fasterxml.jackson.databind.util.internal.LinkedDeque.2
            @Override // com.fasterxml.jackson.databind.util.internal.LinkedDeque.AbstractLinkedIterator
            E computeNext() {
                return (E) this.cursor.getPrevious();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public abstract class AbstractLinkedIterator implements Iterator<E> {
        E cursor;

        abstract E computeNext();

        AbstractLinkedIterator(E e) {
            LinkedDeque.this = r1;
            this.cursor = e;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.cursor != null;
        }

        @Override // java.util.Iterator
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E e = this.cursor;
            this.cursor = (E) computeNext();
            return e;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
