package org.informatika.sirekap.db;

import androidx.room.RoomDatabase;
import kotlin.Metadata;
import org.informatika.sirekap.db.dao.AttendanceDao;
import org.informatika.sirekap.db.dao.AttendancePageDao;
import org.informatika.sirekap.db.dao.BackgroundProcessDao;
import org.informatika.sirekap.db.dao.CandidateDao;
import org.informatika.sirekap.db.dao.ElectionDao;
import org.informatika.sirekap.db.dao.ElectionPageDao;
import org.informatika.sirekap.db.dao.ElectionPageStageDao;
import org.informatika.sirekap.db.dao.FormC1AdministrationDao;
import org.informatika.sirekap.db.dao.FormC1AdministrationHal2Dao;
import org.informatika.sirekap.db.dao.FormC1ErrorDao;
import org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrasiDao;
import org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrationHal2Dao;
import org.informatika.sirekap.db.dao.FormC1KesesuaianDao;
import org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationCandidateVoteDao;
import org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationPartaiDao;
import org.informatika.sirekap.db.dao.FormC1TabulationCandidateVoteDao;
import org.informatika.sirekap.db.dao.FormC1TabulationDao;
import org.informatika.sirekap.db.dao.SecurityDao;
import org.informatika.sirekap.db.dao.SpecialOccurrenceDao;
import org.informatika.sirekap.db.dao.SpecialOccurrencePageDao;
import org.informatika.sirekap.db.dao.TpsDao;
import org.informatika.sirekap.db.dao.TpsTimeDao;
import org.informatika.sirekap.db.dao.UploadFileAttemptDao;
import org.informatika.sirekap.db.dao.UploadImageAttemptDao;
import org.informatika.sirekap.db.dao.UserDao;
import org.informatika.sirekap.db.dao.WitnessDao;

/* compiled from: AppDatabase.kt */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0016H&J\b\u0010\u0017\u001a\u00020\u0018H&J\b\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\u001cH&J\b\u0010\u001d\u001a\u00020\u001eH&J\b\u0010\u001f\u001a\u00020 H&J\b\u0010!\u001a\u00020\"H&J\b\u0010#\u001a\u00020$H&J\b\u0010%\u001a\u00020&H&J\b\u0010'\u001a\u00020(H&J\b\u0010)\u001a\u00020*H&J\b\u0010+\u001a\u00020,H&J\b\u0010-\u001a\u00020.H&J\b\u0010/\u001a\u000200H&J\b\u00101\u001a\u000202H&J\b\u00103\u001a\u000204H&J\b\u00105\u001a\u000206H&¨\u00067"}, d2 = {"Lorg/informatika/sirekap/db/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "attendanceDao", "Lorg/informatika/sirekap/db/dao/AttendanceDao;", "attendancePageDao", "Lorg/informatika/sirekap/db/dao/AttendancePageDao;", "backgroundProcessDao", "Lorg/informatika/sirekap/db/dao/BackgroundProcessDao;", "candidateDao", "Lorg/informatika/sirekap/db/dao/CandidateDao;", "electionDao", "Lorg/informatika/sirekap/db/dao/ElectionDao;", "electionPageDao", "Lorg/informatika/sirekap/db/dao/ElectionPageDao;", "electionPageStageDao", "Lorg/informatika/sirekap/db/dao/ElectionPageStageDao;", "formC1AdministrationDao", "Lorg/informatika/sirekap/db/dao/FormC1AdministrationDao;", "formC1AdministrationHal2Dao", "Lorg/informatika/sirekap/db/dao/FormC1AdministrationHal2Dao;", "formC1ErrorDao", "Lorg/informatika/sirekap/db/dao/FormC1ErrorDao;", "formC1KesesuaianAdministrasiDao", "Lorg/informatika/sirekap/db/dao/FormC1KesesuaianAdministrasiDao;", "formC1KesesuaianAdministrationHal2Dao", "Lorg/informatika/sirekap/db/dao/FormC1KesesuaianAdministrationHal2Dao;", "formC1KesesuaianDao", "Lorg/informatika/sirekap/db/dao/FormC1KesesuaianDao;", "formC1KesesuaianTabulationCandidateVoteDao", "Lorg/informatika/sirekap/db/dao/FormC1KesesuaianTabulationCandidateVoteDao;", "formC1KesesuaianTabulationPartaiDao", "Lorg/informatika/sirekap/db/dao/FormC1KesesuaianTabulationPartaiDao;", "formC1TabulationCandidateVoteDao", "Lorg/informatika/sirekap/db/dao/FormC1TabulationCandidateVoteDao;", "formC1TabulationDao", "Lorg/informatika/sirekap/db/dao/FormC1TabulationDao;", "securityDao", "Lorg/informatika/sirekap/db/dao/SecurityDao;", "specialOccurrenceDao", "Lorg/informatika/sirekap/db/dao/SpecialOccurrenceDao;", "specialOccurrencePageDao", "Lorg/informatika/sirekap/db/dao/SpecialOccurrencePageDao;", "tpsDao", "Lorg/informatika/sirekap/db/dao/TpsDao;", "tpsTimeDao", "Lorg/informatika/sirekap/db/dao/TpsTimeDao;", "uploadFileAttemptDao", "Lorg/informatika/sirekap/db/dao/UploadFileAttemptDao;", "uploadImageAttemptDao", "Lorg/informatika/sirekap/db/dao/UploadImageAttemptDao;", "userDao", "Lorg/informatika/sirekap/db/dao/UserDao;", "witnessDao", "Lorg/informatika/sirekap/db/dao/WitnessDao;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class AppDatabase extends RoomDatabase {
    public abstract AttendanceDao attendanceDao();

    public abstract AttendancePageDao attendancePageDao();

    public abstract BackgroundProcessDao backgroundProcessDao();

    public abstract CandidateDao candidateDao();

    public abstract ElectionDao electionDao();

    public abstract ElectionPageDao electionPageDao();

    public abstract ElectionPageStageDao electionPageStageDao();

    public abstract FormC1AdministrationDao formC1AdministrationDao();

    public abstract FormC1AdministrationHal2Dao formC1AdministrationHal2Dao();

    public abstract FormC1ErrorDao formC1ErrorDao();

    public abstract FormC1KesesuaianAdministrasiDao formC1KesesuaianAdministrasiDao();

    public abstract FormC1KesesuaianAdministrationHal2Dao formC1KesesuaianAdministrationHal2Dao();

    public abstract FormC1KesesuaianDao formC1KesesuaianDao();

    public abstract FormC1KesesuaianTabulationCandidateVoteDao formC1KesesuaianTabulationCandidateVoteDao();

    public abstract FormC1KesesuaianTabulationPartaiDao formC1KesesuaianTabulationPartaiDao();

    public abstract FormC1TabulationCandidateVoteDao formC1TabulationCandidateVoteDao();

    public abstract FormC1TabulationDao formC1TabulationDao();

    public abstract SecurityDao securityDao();

    public abstract SpecialOccurrenceDao specialOccurrenceDao();

    public abstract SpecialOccurrencePageDao specialOccurrencePageDao();

    public abstract TpsDao tpsDao();

    public abstract TpsTimeDao tpsTimeDao();

    public abstract UploadFileAttemptDao uploadFileAttemptDao();

    public abstract UploadImageAttemptDao uploadImageAttemptDao();

    public abstract UserDao userDao();

    public abstract WitnessDao witnessDao();
}
