package androidx.room;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: RoomDatabase.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "androidx.room.RoomDatabaseKt", f = "RoomDatabase.kt", i = {}, l = {99}, m = "createTransactionContext", n = {}, s = {})
/* loaded from: classes.dex */
public final class RoomDatabaseKt$createTransactionContext$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    public RoomDatabaseKt$createTransactionContext$1(Continuation<? super RoomDatabaseKt$createTransactionContext$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object createTransactionContext;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        createTransactionContext = RoomDatabaseKt.createTransactionContext(null, this);
        return createTransactionContext;
    }
}
