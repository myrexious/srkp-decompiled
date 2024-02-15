package org.bouncycastle.crypto.examples;

import java.math.BigInteger;
import java.security.SecureRandom;
import net.openid.appauth.TokenRequest;
import org.apache.commons.text.StringSubstitutor;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.agreement.jpake.JPAKEParticipant;
import org.bouncycastle.crypto.agreement.jpake.JPAKEPrimeOrderGroup;
import org.bouncycastle.crypto.agreement.jpake.JPAKEPrimeOrderGroups;
import org.bouncycastle.crypto.agreement.jpake.JPAKERound1Payload;
import org.bouncycastle.crypto.agreement.jpake.JPAKERound2Payload;
import org.bouncycastle.crypto.agreement.jpake.JPAKERound3Payload;
import org.bouncycastle.crypto.digests.SHA256Digest;

/* loaded from: classes2.dex */
public class JPAKEExample {
    private static BigInteger deriveSessionKey(BigInteger bigInteger) {
        SHA256Digest sHA256Digest = new SHA256Digest();
        byte[] byteArray = bigInteger.toByteArray();
        byte[] bArr = new byte[sHA256Digest.getDigestSize()];
        sHA256Digest.update(byteArray, 0, byteArray.length);
        sHA256Digest.doFinal(bArr, 0);
        return new BigInteger(bArr);
    }

