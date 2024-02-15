package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzfd {
    final /* synthetic */ zzfi zza;
    private final String zzb;
    private final Bundle zzc;
    private Bundle zzd;

    public zzfd(zzfi zzfiVar, String str, Bundle bundle) {
        this.zza = zzfiVar;
        Preconditions.checkNotEmpty("default_event_parameters");
        this.zzb = "default_event_parameters";
        this.zzc = new Bundle();
    }

    public final Bundle zza() {
        char c;
        if (this.zzd == null) {
            String string = this.zza.zza().getString(this.zzb, null);
            if (string != null) {
                try {
                    Bundle bundle = new Bundle();
                    JSONArray jSONArray = new JSONArray(string);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            String string2 = jSONObject.getString(OperatorName.ENDPATH);
                            String string3 = jSONObject.getString("t");
                            int hashCode = string3.hashCode();
                            if (hashCode == 100) {
                                if (string3.equals(OperatorName.SET_LINE_DASHPATTERN)) {
                                    c = 1;
                                }
                                c = 65535;
                            } else if (hashCode != 108) {
                                if (hashCode == 115 && string3.equals(OperatorName.CLOSE_AND_STROKE)) {
                                    c = 0;
                                }
                                c = 65535;
                            } else {
                                if (string3.equals(OperatorName.LINE_TO)) {
                                    c = 2;
                                }
                                c = 65535;
                            }
                            if (c == 0) {
                                bundle.putString(string2, jSONObject.getString(OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT));
                            } else if (c == 1) {
                                bundle.putDouble(string2, Double.parseDouble(jSONObject.getString(OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT)));
                            } else if (c == 2) {
                                bundle.putLong(string2, Long.parseLong(jSONObject.getString(OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT)));
                            } else {
                                this.zza.zzt.zzaA().zzd().zzb("Unrecognized persisted bundle type. Type", string3);
                            }
                        } catch (NumberFormatException | JSONException unused) {
                            this.zza.zzt.zzaA().zzd().zza("Error reading value from SharedPreferences. Value dropped");
                        }
                    }
                    this.zzd = bundle;
                } catch (JSONException unused2) {
                    this.zza.zzt.zzaA().zzd().zza("Error loading bundle from SharedPreferences. Values will be lost");
                }
            }
            if (this.zzd == null) {
                this.zzd = this.zzc;
            }
        }
        return this.zzd;
    }

    public final void zzb(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        SharedPreferences.Editor edit = this.zza.zza().edit();
        if (bundle.size() == 0) {
            edit.remove(this.zzb);
        } else {
            String str = this.zzb;
            JSONArray jSONArray = new JSONArray();
            for (String str2 : bundle.keySet()) {
                Object obj = bundle.get(str2);
                if (obj != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(OperatorName.ENDPATH, str2);
                        jSONObject.put(OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT, obj.toString());
                        if (obj instanceof String) {
                            jSONObject.put("t", OperatorName.CLOSE_AND_STROKE);
                        } else if (obj instanceof Long) {
                            jSONObject.put("t", OperatorName.LINE_TO);
                        } else if (obj instanceof Double) {
                            jSONObject.put("t", OperatorName.SET_LINE_DASHPATTERN);
                        } else {
                            this.zza.zzt.zzaA().zzd().zzb("Cannot serialize bundle value to SharedPreferences. Type", obj.getClass());
                        }
                        jSONArray.put(jSONObject);
                    } catch (JSONException e) {
                        this.zza.zzt.zzaA().zzd().zzb("Cannot serialize bundle value to SharedPreferences", e);
                    }
                }
            }
            edit.putString(str, jSONArray.toString());
        }
        edit.apply();
        this.zzd = bundle;
    }
}
