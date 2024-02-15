package org.informatika.sirekap.di;

import dagger.Binds;
import dagger.Module;
import javax.inject.Singleton;
import kotlin.Metadata;
import org.informatika.sirekap.repository.DefaultSpecialOccurrenceRepository;
import org.informatika.sirekap.repository.SpecialOccurrenceRepository;

/* compiled from: SpecialOccurrenceModule.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/di/SpecialOccurrenceModule;", "", "()V", "bindSpecialOccurrenceRepository", "Lorg/informatika/sirekap/repository/SpecialOccurrenceRepository;", "repository", "Lorg/informatika/sirekap/repository/DefaultSpecialOccurrenceRepository;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Module
/* loaded from: classes2.dex */
public abstract class SpecialOccurrenceModule {
    @Singleton
    @Binds
    public abstract SpecialOccurrenceRepository bindSpecialOccurrenceRepository(DefaultSpecialOccurrenceRepository defaultSpecialOccurrenceRepository);
}
