package org.bouncycastle.asn1.ua;

import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class DSTU4145Params extends ASN1Object {
    private static final byte[] DEFAULT_DKE = {-87, -42, -21, BuiltinOptions.BidirectionalSequenceLSTMOptions, -15, BuiltinOptions.LogicalOrOptions, BuiltinOptions.ReadVariableOptions, -126, ByteCompanionObject.MIN_VALUE, -60, -106, 123, BuiltinOptions.SplitOptions, BuiltinOptions.SequenceRNNOptions, BuiltinOptions.DepthToSpaceOptions, -83, -10, BuiltinOptions.MatrixDiagOptions, -21, -92, -64, 55, BuiltinOptions.LessOptions, BuiltinOptions.DivOptions, BuiltinOptions.PowOptions, -39, BuiltinOptions.HashtableOptions, -16, BuiltinOptions.CastOptions, -54, BuiltinOptions.AbsOptions, BuiltinOptions.GatherOptions, -8, -23, BuiltinOptions.RandomOptions, 13, -58, BuiltinOptions.MulOptions, -76, BuiltinOptions.FakeQuantOptions, BuiltinOptions.ArgMaxOptions, -105, BuiltinOptions.NonMaxSuppressionV4Options, 11, -63, -34, -93, BuiltinOptions.SegmentSumOptions, BuiltinOptions.PowOptions, -75, BuiltinOptions.SegmentSumOptions, -22, BuiltinOptions.GreaterOptions, BuiltinOptions.GatherOptions, -97, -48, BuiltinOptions.SkipGramOptions, BuiltinOptions.LogicalAndOptions, BuiltinOptions.HashtableImportOptions, -72, -6, -59, 121, 4};
    private byte[] dke;
    private DSTU4145ECBinary ecbinary;
    private ASN1ObjectIdentifier namedCurve;

    public DSTU4145Params(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.dke = DEFAULT_DKE;
        this.namedCurve = aSN1ObjectIdentifier;
    }

    public DSTU4145Params(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr) {
        this.dke = DEFAULT_DKE;
        this.namedCurve = aSN1ObjectIdentifier;
        this.dke = Arrays.clone(bArr);
    }

    public DSTU4145Params(DSTU4145ECBinary dSTU4145ECBinary) {
        this.dke = DEFAULT_DKE;
        this.ecbinary = dSTU4145ECBinary;
    }

    public static byte[] getDefaultDKE() {
        return Arrays.clone(DEFAULT_DKE);
    }

    public static DSTU4145Params getInstance(Object obj) {
        if (obj instanceof DSTU4145Params) {
            return (DSTU4145Params) obj;
        }
        if (obj != null) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(obj);
            DSTU4145Params dSTU4145Params = aSN1Sequence.getObjectAt(0) instanceof ASN1ObjectIdentifier ? new DSTU4145Params(ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0))) : new DSTU4145Params(DSTU4145ECBinary.getInstance(aSN1Sequence.getObjectAt(0)));
            if (aSN1Sequence.size() == 2) {
                byte[] octets = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets();
                dSTU4145Params.dke = octets;
                if (octets.length != DEFAULT_DKE.length) {
                    throw new IllegalArgumentException("object parse error");
                }
            }
            return dSTU4145Params;
        }
        throw new IllegalArgumentException("object parse error");
    }

    public byte[] getDKE() {
        return Arrays.clone(this.dke);
    }

    public DSTU4145ECBinary getECBinary() {
        return this.ecbinary;
    }

    public ASN1ObjectIdentifier getNamedCurve() {
        return this.namedCurve;
    }

    public boolean isNamedCurve() {
        return this.namedCurve != null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        ASN1Encodable aSN1Encodable = this.namedCurve;
        if (aSN1Encodable == null) {
            aSN1Encodable = this.ecbinary;
        }
        aSN1EncodableVector.add(aSN1Encodable);
        if (!Arrays.areEqual(this.dke, DEFAULT_DKE)) {
            aSN1EncodableVector.add(new DEROctetString(this.dke));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
