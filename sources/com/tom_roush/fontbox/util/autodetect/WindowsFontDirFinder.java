package com.tom_roush.fontbox.util.autodetect;

import com.google.firebase.sessions.settings.RemoteSettings;
import com.tom_roush.fontbox.util.Charsets;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.SystemProperties;

/* loaded from: classes3.dex */
public class WindowsFontDirFinder implements FontDirFinder {
    private String getWinDir(String str) throws IOException {
        Process exec;
        Runtime runtime = Runtime.getRuntime();
        if (str.startsWith("Windows 9")) {
            exec = runtime.exec("command.com /c echo %windir%");
        } else {
            exec = runtime.exec("cmd.exe /c echo %windir%");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream(), Charsets.ISO_8859_1));
        String readLine = bufferedReader.readLine();
        bufferedReader.close();
        return readLine;
    }

    @Override // com.tom_roush.fontbox.util.autodetect.FontDirFinder
    public List<File> find() {
        String str;
        ArrayList arrayList = new ArrayList();
        try {
            str = System.getProperty("env.windir");
        } catch (SecurityException unused) {
            str = null;
        }
        String property = System.getProperty(SystemProperties.OS_NAME);
        if (str == null) {
            try {
                str = getWinDir(property);
            } catch (IOException | SecurityException unused2) {
            }
        }
        if (str != null && str.length() > 2) {
            if (str.endsWith(RemoteSettings.FORWARD_SLASH_STRING)) {
                str = str.substring(0, str.length() - 1);
            }
            File file = new File(str + File.separator + "FONTS");
            if (file.exists() && file.canRead()) {
                arrayList.add(file);
            }
            File file2 = new File(str.substring(0, 2) + File.separator + "PSFONTS");
            if (file2.exists() && file2.canRead()) {
                arrayList.add(file2);
            }
        } else {
            String str2 = property.endsWith("NT") ? "WINNT" : "WINDOWS";
            for (char c = 'C'; c <= 'E'; c = (char) (c + 1)) {
                File file3 = new File(c + ":" + File.separator + str2 + File.separator + "FONTS");
                try {
                    if (file3.exists() && file3.canRead()) {
                        arrayList.add(file3);
                        break;
                    }
                } catch (SecurityException unused3) {
                }
            }
            for (char c2 = 'C'; c2 <= 'E'; c2 = (char) (c2 + 1)) {
                File file4 = new File(c2 + ":" + File.separator + "PSFONTS");
                try {
                    if (file4.exists() && file4.canRead()) {
                        arrayList.add(file4);
                    }
                } catch (SecurityException unused4) {
                }
            }
        }
        try {
            String str3 = System.getenv("LOCALAPPDATA");
            if (str3 != null && !str3.isEmpty()) {
                File file5 = new File(str3 + File.separator + "Microsoft" + File.separator + "Windows" + File.separator + "Fonts");
                if (file5.exists() && file5.canRead()) {
                    arrayList.add(file5);
                }
            }
        } catch (SecurityException unused5) {
        }
        return arrayList;
    }
}
