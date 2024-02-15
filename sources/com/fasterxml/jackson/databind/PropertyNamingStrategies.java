package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import java.io.Serializable;
import org.apache.commons.codec.language.Soundex;

/* loaded from: classes.dex */
public abstract class PropertyNamingStrategies implements Serializable {
    private static final long serialVersionUID = 2;
    public static final PropertyNamingStrategy LOWER_CAMEL_CASE = LowerCamelCaseStrategy.INSTANCE;
    public static final PropertyNamingStrategy UPPER_CAMEL_CASE = UpperCamelCaseStrategy.INSTANCE;
    public static final PropertyNamingStrategy SNAKE_CASE = SnakeCaseStrategy.INSTANCE;
    public static final PropertyNamingStrategy UPPER_SNAKE_CASE = UpperSnakeCaseStrategy.INSTANCE;
    public static final PropertyNamingStrategy LOWER_CASE = LowerCaseStrategy.INSTANCE;
    public static final PropertyNamingStrategy KEBAB_CASE = KebabCaseStrategy.INSTANCE;
    public static final PropertyNamingStrategy LOWER_DOT_CASE = LowerDotCaseStrategy.INSTANCE;

    /* loaded from: classes.dex */
    public static class LowerCamelCaseStrategy extends NamingBase {
        public static final LowerCamelCaseStrategy INSTANCE = new LowerCamelCaseStrategy();
        private static final long serialVersionUID = 2;

        @Override // com.fasterxml.jackson.databind.PropertyNamingStrategies.NamingBase
        public String translate(String str) {
            return str;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class NamingBase extends PropertyNamingStrategy {
        private static final long serialVersionUID = 2;

        public abstract String translate(String str);

        @Override // com.fasterxml.jackson.databind.PropertyNamingStrategy
        public String nameForField(MapperConfig<?> mapperConfig, AnnotatedField annotatedField, String str) {
            return translate(str);
        }

        @Override // com.fasterxml.jackson.databind.PropertyNamingStrategy
        public String nameForGetterMethod(MapperConfig<?> mapperConfig, AnnotatedMethod annotatedMethod, String str) {
            return translate(str);
        }

        @Override // com.fasterxml.jackson.databind.PropertyNamingStrategy
        public String nameForSetterMethod(MapperConfig<?> mapperConfig, AnnotatedMethod annotatedMethod, String str) {
            return translate(str);
        }

        @Override // com.fasterxml.jackson.databind.PropertyNamingStrategy
        public String nameForConstructorParameter(MapperConfig<?> mapperConfig, AnnotatedParameter annotatedParameter, String str) {
            return translate(str);
        }

        protected String translateLowerCaseWithSeparator(String str, char c) {
            if (str == null || str.isEmpty()) {
                return str;
            }
            int length = str.length();
            StringBuilder sb = new StringBuilder((length >> 1) + length);
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                char lowerCase = Character.toLowerCase(charAt);
                if (lowerCase == charAt) {
                    if (i > 1) {
                        sb.insert(sb.length() - 1, c);
                    }
                    i = 0;
                } else {
                    if (i == 0 && i2 > 0) {
                        sb.append(c);
                    }
                    i++;
                }
                sb.append(lowerCase);
            }
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    public static class SnakeCaseStrategy extends NamingBase {
        public static final SnakeCaseStrategy INSTANCE = new SnakeCaseStrategy();
        private static final long serialVersionUID = 2;

        @Override // com.fasterxml.jackson.databind.PropertyNamingStrategies.NamingBase
        public String translate(String str) {
            if (str == null) {
                return str;
            }
            int length = str.length();
            StringBuilder sb = new StringBuilder(length * 2);
            int i = 0;
            boolean z = false;
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                if (i2 > 0 || charAt != '_') {
                    if (Character.isUpperCase(charAt)) {
                        if (!z && i > 0 && sb.charAt(i - 1) != '_') {
                            sb.append('_');
                            i++;
                        }
                        charAt = Character.toLowerCase(charAt);
                        z = true;
                    } else {
                        z = false;
                    }
                    sb.append(charAt);
                    i++;
                }
            }
            return i > 0 ? sb.toString() : str;
        }
    }

    /* loaded from: classes.dex */
    public static class UpperSnakeCaseStrategy extends SnakeCaseStrategy {
        public static final UpperSnakeCaseStrategy INSTANCE = new UpperSnakeCaseStrategy();
        private static final long serialVersionUID = 2;

        @Override // com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy, com.fasterxml.jackson.databind.PropertyNamingStrategies.NamingBase
        public String translate(String str) {
            String translate = super.translate(str);
            if (translate == null) {
                return null;
            }
            return translate.toUpperCase();
        }
    }

    /* loaded from: classes.dex */
    public static class UpperCamelCaseStrategy extends NamingBase {
        public static final UpperCamelCaseStrategy INSTANCE = new UpperCamelCaseStrategy();
        private static final long serialVersionUID = 2;

        @Override // com.fasterxml.jackson.databind.PropertyNamingStrategies.NamingBase
        public String translate(String str) {
            char charAt;
            char upperCase;
            if (str == null || str.isEmpty() || charAt == (upperCase = Character.toUpperCase((charAt = str.charAt(0))))) {
                return str;
            }
            StringBuilder sb = new StringBuilder(str);
            sb.setCharAt(0, upperCase);
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    public static class LowerCaseStrategy extends NamingBase {
        public static final LowerCaseStrategy INSTANCE = new LowerCaseStrategy();
        private static final long serialVersionUID = 2;

        @Override // com.fasterxml.jackson.databind.PropertyNamingStrategies.NamingBase
        public String translate(String str) {
            return (str == null || str.isEmpty()) ? str : str.toLowerCase();
        }
    }

    /* loaded from: classes.dex */
    public static class KebabCaseStrategy extends NamingBase {
        public static final KebabCaseStrategy INSTANCE = new KebabCaseStrategy();
        private static final long serialVersionUID = 2;

        @Override // com.fasterxml.jackson.databind.PropertyNamingStrategies.NamingBase
        public String translate(String str) {
            return translateLowerCaseWithSeparator(str, Soundex.SILENT_MARKER);
        }
    }

    /* loaded from: classes.dex */
    public static class LowerDotCaseStrategy extends NamingBase {
        public static final LowerDotCaseStrategy INSTANCE = new LowerDotCaseStrategy();
        private static final long serialVersionUID = 2;

        @Override // com.fasterxml.jackson.databind.PropertyNamingStrategies.NamingBase
        public String translate(String str) {
            return translateLowerCaseWithSeparator(str, '.');
        }
    }
}
