package org.informatika.sirekap.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FormC1KesesuaianAdministration.kt */
@Metadata(d1 = {"\u0000)\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\b\n\u0003\b\u009c\u0001\n\u0002\u0010 \n\u0002\b\u0004\b\u0087\b\u0018\u0000 Á\u00012\u00020\u0001:\u0002Á\u0001B\u0091\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010 \u001a\u0004\u0018\u00010!\u0012\b\u0010\"\u001a\u0004\u0018\u00010!\u0012\b\u0010#\u001a\u0004\u0018\u00010!\u0012\b\u0010$\u001a\u0004\u0018\u00010!\u0012\b\u0010%\u001a\u0004\u0018\u00010!\u0012\b\u0010&\u001a\u0004\u0018\u00010!\u0012\b\u0010'\u001a\u0004\u0018\u00010!\u0012\b\u0010(\u001a\u0004\u0018\u00010!\u0012\b\u0010)\u001a\u0004\u0018\u00010!\u0012\b\u0010*\u001a\u0004\u0018\u00010!\u0012\b\u0010+\u001a\u0004\u0018\u00010!\u0012\b\u0010,\u001a\u0004\u0018\u00010!\u0012\b\u0010-\u001a\u0004\u0018\u00010!\u0012\b\u0010.\u001a\u0004\u0018\u00010!\u0012\b\u0010/\u001a\u0004\u0018\u00010!\u0012\b\u00100\u001a\u0004\u0018\u00010!\u0012\b\u00101\u001a\u0004\u0018\u00010!\u0012\b\u00102\u001a\u0004\u0018\u00010!\u0012\b\u00103\u001a\u0004\u0018\u00010!\u0012\b\u00104\u001a\u0004\u0018\u00010!\u0012\b\u00105\u001a\u0004\u0018\u00010!\u0012\b\u00106\u001a\u0004\u0018\u00010!\u0012\b\u00107\u001a\u0004\u0018\u00010!\u0012\b\u00108\u001a\u0004\u0018\u00010!\u0012\b\u00109\u001a\u0004\u0018\u00010!¢\u0006\u0002\u0010:J\t\u0010u\u001a\u00020\u0003HÆ\u0003J\u0010\u0010v\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0010\u0010w\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0010\u0010x\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0010\u0010y\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0010\u0010z\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0010\u0010{\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0010\u0010|\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0010\u0010}\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0010\u0010~\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0010\u0010\u007f\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\n\u0010\u0080\u0001\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0011\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0011\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0011\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0011\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0011\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0011\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0011\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0011\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0011\u0010\u008a\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\n\u0010\u008b\u0001\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u008c\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u008d\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u008e\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u008f\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u0090\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u0091\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u0092\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u0093\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u0094\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u0095\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0011\u0010\u0097\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u0098\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u0099\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u009a\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u009b\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u009c\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u009d\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u009e\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010\u009f\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010 \u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010¡\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0011\u0010¢\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010£\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010¤\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010¥\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010CJ\u0011\u0010¦\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0011\u0010§\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0011\u0010¨\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0011\u0010©\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010@J\u0086\u0005\u0010ª\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010!2\n\b\u0002\u00100\u001a\u0004\u0018\u00010!2\n\b\u0002\u00101\u001a\u0004\u0018\u00010!2\n\b\u0002\u00102\u001a\u0004\u0018\u00010!2\n\b\u0002\u00103\u001a\u0004\u0018\u00010!2\n\b\u0002\u00104\u001a\u0004\u0018\u00010!2\n\b\u0002\u00105\u001a\u0004\u0018\u00010!2\n\b\u0002\u00106\u001a\u0004\u0018\u00010!2\n\b\u0002\u00107\u001a\u0004\u0018\u00010!2\n\b\u0002\u00108\u001a\u0004\u0018\u00010!2\n\b\u0002\u00109\u001a\u0004\u0018\u00010!HÆ\u0001¢\u0006\u0003\u0010«\u0001J\u0015\u0010¬\u0001\u001a\u00020\u00052\t\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\n\u0010®\u0001\u001a\u00020!HÖ\u0001J\u0007\u0010¯\u0001\u001a\u00020\u0005J\u0007\u0010°\u0001\u001a\u00020\u0005J\u0007\u0010±\u0001\u001a\u00020\u0005J\u0007\u0010²\u0001\u001a\u00020\u0005J\u0007\u0010³\u0001\u001a\u00020\u0005J\u0007\u0010´\u0001\u001a\u00020\u0005J\u0007\u0010µ\u0001\u001a\u00020\u0005J\u0007\u0010¶\u0001\u001a\u00020\u0005J\u0007\u0010·\u0001\u001a\u00020\u0005J\u0007\u0010¸\u0001\u001a\u00020\u0005J\u0007\u0010¹\u0001\u001a\u00020\u0005J\u0007\u0010º\u0001\u001a\u00020\u0005J\u0007\u0010»\u0001\u001a\u00020\u0005J\u0007\u0010¼\u0001\u001a\u00020\u0005J\u0010\u0010½\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050¾\u0001J\u0010\u0010¿\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010!0¾\u0001J\n\u0010À\u0001\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010=R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b>\u0010<R\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\b?\u0010@R\u0015\u00107\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bB\u0010CR\u0015\u0010\u001e\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bE\u0010@R\u0015\u00108\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bF\u0010CR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bG\u0010@R\u0015\u0010 \u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bH\u0010CR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bI\u0010@R\u0015\u0010\"\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bJ\u0010CR\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bK\u0010@R\u0015\u0010*\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bL\u0010CR\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bM\u0010@R\u0015\u0010+\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bN\u0010CR\u0015\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bO\u0010@R\u0015\u0010$\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bP\u0010CR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bQ\u0010@R\u0015\u0010%\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bR\u0010CR\u0015\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bS\u0010@R\u0015\u0010'\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bT\u0010CR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bU\u0010@R\u0015\u0010(\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bV\u0010CR\u0015\u0010\u001c\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bW\u0010@R\u0015\u00106\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bX\u0010CR\u0015\u0010\u0017\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bY\u0010@R\u0015\u00101\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bZ\u0010CR\u0015\u0010\u0016\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\b[\u0010@R\u0015\u00100\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\b\\\u0010CR\u0015\u0010\u001a\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\b]\u0010@R\u0015\u00104\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\b^\u0010CR\u0015\u0010\u0018\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\b_\u0010@R\u0015\u00102\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\b`\u0010CR\u0015\u0010\u0019\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\ba\u0010@R\u0015\u00103\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bb\u0010CR\u0015\u0010\u001b\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bc\u0010@R\u0015\u00105\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bd\u0010CR\u0015\u0010\u001f\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\be\u0010@R\u0015\u00109\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bf\u0010CR\u0015\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bg\u0010@R\u0015\u0010#\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bh\u0010CR\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bi\u0010@R\u0015\u0010/\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bj\u0010CR\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bk\u0010@R\u0015\u0010,\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bl\u0010CR\u0015\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bm\u0010@R\u0015\u0010&\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bn\u0010CR\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bo\u0010@R\u0015\u0010)\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bp\u0010CR\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bq\u0010@R\u0015\u0010-\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\br\u0010CR\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010A\u001a\u0004\bs\u0010@R\u0015\u0010.\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010D\u001a\u0004\bt\u0010C¨\u0006Â\u0001"}, d2 = {"Lorg/informatika/sirekap/model/FormC1KesesuaianAdministration;", "", "idImage", "", "isLnPos", "", "jenisPemilihan", "pemilihDpt_L", "pemilihDpt_P", "totalPemilihDpt", "penggunaDpt_L", "penggunaDpt_P", "totalPenggunaDpt", "penggunaDptb_L", "penggunaDptb_P", "totalPenggunaDptb", "penggunaDpk_L", "penggunaDpk_P", "totalPenggunaDpk", "totalPengguna_L", "totalPengguna_P", "totalPengguna", "suratDiterima", "suratDikembalikan", "suratTidakDigunakan", "suratTidakDikembalikan", "suratKembaliPPLN", "suratTidakTerpakai", "suratDigunakan", "pemilihDisabilitas_L", "pemilihDisabilitas_P", "totalPemilihDisabilitas", "pemilihDpt_LCorrected", "", "pemilihDpt_PCorrected", "totalPemilihDptCorrected", "penggunaDpt_LCorrected", "penggunaDpt_PCorrected", "totalPenggunaDptCorrected", "penggunaDptb_LCorrected", "penggunaDptb_PCorrected", "totalPenggunaDptbCorrected", "penggunaDpk_LCorrected", "penggunaDpk_PCorrected", "totalPenggunaDpkCorrected", "totalPengguna_LCorrected", "totalPengguna_PCorrected", "totalPenggunaCorrected", "suratDiterimaCorrected", "suratDikembalikanCorrected", "suratTidakDigunakanCorrected", "suratTidakDikembalikanCorrected", "suratKembaliPPLNCorrected", "suratTidakTerpakaiCorrected", "suratDigunakanCorrected", "pemilihDisabilitas_LCorrected", "pemilihDisabilitas_PCorrected", "totalPemilihDisabilitasCorrected", "(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getIdImage", "()Ljava/lang/String;", "()Z", "getJenisPemilihan", "getPemilihDisabilitas_L", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getPemilihDisabilitas_LCorrected", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPemilihDisabilitas_P", "getPemilihDisabilitas_PCorrected", "getPemilihDpt_L", "getPemilihDpt_LCorrected", "getPemilihDpt_P", "getPemilihDpt_PCorrected", "getPenggunaDpk_L", "getPenggunaDpk_LCorrected", "getPenggunaDpk_P", "getPenggunaDpk_PCorrected", "getPenggunaDpt_L", "getPenggunaDpt_LCorrected", "getPenggunaDpt_P", "getPenggunaDpt_PCorrected", "getPenggunaDptb_L", "getPenggunaDptb_LCorrected", "getPenggunaDptb_P", "getPenggunaDptb_PCorrected", "getSuratDigunakan", "getSuratDigunakanCorrected", "getSuratDikembalikan", "getSuratDikembalikanCorrected", "getSuratDiterima", "getSuratDiterimaCorrected", "getSuratKembaliPPLN", "getSuratKembaliPPLNCorrected", "getSuratTidakDigunakan", "getSuratTidakDigunakanCorrected", "getSuratTidakDikembalikan", "getSuratTidakDikembalikanCorrected", "getSuratTidakTerpakai", "getSuratTidakTerpakaiCorrected", "getTotalPemilihDisabilitas", "getTotalPemilihDisabilitasCorrected", "getTotalPemilihDpt", "getTotalPemilihDptCorrected", "getTotalPengguna", "getTotalPenggunaCorrected", "getTotalPenggunaDpk", "getTotalPenggunaDpkCorrected", "getTotalPenggunaDpt", "getTotalPenggunaDptCorrected", "getTotalPenggunaDptb", "getTotalPenggunaDptbCorrected", "getTotalPengguna_L", "getTotalPengguna_LCorrected", "getTotalPengguna_P", "getTotalPengguna_PCorrected", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component5", "component50", "component51", "component52", "component53", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lorg/informatika/sirekap/model/FormC1KesesuaianAdministration;", "equals", "other", "hashCode", "isCheckedAll", "isCheckedRowPemilihDisabilitas", "isCheckedRowPemilihDpt", "isCheckedRowPenggunaDpk", "isCheckedRowPenggunaDpt", "isCheckedRowPenggunaDptb", "isCheckedRowSuratDigunakan", "isCheckedRowSuratDikembalikan", "isCheckedRowSuratDiterima", "isCheckedRowSuratKembaliPPLN", "isCheckedRowSuratTidakDigunakan", "isCheckedRowSuratTidakDikembalikan", "isCheckedRowSuratTidakTerpakai", "isCheckedRowTotalPengguna", "toIsSesuaiPerItem", "", "toKoreksiPerItem", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormC1KesesuaianAdministration {
    public static final Companion Companion = new Companion(null);
    private final String idImage;
    private final boolean isLnPos;
    private final String jenisPemilihan;
    private final Boolean pemilihDisabilitas_L;
    private final Integer pemilihDisabilitas_LCorrected;
    private final Boolean pemilihDisabilitas_P;
    private final Integer pemilihDisabilitas_PCorrected;
    private final Boolean pemilihDpt_L;
    private final Integer pemilihDpt_LCorrected;
    private final Boolean pemilihDpt_P;
    private final Integer pemilihDpt_PCorrected;
    private final Boolean penggunaDpk_L;
    private final Integer penggunaDpk_LCorrected;
    private final Boolean penggunaDpk_P;
    private final Integer penggunaDpk_PCorrected;
    private final Boolean penggunaDpt_L;
    private final Integer penggunaDpt_LCorrected;
    private final Boolean penggunaDpt_P;
    private final Integer penggunaDpt_PCorrected;
    private final Boolean penggunaDptb_L;
    private final Integer penggunaDptb_LCorrected;
    private final Boolean penggunaDptb_P;
    private final Integer penggunaDptb_PCorrected;
    private final Boolean suratDigunakan;
    private final Integer suratDigunakanCorrected;
    private final Boolean suratDikembalikan;
    private final Integer suratDikembalikanCorrected;
    private final Boolean suratDiterima;
    private final Integer suratDiterimaCorrected;
    private final Boolean suratKembaliPPLN;
    private final Integer suratKembaliPPLNCorrected;
    private final Boolean suratTidakDigunakan;
    private final Integer suratTidakDigunakanCorrected;
    private final Boolean suratTidakDikembalikan;
    private final Integer suratTidakDikembalikanCorrected;
    private final Boolean suratTidakTerpakai;
    private final Integer suratTidakTerpakaiCorrected;
    private final Boolean totalPemilihDisabilitas;
    private final Integer totalPemilihDisabilitasCorrected;
    private final Boolean totalPemilihDpt;
    private final Integer totalPemilihDptCorrected;
    private final Boolean totalPengguna;
    private final Integer totalPenggunaCorrected;
    private final Boolean totalPenggunaDpk;
    private final Integer totalPenggunaDpkCorrected;
    private final Boolean totalPenggunaDpt;
    private final Integer totalPenggunaDptCorrected;
    private final Boolean totalPenggunaDptb;
    private final Integer totalPenggunaDptbCorrected;
    private final Boolean totalPengguna_L;
    private final Integer totalPengguna_LCorrected;
    private final Boolean totalPengguna_P;
    private final Integer totalPengguna_PCorrected;

    public final String component1() {
        return this.idImage;
    }

    public final Boolean component10() {
        return this.penggunaDptb_L;
    }

    public final Boolean component11() {
        return this.penggunaDptb_P;
    }

    public final Boolean component12() {
        return this.totalPenggunaDptb;
    }

    public final Boolean component13() {
        return this.penggunaDpk_L;
    }

    public final Boolean component14() {
        return this.penggunaDpk_P;
    }

    public final Boolean component15() {
        return this.totalPenggunaDpk;
    }

    public final Boolean component16() {
        return this.totalPengguna_L;
    }

    public final Boolean component17() {
        return this.totalPengguna_P;
    }

    public final Boolean component18() {
        return this.totalPengguna;
    }

    public final Boolean component19() {
        return this.suratDiterima;
    }

    public final boolean component2() {
        return this.isLnPos;
    }

    public final Boolean component20() {
        return this.suratDikembalikan;
    }

    public final Boolean component21() {
        return this.suratTidakDigunakan;
    }

    public final Boolean component22() {
        return this.suratTidakDikembalikan;
    }

    public final Boolean component23() {
        return this.suratKembaliPPLN;
    }

    public final Boolean component24() {
        return this.suratTidakTerpakai;
    }

    public final Boolean component25() {
        return this.suratDigunakan;
    }

    public final Boolean component26() {
        return this.pemilihDisabilitas_L;
    }

    public final Boolean component27() {
        return this.pemilihDisabilitas_P;
    }

    public final Boolean component28() {
        return this.totalPemilihDisabilitas;
    }

    public final Integer component29() {
        return this.pemilihDpt_LCorrected;
    }

    public final String component3() {
        return this.jenisPemilihan;
    }

    public final Integer component30() {
        return this.pemilihDpt_PCorrected;
    }

    public final Integer component31() {
        return this.totalPemilihDptCorrected;
    }

    public final Integer component32() {
        return this.penggunaDpt_LCorrected;
    }

    public final Integer component33() {
        return this.penggunaDpt_PCorrected;
    }

    public final Integer component34() {
        return this.totalPenggunaDptCorrected;
    }

    public final Integer component35() {
        return this.penggunaDptb_LCorrected;
    }

    public final Integer component36() {
        return this.penggunaDptb_PCorrected;
    }

    public final Integer component37() {
        return this.totalPenggunaDptbCorrected;
    }

    public final Integer component38() {
        return this.penggunaDpk_LCorrected;
    }

    public final Integer component39() {
        return this.penggunaDpk_PCorrected;
    }

    public final Boolean component4() {
        return this.pemilihDpt_L;
    }

    public final Integer component40() {
        return this.totalPenggunaDpkCorrected;
    }

    public final Integer component41() {
        return this.totalPengguna_LCorrected;
    }

    public final Integer component42() {
        return this.totalPengguna_PCorrected;
    }

    public final Integer component43() {
        return this.totalPenggunaCorrected;
    }

    public final Integer component44() {
        return this.suratDiterimaCorrected;
    }

    public final Integer component45() {
        return this.suratDikembalikanCorrected;
    }

    public final Integer component46() {
        return this.suratTidakDigunakanCorrected;
    }

    public final Integer component47() {
        return this.suratTidakDikembalikanCorrected;
    }

    public final Integer component48() {
        return this.suratKembaliPPLNCorrected;
    }

    public final Integer component49() {
        return this.suratTidakTerpakaiCorrected;
    }

    public final Boolean component5() {
        return this.pemilihDpt_P;
    }

    public final Integer component50() {
        return this.suratDigunakanCorrected;
    }

    public final Integer component51() {
        return this.pemilihDisabilitas_LCorrected;
    }

    public final Integer component52() {
        return this.pemilihDisabilitas_PCorrected;
    }

    public final Integer component53() {
        return this.totalPemilihDisabilitasCorrected;
    }

    public final Boolean component6() {
        return this.totalPemilihDpt;
    }

    public final Boolean component7() {
        return this.penggunaDpt_L;
    }

    public final Boolean component8() {
        return this.penggunaDpt_P;
    }

    public final Boolean component9() {
        return this.totalPenggunaDpt;
    }

    public final FormC1KesesuaianAdministration copy(String idImage, boolean z, String jenisPemilihan, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, Boolean bool11, Boolean bool12, Boolean bool13, Boolean bool14, Boolean bool15, Boolean bool16, Boolean bool17, Boolean bool18, Boolean bool19, Boolean bool20, Boolean bool21, Boolean bool22, Boolean bool23, Boolean bool24, Boolean bool25, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, Integer num12, Integer num13, Integer num14, Integer num15, Integer num16, Integer num17, Integer num18, Integer num19, Integer num20, Integer num21, Integer num22, Integer num23, Integer num24, Integer num25) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        return new FormC1KesesuaianAdministration(idImage, z, jenisPemilihan, bool, bool2, bool3, bool4, bool5, bool6, bool7, bool8, bool9, bool10, bool11, bool12, bool13, bool14, bool15, bool16, bool17, bool18, bool19, bool20, bool21, bool22, bool23, bool24, bool25, num, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11, num12, num13, num14, num15, num16, num17, num18, num19, num20, num21, num22, num23, num24, num25);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FormC1KesesuaianAdministration) {
            FormC1KesesuaianAdministration formC1KesesuaianAdministration = (FormC1KesesuaianAdministration) obj;
            return Intrinsics.areEqual(this.idImage, formC1KesesuaianAdministration.idImage) && this.isLnPos == formC1KesesuaianAdministration.isLnPos && Intrinsics.areEqual(this.jenisPemilihan, formC1KesesuaianAdministration.jenisPemilihan) && Intrinsics.areEqual(this.pemilihDpt_L, formC1KesesuaianAdministration.pemilihDpt_L) && Intrinsics.areEqual(this.pemilihDpt_P, formC1KesesuaianAdministration.pemilihDpt_P) && Intrinsics.areEqual(this.totalPemilihDpt, formC1KesesuaianAdministration.totalPemilihDpt) && Intrinsics.areEqual(this.penggunaDpt_L, formC1KesesuaianAdministration.penggunaDpt_L) && Intrinsics.areEqual(this.penggunaDpt_P, formC1KesesuaianAdministration.penggunaDpt_P) && Intrinsics.areEqual(this.totalPenggunaDpt, formC1KesesuaianAdministration.totalPenggunaDpt) && Intrinsics.areEqual(this.penggunaDptb_L, formC1KesesuaianAdministration.penggunaDptb_L) && Intrinsics.areEqual(this.penggunaDptb_P, formC1KesesuaianAdministration.penggunaDptb_P) && Intrinsics.areEqual(this.totalPenggunaDptb, formC1KesesuaianAdministration.totalPenggunaDptb) && Intrinsics.areEqual(this.penggunaDpk_L, formC1KesesuaianAdministration.penggunaDpk_L) && Intrinsics.areEqual(this.penggunaDpk_P, formC1KesesuaianAdministration.penggunaDpk_P) && Intrinsics.areEqual(this.totalPenggunaDpk, formC1KesesuaianAdministration.totalPenggunaDpk) && Intrinsics.areEqual(this.totalPengguna_L, formC1KesesuaianAdministration.totalPengguna_L) && Intrinsics.areEqual(this.totalPengguna_P, formC1KesesuaianAdministration.totalPengguna_P) && Intrinsics.areEqual(this.totalPengguna, formC1KesesuaianAdministration.totalPengguna) && Intrinsics.areEqual(this.suratDiterima, formC1KesesuaianAdministration.suratDiterima) && Intrinsics.areEqual(this.suratDikembalikan, formC1KesesuaianAdministration.suratDikembalikan) && Intrinsics.areEqual(this.suratTidakDigunakan, formC1KesesuaianAdministration.suratTidakDigunakan) && Intrinsics.areEqual(this.suratTidakDikembalikan, formC1KesesuaianAdministration.suratTidakDikembalikan) && Intrinsics.areEqual(this.suratKembaliPPLN, formC1KesesuaianAdministration.suratKembaliPPLN) && Intrinsics.areEqual(this.suratTidakTerpakai, formC1KesesuaianAdministration.suratTidakTerpakai) && Intrinsics.areEqual(this.suratDigunakan, formC1KesesuaianAdministration.suratDigunakan) && Intrinsics.areEqual(this.pemilihDisabilitas_L, formC1KesesuaianAdministration.pemilihDisabilitas_L) && Intrinsics.areEqual(this.pemilihDisabilitas_P, formC1KesesuaianAdministration.pemilihDisabilitas_P) && Intrinsics.areEqual(this.totalPemilihDisabilitas, formC1KesesuaianAdministration.totalPemilihDisabilitas) && Intrinsics.areEqual(this.pemilihDpt_LCorrected, formC1KesesuaianAdministration.pemilihDpt_LCorrected) && Intrinsics.areEqual(this.pemilihDpt_PCorrected, formC1KesesuaianAdministration.pemilihDpt_PCorrected) && Intrinsics.areEqual(this.totalPemilihDptCorrected, formC1KesesuaianAdministration.totalPemilihDptCorrected) && Intrinsics.areEqual(this.penggunaDpt_LCorrected, formC1KesesuaianAdministration.penggunaDpt_LCorrected) && Intrinsics.areEqual(this.penggunaDpt_PCorrected, formC1KesesuaianAdministration.penggunaDpt_PCorrected) && Intrinsics.areEqual(this.totalPenggunaDptCorrected, formC1KesesuaianAdministration.totalPenggunaDptCorrected) && Intrinsics.areEqual(this.penggunaDptb_LCorrected, formC1KesesuaianAdministration.penggunaDptb_LCorrected) && Intrinsics.areEqual(this.penggunaDptb_PCorrected, formC1KesesuaianAdministration.penggunaDptb_PCorrected) && Intrinsics.areEqual(this.totalPenggunaDptbCorrected, formC1KesesuaianAdministration.totalPenggunaDptbCorrected) && Intrinsics.areEqual(this.penggunaDpk_LCorrected, formC1KesesuaianAdministration.penggunaDpk_LCorrected) && Intrinsics.areEqual(this.penggunaDpk_PCorrected, formC1KesesuaianAdministration.penggunaDpk_PCorrected) && Intrinsics.areEqual(this.totalPenggunaDpkCorrected, formC1KesesuaianAdministration.totalPenggunaDpkCorrected) && Intrinsics.areEqual(this.totalPengguna_LCorrected, formC1KesesuaianAdministration.totalPengguna_LCorrected) && Intrinsics.areEqual(this.totalPengguna_PCorrected, formC1KesesuaianAdministration.totalPengguna_PCorrected) && Intrinsics.areEqual(this.totalPenggunaCorrected, formC1KesesuaianAdministration.totalPenggunaCorrected) && Intrinsics.areEqual(this.suratDiterimaCorrected, formC1KesesuaianAdministration.suratDiterimaCorrected) && Intrinsics.areEqual(this.suratDikembalikanCorrected, formC1KesesuaianAdministration.suratDikembalikanCorrected) && Intrinsics.areEqual(this.suratTidakDigunakanCorrected, formC1KesesuaianAdministration.suratTidakDigunakanCorrected) && Intrinsics.areEqual(this.suratTidakDikembalikanCorrected, formC1KesesuaianAdministration.suratTidakDikembalikanCorrected) && Intrinsics.areEqual(this.suratKembaliPPLNCorrected, formC1KesesuaianAdministration.suratKembaliPPLNCorrected) && Intrinsics.areEqual(this.suratTidakTerpakaiCorrected, formC1KesesuaianAdministration.suratTidakTerpakaiCorrected) && Intrinsics.areEqual(this.suratDigunakanCorrected, formC1KesesuaianAdministration.suratDigunakanCorrected) && Intrinsics.areEqual(this.pemilihDisabilitas_LCorrected, formC1KesesuaianAdministration.pemilihDisabilitas_LCorrected) && Intrinsics.areEqual(this.pemilihDisabilitas_PCorrected, formC1KesesuaianAdministration.pemilihDisabilitas_PCorrected) && Intrinsics.areEqual(this.totalPemilihDisabilitasCorrected, formC1KesesuaianAdministration.totalPemilihDisabilitasCorrected);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.idImage.hashCode() * 31;
        boolean z = this.isLnPos;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int hashCode2 = (((hashCode + i) * 31) + this.jenisPemilihan.hashCode()) * 31;
        Boolean bool = this.pemilihDpt_L;
        int hashCode3 = (hashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.pemilihDpt_P;
        int hashCode4 = (hashCode3 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.totalPemilihDpt;
        int hashCode5 = (hashCode4 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Boolean bool4 = this.penggunaDpt_L;
        int hashCode6 = (hashCode5 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        Boolean bool5 = this.penggunaDpt_P;
        int hashCode7 = (hashCode6 + (bool5 == null ? 0 : bool5.hashCode())) * 31;
        Boolean bool6 = this.totalPenggunaDpt;
        int hashCode8 = (hashCode7 + (bool6 == null ? 0 : bool6.hashCode())) * 31;
        Boolean bool7 = this.penggunaDptb_L;
        int hashCode9 = (hashCode8 + (bool7 == null ? 0 : bool7.hashCode())) * 31;
        Boolean bool8 = this.penggunaDptb_P;
        int hashCode10 = (hashCode9 + (bool8 == null ? 0 : bool8.hashCode())) * 31;
        Boolean bool9 = this.totalPenggunaDptb;
        int hashCode11 = (hashCode10 + (bool9 == null ? 0 : bool9.hashCode())) * 31;
        Boolean bool10 = this.penggunaDpk_L;
        int hashCode12 = (hashCode11 + (bool10 == null ? 0 : bool10.hashCode())) * 31;
        Boolean bool11 = this.penggunaDpk_P;
        int hashCode13 = (hashCode12 + (bool11 == null ? 0 : bool11.hashCode())) * 31;
        Boolean bool12 = this.totalPenggunaDpk;
        int hashCode14 = (hashCode13 + (bool12 == null ? 0 : bool12.hashCode())) * 31;
        Boolean bool13 = this.totalPengguna_L;
        int hashCode15 = (hashCode14 + (bool13 == null ? 0 : bool13.hashCode())) * 31;
        Boolean bool14 = this.totalPengguna_P;
        int hashCode16 = (hashCode15 + (bool14 == null ? 0 : bool14.hashCode())) * 31;
        Boolean bool15 = this.totalPengguna;
        int hashCode17 = (hashCode16 + (bool15 == null ? 0 : bool15.hashCode())) * 31;
        Boolean bool16 = this.suratDiterima;
        int hashCode18 = (hashCode17 + (bool16 == null ? 0 : bool16.hashCode())) * 31;
        Boolean bool17 = this.suratDikembalikan;
        int hashCode19 = (hashCode18 + (bool17 == null ? 0 : bool17.hashCode())) * 31;
        Boolean bool18 = this.suratTidakDigunakan;
        int hashCode20 = (hashCode19 + (bool18 == null ? 0 : bool18.hashCode())) * 31;
        Boolean bool19 = this.suratTidakDikembalikan;
        int hashCode21 = (hashCode20 + (bool19 == null ? 0 : bool19.hashCode())) * 31;
        Boolean bool20 = this.suratKembaliPPLN;
        int hashCode22 = (hashCode21 + (bool20 == null ? 0 : bool20.hashCode())) * 31;
        Boolean bool21 = this.suratTidakTerpakai;
        int hashCode23 = (hashCode22 + (bool21 == null ? 0 : bool21.hashCode())) * 31;
        Boolean bool22 = this.suratDigunakan;
        int hashCode24 = (hashCode23 + (bool22 == null ? 0 : bool22.hashCode())) * 31;
        Boolean bool23 = this.pemilihDisabilitas_L;
        int hashCode25 = (hashCode24 + (bool23 == null ? 0 : bool23.hashCode())) * 31;
        Boolean bool24 = this.pemilihDisabilitas_P;
        int hashCode26 = (hashCode25 + (bool24 == null ? 0 : bool24.hashCode())) * 31;
        Boolean bool25 = this.totalPemilihDisabilitas;
        int hashCode27 = (hashCode26 + (bool25 == null ? 0 : bool25.hashCode())) * 31;
        Integer num = this.pemilihDpt_LCorrected;
        int hashCode28 = (hashCode27 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.pemilihDpt_PCorrected;
        int hashCode29 = (hashCode28 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.totalPemilihDptCorrected;
        int hashCode30 = (hashCode29 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.penggunaDpt_LCorrected;
        int hashCode31 = (hashCode30 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.penggunaDpt_PCorrected;
        int hashCode32 = (hashCode31 + (num5 == null ? 0 : num5.hashCode())) * 31;
        Integer num6 = this.totalPenggunaDptCorrected;
        int hashCode33 = (hashCode32 + (num6 == null ? 0 : num6.hashCode())) * 31;
        Integer num7 = this.penggunaDptb_LCorrected;
        int hashCode34 = (hashCode33 + (num7 == null ? 0 : num7.hashCode())) * 31;
        Integer num8 = this.penggunaDptb_PCorrected;
        int hashCode35 = (hashCode34 + (num8 == null ? 0 : num8.hashCode())) * 31;
        Integer num9 = this.totalPenggunaDptbCorrected;
        int hashCode36 = (hashCode35 + (num9 == null ? 0 : num9.hashCode())) * 31;
        Integer num10 = this.penggunaDpk_LCorrected;
        int hashCode37 = (hashCode36 + (num10 == null ? 0 : num10.hashCode())) * 31;
        Integer num11 = this.penggunaDpk_PCorrected;
        int hashCode38 = (hashCode37 + (num11 == null ? 0 : num11.hashCode())) * 31;
        Integer num12 = this.totalPenggunaDpkCorrected;
        int hashCode39 = (hashCode38 + (num12 == null ? 0 : num12.hashCode())) * 31;
        Integer num13 = this.totalPengguna_LCorrected;
        int hashCode40 = (hashCode39 + (num13 == null ? 0 : num13.hashCode())) * 31;
        Integer num14 = this.totalPengguna_PCorrected;
        int hashCode41 = (hashCode40 + (num14 == null ? 0 : num14.hashCode())) * 31;
        Integer num15 = this.totalPenggunaCorrected;
        int hashCode42 = (hashCode41 + (num15 == null ? 0 : num15.hashCode())) * 31;
        Integer num16 = this.suratDiterimaCorrected;
        int hashCode43 = (hashCode42 + (num16 == null ? 0 : num16.hashCode())) * 31;
        Integer num17 = this.suratDikembalikanCorrected;
        int hashCode44 = (hashCode43 + (num17 == null ? 0 : num17.hashCode())) * 31;
        Integer num18 = this.suratTidakDigunakanCorrected;
        int hashCode45 = (hashCode44 + (num18 == null ? 0 : num18.hashCode())) * 31;
        Integer num19 = this.suratTidakDikembalikanCorrected;
        int hashCode46 = (hashCode45 + (num19 == null ? 0 : num19.hashCode())) * 31;
        Integer num20 = this.suratKembaliPPLNCorrected;
        int hashCode47 = (hashCode46 + (num20 == null ? 0 : num20.hashCode())) * 31;
        Integer num21 = this.suratTidakTerpakaiCorrected;
        int hashCode48 = (hashCode47 + (num21 == null ? 0 : num21.hashCode())) * 31;
        Integer num22 = this.suratDigunakanCorrected;
        int hashCode49 = (hashCode48 + (num22 == null ? 0 : num22.hashCode())) * 31;
        Integer num23 = this.pemilihDisabilitas_LCorrected;
        int hashCode50 = (hashCode49 + (num23 == null ? 0 : num23.hashCode())) * 31;
        Integer num24 = this.pemilihDisabilitas_PCorrected;
        int hashCode51 = (hashCode50 + (num24 == null ? 0 : num24.hashCode())) * 31;
        Integer num25 = this.totalPemilihDisabilitasCorrected;
        return hashCode51 + (num25 != null ? num25.hashCode() : 0);
    }

    public String toString() {
        String str = this.idImage;
        boolean z = this.isLnPos;
        String str2 = this.jenisPemilihan;
        Boolean bool = this.pemilihDpt_L;
        Boolean bool2 = this.pemilihDpt_P;
        Boolean bool3 = this.totalPemilihDpt;
        Boolean bool4 = this.penggunaDpt_L;
        Boolean bool5 = this.penggunaDpt_P;
        Boolean bool6 = this.totalPenggunaDpt;
        Boolean bool7 = this.penggunaDptb_L;
        Boolean bool8 = this.penggunaDptb_P;
        Boolean bool9 = this.totalPenggunaDptb;
        Boolean bool10 = this.penggunaDpk_L;
        Boolean bool11 = this.penggunaDpk_P;
        Boolean bool12 = this.totalPenggunaDpk;
        Boolean bool13 = this.totalPengguna_L;
        Boolean bool14 = this.totalPengguna_P;
        Boolean bool15 = this.totalPengguna;
        Boolean bool16 = this.suratDiterima;
        Boolean bool17 = this.suratDikembalikan;
        Boolean bool18 = this.suratTidakDigunakan;
        Boolean bool19 = this.suratTidakDikembalikan;
        Boolean bool20 = this.suratKembaliPPLN;
        Boolean bool21 = this.suratTidakTerpakai;
        Boolean bool22 = this.suratDigunakan;
        Boolean bool23 = this.pemilihDisabilitas_L;
        Boolean bool24 = this.pemilihDisabilitas_P;
        Boolean bool25 = this.totalPemilihDisabilitas;
        Integer num = this.pemilihDpt_LCorrected;
        Integer num2 = this.pemilihDpt_PCorrected;
        Integer num3 = this.totalPemilihDptCorrected;
        Integer num4 = this.penggunaDpt_LCorrected;
        Integer num5 = this.penggunaDpt_PCorrected;
        Integer num6 = this.totalPenggunaDptCorrected;
        Integer num7 = this.penggunaDptb_LCorrected;
        Integer num8 = this.penggunaDptb_PCorrected;
        Integer num9 = this.totalPenggunaDptbCorrected;
        Integer num10 = this.penggunaDpk_LCorrected;
        Integer num11 = this.penggunaDpk_PCorrected;
        Integer num12 = this.totalPenggunaDpkCorrected;
        Integer num13 = this.totalPengguna_LCorrected;
        Integer num14 = this.totalPengguna_PCorrected;
        Integer num15 = this.totalPenggunaCorrected;
        Integer num16 = this.suratDiterimaCorrected;
        Integer num17 = this.suratDikembalikanCorrected;
        Integer num18 = this.suratTidakDigunakanCorrected;
        Integer num19 = this.suratTidakDikembalikanCorrected;
        Integer num20 = this.suratKembaliPPLNCorrected;
        Integer num21 = this.suratTidakTerpakaiCorrected;
        Integer num22 = this.suratDigunakanCorrected;
        Integer num23 = this.pemilihDisabilitas_LCorrected;
        Integer num24 = this.pemilihDisabilitas_PCorrected;
        return "FormC1KesesuaianAdministration(idImage=" + str + ", isLnPos=" + z + ", jenisPemilihan=" + str2 + ", pemilihDpt_L=" + bool + ", pemilihDpt_P=" + bool2 + ", totalPemilihDpt=" + bool3 + ", penggunaDpt_L=" + bool4 + ", penggunaDpt_P=" + bool5 + ", totalPenggunaDpt=" + bool6 + ", penggunaDptb_L=" + bool7 + ", penggunaDptb_P=" + bool8 + ", totalPenggunaDptb=" + bool9 + ", penggunaDpk_L=" + bool10 + ", penggunaDpk_P=" + bool11 + ", totalPenggunaDpk=" + bool12 + ", totalPengguna_L=" + bool13 + ", totalPengguna_P=" + bool14 + ", totalPengguna=" + bool15 + ", suratDiterima=" + bool16 + ", suratDikembalikan=" + bool17 + ", suratTidakDigunakan=" + bool18 + ", suratTidakDikembalikan=" + bool19 + ", suratKembaliPPLN=" + bool20 + ", suratTidakTerpakai=" + bool21 + ", suratDigunakan=" + bool22 + ", pemilihDisabilitas_L=" + bool23 + ", pemilihDisabilitas_P=" + bool24 + ", totalPemilihDisabilitas=" + bool25 + ", pemilihDpt_LCorrected=" + num + ", pemilihDpt_PCorrected=" + num2 + ", totalPemilihDptCorrected=" + num3 + ", penggunaDpt_LCorrected=" + num4 + ", penggunaDpt_PCorrected=" + num5 + ", totalPenggunaDptCorrected=" + num6 + ", penggunaDptb_LCorrected=" + num7 + ", penggunaDptb_PCorrected=" + num8 + ", totalPenggunaDptbCorrected=" + num9 + ", penggunaDpk_LCorrected=" + num10 + ", penggunaDpk_PCorrected=" + num11 + ", totalPenggunaDpkCorrected=" + num12 + ", totalPengguna_LCorrected=" + num13 + ", totalPengguna_PCorrected=" + num14 + ", totalPenggunaCorrected=" + num15 + ", suratDiterimaCorrected=" + num16 + ", suratDikembalikanCorrected=" + num17 + ", suratTidakDigunakanCorrected=" + num18 + ", suratTidakDikembalikanCorrected=" + num19 + ", suratKembaliPPLNCorrected=" + num20 + ", suratTidakTerpakaiCorrected=" + num21 + ", suratDigunakanCorrected=" + num22 + ", pemilihDisabilitas_LCorrected=" + num23 + ", pemilihDisabilitas_PCorrected=" + num24 + ", totalPemilihDisabilitasCorrected=" + this.totalPemilihDisabilitasCorrected + ")";
    }

    public FormC1KesesuaianAdministration(String idImage, boolean z, String jenisPemilihan, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, Boolean bool11, Boolean bool12, Boolean bool13, Boolean bool14, Boolean bool15, Boolean bool16, Boolean bool17, Boolean bool18, Boolean bool19, Boolean bool20, Boolean bool21, Boolean bool22, Boolean bool23, Boolean bool24, Boolean bool25, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, Integer num12, Integer num13, Integer num14, Integer num15, Integer num16, Integer num17, Integer num18, Integer num19, Integer num20, Integer num21, Integer num22, Integer num23, Integer num24, Integer num25) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        this.idImage = idImage;
        this.isLnPos = z;
        this.jenisPemilihan = jenisPemilihan;
        this.pemilihDpt_L = bool;
        this.pemilihDpt_P = bool2;
        this.totalPemilihDpt = bool3;
        this.penggunaDpt_L = bool4;
        this.penggunaDpt_P = bool5;
        this.totalPenggunaDpt = bool6;
        this.penggunaDptb_L = bool7;
        this.penggunaDptb_P = bool8;
        this.totalPenggunaDptb = bool9;
        this.penggunaDpk_L = bool10;
        this.penggunaDpk_P = bool11;
        this.totalPenggunaDpk = bool12;
        this.totalPengguna_L = bool13;
        this.totalPengguna_P = bool14;
        this.totalPengguna = bool15;
        this.suratDiterima = bool16;
        this.suratDikembalikan = bool17;
        this.suratTidakDigunakan = bool18;
        this.suratTidakDikembalikan = bool19;
        this.suratKembaliPPLN = bool20;
        this.suratTidakTerpakai = bool21;
        this.suratDigunakan = bool22;
        this.pemilihDisabilitas_L = bool23;
        this.pemilihDisabilitas_P = bool24;
        this.totalPemilihDisabilitas = bool25;
        this.pemilihDpt_LCorrected = num;
        this.pemilihDpt_PCorrected = num2;
        this.totalPemilihDptCorrected = num3;
        this.penggunaDpt_LCorrected = num4;
        this.penggunaDpt_PCorrected = num5;
        this.totalPenggunaDptCorrected = num6;
        this.penggunaDptb_LCorrected = num7;
        this.penggunaDptb_PCorrected = num8;
        this.totalPenggunaDptbCorrected = num9;
        this.penggunaDpk_LCorrected = num10;
        this.penggunaDpk_PCorrected = num11;
        this.totalPenggunaDpkCorrected = num12;
        this.totalPengguna_LCorrected = num13;
        this.totalPengguna_PCorrected = num14;
        this.totalPenggunaCorrected = num15;
        this.suratDiterimaCorrected = num16;
        this.suratDikembalikanCorrected = num17;
        this.suratTidakDigunakanCorrected = num18;
        this.suratTidakDikembalikanCorrected = num19;
        this.suratKembaliPPLNCorrected = num20;
        this.suratTidakTerpakaiCorrected = num21;
        this.suratDigunakanCorrected = num22;
        this.pemilihDisabilitas_LCorrected = num23;
        this.pemilihDisabilitas_PCorrected = num24;
        this.totalPemilihDisabilitasCorrected = num25;
    }

    public final String getIdImage() {
        return this.idImage;
    }

    public final boolean isLnPos() {
        return this.isLnPos;
    }

    public final String getJenisPemilihan() {
        return this.jenisPemilihan;
    }

    public final Boolean getPemilihDpt_L() {
        return this.pemilihDpt_L;
    }

    public final Boolean getPemilihDpt_P() {
        return this.pemilihDpt_P;
    }

    public final Boolean getTotalPemilihDpt() {
        return this.totalPemilihDpt;
    }

    public final Boolean getPenggunaDpt_L() {
        return this.penggunaDpt_L;
    }

    public final Boolean getPenggunaDpt_P() {
        return this.penggunaDpt_P;
    }

    public final Boolean getTotalPenggunaDpt() {
        return this.totalPenggunaDpt;
    }

    public final Boolean getPenggunaDptb_L() {
        return this.penggunaDptb_L;
    }

    public final Boolean getPenggunaDptb_P() {
        return this.penggunaDptb_P;
    }

    public final Boolean getTotalPenggunaDptb() {
        return this.totalPenggunaDptb;
    }

    public final Boolean getPenggunaDpk_L() {
        return this.penggunaDpk_L;
    }

    public final Boolean getPenggunaDpk_P() {
        return this.penggunaDpk_P;
    }

    public final Boolean getTotalPenggunaDpk() {
        return this.totalPenggunaDpk;
    }

    public final Boolean getTotalPengguna_L() {
        return this.totalPengguna_L;
    }

    public final Boolean getTotalPengguna_P() {
        return this.totalPengguna_P;
    }

    public final Boolean getTotalPengguna() {
        return this.totalPengguna;
    }

    public final Boolean getSuratDiterima() {
        return this.suratDiterima;
    }

    public final Boolean getSuratDikembalikan() {
        return this.suratDikembalikan;
    }

    public final Boolean getSuratTidakDigunakan() {
        return this.suratTidakDigunakan;
    }

    public final Boolean getSuratTidakDikembalikan() {
        return this.suratTidakDikembalikan;
    }

    public final Boolean getSuratKembaliPPLN() {
        return this.suratKembaliPPLN;
    }

    public final Boolean getSuratTidakTerpakai() {
        return this.suratTidakTerpakai;
    }

    public final Boolean getSuratDigunakan() {
        return this.suratDigunakan;
    }

    public final Boolean getPemilihDisabilitas_L() {
        return this.pemilihDisabilitas_L;
    }

    public final Boolean getPemilihDisabilitas_P() {
        return this.pemilihDisabilitas_P;
    }

    public final Boolean getTotalPemilihDisabilitas() {
        return this.totalPemilihDisabilitas;
    }

    public final Integer getPemilihDpt_LCorrected() {
        return this.pemilihDpt_LCorrected;
    }

    public final Integer getPemilihDpt_PCorrected() {
        return this.pemilihDpt_PCorrected;
    }

    public final Integer getTotalPemilihDptCorrected() {
        return this.totalPemilihDptCorrected;
    }

    public final Integer getPenggunaDpt_LCorrected() {
        return this.penggunaDpt_LCorrected;
    }

    public final Integer getPenggunaDpt_PCorrected() {
        return this.penggunaDpt_PCorrected;
    }

    public final Integer getTotalPenggunaDptCorrected() {
        return this.totalPenggunaDptCorrected;
    }

    public final Integer getPenggunaDptb_LCorrected() {
        return this.penggunaDptb_LCorrected;
    }

    public final Integer getPenggunaDptb_PCorrected() {
        return this.penggunaDptb_PCorrected;
    }

    public final Integer getTotalPenggunaDptbCorrected() {
        return this.totalPenggunaDptbCorrected;
    }

    public final Integer getPenggunaDpk_LCorrected() {
        return this.penggunaDpk_LCorrected;
    }

    public final Integer getPenggunaDpk_PCorrected() {
        return this.penggunaDpk_PCorrected;
    }

    public final Integer getTotalPenggunaDpkCorrected() {
        return this.totalPenggunaDpkCorrected;
    }

    public final Integer getTotalPengguna_LCorrected() {
        return this.totalPengguna_LCorrected;
    }

    public final Integer getTotalPengguna_PCorrected() {
        return this.totalPengguna_PCorrected;
    }

    public final Integer getTotalPenggunaCorrected() {
        return this.totalPenggunaCorrected;
    }

    public final Integer getSuratDiterimaCorrected() {
        return this.suratDiterimaCorrected;
    }

    public final Integer getSuratDikembalikanCorrected() {
        return this.suratDikembalikanCorrected;
    }

    public final Integer getSuratTidakDigunakanCorrected() {
        return this.suratTidakDigunakanCorrected;
    }

    public final Integer getSuratTidakDikembalikanCorrected() {
        return this.suratTidakDikembalikanCorrected;
    }

    public final Integer getSuratKembaliPPLNCorrected() {
        return this.suratKembaliPPLNCorrected;
    }

    public final Integer getSuratTidakTerpakaiCorrected() {
        return this.suratTidakTerpakaiCorrected;
    }

    public final Integer getSuratDigunakanCorrected() {
        return this.suratDigunakanCorrected;
    }

    public final Integer getPemilihDisabilitas_LCorrected() {
        return this.pemilihDisabilitas_LCorrected;
    }

    public final Integer getPemilihDisabilitas_PCorrected() {
        return this.pemilihDisabilitas_PCorrected;
    }

    public final Integer getTotalPemilihDisabilitasCorrected() {
        return this.totalPemilihDisabilitasCorrected;
    }

    public final boolean isCheckedRowPemilihDpt() {
        return (this.pemilihDpt_L == null || this.pemilihDpt_P == null || this.totalPemilihDpt == null) ? false : true;
    }

    public final boolean isCheckedRowPenggunaDpt() {
        return (this.penggunaDpt_L == null || this.penggunaDpt_P == null || this.totalPenggunaDpt == null) ? false : true;
    }

    public final boolean isCheckedRowPenggunaDptb() {
        return (this.penggunaDptb_L == null || this.penggunaDptb_P == null || this.totalPenggunaDptb == null) ? false : true;
    }

    public final boolean isCheckedRowPenggunaDpk() {
        return (this.penggunaDpk_L == null || this.penggunaDpk_P == null || this.totalPenggunaDpk == null) ? false : true;
    }

    public final boolean isCheckedRowTotalPengguna() {
        return (this.totalPengguna_L == null || this.totalPengguna_P == null || this.totalPengguna == null) ? false : true;
    }

    public final boolean isCheckedRowPemilihDisabilitas() {
        return (this.pemilihDisabilitas_L == null || this.pemilihDisabilitas_P == null || this.totalPemilihDisabilitas == null) ? false : true;
    }

    public final boolean isCheckedRowSuratDiterima() {
        return this.suratDiterima != null;
    }

    public final boolean isCheckedRowSuratDikembalikan() {
        return this.suratDikembalikan != null;
    }

    public final boolean isCheckedRowSuratTidakDigunakan() {
        return this.suratTidakDigunakan != null;
    }

    public final boolean isCheckedRowSuratTidakDikembalikan() {
        return this.suratTidakDikembalikan != null;
    }

    public final boolean isCheckedRowSuratKembaliPPLN() {
        return this.suratKembaliPPLN != null;
    }

    public final boolean isCheckedRowSuratTidakTerpakai() {
        return this.suratTidakTerpakai != null;
    }

    public final boolean isCheckedRowSuratDigunakan() {
        return this.suratDigunakan != null;
    }

    public final boolean isCheckedAll() {
        return isCheckedRowPemilihDpt() && isCheckedRowPenggunaDpt() && isCheckedRowPenggunaDptb() && isCheckedRowPenggunaDpk() && isCheckedRowTotalPengguna() && isCheckedRowSuratDiterima() && isCheckedRowSuratDikembalikan() && isCheckedRowSuratTidakDigunakan() && isCheckedRowSuratDigunakan() && isCheckedRowPemilihDisabilitas();
    }

    public final List<Boolean> toIsSesuaiPerItem() {
        if (this.isLnPos) {
            return CollectionsKt.listOf((Object[]) new Boolean[]{this.pemilihDpt_L, this.pemilihDpt_P, this.totalPemilihDpt, this.penggunaDpt_L, this.penggunaDpt_P, this.totalPenggunaDpt, this.penggunaDptb_L, this.penggunaDptb_P, this.totalPenggunaDptb, this.penggunaDpk_L, this.penggunaDpk_P, this.totalPenggunaDpk, this.totalPengguna_L, this.totalPengguna_P, this.totalPengguna, this.suratDiterima, this.suratDigunakan, this.suratDikembalikan, this.suratTidakDigunakan, this.suratKembaliPPLN, this.suratTidakTerpakai, this.suratTidakDikembalikan, this.pemilihDisabilitas_L, this.pemilihDisabilitas_P, this.totalPemilihDisabilitas});
        }
        return CollectionsKt.listOf((Object[]) new Boolean[]{this.pemilihDpt_L, this.pemilihDpt_P, this.totalPemilihDpt, this.penggunaDpt_L, this.penggunaDpt_P, this.totalPenggunaDpt, this.penggunaDptb_L, this.penggunaDptb_P, this.totalPenggunaDptb, this.penggunaDpk_L, this.penggunaDpk_P, this.totalPenggunaDpk, this.totalPengguna_L, this.totalPengguna_P, this.totalPengguna, this.suratDiterima, this.suratDigunakan, this.suratDikembalikan, this.suratTidakDigunakan, this.pemilihDisabilitas_L, this.pemilihDisabilitas_P, this.totalPemilihDisabilitas});
    }

    public final List<Integer> toKoreksiPerItem() {
        if (this.isLnPos) {
            return CollectionsKt.listOf((Object[]) new Integer[]{this.pemilihDpt_LCorrected, this.pemilihDpt_PCorrected, this.totalPemilihDptCorrected, this.penggunaDpt_LCorrected, this.penggunaDpt_PCorrected, this.totalPenggunaDptCorrected, this.penggunaDptb_LCorrected, this.penggunaDptb_PCorrected, this.totalPenggunaDptbCorrected, this.penggunaDpk_LCorrected, this.penggunaDpk_PCorrected, this.totalPenggunaDpkCorrected, this.totalPengguna_LCorrected, this.totalPengguna_PCorrected, this.totalPenggunaCorrected, this.suratDiterimaCorrected, this.suratDigunakanCorrected, this.suratDikembalikanCorrected, this.suratTidakDigunakanCorrected, this.suratKembaliPPLNCorrected, this.suratTidakTerpakaiCorrected, this.suratTidakDikembalikanCorrected, this.pemilihDisabilitas_LCorrected, this.pemilihDisabilitas_PCorrected, this.totalPemilihDisabilitasCorrected});
        }
        return CollectionsKt.listOf((Object[]) new Integer[]{this.pemilihDpt_LCorrected, this.pemilihDpt_PCorrected, this.totalPemilihDptCorrected, this.penggunaDpt_LCorrected, this.penggunaDpt_PCorrected, this.totalPenggunaDptCorrected, this.penggunaDptb_LCorrected, this.penggunaDptb_PCorrected, this.totalPenggunaDptbCorrected, this.penggunaDpk_LCorrected, this.penggunaDpk_PCorrected, this.totalPenggunaDpkCorrected, this.totalPengguna_LCorrected, this.totalPengguna_PCorrected, this.totalPenggunaCorrected, this.suratDiterimaCorrected, this.suratDigunakanCorrected, this.suratDikembalikanCorrected, this.suratTidakDigunakanCorrected, this.pemilihDisabilitas_LCorrected, this.pemilihDisabilitas_PCorrected, this.totalPemilihDisabilitasCorrected});
    }

    /* compiled from: FormC1KesesuaianAdministration.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006J>\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\f2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/model/FormC1KesesuaianAdministration$Companion;", "", "()V", "createFilled", "Lorg/informatika/sirekap/model/FormC1KesesuaianAdministration;", "idImage", "", "isLnPos", "", "jenisPemilihan", "createFromIsSesuaiPerItem", "isSesuaiPerItem", "", "koreksiPerItem", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FormC1KesesuaianAdministration createFilled(String idImage, boolean z, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new FormC1KesesuaianAdministration(idImage, z, jenisPemilihan, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        }

        public final FormC1KesesuaianAdministration createFromIsSesuaiPerItem(String idImage, List<Boolean> isSesuaiPerItem, List<Integer> koreksiPerItem, boolean z, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(idImage, "idImage");
            Intrinsics.checkNotNullParameter(isSesuaiPerItem, "isSesuaiPerItem");
            Intrinsics.checkNotNullParameter(koreksiPerItem, "koreksiPerItem");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            if (z) {
                Boolean bool = isSesuaiPerItem.get(16);
                Boolean bool2 = isSesuaiPerItem.get(19);
                Boolean bool3 = isSesuaiPerItem.get(20);
                Boolean bool4 = isSesuaiPerItem.get(24);
                Integer num = koreksiPerItem.get(0);
                Integer num2 = koreksiPerItem.get(1);
                Integer num3 = koreksiPerItem.get(2);
                Integer num4 = koreksiPerItem.get(3);
                Integer num5 = koreksiPerItem.get(4);
                Integer num6 = koreksiPerItem.get(5);
                Integer num7 = koreksiPerItem.get(6);
                Integer num8 = koreksiPerItem.get(7);
                Integer num9 = koreksiPerItem.get(8);
                Integer num10 = koreksiPerItem.get(9);
                Integer num11 = koreksiPerItem.get(10);
                Integer num12 = koreksiPerItem.get(11);
                Integer num13 = koreksiPerItem.get(12);
                Integer num14 = koreksiPerItem.get(13);
                Integer num15 = koreksiPerItem.get(14);
                Integer num16 = koreksiPerItem.get(15);
                Integer num17 = koreksiPerItem.get(16);
                return new FormC1KesesuaianAdministration(idImage, z, jenisPemilihan, isSesuaiPerItem.get(0), isSesuaiPerItem.get(1), isSesuaiPerItem.get(2), isSesuaiPerItem.get(3), isSesuaiPerItem.get(4), isSesuaiPerItem.get(5), isSesuaiPerItem.get(6), isSesuaiPerItem.get(7), isSesuaiPerItem.get(8), isSesuaiPerItem.get(9), isSesuaiPerItem.get(10), isSesuaiPerItem.get(11), isSesuaiPerItem.get(12), isSesuaiPerItem.get(13), isSesuaiPerItem.get(14), isSesuaiPerItem.get(15), isSesuaiPerItem.get(17), isSesuaiPerItem.get(18), isSesuaiPerItem.get(21), bool2, bool3, bool, isSesuaiPerItem.get(22), isSesuaiPerItem.get(23), bool4, num, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11, num12, num13, num14, num15, num16, koreksiPerItem.get(17), koreksiPerItem.get(18), koreksiPerItem.get(21), koreksiPerItem.get(19), koreksiPerItem.get(20), num17, koreksiPerItem.get(22), koreksiPerItem.get(23), koreksiPerItem.get(24));
            }
            return new FormC1KesesuaianAdministration(idImage, z, jenisPemilihan, isSesuaiPerItem.get(0), isSesuaiPerItem.get(1), isSesuaiPerItem.get(2), isSesuaiPerItem.get(3), isSesuaiPerItem.get(4), isSesuaiPerItem.get(5), isSesuaiPerItem.get(6), isSesuaiPerItem.get(7), isSesuaiPerItem.get(8), isSesuaiPerItem.get(9), isSesuaiPerItem.get(10), isSesuaiPerItem.get(11), isSesuaiPerItem.get(12), isSesuaiPerItem.get(13), isSesuaiPerItem.get(14), isSesuaiPerItem.get(15), isSesuaiPerItem.get(17), isSesuaiPerItem.get(18), null, null, null, isSesuaiPerItem.get(16), isSesuaiPerItem.get(19), isSesuaiPerItem.get(20), isSesuaiPerItem.get(21), koreksiPerItem.get(0), koreksiPerItem.get(1), koreksiPerItem.get(2), koreksiPerItem.get(3), koreksiPerItem.get(4), koreksiPerItem.get(5), koreksiPerItem.get(6), koreksiPerItem.get(7), koreksiPerItem.get(8), koreksiPerItem.get(9), koreksiPerItem.get(10), koreksiPerItem.get(11), koreksiPerItem.get(12), koreksiPerItem.get(13), koreksiPerItem.get(14), koreksiPerItem.get(15), koreksiPerItem.get(17), koreksiPerItem.get(18), null, null, null, koreksiPerItem.get(16), koreksiPerItem.get(19), koreksiPerItem.get(20), koreksiPerItem.get(21));
        }
    }
}
