package com.tom_roush.fontbox.cff;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class CFFCharset {
    private final boolean isCIDFont;
    private final Map<Integer, Integer> sidOrCidToGid = new HashMap((int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    private final Map<Integer, Integer> gidToSid = new HashMap((int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    private final Map<String, Integer> nameToSid = new HashMap((int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    private final Map<Integer, Integer> gidToCid = new HashMap();
    private final Map<Integer, String> gidToName = new HashMap((int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);

    public CFFCharset(boolean z) {
        this.isCIDFont = z;
    }

    public boolean isCIDFont() {
        return this.isCIDFont;
    }

    public void addSID(int i, int i2, String str) {
        if (this.isCIDFont) {
            throw new IllegalStateException("Not a Type 1-equivalent font");
        }
        this.sidOrCidToGid.put(Integer.valueOf(i2), Integer.valueOf(i));
        this.gidToSid.put(Integer.valueOf(i), Integer.valueOf(i2));
        this.nameToSid.put(str, Integer.valueOf(i2));
        this.gidToName.put(Integer.valueOf(i), str);
    }

    public void addCID(int i, int i2) {
        if (!this.isCIDFont) {
            throw new IllegalStateException("Not a CIDFont");
        }
        this.sidOrCidToGid.put(Integer.valueOf(i2), Integer.valueOf(i));
        this.gidToCid.put(Integer.valueOf(i), Integer.valueOf(i2));
    }

    public int getSIDForGID(int i) {
        if (this.isCIDFont) {
            throw new IllegalStateException("Not a Type 1-equivalent font");
        }
        Integer num = this.gidToSid.get(Integer.valueOf(i));
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public int getGIDForSID(int i) {
        if (this.isCIDFont) {
            throw new IllegalStateException("Not a Type 1-equivalent font");
        }
        Integer num = this.sidOrCidToGid.get(Integer.valueOf(i));
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public int getGIDForCID(int i) {
        if (!this.isCIDFont) {
            throw new IllegalStateException("Not a CIDFont");
        }
        Integer num = this.sidOrCidToGid.get(Integer.valueOf(i));
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public int getSID(String str) {
        if (this.isCIDFont) {
            throw new IllegalStateException("Not a Type 1-equivalent font");
        }
        Integer num = this.nameToSid.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public String getNameForGID(int i) {
        if (this.isCIDFont) {
            throw new IllegalStateException("Not a Type 1-equivalent font");
        }
        return this.gidToName.get(Integer.valueOf(i));
    }

    public int getCIDForGID(int i) {
        if (!this.isCIDFont) {
            throw new IllegalStateException("Not a CIDFont");
        }
        Integer num = this.gidToCid.get(Integer.valueOf(i));
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }
}
