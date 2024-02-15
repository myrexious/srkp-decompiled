package org.bouncycastle.asn1.util;

import java.io.FileInputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;

/* loaded from: classes2.dex */
public class Dump {
    public static void main(String[] strArr) throws Exception {
        char c = 1;
        if (strArr.length < 1) {
            System.out.println("usage: Dump [-v] filename");
            System.exit(1);
        }
        boolean z = false;
        if (strArr.length > 1) {
            z = "-v".equals(strArr[0]);
        } else {
            c = 0;
        }
        FileInputStream fileInputStream = new FileInputStream(strArr[c]);
        try {
            ASN1InputStream aSN1InputStream = new ASN1InputStream(fileInputStream);
            while (true) {
                ASN1Primitive readObject = aSN1InputStream.readObject();
                if (readObject == null) {
                    return;
                }
                System.out.println(ASN1Dump.dumpAsString(readObject, z));
            }
        } finally {
            fileInputStream.close();
        }
    }
}
