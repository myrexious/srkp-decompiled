package org.informatika.sirekap.ui.witness.register;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.Witness;
import org.informatika.sirekap.repository.WitnessRepository;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.FormUtil;

/* compiled from: WitnessRegisterFormState.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\bM\n\u0002\u0010$\n\u0002\bF\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010±\u0001\u001a\u00030²\u00012\u0007\u0010³\u0001\u001a\u00020\u0003Jj\u0010´\u0001\u001a\u00020\u00062\u000b\b\u0002\u0010µ\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010¶\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010·\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010¸\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010¹\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010º\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010»\u0001\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0003\u0010¼\u0001J\n\u0010½\u0001\u001a\u00030¾\u0001H\u0002R&\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R&\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0004R&\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\t\"\u0004\b\u0018\u0010\u000bR\u001c\u0010\u0019\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\t\"\u0004\b\u001b\u0010\u000bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010\u0004R&\u0010!\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\t\"\u0004\b#\u0010\u000bR\u001c\u0010$\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\t\"\u0004\b&\u0010\u000bR\u000e\u0010'\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010(\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0014\"\u0004\b*\u0010\u0004R&\u0010+\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\t\"\u0004\b-\u0010\u000bR\u001c\u0010.\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\t\"\u0004\b0\u0010\u000bR\u000e\u00101\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R&\u00102\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0014\"\u0004\b4\u0010\u0004R&\u00105\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\t\"\u0004\b7\u0010\u000bR\u001c\u00108\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\t\"\u0004\b:\u0010\u000bR\u000e\u0010;\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010<\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0014\"\u0004\b>\u0010\u0004R&\u0010?\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\t\"\u0004\bA\u0010\u000bR\u001c\u0010B\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\t\"\u0004\bD\u0010\u000bR\u000e\u0010E\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010F\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u0014\"\u0004\bH\u0010\u0004R&\u0010I\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\t\"\u0004\bK\u0010\u000bR\u001c\u0010L\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\t\"\u0004\bN\u0010\u000bR\u000e\u0010O\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010P\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010\u0014\"\u0004\bR\u0010\u0004R&\u0010S\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010\t\"\u0004\bU\u0010\u000bR\u001c\u0010V\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010\t\"\u0004\bX\u0010\u000bR\u000e\u0010Y\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010Z\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010\t\"\u0004\b[\u0010\u000bR&\u0010\\\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010\t\"\u0004\b]\u0010\u000bR&\u0010^\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010\t\"\u0004\b_\u0010\u000bR&\u0010`\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010\t\"\u0004\ba\u0010\u000bR&\u0010b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010\t\"\u0004\bc\u0010\u000bR&\u0010d\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010\t\"\u0004\be\u0010\u000bR&\u0010f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010\t\"\u0004\bg\u0010\u000bR&\u0010h\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010\u0014\"\u0004\bj\u0010\u0004R>\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030k2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030k8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010n\"\u0004\bo\u0010pR\u001c\u0010q\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010\t\"\u0004\bs\u0010\u000bR\u001c\u0010t\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bu\u0010\t\"\u0004\bv\u0010\u000bR&\u0010w\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010\u0014\"\u0004\by\u0010\u0004R\u001c\u0010z\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b{\u0010\t\"\u0004\b|\u0010\u000bR\u001c\u0010}\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b~\u0010\t\"\u0004\b\u007f\u0010\u000bR\u0012\u0010\u0002\u001a\u00020\u0003¢\u0006\t\n\u0000\u001a\u0005\b\u0080\u0001\u0010\u0014R)\u0010\u0081\u0001\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038G@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010\u0014\"\u0005\b\u0083\u0001\u0010\u0004R\u001f\u0010\u0084\u0001\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0085\u0001\u0010\t\"\u0005\b\u0086\u0001\u0010\u000bR\u001f\u0010\u0087\u0001\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0088\u0001\u0010\t\"\u0005\b\u0089\u0001\u0010\u000bR)\u0010\u008a\u0001\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038G@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008b\u0001\u0010\u0014\"\u0005\b\u008c\u0001\u0010\u0004R\u001f\u0010\u008d\u0001\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008e\u0001\u0010\t\"\u0005\b\u008f\u0001\u0010\u000bR\u001f\u0010\u0090\u0001\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0091\u0001\u0010\t\"\u0005\b\u0092\u0001\u0010\u000bR)\u0010\u0093\u0001\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038G@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0094\u0001\u0010\u0014\"\u0005\b\u0095\u0001\u0010\u0004R\u001f\u0010\u0096\u0001\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0097\u0001\u0010\t\"\u0005\b\u0098\u0001\u0010\u000bR\u001f\u0010\u0099\u0001\u001a\u00020\u00068GX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009a\u0001\u0010\t\"\u0005\b\u009b\u0001\u0010\u000bR)\u0010\u009c\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001d0kX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009d\u0001\u0010n\"\u0005\b\u009e\u0001\u0010pR)\u0010\u009f\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001d0kX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b \u0001\u0010n\"\u0005\b¡\u0001\u0010pR)\u0010¢\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001d0kX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b£\u0001\u0010n\"\u0005\b¤\u0001\u0010pR)\u0010¥\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001d0kX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¦\u0001\u0010n\"\u0005\b§\u0001\u0010pR)\u0010¨\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001d0kX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b©\u0001\u0010n\"\u0005\bª\u0001\u0010pR)\u0010«\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001d0kX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¬\u0001\u0010n\"\u0005\b\u00ad\u0001\u0010pR)\u0010®\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001d0kX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¯\u0001\u0010n\"\u0005\b°\u0001\u0010p¨\u0006¿\u0001"}, d2 = {"Lorg/informatika/sirekap/ui/witness/register/WitnessRegisterFormState;", "Landroidx/databinding/BaseObservable;", "kodeTps", "", "(Ljava/lang/String;)V", "value", "", "allValid", "getAllValid", "()Z", "setAllValid", "(Z)V", "electionTypes", "", "getElectionTypes", "()Ljava/util/List;", "setElectionTypes", "(Ljava/util/List;)V", "idPartaiDpr", "getIdPartaiDpr", "()Ljava/lang/String;", "setIdPartaiDpr", "idPartaiDprTouched", "getIdPartaiDprTouched", "setIdPartaiDprTouched", "idPartaiDprValid", "getIdPartaiDprValid", "setIdPartaiDprValid", "idPartaiDprValue", "", "idPartaiDprdk", "getIdPartaiDprdk", "setIdPartaiDprdk", "idPartaiDprdkTouched", "getIdPartaiDprdkTouched", "setIdPartaiDprdkTouched", "idPartaiDprdkValid", "getIdPartaiDprdkValid", "setIdPartaiDprdkValid", "idPartaiDprdkValue", "idPartaiDprdp", "getIdPartaiDprdp", "setIdPartaiDprdp", "idPartaiDprdpTouched", "getIdPartaiDprdpTouched", "setIdPartaiDprdpTouched", "idPartaiDprdpValid", "getIdPartaiDprdpValid", "setIdPartaiDprdpValid", "idPartaiDprdpValue", "idPaslonDpd", "getIdPaslonDpd", "setIdPaslonDpd", "idPaslonDpdTouched", "getIdPaslonDpdTouched", "setIdPaslonDpdTouched", "idPaslonDpdValid", "getIdPaslonDpdValid", "setIdPaslonDpdValid", "idPaslonDpdValue", "idPaslonPilgub", "getIdPaslonPilgub", "setIdPaslonPilgub", "idPaslonPilgubTouched", "getIdPaslonPilgubTouched", "setIdPaslonPilgubTouched", "idPaslonPilgubValid", "getIdPaslonPilgubValid", "setIdPaslonPilgubValid", "idPaslonPilgubValue", "idPaslonPilwalkot", "getIdPaslonPilwalkot", "setIdPaslonPilwalkot", "idPaslonPilwalkotTouched", "getIdPaslonPilwalkotTouched", "setIdPaslonPilwalkotTouched", "idPaslonPilwalkotValid", "getIdPaslonPilwalkotValid", "setIdPaslonPilwalkotValid", "idPaslonPilwalkotValue", "idPaslonPresiden", "getIdPaslonPresiden", "setIdPaslonPresiden", "idPaslonPresidenTouched", "getIdPaslonPresidenTouched", "setIdPaslonPresidenTouched", "idPaslonPresidenValid", "getIdPaslonPresidenValid", "setIdPaslonPresidenValid", "idPaslonPresidenValue", "isJenisPemilihanDpd", "setJenisPemilihanDpd", "isJenisPemilihanDpr", "setJenisPemilihanDpr", "isJenisPemilihanDprdk", "setJenisPemilihanDprdk", "isJenisPemilihanDprdp", "setJenisPemilihanDprdp", "isJenisPemilihanPkwkk", "setJenisPemilihanPkwkk", "isJenisPemilihanPkwkp", "setJenisPemilihanPkwkp", "isJenisPemilihanPresiden", "setJenisPemilihanPresiden", "jenisPemeriksa", "getJenisPemeriksa", "setJenisPemeriksa", "", "jenisPemeriksaOptions", "getJenisPemeriksaOptions", "()Ljava/util/Map;", "setJenisPemeriksaOptions", "(Ljava/util/Map;)V", "jenisPemeriksaTouched", "getJenisPemeriksaTouched", "setJenisPemeriksaTouched", "jenisPemeriksaValid", "getJenisPemeriksaValid", "setJenisPemeriksaValid", "jenisPemeriksaValue", "getJenisPemeriksaValue", "setJenisPemeriksaValue", "jenisPemilihanTouched", "getJenisPemilihanTouched", "setJenisPemilihanTouched", "jenisPemilihanValid", "getJenisPemilihanValid", "setJenisPemilihanValid", "getKodeTps", "name", "getName", "setName", "nameTouched", "getNameTouched", "setNameTouched", "nameValid", "getNameValid", "setNameValid", "nik", "getNik", "setNik", "nikTouched", "getNikTouched", "setNikTouched", "nikValid", "getNikValid", "setNikValid", "noHandphone", "getNoHandphone", "setNoHandphone", "noHandphoneTouched", "getNoHandphoneTouched", "setNoHandphoneTouched", "noHandphoneValid", "getNoHandphoneValid", "setNoHandphoneValid", "partaiDprOptions", "getPartaiDprOptions", "setPartaiDprOptions", "partaiDprdkOptions", "getPartaiDprdkOptions", "setPartaiDprdkOptions", "partaiDprdpOptions", "getPartaiDprdpOptions", "setPartaiDprdpOptions", "paslonDpdOptions", "getPaslonDpdOptions", "setPaslonDpdOptions", "paslonPilgubOptions", "getPaslonPilgubOptions", "setPaslonPilgubOptions", "paslonPilwalkotOptions", "getPaslonPilwalkotOptions", "setPaslonPilwalkotOptions", "paslonPresidenOptions", "getPaslonPresidenOptions", "setPaslonPresidenOptions", "toAddWitnessModels", "Lorg/informatika/sirekap/repository/WitnessRepository$AddWitnessModel;", "profile", "validateJenisPemilihan", "newIsJenisPemilihanPresiden", "newIsJenisPemilihanPkwkp", "newIsJenisPemilihanPkwkk", "newIsJenisPemilihanDpr", "newIsJenisPemilihanDprdp", "newIsJenisPemilihanDprdk", "newIsJenisPemilihanDpd", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)Z", "verifyAllForms", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WitnessRegisterFormState extends BaseObservable {
    private boolean allValid;
    private List<String> electionTypes;
    private String idPartaiDpr;
    private boolean idPartaiDprTouched;
    private boolean idPartaiDprValid;
    private long idPartaiDprValue;
    private String idPartaiDprdk;
    private boolean idPartaiDprdkTouched;
    private boolean idPartaiDprdkValid;
    private long idPartaiDprdkValue;
    private String idPartaiDprdp;
    private boolean idPartaiDprdpTouched;
    private boolean idPartaiDprdpValid;
    private long idPartaiDprdpValue;
    private String idPaslonDpd;
    private boolean idPaslonDpdTouched;
    private boolean idPaslonDpdValid;
    private long idPaslonDpdValue;
    private String idPaslonPilgub;
    private boolean idPaslonPilgubTouched;
    private boolean idPaslonPilgubValid;
    private long idPaslonPilgubValue;
    private String idPaslonPilwalkot;
    private boolean idPaslonPilwalkotTouched;
    private boolean idPaslonPilwalkotValid;
    private long idPaslonPilwalkotValue;
    private String idPaslonPresiden;
    private boolean idPaslonPresidenTouched;
    private boolean idPaslonPresidenValid;
    private long idPaslonPresidenValue;
    private boolean isJenisPemilihanDpd;
    private boolean isJenisPemilihanDpr;
    private boolean isJenisPemilihanDprdk;
    private boolean isJenisPemilihanDprdp;
    private boolean isJenisPemilihanPkwkk;
    private boolean isJenisPemilihanPkwkp;
    private boolean isJenisPemilihanPresiden;
    private String jenisPemeriksa;
    private Map<String, String> jenisPemeriksaOptions;
    private boolean jenisPemeriksaTouched;
    private boolean jenisPemeriksaValid;
    private String jenisPemeriksaValue;
    private boolean jenisPemilihanTouched;
    private boolean jenisPemilihanValid;
    private final String kodeTps;
    private String name;
    private boolean nameTouched;
    private boolean nameValid;
    private String nik;
    private boolean nikTouched;
    private boolean nikValid;
    private String noHandphone;
    private boolean noHandphoneTouched;
    private boolean noHandphoneValid;
    private Map<String, Long> partaiDprOptions;
    private Map<String, Long> partaiDprdkOptions;
    private Map<String, Long> partaiDprdpOptions;
    private Map<String, Long> paslonDpdOptions;
    private Map<String, Long> paslonPilgubOptions;
    private Map<String, Long> paslonPilwalkotOptions;
    private Map<String, Long> paslonPresidenOptions;

    public final String getKodeTps() {
        return this.kodeTps;
    }

    public WitnessRegisterFormState(String kodeTps) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        this.kodeTps = kodeTps;
        this.jenisPemeriksaOptions = new LinkedHashMap();
        this.paslonPresidenOptions = MapsKt.emptyMap();
        this.paslonPilgubOptions = MapsKt.emptyMap();
        this.paslonPilwalkotOptions = MapsKt.emptyMap();
        this.partaiDprOptions = MapsKt.emptyMap();
        this.partaiDprdpOptions = MapsKt.emptyMap();
        this.partaiDprdkOptions = MapsKt.emptyMap();
        this.paslonDpdOptions = MapsKt.emptyMap();
        this.electionTypes = new ArrayList();
        this.name = "";
        this.nik = "";
        this.noHandphone = "";
        this.jenisPemeriksaValue = "";
        this.jenisPemeriksa = "";
        this.idPaslonPresiden = "";
        this.idPaslonPilgub = "";
        this.idPaslonPilwalkot = "";
        this.idPartaiDpr = "";
        this.idPartaiDprdp = "";
        this.idPartaiDprdk = "";
        this.idPaslonDpd = "";
    }

    @Bindable
    public final Map<String, String> getJenisPemeriksaOptions() {
        return this.jenisPemeriksaOptions;
    }

    public final void setJenisPemeriksaOptions(Map<String, String> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.jenisPemeriksaOptions = value;
        notifyPropertyChanged(50);
    }

    public final Map<String, Long> getPaslonPresidenOptions() {
        return this.paslonPresidenOptions;
    }

    public final void setPaslonPresidenOptions(Map<String, Long> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.paslonPresidenOptions = map;
    }

    public final Map<String, Long> getPaslonPilgubOptions() {
        return this.paslonPilgubOptions;
    }

    public final void setPaslonPilgubOptions(Map<String, Long> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.paslonPilgubOptions = map;
    }

    public final Map<String, Long> getPaslonPilwalkotOptions() {
        return this.paslonPilwalkotOptions;
    }

    public final void setPaslonPilwalkotOptions(Map<String, Long> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.paslonPilwalkotOptions = map;
    }

    public final Map<String, Long> getPartaiDprOptions() {
        return this.partaiDprOptions;
    }

    public final void setPartaiDprOptions(Map<String, Long> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.partaiDprOptions = map;
    }

    public final Map<String, Long> getPartaiDprdpOptions() {
        return this.partaiDprdpOptions;
    }

    public final void setPartaiDprdpOptions(Map<String, Long> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.partaiDprdpOptions = map;
    }

    public final Map<String, Long> getPartaiDprdkOptions() {
        return this.partaiDprdkOptions;
    }

    public final void setPartaiDprdkOptions(Map<String, Long> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.partaiDprdkOptions = map;
    }

    public final Map<String, Long> getPaslonDpdOptions() {
        return this.paslonDpdOptions;
    }

    public final void setPaslonDpdOptions(Map<String, Long> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.paslonDpdOptions = map;
    }

    public final List<String> getElectionTypes() {
        return this.electionTypes;
    }

    public final void setElectionTypes(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.electionTypes = list;
    }

    @Bindable
    public final String getName() {
        return this.name;
    }

    public final void setName(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.name = value;
        this.nameValid = !StringsKt.isBlank(value);
        verifyAllForms();
        notifyPropertyChanged(66);
        notifyPropertyChanged(68);
        if (this.nameTouched) {
            return;
        }
        this.nameTouched = true;
        notifyPropertyChanged(67);
    }

    @Bindable
    public final boolean getNameTouched() {
        return this.nameTouched;
    }

    public final void setNameTouched(boolean z) {
        this.nameTouched = z;
    }

    @Bindable
    public final boolean getNameValid() {
        return this.nameValid;
    }

    public final void setNameValid(boolean z) {
        this.nameValid = z;
    }

    @Bindable
    public final String getNik() {
        return this.nik;
    }

    public final void setNik(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.nik = value;
        this.nikValid = FormUtil.Companion.isValidNik(value);
        verifyAllForms();
        notifyPropertyChanged(69);
        notifyPropertyChanged(71);
        if (this.nikTouched) {
            return;
        }
        this.nikTouched = true;
        notifyPropertyChanged(70);
    }

    @Bindable
    public final boolean getNikTouched() {
        return this.nikTouched;
    }

    public final void setNikTouched(boolean z) {
        this.nikTouched = z;
    }

    @Bindable
    public final boolean getNikValid() {
        return this.nikValid;
    }

    public final void setNikValid(boolean z) {
        this.nikValid = z;
    }

    @Bindable
    public final String getNoHandphone() {
        return this.noHandphone;
    }

    public final void setNoHandphone(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.noHandphone = value;
        this.noHandphoneValid = FormUtil.Companion.isValidPhoneNumber(value);
        verifyAllForms();
        notifyPropertyChanged(72);
        notifyPropertyChanged(74);
        if (this.noHandphoneTouched) {
            return;
        }
        this.noHandphoneTouched = true;
        notifyPropertyChanged(73);
    }

    @Bindable
    public final boolean getNoHandphoneTouched() {
        return this.noHandphoneTouched;
    }

    public final void setNoHandphoneTouched(boolean z) {
        this.noHandphoneTouched = z;
    }

    @Bindable
    public final boolean getNoHandphoneValid() {
        return this.noHandphoneValid;
    }

    public final void setNoHandphoneValid(boolean z) {
        this.noHandphoneValid = z;
    }

    @Bindable
    public final String getJenisPemeriksaValue() {
        return this.jenisPemeriksaValue;
    }

    public final void setJenisPemeriksaValue(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.jenisPemeriksaValue = value;
        notifyPropertyChanged(53);
        setIdPaslonPresiden(!Intrinsics.areEqual(value, Witness.WITNESS_TYPE_SAKSI) ? "" : this.idPaslonPresiden);
        setIdPaslonPilgub(!Intrinsics.areEqual(value, Witness.WITNESS_TYPE_SAKSI) ? "" : this.idPaslonPilgub);
        setIdPaslonPilwalkot(!Intrinsics.areEqual(value, Witness.WITNESS_TYPE_SAKSI) ? "" : this.idPaslonPilwalkot);
        setIdPartaiDpr(!Intrinsics.areEqual(value, Witness.WITNESS_TYPE_SAKSI) ? "" : this.idPartaiDpr);
        setIdPartaiDprdp(!Intrinsics.areEqual(value, Witness.WITNESS_TYPE_SAKSI) ? "" : this.idPartaiDprdp);
        setIdPartaiDprdk(!Intrinsics.areEqual(value, Witness.WITNESS_TYPE_SAKSI) ? "" : this.idPartaiDprdk);
        setIdPaslonDpd(Intrinsics.areEqual(value, Witness.WITNESS_TYPE_SAKSI) ? this.idPaslonDpd : "");
        setIdPaslonPresidenTouched(false);
        setIdPaslonPilgubTouched(false);
        setIdPaslonPilwalkotTouched(false);
        setIdPartaiDprTouched(false);
        setIdPartaiDprdpTouched(false);
        setIdPartaiDprdkTouched(false);
        setIdPaslonDpdTouched(false);
    }

    @Bindable
    public final String getJenisPemeriksa() {
        return this.jenisPemeriksa;
    }

    public final void setJenisPemeriksa(String value) {
        Object obj;
        Intrinsics.checkNotNullParameter(value, "value");
        this.jenisPemeriksa = value;
        this.jenisPemeriksaValid = this.jenisPemeriksaOptions.keySet().contains(value);
        Iterator<T> it = this.jenisPemeriksaOptions.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((Map.Entry) obj).getKey(), value)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        setJenisPemeriksaValue((entry == null || (r11 = (String) entry.getValue()) == null) ? "" : "");
        this.jenisPemilihanValid = validateJenisPemilihan$default(this, null, null, null, null, null, null, null, 127, null);
        verifyAllForms();
        notifyPropertyChanged(49);
        notifyPropertyChanged(52);
        notifyPropertyChanged(62);
        if (this.jenisPemeriksaTouched) {
            return;
        }
        this.jenisPemeriksaTouched = true;
        notifyPropertyChanged(51);
    }

    @Bindable
    public final boolean getJenisPemeriksaTouched() {
        return this.jenisPemeriksaTouched;
    }

    public final void setJenisPemeriksaTouched(boolean z) {
        this.jenisPemeriksaTouched = z;
    }

    @Bindable
    public final boolean getJenisPemeriksaValid() {
        return this.jenisPemeriksaValid;
    }

    public final void setJenisPemeriksaValid(boolean z) {
        this.jenisPemeriksaValid = z;
    }

    static /* synthetic */ boolean validateJenisPemilihan$default(WitnessRegisterFormState witnessRegisterFormState, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = null;
        }
        if ((i & 2) != 0) {
            bool2 = null;
        }
        if ((i & 4) != 0) {
            bool3 = null;
        }
        if ((i & 8) != 0) {
            bool4 = null;
        }
        if ((i & 16) != 0) {
            bool5 = null;
        }
        if ((i & 32) != 0) {
            bool6 = null;
        }
        if ((i & 64) != 0) {
            bool7 = null;
        }
        return witnessRegisterFormState.validateJenisPemilihan(bool, bool2, bool3, bool4, bool5, bool6, bool7);
    }

    private final boolean validateJenisPemilihan(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7) {
        if (!(bool != null ? bool.booleanValue() : this.isJenisPemilihanPresiden)) {
            if (!(bool2 != null ? bool2.booleanValue() : this.isJenisPemilihanPkwkp)) {
                if (!(bool3 != null ? bool3.booleanValue() : this.isJenisPemilihanPkwkk)) {
                    if (!(bool4 != null ? bool4.booleanValue() : this.isJenisPemilihanDpr)) {
                        if (!(bool5 != null ? bool5.booleanValue() : this.isJenisPemilihanDprdp)) {
                            if (!(bool6 != null ? bool6.booleanValue() : this.isJenisPemilihanDprdk)) {
                                if (!(bool7 != null ? bool7.booleanValue() : this.isJenisPemilihanDpd) && !Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_PPS) && !Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_PANWAS)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Bindable
    public final boolean isJenisPemilihanPresiden() {
        return this.isJenisPemilihanPresiden;
    }

    public final void setJenisPemilihanPresiden(boolean z) {
        this.isJenisPemilihanPresiden = z;
        this.jenisPemilihanValid = validateJenisPemilihan$default(this, Boolean.valueOf(z), null, null, null, null, null, null, 126, null);
        verifyAllForms();
        notifyPropertyChanged(60);
        notifyPropertyChanged(62);
        if (!this.jenisPemilihanTouched) {
            this.jenisPemilihanTouched = true;
            notifyPropertyChanged(61);
        }
        setIdPaslonPresiden(!z ? "" : this.idPaslonPresiden);
        setIdPaslonPresidenTouched(false);
    }

    @Bindable
    public final boolean isJenisPemilihanPkwkk() {
        return this.isJenisPemilihanPkwkk;
    }

    public final void setJenisPemilihanPkwkk(boolean z) {
        this.isJenisPemilihanPkwkk = z;
        this.jenisPemilihanValid = validateJenisPemilihan$default(this, null, null, Boolean.valueOf(z), null, null, null, null, 123, null);
        verifyAllForms();
        notifyPropertyChanged(58);
        notifyPropertyChanged(62);
        if (!this.jenisPemilihanTouched) {
            this.jenisPemilihanTouched = true;
            notifyPropertyChanged(61);
        }
        setIdPaslonPilwalkot(!z ? "" : this.idPaslonPilwalkot);
        setIdPaslonPilwalkotTouched(false);
    }

    @Bindable
    public final boolean isJenisPemilihanPkwkp() {
        return this.isJenisPemilihanPkwkp;
    }

    public final void setJenisPemilihanPkwkp(boolean z) {
        this.isJenisPemilihanPkwkp = z;
        this.jenisPemilihanValid = validateJenisPemilihan$default(this, null, Boolean.valueOf(z), null, null, null, null, null, 125, null);
        verifyAllForms();
        notifyPropertyChanged(59);
        notifyPropertyChanged(62);
        if (!this.jenisPemilihanTouched) {
            this.jenisPemilihanTouched = true;
            notifyPropertyChanged(61);
        }
        setIdPaslonPilgub(!z ? "" : this.idPaslonPilgub);
        setIdPaslonPilgubTouched(false);
    }

    @Bindable
    public final boolean isJenisPemilihanDpr() {
        return this.isJenisPemilihanDpr;
    }

    public final void setJenisPemilihanDpr(boolean z) {
        this.isJenisPemilihanDpr = z;
        this.jenisPemilihanValid = validateJenisPemilihan$default(this, null, null, null, Boolean.valueOf(z), null, null, null, 119, null);
        verifyAllForms();
        notifyPropertyChanged(55);
        notifyPropertyChanged(62);
        if (!this.jenisPemilihanTouched) {
            this.jenisPemilihanTouched = true;
            notifyPropertyChanged(61);
        }
        setIdPartaiDpr(!z ? "" : this.idPartaiDpr);
        setIdPartaiDprTouched(false);
    }

    @Bindable
    public final boolean isJenisPemilihanDprdp() {
        return this.isJenisPemilihanDprdp;
    }

    public final void setJenisPemilihanDprdp(boolean z) {
        this.isJenisPemilihanDprdp = z;
        this.jenisPemilihanValid = validateJenisPemilihan$default(this, null, null, null, null, Boolean.valueOf(z), null, null, 111, null);
        verifyAllForms();
        notifyPropertyChanged(57);
        notifyPropertyChanged(62);
        if (!this.jenisPemilihanTouched) {
            this.jenisPemilihanTouched = true;
            notifyPropertyChanged(61);
        }
        setIdPartaiDprdp(!z ? "" : this.idPartaiDprdp);
        setIdPartaiDprdpTouched(false);
    }

    @Bindable
    public final boolean isJenisPemilihanDprdk() {
        return this.isJenisPemilihanDprdk;
    }

    public final void setJenisPemilihanDprdk(boolean z) {
        this.isJenisPemilihanDprdk = z;
        this.jenisPemilihanValid = validateJenisPemilihan$default(this, null, null, null, null, null, Boolean.valueOf(z), null, 95, null);
        verifyAllForms();
        notifyPropertyChanged(56);
        notifyPropertyChanged(62);
        if (!this.jenisPemilihanTouched) {
            this.jenisPemilihanTouched = true;
            notifyPropertyChanged(61);
        }
        setIdPartaiDprdk(!z ? "" : this.idPartaiDprdk);
        setIdPartaiDprdkTouched(false);
    }

    @Bindable
    public final boolean isJenisPemilihanDpd() {
        return this.isJenisPemilihanDpd;
    }

    public final void setJenisPemilihanDpd(boolean z) {
        this.isJenisPemilihanDpd = z;
        this.jenisPemilihanValid = validateJenisPemilihan$default(this, null, null, null, null, null, null, Boolean.valueOf(z), 63, null);
        verifyAllForms();
        notifyPropertyChanged(54);
        notifyPropertyChanged(62);
        if (!this.jenisPemilihanTouched) {
            this.jenisPemilihanTouched = true;
            notifyPropertyChanged(61);
        }
        setIdPaslonDpd(!z ? "" : this.idPaslonDpd);
        setIdPaslonDpdTouched(false);
    }

    @Bindable
    public final boolean getJenisPemilihanTouched() {
        return this.jenisPemilihanTouched;
    }

    public final void setJenisPemilihanTouched(boolean z) {
        this.jenisPemilihanTouched = z;
    }

    @Bindable
    public final boolean getJenisPemilihanValid() {
        return this.jenisPemilihanValid;
    }

    public final void setJenisPemilihanValid(boolean z) {
        this.jenisPemilihanValid = z;
    }

    @Bindable
    public final String getIdPaslonPresiden() {
        return this.idPaslonPresiden;
    }

    public final void setIdPaslonPresiden(String value) {
        Object obj;
        Intrinsics.checkNotNullParameter(value, "value");
        this.idPaslonPresiden = value;
        this.idPaslonPresidenValid = !Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI) || this.paslonPresidenOptions.keySet().contains(value);
        Iterator<T> it = this.paslonPresidenOptions.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((Map.Entry) obj).getKey(), value)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        this.idPaslonPresidenValue = entry != null ? ((Number) entry.getValue()).longValue() : 0L;
        verifyAllForms();
        notifyPropertyChanged(40);
        notifyPropertyChanged(42);
        if (this.idPaslonPresidenTouched) {
            return;
        }
        setIdPaslonPresidenTouched(true);
    }

    @Bindable
    public final String getIdPaslonPilgub() {
        return this.idPaslonPilgub;
    }

    public final void setIdPaslonPilgub(String value) {
        Object obj;
        Intrinsics.checkNotNullParameter(value, "value");
        this.idPaslonPilgub = value;
        this.idPaslonPilgubValid = !Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI) || this.paslonPilgubOptions.keySet().contains(value);
        Iterator<T> it = this.paslonPilgubOptions.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((Map.Entry) obj).getKey(), value)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        this.idPaslonPilgubValue = entry != null ? ((Number) entry.getValue()).longValue() : 0L;
        verifyAllForms();
        notifyPropertyChanged(34);
        notifyPropertyChanged(36);
        if (this.idPaslonPilgubTouched) {
            return;
        }
        setIdPaslonPilgubTouched(true);
    }

    @Bindable
    public final String getIdPaslonPilwalkot() {
        return this.idPaslonPilwalkot;
    }

    public final void setIdPaslonPilwalkot(String value) {
        Object obj;
        Intrinsics.checkNotNullParameter(value, "value");
        this.idPaslonPilwalkot = value;
        this.idPaslonPilwalkotValid = !Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI) || this.paslonPilwalkotOptions.keySet().contains(value);
        Iterator<T> it = this.paslonPilwalkotOptions.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((Map.Entry) obj).getKey(), value)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        this.idPaslonPilwalkotValue = entry != null ? ((Number) entry.getValue()).longValue() : 0L;
        verifyAllForms();
        notifyPropertyChanged(37);
        notifyPropertyChanged(39);
        if (this.idPaslonPilwalkotTouched) {
            return;
        }
        setIdPaslonPilwalkotTouched(true);
    }

    @Bindable
    public final String getIdPartaiDpr() {
        return this.idPartaiDpr;
    }

    public final void setIdPartaiDpr(String value) {
        Object obj;
        Intrinsics.checkNotNullParameter(value, "value");
        this.idPartaiDpr = value;
        this.idPartaiDprValid = !Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI) || this.partaiDprOptions.keySet().contains(value);
        Iterator<T> it = this.partaiDprOptions.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((Map.Entry) obj).getKey(), value)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        this.idPartaiDprValue = entry != null ? ((Number) entry.getValue()).longValue() : 0L;
        verifyAllForms();
        notifyPropertyChanged(22);
        notifyPropertyChanged(24);
        if (this.idPartaiDprTouched) {
            return;
        }
        setIdPartaiDprTouched(true);
    }

    @Bindable
    public final String getIdPartaiDprdp() {
        return this.idPartaiDprdp;
    }

    public final void setIdPartaiDprdp(String value) {
        Object obj;
        Intrinsics.checkNotNullParameter(value, "value");
        this.idPartaiDprdp = value;
        this.idPartaiDprdpValid = !Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI) || this.partaiDprdpOptions.keySet().contains(value);
        Iterator<T> it = this.partaiDprdpOptions.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((Map.Entry) obj).getKey(), value)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        this.idPartaiDprdpValue = entry != null ? ((Number) entry.getValue()).longValue() : 0L;
        verifyAllForms();
        notifyPropertyChanged(28);
        notifyPropertyChanged(30);
        if (this.idPartaiDprdpTouched) {
            return;
        }
        setIdPartaiDprdpTouched(true);
    }

    @Bindable
    public final String getIdPartaiDprdk() {
        return this.idPartaiDprdk;
    }

    public final void setIdPartaiDprdk(String value) {
        Object obj;
        Intrinsics.checkNotNullParameter(value, "value");
        this.idPartaiDprdk = value;
        this.idPartaiDprdkValid = !Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI) || this.partaiDprdkOptions.keySet().contains(value);
        Iterator<T> it = this.partaiDprdkOptions.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((Map.Entry) obj).getKey(), value)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        this.idPartaiDprdkValue = entry != null ? ((Number) entry.getValue()).longValue() : 0L;
        verifyAllForms();
        notifyPropertyChanged(25);
        notifyPropertyChanged(27);
        if (this.idPartaiDprdkTouched) {
            return;
        }
        setIdPartaiDprdkTouched(true);
    }

    @Bindable
    public final String getIdPaslonDpd() {
        return this.idPaslonDpd;
    }

    public final void setIdPaslonDpd(String value) {
        Object obj;
        Intrinsics.checkNotNullParameter(value, "value");
        this.idPaslonDpd = value;
        this.idPaslonDpdValid = !Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI) || this.paslonDpdOptions.keySet().contains(value);
        Iterator<T> it = this.paslonDpdOptions.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((Map.Entry) obj).getKey(), value)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        this.idPaslonDpdValue = entry != null ? ((Number) entry.getValue()).longValue() : 0L;
        verifyAllForms();
        notifyPropertyChanged(31);
        notifyPropertyChanged(33);
        if (this.idPaslonDpdTouched) {
            return;
        }
        setIdPaslonDpdTouched(true);
    }

    @Bindable
    public final boolean getIdPaslonPresidenTouched() {
        return this.idPaslonPresidenTouched;
    }

    public final void setIdPaslonPresidenTouched(boolean z) {
        this.idPaslonPresidenTouched = z;
        notifyPropertyChanged(41);
    }

    @Bindable
    public final boolean getIdPaslonPilgubTouched() {
        return this.idPaslonPilgubTouched;
    }

    public final void setIdPaslonPilgubTouched(boolean z) {
        this.idPaslonPilgubTouched = z;
        notifyPropertyChanged(35);
    }

    @Bindable
    public final boolean getIdPaslonPilwalkotTouched() {
        return this.idPaslonPilwalkotTouched;
    }

    public final void setIdPaslonPilwalkotTouched(boolean z) {
        this.idPaslonPilwalkotTouched = z;
        notifyPropertyChanged(38);
    }

    @Bindable
    public final boolean getIdPartaiDprTouched() {
        return this.idPartaiDprTouched;
    }

    public final void setIdPartaiDprTouched(boolean z) {
        this.idPartaiDprTouched = z;
        notifyPropertyChanged(23);
    }

    @Bindable
    public final boolean getIdPartaiDprdpTouched() {
        return this.idPartaiDprdpTouched;
    }

    public final void setIdPartaiDprdpTouched(boolean z) {
        this.idPartaiDprdpTouched = z;
        notifyPropertyChanged(29);
    }

    @Bindable
    public final boolean getIdPartaiDprdkTouched() {
        return this.idPartaiDprdkTouched;
    }

    public final void setIdPartaiDprdkTouched(boolean z) {
        this.idPartaiDprdkTouched = z;
        notifyPropertyChanged(26);
    }

    @Bindable
    public final boolean getIdPaslonDpdTouched() {
        return this.idPaslonDpdTouched;
    }

    public final void setIdPaslonDpdTouched(boolean z) {
        this.idPaslonDpdTouched = z;
        notifyPropertyChanged(32);
    }

    @Bindable
    public final boolean getIdPaslonPresidenValid() {
        return this.idPaslonPresidenValid;
    }

    public final void setIdPaslonPresidenValid(boolean z) {
        this.idPaslonPresidenValid = z;
    }

    @Bindable
    public final boolean getIdPaslonPilgubValid() {
        return this.idPaslonPilgubValid;
    }

    public final void setIdPaslonPilgubValid(boolean z) {
        this.idPaslonPilgubValid = z;
    }

    @Bindable
    public final boolean getIdPaslonPilwalkotValid() {
        return this.idPaslonPilwalkotValid;
    }

    public final void setIdPaslonPilwalkotValid(boolean z) {
        this.idPaslonPilwalkotValid = z;
    }

    @Bindable
    public final boolean getIdPartaiDprValid() {
        return this.idPartaiDprValid;
    }

    public final void setIdPartaiDprValid(boolean z) {
        this.idPartaiDprValid = z;
    }

    @Bindable
    public final boolean getIdPartaiDprdpValid() {
        return this.idPartaiDprdpValid;
    }

    public final void setIdPartaiDprdpValid(boolean z) {
        this.idPartaiDprdpValid = z;
    }

    @Bindable
    public final boolean getIdPartaiDprdkValid() {
        return this.idPartaiDprdkValid;
    }

    public final void setIdPartaiDprdkValid(boolean z) {
        this.idPartaiDprdkValid = z;
    }

    @Bindable
    public final boolean getIdPaslonDpdValid() {
        return this.idPaslonDpdValid;
    }

    public final void setIdPaslonDpdValid(boolean z) {
        this.idPaslonDpdValid = z;
    }

    @Bindable
    public final boolean getAllValid() {
        return this.allValid;
    }

    public final void setAllValid(boolean z) {
        this.allValid = z;
        notifyPropertyChanged(1);
    }

    private final void verifyAllForms() {
        boolean z = true;
        boolean z2 = this.nameValid && this.nikValid && this.noHandphoneValid && this.jenisPemeriksaValid && this.jenisPemilihanValid;
        boolean z3 = this.isJenisPemilihanPresiden;
        boolean z4 = !z3 || (z3 && this.idPaslonPresidenValid);
        boolean z5 = this.isJenisPemilihanPkwkp;
        boolean z6 = !z5 || (z5 && this.idPaslonPilgubValid);
        boolean z7 = this.isJenisPemilihanPkwkk;
        boolean z8 = !z7 || (z7 && this.idPaslonPilwalkotValid);
        boolean z9 = this.isJenisPemilihanDpr;
        boolean z10 = !z9 || (z9 && this.idPartaiDprValid);
        boolean z11 = this.isJenisPemilihanDprdp;
        boolean z12 = !z11 || (z11 && this.idPartaiDprdpValid);
        boolean z13 = this.isJenisPemilihanDprdk;
        boolean z14 = !z13 || (z13 && this.idPartaiDprdkValid);
        boolean z15 = this.isJenisPemilihanDpd;
        boolean z16 = !z15 || (z15 && this.idPaslonDpdValid);
        if (!z2 || !z4 || !z6 || !z8 || !z10 || !z12 || !z14 || !z16) {
            z = false;
        }
        setAllValid(z);
    }

    public final WitnessRepository.AddWitnessModel toAddWitnessModels(String profile) {
        Intrinsics.checkNotNullParameter(profile, "profile");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (this.isJenisPemilihanPresiden || (!Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI) && this.electionTypes.contains(Election.ELECTION_PEMILIHAN_PRESIDEN))) {
            arrayList.add(Election.ELECTION_PEMILIHAN_PRESIDEN);
            if (Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI)) {
                arrayList2.add(Long.valueOf(this.idPaslonPresidenValue));
            } else {
                arrayList2.add(0L);
            }
        }
        if (this.isJenisPemilihanPkwkp || (!Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI) && this.electionTypes.contains(Election.ELECTION_PEMILIHAN_PROVINSI))) {
            arrayList.add(Election.ELECTION_PEMILIHAN_PROVINSI);
            if (Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI)) {
                arrayList2.add(Long.valueOf(this.idPaslonPilgubValue));
            } else {
                arrayList2.add(0L);
            }
        }
        if (this.isJenisPemilihanPkwkk || (!Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI) && this.electionTypes.contains(Election.ELECTION_PEMILIHAN_KOTAKAB))) {
            arrayList.add(Election.ELECTION_PEMILIHAN_KOTAKAB);
            if (Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI)) {
                arrayList2.add(Long.valueOf(this.idPaslonPilwalkotValue));
            } else {
                arrayList2.add(0L);
            }
        }
        if (this.isJenisPemilihanDpr || (!Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI) && this.electionTypes.contains(Election.ELECTION_PEMILIHAN_DPR))) {
            arrayList.add(Election.ELECTION_PEMILIHAN_DPR);
            if (Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI)) {
                arrayList2.add(Long.valueOf(this.idPartaiDprValue));
            } else {
                arrayList2.add(0L);
            }
        }
        if (this.isJenisPemilihanDprdp || (!Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI) && this.electionTypes.contains(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI))) {
            arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI);
            if (Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI)) {
                arrayList2.add(Long.valueOf(this.idPartaiDprdpValue));
            } else {
                arrayList2.add(0L);
            }
        }
        if (this.isJenisPemilihanDprdk || (!Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI) && this.electionTypes.contains(Election.ELECTION_PEMILIHAN_DPRD_KABKO))) {
            arrayList.add(Election.ELECTION_PEMILIHAN_DPRD_KABKO);
            if (Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI)) {
                arrayList2.add(Long.valueOf(this.idPartaiDprdkValue));
            } else {
                arrayList2.add(0L);
            }
        }
        if (this.isJenisPemilihanDpd || (!Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI) && this.electionTypes.contains(Election.ELECTION_PEMILIHAN_DPD))) {
            arrayList.add(Election.ELECTION_PEMILIHAN_DPD);
            if (Intrinsics.areEqual(this.jenisPemeriksaValue, Witness.WITNESS_TYPE_SAKSI)) {
                arrayList2.add(Long.valueOf(this.idPaslonDpdValue));
            } else {
                arrayList2.add(0L);
            }
        }
        return new WitnessRepository.AddWitnessModel(this.name, this.jenisPemeriksaValue, this.nik, ElectionUtil.generateWitnessId(this.noHandphone, profile), this.kodeTps, arrayList2, arrayList);
    }
}
