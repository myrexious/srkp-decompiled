package org.informatika.sirekap.db;

import androidx.core.app.NotificationCompat;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.db.dao.AttendanceDao;
import org.informatika.sirekap.db.dao.AttendanceDao_Impl;
import org.informatika.sirekap.db.dao.AttendancePageDao;
import org.informatika.sirekap.db.dao.AttendancePageDao_Impl;
import org.informatika.sirekap.db.dao.BackgroundProcessDao;
import org.informatika.sirekap.db.dao.BackgroundProcessDao_Impl;
import org.informatika.sirekap.db.dao.CandidateDao;
import org.informatika.sirekap.db.dao.CandidateDao_Impl;
import org.informatika.sirekap.db.dao.ElectionDao;
import org.informatika.sirekap.db.dao.ElectionDao_Impl;
import org.informatika.sirekap.db.dao.ElectionPageDao;
import org.informatika.sirekap.db.dao.ElectionPageDao_Impl;
import org.informatika.sirekap.db.dao.ElectionPageStageDao;
import org.informatika.sirekap.db.dao.ElectionPageStageDao_Impl;
import org.informatika.sirekap.db.dao.FormC1AdministrationDao;
import org.informatika.sirekap.db.dao.FormC1AdministrationDao_Impl;
import org.informatika.sirekap.db.dao.FormC1AdministrationHal2Dao;
import org.informatika.sirekap.db.dao.FormC1AdministrationHal2Dao_Impl;
import org.informatika.sirekap.db.dao.FormC1ErrorDao;
import org.informatika.sirekap.db.dao.FormC1ErrorDao_Impl;
import org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrasiDao;
import org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrasiDao_Impl;
import org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrationHal2Dao;
import org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrationHal2Dao_Impl;
import org.informatika.sirekap.db.dao.FormC1KesesuaianDao;
import org.informatika.sirekap.db.dao.FormC1KesesuaianDao_Impl;
import org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationCandidateVoteDao;
import org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationCandidateVoteDao_Impl;
import org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationPartaiDao;
import org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationPartaiDao_Impl;
import org.informatika.sirekap.db.dao.FormC1TabulationCandidateVoteDao;
import org.informatika.sirekap.db.dao.FormC1TabulationCandidateVoteDao_Impl;
import org.informatika.sirekap.db.dao.FormC1TabulationDao;
import org.informatika.sirekap.db.dao.FormC1TabulationDao_Impl;
import org.informatika.sirekap.db.dao.SecurityDao;
import org.informatika.sirekap.db.dao.SecurityDao_Impl;
import org.informatika.sirekap.db.dao.SpecialOccurrenceDao;
import org.informatika.sirekap.db.dao.SpecialOccurrenceDao_Impl;
import org.informatika.sirekap.db.dao.SpecialOccurrencePageDao;
import org.informatika.sirekap.db.dao.SpecialOccurrencePageDao_Impl;
import org.informatika.sirekap.db.dao.TpsDao;
import org.informatika.sirekap.db.dao.TpsDao_Impl;
import org.informatika.sirekap.db.dao.TpsTimeDao;
import org.informatika.sirekap.db.dao.TpsTimeDao_Impl;
import org.informatika.sirekap.db.dao.UploadFileAttemptDao;
import org.informatika.sirekap.db.dao.UploadFileAttemptDao_Impl;
import org.informatika.sirekap.db.dao.UploadImageAttemptDao;
import org.informatika.sirekap.db.dao.UploadImageAttemptDao_Impl;
import org.informatika.sirekap.db.dao.UserDao;
import org.informatika.sirekap.db.dao.UserDao_Impl;
import org.informatika.sirekap.db.dao.WitnessDao;
import org.informatika.sirekap.db.dao.WitnessDao_Impl;

