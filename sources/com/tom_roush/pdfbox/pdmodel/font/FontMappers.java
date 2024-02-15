package com.tom_roush.pdfbox.pdmodel.font;

/* loaded from: classes3.dex */
public final class FontMappers {
    private static FontMapper instance;

    private FontMappers() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class DefaultFontMapper {
        private static final FontMapper INSTANCE = new FontMapperImpl();

        private DefaultFontMapper() {
        }
    }

    public static FontMapper instance() {
        if (instance == null) {
            instance = DefaultFontMapper.INSTANCE;
        }
        return instance;
    }

    public static synchronized void set(FontMapper fontMapper) {
        synchronized (FontMappers.class) {
            instance = fontMapper;
        }
    }
}
