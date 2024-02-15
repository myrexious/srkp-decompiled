package org.apache.commons.imaging.common;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Map;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes2.dex */
public class BasicCParser {
    private final PushbackInputStream is;

    public BasicCParser(ByteArrayInputStream byteArrayInputStream) {
        this.is = new PushbackInputStream(byteArrayInputStream);
    }

    public String nextToken() throws IOException, ImageReadException {
        StringBuilder sb = new StringBuilder();
        int read = this.is.read();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (read != -1) {
            if (z2) {
                if (read == 10 || read == 13) {
                    throw new ImageReadException("Unterminated string in XPM file");
                }
                if (read == 34) {
                    sb.append('\"');
                    if (!z3) {
                        return sb.toString();
                    }
                } else if (read == 92) {
                    sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                    z3 = !z3;
                } else {
                    sb.append((char) read);
                }
                z3 = false;
            } else if (z) {
                if (!Character.isLetterOrDigit(read) && read != 95) {
                    this.is.unread(read);
                    return sb.toString();
                }
                sb.append((char) read);
            } else if (read == 34) {
                sb.append('\"');
                z2 = true;
            } else if (Character.isLetterOrDigit(read) || read == 95) {
                sb.append((char) read);
                z = true;
            } else if (read == 123 || read == 125 || read == 91 || read == 93 || read == 42 || read == 59 || read == 61 || read == 44) {
                sb.append((char) read);
                return sb.toString();
            } else if (read != 32 && read != 9 && read != 13 && read != 10) {
                throw new ImageReadException("Unhandled/invalid character '" + ((char) read) + "' found in XPM file");
            }
            read = this.is.read();
        }
        if (z) {
            return sb.toString();
        }
        if (z2) {
            throw new ImageReadException("Unterminated string ends XMP file");
        }
        return null;
    }

    public static ByteArrayOutputStream preprocess(InputStream inputStream, StringBuilder sb, Map<String, String> map) throws IOException, ImageReadException {
        int i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean z = sb == null;
        StringBuilder sb2 = new StringBuilder();
        int read = inputStream.read();
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        while (read != -1) {
            if (z3) {
                if (read == 42) {
                    if (z4 && !z) {
                        sb.append('*');
                    }
                    z4 = true;
                } else {
                    if (read != 47) {
                        if (z4 && !z) {
                            sb.append('*');
                        }
                        if (!z) {
                            sb.append((char) read);
                        }
                    } else if (z4) {
                        z = true;
                        z3 = false;
                    } else if (!z) {
                        sb.append((char) read);
                    }
                    z4 = false;
                }
            } else if (z5) {
                if (read == 10 || read == 13) {
                    throw new ImageReadException("Unterminated single quote in file");
                }
                if (read == 39) {
                    if (z8) {
                        byteArrayOutputStream.write(92);
                        i = 39;
                        z8 = false;
                    } else {
                        i = 39;
                        z5 = false;
                    }
                    byteArrayOutputStream.write(i);
                } else if (read != 92) {
                    if (z8) {
                        byteArrayOutputStream.write(92);
                        z8 = false;
                    }
                    byteArrayOutputStream.write(read);
                } else {
                    if (z8) {
                        byteArrayOutputStream.write(92);
                        byteArrayOutputStream.write(92);
                        z8 = false;
                    }
                    z8 = true;
                }
            } else if (!z6) {
                if (!z7) {
                    if (read == 34) {
                        if (z2) {
                            byteArrayOutputStream.write(47);
                        }
                        byteArrayOutputStream.write(read);
                        z6 = true;
                    } else if (read != 35) {
                        if (read == 39) {
                            if (z2) {
                                byteArrayOutputStream.write(47);
                            }
                            byteArrayOutputStream.write(read);
                            z5 = true;
                        } else if (read != 42) {
                            if (read != 47) {
                                if (z2) {
                                    byteArrayOutputStream.write(47);
                                }
                                byteArrayOutputStream.write(read);
                                if (read != 32 && read != 9 && read != 13 && read != 10) {
                                    z = true;
                                }
                            } else {
                                if (z2) {
                                    byteArrayOutputStream.write(47);
                                }
                                z2 = true;
                            }
                        } else if (z2) {
                            z3 = true;
                        } else {
                            byteArrayOutputStream.write(read);
                        }
                    } else if (map == null) {
                        throw new ImageReadException("Unexpected preprocessor directive");
                    } else {
                        z7 = true;
                    }
                    z2 = false;
                } else if (read == 13 || read == 10) {
                    String[] strArr = tokenizeRow(sb2.toString());
                    if (strArr.length < 2 || strArr.length > 3) {
                        throw new ImageReadException("Bad preprocessor directive");
                    }
                    if (!strArr[0].equals("define")) {
                        throw new ImageReadException("Invalid/unsupported preprocessor directive '" + strArr[0] + OperatorName.SHOW_TEXT_LINE);
                    }
                    map.put(strArr[1], strArr.length == 3 ? strArr[2] : null);
                    sb2.setLength(0);
                    z7 = false;
                } else {
                    sb2.append((char) read);
                }
                read = inputStream.read();
            } else if (read == 10 || read == 13) {
                throw new ImageReadException("Unterminated string in file");
            } else {
                if (read == 34) {
                    if (z8) {
                        byteArrayOutputStream.write(92);
                        z8 = false;
                    } else {
                        z6 = false;
                    }
                    byteArrayOutputStream.write(34);
                } else if (read != 92) {
                    if (z8) {
                        byteArrayOutputStream.write(92);
                        z8 = false;
                    }
                    byteArrayOutputStream.write(read);
                } else {
                    if (z8) {
                        byteArrayOutputStream.write(92);
                        byteArrayOutputStream.write(92);
                        z8 = false;
                    }
                    z8 = true;
                }
            }
            read = inputStream.read();
        }
        if (z2) {
            byteArrayOutputStream.write(47);
        }
        if (z4) {
            byteArrayOutputStream.write(42);
        }
        if (z6) {
            throw new ImageReadException("Unterminated string at the end of file");
        }
        if (z3) {
            throw new ImageReadException("Unterminated comment at the end of file");
        }
        return byteArrayOutputStream;
    }

