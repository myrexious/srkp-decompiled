package org.informatika.sirekap.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.response.EmptyApiResponse;

/* compiled from: ApiResponse.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001a*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u001aB\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006B!\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0002\u0010\tJ\u000e\u0010\u000f\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0015\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\bHÆ\u0003J4\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\bHÆ\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/api/ApiSuccessResponse;", "T", "Lorg/informatika/sirekap/api/ApiResponse;", "body", "linkHeader", "", "(Ljava/lang/Object;Ljava/lang/String;)V", "links", "", "(Ljava/lang/Object;Ljava/util/Map;)V", "getBody", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getLinks", "()Ljava/util/Map;", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/util/Map;)Lorg/informatika/sirekap/api/ApiSuccessResponse;", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ApiSuccessResponse<T> extends ApiResponse<T> {
    private static final String NEXT_LINK = "next";
    private final T body;
    private final Map<String, String> links;
    public static final Companion Companion = new Companion(null);
    private static final Pattern LINK_PATTERN = Pattern.compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"");
    private static final Pattern PAGE_PATTERN = Pattern.compile("\\bpage=(\\d+)");

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ApiSuccessResponse copy$default(ApiSuccessResponse apiSuccessResponse, Object obj, Map map, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = apiSuccessResponse.body;
        }
        if ((i & 2) != 0) {
            map = apiSuccessResponse.links;
        }
        return apiSuccessResponse.copy(obj, map);
    }

    public final T component1() {
        return this.body;
    }

    public final Map<String, String> component2() {
        return this.links;
    }

    public final ApiSuccessResponse<T> copy(T t, Map<String, String> links) {
        Intrinsics.checkNotNullParameter(links, "links");
        return new ApiSuccessResponse<>(t, links);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ApiSuccessResponse) {
            ApiSuccessResponse apiSuccessResponse = (ApiSuccessResponse) obj;
            return Intrinsics.areEqual(this.body, apiSuccessResponse.body) && Intrinsics.areEqual(this.links, apiSuccessResponse.links);
        }
        return false;
    }

    public int hashCode() {
        T t = this.body;
        return ((t == null ? 0 : t.hashCode()) * 31) + this.links.hashCode();
    }

    public String toString() {
        T t = this.body;
        return "ApiSuccessResponse(body=" + t + ", links=" + this.links + ")";
    }

    public final T getBody() {
        return this.body;
    }

    public final Map<String, String> getLinks() {
        return this.links;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ApiSuccessResponse(T t, Map<String, String> links) {
        super(null);
        Intrinsics.checkNotNullParameter(links, "links");
        this.body = t;
        this.links = links;
    }

    public ApiSuccessResponse(T t, String str) {
        this(t, (str == null || (r3 = Companion.extractLinks(str)) == null) ? MapsKt.emptyMap() : r3);
        Map emptyMap;
    }

    /* compiled from: ApiResponse.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fJ\u0018\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0010*\u00020\u0007H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002R\u001c\u0010\t\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0002¨\u0006\u0011"}, d2 = {"Lorg/informatika/sirekap/api/ApiSuccessResponse$Companion;", "", "()V", "LINK_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "NEXT_LINK", "", "getNEXT_LINK$annotations", "PAGE_PATTERN", "getPAGE_PATTERN$annotations", "getEmptyApiResponse", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/api/ApiResponse;", "Lorg/informatika/sirekap/api/response/EmptyApiResponse;", "extractLinks", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getNEXT_LINK$annotations() {
        }

        private static /* synthetic */ void getPAGE_PATTERN$annotations() {
        }

        private Companion() {
        }

        public final Map<String, String> extractLinks(String str) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Matcher matcher = ApiSuccessResponse.LINK_PATTERN.matcher(str);
            while (matcher.find()) {
                if (matcher.groupCount() == 2) {
                    String group = matcher.group(2);
                    Intrinsics.checkNotNull(group);
                    String group2 = matcher.group(1);
                    Intrinsics.checkNotNull(group2);
                    linkedHashMap.put(group, group2);
                }
            }
            return linkedHashMap;
        }

        public final LiveData<ApiResponse<EmptyApiResponse>> getEmptyApiResponse() {
            return new MutableLiveData(new ApiSuccessResponse(new EmptyApiResponse(true, "", "", null), ""));
        }
    }
}
