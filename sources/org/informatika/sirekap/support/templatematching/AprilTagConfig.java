package org.informatika.sirekap.support.templatematching;

import kotlin.Metadata;
import kotlin.Pair;
import org.informatika.sirekap.model.Election;

/* compiled from: AprilTagConfig.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t2\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/support/templatematching/AprilTagConfig;", "", "()V", "FORMC_NOT_FOUND", "", "getCandidateNum", "", "aprilTagCode", "getElectionProfile", "Lkotlin/Pair;", "getFormName", "isFormValid", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AprilTagConfig {
    public static final String FORMC_NOT_FOUND = "Form tidak diketahui";
    public static final AprilTagConfig INSTANCE = new AprilTagConfig();

    public final int getCandidateNum(int i) {
        boolean z = true;
        int i2 = 3;
        if (!(101 <= i && i < 104)) {
            if (!(131 <= i && i < 134)) {
                if (!(161 <= i && i < 164)) {
                    i2 = 10;
                    if (!(201 <= i && i < 220)) {
                        if (!(231 <= i && i < 250)) {
                            if (!(261 <= i && i < 280)) {
                                if (301 <= i && i < 314) {
                                    return 8;
                                }
                                if (!(342 <= i && i < 354)) {
                                    if (!(372 <= i && i < 384)) {
                                        if (!(401 <= i && i < 426)) {
                                            if (!(442 <= i && i < 466)) {
                                                if (!(472 <= i && i < 496)) {
                                                    if (!(501 <= i && i < 526)) {
                                                        if (!(542 <= i && i < 566)) {
                                                            if (572 > i || i >= 596) {
                                                                z = false;
                                                            }
                                                            if (!z) {
                                                                return 0;
                                                            }
                                                        }
                                                    }
                                                }
                                                return 14;
                                            }
                                        }
                                    }
                                    return 12;
                                }
                            }
                        }
                    }
                }
            }
        }
        return i2;
    }

    public final boolean isFormValid(int i) {
        return ((((((((((((((((((((101 <= i && i < 104) || (131 <= i && i < 134)) || (161 <= i && i < 164)) || (201 <= i && i < 219)) || (231 <= i && i < 249)) || (261 <= i && i < 279)) || (219 <= i && i < 221)) || (249 <= i && i < 251)) || (279 <= i && i < 281)) || (301 <= i && i < 314)) || (342 <= i && i < 354)) || (372 <= i && i < 384)) || i == 399) || (401 <= i && i < 426)) || (442 <= i && i < 466)) || (472 <= i && i < 496)) || i == 499) || (501 <= i && i < 526)) || (542 <= i && i < 566)) || (572 <= i && i < 596)) || i == 599;
    }

    private AprilTagConfig() {
    }

    public final String getFormName(int i) {
        if (isFormValid(i)) {
            if (i / 500 > 0) {
                return "PDPRD Kabko (" + i + ")";
            }
            if (i / 400 > 0) {
                return "PDPRD Prov (" + i + ")";
            }
            if (i / 300 > 0) {
                return "PDPD (" + i + ")";
            }
            if (i / 200 > 0) {
                return "PDPR RI (" + i + ")";
            }
            return i / 100 > 0 ? "PPWP (" + i + ")" : FORMC_NOT_FOUND;
        }
        return FORMC_NOT_FOUND;
    }

    public final Pair<String, Integer> getElectionProfile(int i) {
        boolean z = true;
        if (101 <= i && i < 104) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_PRESIDEN, Integer.valueOf(i - 100));
        }
        if (131 <= i && i < 134) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_PRESIDEN, Integer.valueOf(i - 130));
        }
        if (161 <= i && i < 164) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_PRESIDEN, Integer.valueOf(i - 160));
        }
        if (201 <= i && i < 219) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPR, Integer.valueOf(i - 200));
        }
        if (231 <= i && i < 249) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPR, Integer.valueOf(i - 230));
        }
        if (261 <= i && i < 279) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPR, Integer.valueOf(i - 260));
        }
        if (219 <= i && i < 221) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPR, Integer.valueOf((i - 219) + 19));
        }
        if (249 <= i && i < 251) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPR, Integer.valueOf((i - 249) + 19));
        }
        if (279 <= i && i < 281) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPR, Integer.valueOf((i - 279) + 19));
        }
        if (301 <= i && i < 314) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPD, Integer.valueOf(i - 300));
        }
        if (342 <= i && i < 354) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPD, Integer.valueOf(i - 340));
        }
        if (372 <= i && i < 384) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPD, Integer.valueOf(i - 370));
        }
        if (i == 399) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPD, 99);
        }
        if (401 <= i && i < 426) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI, Integer.valueOf(i - 400));
        }
        if (442 <= i && i < 466) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI, Integer.valueOf(i - 440));
        }
        if (472 <= i && i < 496) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI, Integer.valueOf(i - 470));
        }
        if (i == 499) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI, 99);
        }
        if (501 <= i && i < 526) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPRD_KABKO, Integer.valueOf(i - 500));
        }
        if (542 <= i && i < 566) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPRD_KABKO, Integer.valueOf(i - 540));
        }
        if (572 > i || i >= 596) {
            z = false;
        }
        if (z) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPRD_KABKO, Integer.valueOf(i - 570));
        }
        if (i == 599) {
            return new Pair<>(Election.ELECTION_PEMILIHAN_DPRD_KABKO, 99);
        }
        return null;
    }
}
