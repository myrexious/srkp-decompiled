package com.tom_roush.pdfbox.contentstream.operator;

import com.google.firebase.sessions.settings.RemoteSettings;
import com.tom_roush.pdfbox.cos.COSDictionary;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes3.dex */
public final class Operator {
    private static final ConcurrentMap<String, Operator> operators = new ConcurrentHashMap();
    private byte[] imageData;
    private COSDictionary imageParameters;
    private final String theOperator;

    private Operator(String str) {
        this.theOperator = str;
        if (str.startsWith(RemoteSettings.FORWARD_SLASH_STRING)) {
            throw new IllegalArgumentException("Operators are not allowed to start with / '" + str + OperatorName.SHOW_TEXT_LINE);
        }
    }

    public static Operator getOperator(String str) {
        if (str.equals(OperatorName.BEGIN_INLINE_IMAGE_DATA) || OperatorName.BEGIN_INLINE_IMAGE.equals(str)) {
            return new Operator(str);
        }
        ConcurrentMap<String, Operator> concurrentMap = operators;
        Operator operator = concurrentMap.get(str);
        if (operator == null) {
            Operator putIfAbsent = concurrentMap.putIfAbsent(str, new Operator(str));
            return putIfAbsent == null ? concurrentMap.get(str) : putIfAbsent;
        }
        return operator;
    }

    public String getName() {
        return this.theOperator;
    }

    public String toString() {
        return "PDFOperator{" + this.theOperator + StringSubstitutor.DEFAULT_VAR_END;
    }

    public byte[] getImageData() {
        return this.imageData;
    }

    public void setImageData(byte[] bArr) {
        this.imageData = bArr;
    }

    public COSDictionary getImageParameters() {
        return this.imageParameters;
    }

    public void setImageParameters(COSDictionary cOSDictionary) {
        this.imageParameters = cOSDictionary;
    }
}
