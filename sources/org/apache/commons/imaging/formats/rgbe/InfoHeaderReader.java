package org.apache.commons.imaging.formats.rgbe;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
class InfoHeaderReader {
    private final InputStream is;

    public InfoHeaderReader(InputStream inputStream) {
        this.is = inputStream;
    }

    private char read() throws IOException {
        int read = this.is.read();
        if (read >= 0) {
            return (char) read;
        }
        throw new IOException("HDR: Unexpected EOF");
    }

    public String readNextLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            char read = read();
            if (read != '\n') {
                sb.append(read);
            } else {
                return sb.toString();
            }
        }
    }
}
