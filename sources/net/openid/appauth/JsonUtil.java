package net.openid.appauth;

import android.net.Uri;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
final class JsonUtil {
    private JsonUtil() {
        throw new IllegalStateException("This type is not intended to be instantiated");
    }

    public static void put(JSONObject json, String field, int value) {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        Preconditions.checkNotNull(Integer.valueOf(value), "value must not be null");
        try {
            json.put(field, value);
        } catch (JSONException unused) {
            throw new IllegalStateException("JSONException thrown in violation of contract, ex");
        }
    }

    public static void put(JSONObject json, String field, String value) {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        Preconditions.checkNotNull(value, "value must not be null");
        try {
            json.put(field, value);
        } catch (JSONException e) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e);
        }
    }

    public static void put(JSONObject json, String field, JSONArray value) {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        Preconditions.checkNotNull(value, "value must not be null");
        try {
            json.put(field, value);
        } catch (JSONException e) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e);
        }
    }

    public static void put(JSONObject json, String field, JSONObject value) {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        Preconditions.checkNotNull(value, "value must not be null");
        try {
            json.put(field, value);
        } catch (JSONException e) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e);
        }
    }

    public static void putIfNotNull(JSONObject json, String field, String value) {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        if (value == null) {
            return;
        }
        try {
            json.put(field, value);
        } catch (JSONException e) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e);
        }
    }

    public static void putIfNotNull(JSONObject json, String field, Uri value) {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        if (value == null) {
            return;
        }
        try {
            json.put(field, value.toString());
        } catch (JSONException e) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e);
        }
    }

    public static void putIfNotNull(JSONObject json, String field, Long value) {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        if (value == null) {
            return;
        }
        try {
            json.put(field, value);
        } catch (JSONException e) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e);
        }
    }

    public static void putIfNotNull(JSONObject json, String field, JSONObject value) {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        if (value == null) {
            return;
        }
        try {
            json.put(field, value);
        } catch (JSONException e) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e);
        }
    }

    static void putIfNotNull(JSONObject json, String field, Object value) {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        if (value == null) {
            return;
        }
        try {
            if (value instanceof Collection) {
                json.put(field, new JSONArray((Collection) value));
            } else if (value instanceof Map) {
                Map map = (Map) value;
                JSONObject jSONObject = new JSONObject();
                for (String str : map.keySet()) {
                    putIfNotNull(jSONObject, str, map.get(str));
                }
                json.put(field, jSONObject);
            } else {
                json.put(field, value);
            }
        } catch (JSONException e) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e);
        }
    }

    public static String getString(JSONObject json, String field) throws JSONException {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        if (!json.has(field)) {
            throw new JSONException("field \"" + field + "\" not found in json object");
        }
        String string = json.getString(field);
        if (string != null) {
            return string;
        }
        throw new JSONException("field \"" + field + "\" is mapped to a null value");
    }

    public static String getStringIfDefined(JSONObject json, String field) throws JSONException {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        if (json.has(field)) {
            String string = json.getString(field);
            if (string != null) {
                return string;
            }
            throw new JSONException("field \"" + field + "\" is mapped to a null value");
        }
        return null;
    }

    public static List<String> getStringListIfDefined(JSONObject json, String field) throws JSONException {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        if (json.has(field)) {
            JSONArray jSONArray = json.getJSONArray(field);
            if (jSONArray == null) {
                throw new JSONException("field \"" + field + "\" is mapped to a null value");
            }
            return toStringList(jSONArray);
        }
        return null;
    }

    public static Uri getUri(JSONObject json, String field) throws JSONException {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        String string = json.getString(field);
        if (string == null) {
            throw new JSONException("field \"" + field + "\" is mapped to a null value");
        }
        return Uri.parse(string);
    }

    public static Uri getUriIfDefined(JSONObject json, String field) throws JSONException {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        if (json.has(field)) {
            String string = json.getString(field);
            if (string == null) {
                throw new JSONException("field \"" + field + "\" is mapped to a null value");
            }
            return Uri.parse(string);
        }
        return null;
    }

    public static Long getLongIfDefined(JSONObject json, String field) throws JSONException {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        if (json.has(field) && !json.isNull(field)) {
            try {
                return Long.valueOf(json.getLong(field));
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public static List<String> getStringList(JSONObject json, String field) throws JSONException {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        if (!json.has(field)) {
            throw new JSONException("field \"" + field + "\" not found in json object");
        }
        return toStringList(json.getJSONArray(field));
    }

    public static List<Uri> getUriList(JSONObject json, String field) throws JSONException {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        if (!json.has(field)) {
            throw new JSONException("field \"" + field + "\" not found in json object");
        }
        return toUriList(json.getJSONArray(field));
    }

    public static Map<String, String> getStringMap(JSONObject json, String field) throws JSONException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        if (json.has(field)) {
            JSONObject jSONObject = json.getJSONObject(field);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                linkedHashMap.put(next, (String) Preconditions.checkNotNull(jSONObject.getString(next), "additional parameter values must not be null"));
            }
            return linkedHashMap;
        }
        return linkedHashMap;
    }

    public static JSONObject getJsonObjectIfDefined(JSONObject json, String field) throws JSONException {
        Preconditions.checkNotNull(json, "json must not be null");
        Preconditions.checkNotNull(field, "field must not be null");
        if (json.has(field)) {
            JSONObject optJSONObject = json.optJSONObject(field);
            if (optJSONObject != null) {
                return optJSONObject;
            }
            throw new JSONException("field \"" + field + "\" is mapped to a null value");
        }
        return null;
    }

    public static List<String> toStringList(JSONArray jsonArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                arrayList.add(Preconditions.checkNotNull(jsonArray.get(i)).toString());
            }
        }
        return arrayList;
    }

    public static Map<String, Object> toMap(JSONObject json) throws JSONException {
        Preconditions.checkNotNull(json, "json must not be null");
        HashMap hashMap = new HashMap();
        Iterator<String> keys = json.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = json.get(next);
            if (obj instanceof JSONArray) {
                obj = toList((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = toMap((JSONObject) obj);
            }
            hashMap.put(next, obj);
        }
        return hashMap;
    }

    public static List<Object> toList(JSONArray jsonArray) throws JSONException {
        Preconditions.checkNotNull(jsonArray, "jsonArray must not be null");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object obj = jsonArray.get(i);
            if (obj instanceof JSONArray) {
                obj = toList((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = toMap((JSONObject) obj);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }

    public static List<Uri> toUriList(JSONArray jsonArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                arrayList.add(Uri.parse(Preconditions.checkNotNull(jsonArray.get(i)).toString()));
            }
        }
        return arrayList;
    }

    public static JSONArray toJsonArray(Iterable<?> objects) {
        Preconditions.checkNotNull(objects, "objects cannot be null");
        JSONArray jSONArray = new JSONArray();
        Iterator<?> it = objects.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().toString());
        }
        return jSONArray;
    }

    public static JSONObject mapToJsonObject(Map<String, String> map) {
        Preconditions.checkNotNull(map);
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Preconditions.checkNotNull(entry.getKey(), "map entries must not have null keys");
            Preconditions.checkNotNull(entry.getValue(), "map entries must not have null values");
            put(jSONObject, entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }

    public static <T> T get(JSONObject json, Field<T> field) {
        try {
            if (!json.has(field.key)) {
                return field.defaultValue;
            }
            return field.convert(json.getString(field.key));
        } catch (JSONException e) {
            throw new IllegalStateException("unexpected JSONException", e);
        }
    }

    public static <T> List<T> get(JSONObject json, ListField<T> field) {
        try {
            if (!json.has(field.key)) {
                return field.defaultValue;
            }
            Object obj = json.get(field.key);
            if (!(obj instanceof JSONArray)) {
                throw new IllegalStateException(field.key + " does not contain the expected JSON array");
            }
            JSONArray jSONArray = (JSONArray) obj;
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(field.convert(jSONArray.getString(i)));
            }
            return arrayList;
        } catch (JSONException e) {
            throw new IllegalStateException("unexpected JSONException", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class Field<T> {
        public final T defaultValue;
        public final String key;

        abstract T convert(String value);

        Field(String key, T defaultValue) {
            this.key = key;
            this.defaultValue = defaultValue;
        }
    }

    /* loaded from: classes2.dex */
    static final class UriField extends Field<Uri> {
        UriField(String key, Uri defaultValue) {
            super(key, defaultValue);
        }

        public UriField(String key) {
            this(key, null);
        }

        @Override // net.openid.appauth.JsonUtil.Field
        public Uri convert(String value) {
            return Uri.parse(value);
        }
    }

    /* loaded from: classes2.dex */
    static final class StringField extends Field<String> {
        @Override // net.openid.appauth.JsonUtil.Field
        public String convert(String value) {
            return value;
        }

        StringField(String key, String defaultValue) {
            super(key, defaultValue);
        }

        public StringField(String key) {
            this(key, null);
        }
    }

    /* loaded from: classes2.dex */
    static final class BooleanField extends Field<Boolean> {
        public BooleanField(String key, boolean defaultValue) {
            super(key, Boolean.valueOf(defaultValue));
        }

        @Override // net.openid.appauth.JsonUtil.Field
        public Boolean convert(String value) {
            return Boolean.valueOf(Boolean.parseBoolean(value));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class ListField<T> {
        public final List<T> defaultValue;
        public final String key;

        abstract T convert(String value);

        ListField(String key, List<T> defaultValue) {
            this.key = key;
            this.defaultValue = defaultValue;
        }
    }

    /* loaded from: classes2.dex */
    static final class StringListField extends ListField<String> {
        @Override // net.openid.appauth.JsonUtil.ListField
        public String convert(String value) {
            return value;
        }

        public StringListField(String key) {
            super(key, null);
        }

        public StringListField(String key, List<String> defaultValue) {
            super(key, defaultValue);
        }
    }
}
