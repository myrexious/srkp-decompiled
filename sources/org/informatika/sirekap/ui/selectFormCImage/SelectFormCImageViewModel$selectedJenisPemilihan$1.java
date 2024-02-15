package org.informatika.sirekap.ui.selectFormCImage;

import java.util.Map;
import java.util.function.BiConsumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* compiled from: SelectFormCImageViewModel.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\t\u0018\u00010\u0001¢\u0006\u0002\b\u00022\r\u0010\u0003\u001a\t\u0018\u00010\u0004¢\u0006\u0002\b\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "Lkotlin/jvm/JvmSuppressWildcards;", "it", "", "invoke", "(Ljava/lang/Integer;)Ljava/lang/String;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SelectFormCImageViewModel$selectedJenisPemilihan$1 extends Lambda implements Function1<Integer, String> {
    public static final SelectFormCImageViewModel$selectedJenisPemilihan$1 INSTANCE = new SelectFormCImageViewModel$selectedJenisPemilihan$1();

    SelectFormCImageViewModel$selectedJenisPemilihan$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final String invoke(final Integer num) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Map<String, Integer> jenisPemilihanIdMap = SelectFormCImageViewModelKt.getJenisPemilihanIdMap();
        final Function2<String, Integer, Unit> function2 = new Function2<String, Integer, Unit>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageViewModel$selectedJenisPemilihan$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Integer num2) {
                invoke2(str, num2);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke */
            public final void invoke2(String key, Integer value) {
                Intrinsics.checkNotNullParameter(key, "key");
                Intrinsics.checkNotNullParameter(value, "value");
                if (Intrinsics.areEqual(num, value)) {
                    objectRef.element = key;
                }
            }
        };
        jenisPemilihanIdMap.forEach(new BiConsumer() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageViewModel$selectedJenisPemilihan$1$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                SelectFormCImageViewModel$selectedJenisPemilihan$1.invoke$lambda$0(Function2.this, obj, obj2);
            }
        });
        return (String) objectRef.element;
    }

    public static final void invoke$lambda$0(Function2 tmp0, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj, obj2);
    }
}
