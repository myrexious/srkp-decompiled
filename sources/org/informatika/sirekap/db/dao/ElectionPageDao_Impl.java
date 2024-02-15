package org.informatika.sirekap.db.dao;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageStage;
import org.informatika.sirekap.model.ElectionPageWithRelation;

/* loaded from: classes2.dex */
public final class ElectionPageDao_Impl implements ElectionPageDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<ElectionPage> __insertionAdapterOfElectionPage;
    private final SharedSQLiteStatement __preparedStmtOfAddVisionError;
    private final SharedSQLiteStatement __preparedStmtOfDeleteById;
    private final SharedSQLiteStatement __preparedStmtOfDeletePhoto;
    private final SharedSQLiteStatement __preparedStmtOfFinishCreatePdf;
    private final SharedSQLiteStatement __preparedStmtOfFinishPerspectiveCorrection;
    private final SharedSQLiteStatement __preparedStmtOfFinishPhoto;
    private final SharedSQLiteStatement __preparedStmtOfFinishPhotoFromServer;
    private final SharedSQLiteStatement __preparedStmtOfFinishSend;
    private final SharedSQLiteStatement __preparedStmtOfFinishVerify;
    private final SharedSQLiteStatement __preparedStmtOfMarkAsContinueVerify;
    private final SharedSQLiteStatement __preparedStmtOfMarkAsSaved;
    private final SharedSQLiteStatement __preparedStmtOfRemoveVisionError;
    private final SharedSQLiteStatement __preparedStmtOfStartSend;

    public ElectionPageDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfElectionPage = new EntityInsertionAdapter<ElectionPage>(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `election_page` (`id`,`jenisPemilihan`,`electionId`,`kind`,`number`,`currentStageIndex`,`takePhotoAttempt`,`photoPath`,`croppedPhotoPath`,`correctedPhotoPath`,`perspectiveCorrectionError`,`isCorrected`,`idImage`,`isSaved`,`signatureCroppedPhoto`,`hashDocumentCropped`,`hashDocumentCorrected`,`isContinueVerify`,`hasVisionError`,`aprilTagCode`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, ElectionPage value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
                if (value.getJenisPemilihan() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getJenisPemilihan());
                }
                if (value.getElectionId() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getElectionId());
                }
                stmt.bindLong(4, value.getKind());
                stmt.bindLong(5, value.getNumber());
                stmt.bindLong(6, value.getCurrentStageIndex());
                stmt.bindLong(7, value.getTakePhotoAttempt());
                if (value.getPhotoPath() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getPhotoPath());
                }
                if (value.getCroppedPhotoPath() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getCroppedPhotoPath());
                }
                if (value.getCorrectedPhotoPath() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getCorrectedPhotoPath());
                }
                if (value.getPerspectiveCorrectionError() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.getPerspectiveCorrectionError());
                }
                stmt.bindLong(12, value.isCorrected() ? 1L : 0L);
                if (value.getIdImage() == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindString(13, value.getIdImage());
                }
                Integer valueOf = value.isSaved() == null ? null : Integer.valueOf(value.isSaved().booleanValue() ? 1 : 0);
                if (valueOf == null) {
                    stmt.bindNull(14);
                } else {
                    stmt.bindLong(14, valueOf.intValue());
                }
                if (value.getSignatureCroppedPhoto() == null) {
                    stmt.bindNull(15);
                } else {
                    stmt.bindString(15, value.getSignatureCroppedPhoto());
                }
                if (value.getHashDocumentCropped() == null) {
                    stmt.bindNull(16);
                } else {
                    stmt.bindString(16, value.getHashDocumentCropped());
                }
                if (value.getHashDocumentCorrected() == null) {
                    stmt.bindNull(17);
                } else {
                    stmt.bindString(17, value.getHashDocumentCorrected());
                }
                stmt.bindLong(18, value.isContinueVerify() ? 1L : 0L);
                stmt.bindLong(19, value.getHasVisionError() ? 1L : 0L);
                if (value.getAprilTagCode() == null) {
                    stmt.bindNull(20);
                } else {
                    stmt.bindLong(20, value.getAprilTagCode().intValue());
                }
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM election_page WHERE id = ?";
            }
        };
        this.__preparedStmtOfFinishPhoto = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page SET photoPath = ?, croppedPhotoPath = ?, currentStageIndex = 1, correctedPhotoPath = null, isCorrected = 0, perspectiveCorrectionError = null, takePhotoAttempt = ?, hashDocumentCropped = ?, signatureCroppedPhoto = ?, hashDocumentCorrected = null, isSaved = 0, idImage = null, isContinueVerify = 0, hasVisionError = 0, aprilTagCode = ? WHERE id = ?";
            }
        };
        this.__preparedStmtOfFinishPhotoFromServer = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page SET croppedPhotoPath = ?, currentStageIndex = 2, hashDocumentCropped = ?, idImage = ?, isCorrected = 1 WHERE id = ?";
            }
        };
        this.__preparedStmtOfDeletePhoto = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page SET photoPath = null, croppedPhotoPath = null, currentStageIndex = 0, correctedPhotoPath = null, isCorrected = 0, perspectiveCorrectionError = null, hashDocumentCropped = null, hashDocumentCorrected = null, isSaved = null, idImage = null WHERE id = ?";
            }
        };
        this.__preparedStmtOfFinishPerspectiveCorrection = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page SET isCorrected = 1, correctedPhotoPath = ?, perspectiveCorrectionError = ?, hashDocumentCorrected = ?, aprilTagCode = ? WHERE id = ?";
            }
        };
        this.__preparedStmtOfStartSend = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page SET currentStageIndex = 1 WHERE id = ?";
            }
        };
        this.__preparedStmtOfFinishSend = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.8
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page SET currentStageIndex = 2, idImage = ?, isSaved = 0, perspectiveCorrectionError = null WHERE id = ?";
            }
        };
        this.__preparedStmtOfMarkAsSaved = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.9
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page SET isSaved = ? WHERE id = ?";
            }
        };
        this.__preparedStmtOfFinishVerify = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.10
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page SET currentStageIndex = 3 WHERE id = ?";
            }
        };
        this.__preparedStmtOfFinishCreatePdf = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.11
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page SET currentStageIndex = 3, idImage = -1 WHERE electionId = ?";
            }
        };
        this.__preparedStmtOfMarkAsContinueVerify = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.12
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page SET isContinueVerify = 1, hasVisionError = 0 WHERE id = ?";
            }
        };
        this.__preparedStmtOfAddVisionError = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.13
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page SET hasVisionError = 1 WHERE idImage = ?";
            }
        };
        this.__preparedStmtOfRemoveVisionError = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.14
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE election_page SET hasVisionError = 0 WHERE idImage = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public void insertAll(final List<ElectionPage> electionPages) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfElectionPage.insert(electionPages);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public void deleteById(final String electionPageId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteById.acquire();
        if (electionPageId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, electionPageId);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteById.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public void finishPhoto(final String id2, final String photoPath, final String croppedPhotoPath, final int takePhotoAttempt, final String signatureCroppedPhoto, final String hashDocumentCropped, final Integer aprilTagCode) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfFinishPhoto.acquire();
        if (photoPath == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, photoPath);
        }
        if (croppedPhotoPath == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, croppedPhotoPath);
        }
        acquire.bindLong(3, takePhotoAttempt);
        if (hashDocumentCropped == null) {
            acquire.bindNull(4);
        } else {
            acquire.bindString(4, hashDocumentCropped);
        }
        if (signatureCroppedPhoto == null) {
            acquire.bindNull(5);
        } else {
            acquire.bindString(5, signatureCroppedPhoto);
        }
        if (aprilTagCode == null) {
            acquire.bindNull(6);
        } else {
            acquire.bindLong(6, aprilTagCode.intValue());
        }
        if (id2 == null) {
            acquire.bindNull(7);
        } else {
            acquire.bindString(7, id2);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfFinishPhoto.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public void finishPhotoFromServer(final String id2, final String croppedPhotoPath, final String hashDocumentCropped, final String idImage) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfFinishPhotoFromServer.acquire();
        if (croppedPhotoPath == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, croppedPhotoPath);
        }
        if (hashDocumentCropped == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, hashDocumentCropped);
        }
        if (idImage == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, idImage);
        }
        if (id2 == null) {
            acquire.bindNull(4);
        } else {
            acquire.bindString(4, id2);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfFinishPhotoFromServer.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public void deletePhoto(final String id2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeletePhoto.acquire();
        if (id2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id2);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeletePhoto.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public void finishPerspectiveCorrection(final String id2, final String correctedPhotoPath, final String perspectiveCorrectionError, final String hashDocumentCorrected, final Integer aprilTagCode) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfFinishPerspectiveCorrection.acquire();
        if (correctedPhotoPath == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, correctedPhotoPath);
        }
        if (perspectiveCorrectionError == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, perspectiveCorrectionError);
        }
        if (hashDocumentCorrected == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, hashDocumentCorrected);
        }
        if (aprilTagCode == null) {
            acquire.bindNull(4);
        } else {
            acquire.bindLong(4, aprilTagCode.intValue());
        }
        if (id2 == null) {
            acquire.bindNull(5);
        } else {
            acquire.bindString(5, id2);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfFinishPerspectiveCorrection.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public void startSend(final String id2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfStartSend.acquire();
        if (id2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id2);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfStartSend.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public void finishSend(final String id2, final String idImage) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfFinishSend.acquire();
        if (idImage == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, idImage);
        }
        if (id2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, id2);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfFinishSend.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public void markAsSaved(final String id2, final boolean isSaved) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkAsSaved.acquire();
        acquire.bindLong(1, isSaved ? 1L : 0L);
        if (id2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, id2);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkAsSaved.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public void finishVerify(final String id2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfFinishVerify.acquire();
        if (id2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id2);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfFinishVerify.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public void finishCreatePdf(final String electionId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfFinishCreatePdf.acquire();
        if (electionId == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, electionId);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfFinishCreatePdf.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public void markAsContinueVerify(final String id2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkAsContinueVerify.acquire();
        if (id2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id2);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfMarkAsContinueVerify.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public void addVisionError(final String idImage) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfAddVisionError.acquire();
        if (idImage == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, idImage);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfAddVisionError.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public void removeVisionError(final String idImage) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfRemoveVisionError.acquire();
        if (idImage == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, idImage);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfRemoveVisionError.release(acquire);
        }
    }

    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    public LiveData<ElectionPageWithRelation> getById(final String id2) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM election_page WHERE id = ?", 1);
        if (id2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, id2);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"election_page_stage", "election", "election_page"}, true, new Callable<ElectionPageWithRelation>() { // from class: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.15
            /* JADX WARN: Removed duplicated region for block: B:234:0x01bb  */
            /* JADX WARN: Removed duplicated region for block: B:235:0x01be A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            /* JADX WARN: Removed duplicated region for block: B:238:0x01ca  */
            /* JADX WARN: Removed duplicated region for block: B:239:0x01cd A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            /* JADX WARN: Removed duplicated region for block: B:242:0x01d9  */
            /* JADX WARN: Removed duplicated region for block: B:243:0x01dc A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            /* JADX WARN: Removed duplicated region for block: B:246:0x01f8  */
            /* JADX WARN: Removed duplicated region for block: B:247:0x01fb A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            /* JADX WARN: Removed duplicated region for block: B:250:0x0207  */
            /* JADX WARN: Removed duplicated region for block: B:251:0x020a A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            /* JADX WARN: Removed duplicated region for block: B:254:0x0216  */
            /* JADX WARN: Removed duplicated region for block: B:255:0x0219 A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            /* JADX WARN: Removed duplicated region for block: B:258:0x0225  */
            /* JADX WARN: Removed duplicated region for block: B:259:0x0228 A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            /* JADX WARN: Removed duplicated region for block: B:262:0x0235  */
            /* JADX WARN: Removed duplicated region for block: B:263:0x0238  */
            /* JADX WARN: Removed duplicated region for block: B:266:0x0240  */
            /* JADX WARN: Removed duplicated region for block: B:267:0x0243 A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            /* JADX WARN: Removed duplicated region for block: B:270:0x024f  */
            /* JADX WARN: Removed duplicated region for block: B:271:0x0251 A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            /* JADX WARN: Removed duplicated region for block: B:273:0x025b  */
            /* JADX WARN: Removed duplicated region for block: B:274:0x0260 A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            /* JADX WARN: Removed duplicated region for block: B:281:0x0277  */
            /* JADX WARN: Removed duplicated region for block: B:282:0x027c A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            /* JADX WARN: Removed duplicated region for block: B:285:0x028a  */
            /* JADX WARN: Removed duplicated region for block: B:286:0x028f A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            /* JADX WARN: Removed duplicated region for block: B:289:0x029d  */
            /* JADX WARN: Removed duplicated region for block: B:290:0x02a2 A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            /* JADX WARN: Removed duplicated region for block: B:293:0x02b0  */
            /* JADX WARN: Removed duplicated region for block: B:294:0x02b5  */
            /* JADX WARN: Removed duplicated region for block: B:297:0x02bf  */
            /* JADX WARN: Removed duplicated region for block: B:298:0x02c2  */
            /* JADX WARN: Removed duplicated region for block: B:301:0x02ca  */
            /* JADX WARN: Removed duplicated region for block: B:302:0x02cd A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            /* JADX WARN: Removed duplicated region for block: B:306:0x02ec A[Catch: all -> 0x0306, TryCatch #1 {all -> 0x0306, blocks: (B:179:0x00f2, B:181:0x00f8, B:183:0x00fe, B:185:0x0104, B:187:0x010a, B:189:0x0110, B:191:0x0116, B:193:0x011c, B:195:0x0122, B:197:0x0128, B:199:0x012e, B:201:0x0136, B:203:0x013e, B:205:0x0146, B:207:0x0150, B:209:0x015a, B:211:0x0164, B:213:0x016e, B:215:0x0178, B:217:0x0182, B:232:0x01b5, B:236:0x01c4, B:240:0x01d3, B:244:0x01e2, B:248:0x0201, B:252:0x0210, B:256:0x021f, B:260:0x022e, B:264:0x023a, B:268:0x0249, B:279:0x0271, B:283:0x0284, B:287:0x0297, B:291:0x02aa, B:295:0x02b9, B:299:0x02c4, B:303:0x02d7, B:304:0x02de, B:306:0x02ec, B:307:0x02f1, B:302:0x02cd, B:290:0x02a2, B:286:0x028f, B:282:0x027c, B:274:0x0260, B:278:0x0269, B:271:0x0251, B:267:0x0243, B:259:0x0228, B:255:0x0219, B:251:0x020a, B:247:0x01fb, B:243:0x01dc, B:239:0x01cd, B:235:0x01be), top: B:324:0x00f2 }] */
            @Override // java.util.concurrent.Callable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public org.informatika.sirekap.model.ElectionPageWithRelation call() throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 819
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.AnonymousClass15.call():org.informatika.sirekap.model.ElectionPageWithRelation");
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:239:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x01c3 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:243:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x01d2 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:247:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x01e1 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:251:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0200 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:255:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x020f A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:259:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x021e A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:263:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x022d A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:267:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0248 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0256 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0265 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:286:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0281 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:290:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0294 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:294:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x02a7 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:298:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x02d2 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:311:0x02f1 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.informatika.sirekap.model.ElectionPageWithRelation getByIdImage(final java.lang.String r47) {
        /*
            Method dump skipped, instructions count: 797
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.getByIdImage(java.lang.String):org.informatika.sirekap.model.ElectionPageWithRelation");
    }

    /* JADX WARN: Removed duplicated region for block: B:239:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x01c3 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:243:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x01d2 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:247:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x01e1 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:251:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0200 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:255:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x020f A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:259:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x021e A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:263:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x022d A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:267:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0248 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0256 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0265 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:286:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0281 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:290:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0294 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:294:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x02a7 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:298:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x02d2 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:311:0x02f1 A[Catch: all -> 0x0311, TryCatch #0 {all -> 0x0311, blocks: (B:174:0x0070, B:175:0x00b4, B:177:0x00ba, B:179:0x00c8, B:181:0x00d5, B:182:0x00e2, B:184:0x00f7, B:186:0x00fd, B:188:0x0103, B:190:0x0109, B:192:0x010f, B:194:0x0115, B:196:0x011b, B:198:0x0121, B:200:0x0127, B:202:0x012d, B:204:0x0133, B:206:0x013b, B:208:0x0143, B:210:0x014b, B:212:0x0155, B:214:0x015f, B:216:0x0169, B:218:0x0173, B:220:0x017d, B:222:0x0187, B:237:0x01ba, B:241:0x01c9, B:245:0x01d8, B:249:0x01e7, B:253:0x0206, B:257:0x0215, B:261:0x0224, B:265:0x0233, B:269:0x023f, B:273:0x024e, B:284:0x0276, B:288:0x0289, B:292:0x029c, B:296:0x02af, B:300:0x02be, B:304:0x02c9, B:308:0x02dc, B:309:0x02e3, B:311:0x02f1, B:312:0x02f6, B:307:0x02d2, B:295:0x02a7, B:291:0x0294, B:287:0x0281, B:279:0x0265, B:283:0x026e, B:276:0x0256, B:272:0x0248, B:264:0x022d, B:260:0x021e, B:256:0x020f, B:252:0x0200, B:248:0x01e1, B:244:0x01d2, B:240:0x01c3), top: B:323:0x0070 }] */
    @Override // org.informatika.sirekap.db.dao.ElectionPageDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.informatika.sirekap.model.ElectionPageWithRelation getByIdImageNullable(final java.lang.String r47) {
        /*
            Method dump skipped, instructions count: 797
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.getByIdImageNullable(java.lang.String):org.informatika.sirekap.model.ElectionPageWithRelation");
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    public void __fetchRelationshipelectionPageStageAsorgInformatikaSirekapModelElectionPageStage(final ArrayMap<String, ArrayList<ElectionPageStage>> _map) {
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, ArrayList<ElectionPageStage>> arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                arrayMap.put(_map.keyAt(i), _map.valueAt(i));
                i++;
                i2++;
                if (i2 == 999) {
                    __fetchRelationshipelectionPageStageAsorgInformatikaSirekapModelElectionPageStage(arrayMap);
                    arrayMap = new ArrayMap<>((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                __fetchRelationshipelectionPageStageAsorgInformatikaSirekapModelElectionPageStage(arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `electionId`,`electionPageId`,`type`,`status`,`isOffline` FROM `election_page_stage` WHERE `electionPageId` IN (");
        int size2 = keySet.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2 + 0);
        int i3 = 1;
        for (String str : keySet) {
            if (str == null) {
                acquire.bindNull(i3);
            } else {
                acquire.bindString(i3, str);
            }
            i3++;
        }
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(query, "electionPageId");
            if (columnIndex == -1) {
                return;
            }
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "electionId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "electionPageId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isOffline");
            while (query.moveToNext()) {
                ArrayList<ElectionPageStage> arrayList = _map.get(query.getString(columnIndex));
                if (arrayList != null) {
                    arrayList.add(new ElectionPageStage(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5) != 0));
                }
            }
        } finally {
            query.close();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:336:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x02ab A[Catch: all -> 0x0498, TryCatch #0 {all -> 0x0498, blocks: (B:249:0x008e, B:254:0x0099, B:255:0x013d, B:257:0x0143, B:259:0x014f, B:263:0x015e, B:267:0x0171, B:271:0x0180, B:275:0x0193, B:279:0x019e, B:283:0x01b1, B:287:0x01c0, B:291:0x01cf, B:295:0x01da, B:299:0x01eb, B:303:0x01fc, B:305:0x0208, B:307:0x0212, B:309:0x021c, B:311:0x0226, B:313:0x0230, B:315:0x023a, B:317:0x0244, B:319:0x024e, B:321:0x0258, B:334:0x02a1, B:338:0x02b3, B:342:0x02c5, B:344:0x02cb, B:346:0x02d1, B:348:0x02d7, B:350:0x02dd, B:352:0x02e3, B:354:0x02e9, B:356:0x02ef, B:426:0x0424, B:427:0x042b, B:360:0x030b, B:364:0x031d, B:368:0x032f, B:370:0x0335, B:372:0x033b, B:374:0x0341, B:376:0x0347, B:378:0x034d, B:425:0x041f, B:382:0x0365, B:386:0x0377, B:390:0x0389, B:392:0x038f, B:394:0x0395, B:396:0x039b, B:424:0x041a, B:400:0x03af, B:404:0x03c1, B:408:0x03d3, B:410:0x03d9, B:423:0x0415, B:414:0x03e8, B:418:0x03fa, B:422:0x0410, B:421:0x0406, B:417:0x03f2, B:407:0x03cb, B:403:0x03b9, B:389:0x0381, B:385:0x036f, B:367:0x0327, B:363:0x0315, B:341:0x02bd, B:337:0x02ab, B:290:0x01c9, B:286:0x01ba, B:282:0x01ab, B:274:0x018d, B:270:0x017a, B:266:0x016b, B:262:0x0158), top: B:436:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:340:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x02bd A[Catch: all -> 0x0498, TryCatch #0 {all -> 0x0498, blocks: (B:249:0x008e, B:254:0x0099, B:255:0x013d, B:257:0x0143, B:259:0x014f, B:263:0x015e, B:267:0x0171, B:271:0x0180, B:275:0x0193, B:279:0x019e, B:283:0x01b1, B:287:0x01c0, B:291:0x01cf, B:295:0x01da, B:299:0x01eb, B:303:0x01fc, B:305:0x0208, B:307:0x0212, B:309:0x021c, B:311:0x0226, B:313:0x0230, B:315:0x023a, B:317:0x0244, B:319:0x024e, B:321:0x0258, B:334:0x02a1, B:338:0x02b3, B:342:0x02c5, B:344:0x02cb, B:346:0x02d1, B:348:0x02d7, B:350:0x02dd, B:352:0x02e3, B:354:0x02e9, B:356:0x02ef, B:426:0x0424, B:427:0x042b, B:360:0x030b, B:364:0x031d, B:368:0x032f, B:370:0x0335, B:372:0x033b, B:374:0x0341, B:376:0x0347, B:378:0x034d, B:425:0x041f, B:382:0x0365, B:386:0x0377, B:390:0x0389, B:392:0x038f, B:394:0x0395, B:396:0x039b, B:424:0x041a, B:400:0x03af, B:404:0x03c1, B:408:0x03d3, B:410:0x03d9, B:423:0x0415, B:414:0x03e8, B:418:0x03fa, B:422:0x0410, B:421:0x0406, B:417:0x03f2, B:407:0x03cb, B:403:0x03b9, B:389:0x0381, B:385:0x036f, B:367:0x0327, B:363:0x0315, B:341:0x02bd, B:337:0x02ab, B:290:0x01c9, B:286:0x01ba, B:282:0x01ab, B:274:0x018d, B:270:0x017a, B:266:0x016b, B:262:0x0158), top: B:436:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:344:0x02cb A[Catch: all -> 0x0498, TryCatch #0 {all -> 0x0498, blocks: (B:249:0x008e, B:254:0x0099, B:255:0x013d, B:257:0x0143, B:259:0x014f, B:263:0x015e, B:267:0x0171, B:271:0x0180, B:275:0x0193, B:279:0x019e, B:283:0x01b1, B:287:0x01c0, B:291:0x01cf, B:295:0x01da, B:299:0x01eb, B:303:0x01fc, B:305:0x0208, B:307:0x0212, B:309:0x021c, B:311:0x0226, B:313:0x0230, B:315:0x023a, B:317:0x0244, B:319:0x024e, B:321:0x0258, B:334:0x02a1, B:338:0x02b3, B:342:0x02c5, B:344:0x02cb, B:346:0x02d1, B:348:0x02d7, B:350:0x02dd, B:352:0x02e3, B:354:0x02e9, B:356:0x02ef, B:426:0x0424, B:427:0x042b, B:360:0x030b, B:364:0x031d, B:368:0x032f, B:370:0x0335, B:372:0x033b, B:374:0x0341, B:376:0x0347, B:378:0x034d, B:425:0x041f, B:382:0x0365, B:386:0x0377, B:390:0x0389, B:392:0x038f, B:394:0x0395, B:396:0x039b, B:424:0x041a, B:400:0x03af, B:404:0x03c1, B:408:0x03d3, B:410:0x03d9, B:423:0x0415, B:414:0x03e8, B:418:0x03fa, B:422:0x0410, B:421:0x0406, B:417:0x03f2, B:407:0x03cb, B:403:0x03b9, B:389:0x0381, B:385:0x036f, B:367:0x0327, B:363:0x0315, B:341:0x02bd, B:337:0x02ab, B:290:0x01c9, B:286:0x01ba, B:282:0x01ab, B:274:0x018d, B:270:0x017a, B:266:0x016b, B:262:0x0158), top: B:436:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:362:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0315 A[Catch: all -> 0x0498, TryCatch #0 {all -> 0x0498, blocks: (B:249:0x008e, B:254:0x0099, B:255:0x013d, B:257:0x0143, B:259:0x014f, B:263:0x015e, B:267:0x0171, B:271:0x0180, B:275:0x0193, B:279:0x019e, B:283:0x01b1, B:287:0x01c0, B:291:0x01cf, B:295:0x01da, B:299:0x01eb, B:303:0x01fc, B:305:0x0208, B:307:0x0212, B:309:0x021c, B:311:0x0226, B:313:0x0230, B:315:0x023a, B:317:0x0244, B:319:0x024e, B:321:0x0258, B:334:0x02a1, B:338:0x02b3, B:342:0x02c5, B:344:0x02cb, B:346:0x02d1, B:348:0x02d7, B:350:0x02dd, B:352:0x02e3, B:354:0x02e9, B:356:0x02ef, B:426:0x0424, B:427:0x042b, B:360:0x030b, B:364:0x031d, B:368:0x032f, B:370:0x0335, B:372:0x033b, B:374:0x0341, B:376:0x0347, B:378:0x034d, B:425:0x041f, B:382:0x0365, B:386:0x0377, B:390:0x0389, B:392:0x038f, B:394:0x0395, B:396:0x039b, B:424:0x041a, B:400:0x03af, B:404:0x03c1, B:408:0x03d3, B:410:0x03d9, B:423:0x0415, B:414:0x03e8, B:418:0x03fa, B:422:0x0410, B:421:0x0406, B:417:0x03f2, B:407:0x03cb, B:403:0x03b9, B:389:0x0381, B:385:0x036f, B:367:0x0327, B:363:0x0315, B:341:0x02bd, B:337:0x02ab, B:290:0x01c9, B:286:0x01ba, B:282:0x01ab, B:274:0x018d, B:270:0x017a, B:266:0x016b, B:262:0x0158), top: B:436:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:366:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x0327 A[Catch: all -> 0x0498, TryCatch #0 {all -> 0x0498, blocks: (B:249:0x008e, B:254:0x0099, B:255:0x013d, B:257:0x0143, B:259:0x014f, B:263:0x015e, B:267:0x0171, B:271:0x0180, B:275:0x0193, B:279:0x019e, B:283:0x01b1, B:287:0x01c0, B:291:0x01cf, B:295:0x01da, B:299:0x01eb, B:303:0x01fc, B:305:0x0208, B:307:0x0212, B:309:0x021c, B:311:0x0226, B:313:0x0230, B:315:0x023a, B:317:0x0244, B:319:0x024e, B:321:0x0258, B:334:0x02a1, B:338:0x02b3, B:342:0x02c5, B:344:0x02cb, B:346:0x02d1, B:348:0x02d7, B:350:0x02dd, B:352:0x02e3, B:354:0x02e9, B:356:0x02ef, B:426:0x0424, B:427:0x042b, B:360:0x030b, B:364:0x031d, B:368:0x032f, B:370:0x0335, B:372:0x033b, B:374:0x0341, B:376:0x0347, B:378:0x034d, B:425:0x041f, B:382:0x0365, B:386:0x0377, B:390:0x0389, B:392:0x038f, B:394:0x0395, B:396:0x039b, B:424:0x041a, B:400:0x03af, B:404:0x03c1, B:408:0x03d3, B:410:0x03d9, B:423:0x0415, B:414:0x03e8, B:418:0x03fa, B:422:0x0410, B:421:0x0406, B:417:0x03f2, B:407:0x03cb, B:403:0x03b9, B:389:0x0381, B:385:0x036f, B:367:0x0327, B:363:0x0315, B:341:0x02bd, B:337:0x02ab, B:290:0x01c9, B:286:0x01ba, B:282:0x01ab, B:274:0x018d, B:270:0x017a, B:266:0x016b, B:262:0x0158), top: B:436:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0335 A[Catch: all -> 0x0498, TryCatch #0 {all -> 0x0498, blocks: (B:249:0x008e, B:254:0x0099, B:255:0x013d, B:257:0x0143, B:259:0x014f, B:263:0x015e, B:267:0x0171, B:271:0x0180, B:275:0x0193, B:279:0x019e, B:283:0x01b1, B:287:0x01c0, B:291:0x01cf, B:295:0x01da, B:299:0x01eb, B:303:0x01fc, B:305:0x0208, B:307:0x0212, B:309:0x021c, B:311:0x0226, B:313:0x0230, B:315:0x023a, B:317:0x0244, B:319:0x024e, B:321:0x0258, B:334:0x02a1, B:338:0x02b3, B:342:0x02c5, B:344:0x02cb, B:346:0x02d1, B:348:0x02d7, B:350:0x02dd, B:352:0x02e3, B:354:0x02e9, B:356:0x02ef, B:426:0x0424, B:427:0x042b, B:360:0x030b, B:364:0x031d, B:368:0x032f, B:370:0x0335, B:372:0x033b, B:374:0x0341, B:376:0x0347, B:378:0x034d, B:425:0x041f, B:382:0x0365, B:386:0x0377, B:390:0x0389, B:392:0x038f, B:394:0x0395, B:396:0x039b, B:424:0x041a, B:400:0x03af, B:404:0x03c1, B:408:0x03d3, B:410:0x03d9, B:423:0x0415, B:414:0x03e8, B:418:0x03fa, B:422:0x0410, B:421:0x0406, B:417:0x03f2, B:407:0x03cb, B:403:0x03b9, B:389:0x0381, B:385:0x036f, B:367:0x0327, B:363:0x0315, B:341:0x02bd, B:337:0x02ab, B:290:0x01c9, B:286:0x01ba, B:282:0x01ab, B:274:0x018d, B:270:0x017a, B:266:0x016b, B:262:0x0158), top: B:436:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:384:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x036f A[Catch: all -> 0x0498, TryCatch #0 {all -> 0x0498, blocks: (B:249:0x008e, B:254:0x0099, B:255:0x013d, B:257:0x0143, B:259:0x014f, B:263:0x015e, B:267:0x0171, B:271:0x0180, B:275:0x0193, B:279:0x019e, B:283:0x01b1, B:287:0x01c0, B:291:0x01cf, B:295:0x01da, B:299:0x01eb, B:303:0x01fc, B:305:0x0208, B:307:0x0212, B:309:0x021c, B:311:0x0226, B:313:0x0230, B:315:0x023a, B:317:0x0244, B:319:0x024e, B:321:0x0258, B:334:0x02a1, B:338:0x02b3, B:342:0x02c5, B:344:0x02cb, B:346:0x02d1, B:348:0x02d7, B:350:0x02dd, B:352:0x02e3, B:354:0x02e9, B:356:0x02ef, B:426:0x0424, B:427:0x042b, B:360:0x030b, B:364:0x031d, B:368:0x032f, B:370:0x0335, B:372:0x033b, B:374:0x0341, B:376:0x0347, B:378:0x034d, B:425:0x041f, B:382:0x0365, B:386:0x0377, B:390:0x0389, B:392:0x038f, B:394:0x0395, B:396:0x039b, B:424:0x041a, B:400:0x03af, B:404:0x03c1, B:408:0x03d3, B:410:0x03d9, B:423:0x0415, B:414:0x03e8, B:418:0x03fa, B:422:0x0410, B:421:0x0406, B:417:0x03f2, B:407:0x03cb, B:403:0x03b9, B:389:0x0381, B:385:0x036f, B:367:0x0327, B:363:0x0315, B:341:0x02bd, B:337:0x02ab, B:290:0x01c9, B:286:0x01ba, B:282:0x01ab, B:274:0x018d, B:270:0x017a, B:266:0x016b, B:262:0x0158), top: B:436:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:388:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0381 A[Catch: all -> 0x0498, TryCatch #0 {all -> 0x0498, blocks: (B:249:0x008e, B:254:0x0099, B:255:0x013d, B:257:0x0143, B:259:0x014f, B:263:0x015e, B:267:0x0171, B:271:0x0180, B:275:0x0193, B:279:0x019e, B:283:0x01b1, B:287:0x01c0, B:291:0x01cf, B:295:0x01da, B:299:0x01eb, B:303:0x01fc, B:305:0x0208, B:307:0x0212, B:309:0x021c, B:311:0x0226, B:313:0x0230, B:315:0x023a, B:317:0x0244, B:319:0x024e, B:321:0x0258, B:334:0x02a1, B:338:0x02b3, B:342:0x02c5, B:344:0x02cb, B:346:0x02d1, B:348:0x02d7, B:350:0x02dd, B:352:0x02e3, B:354:0x02e9, B:356:0x02ef, B:426:0x0424, B:427:0x042b, B:360:0x030b, B:364:0x031d, B:368:0x032f, B:370:0x0335, B:372:0x033b, B:374:0x0341, B:376:0x0347, B:378:0x034d, B:425:0x041f, B:382:0x0365, B:386:0x0377, B:390:0x0389, B:392:0x038f, B:394:0x0395, B:396:0x039b, B:424:0x041a, B:400:0x03af, B:404:0x03c1, B:408:0x03d3, B:410:0x03d9, B:423:0x0415, B:414:0x03e8, B:418:0x03fa, B:422:0x0410, B:421:0x0406, B:417:0x03f2, B:407:0x03cb, B:403:0x03b9, B:389:0x0381, B:385:0x036f, B:367:0x0327, B:363:0x0315, B:341:0x02bd, B:337:0x02ab, B:290:0x01c9, B:286:0x01ba, B:282:0x01ab, B:274:0x018d, B:270:0x017a, B:266:0x016b, B:262:0x0158), top: B:436:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:392:0x038f A[Catch: all -> 0x0498, TryCatch #0 {all -> 0x0498, blocks: (B:249:0x008e, B:254:0x0099, B:255:0x013d, B:257:0x0143, B:259:0x014f, B:263:0x015e, B:267:0x0171, B:271:0x0180, B:275:0x0193, B:279:0x019e, B:283:0x01b1, B:287:0x01c0, B:291:0x01cf, B:295:0x01da, B:299:0x01eb, B:303:0x01fc, B:305:0x0208, B:307:0x0212, B:309:0x021c, B:311:0x0226, B:313:0x0230, B:315:0x023a, B:317:0x0244, B:319:0x024e, B:321:0x0258, B:334:0x02a1, B:338:0x02b3, B:342:0x02c5, B:344:0x02cb, B:346:0x02d1, B:348:0x02d7, B:350:0x02dd, B:352:0x02e3, B:354:0x02e9, B:356:0x02ef, B:426:0x0424, B:427:0x042b, B:360:0x030b, B:364:0x031d, B:368:0x032f, B:370:0x0335, B:372:0x033b, B:374:0x0341, B:376:0x0347, B:378:0x034d, B:425:0x041f, B:382:0x0365, B:386:0x0377, B:390:0x0389, B:392:0x038f, B:394:0x0395, B:396:0x039b, B:424:0x041a, B:400:0x03af, B:404:0x03c1, B:408:0x03d3, B:410:0x03d9, B:423:0x0415, B:414:0x03e8, B:418:0x03fa, B:422:0x0410, B:421:0x0406, B:417:0x03f2, B:407:0x03cb, B:403:0x03b9, B:389:0x0381, B:385:0x036f, B:367:0x0327, B:363:0x0315, B:341:0x02bd, B:337:0x02ab, B:290:0x01c9, B:286:0x01ba, B:282:0x01ab, B:274:0x018d, B:270:0x017a, B:266:0x016b, B:262:0x0158), top: B:436:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:402:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x03b9 A[Catch: all -> 0x0498, TryCatch #0 {all -> 0x0498, blocks: (B:249:0x008e, B:254:0x0099, B:255:0x013d, B:257:0x0143, B:259:0x014f, B:263:0x015e, B:267:0x0171, B:271:0x0180, B:275:0x0193, B:279:0x019e, B:283:0x01b1, B:287:0x01c0, B:291:0x01cf, B:295:0x01da, B:299:0x01eb, B:303:0x01fc, B:305:0x0208, B:307:0x0212, B:309:0x021c, B:311:0x0226, B:313:0x0230, B:315:0x023a, B:317:0x0244, B:319:0x024e, B:321:0x0258, B:334:0x02a1, B:338:0x02b3, B:342:0x02c5, B:344:0x02cb, B:346:0x02d1, B:348:0x02d7, B:350:0x02dd, B:352:0x02e3, B:354:0x02e9, B:356:0x02ef, B:426:0x0424, B:427:0x042b, B:360:0x030b, B:364:0x031d, B:368:0x032f, B:370:0x0335, B:372:0x033b, B:374:0x0341, B:376:0x0347, B:378:0x034d, B:425:0x041f, B:382:0x0365, B:386:0x0377, B:390:0x0389, B:392:0x038f, B:394:0x0395, B:396:0x039b, B:424:0x041a, B:400:0x03af, B:404:0x03c1, B:408:0x03d3, B:410:0x03d9, B:423:0x0415, B:414:0x03e8, B:418:0x03fa, B:422:0x0410, B:421:0x0406, B:417:0x03f2, B:407:0x03cb, B:403:0x03b9, B:389:0x0381, B:385:0x036f, B:367:0x0327, B:363:0x0315, B:341:0x02bd, B:337:0x02ab, B:290:0x01c9, B:286:0x01ba, B:282:0x01ab, B:274:0x018d, B:270:0x017a, B:266:0x016b, B:262:0x0158), top: B:436:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:406:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x03cb A[Catch: all -> 0x0498, TryCatch #0 {all -> 0x0498, blocks: (B:249:0x008e, B:254:0x0099, B:255:0x013d, B:257:0x0143, B:259:0x014f, B:263:0x015e, B:267:0x0171, B:271:0x0180, B:275:0x0193, B:279:0x019e, B:283:0x01b1, B:287:0x01c0, B:291:0x01cf, B:295:0x01da, B:299:0x01eb, B:303:0x01fc, B:305:0x0208, B:307:0x0212, B:309:0x021c, B:311:0x0226, B:313:0x0230, B:315:0x023a, B:317:0x0244, B:319:0x024e, B:321:0x0258, B:334:0x02a1, B:338:0x02b3, B:342:0x02c5, B:344:0x02cb, B:346:0x02d1, B:348:0x02d7, B:350:0x02dd, B:352:0x02e3, B:354:0x02e9, B:356:0x02ef, B:426:0x0424, B:427:0x042b, B:360:0x030b, B:364:0x031d, B:368:0x032f, B:370:0x0335, B:372:0x033b, B:374:0x0341, B:376:0x0347, B:378:0x034d, B:425:0x041f, B:382:0x0365, B:386:0x0377, B:390:0x0389, B:392:0x038f, B:394:0x0395, B:396:0x039b, B:424:0x041a, B:400:0x03af, B:404:0x03c1, B:408:0x03d3, B:410:0x03d9, B:423:0x0415, B:414:0x03e8, B:418:0x03fa, B:422:0x0410, B:421:0x0406, B:417:0x03f2, B:407:0x03cb, B:403:0x03b9, B:389:0x0381, B:385:0x036f, B:367:0x0327, B:363:0x0315, B:341:0x02bd, B:337:0x02ab, B:290:0x01c9, B:286:0x01ba, B:282:0x01ab, B:274:0x018d, B:270:0x017a, B:266:0x016b, B:262:0x0158), top: B:436:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:410:0x03d9 A[Catch: all -> 0x0498, TryCatch #0 {all -> 0x0498, blocks: (B:249:0x008e, B:254:0x0099, B:255:0x013d, B:257:0x0143, B:259:0x014f, B:263:0x015e, B:267:0x0171, B:271:0x0180, B:275:0x0193, B:279:0x019e, B:283:0x01b1, B:287:0x01c0, B:291:0x01cf, B:295:0x01da, B:299:0x01eb, B:303:0x01fc, B:305:0x0208, B:307:0x0212, B:309:0x021c, B:311:0x0226, B:313:0x0230, B:315:0x023a, B:317:0x0244, B:319:0x024e, B:321:0x0258, B:334:0x02a1, B:338:0x02b3, B:342:0x02c5, B:344:0x02cb, B:346:0x02d1, B:348:0x02d7, B:350:0x02dd, B:352:0x02e3, B:354:0x02e9, B:356:0x02ef, B:426:0x0424, B:427:0x042b, B:360:0x030b, B:364:0x031d, B:368:0x032f, B:370:0x0335, B:372:0x033b, B:374:0x0341, B:376:0x0347, B:378:0x034d, B:425:0x041f, B:382:0x0365, B:386:0x0377, B:390:0x0389, B:392:0x038f, B:394:0x0395, B:396:0x039b, B:424:0x041a, B:400:0x03af, B:404:0x03c1, B:408:0x03d3, B:410:0x03d9, B:423:0x0415, B:414:0x03e8, B:418:0x03fa, B:422:0x0410, B:421:0x0406, B:417:0x03f2, B:407:0x03cb, B:403:0x03b9, B:389:0x0381, B:385:0x036f, B:367:0x0327, B:363:0x0315, B:341:0x02bd, B:337:0x02ab, B:290:0x01c9, B:286:0x01ba, B:282:0x01ab, B:274:0x018d, B:270:0x017a, B:266:0x016b, B:262:0x0158), top: B:436:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:416:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x03f2 A[Catch: all -> 0x0498, TryCatch #0 {all -> 0x0498, blocks: (B:249:0x008e, B:254:0x0099, B:255:0x013d, B:257:0x0143, B:259:0x014f, B:263:0x015e, B:267:0x0171, B:271:0x0180, B:275:0x0193, B:279:0x019e, B:283:0x01b1, B:287:0x01c0, B:291:0x01cf, B:295:0x01da, B:299:0x01eb, B:303:0x01fc, B:305:0x0208, B:307:0x0212, B:309:0x021c, B:311:0x0226, B:313:0x0230, B:315:0x023a, B:317:0x0244, B:319:0x024e, B:321:0x0258, B:334:0x02a1, B:338:0x02b3, B:342:0x02c5, B:344:0x02cb, B:346:0x02d1, B:348:0x02d7, B:350:0x02dd, B:352:0x02e3, B:354:0x02e9, B:356:0x02ef, B:426:0x0424, B:427:0x042b, B:360:0x030b, B:364:0x031d, B:368:0x032f, B:370:0x0335, B:372:0x033b, B:374:0x0341, B:376:0x0347, B:378:0x034d, B:425:0x041f, B:382:0x0365, B:386:0x0377, B:390:0x0389, B:392:0x038f, B:394:0x0395, B:396:0x039b, B:424:0x041a, B:400:0x03af, B:404:0x03c1, B:408:0x03d3, B:410:0x03d9, B:423:0x0415, B:414:0x03e8, B:418:0x03fa, B:422:0x0410, B:421:0x0406, B:417:0x03f2, B:407:0x03cb, B:403:0x03b9, B:389:0x0381, B:385:0x036f, B:367:0x0327, B:363:0x0315, B:341:0x02bd, B:337:0x02ab, B:290:0x01c9, B:286:0x01ba, B:282:0x01ab, B:274:0x018d, B:270:0x017a, B:266:0x016b, B:262:0x0158), top: B:436:0x008e }] */
    /* JADX WARN: Removed duplicated region for block: B:420:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0406 A[Catch: all -> 0x0498, TryCatch #0 {all -> 0x0498, blocks: (B:249:0x008e, B:254:0x0099, B:255:0x013d, B:257:0x0143, B:259:0x014f, B:263:0x015e, B:267:0x0171, B:271:0x0180, B:275:0x0193, B:279:0x019e, B:283:0x01b1, B:287:0x01c0, B:291:0x01cf, B:295:0x01da, B:299:0x01eb, B:303:0x01fc, B:305:0x0208, B:307:0x0212, B:309:0x021c, B:311:0x0226, B:313:0x0230, B:315:0x023a, B:317:0x0244, B:319:0x024e, B:321:0x0258, B:334:0x02a1, B:338:0x02b3, B:342:0x02c5, B:344:0x02cb, B:346:0x02d1, B:348:0x02d7, B:350:0x02dd, B:352:0x02e3, B:354:0x02e9, B:356:0x02ef, B:426:0x0424, B:427:0x042b, B:360:0x030b, B:364:0x031d, B:368:0x032f, B:370:0x0335, B:372:0x033b, B:374:0x0341, B:376:0x0347, B:378:0x034d, B:425:0x041f, B:382:0x0365, B:386:0x0377, B:390:0x0389, B:392:0x038f, B:394:0x0395, B:396:0x039b, B:424:0x041a, B:400:0x03af, B:404:0x03c1, B:408:0x03d3, B:410:0x03d9, B:423:0x0415, B:414:0x03e8, B:418:0x03fa, B:422:0x0410, B:421:0x0406, B:417:0x03f2, B:407:0x03cb, B:403:0x03b9, B:389:0x0381, B:385:0x036f, B:367:0x0327, B:363:0x0315, B:341:0x02bd, B:337:0x02ab, B:290:0x01c9, B:286:0x01ba, B:282:0x01ab, B:274:0x018d, B:270:0x017a, B:266:0x016b, B:262:0x0158), top: B:436:0x008e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void __fetchRelationshipelectionAsorgInformatikaSirekapModelElection(final androidx.collection.ArrayMap<java.lang.String, org.informatika.sirekap.model.Election> r57) {
        /*
            Method dump skipped, instructions count: 1181
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.db.dao.ElectionPageDao_Impl.__fetchRelationshipelectionAsorgInformatikaSirekapModelElection(androidx.collection.ArrayMap):void");
    }
}
