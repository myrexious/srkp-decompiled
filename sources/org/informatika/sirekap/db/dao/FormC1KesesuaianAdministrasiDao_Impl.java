package org.informatika.sirekap.db.dao;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import org.informatika.sirekap.model.FormC1KesesuaianAdministration;

/* loaded from: classes2.dex */
public final class FormC1KesesuaianAdministrasiDao_Impl implements FormC1KesesuaianAdministrasiDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<FormC1KesesuaianAdministration> __insertionAdapterOfFormC1KesesuaianAdministration;
    private final EntityInsertionAdapter<FormC1KesesuaianAdministration> __insertionAdapterOfFormC1KesesuaianAdministration_1;
    private final SharedSQLiteStatement __preparedStmtOfDeleteBy;

    public FormC1KesesuaianAdministrasiDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfFormC1KesesuaianAdministration = new EntityInsertionAdapter<FormC1KesesuaianAdministration>(__db) { // from class: org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrasiDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `form_c1_kesesuaian_administration` (`idImage`,`isLnPos`,`jenisPemilihan`,`pemilihDpt_L`,`pemilihDpt_P`,`totalPemilihDpt`,`penggunaDpt_L`,`penggunaDpt_P`,`totalPenggunaDpt`,`penggunaDptb_L`,`penggunaDptb_P`,`totalPenggunaDptb`,`penggunaDpk_L`,`penggunaDpk_P`,`totalPenggunaDpk`,`totalPengguna_L`,`totalPengguna_P`,`totalPengguna`,`suratDiterima`,`suratDikembalikan`,`suratTidakDigunakan`,`suratTidakDikembalikan`,`suratKembaliPPLN`,`suratTidakTerpakai`,`suratDigunakan`,`pemilihDisabilitas_L`,`pemilihDisabilitas_P`,`totalPemilihDisabilitas`,`pemilihDpt_LCorrected`,`pemilihDpt_PCorrected`,`totalPemilihDptCorrected`,`penggunaDpt_LCorrected`,`penggunaDpt_PCorrected`,`totalPenggunaDptCorrected`,`penggunaDptb_LCorrected`,`penggunaDptb_PCorrected`,`totalPenggunaDptbCorrected`,`penggunaDpk_LCorrected`,`penggunaDpk_PCorrected`,`totalPenggunaDpkCorrected`,`totalPengguna_LCorrected`,`totalPengguna_PCorrected`,`totalPenggunaCorrected`,`suratDiterimaCorrected`,`suratDikembalikanCorrected`,`suratTidakDigunakanCorrected`,`suratTidakDikembalikanCorrected`,`suratKembaliPPLNCorrected`,`suratTidakTerpakaiCorrected`,`suratDigunakanCorrected`,`pemilihDisabilitas_LCorrected`,`pemilihDisabilitas_PCorrected`,`totalPemilihDisabilitasCorrected`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FormC1KesesuaianAdministration value) {
                if (value.getIdImage() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdImage());
                }
                stmt.bindLong(2, value.isLnPos() ? 1L : 0L);
                if (value.getJenisPemilihan() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getJenisPemilihan());
                }
                Integer valueOf = value.getPemilihDpt_L() == null ? null : Integer.valueOf(value.getPemilihDpt_L().booleanValue() ? 1 : 0);
                if (valueOf == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindLong(4, valueOf.intValue());
                }
                Integer valueOf2 = value.getPemilihDpt_P() == null ? null : Integer.valueOf(value.getPemilihDpt_P().booleanValue() ? 1 : 0);
                if (valueOf2 == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindLong(5, valueOf2.intValue());
                }
                Integer valueOf3 = value.getTotalPemilihDpt() == null ? null : Integer.valueOf(value.getTotalPemilihDpt().booleanValue() ? 1 : 0);
                if (valueOf3 == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindLong(6, valueOf3.intValue());
                }
                Integer valueOf4 = value.getPenggunaDpt_L() == null ? null : Integer.valueOf(value.getPenggunaDpt_L().booleanValue() ? 1 : 0);
                if (valueOf4 == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindLong(7, valueOf4.intValue());
                }
                Integer valueOf5 = value.getPenggunaDpt_P() == null ? null : Integer.valueOf(value.getPenggunaDpt_P().booleanValue() ? 1 : 0);
                if (valueOf5 == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindLong(8, valueOf5.intValue());
                }
                Integer valueOf6 = value.getTotalPenggunaDpt() == null ? null : Integer.valueOf(value.getTotalPenggunaDpt().booleanValue() ? 1 : 0);
                if (valueOf6 == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindLong(9, valueOf6.intValue());
                }
                Integer valueOf7 = value.getPenggunaDptb_L() == null ? null : Integer.valueOf(value.getPenggunaDptb_L().booleanValue() ? 1 : 0);
                if (valueOf7 == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindLong(10, valueOf7.intValue());
                }
                Integer valueOf8 = value.getPenggunaDptb_P() == null ? null : Integer.valueOf(value.getPenggunaDptb_P().booleanValue() ? 1 : 0);
                if (valueOf8 == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindLong(11, valueOf8.intValue());
                }
                Integer valueOf9 = value.getTotalPenggunaDptb() == null ? null : Integer.valueOf(value.getTotalPenggunaDptb().booleanValue() ? 1 : 0);
                if (valueOf9 == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindLong(12, valueOf9.intValue());
                }
                Integer valueOf10 = value.getPenggunaDpk_L() == null ? null : Integer.valueOf(value.getPenggunaDpk_L().booleanValue() ? 1 : 0);
                if (valueOf10 == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindLong(13, valueOf10.intValue());
                }
                Integer valueOf11 = value.getPenggunaDpk_P() == null ? null : Integer.valueOf(value.getPenggunaDpk_P().booleanValue() ? 1 : 0);
                if (valueOf11 == null) {
                    stmt.bindNull(14);
                } else {
                    stmt.bindLong(14, valueOf11.intValue());
                }
                Integer valueOf12 = value.getTotalPenggunaDpk() == null ? null : Integer.valueOf(value.getTotalPenggunaDpk().booleanValue() ? 1 : 0);
                if (valueOf12 == null) {
                    stmt.bindNull(15);
                } else {
                    stmt.bindLong(15, valueOf12.intValue());
                }
                Integer valueOf13 = value.getTotalPengguna_L() == null ? null : Integer.valueOf(value.getTotalPengguna_L().booleanValue() ? 1 : 0);
                if (valueOf13 == null) {
                    stmt.bindNull(16);
                } else {
                    stmt.bindLong(16, valueOf13.intValue());
                }
                Integer valueOf14 = value.getTotalPengguna_P() == null ? null : Integer.valueOf(value.getTotalPengguna_P().booleanValue() ? 1 : 0);
                if (valueOf14 == null) {
                    stmt.bindNull(17);
                } else {
                    stmt.bindLong(17, valueOf14.intValue());
                }
                Integer valueOf15 = value.getTotalPengguna() == null ? null : Integer.valueOf(value.getTotalPengguna().booleanValue() ? 1 : 0);
                if (valueOf15 == null) {
                    stmt.bindNull(18);
                } else {
                    stmt.bindLong(18, valueOf15.intValue());
                }
                Integer valueOf16 = value.getSuratDiterima() == null ? null : Integer.valueOf(value.getSuratDiterima().booleanValue() ? 1 : 0);
                if (valueOf16 == null) {
                    stmt.bindNull(19);
                } else {
                    stmt.bindLong(19, valueOf16.intValue());
                }
                Integer valueOf17 = value.getSuratDikembalikan() == null ? null : Integer.valueOf(value.getSuratDikembalikan().booleanValue() ? 1 : 0);
                if (valueOf17 == null) {
                    stmt.bindNull(20);
                } else {
                    stmt.bindLong(20, valueOf17.intValue());
                }
                Integer valueOf18 = value.getSuratTidakDigunakan() == null ? null : Integer.valueOf(value.getSuratTidakDigunakan().booleanValue() ? 1 : 0);
                if (valueOf18 == null) {
                    stmt.bindNull(21);
                } else {
                    stmt.bindLong(21, valueOf18.intValue());
                }
                Integer valueOf19 = value.getSuratTidakDikembalikan() == null ? null : Integer.valueOf(value.getSuratTidakDikembalikan().booleanValue() ? 1 : 0);
                if (valueOf19 == null) {
                    stmt.bindNull(22);
                } else {
                    stmt.bindLong(22, valueOf19.intValue());
                }
                Integer valueOf20 = value.getSuratKembaliPPLN() == null ? null : Integer.valueOf(value.getSuratKembaliPPLN().booleanValue() ? 1 : 0);
                if (valueOf20 == null) {
                    stmt.bindNull(23);
                } else {
                    stmt.bindLong(23, valueOf20.intValue());
                }
                Integer valueOf21 = value.getSuratTidakTerpakai() == null ? null : Integer.valueOf(value.getSuratTidakTerpakai().booleanValue() ? 1 : 0);
                if (valueOf21 == null) {
                    stmt.bindNull(24);
                } else {
                    stmt.bindLong(24, valueOf21.intValue());
                }
                Integer valueOf22 = value.getSuratDigunakan() == null ? null : Integer.valueOf(value.getSuratDigunakan().booleanValue() ? 1 : 0);
                if (valueOf22 == null) {
                    stmt.bindNull(25);
                } else {
                    stmt.bindLong(25, valueOf22.intValue());
                }
                Integer valueOf23 = value.getPemilihDisabilitas_L() == null ? null : Integer.valueOf(value.getPemilihDisabilitas_L().booleanValue() ? 1 : 0);
                if (valueOf23 == null) {
                    stmt.bindNull(26);
                } else {
                    stmt.bindLong(26, valueOf23.intValue());
                }
                Integer valueOf24 = value.getPemilihDisabilitas_P() == null ? null : Integer.valueOf(value.getPemilihDisabilitas_P().booleanValue() ? 1 : 0);
                if (valueOf24 == null) {
                    stmt.bindNull(27);
                } else {
                    stmt.bindLong(27, valueOf24.intValue());
                }
                Integer valueOf25 = value.getTotalPemilihDisabilitas() != null ? Integer.valueOf(value.getTotalPemilihDisabilitas().booleanValue() ? 1 : 0) : null;
                if (valueOf25 == null) {
                    stmt.bindNull(28);
                } else {
                    stmt.bindLong(28, valueOf25.intValue());
                }
                if (value.getPemilihDpt_LCorrected() == null) {
                    stmt.bindNull(29);
                } else {
                    stmt.bindLong(29, value.getPemilihDpt_LCorrected().intValue());
                }
                if (value.getPemilihDpt_PCorrected() == null) {
                    stmt.bindNull(30);
                } else {
                    stmt.bindLong(30, value.getPemilihDpt_PCorrected().intValue());
                }
                if (value.getTotalPemilihDptCorrected() == null) {
                    stmt.bindNull(31);
                } else {
                    stmt.bindLong(31, value.getTotalPemilihDptCorrected().intValue());
                }
                if (value.getPenggunaDpt_LCorrected() == null) {
                    stmt.bindNull(32);
                } else {
                    stmt.bindLong(32, value.getPenggunaDpt_LCorrected().intValue());
                }
                if (value.getPenggunaDpt_PCorrected() == null) {
                    stmt.bindNull(33);
                } else {
                    stmt.bindLong(33, value.getPenggunaDpt_PCorrected().intValue());
                }
                if (value.getTotalPenggunaDptCorrected() == null) {
                    stmt.bindNull(34);
                } else {
                    stmt.bindLong(34, value.getTotalPenggunaDptCorrected().intValue());
                }
                if (value.getPenggunaDptb_LCorrected() == null) {
                    stmt.bindNull(35);
                } else {
                    stmt.bindLong(35, value.getPenggunaDptb_LCorrected().intValue());
                }
                if (value.getPenggunaDptb_PCorrected() == null) {
                    stmt.bindNull(36);
                } else {
                    stmt.bindLong(36, value.getPenggunaDptb_PCorrected().intValue());
                }
                if (value.getTotalPenggunaDptbCorrected() == null) {
                    stmt.bindNull(37);
                } else {
                    stmt.bindLong(37, value.getTotalPenggunaDptbCorrected().intValue());
                }
                if (value.getPenggunaDpk_LCorrected() == null) {
                    stmt.bindNull(38);
                } else {
                    stmt.bindLong(38, value.getPenggunaDpk_LCorrected().intValue());
                }
                if (value.getPenggunaDpk_PCorrected() == null) {
                    stmt.bindNull(39);
                } else {
                    stmt.bindLong(39, value.getPenggunaDpk_PCorrected().intValue());
                }
                if (value.getTotalPenggunaDpkCorrected() == null) {
                    stmt.bindNull(40);
                } else {
                    stmt.bindLong(40, value.getTotalPenggunaDpkCorrected().intValue());
                }
                if (value.getTotalPengguna_LCorrected() == null) {
                    stmt.bindNull(41);
                } else {
                    stmt.bindLong(41, value.getTotalPengguna_LCorrected().intValue());
                }
                if (value.getTotalPengguna_PCorrected() == null) {
                    stmt.bindNull(42);
                } else {
                    stmt.bindLong(42, value.getTotalPengguna_PCorrected().intValue());
                }
                if (value.getTotalPenggunaCorrected() == null) {
                    stmt.bindNull(43);
                } else {
                    stmt.bindLong(43, value.getTotalPenggunaCorrected().intValue());
                }
                if (value.getSuratDiterimaCorrected() == null) {
                    stmt.bindNull(44);
                } else {
                    stmt.bindLong(44, value.getSuratDiterimaCorrected().intValue());
                }
                if (value.getSuratDikembalikanCorrected() == null) {
                    stmt.bindNull(45);
                } else {
                    stmt.bindLong(45, value.getSuratDikembalikanCorrected().intValue());
                }
                if (value.getSuratTidakDigunakanCorrected() == null) {
                    stmt.bindNull(46);
                } else {
                    stmt.bindLong(46, value.getSuratTidakDigunakanCorrected().intValue());
                }
                if (value.getSuratTidakDikembalikanCorrected() == null) {
                    stmt.bindNull(47);
                } else {
                    stmt.bindLong(47, value.getSuratTidakDikembalikanCorrected().intValue());
                }
                if (value.getSuratKembaliPPLNCorrected() == null) {
                    stmt.bindNull(48);
                } else {
                    stmt.bindLong(48, value.getSuratKembaliPPLNCorrected().intValue());
                }
                if (value.getSuratTidakTerpakaiCorrected() == null) {
                    stmt.bindNull(49);
                } else {
                    stmt.bindLong(49, value.getSuratTidakTerpakaiCorrected().intValue());
                }
                if (value.getSuratDigunakanCorrected() == null) {
                    stmt.bindNull(50);
                } else {
                    stmt.bindLong(50, value.getSuratDigunakanCorrected().intValue());
                }
                if (value.getPemilihDisabilitas_LCorrected() == null) {
                    stmt.bindNull(51);
                } else {
                    stmt.bindLong(51, value.getPemilihDisabilitas_LCorrected().intValue());
                }
                if (value.getPemilihDisabilitas_PCorrected() == null) {
                    stmt.bindNull(52);
                } else {
                    stmt.bindLong(52, value.getPemilihDisabilitas_PCorrected().intValue());
                }
                if (value.getTotalPemilihDisabilitasCorrected() == null) {
                    stmt.bindNull(53);
                } else {
                    stmt.bindLong(53, value.getTotalPemilihDisabilitasCorrected().intValue());
                }
            }
        };
        this.__insertionAdapterOfFormC1KesesuaianAdministration_1 = new EntityInsertionAdapter<FormC1KesesuaianAdministration>(__db) { // from class: org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrasiDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `form_c1_kesesuaian_administration` (`idImage`,`isLnPos`,`jenisPemilihan`,`pemilihDpt_L`,`pemilihDpt_P`,`totalPemilihDpt`,`penggunaDpt_L`,`penggunaDpt_P`,`totalPenggunaDpt`,`penggunaDptb_L`,`penggunaDptb_P`,`totalPenggunaDptb`,`penggunaDpk_L`,`penggunaDpk_P`,`totalPenggunaDpk`,`totalPengguna_L`,`totalPengguna_P`,`totalPengguna`,`suratDiterima`,`suratDikembalikan`,`suratTidakDigunakan`,`suratTidakDikembalikan`,`suratKembaliPPLN`,`suratTidakTerpakai`,`suratDigunakan`,`pemilihDisabilitas_L`,`pemilihDisabilitas_P`,`totalPemilihDisabilitas`,`pemilihDpt_LCorrected`,`pemilihDpt_PCorrected`,`totalPemilihDptCorrected`,`penggunaDpt_LCorrected`,`penggunaDpt_PCorrected`,`totalPenggunaDptCorrected`,`penggunaDptb_LCorrected`,`penggunaDptb_PCorrected`,`totalPenggunaDptbCorrected`,`penggunaDpk_LCorrected`,`penggunaDpk_PCorrected`,`totalPenggunaDpkCorrected`,`totalPengguna_LCorrected`,`totalPengguna_PCorrected`,`totalPenggunaCorrected`,`suratDiterimaCorrected`,`suratDikembalikanCorrected`,`suratTidakDigunakanCorrected`,`suratTidakDikembalikanCorrected`,`suratKembaliPPLNCorrected`,`suratTidakTerpakaiCorrected`,`suratDigunakanCorrected`,`pemilihDisabilitas_LCorrected`,`pemilihDisabilitas_PCorrected`,`totalPemilihDisabilitasCorrected`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, FormC1KesesuaianAdministration value) {
                if (value.getIdImage() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdImage());
                }
                stmt.bindLong(2, value.isLnPos() ? 1L : 0L);
                if (value.getJenisPemilihan() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getJenisPemilihan());
                }
                Integer valueOf = value.getPemilihDpt_L() == null ? null : Integer.valueOf(value.getPemilihDpt_L().booleanValue() ? 1 : 0);
                if (valueOf == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindLong(4, valueOf.intValue());
                }
                Integer valueOf2 = value.getPemilihDpt_P() == null ? null : Integer.valueOf(value.getPemilihDpt_P().booleanValue() ? 1 : 0);
                if (valueOf2 == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindLong(5, valueOf2.intValue());
                }
                Integer valueOf3 = value.getTotalPemilihDpt() == null ? null : Integer.valueOf(value.getTotalPemilihDpt().booleanValue() ? 1 : 0);
                if (valueOf3 == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindLong(6, valueOf3.intValue());
                }
                Integer valueOf4 = value.getPenggunaDpt_L() == null ? null : Integer.valueOf(value.getPenggunaDpt_L().booleanValue() ? 1 : 0);
                if (valueOf4 == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindLong(7, valueOf4.intValue());
                }
                Integer valueOf5 = value.getPenggunaDpt_P() == null ? null : Integer.valueOf(value.getPenggunaDpt_P().booleanValue() ? 1 : 0);
                if (valueOf5 == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindLong(8, valueOf5.intValue());
                }
                Integer valueOf6 = value.getTotalPenggunaDpt() == null ? null : Integer.valueOf(value.getTotalPenggunaDpt().booleanValue() ? 1 : 0);
                if (valueOf6 == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindLong(9, valueOf6.intValue());
                }
                Integer valueOf7 = value.getPenggunaDptb_L() == null ? null : Integer.valueOf(value.getPenggunaDptb_L().booleanValue() ? 1 : 0);
                if (valueOf7 == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindLong(10, valueOf7.intValue());
                }
                Integer valueOf8 = value.getPenggunaDptb_P() == null ? null : Integer.valueOf(value.getPenggunaDptb_P().booleanValue() ? 1 : 0);
                if (valueOf8 == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindLong(11, valueOf8.intValue());
                }
                Integer valueOf9 = value.getTotalPenggunaDptb() == null ? null : Integer.valueOf(value.getTotalPenggunaDptb().booleanValue() ? 1 : 0);
                if (valueOf9 == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindLong(12, valueOf9.intValue());
                }
                Integer valueOf10 = value.getPenggunaDpk_L() == null ? null : Integer.valueOf(value.getPenggunaDpk_L().booleanValue() ? 1 : 0);
                if (valueOf10 == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindLong(13, valueOf10.intValue());
                }
                Integer valueOf11 = value.getPenggunaDpk_P() == null ? null : Integer.valueOf(value.getPenggunaDpk_P().booleanValue() ? 1 : 0);
                if (valueOf11 == null) {
                    stmt.bindNull(14);
                } else {
                    stmt.bindLong(14, valueOf11.intValue());
                }
                Integer valueOf12 = value.getTotalPenggunaDpk() == null ? null : Integer.valueOf(value.getTotalPenggunaDpk().booleanValue() ? 1 : 0);
                if (valueOf12 == null) {
                    stmt.bindNull(15);
                } else {
                    stmt.bindLong(15, valueOf12.intValue());
                }
                Integer valueOf13 = value.getTotalPengguna_L() == null ? null : Integer.valueOf(value.getTotalPengguna_L().booleanValue() ? 1 : 0);
                if (valueOf13 == null) {
                    stmt.bindNull(16);
                } else {
                    stmt.bindLong(16, valueOf13.intValue());
                }
                Integer valueOf14 = value.getTotalPengguna_P() == null ? null : Integer.valueOf(value.getTotalPengguna_P().booleanValue() ? 1 : 0);
                if (valueOf14 == null) {
                    stmt.bindNull(17);
                } else {
                    stmt.bindLong(17, valueOf14.intValue());
                }
                Integer valueOf15 = value.getTotalPengguna() == null ? null : Integer.valueOf(value.getTotalPengguna().booleanValue() ? 1 : 0);
                if (valueOf15 == null) {
                    stmt.bindNull(18);
                } else {
                    stmt.bindLong(18, valueOf15.intValue());
                }
                Integer valueOf16 = value.getSuratDiterima() == null ? null : Integer.valueOf(value.getSuratDiterima().booleanValue() ? 1 : 0);
                if (valueOf16 == null) {
                    stmt.bindNull(19);
                } else {
                    stmt.bindLong(19, valueOf16.intValue());
                }
                Integer valueOf17 = value.getSuratDikembalikan() == null ? null : Integer.valueOf(value.getSuratDikembalikan().booleanValue() ? 1 : 0);
                if (valueOf17 == null) {
                    stmt.bindNull(20);
                } else {
                    stmt.bindLong(20, valueOf17.intValue());
                }
                Integer valueOf18 = value.getSuratTidakDigunakan() == null ? null : Integer.valueOf(value.getSuratTidakDigunakan().booleanValue() ? 1 : 0);
                if (valueOf18 == null) {
                    stmt.bindNull(21);
                } else {
                    stmt.bindLong(21, valueOf18.intValue());
                }
                Integer valueOf19 = value.getSuratTidakDikembalikan() == null ? null : Integer.valueOf(value.getSuratTidakDikembalikan().booleanValue() ? 1 : 0);
                if (valueOf19 == null) {
                    stmt.bindNull(22);
                } else {
                    stmt.bindLong(22, valueOf19.intValue());
                }
                Integer valueOf20 = value.getSuratKembaliPPLN() == null ? null : Integer.valueOf(value.getSuratKembaliPPLN().booleanValue() ? 1 : 0);
                if (valueOf20 == null) {
                    stmt.bindNull(23);
                } else {
                    stmt.bindLong(23, valueOf20.intValue());
                }
                Integer valueOf21 = value.getSuratTidakTerpakai() == null ? null : Integer.valueOf(value.getSuratTidakTerpakai().booleanValue() ? 1 : 0);
                if (valueOf21 == null) {
                    stmt.bindNull(24);
                } else {
                    stmt.bindLong(24, valueOf21.intValue());
                }
                Integer valueOf22 = value.getSuratDigunakan() == null ? null : Integer.valueOf(value.getSuratDigunakan().booleanValue() ? 1 : 0);
                if (valueOf22 == null) {
                    stmt.bindNull(25);
                } else {
                    stmt.bindLong(25, valueOf22.intValue());
                }
                Integer valueOf23 = value.getPemilihDisabilitas_L() == null ? null : Integer.valueOf(value.getPemilihDisabilitas_L().booleanValue() ? 1 : 0);
                if (valueOf23 == null) {
                    stmt.bindNull(26);
                } else {
                    stmt.bindLong(26, valueOf23.intValue());
                }
                Integer valueOf24 = value.getPemilihDisabilitas_P() == null ? null : Integer.valueOf(value.getPemilihDisabilitas_P().booleanValue() ? 1 : 0);
                if (valueOf24 == null) {
                    stmt.bindNull(27);
                } else {
                    stmt.bindLong(27, valueOf24.intValue());
                }
                Integer valueOf25 = value.getTotalPemilihDisabilitas() != null ? Integer.valueOf(value.getTotalPemilihDisabilitas().booleanValue() ? 1 : 0) : null;
                if (valueOf25 == null) {
                    stmt.bindNull(28);
                } else {
                    stmt.bindLong(28, valueOf25.intValue());
                }
                if (value.getPemilihDpt_LCorrected() == null) {
                    stmt.bindNull(29);
                } else {
                    stmt.bindLong(29, value.getPemilihDpt_LCorrected().intValue());
                }
                if (value.getPemilihDpt_PCorrected() == null) {
                    stmt.bindNull(30);
                } else {
                    stmt.bindLong(30, value.getPemilihDpt_PCorrected().intValue());
                }
                if (value.getTotalPemilihDptCorrected() == null) {
                    stmt.bindNull(31);
                } else {
                    stmt.bindLong(31, value.getTotalPemilihDptCorrected().intValue());
                }
                if (value.getPenggunaDpt_LCorrected() == null) {
                    stmt.bindNull(32);
                } else {
                    stmt.bindLong(32, value.getPenggunaDpt_LCorrected().intValue());
                }
                if (value.getPenggunaDpt_PCorrected() == null) {
                    stmt.bindNull(33);
                } else {
                    stmt.bindLong(33, value.getPenggunaDpt_PCorrected().intValue());
                }
                if (value.getTotalPenggunaDptCorrected() == null) {
                    stmt.bindNull(34);
                } else {
                    stmt.bindLong(34, value.getTotalPenggunaDptCorrected().intValue());
                }
                if (value.getPenggunaDptb_LCorrected() == null) {
                    stmt.bindNull(35);
                } else {
                    stmt.bindLong(35, value.getPenggunaDptb_LCorrected().intValue());
                }
                if (value.getPenggunaDptb_PCorrected() == null) {
                    stmt.bindNull(36);
                } else {
                    stmt.bindLong(36, value.getPenggunaDptb_PCorrected().intValue());
                }
                if (value.getTotalPenggunaDptbCorrected() == null) {
                    stmt.bindNull(37);
                } else {
                    stmt.bindLong(37, value.getTotalPenggunaDptbCorrected().intValue());
                }
                if (value.getPenggunaDpk_LCorrected() == null) {
                    stmt.bindNull(38);
                } else {
                    stmt.bindLong(38, value.getPenggunaDpk_LCorrected().intValue());
                }
                if (value.getPenggunaDpk_PCorrected() == null) {
                    stmt.bindNull(39);
                } else {
                    stmt.bindLong(39, value.getPenggunaDpk_PCorrected().intValue());
                }
                if (value.getTotalPenggunaDpkCorrected() == null) {
                    stmt.bindNull(40);
                } else {
                    stmt.bindLong(40, value.getTotalPenggunaDpkCorrected().intValue());
                }
                if (value.getTotalPengguna_LCorrected() == null) {
                    stmt.bindNull(41);
                } else {
                    stmt.bindLong(41, value.getTotalPengguna_LCorrected().intValue());
                }
                if (value.getTotalPengguna_PCorrected() == null) {
                    stmt.bindNull(42);
                } else {
                    stmt.bindLong(42, value.getTotalPengguna_PCorrected().intValue());
                }
                if (value.getTotalPenggunaCorrected() == null) {
                    stmt.bindNull(43);
                } else {
                    stmt.bindLong(43, value.getTotalPenggunaCorrected().intValue());
                }
                if (value.getSuratDiterimaCorrected() == null) {
                    stmt.bindNull(44);
                } else {
                    stmt.bindLong(44, value.getSuratDiterimaCorrected().intValue());
                }
                if (value.getSuratDikembalikanCorrected() == null) {
                    stmt.bindNull(45);
                } else {
                    stmt.bindLong(45, value.getSuratDikembalikanCorrected().intValue());
                }
                if (value.getSuratTidakDigunakanCorrected() == null) {
                    stmt.bindNull(46);
                } else {
                    stmt.bindLong(46, value.getSuratTidakDigunakanCorrected().intValue());
                }
                if (value.getSuratTidakDikembalikanCorrected() == null) {
                    stmt.bindNull(47);
                } else {
                    stmt.bindLong(47, value.getSuratTidakDikembalikanCorrected().intValue());
                }
                if (value.getSuratKembaliPPLNCorrected() == null) {
                    stmt.bindNull(48);
                } else {
                    stmt.bindLong(48, value.getSuratKembaliPPLNCorrected().intValue());
                }
                if (value.getSuratTidakTerpakaiCorrected() == null) {
                    stmt.bindNull(49);
                } else {
                    stmt.bindLong(49, value.getSuratTidakTerpakaiCorrected().intValue());
                }
                if (value.getSuratDigunakanCorrected() == null) {
                    stmt.bindNull(50);
                } else {
                    stmt.bindLong(50, value.getSuratDigunakanCorrected().intValue());
                }
                if (value.getPemilihDisabilitas_LCorrected() == null) {
                    stmt.bindNull(51);
                } else {
                    stmt.bindLong(51, value.getPemilihDisabilitas_LCorrected().intValue());
                }
                if (value.getPemilihDisabilitas_PCorrected() == null) {
                    stmt.bindNull(52);
                } else {
                    stmt.bindLong(52, value.getPemilihDisabilitas_PCorrected().intValue());
                }
                if (value.getTotalPemilihDisabilitasCorrected() == null) {
                    stmt.bindNull(53);
                } else {
                    stmt.bindLong(53, value.getTotalPemilihDisabilitasCorrected().intValue());
                }
            }
        };
        this.__preparedStmtOfDeleteBy = new SharedSQLiteStatement(__db) { // from class: org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrasiDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM form_c1_kesesuaian_administration WHERE idImage = ?";
            }
        };
    }

    @Override // org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrasiDao
    public void insertAllReplace(final List<FormC1KesesuaianAdministration> kesesuaian) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFormC1KesesuaianAdministration.insert(kesesuaian);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrasiDao
    public void insertAll(final List<FormC1KesesuaianAdministration> kesesuaian) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFormC1KesesuaianAdministration_1.insert(kesesuaian);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrasiDao
    public void deleteBy(final String idImage) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteBy.acquire();
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
            this.__preparedStmtOfDeleteBy.release(acquire);
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
