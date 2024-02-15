package com.google.android.material.textfield;

import android.widget.EditText;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class EditTextUtils {
    private EditTextUtils() {
    }

    public static boolean isEditable(EditText editText) {
        return editText.getInputType() != 0;
    }
}
