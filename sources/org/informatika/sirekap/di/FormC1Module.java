package org.informatika.sirekap.di;

import dagger.Binds;
import dagger.Module;
import javax.inject.Singleton;
import kotlin.Metadata;
import org.informatika.sirekap.repository.DefaultFormC1Repository;
import org.informatika.sirekap.repository.FormC1Repository;
import org.informatika.sirekap.repository.fake.FakeFormC1Repository;

/* compiled from: FormC1Module.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bH'¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/di/FormC1Module;", "", "()V", "bindFakeFormC1Repository", "Lorg/informatika/sirekap/repository/FormC1Repository;", "repository", "Lorg/informatika/sirekap/repository/fake/FakeFormC1Repository;", "bindFormC1Repository", "Lorg/informatika/sirekap/repository/DefaultFormC1Repository;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Module
/* loaded from: classes2.dex */
public abstract class FormC1Module {
    @Singleton
    @Binds
    public abstract FormC1Repository bindFakeFormC1Repository(FakeFormC1Repository fakeFormC1Repository);

    @Singleton
    @Binds
    public abstract FormC1Repository bindFormC1Repository(DefaultFormC1Repository defaultFormC1Repository);
}
