package androidx.room;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: RoomDatabase.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "androidx.room.RoomDatabaseKt", f = "RoomDatabase.kt", i = {}, l = {50, 51}, m = "withTransaction", n = {}, s = {})
/* loaded from: classes.dex */
public final class RoomDatabaseKt$withTransaction$1<R> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    public RoomDatabaseKt$withTransaction$1(Continuation<? super RoomDatabaseKt$withTransaction$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return RoomDatabaseKt.withTransaction(null, null, this);
    }
}