/* loaded from: classes2.dex */
public final class AppDatabase_Impl extends AppDatabase {
    private volatile AttendanceDao _attendanceDao;
    private volatile AttendancePageDao _attendancePageDao;
    private volatile BackgroundProcessDao _backgroundProcessDao;
    private volatile CandidateDao _candidateDao;
    private volatile ElectionDao _electionDao;
    private volatile ElectionPageDao _electionPageDao;
    private volatile ElectionPageStageDao _electionPageStageDao;
    private volatile FormC1AdministrationDao _formC1AdministrationDao;
    private volatile FormC1AdministrationHal2Dao _formC1AdministrationHal2Dao;
    private volatile FormC1ErrorDao _formC1ErrorDao;
    private volatile FormC1KesesuaianAdministrasiDao _formC1KesesuaianAdministrasiDao;
    private volatile FormC1KesesuaianAdministrationHal2Dao _formC1KesesuaianAdministrationHal2Dao;
    private volatile FormC1KesesuaianDao _formC1KesesuaianDao;
    private volatile FormC1KesesuaianTabulationCandidateVoteDao _formC1KesesuaianTabulationCandidateVoteDao;
    private volatile FormC1KesesuaianTabulationPartaiDao _formC1KesesuaianTabulationPartaiDao;
    private volatile FormC1TabulationCandidateVoteDao _formC1TabulationCandidateVoteDao;
    private volatile FormC1TabulationDao _formC1TabulationDao;
    private volatile SecurityDao _securityDao;
    private volatile SpecialOccurrenceDao _specialOccurrenceDao;
    private volatile SpecialOccurrencePageDao _specialOccurrencePageDao;
    private volatile TpsDao _tpsDao;
    private volatile TpsTimeDao _tpsTimeDao;
    private volatile UploadFileAttemptDao _uploadFileAttemptDao;
    private volatile UploadImageAttemptDao _uploadImageAttemptDao;
    private volatile UserDao _userDao;
    private volatile WitnessDao _witnessDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(64) { // from class: org.informatika.sirekap.db.AppDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `election` (`id` TEXT NOT NULL, `jenis` INTEGER NOT NULL, `pemilihan` TEXT NOT NULL, `dapil` TEXT, `jmlLembar` INTEGER NOT NULL, `electionType` TEXT NOT NULL, `isCreatePdf` INTEGER NOT NULL, `uploadPdfStatus` INTEGER NOT NULL, `pdfFilePath` TEXT, `pdfFileHash` TEXT, `pdfWitnessFilePath` TEXT, `isUploadedPdf` INTEGER NOT NULL, `isUploadedPdfOffline` INTEGER NOT NULL, `isZipped` INTEGER NOT NULL, `tps_kodeTps` TEXT NOT NULL, `tps_name` TEXT NOT NULL, `tps_kelurahan_id` TEXT, `tps_kelurahan_name` TEXT, `tps_kelurahan_kecamatan_id` TEXT, `tps_kelurahan_kecamatan_name` TEXT, `tps_kelurahan_kecamatan_kotaKabupaten_id` TEXT, `tps_kelurahan_kecamatan_kotaKabupaten_name` TEXT, `tps_kelurahan_kecamatan_kotaKabupaten_provinsi_id` TEXT, `tps_kelurahan_kecamatan_kotaKabupaten_provinsi_name` TEXT, PRIMARY KEY(`id`, `electionType`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `witnesses` (`idPetugas` INTEGER NOT NULL, `nama` TEXT NOT NULL, `nik` TEXT NOT NULL, `noHandphone` TEXT NOT NULL, `jenisPemeriksa` TEXT NOT NULL, `kodeTps` TEXT NOT NULL, `urutan` TEXT NOT NULL, PRIMARY KEY(`noHandphone`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `witness_shares` (`noHandphone` TEXT NOT NULL, `jenisPemilihan` TEXT NOT NULL, `isShared` INTEGER NOT NULL, PRIMARY KEY(`noHandphone`, `jenisPemilihan`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `witness_pemeriksas` (`noHandphone` TEXT NOT NULL, `jenisPemilihan` TEXT NOT NULL, `idPilihan` INTEGER NOT NULL, `url` TEXT NOT NULL, PRIMARY KEY(`noHandphone`, `jenisPemilihan`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `election_page` (`id` TEXT NOT NULL, `jenisPemilihan` TEXT NOT NULL, `electionId` TEXT NOT NULL, `kind` INTEGER NOT NULL, `number` INTEGER NOT NULL, `currentStageIndex` INTEGER NOT NULL, `takePhotoAttempt` INTEGER NOT NULL, `photoPath` TEXT, `croppedPhotoPath` TEXT, `correctedPhotoPath` TEXT, `perspectiveCorrectionError` TEXT, `isCorrected` INTEGER NOT NULL, `idImage` TEXT, `isSaved` INTEGER, `signatureCroppedPhoto` TEXT, `hashDocumentCropped` TEXT, `hashDocumentCorrected` TEXT, `isContinueVerify` INTEGER NOT NULL, `hasVisionError` INTEGER NOT NULL, `aprilTagCode` INTEGER, PRIMARY KEY(`id`, `electionId`, `kind`, `number`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `election_page_stage` (`electionId` TEXT NOT NULL, `electionPageId` TEXT NOT NULL, `type` TEXT NOT NULL, `status` INTEGER NOT NULL, `isOffline` INTEGER NOT NULL, PRIMARY KEY(`electionPageId`, `type`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `candidates` (`index` INTEGER NOT NULL, `idPilihan` INTEGER NOT NULL, `electionId` TEXT NOT NULL, `electionPemilihan` TEXT NOT NULL, `noUrutPencalonan` INTEGER NOT NULL, `noUrutLabel` INTEGER NOT NULL, `namaCalonKepala` TEXT NOT NULL, `namaCalonWakil` TEXT, `indexPartai` INTEGER, `idPartai` INTEGER, `namaPartai` TEXT, `noUrutPencalonanPartai` INTEGER, `noUrutLabelPartai` INTEGER, PRIMARY KEY(`idPilihan`, `electionId`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` TEXT NOT NULL, `fullName` TEXT NOT NULL, `role` TEXT NOT NULL, `namaProfil` TEXT NOT NULL, `profilStart` TEXT NOT NULL, `profilEnd` TEXT NOT NULL, `tps_kodeTps` TEXT NOT NULL, `tps_name` TEXT NOT NULL, `tps_kelurahan_id` TEXT, `tps_kelurahan_name` TEXT, `tps_kelurahan_kecamatan_id` TEXT, `tps_kelurahan_kecamatan_name` TEXT, `tps_kelurahan_kecamatan_kotaKabupaten_id` TEXT, `tps_kelurahan_kecamatan_kotaKabupaten_name` TEXT, `tps_kelurahan_kecamatan_kotaKabupaten_provinsi_id` TEXT, `tps_kelurahan_kecamatan_kotaKabupaten_provinsi_name` TEXT, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `form_c1_administration` (`idImage` TEXT NOT NULL, `isLn` INTEGER NOT NULL, `isLnPos` INTEGER NOT NULL, `formType` INTEGER NOT NULL, `pemilihDpt_L` INTEGER NOT NULL, `pemilihDpt_P` INTEGER NOT NULL, `totalPemilihDpt` INTEGER NOT NULL, `penggunaDpt_L` INTEGER NOT NULL, `penggunaDpt_P` INTEGER NOT NULL, `totalPenggunaDpt` INTEGER NOT NULL, `penggunaDptb_L` INTEGER, `penggunaDptb_P` INTEGER, `totalPenggunaDptb` INTEGER, `penggunaDpk_L` INTEGER, `penggunaDpk_P` INTEGER, `totalPenggunaDpk` INTEGER, `totalPengguna_L` INTEGER, `totalPengguna_P` INTEGER, `totalPengguna` INTEGER, `suratDiterima` INTEGER NOT NULL, `suratDikembalikan` INTEGER NOT NULL, `suratTidakDigunakan` INTEGER NOT NULL, `suratTidakDikembalikan` INTEGER, `suratKembaliPPLN` INTEGER, `suratTidakTerpakai` INTEGER, `suratDigunakan` INTEGER NOT NULL, `pemilihDisabilitas_L` INTEGER NOT NULL, `pemilihDisabilitas_P` INTEGER NOT NULL, `totalPemilihDisabilitas` INTEGER NOT NULL, PRIMARY KEY(`idImage`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `form_c1_administration_hal2` (`idImage` TEXT NOT NULL, `isLn` INTEGER NOT NULL, `isLnPos` INTEGER NOT NULL, `formType` INTEGER NOT NULL, `suratSuaraSah` INTEGER, `suratSuaraTidakSah` INTEGER, `totalSuratSuara` INTEGER, PRIMARY KEY(`idImage`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `form_c1_tabulation_candidate_vote` (`idImage` TEXT NOT NULL, `index` INTEGER NOT NULL, `vote` INTEGER NOT NULL, PRIMARY KEY(`idImage`, `index`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `form_c1_tabulation_partai` (`idImage` TEXT NOT NULL, `formType` INTEGER NOT NULL, `suratSahPartaiDanCalon` INTEGER, PRIMARY KEY(`idImage`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `form_c1_error` (`idImage` TEXT NOT NULL, `formType` INTEGER NOT NULL, `errorType` INTEGER NOT NULL, `error` TEXT NOT NULL, PRIMARY KEY(`idImage`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `form_c1_kesesuaian` (`idImage` TEXT NOT NULL, `isSesuai` INTEGER NOT NULL, `komentar` TEXT, PRIMARY KEY(`idImage`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `form_c1_kesesuaian_administration` (`idImage` TEXT NOT NULL, `isLnPos` INTEGER NOT NULL, `jenisPemilihan` TEXT NOT NULL, `pemilihDpt_L` INTEGER, `pemilihDpt_P` INTEGER, `totalPemilihDpt` INTEGER, `penggunaDpt_L` INTEGER, `penggunaDpt_P` INTEGER, `totalPenggunaDpt` INTEGER, `penggunaDptb_L` INTEGER, `penggunaDptb_P` INTEGER, `totalPenggunaDptb` INTEGER, `penggunaDpk_L` INTEGER, `penggunaDpk_P` INTEGER, `totalPenggunaDpk` INTEGER, `totalPengguna_L` INTEGER, `totalPengguna_P` INTEGER, `totalPengguna` INTEGER, `suratDiterima` INTEGER, `suratDikembalikan` INTEGER, `suratTidakDigunakan` INTEGER, `suratTidakDikembalikan` INTEGER, `suratKembaliPPLN` INTEGER, `suratTidakTerpakai` INTEGER, `suratDigunakan` INTEGER, `pemilihDisabilitas_L` INTEGER, `pemilihDisabilitas_P` INTEGER, `totalPemilihDisabilitas` INTEGER, `pemilihDpt_LCorrected` INTEGER, `pemilihDpt_PCorrected` INTEGER, `totalPemilihDptCorrected` INTEGER, `penggunaDpt_LCorrected` INTEGER, `penggunaDpt_PCorrected` INTEGER, `totalPenggunaDptCorrected` INTEGER, `penggunaDptb_LCorrected` INTEGER, `penggunaDptb_PCorrected` INTEGER, `totalPenggunaDptbCorrected` INTEGER, `penggunaDpk_LCorrected` INTEGER, `penggunaDpk_PCorrected` INTEGER, `totalPenggunaDpkCorrected` INTEGER, `totalPengguna_LCorrected` INTEGER, `totalPengguna_PCorrected` INTEGER, `totalPenggunaCorrected` INTEGER, `suratDiterimaCorrected` INTEGER, `suratDikembalikanCorrected` INTEGER, `suratTidakDigunakanCorrected` INTEGER, `suratTidakDikembalikanCorrected` INTEGER, `suratKembaliPPLNCorrected` INTEGER, `suratTidakTerpakaiCorrected` INTEGER, `suratDigunakanCorrected` INTEGER, `pemilihDisabilitas_LCorrected` INTEGER, `pemilihDisabilitas_PCorrected` INTEGER, `totalPemilihDisabilitasCorrected` INTEGER, PRIMARY KEY(`idImage`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `form_c1_kesesuaian_administration_hal2` (`idImage` TEXT NOT NULL, `suratSuaraSah` INTEGER, `suratSuaraTidakSah` INTEGER, `totalSuratSuara` INTEGER, `suratSuaraSahCorrected` INTEGER, `suratSuaraTidakSahCorrected` INTEGER, `totalSuratSuaraCorrected` INTEGER, PRIMARY KEY(`idImage`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `form_c1_kesesuaian_tabulation_candidate_vote` (`idImage` TEXT NOT NULL, `index` INTEGER NOT NULL, `vote` INTEGER, `voteCorrected` INTEGER, PRIMARY KEY(`idImage`, `index`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `form_c1_kesesuaian_tabulation_partai` (`idImage` TEXT NOT NULL, `suratSahPartaiDanCalon` INTEGER, `suratSahPartaiDanCalonCorrected` INTEGER, PRIMARY KEY(`idImage`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `upload_image_attempts` (`electionPageId` TEXT NOT NULL, `jenisImage` INTEGER NOT NULL, `attempt` INTEGER NOT NULL, `isSuccess` INTEGER NOT NULL, PRIMARY KEY(`electionPageId`, `jenisImage`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `upload_file_attempts` (`id` TEXT NOT NULL, `type` TEXT NOT NULL, `attempt` INTEGER NOT NULL, `isSuccess` INTEGER NOT NULL, PRIMARY KEY(`id`, `type`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `tps` (`kodeTps` TEXT NOT NULL, `name` TEXT NOT NULL, `kelurahan_id` TEXT, `kelurahan_name` TEXT, `kelurahan_kecamatan_id` TEXT, `kelurahan_kecamatan_name` TEXT, `kelurahan_kecamatan_kotaKabupaten_id` TEXT, `kelurahan_kecamatan_kotaKabupaten_name` TEXT, `kelurahan_kecamatan_kotaKabupaten_provinsi_id` TEXT, `kelurahan_kecamatan_kotaKabupaten_provinsi_name` TEXT, PRIMARY KEY(`kodeTps`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `tps_time` (`kodeTps` TEXT NOT NULL, `jenisWaktu` INTEGER NOT NULL, `jenisPemilihan` TEXT NOT NULL, `startDate` INTEGER NOT NULL, `startTimeHour` INTEGER NOT NULL, `startTimeMinute` INTEGER NOT NULL, `endDate` INTEGER NOT NULL, `endTimeHour` INTEGER NOT NULL, `endTimeMinute` INTEGER NOT NULL, `isSubmittedOffline` INTEGER NOT NULL, PRIMARY KEY(`kodeTps`, `jenisWaktu`, `jenisPemilihan`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `attendance_page` (`id` INTEGER NOT NULL, `kodeTps` TEXT NOT NULL, `photoPath` TEXT NOT NULL, `croppedPhotoPath` TEXT NOT NULL, `urutan` INTEGER NOT NULL, `hashDocumentCropped` TEXT NOT NULL, `checked` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `attendance` (`kodeTps` TEXT NOT NULL, `isPdf` INTEGER NOT NULL, `uploadPdfStatus` INTEGER NOT NULL, `isUploadedPdf` INTEGER NOT NULL, `isUploadedPdfOffline` INTEGER NOT NULL, `pdfFilePath` TEXT, `pdfFileHash` TEXT, PRIMARY KEY(`kodeTps`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `special_occurrence` (`kodeTps` TEXT NOT NULL, `isPdf` INTEGER NOT NULL, `uploadPdfStatus` INTEGER NOT NULL, `isUploadedPdf` INTEGER NOT NULL, `isUploadedPdfOffline` INTEGER NOT NULL, `pdfFilePath` TEXT, `pdfFileHash` TEXT, PRIMARY KEY(`kodeTps`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `special_occurrence_page` (`id` INTEGER NOT NULL, `kodeTps` TEXT NOT NULL, `photoPath` TEXT NOT NULL, `croppedPhotoPath` TEXT NOT NULL, `hashDocumentCropped` TEXT NOT NULL, `checked` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `background_processses` (`id` TEXT NOT NULL, `startedAt` INTEGER NOT NULL, `endedAt` INTEGER, `isSuccess` INTEGER, `errorMessage` TEXT, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `security_properties` (`key` TEXT NOT NULL, `value` TEXT NOT NULL, PRIMARY KEY(`key`))");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '75090633c7ee6c7f5603648761b16ee1')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `election`");
                _db.execSQL("DROP TABLE IF EXISTS `witnesses`");
                _db.execSQL("DROP TABLE IF EXISTS `witness_shares`");
                _db.execSQL("DROP TABLE IF EXISTS `witness_pemeriksas`");
                _db.execSQL("DROP TABLE IF EXISTS `election_page`");
                _db.execSQL("DROP TABLE IF EXISTS `election_page_stage`");
                _db.execSQL("DROP TABLE IF EXISTS `candidates`");
                _db.execSQL("DROP TABLE IF EXISTS `users`");
                _db.execSQL("DROP TABLE IF EXISTS `form_c1_administration`");
                _db.execSQL("DROP TABLE IF EXISTS `form_c1_administration_hal2`");
                _db.execSQL("DROP TABLE IF EXISTS `form_c1_tabulation_candidate_vote`");
                _db.execSQL("DROP TABLE IF EXISTS `form_c1_tabulation_partai`");
                _db.execSQL("DROP TABLE IF EXISTS `form_c1_error`");
                _db.execSQL("DROP TABLE IF EXISTS `form_c1_kesesuaian`");
                _db.execSQL("DROP TABLE IF EXISTS `form_c1_kesesuaian_administration`");
                _db.execSQL("DROP TABLE IF EXISTS `form_c1_kesesuaian_administration_hal2`");
                _db.execSQL("DROP TABLE IF EXISTS `form_c1_kesesuaian_tabulation_candidate_vote`");
                _db.execSQL("DROP TABLE IF EXISTS `form_c1_kesesuaian_tabulation_partai`");
                _db.execSQL("DROP TABLE IF EXISTS `upload_image_attempts`");
                _db.execSQL("DROP TABLE IF EXISTS `upload_file_attempts`");
                _db.execSQL("DROP TABLE IF EXISTS `tps`");
                _db.execSQL("DROP TABLE IF EXISTS `tps_time`");
                _db.execSQL("DROP TABLE IF EXISTS `attendance_page`");
                _db.execSQL("DROP TABLE IF EXISTS `attendance`");
                _db.execSQL("DROP TABLE IF EXISTS `special_occurrence`");
                _db.execSQL("DROP TABLE IF EXISTS `special_occurrence_page`");
                _db.execSQL("DROP TABLE IF EXISTS `background_processses`");
                _db.execSQL("DROP TABLE IF EXISTS `security_properties`");
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase _db) {
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                AppDatabase_Impl.this.mDatabase = _db;
                AppDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap hashMap = new HashMap(24);
                hashMap.put(JobType.ID, new TableInfo.Column(JobType.ID, "TEXT", true, 1, null, 1));
                hashMap.put("jenis", new TableInfo.Column("jenis", "INTEGER", true, 0, null, 1));
                hashMap.put("pemilihan", new TableInfo.Column("pemilihan", "TEXT", true, 0, null, 1));
                hashMap.put("dapil", new TableInfo.Column("dapil", "TEXT", false, 0, null, 1));
                hashMap.put("jmlLembar", new TableInfo.Column("jmlLembar", "INTEGER", true, 0, null, 1));
                hashMap.put("electionType", new TableInfo.Column("electionType", "TEXT", true, 2, null, 1));
                hashMap.put("isCreatePdf", new TableInfo.Column("isCreatePdf", "INTEGER", true, 0, null, 1));
                hashMap.put("uploadPdfStatus", new TableInfo.Column("uploadPdfStatus", "INTEGER", true, 0, null, 1));
                hashMap.put("pdfFilePath", new TableInfo.Column("pdfFilePath", "TEXT", false, 0, null, 1));
                hashMap.put("pdfFileHash", new TableInfo.Column("pdfFileHash", "TEXT", false, 0, null, 1));
                hashMap.put("pdfWitnessFilePath", new TableInfo.Column("pdfWitnessFilePath", "TEXT", false, 0, null, 1));
                hashMap.put("isUploadedPdf", new TableInfo.Column("isUploadedPdf", "INTEGER", true, 0, null, 1));
                hashMap.put("isUploadedPdfOffline", new TableInfo.Column("isUploadedPdfOffline", "INTEGER", true, 0, null, 1));
                hashMap.put("isZipped", new TableInfo.Column("isZipped", "INTEGER", true, 0, null, 1));
                hashMap.put("tps_kodeTps", new TableInfo.Column("tps_kodeTps", "TEXT", true, 0, null, 1));
                hashMap.put("tps_name", new TableInfo.Column("tps_name", "TEXT", true, 0, null, 1));
                hashMap.put("tps_kelurahan_id", new TableInfo.Column("tps_kelurahan_id", "TEXT", false, 0, null, 1));
                hashMap.put("tps_kelurahan_name", new TableInfo.Column("tps_kelurahan_name", "TEXT", false, 0, null, 1));
                hashMap.put("tps_kelurahan_kecamatan_id", new TableInfo.Column("tps_kelurahan_kecamatan_id", "TEXT", false, 0, null, 1));
                hashMap.put("tps_kelurahan_kecamatan_name", new TableInfo.Column("tps_kelurahan_kecamatan_name", "TEXT", false, 0, null, 1));
                hashMap.put("tps_kelurahan_kecamatan_kotaKabupaten_id", new TableInfo.Column("tps_kelurahan_kecamatan_kotaKabupaten_id", "TEXT", false, 0, null, 1));
                hashMap.put("tps_kelurahan_kecamatan_kotaKabupaten_name", new TableInfo.Column("tps_kelurahan_kecamatan_kotaKabupaten_name", "TEXT", false, 0, null, 1));
                hashMap.put("tps_kelurahan_kecamatan_kotaKabupaten_provinsi_id", new TableInfo.Column("tps_kelurahan_kecamatan_kotaKabupaten_provinsi_id", "TEXT", false, 0, null, 1));
                hashMap.put("tps_kelurahan_kecamatan_kotaKabupaten_provinsi_name", new TableInfo.Column("tps_kelurahan_kecamatan_kotaKabupaten_provinsi_name", "TEXT", false, 0, null, 1));
                TableInfo tableInfo = new TableInfo("election", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(_db, "election");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "election(org.informatika.sirekap.model.Election).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(7);
                hashMap2.put("idPetugas", new TableInfo.Column("idPetugas", "INTEGER", true, 0, null, 1));
                hashMap2.put("nama", new TableInfo.Column("nama", "TEXT", true, 0, null, 1));
                hashMap2.put("nik", new TableInfo.Column("nik", "TEXT", true, 0, null, 1));
                hashMap2.put("noHandphone", new TableInfo.Column("noHandphone", "TEXT", true, 1, null, 1));
                hashMap2.put("jenisPemeriksa", new TableInfo.Column("jenisPemeriksa", "TEXT", true, 0, null, 1));
                hashMap2.put("kodeTps", new TableInfo.Column("kodeTps", "TEXT", true, 0, null, 1));
                hashMap2.put("urutan", new TableInfo.Column("urutan", "TEXT", true, 0, null, 1));
                TableInfo tableInfo2 = new TableInfo("witnesses", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(_db, "witnesses");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, "witnesses(org.informatika.sirekap.model.Witness).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                HashMap hashMap3 = new HashMap(3);
                hashMap3.put("noHandphone", new TableInfo.Column("noHandphone", "TEXT", true, 1, null, 1));
                hashMap3.put("jenisPemilihan", new TableInfo.Column("jenisPemilihan", "TEXT", true, 2, null, 1));
                hashMap3.put("isShared", new TableInfo.Column("isShared", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo("witness_shares", hashMap3, new HashSet(0), new HashSet(0));
                TableInfo read3 = TableInfo.read(_db, "witness_shares");
                if (!tableInfo3.equals(read3)) {
                    return new RoomOpenHelper.ValidationResult(false, "witness_shares(org.informatika.sirekap.model.WitnessShare).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                }
                HashMap hashMap4 = new HashMap(4);
                hashMap4.put("noHandphone", new TableInfo.Column("noHandphone", "TEXT", true, 1, null, 1));
                hashMap4.put("jenisPemilihan", new TableInfo.Column("jenisPemilihan", "TEXT", true, 2, null, 1));
                hashMap4.put("idPilihan", new TableInfo.Column("idPilihan", "INTEGER", true, 0, null, 1));
                hashMap4.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, 1));
                TableInfo tableInfo4 = new TableInfo("witness_pemeriksas", hashMap4, new HashSet(0), new HashSet(0));
                TableInfo read4 = TableInfo.read(_db, "witness_pemeriksas");
                if (!tableInfo4.equals(read4)) {
                    return new RoomOpenHelper.ValidationResult(false, "witness_pemeriksas(org.informatika.sirekap.model.WitnessPemeriksa).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
                }
                HashMap hashMap5 = new HashMap(20);
                hashMap5.put(JobType.ID, new TableInfo.Column(JobType.ID, "TEXT", true, 1, null, 1));
                hashMap5.put("jenisPemilihan", new TableInfo.Column("jenisPemilihan", "TEXT", true, 0, null, 1));
                hashMap5.put("electionId", new TableInfo.Column("electionId", "TEXT", true, 2, null, 1));
                hashMap5.put("kind", new TableInfo.Column("kind", "INTEGER", true, 3, null, 1));
                hashMap5.put("number", new TableInfo.Column("number", "INTEGER", true, 4, null, 1));
                hashMap5.put("currentStageIndex", new TableInfo.Column("currentStageIndex", "INTEGER", true, 0, null, 1));
                hashMap5.put("takePhotoAttempt", new TableInfo.Column("takePhotoAttempt", "INTEGER", true, 0, null, 1));
                hashMap5.put("photoPath", new TableInfo.Column("photoPath", "TEXT", false, 0, null, 1));
                hashMap5.put("croppedPhotoPath", new TableInfo.Column("croppedPhotoPath", "TEXT", false, 0, null, 1));
                hashMap5.put("correctedPhotoPath", new TableInfo.Column("correctedPhotoPath", "TEXT", false, 0, null, 1));
                hashMap5.put("perspectiveCorrectionError", new TableInfo.Column("perspectiveCorrectionError", "TEXT", false, 0, null, 1));
                hashMap5.put("isCorrected", new TableInfo.Column("isCorrected", "INTEGER", true, 0, null, 1));
                hashMap5.put("idImage", new TableInfo.Column("idImage", "TEXT", false, 0, null, 1));
                hashMap5.put("isSaved", new TableInfo.Column("isSaved", "INTEGER", false, 0, null, 1));
                hashMap5.put("signatureCroppedPhoto", new TableInfo.Column("signatureCroppedPhoto", "TEXT", false, 0, null, 1));
                hashMap5.put("hashDocumentCropped", new TableInfo.Column("hashDocumentCropped", "TEXT", false, 0, null, 1));
                hashMap5.put("hashDocumentCorrected", new TableInfo.Column("hashDocumentCorrected", "TEXT", false, 0, null, 1));
                hashMap5.put("isContinueVerify", new TableInfo.Column("isContinueVerify", "INTEGER", true, 0, null, 1));
                hashMap5.put("hasVisionError", new TableInfo.Column("hasVisionError", "INTEGER", true, 0, null, 1));
                hashMap5.put("aprilTagCode", new TableInfo.Column("aprilTagCode", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo5 = new TableInfo("election_page", hashMap5, new HashSet(0), new HashSet(0));
                TableInfo read5 = TableInfo.read(_db, "election_page");
                if (!tableInfo5.equals(read5)) {
                    return new RoomOpenHelper.ValidationResult(false, "election_page(org.informatika.sirekap.model.ElectionPage).\n Expected:\n" + tableInfo5 + "\n Found:\n" + read5);
                }
                HashMap hashMap6 = new HashMap(5);
                hashMap6.put("electionId", new TableInfo.Column("electionId", "TEXT", true, 0, null, 1));
                hashMap6.put("electionPageId", new TableInfo.Column("electionPageId", "TEXT", true, 1, null, 1));
                hashMap6.put("type", new TableInfo.Column("type", "TEXT", true, 2, null, 1));
                hashMap6.put(NotificationCompat.CATEGORY_STATUS, new TableInfo.Column(NotificationCompat.CATEGORY_STATUS, "INTEGER", true, 0, null, 1));
                hashMap6.put("isOffline", new TableInfo.Column("isOffline", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo6 = new TableInfo("election_page_stage", hashMap6, new HashSet(0), new HashSet(0));
                TableInfo read6 = TableInfo.read(_db, "election_page_stage");
                if (!tableInfo6.equals(read6)) {
                    return new RoomOpenHelper.ValidationResult(false, "election_page_stage(org.informatika.sirekap.model.ElectionPageStage).\n Expected:\n" + tableInfo6 + "\n Found:\n" + read6);
                }
                HashMap hashMap7 = new HashMap(13);
                hashMap7.put(FirebaseAnalytics.Param.INDEX, new TableInfo.Column(FirebaseAnalytics.Param.INDEX, "INTEGER", true, 0, null, 1));
                hashMap7.put("idPilihan", new TableInfo.Column("idPilihan", "INTEGER", true, 1, null, 1));
                hashMap7.put("electionId", new TableInfo.Column("electionId", "TEXT", true, 2, null, 1));
                hashMap7.put("electionPemilihan", new TableInfo.Column("electionPemilihan", "TEXT", true, 0, null, 1));
                hashMap7.put("noUrutPencalonan", new TableInfo.Column("noUrutPencalonan", "INTEGER", true, 0, null, 1));
                hashMap7.put("noUrutLabel", new TableInfo.Column("noUrutLabel", "INTEGER", true, 0, null, 1));
                hashMap7.put("namaCalonKepala", new TableInfo.Column("namaCalonKepala", "TEXT", true, 0, null, 1));
                hashMap7.put("namaCalonWakil", new TableInfo.Column("namaCalonWakil", "TEXT", false, 0, null, 1));
                hashMap7.put("indexPartai", new TableInfo.Column("indexPartai", "INTEGER", false, 0, null, 1));
                hashMap7.put("idPartai", new TableInfo.Column("idPartai", "INTEGER", false, 0, null, 1));
                hashMap7.put("namaPartai", new TableInfo.Column("namaPartai", "TEXT", false, 0, null, 1));
                hashMap7.put("noUrutPencalonanPartai", new TableInfo.Column("noUrutPencalonanPartai", "INTEGER", false, 0, null, 1));
                hashMap7.put("noUrutLabelPartai", new TableInfo.Column("noUrutLabelPartai", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo7 = new TableInfo("candidates", hashMap7, new HashSet(0), new HashSet(0));
                TableInfo read7 = TableInfo.read(_db, "candidates");
                if (!tableInfo7.equals(read7)) {
                    return new RoomOpenHelper.ValidationResult(false, "candidates(org.informatika.sirekap.model.Candidate).\n Expected:\n" + tableInfo7 + "\n Found:\n" + read7);
                }
                HashMap hashMap8 = new HashMap(16);
                hashMap8.put(JobType.ID, new TableInfo.Column(JobType.ID, "TEXT", true, 1, null, 1));
                hashMap8.put("fullName", new TableInfo.Column("fullName", "TEXT", true, 0, null, 1));
                hashMap8.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, 1));
                hashMap8.put("namaProfil", new TableInfo.Column("namaProfil", "TEXT", true, 0, null, 1));
                hashMap8.put("profilStart", new TableInfo.Column("profilStart", "TEXT", true, 0, null, 1));
                hashMap8.put("profilEnd", new TableInfo.Column("profilEnd", "TEXT", true, 0, null, 1));
                hashMap8.put("tps_kodeTps", new TableInfo.Column("tps_kodeTps", "TEXT", true, 0, null, 1));
                hashMap8.put("tps_name", new TableInfo.Column("tps_name", "TEXT", true, 0, null, 1));
                hashMap8.put("tps_kelurahan_id", new TableInfo.Column("tps_kelurahan_id", "TEXT", false, 0, null, 1));
                hashMap8.put("tps_kelurahan_name", new TableInfo.Column("tps_kelurahan_name", "TEXT", false, 0, null, 1));
                hashMap8.put("tps_kelurahan_kecamatan_id", new TableInfo.Column("tps_kelurahan_kecamatan_id", "TEXT", false, 0, null, 1));
                hashMap8.put("tps_kelurahan_kecamatan_name", new TableInfo.Column("tps_kelurahan_kecamatan_name", "TEXT", false, 0, null, 1));
                hashMap8.put("tps_kelurahan_kecamatan_kotaKabupaten_id", new TableInfo.Column("tps_kelurahan_kecamatan_kotaKabupaten_id", "TEXT", false, 0, null, 1));
                hashMap8.put("tps_kelurahan_kecamatan_kotaKabupaten_name", new TableInfo.Column("tps_kelurahan_kecamatan_kotaKabupaten_name", "TEXT", false, 0, null, 1));
                hashMap8.put("tps_kelurahan_kecamatan_kotaKabupaten_provinsi_id", new TableInfo.Column("tps_kelurahan_kecamatan_kotaKabupaten_provinsi_id", "TEXT", false, 0, null, 1));
                hashMap8.put("tps_kelurahan_kecamatan_kotaKabupaten_provinsi_name", new TableInfo.Column("tps_kelurahan_kecamatan_kotaKabupaten_provinsi_name", "TEXT", false, 0, null, 1));
                TableInfo tableInfo8 = new TableInfo("users", hashMap8, new HashSet(0), new HashSet(0));
                TableInfo read8 = TableInfo.read(_db, "users");
                if (!tableInfo8.equals(read8)) {
                    return new RoomOpenHelper.ValidationResult(false, "users(org.informatika.sirekap.model.User).\n Expected:\n" + tableInfo8 + "\n Found:\n" + read8);
                }
                HashMap hashMap9 = new HashMap(29);
                hashMap9.put("idImage", new TableInfo.Column("idImage", "TEXT", true, 1, null, 1));
                hashMap9.put("isLn", new TableInfo.Column("isLn", "INTEGER", true, 0, null, 1));
                hashMap9.put("isLnPos", new TableInfo.Column("isLnPos", "INTEGER", true, 0, null, 1));
                hashMap9.put("formType", new TableInfo.Column("formType", "INTEGER", true, 0, null, 1));
                hashMap9.put("pemilihDpt_L", new TableInfo.Column("pemilihDpt_L", "INTEGER", true, 0, null, 1));
                hashMap9.put("pemilihDpt_P", new TableInfo.Column("pemilihDpt_P", "INTEGER", true, 0, null, 1));
                hashMap9.put("totalPemilihDpt", new TableInfo.Column("totalPemilihDpt", "INTEGER", true, 0, null, 1));
                hashMap9.put("penggunaDpt_L", new TableInfo.Column("penggunaDpt_L", "INTEGER", true, 0, null, 1));
                hashMap9.put("penggunaDpt_P", new TableInfo.Column("penggunaDpt_P", "INTEGER", true, 0, null, 1));
                hashMap9.put("totalPenggunaDpt", new TableInfo.Column("totalPenggunaDpt", "INTEGER", true, 0, null, 1));
                hashMap9.put("penggunaDptb_L", new TableInfo.Column("penggunaDptb_L", "INTEGER", false, 0, null, 1));
                hashMap9.put("penggunaDptb_P", new TableInfo.Column("penggunaDptb_P", "INTEGER", false, 0, null, 1));
                hashMap9.put("totalPenggunaDptb", new TableInfo.Column("totalPenggunaDptb", "INTEGER", false, 0, null, 1));
                hashMap9.put("penggunaDpk_L", new TableInfo.Column("penggunaDpk_L", "INTEGER", false, 0, null, 1));
                hashMap9.put("penggunaDpk_P", new TableInfo.Column("penggunaDpk_P", "INTEGER", false, 0, null, 1));
                hashMap9.put("totalPenggunaDpk", new TableInfo.Column("totalPenggunaDpk", "INTEGER", false, 0, null, 1));
                hashMap9.put("totalPengguna_L", new TableInfo.Column("totalPengguna_L", "INTEGER", false, 0, null, 1));
                hashMap9.put("totalPengguna_P", new TableInfo.Column("totalPengguna_P", "INTEGER", false, 0, null, 1));
                hashMap9.put("totalPengguna", new TableInfo.Column("totalPengguna", "INTEGER", false, 0, null, 1));
                hashMap9.put("suratDiterima", new TableInfo.Column("suratDiterima", "INTEGER", true, 0, null, 1));
                hashMap9.put("suratDikembalikan", new TableInfo.Column("suratDikembalikan", "INTEGER", true, 0, null, 1));
                hashMap9.put("suratTidakDigunakan", new TableInfo.Column("suratTidakDigunakan", "INTEGER", true, 0, null, 1));
                hashMap9.put("suratTidakDikembalikan", new TableInfo.Column("suratTidakDikembalikan", "INTEGER", false, 0, null, 1));
                hashMap9.put("suratKembaliPPLN", new TableInfo.Column("suratKembaliPPLN", "INTEGER", false, 0, null, 1));
                hashMap9.put("suratTidakTerpakai", new TableInfo.Column("suratTidakTerpakai", "INTEGER", false, 0, null, 1));
                hashMap9.put("suratDigunakan", new TableInfo.Column("suratDigunakan", "INTEGER", true, 0, null, 1));
                hashMap9.put("pemilihDisabilitas_L", new TableInfo.Column("pemilihDisabilitas_L", "INTEGER", true, 0, null, 1));
                hashMap9.put("pemilihDisabilitas_P", new TableInfo.Column("pemilihDisabilitas_P", "INTEGER", true, 0, null, 1));
                hashMap9.put("totalPemilihDisabilitas", new TableInfo.Column("totalPemilihDisabilitas", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo9 = new TableInfo("form_c1_administration", hashMap9, new HashSet(0), new HashSet(0));
                TableInfo read9 = TableInfo.read(_db, "form_c1_administration");
                if (!tableInfo9.equals(read9)) {
                    return new RoomOpenHelper.ValidationResult(false, "form_c1_administration(org.informatika.sirekap.model.FormC1Administration).\n Expected:\n" + tableInfo9 + "\n Found:\n" + read9);
                }
                HashMap hashMap10 = new HashMap(7);
                hashMap10.put("idImage", new TableInfo.Column("idImage", "TEXT", true, 1, null, 1));
                hashMap10.put("isLn", new TableInfo.Column("isLn", "INTEGER", true, 0, null, 1));
                hashMap10.put("isLnPos", new TableInfo.Column("isLnPos", "INTEGER", true, 0, null, 1));
                hashMap10.put("formType", new TableInfo.Column("formType", "INTEGER", true, 0, null, 1));
                hashMap10.put("suratSuaraSah", new TableInfo.Column("suratSuaraSah", "INTEGER", false, 0, null, 1));
                hashMap10.put("suratSuaraTidakSah", new TableInfo.Column("suratSuaraTidakSah", "INTEGER", false, 0, null, 1));
                hashMap10.put("totalSuratSuara", new TableInfo.Column("totalSuratSuara", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo10 = new TableInfo("form_c1_administration_hal2", hashMap10, new HashSet(0), new HashSet(0));
                TableInfo read10 = TableInfo.read(_db, "form_c1_administration_hal2");
                if (!tableInfo10.equals(read10)) {
                    return new RoomOpenHelper.ValidationResult(false, "form_c1_administration_hal2(org.informatika.sirekap.model.FormC1AdministrationHal2).\n Expected:\n" + tableInfo10 + "\n Found:\n" + read10);
                }
                HashMap hashMap11 = new HashMap(3);
                hashMap11.put("idImage", new TableInfo.Column("idImage", "TEXT", true, 1, null, 1));
                hashMap11.put(FirebaseAnalytics.Param.INDEX, new TableInfo.Column(FirebaseAnalytics.Param.INDEX, "INTEGER", true, 2, null, 1));
                hashMap11.put("vote", new TableInfo.Column("vote", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo11 = new TableInfo("form_c1_tabulation_candidate_vote", hashMap11, new HashSet(0), new HashSet(0));
                TableInfo read11 = TableInfo.read(_db, "form_c1_tabulation_candidate_vote");
                if (!tableInfo11.equals(read11)) {
                    return new RoomOpenHelper.ValidationResult(false, "form_c1_tabulation_candidate_vote(org.informatika.sirekap.model.FormC1TabulationCandidateVote).\n Expected:\n" + tableInfo11 + "\n Found:\n" + read11);
                }
                HashMap hashMap12 = new HashMap(3);
                hashMap12.put("idImage", new TableInfo.Column("idImage", "TEXT", true, 1, null, 1));
                hashMap12.put("formType", new TableInfo.Column("formType", "INTEGER", true, 0, null, 1));
                hashMap12.put("suratSahPartaiDanCalon", new TableInfo.Column("suratSahPartaiDanCalon", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo12 = new TableInfo("form_c1_tabulation_partai", hashMap12, new HashSet(0), new HashSet(0));
                TableInfo read12 = TableInfo.read(_db, "form_c1_tabulation_partai");
                if (!tableInfo12.equals(read12)) {
                    return new RoomOpenHelper.ValidationResult(false, "form_c1_tabulation_partai(org.informatika.sirekap.model.FormC1TabulationPartai).\n Expected:\n" + tableInfo12 + "\n Found:\n" + read12);
                }
                HashMap hashMap13 = new HashMap(4);
                hashMap13.put("idImage", new TableInfo.Column("idImage", "TEXT", true, 1, null, 1));
                hashMap13.put("formType", new TableInfo.Column("formType", "INTEGER", true, 0, null, 1));
                hashMap13.put("errorType", new TableInfo.Column("errorType", "INTEGER", true, 0, null, 1));
                hashMap13.put("error", new TableInfo.Column("error", "TEXT", true, 0, null, 1));
                TableInfo tableInfo13 = new TableInfo("form_c1_error", hashMap13, new HashSet(0), new HashSet(0));
                TableInfo read13 = TableInfo.read(_db, "form_c1_error");
                if (!tableInfo13.equals(read13)) {
                    return new RoomOpenHelper.ValidationResult(false, "form_c1_error(org.informatika.sirekap.model.FormC1Error).\n Expected:\n" + tableInfo13 + "\n Found:\n" + read13);
                }
                HashMap hashMap14 = new HashMap(3);
                hashMap14.put("idImage", new TableInfo.Column("idImage", "TEXT", true, 1, null, 1));
                hashMap14.put("isSesuai", new TableInfo.Column("isSesuai", "INTEGER", true, 0, null, 1));
                hashMap14.put("komentar", new TableInfo.Column("komentar", "TEXT", false, 0, null, 1));
                TableInfo tableInfo14 = new TableInfo("form_c1_kesesuaian", hashMap14, new HashSet(0), new HashSet(0));
                TableInfo read14 = TableInfo.read(_db, "form_c1_kesesuaian");
                if (!tableInfo14.equals(read14)) {
                    return new RoomOpenHelper.ValidationResult(false, "form_c1_kesesuaian(org.informatika.sirekap.model.FormC1Kesesuaian).\n Expected:\n" + tableInfo14 + "\n Found:\n" + read14);
                }
                HashMap hashMap15 = new HashMap(53);
                hashMap15.put("idImage", new TableInfo.Column("idImage", "TEXT", true, 1, null, 1));
                hashMap15.put("isLnPos", new TableInfo.Column("isLnPos", "INTEGER", true, 0, null, 1));
                hashMap15.put("jenisPemilihan", new TableInfo.Column("jenisPemilihan", "TEXT", true, 0, null, 1));
                hashMap15.put("pemilihDpt_L", new TableInfo.Column("pemilihDpt_L", "INTEGER", false, 0, null, 1));
                hashMap15.put("pemilihDpt_P", new TableInfo.Column("pemilihDpt_P", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPemilihDpt", new TableInfo.Column("totalPemilihDpt", "INTEGER", false, 0, null, 1));
                hashMap15.put("penggunaDpt_L", new TableInfo.Column("penggunaDpt_L", "INTEGER", false, 0, null, 1));
                hashMap15.put("penggunaDpt_P", new TableInfo.Column("penggunaDpt_P", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPenggunaDpt", new TableInfo.Column("totalPenggunaDpt", "INTEGER", false, 0, null, 1));
                hashMap15.put("penggunaDptb_L", new TableInfo.Column("penggunaDptb_L", "INTEGER", false, 0, null, 1));
                hashMap15.put("penggunaDptb_P", new TableInfo.Column("penggunaDptb_P", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPenggunaDptb", new TableInfo.Column("totalPenggunaDptb", "INTEGER", false, 0, null, 1));
                hashMap15.put("penggunaDpk_L", new TableInfo.Column("penggunaDpk_L", "INTEGER", false, 0, null, 1));
                hashMap15.put("penggunaDpk_P", new TableInfo.Column("penggunaDpk_P", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPenggunaDpk", new TableInfo.Column("totalPenggunaDpk", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPengguna_L", new TableInfo.Column("totalPengguna_L", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPengguna_P", new TableInfo.Column("totalPengguna_P", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPengguna", new TableInfo.Column("totalPengguna", "INTEGER", false, 0, null, 1));
                hashMap15.put("suratDiterima", new TableInfo.Column("suratDiterima", "INTEGER", false, 0, null, 1));
                hashMap15.put("suratDikembalikan", new TableInfo.Column("suratDikembalikan", "INTEGER", false, 0, null, 1));
                hashMap15.put("suratTidakDigunakan", new TableInfo.Column("suratTidakDigunakan", "INTEGER", false, 0, null, 1));
                hashMap15.put("suratTidakDikembalikan", new TableInfo.Column("suratTidakDikembalikan", "INTEGER", false, 0, null, 1));
                hashMap15.put("suratKembaliPPLN", new TableInfo.Column("suratKembaliPPLN", "INTEGER", false, 0, null, 1));
                hashMap15.put("suratTidakTerpakai", new TableInfo.Column("suratTidakTerpakai", "INTEGER", false, 0, null, 1));
                hashMap15.put("suratDigunakan", new TableInfo.Column("suratDigunakan", "INTEGER", false, 0, null, 1));
                hashMap15.put("pemilihDisabilitas_L", new TableInfo.Column("pemilihDisabilitas_L", "INTEGER", false, 0, null, 1));
                hashMap15.put("pemilihDisabilitas_P", new TableInfo.Column("pemilihDisabilitas_P", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPemilihDisabilitas", new TableInfo.Column("totalPemilihDisabilitas", "INTEGER", false, 0, null, 1));
                hashMap15.put("pemilihDpt_LCorrected", new TableInfo.Column("pemilihDpt_LCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("pemilihDpt_PCorrected", new TableInfo.Column("pemilihDpt_PCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPemilihDptCorrected", new TableInfo.Column("totalPemilihDptCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("penggunaDpt_LCorrected", new TableInfo.Column("penggunaDpt_LCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("penggunaDpt_PCorrected", new TableInfo.Column("penggunaDpt_PCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPenggunaDptCorrected", new TableInfo.Column("totalPenggunaDptCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("penggunaDptb_LCorrected", new TableInfo.Column("penggunaDptb_LCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("penggunaDptb_PCorrected", new TableInfo.Column("penggunaDptb_PCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPenggunaDptbCorrected", new TableInfo.Column("totalPenggunaDptbCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("penggunaDpk_LCorrected", new TableInfo.Column("penggunaDpk_LCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("penggunaDpk_PCorrected", new TableInfo.Column("penggunaDpk_PCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPenggunaDpkCorrected", new TableInfo.Column("totalPenggunaDpkCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPengguna_LCorrected", new TableInfo.Column("totalPengguna_LCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPengguna_PCorrected", new TableInfo.Column("totalPengguna_PCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPenggunaCorrected", new TableInfo.Column("totalPenggunaCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("suratDiterimaCorrected", new TableInfo.Column("suratDiterimaCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("suratDikembalikanCorrected", new TableInfo.Column("suratDikembalikanCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("suratTidakDigunakanCorrected", new TableInfo.Column("suratTidakDigunakanCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("suratTidakDikembalikanCorrected", new TableInfo.Column("suratTidakDikembalikanCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("suratKembaliPPLNCorrected", new TableInfo.Column("suratKembaliPPLNCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("suratTidakTerpakaiCorrected", new TableInfo.Column("suratTidakTerpakaiCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("suratDigunakanCorrected", new TableInfo.Column("suratDigunakanCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("pemilihDisabilitas_LCorrected", new TableInfo.Column("pemilihDisabilitas_LCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("pemilihDisabilitas_PCorrected", new TableInfo.Column("pemilihDisabilitas_PCorrected", "INTEGER", false, 0, null, 1));
                hashMap15.put("totalPemilihDisabilitasCorrected", new TableInfo.Column("totalPemilihDisabilitasCorrected", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo15 = new TableInfo("form_c1_kesesuaian_administration", hashMap15, new HashSet(0), new HashSet(0));
                TableInfo read15 = TableInfo.read(_db, "form_c1_kesesuaian_administration");
                if (!tableInfo15.equals(read15)) {
                    return new RoomOpenHelper.ValidationResult(false, "form_c1_kesesuaian_administration(org.informatika.sirekap.model.FormC1KesesuaianAdministration).\n Expected:\n" + tableInfo15 + "\n Found:\n" + read15);
                }
                HashMap hashMap16 = new HashMap(7);
                hashMap16.put("idImage", new TableInfo.Column("idImage", "TEXT", true, 1, null, 1));
                hashMap16.put("suratSuaraSah", new TableInfo.Column("suratSuaraSah", "INTEGER", false, 0, null, 1));
                hashMap16.put("suratSuaraTidakSah", new TableInfo.Column("suratSuaraTidakSah", "INTEGER", false, 0, null, 1));
                hashMap16.put("totalSuratSuara", new TableInfo.Column("totalSuratSuara", "INTEGER", false, 0, null, 1));
                hashMap16.put("suratSuaraSahCorrected", new TableInfo.Column("suratSuaraSahCorrected", "INTEGER", false, 0, null, 1));
                hashMap16.put("suratSuaraTidakSahCorrected", new TableInfo.Column("suratSuaraTidakSahCorrected", "INTEGER", false, 0, null, 1));
                hashMap16.put("totalSuratSuaraCorrected", new TableInfo.Column("totalSuratSuaraCorrected", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo16 = new TableInfo("form_c1_kesesuaian_administration_hal2", hashMap16, new HashSet(0), new HashSet(0));
                TableInfo read16 = TableInfo.read(_db, "form_c1_kesesuaian_administration_hal2");
                if (!tableInfo16.equals(read16)) {
                    return new RoomOpenHelper.ValidationResult(false, "form_c1_kesesuaian_administration_hal2(org.informatika.sirekap.model.FormC1KesesuaianAdministrationHal2).\n Expected:\n" + tableInfo16 + "\n Found:\n" + read16);
                }
                HashMap hashMap17 = new HashMap(4);
                hashMap17.put("idImage", new TableInfo.Column("idImage", "TEXT", true, 1, null, 1));
                hashMap17.put(FirebaseAnalytics.Param.INDEX, new TableInfo.Column(FirebaseAnalytics.Param.INDEX, "INTEGER", true, 2, null, 1));
                hashMap17.put("vote", new TableInfo.Column("vote", "INTEGER", false, 0, null, 1));
                hashMap17.put("voteCorrected", new TableInfo.Column("voteCorrected", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo17 = new TableInfo("form_c1_kesesuaian_tabulation_candidate_vote", hashMap17, new HashSet(0), new HashSet(0));
                TableInfo read17 = TableInfo.read(_db, "form_c1_kesesuaian_tabulation_candidate_vote");
                if (!tableInfo17.equals(read17)) {
                    return new RoomOpenHelper.ValidationResult(false, "form_c1_kesesuaian_tabulation_candidate_vote(org.informatika.sirekap.model.FormC1KesesuaianTabulationCandidateVote).\n Expected:\n" + tableInfo17 + "\n Found:\n" + read17);
                }
                HashMap hashMap18 = new HashMap(3);
                hashMap18.put("idImage", new TableInfo.Column("idImage", "TEXT", true, 1, null, 1));
                hashMap18.put("suratSahPartaiDanCalon", new TableInfo.Column("suratSahPartaiDanCalon", "INTEGER", false, 0, null, 1));
                hashMap18.put("suratSahPartaiDanCalonCorrected", new TableInfo.Column("suratSahPartaiDanCalonCorrected", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo18 = new TableInfo("form_c1_kesesuaian_tabulation_partai", hashMap18, new HashSet(0), new HashSet(0));
                TableInfo read18 = TableInfo.read(_db, "form_c1_kesesuaian_tabulation_partai");
                if (!tableInfo18.equals(read18)) {
                    return new RoomOpenHelper.ValidationResult(false, "form_c1_kesesuaian_tabulation_partai(org.informatika.sirekap.model.FormC1KesesuaianTabulationPartai).\n Expected:\n" + tableInfo18 + "\n Found:\n" + read18);
                }
                HashMap hashMap19 = new HashMap(4);
                hashMap19.put("electionPageId", new TableInfo.Column("electionPageId", "TEXT", true, 1, null, 1));
                hashMap19.put("jenisImage", new TableInfo.Column("jenisImage", "INTEGER", true, 2, null, 1));
                hashMap19.put("attempt", new TableInfo.Column("attempt", "INTEGER", true, 0, null, 1));
                hashMap19.put("isSuccess", new TableInfo.Column("isSuccess", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo19 = new TableInfo("upload_image_attempts", hashMap19, new HashSet(0), new HashSet(0));
                TableInfo read19 = TableInfo.read(_db, "upload_image_attempts");
                if (!tableInfo19.equals(read19)) {
                    return new RoomOpenHelper.ValidationResult(false, "upload_image_attempts(org.informatika.sirekap.model.UploadImageAttempt).\n Expected:\n" + tableInfo19 + "\n Found:\n" + read19);
                }
                HashMap hashMap20 = new HashMap(4);
                hashMap20.put(JobType.ID, new TableInfo.Column(JobType.ID, "TEXT", true, 1, null, 1));
                hashMap20.put("type", new TableInfo.Column("type", "TEXT", true, 2, null, 1));
                hashMap20.put("attempt", new TableInfo.Column("attempt", "INTEGER", true, 0, null, 1));
                hashMap20.put("isSuccess", new TableInfo.Column("isSuccess", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo20 = new TableInfo("upload_file_attempts", hashMap20, new HashSet(0), new HashSet(0));
                TableInfo read20 = TableInfo.read(_db, "upload_file_attempts");
                if (!tableInfo20.equals(read20)) {
                    return new RoomOpenHelper.ValidationResult(false, "upload_file_attempts(org.informatika.sirekap.model.UploadFileAttempt).\n Expected:\n" + tableInfo20 + "\n Found:\n" + read20);
                }
                HashMap hashMap21 = new HashMap(10);
                hashMap21.put("kodeTps", new TableInfo.Column("kodeTps", "TEXT", true, 1, null, 1));
                hashMap21.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, 1));
                hashMap21.put("kelurahan_id", new TableInfo.Column("kelurahan_id", "TEXT", false, 0, null, 1));
                hashMap21.put("kelurahan_name", new TableInfo.Column("kelurahan_name", "TEXT", false, 0, null, 1));
                hashMap21.put("kelurahan_kecamatan_id", new TableInfo.Column("kelurahan_kecamatan_id", "TEXT", false, 0, null, 1));
                hashMap21.put("kelurahan_kecamatan_name", new TableInfo.Column("kelurahan_kecamatan_name", "TEXT", false, 0, null, 1));
                hashMap21.put("kelurahan_kecamatan_kotaKabupaten_id", new TableInfo.Column("kelurahan_kecamatan_kotaKabupaten_id", "TEXT", false, 0, null, 1));
                hashMap21.put("kelurahan_kecamatan_kotaKabupaten_name", new TableInfo.Column("kelurahan_kecamatan_kotaKabupaten_name", "TEXT", false, 0, null, 1));
                hashMap21.put("kelurahan_kecamatan_kotaKabupaten_provinsi_id", new TableInfo.Column("kelurahan_kecamatan_kotaKabupaten_provinsi_id", "TEXT", false, 0, null, 1));
                hashMap21.put("kelurahan_kecamatan_kotaKabupaten_provinsi_name", new TableInfo.Column("kelurahan_kecamatan_kotaKabupaten_provinsi_name", "TEXT", false, 0, null, 1));
                TableInfo tableInfo21 = new TableInfo("tps", hashMap21, new HashSet(0), new HashSet(0));
                TableInfo read21 = TableInfo.read(_db, "tps");
                if (!tableInfo21.equals(read21)) {
                    return new RoomOpenHelper.ValidationResult(false, "tps(org.informatika.sirekap.model.Tps).\n Expected:\n" + tableInfo21 + "\n Found:\n" + read21);
                }
                HashMap hashMap22 = new HashMap(10);
                hashMap22.put("kodeTps", new TableInfo.Column("kodeTps", "TEXT", true, 1, null, 1));
                hashMap22.put("jenisWaktu", new TableInfo.Column("jenisWaktu", "INTEGER", true, 2, null, 1));
                hashMap22.put("jenisPemilihan", new TableInfo.Column("jenisPemilihan", "TEXT", true, 3, null, 1));
                hashMap22.put("startDate", new TableInfo.Column("startDate", "INTEGER", true, 0, null, 1));
                hashMap22.put("startTimeHour", new TableInfo.Column("startTimeHour", "INTEGER", true, 0, null, 1));
                hashMap22.put("startTimeMinute", new TableInfo.Column("startTimeMinute", "INTEGER", true, 0, null, 1));
                hashMap22.put("endDate", new TableInfo.Column("endDate", "INTEGER", true, 0, null, 1));
                hashMap22.put("endTimeHour", new TableInfo.Column("endTimeHour", "INTEGER", true, 0, null, 1));
                hashMap22.put("endTimeMinute", new TableInfo.Column("endTimeMinute", "INTEGER", true, 0, null, 1));
                hashMap22.put("isSubmittedOffline", new TableInfo.Column("isSubmittedOffline", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo22 = new TableInfo("tps_time", hashMap22, new HashSet(0), new HashSet(0));
                TableInfo read22 = TableInfo.read(_db, "tps_time");
                if (!tableInfo22.equals(read22)) {
                    return new RoomOpenHelper.ValidationResult(false, "tps_time(org.informatika.sirekap.model.TpsTime).\n Expected:\n" + tableInfo22 + "\n Found:\n" + read22);
                }
                HashMap hashMap23 = new HashMap(7);
                hashMap23.put(JobType.ID, new TableInfo.Column(JobType.ID, "INTEGER", true, 1, null, 1));
                hashMap23.put("kodeTps", new TableInfo.Column("kodeTps", "TEXT", true, 0, null, 1));
                hashMap23.put("photoPath", new TableInfo.Column("photoPath", "TEXT", true, 0, null, 1));
                hashMap23.put("croppedPhotoPath", new TableInfo.Column("croppedPhotoPath", "TEXT", true, 0, null, 1));
                hashMap23.put("urutan", new TableInfo.Column("urutan", "INTEGER", true, 0, null, 1));
                hashMap23.put("hashDocumentCropped", new TableInfo.Column("hashDocumentCropped", "TEXT", true, 0, null, 1));
                hashMap23.put("checked", new TableInfo.Column("checked", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo23 = new TableInfo("attendance_page", hashMap23, new HashSet(0), new HashSet(0));
                TableInfo read23 = TableInfo.read(_db, "attendance_page");
                if (!tableInfo23.equals(read23)) {
                    return new RoomOpenHelper.ValidationResult(false, "attendance_page(org.informatika.sirekap.model.AttendancePage).\n Expected:\n" + tableInfo23 + "\n Found:\n" + read23);
                }
                HashMap hashMap24 = new HashMap(7);
                hashMap24.put("kodeTps", new TableInfo.Column("kodeTps", "TEXT", true, 1, null, 1));
                hashMap24.put("isPdf", new TableInfo.Column("isPdf", "INTEGER", true, 0, null, 1));
                hashMap24.put("uploadPdfStatus", new TableInfo.Column("uploadPdfStatus", "INTEGER", true, 0, null, 1));
                hashMap24.put("isUploadedPdf", new TableInfo.Column("isUploadedPdf", "INTEGER", true, 0, null, 1));
                hashMap24.put("isUploadedPdfOffline", new TableInfo.Column("isUploadedPdfOffline", "INTEGER", true, 0, null, 1));
                hashMap24.put("pdfFilePath", new TableInfo.Column("pdfFilePath", "TEXT", false, 0, null, 1));
                hashMap24.put("pdfFileHash", new TableInfo.Column("pdfFileHash", "TEXT", false, 0, null, 1));
                TableInfo tableInfo24 = new TableInfo("attendance", hashMap24, new HashSet(0), new HashSet(0));
                TableInfo read24 = TableInfo.read(_db, "attendance");
                if (!tableInfo24.equals(read24)) {
                    return new RoomOpenHelper.ValidationResult(false, "attendance(org.informatika.sirekap.model.Attendance).\n Expected:\n" + tableInfo24 + "\n Found:\n" + read24);
                }
                HashMap hashMap25 = new HashMap(7);
                hashMap25.put("kodeTps", new TableInfo.Column("kodeTps", "TEXT", true, 1, null, 1));
                hashMap25.put("isPdf", new TableInfo.Column("isPdf", "INTEGER", true, 0, null, 1));
                hashMap25.put("uploadPdfStatus", new TableInfo.Column("uploadPdfStatus", "INTEGER", true, 0, null, 1));
                hashMap25.put("isUploadedPdf", new TableInfo.Column("isUploadedPdf", "INTEGER", true, 0, null, 1));
                hashMap25.put("isUploadedPdfOffline", new TableInfo.Column("isUploadedPdfOffline", "INTEGER", true, 0, null, 1));
                hashMap25.put("pdfFilePath", new TableInfo.Column("pdfFilePath", "TEXT", false, 0, null, 1));
                hashMap25.put("pdfFileHash", new TableInfo.Column("pdfFileHash", "TEXT", false, 0, null, 1));
                TableInfo tableInfo25 = new TableInfo("special_occurrence", hashMap25, new HashSet(0), new HashSet(0));
                TableInfo read25 = TableInfo.read(_db, "special_occurrence");
                if (!tableInfo25.equals(read25)) {
                    return new RoomOpenHelper.ValidationResult(false, "special_occurrence(org.informatika.sirekap.model.SpecialOccurrence).\n Expected:\n" + tableInfo25 + "\n Found:\n" + read25);
                }
                HashMap hashMap26 = new HashMap(6);
                hashMap26.put(JobType.ID, new TableInfo.Column(JobType.ID, "INTEGER", true, 1, null, 1));
                hashMap26.put("kodeTps", new TableInfo.Column("kodeTps", "TEXT", true, 0, null, 1));
                hashMap26.put("photoPath", new TableInfo.Column("photoPath", "TEXT", true, 0, null, 1));
                hashMap26.put("croppedPhotoPath", new TableInfo.Column("croppedPhotoPath", "TEXT", true, 0, null, 1));
                hashMap26.put("hashDocumentCropped", new TableInfo.Column("hashDocumentCropped", "TEXT", true, 0, null, 1));
                hashMap26.put("checked", new TableInfo.Column("checked", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo26 = new TableInfo("special_occurrence_page", hashMap26, new HashSet(0), new HashSet(0));
                TableInfo read26 = TableInfo.read(_db, "special_occurrence_page");
                if (!tableInfo26.equals(read26)) {
                    return new RoomOpenHelper.ValidationResult(false, "special_occurrence_page(org.informatika.sirekap.model.SpecialOccurrencePage).\n Expected:\n" + tableInfo26 + "\n Found:\n" + read26);
                }
                HashMap hashMap27 = new HashMap(5);
                hashMap27.put(JobType.ID, new TableInfo.Column(JobType.ID, "TEXT", true, 1, null, 1));
                hashMap27.put("startedAt", new TableInfo.Column("startedAt", "INTEGER", true, 0, null, 1));
                hashMap27.put("endedAt", new TableInfo.Column("endedAt", "INTEGER", false, 0, null, 1));
                hashMap27.put("isSuccess", new TableInfo.Column("isSuccess", "INTEGER", false, 0, null, 1));
                hashMap27.put("errorMessage", new TableInfo.Column("errorMessage", "TEXT", false, 0, null, 1));
                TableInfo tableInfo27 = new TableInfo("background_processses", hashMap27, new HashSet(0), new HashSet(0));
                TableInfo read27 = TableInfo.read(_db, "background_processses");
                if (!tableInfo27.equals(read27)) {
                    return new RoomOpenHelper.ValidationResult(false, "background_processses(org.informatika.sirekap.model.BackgroundProcess).\n Expected:\n" + tableInfo27 + "\n Found:\n" + read27);
                }
                HashMap hashMap28 = new HashMap(2);
                hashMap28.put("key", new TableInfo.Column("key", "TEXT", true, 1, null, 1));
                hashMap28.put("value", new TableInfo.Column("value", "TEXT", true, 0, null, 1));
                TableInfo tableInfo28 = new TableInfo("security_properties", hashMap28, new HashSet(0), new HashSet(0));
                TableInfo read28 = TableInfo.read(_db, "security_properties");
                if (!tableInfo28.equals(read28)) {
                    return new RoomOpenHelper.ValidationResult(false, "security_properties(org.informatika.sirekap.model.SecurityProperties).\n Expected:\n" + tableInfo28 + "\n Found:\n" + read28);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "75090633c7ee6c7f5603648761b16ee1", "0e8e423444c25498a7526ce9df779917")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "election", "witnesses", "witness_shares", "witness_pemeriksas", "election_page", "election_page_stage", "candidates", "users", "form_c1_administration", "form_c1_administration_hal2", "form_c1_tabulation_candidate_vote", "form_c1_tabulation_partai", "form_c1_error", "form_c1_kesesuaian", "form_c1_kesesuaian_administration", "form_c1_kesesuaian_administration_hal2", "form_c1_kesesuaian_tabulation_candidate_vote", "form_c1_kesesuaian_tabulation_partai", "upload_image_attempts", "upload_file_attempts", "tps", "tps_time", "attendance_page", "attendance", "special_occurrence", "special_occurrence_page", "background_processses", "security_properties");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `election`");
            writableDatabase.execSQL("DELETE FROM `witnesses`");
            writableDatabase.execSQL("DELETE FROM `witness_shares`");
            writableDatabase.execSQL("DELETE FROM `witness_pemeriksas`");
            writableDatabase.execSQL("DELETE FROM `election_page`");
            writableDatabase.execSQL("DELETE FROM `election_page_stage`");
            writableDatabase.execSQL("DELETE FROM `candidates`");
            writableDatabase.execSQL("DELETE FROM `users`");
            writableDatabase.execSQL("DELETE FROM `form_c1_administration`");
            writableDatabase.execSQL("DELETE FROM `form_c1_administration_hal2`");
            writableDatabase.execSQL("DELETE FROM `form_c1_tabulation_candidate_vote`");
            writableDatabase.execSQL("DELETE FROM `form_c1_tabulation_partai`");
            writableDatabase.execSQL("DELETE FROM `form_c1_error`");
            writableDatabase.execSQL("DELETE FROM `form_c1_kesesuaian`");
            writableDatabase.execSQL("DELETE FROM `form_c1_kesesuaian_administration`");
            writableDatabase.execSQL("DELETE FROM `form_c1_kesesuaian_administration_hal2`");
            writableDatabase.execSQL("DELETE FROM `form_c1_kesesuaian_tabulation_candidate_vote`");
            writableDatabase.execSQL("DELETE FROM `form_c1_kesesuaian_tabulation_partai`");
            writableDatabase.execSQL("DELETE FROM `upload_image_attempts`");
            writableDatabase.execSQL("DELETE FROM `upload_file_attempts`");
            writableDatabase.execSQL("DELETE FROM `tps`");
            writableDatabase.execSQL("DELETE FROM `tps_time`");
            writableDatabase.execSQL("DELETE FROM `attendance_page`");
            writableDatabase.execSQL("DELETE FROM `attendance`");
            writableDatabase.execSQL("DELETE FROM `special_occurrence`");
            writableDatabase.execSQL("DELETE FROM `special_occurrence_page`");
            writableDatabase.execSQL("DELETE FROM `background_processses`");
            writableDatabase.execSQL("DELETE FROM `security_properties`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(WitnessDao.class, WitnessDao_Impl.getRequiredConverters());
        hashMap.put(CandidateDao.class, CandidateDao_Impl.getRequiredConverters());
        hashMap.put(ElectionDao.class, ElectionDao_Impl.getRequiredConverters());
        hashMap.put(ElectionPageDao.class, ElectionPageDao_Impl.getRequiredConverters());
        hashMap.put(ElectionPageStageDao.class, ElectionPageStageDao_Impl.getRequiredConverters());
        hashMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
        hashMap.put(FormC1AdministrationDao.class, FormC1AdministrationDao_Impl.getRequiredConverters());
        hashMap.put(FormC1KesesuaianAdministrasiDao.class, FormC1KesesuaianAdministrasiDao_Impl.getRequiredConverters());
        hashMap.put(FormC1ErrorDao.class, FormC1ErrorDao_Impl.getRequiredConverters());
        hashMap.put(FormC1KesesuaianDao.class, FormC1KesesuaianDao_Impl.getRequiredConverters());
        hashMap.put(FormC1AdministrationHal2Dao.class, FormC1AdministrationHal2Dao_Impl.getRequiredConverters());
        hashMap.put(FormC1TabulationDao.class, FormC1TabulationDao_Impl.getRequiredConverters());
        hashMap.put(FormC1TabulationCandidateVoteDao.class, FormC1TabulationCandidateVoteDao_Impl.getRequiredConverters());
        hashMap.put(FormC1KesesuaianAdministrationHal2Dao.class, FormC1KesesuaianAdministrationHal2Dao_Impl.getRequiredConverters());
        hashMap.put(FormC1KesesuaianTabulationCandidateVoteDao.class, FormC1KesesuaianTabulationCandidateVoteDao_Impl.getRequiredConverters());
        hashMap.put(FormC1KesesuaianTabulationPartaiDao.class, FormC1KesesuaianTabulationPartaiDao_Impl.getRequiredConverters());
        hashMap.put(UploadImageAttemptDao.class, UploadImageAttemptDao_Impl.getRequiredConverters());
        hashMap.put(UploadFileAttemptDao.class, UploadFileAttemptDao_Impl.getRequiredConverters());
        hashMap.put(TpsDao.class, TpsDao_Impl.getRequiredConverters());
        hashMap.put(TpsTimeDao.class, TpsTimeDao_Impl.getRequiredConverters());
        hashMap.put(AttendancePageDao.class, AttendancePageDao_Impl.getRequiredConverters());
        hashMap.put(AttendanceDao.class, AttendanceDao_Impl.getRequiredConverters());
        hashMap.put(SpecialOccurrencePageDao.class, SpecialOccurrencePageDao_Impl.getRequiredConverters());
        hashMap.put(SpecialOccurrenceDao.class, SpecialOccurrenceDao_Impl.getRequiredConverters());
        hashMap.put(BackgroundProcessDao.class, BackgroundProcessDao_Impl.getRequiredConverters());
        hashMap.put(SecurityDao.class, SecurityDao_Impl.getRequiredConverters());
        return hashMap;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public WitnessDao witnessDao() {
        WitnessDao witnessDao;
        if (this._witnessDao != null) {
            return this._witnessDao;
        }
        synchronized (this) {
            if (this._witnessDao == null) {
                this._witnessDao = new WitnessDao_Impl(this);
            }
            witnessDao = this._witnessDao;
        }
        return witnessDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public CandidateDao candidateDao() {
        CandidateDao candidateDao;
        if (this._candidateDao != null) {
            return this._candidateDao;
        }
        synchronized (this) {
            if (this._candidateDao == null) {
                this._candidateDao = new CandidateDao_Impl(this);
            }
            candidateDao = this._candidateDao;
        }
        return candidateDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public ElectionDao electionDao() {
        ElectionDao electionDao;
        if (this._electionDao != null) {
            return this._electionDao;
        }
        synchronized (this) {
            if (this._electionDao == null) {
                this._electionDao = new ElectionDao_Impl(this);
            }
            electionDao = this._electionDao;
        }
        return electionDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public ElectionPageDao electionPageDao() {
        ElectionPageDao electionPageDao;
        if (this._electionPageDao != null) {
            return this._electionPageDao;
        }
        synchronized (this) {
            if (this._electionPageDao == null) {
                this._electionPageDao = new ElectionPageDao_Impl(this);
            }
            electionPageDao = this._electionPageDao;
        }
        return electionPageDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public ElectionPageStageDao electionPageStageDao() {
        ElectionPageStageDao electionPageStageDao;
        if (this._electionPageStageDao != null) {
            return this._electionPageStageDao;
        }
        synchronized (this) {
            if (this._electionPageStageDao == null) {
                this._electionPageStageDao = new ElectionPageStageDao_Impl(this);
            }
            electionPageStageDao = this._electionPageStageDao;
        }
        return electionPageStageDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public UserDao userDao() {
        UserDao userDao;
        if (this._userDao != null) {
            return this._userDao;
        }
        synchronized (this) {
            if (this._userDao == null) {
                this._userDao = new UserDao_Impl(this);
            }
            userDao = this._userDao;
        }
        return userDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public FormC1AdministrationDao formC1AdministrationDao() {
        FormC1AdministrationDao formC1AdministrationDao;
        if (this._formC1AdministrationDao != null) {
            return this._formC1AdministrationDao;
        }
        synchronized (this) {
            if (this._formC1AdministrationDao == null) {
                this._formC1AdministrationDao = new FormC1AdministrationDao_Impl(this);
            }
            formC1AdministrationDao = this._formC1AdministrationDao;
        }
        return formC1AdministrationDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public FormC1KesesuaianAdministrasiDao formC1KesesuaianAdministrasiDao() {
        FormC1KesesuaianAdministrasiDao formC1KesesuaianAdministrasiDao;
        if (this._formC1KesesuaianAdministrasiDao != null) {
            return this._formC1KesesuaianAdministrasiDao;
        }
        synchronized (this) {
            if (this._formC1KesesuaianAdministrasiDao == null) {
                this._formC1KesesuaianAdministrasiDao = new FormC1KesesuaianAdministrasiDao_Impl(this);
            }
            formC1KesesuaianAdministrasiDao = this._formC1KesesuaianAdministrasiDao;
        }
        return formC1KesesuaianAdministrasiDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public FormC1ErrorDao formC1ErrorDao() {
        FormC1ErrorDao formC1ErrorDao;
        if (this._formC1ErrorDao != null) {
            return this._formC1ErrorDao;
        }
        synchronized (this) {
            if (this._formC1ErrorDao == null) {
                this._formC1ErrorDao = new FormC1ErrorDao_Impl(this);
            }
            formC1ErrorDao = this._formC1ErrorDao;
        }
        return formC1ErrorDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public FormC1KesesuaianDao formC1KesesuaianDao() {
        FormC1KesesuaianDao formC1KesesuaianDao;
        if (this._formC1KesesuaianDao != null) {
            return this._formC1KesesuaianDao;
        }
        synchronized (this) {
            if (this._formC1KesesuaianDao == null) {
                this._formC1KesesuaianDao = new FormC1KesesuaianDao_Impl(this);
            }
            formC1KesesuaianDao = this._formC1KesesuaianDao;
        }
        return formC1KesesuaianDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public FormC1AdministrationHal2Dao formC1AdministrationHal2Dao() {
        FormC1AdministrationHal2Dao formC1AdministrationHal2Dao;
        if (this._formC1AdministrationHal2Dao != null) {
            return this._formC1AdministrationHal2Dao;
        }
        synchronized (this) {
            if (this._formC1AdministrationHal2Dao == null) {
                this._formC1AdministrationHal2Dao = new FormC1AdministrationHal2Dao_Impl(this);
            }
            formC1AdministrationHal2Dao = this._formC1AdministrationHal2Dao;
        }
        return formC1AdministrationHal2Dao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public FormC1TabulationDao formC1TabulationDao() {
        FormC1TabulationDao formC1TabulationDao;
        if (this._formC1TabulationDao != null) {
            return this._formC1TabulationDao;
        }
        synchronized (this) {
            if (this._formC1TabulationDao == null) {
                this._formC1TabulationDao = new FormC1TabulationDao_Impl(this);
            }
            formC1TabulationDao = this._formC1TabulationDao;
        }
        return formC1TabulationDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public FormC1TabulationCandidateVoteDao formC1TabulationCandidateVoteDao() {
        FormC1TabulationCandidateVoteDao formC1TabulationCandidateVoteDao;
        if (this._formC1TabulationCandidateVoteDao != null) {
            return this._formC1TabulationCandidateVoteDao;
        }
        synchronized (this) {
            if (this._formC1TabulationCandidateVoteDao == null) {
                this._formC1TabulationCandidateVoteDao = new FormC1TabulationCandidateVoteDao_Impl(this);
            }
            formC1TabulationCandidateVoteDao = this._formC1TabulationCandidateVoteDao;
        }
        return formC1TabulationCandidateVoteDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public FormC1KesesuaianAdministrationHal2Dao formC1KesesuaianAdministrationHal2Dao() {
        FormC1KesesuaianAdministrationHal2Dao formC1KesesuaianAdministrationHal2Dao;
        if (this._formC1KesesuaianAdministrationHal2Dao != null) {
            return this._formC1KesesuaianAdministrationHal2Dao;
        }
        synchronized (this) {
            if (this._formC1KesesuaianAdministrationHal2Dao == null) {
                this._formC1KesesuaianAdministrationHal2Dao = new FormC1KesesuaianAdministrationHal2Dao_Impl(this);
            }
            formC1KesesuaianAdministrationHal2Dao = this._formC1KesesuaianAdministrationHal2Dao;
        }
        return formC1KesesuaianAdministrationHal2Dao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public FormC1KesesuaianTabulationCandidateVoteDao formC1KesesuaianTabulationCandidateVoteDao() {
        FormC1KesesuaianTabulationCandidateVoteDao formC1KesesuaianTabulationCandidateVoteDao;
        if (this._formC1KesesuaianTabulationCandidateVoteDao != null) {
            return this._formC1KesesuaianTabulationCandidateVoteDao;
        }
        synchronized (this) {
            if (this._formC1KesesuaianTabulationCandidateVoteDao == null) {
                this._formC1KesesuaianTabulationCandidateVoteDao = new FormC1KesesuaianTabulationCandidateVoteDao_Impl(this);
            }
            formC1KesesuaianTabulationCandidateVoteDao = this._formC1KesesuaianTabulationCandidateVoteDao;
        }
        return formC1KesesuaianTabulationCandidateVoteDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public FormC1KesesuaianTabulationPartaiDao formC1KesesuaianTabulationPartaiDao() {
        FormC1KesesuaianTabulationPartaiDao formC1KesesuaianTabulationPartaiDao;
        if (this._formC1KesesuaianTabulationPartaiDao != null) {
            return this._formC1KesesuaianTabulationPartaiDao;
        }
        synchronized (this) {
            if (this._formC1KesesuaianTabulationPartaiDao == null) {
                this._formC1KesesuaianTabulationPartaiDao = new FormC1KesesuaianTabulationPartaiDao_Impl(this);
            }
            formC1KesesuaianTabulationPartaiDao = this._formC1KesesuaianTabulationPartaiDao;
        }
        return formC1KesesuaianTabulationPartaiDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public UploadImageAttemptDao uploadImageAttemptDao() {
        UploadImageAttemptDao uploadImageAttemptDao;
        if (this._uploadImageAttemptDao != null) {
            return this._uploadImageAttemptDao;
        }
        synchronized (this) {
            if (this._uploadImageAttemptDao == null) {
                this._uploadImageAttemptDao = new UploadImageAttemptDao_Impl(this);
            }
            uploadImageAttemptDao = this._uploadImageAttemptDao;
        }
        return uploadImageAttemptDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public UploadFileAttemptDao uploadFileAttemptDao() {
        UploadFileAttemptDao uploadFileAttemptDao;
        if (this._uploadFileAttemptDao != null) {
            return this._uploadFileAttemptDao;
        }
        synchronized (this) {
            if (this._uploadFileAttemptDao == null) {
                this._uploadFileAttemptDao = new UploadFileAttemptDao_Impl(this);
            }
            uploadFileAttemptDao = this._uploadFileAttemptDao;
        }
        return uploadFileAttemptDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public TpsDao tpsDao() {
        TpsDao tpsDao;
        if (this._tpsDao != null) {
            return this._tpsDao;
        }
        synchronized (this) {
            if (this._tpsDao == null) {
                this._tpsDao = new TpsDao_Impl(this);
            }
            tpsDao = this._tpsDao;
        }
        return tpsDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public TpsTimeDao tpsTimeDao() {
        TpsTimeDao tpsTimeDao;
        if (this._tpsTimeDao != null) {
            return this._tpsTimeDao;
        }
        synchronized (this) {
            if (this._tpsTimeDao == null) {
                this._tpsTimeDao = new TpsTimeDao_Impl(this);
            }
            tpsTimeDao = this._tpsTimeDao;
        }
        return tpsTimeDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public AttendancePageDao attendancePageDao() {
        AttendancePageDao attendancePageDao;
        if (this._attendancePageDao != null) {
            return this._attendancePageDao;
        }
        synchronized (this) {
            if (this._attendancePageDao == null) {
                this._attendancePageDao = new AttendancePageDao_Impl(this);
            }
            attendancePageDao = this._attendancePageDao;
        }
        return attendancePageDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public AttendanceDao attendanceDao() {
        AttendanceDao attendanceDao;
        if (this._attendanceDao != null) {
            return this._attendanceDao;
        }
        synchronized (this) {
            if (this._attendanceDao == null) {
                this._attendanceDao = new AttendanceDao_Impl(this);
            }
            attendanceDao = this._attendanceDao;
        }
        return attendanceDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public SpecialOccurrencePageDao specialOccurrencePageDao() {
        SpecialOccurrencePageDao specialOccurrencePageDao;
        if (this._specialOccurrencePageDao != null) {
            return this._specialOccurrencePageDao;
        }
        synchronized (this) {
            if (this._specialOccurrencePageDao == null) {
                this._specialOccurrencePageDao = new SpecialOccurrencePageDao_Impl(this);
            }
            specialOccurrencePageDao = this._specialOccurrencePageDao;
        }
        return specialOccurrencePageDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public SpecialOccurrenceDao specialOccurrenceDao() {
        SpecialOccurrenceDao specialOccurrenceDao;
        if (this._specialOccurrenceDao != null) {
            return this._specialOccurrenceDao;
        }
        synchronized (this) {
            if (this._specialOccurrenceDao == null) {
                this._specialOccurrenceDao = new SpecialOccurrenceDao_Impl(this);
            }
            specialOccurrenceDao = this._specialOccurrenceDao;
        }
        return specialOccurrenceDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public BackgroundProcessDao backgroundProcessDao() {
        BackgroundProcessDao backgroundProcessDao;
        if (this._backgroundProcessDao != null) {
            return this._backgroundProcessDao;
        }
        synchronized (this) {
            if (this._backgroundProcessDao == null) {
                this._backgroundProcessDao = new BackgroundProcessDao_Impl(this);
            }
            backgroundProcessDao = this._backgroundProcessDao;
        }
        return backgroundProcessDao;
    }

    @Override // org.informatika.sirekap.db.AppDatabase
    public SecurityDao securityDao() {
        SecurityDao securityDao;
        if (this._securityDao != null) {
            return this._securityDao;
        }
        synchronized (this) {
            if (this._securityDao == null) {
                this._securityDao = new SecurityDao_Impl(this);
            }
            securityDao = this._securityDao;
        }
        return securityDao;
    }
}
