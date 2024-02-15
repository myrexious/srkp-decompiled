package org.informatika.sirekap.db.dao;

import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.model.Candidate;

/* compiled from: CandidateDao.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH'J\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH'J\u0016\u0010\r\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\bH'¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/db/dao/CandidateDao;", "", "deleteAll", "", "deleteAllByElectionId", "electionId", "", "getAllByIndexPartai", "", "Lorg/informatika/sirekap/model/Candidate;", "indexPartai", "", "getOneByIndexPartai", "insertAll", "candidates", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface CandidateDao {
    void deleteAll();

    void deleteAllByElectionId(String str);

    List<Candidate> getAllByIndexPartai(String str, int i);

    Candidate getOneByIndexPartai(String str, int i);

    void insertAll(List<Candidate> list);
}