    public static String[] tokenizeRow(String str) {
        String[] split = str.split("[ \t]");
        int i = 0;
        for (String str2 : split) {
            if (str2 != null && !str2.isEmpty()) {
                i++;
            }
        }
        String[] strArr = new String[i];
        int i2 = 0;
        for (String str3 : split) {
            if (str3 != null && !str3.isEmpty()) {
                strArr[i2] = str3;
                i2++;
            }
        }
        return strArr;
    }

    public static void unescapeString(StringBuilder sb, String str) throws ImageReadException {
        if (str.length() < 2) {
            throw new ImageReadException("Parsing XPM file failed, string is too short");
        }
        if (str.charAt(0) != '\"' || str.charAt(str.length() - 1) != '\"') {
            throw new ImageReadException("Parsing XPM file failed, string not surrounded by '\"'");
        }
        boolean z = false;
        int i = 1;
        while (i < str.length() - 1) {
            char charAt = str.charAt(i);
            if (z) {
                if (charAt == '\\') {
                    sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                } else if (charAt == '\"') {
                    sb.append('\"');
                } else if (charAt == '\'') {
                    sb.append('\'');
                } else if (charAt == 'x') {
                    int i2 = i + 2;
                    if (i2 >= str.length()) {
                        throw new ImageReadException("Parsing XPM file failed, hex constant in string too short");
                    }
                    try {
                        sb.append((char) Integer.parseInt(str.charAt(i + 1) + Character.toString(str.charAt(i2)), 16));
                        i = i2;
                    } catch (NumberFormatException e) {
                        throw new ImageReadException("Parsing XPM file failed, hex constant invalid", e);
                    }
                } else if (charAt == 'a') {
                    sb.append((char) 7);
                } else if (charAt == 'b') {
                    sb.append('\b');
                } else if (charAt == 'f') {
                    sb.append('\f');
                } else if (charAt == 'n') {
                    sb.append('\n');
                } else if (charAt == 'r') {
                    sb.append(CharUtils.CR);
                } else if (charAt == 't') {
                    sb.append('\t');
                } else if (charAt != 'v') {
                    switch (charAt) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                            int i3 = i + 1;
                            int i4 = (i3 >= str.length() || '0' > str.charAt(i3) || str.charAt(i3) > '7') ? 1 : 2;
                            int i5 = i + 2;
                            if (i5 < str.length() && '0' <= str.charAt(i5) && str.charAt(i5) <= '7') {
                                i4++;
                            }
                            int i6 = 0;
                            for (int i7 = 0; i7 < i4; i7++) {
                                i6 = (i6 * 8) + (str.charAt(i + i7) - '0');
                            }
                            i += i4 - 1;
                            sb.append((char) i6);
                            break;
                        default:
                            throw new ImageReadException("Parsing XPM file failed, invalid escape sequence");
                    }
                } else {
                    sb.append((char) 11);
                }
                z = false;
            } else if (charAt == '\\') {
                z = true;
            } else if (charAt == '\"') {
                throw new ImageReadException("Parsing XPM file failed, extra '\"' found in string");
            } else {
                sb.append(charAt);
            }
            i++;
        }
        if (z) {
            throw new ImageReadException("Parsing XPM file failed, unterminated escape sequence found in string");
        }
    }
}
