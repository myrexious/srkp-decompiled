package org.informatika.sirekap.di;

import dagger.Binds;
import dagger.Module;
import javax.inject.Singleton;
import kotlin.Metadata;
import org.informatika.sirekap.repository.CertmanRepository;
import org.informatika.sirekap.repository.DefaultCertmanRepository;

/* compiled from: SecurityModule.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/di/SecurityModule;", "", "()V", "provideSirekapSecurityRepository", "Lorg/informatika/sirekap/repository/CertmanRepository;", "repository", "Lorg/informatika/sirekap/repository/DefaultCertmanRepository;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Module
/* loaded from: classes2.dex */
public abstract class SecurityModule {
    @Singleton
    @Binds
    public abstract CertmanRepository provideSirekapSecurityRepository(DefaultCertmanRepository defaultCertmanRepository);
}