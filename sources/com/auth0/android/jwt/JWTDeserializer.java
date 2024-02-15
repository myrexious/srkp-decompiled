package com.auth0.android.jwt;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class JWTDeserializer implements JsonDeserializer<JWTPayload> {
    @Override // com.google.gson.JsonDeserializer
    public JWTPayload deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.isJsonNull() || !jsonElement.isJsonObject()) {
            throw new DecodeException("The token's payload had an invalid JSON format.");
        }
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        String string = getString(asJsonObject, "iss");
        String string2 = getString(asJsonObject, "sub");
        Date date = getDate(asJsonObject, "exp");
        Date date2 = getDate(asJsonObject, "nbf");
        Date date3 = getDate(asJsonObject, "iat");
        String string3 = getString(asJsonObject, "jti");
        List<String> stringOrArray = getStringOrArray(asJsonObject, "aud");
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            hashMap.put(entry.getKey(), new ClaimImpl(entry.getValue()));
        }
        return new JWTPayload(string, string2, date, date2, date3, string3, stringOrArray, hashMap);
    }

    private List<String> getStringOrArray(JsonObject jsonObject, String str) {
        List<String> emptyList = Collections.emptyList();
        if (jsonObject.has(str)) {
            JsonElement jsonElement = jsonObject.get(str);
            if (jsonElement.isJsonArray()) {
                JsonArray asJsonArray = jsonElement.getAsJsonArray();
                ArrayList arrayList = new ArrayList(asJsonArray.size());
                for (int i = 0; i < asJsonArray.size(); i++) {
                    arrayList.add(asJsonArray.get(i).getAsString());
                }
                return arrayList;
            }
            return Collections.singletonList(jsonElement.getAsString());
        }
        return emptyList;
    }

    private Date getDate(JsonObject jsonObject, String str) {
        if (jsonObject.has(str)) {
            return new Date(jsonObject.get(str).getAsLong() * 1000);
        }
        return null;
    }

    private String getString(JsonObject jsonObject, String str) {
        if (jsonObject.has(str)) {
            return jsonObject.get(str).getAsString();
        }
        return null;
    }
}
