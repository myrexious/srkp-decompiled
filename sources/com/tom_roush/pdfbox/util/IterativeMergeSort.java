package com.tom_roush.pdfbox.util;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes3.dex */
public final class IterativeMergeSort {
    private IterativeMergeSort() {
    }

    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
        if (list.size() < 2) {
            return;
        }
        Object[] array = list.toArray();
        iterativeMergeSort(array, comparator);
        ListIterator<T> listIterator = list.listIterator();
        for (Object obj : array) {
            listIterator.next();
            listIterator.set((T) obj);
        }
    }

    private static <T> void iterativeMergeSort(T[] tArr, Comparator<? super T> comparator) {
        Object[] objArr = (Object[]) tArr.clone();
        for (int i = 1; i < tArr.length; i <<= 1) {
            int i2 = 0;
            while (i2 < tArr.length) {
                int i3 = i2 + (i << 1);
                merge(tArr, objArr, i2, i2 + i, i3, comparator);
                i2 = i3;
            }
        }
    }

    private static <T> void merge(T[] tArr, T[] tArr2, int i, int i2, int i3, Comparator<? super T> comparator) {
        int i4;
        int i5;
        if (i2 >= tArr.length) {
            return;
        }
        if (i3 > tArr.length) {
            i3 = tArr.length;
        }
        int i6 = i;
        int i7 = i6;
        int i8 = i2;
        while (i6 < i3) {
            if (i7 == i2) {
                i5 = i8 + 1;
                tArr2[i6] = tArr[i8];
            } else {
                if (i8 == i3) {
                    i4 = i7 + 1;
                    tArr2[i6] = tArr[i7];
                } else if (comparator.compare(tArr[i8], tArr[i7]) < 0) {
                    i5 = i8 + 1;
                    tArr2[i6] = tArr[i8];
                } else {
                    i4 = i7 + 1;
                    tArr2[i6] = tArr[i7];
                }
                i7 = i4;
                i6++;
            }
            i8 = i5;
            i6++;
        }
        System.arraycopy(tArr2, i, tArr, i, i3 - i);
    }
}