    public static void main(String[] strArr) throws CryptoException {
        JPAKEPrimeOrderGroup jPAKEPrimeOrderGroup = JPAKEPrimeOrderGroups.NIST_3072;
        BigInteger p = jPAKEPrimeOrderGroup.getP();
        BigInteger q = jPAKEPrimeOrderGroup.getQ();
        BigInteger g = jPAKEPrimeOrderGroup.getG();
        System.out.println("********* Initialization **********");
        System.out.println("Public parameters for the cyclic group:");
        System.out.println("p (" + p.bitLength() + " bits): " + p.toString(16));
        System.out.println("q (" + q.bitLength() + " bits): " + q.toString(16));
        System.out.println("g (" + p.bitLength() + " bits): " + g.toString(16));
        System.out.println("p mod q = " + p.mod(q).toString(16));
        System.out.println("g^{q} mod p = " + g.modPow(q, p).toString(16));
        System.out.println("");
        System.out.println("(Secret passwords used by Alice and Bob: \"password\" and \"password\")\n");
        SHA256Digest sHA256Digest = new SHA256Digest();
        SecureRandom secureRandom = new SecureRandom();
        JPAKEParticipant jPAKEParticipant = new JPAKEParticipant("alice", TokenRequest.GRANT_TYPE_PASSWORD.toCharArray(), jPAKEPrimeOrderGroup, sHA256Digest, secureRandom);
        JPAKEParticipant jPAKEParticipant2 = new JPAKEParticipant("bob", TokenRequest.GRANT_TYPE_PASSWORD.toCharArray(), jPAKEPrimeOrderGroup, sHA256Digest, secureRandom);
        JPAKERound1Payload createRound1PayloadToSend = jPAKEParticipant.createRound1PayloadToSend();
        JPAKERound1Payload createRound1PayloadToSend2 = jPAKEParticipant2.createRound1PayloadToSend();
        System.out.println("************ Round 1 **************");
        System.out.println("Alice sends to Bob: ");
        System.out.println("g^{x1}=" + createRound1PayloadToSend.getGx1().toString(16));
        System.out.println("g^{x2}=" + createRound1PayloadToSend.getGx2().toString(16));
        System.out.println("KP{x1}={" + createRound1PayloadToSend.getKnowledgeProofForX1()[0].toString(16) + "};{" + createRound1PayloadToSend.getKnowledgeProofForX1()[1].toString(16) + StringSubstitutor.DEFAULT_VAR_END);
        System.out.println("KP{x2}={" + createRound1PayloadToSend.getKnowledgeProofForX2()[0].toString(16) + "};{" + createRound1PayloadToSend.getKnowledgeProofForX2()[1].toString(16) + StringSubstitutor.DEFAULT_VAR_END);
        System.out.println("");
        System.out.println("Bob sends to Alice: ");
        System.out.println("g^{x3}=" + createRound1PayloadToSend2.getGx1().toString(16));
        System.out.println("g^{x4}=" + createRound1PayloadToSend2.getGx2().toString(16));
        System.out.println("KP{x3}={" + createRound1PayloadToSend2.getKnowledgeProofForX1()[0].toString(16) + "};{" + createRound1PayloadToSend2.getKnowledgeProofForX1()[1].toString(16) + StringSubstitutor.DEFAULT_VAR_END);
        System.out.println("KP{x4}={" + createRound1PayloadToSend2.getKnowledgeProofForX2()[0].toString(16) + "};{" + createRound1PayloadToSend2.getKnowledgeProofForX2()[1].toString(16) + StringSubstitutor.DEFAULT_VAR_END);
        System.out.println("");
        jPAKEParticipant.validateRound1PayloadReceived(createRound1PayloadToSend2);
        System.out.println("Alice checks g^{x4}!=1: OK");
        System.out.println("Alice checks KP{x3}: OK");
        System.out.println("Alice checks KP{x4}: OK");
        System.out.println("");
        jPAKEParticipant2.validateRound1PayloadReceived(createRound1PayloadToSend);
        System.out.println("Bob checks g^{x2}!=1: OK");
        System.out.println("Bob checks KP{x1},: OK");
        System.out.println("Bob checks KP{x2},: OK");
        System.out.println("");
        JPAKERound2Payload createRound2PayloadToSend = jPAKEParticipant.createRound2PayloadToSend();
        JPAKERound2Payload createRound2PayloadToSend2 = jPAKEParticipant2.createRound2PayloadToSend();
        System.out.println("************ Round 2 **************");
        System.out.println("Alice sends to Bob: ");
        System.out.println("A=" + createRound2PayloadToSend.getA().toString(16));
        System.out.println("KP{x2*s}={" + createRound2PayloadToSend.getKnowledgeProofForX2s()[0].toString(16) + "},{" + createRound2PayloadToSend.getKnowledgeProofForX2s()[1].toString(16) + StringSubstitutor.DEFAULT_VAR_END);
        System.out.println("");
        System.out.println("Bob sends to Alice");
        System.out.println("B=" + createRound2PayloadToSend2.getA().toString(16));
        System.out.println("KP{x4*s}={" + createRound2PayloadToSend2.getKnowledgeProofForX2s()[0].toString(16) + "},{" + createRound2PayloadToSend2.getKnowledgeProofForX2s()[1].toString(16) + StringSubstitutor.DEFAULT_VAR_END);
        System.out.println("");
        jPAKEParticipant.validateRound2PayloadReceived(createRound2PayloadToSend2);
        System.out.println("Alice checks KP{x4*s}: OK\n");
        jPAKEParticipant2.validateRound2PayloadReceived(createRound2PayloadToSend);
        System.out.println("Bob checks KP{x2*s}: OK\n");
        BigInteger calculateKeyingMaterial = jPAKEParticipant.calculateKeyingMaterial();
        BigInteger calculateKeyingMaterial2 = jPAKEParticipant2.calculateKeyingMaterial();
        System.out.println("********* After round 2 ***********");
        System.out.println("Alice computes key material \t K=" + calculateKeyingMaterial.toString(16));
        System.out.println("Bob computes key material \t K=" + calculateKeyingMaterial2.toString(16));
        System.out.println();
        deriveSessionKey(calculateKeyingMaterial);
        deriveSessionKey(calculateKeyingMaterial2);
        JPAKERound3Payload createRound3PayloadToSend = jPAKEParticipant.createRound3PayloadToSend(calculateKeyingMaterial);
        JPAKERound3Payload createRound3PayloadToSend2 = jPAKEParticipant2.createRound3PayloadToSend(calculateKeyingMaterial2);
        System.out.println("************ Round 3 **************");
        System.out.println("Alice sends to Bob: ");
        System.out.println("MacTag=" + createRound3PayloadToSend.getMacTag().toString(16));
        System.out.println("");
        System.out.println("Bob sends to Alice: ");
        System.out.println("MacTag=" + createRound3PayloadToSend2.getMacTag().toString(16));
        System.out.println("");
        jPAKEParticipant.validateRound3PayloadReceived(createRound3PayloadToSend2, calculateKeyingMaterial);
        System.out.println("Alice checks MacTag: OK\n");
        jPAKEParticipant2.validateRound3PayloadReceived(createRound3PayloadToSend, calculateKeyingMaterial2);
        System.out.println("Bob checks MacTag: OK\n");
        System.out.println();
        System.out.println("MacTags validated, therefore the keying material matches.");
    }
}
