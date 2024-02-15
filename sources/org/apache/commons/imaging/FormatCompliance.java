package org.apache.commons.imaging;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
public class FormatCompliance {
    private static final Logger LOGGER = Logger.getLogger(FormatCompliance.class.getName());
    private final List<String> comments;
    private final String description;
    private final boolean failOnError;

    public FormatCompliance(String str) {
        this.comments = new ArrayList();
        this.description = str;
        this.failOnError = false;
    }

    public FormatCompliance(String str, boolean z) {
        this.comments = new ArrayList();
        this.description = str;
        this.failOnError = z;
    }

    public static FormatCompliance getDefault() {
        return new FormatCompliance("ignore", false);
    }

    public void addComment(String str) throws ImageReadException {
        this.comments.add(str);
        if (this.failOnError) {
            throw new ImageReadException(str);
        }
    }

    public void addComment(String str, int i) throws ImageReadException {
        addComment(str + ": " + getValueDescription(i));
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        dump(new PrintWriter(stringWriter));
        return stringWriter.getBuffer().toString();
    }

    public void dump() {
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            try {
                dump(printWriter);
                printWriter.flush();
                stringWriter.flush();
                LOGGER.fine(stringWriter.toString());
                printWriter.close();
                stringWriter.close();
            } catch (Throwable th) {
                try {
                    printWriter.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), (Throwable) e);
        }
    }

    public void dump(PrintWriter printWriter) {
        printWriter.println("Format Compliance: " + this.description);
        if (this.comments.isEmpty()) {
            printWriter.println("\tNo comments.");
        } else {
            int i = 0;
            while (i < this.comments.size()) {
                int i2 = i + 1;
                printWriter.println("\t" + i2 + ": " + this.comments.get(i));
                i = i2;
            }
        }
        printWriter.println("");
        printWriter.flush();
    }

    private String getValueDescription(int i) {
        return i + " (" + Integer.toHexString(i) + ")";
    }

    public boolean compareBytes(String str, byte[] bArr, byte[] bArr2) throws ImageReadException {
        if (bArr.length != bArr2.length) {
            addComment(str + ": Unexpected length: (expected: " + bArr.length + ", actual: " + bArr2.length + ")");
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                addComment(str + ": Unexpected value: (expected: " + getValueDescription(bArr[i]) + ", actual: " + getValueDescription(bArr2[i]) + ")");
                return false;
            }
        }
        return true;
    }

    public boolean checkBounds(String str, int i, int i2, int i3) throws ImageReadException {
        if (i3 < i || i3 > i2) {
            addComment(str + ": bounds check: " + i + " <= " + i3 + " <= " + i2 + ": false");
            return false;
        }
        return true;
    }

    public boolean compare(String str, int i, int i2) throws ImageReadException {
        return compare(str, new int[]{i}, i2);
    }

    public boolean compare(String str, int[] iArr, int i) throws ImageReadException {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        StringBuilder sb = new StringBuilder(43);
        sb.append(str);
        sb.append(": Unexpected value: (valid: ");
        if (iArr.length > 1) {
            sb.append('{');
        }
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            sb.append(getValueDescription(iArr[i3]));
        }
        if (iArr.length > 1) {
            sb.append('}');
        }
        sb.append(", actual: ").append(getValueDescription(i)).append(")");
        addComment(sb.toString());
        return false;
    }
}
