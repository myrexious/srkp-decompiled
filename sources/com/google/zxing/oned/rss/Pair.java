package com.google.zxing.oned.rss;

/* loaded from: classes3.dex */
final class Pair extends DataCharacter {
    private int count;
    private final FinderPattern finderPattern;

    public Pair(int i, int i2, FinderPattern finderPattern) {
        super(i, i2);
        this.finderPattern = finderPattern;
    }

    public FinderPattern getFinderPattern() {
        return this.finderPattern;
    }

    public int getCount() {
        return this.count;
    }

    public void incrementCount() {
        this.count++;
    }
}
