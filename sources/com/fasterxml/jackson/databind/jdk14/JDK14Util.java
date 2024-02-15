package com.fasterxml.jackson.databind.jdk14;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.NativeImageUtil;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class JDK14Util {
    public static String[] getRecordFieldNames(Class<?> cls) {
        return RecordAccessor.instance().getRecordFieldNames(cls);
    }

    public static AnnotatedConstructor findRecordConstructor(DeserializationContext deserializationContext, BeanDescription beanDescription, List<String> list) {
        return findRecordConstructor(beanDescription.getClassInfo(), deserializationContext.getAnnotationIntrospector(), deserializationContext.getConfig(), list);
    }

    public static AnnotatedConstructor findRecordConstructor(AnnotatedClass annotatedClass, AnnotationIntrospector annotationIntrospector, MapperConfig<?> mapperConfig, List<String> list) {
        return new CreatorLocator(annotatedClass, annotationIntrospector, mapperConfig).locate(list);
    }

    /* loaded from: classes.dex */
    public static class RecordAccessor {
        private static final RecordAccessor INSTANCE;
        private static final RuntimeException PROBLEM;
        private final Method RECORD_COMPONENT_GET_NAME;
        private final Method RECORD_COMPONENT_GET_TYPE;
        private final Method RECORD_GET_RECORD_COMPONENTS;

        static {
            RecordAccessor recordAccessor = null;
            try {
                e = null;
                recordAccessor = new RecordAccessor();
            } catch (RuntimeException e) {
                e = e;
            }
            INSTANCE = recordAccessor;
            PROBLEM = e;
        }

        private RecordAccessor() throws RuntimeException {
            try {
                this.RECORD_GET_RECORD_COMPONENTS = Class.class.getMethod("getRecordComponents", new Class[0]);
                Class<?> cls = Class.forName("java.lang.reflect.RecordComponent");
                this.RECORD_COMPONENT_GET_NAME = cls.getMethod("getName", new Class[0]);
                this.RECORD_COMPONENT_GET_TYPE = cls.getMethod("getType", new Class[0]);
            } catch (Exception e) {
                throw new RuntimeException(String.format("Failed to access Methods needed to support `java.lang.Record`: (%s) %s", e.getClass().getName(), e.getMessage()), e);
            }
        }

        public static RecordAccessor instance() {
            RuntimeException runtimeException = PROBLEM;
            if (runtimeException != null) {
                throw runtimeException;
            }
            return INSTANCE;
        }

        public String[] getRecordFieldNames(Class<?> cls) throws IllegalArgumentException {
            Object[] recordComponents = recordComponents(cls);
            if (recordComponents == null) {
                return null;
            }
            String[] strArr = new String[recordComponents.length];
            for (int i = 0; i < recordComponents.length; i++) {
                try {
                    strArr[i] = (String) this.RECORD_COMPONENT_GET_NAME.invoke(recordComponents[i], new Object[0]);
                } catch (Exception e) {
                    throw new IllegalArgumentException(String.format("Failed to access name of field #%d (of %d) of Record type %s", Integer.valueOf(i), Integer.valueOf(recordComponents.length), ClassUtil.nameOf(cls)), e);
                }
            }
            return strArr;
        }

        public RawTypeName[] getRecordFields(Class<?> cls) throws IllegalArgumentException {
            Object[] recordComponents = recordComponents(cls);
            if (recordComponents == null) {
                return null;
            }
            RawTypeName[] rawTypeNameArr = new RawTypeName[recordComponents.length];
            for (int i = 0; i < recordComponents.length; i++) {
                try {
                    try {
                        rawTypeNameArr[i] = new RawTypeName((Class) this.RECORD_COMPONENT_GET_TYPE.invoke(recordComponents[i], new Object[0]), (String) this.RECORD_COMPONENT_GET_NAME.invoke(recordComponents[i], new Object[0]));
                    } catch (Exception e) {
                        throw new IllegalArgumentException(String.format("Failed to access type of field #%d (of %d) of Record type %s", Integer.valueOf(i), Integer.valueOf(recordComponents.length), ClassUtil.nameOf(cls)), e);
                    }
                } catch (Exception e2) {
                    throw new IllegalArgumentException(String.format("Failed to access name of field #%d (of %d) of Record type %s", Integer.valueOf(i), Integer.valueOf(recordComponents.length), ClassUtil.nameOf(cls)), e2);
                }
            }
            return rawTypeNameArr;
        }

        protected Object[] recordComponents(Class<?> cls) throws IllegalArgumentException {
            try {
                return (Object[]) this.RECORD_GET_RECORD_COMPONENTS.invoke(cls, new Object[0]);
            } catch (Exception e) {
                if (NativeImageUtil.isUnsupportedFeatureError(e)) {
                    return null;
                }
                throw new IllegalArgumentException("Failed to access RecordComponents of type " + ClassUtil.nameOf(cls));
            }
        }
    }

    /* loaded from: classes.dex */
    public static class RawTypeName {
        public final String name;
        public final Class<?> rawType;

        public RawTypeName(Class<?> cls, String str) {
            this.rawType = cls;
            this.name = str;
        }
    }

    /* loaded from: classes.dex */
    public static class CreatorLocator {
        protected final MapperConfig<?> _config;
        protected final List<AnnotatedConstructor> _constructors;
        protected final AnnotationIntrospector _intr;
        protected final AnnotatedConstructor _primaryConstructor;
        protected final AnnotatedClass _recordClass;
        protected final RawTypeName[] _recordFields;

        CreatorLocator(AnnotatedClass annotatedClass, AnnotationIntrospector annotationIntrospector, MapperConfig<?> mapperConfig) {
            this._recordClass = annotatedClass;
            this._intr = annotationIntrospector;
            this._config = mapperConfig;
            RawTypeName[] recordFields = RecordAccessor.instance().getRecordFields(annotatedClass.getRawType());
            this._recordFields = recordFields;
            AnnotatedConstructor annotatedConstructor = null;
            if (recordFields == null) {
                this._constructors = annotatedClass.getConstructors();
                this._primaryConstructor = null;
                return;
            }
            int length = recordFields.length;
            if (length == 0) {
                annotatedConstructor = annotatedClass.getDefaultConstructor();
                this._constructors = Collections.singletonList(annotatedConstructor);
            } else {
                List<AnnotatedConstructor> constructors = annotatedClass.getConstructors();
                this._constructors = constructors;
                Iterator<AnnotatedConstructor> it = constructors.iterator();
                loop0: while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    AnnotatedConstructor next = it.next();
                    if (next.getParameterCount() == length) {
                        for (int i = 0; i < length; i++) {
                            if (!next.getRawParameterType(i).equals(this._recordFields[i].rawType)) {
                                break;
                            }
                        }
                        annotatedConstructor = next;
                        break loop0;
                    }
                }
            }
            if (annotatedConstructor == null) {
                throw new IllegalArgumentException("Failed to find the canonical Record constructor of type " + ClassUtil.getTypeDescription(this._recordClass.getType()));
            }
            this._primaryConstructor = annotatedConstructor;
        }

        public AnnotatedConstructor locate(List<String> list) {
            for (AnnotatedConstructor annotatedConstructor : this._constructors) {
                JsonCreator.Mode findCreatorAnnotation = this._intr.findCreatorAnnotation(this._config, annotatedConstructor);
                if (findCreatorAnnotation != null && JsonCreator.Mode.DISABLED != findCreatorAnnotation && (JsonCreator.Mode.DELEGATING == findCreatorAnnotation || annotatedConstructor != this._primaryConstructor)) {
                    return null;
                }
            }
            RawTypeName[] rawTypeNameArr = this._recordFields;
            if (rawTypeNameArr == null) {
                return null;
            }
            for (RawTypeName rawTypeName : rawTypeNameArr) {
                list.add(rawTypeName.name);
            }
            return this._primaryConstructor;
        }
    }
}
