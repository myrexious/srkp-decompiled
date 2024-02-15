package org.bouncycastle.util.encoders;

import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class UrlBase64Encoder extends Base64Encoder {
    public UrlBase64Encoder() {
        this.encodingTable[this.encodingTable.length - 2] = BuiltinOptions.GreaterEqualOptions;
        this.encodingTable[this.encodingTable.length - 1] = BuiltinOptions.NonMaxSuppressionV4Options;
        this.padding = BuiltinOptions.LessEqualOptions;
        initialiseDecodingTable();
    }
}
