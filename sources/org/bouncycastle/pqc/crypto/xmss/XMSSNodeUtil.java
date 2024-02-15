package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.pqc.crypto.xmss.HashTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.LTreeAddress;

/* loaded from: classes2.dex */
class XMSSNodeUtil {
    XMSSNodeUtil() {
    }

    public static XMSSNode lTree(WOTSPlus wOTSPlus, WOTSPlusPublicKeyParameters wOTSPlusPublicKeyParameters, LTreeAddress lTreeAddress) {
        double d;
        if (wOTSPlusPublicKeyParameters == null) {
            throw new NullPointerException("publicKey == null");
        }
        if (lTreeAddress == null) {
            throw new NullPointerException("address == null");
        }
        int len = wOTSPlus.getParams().getLen();
        byte[][] byteArray = wOTSPlusPublicKeyParameters.toByteArray();
        XMSSNode[] xMSSNodeArr = new XMSSNode[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            xMSSNodeArr[i] = new XMSSNode(0, byteArray[i]);
        }
        LTreeAddress.Builder withKeyAndMask = new LTreeAddress.Builder().withLayerAddress(lTreeAddress.getLayerAddress()).withTreeAddress(lTreeAddress.getTreeAddress()).withLTreeAddress(lTreeAddress.getLTreeAddress()).withTreeHeight(0).withTreeIndex(lTreeAddress.getTreeIndex()).withKeyAndMask(lTreeAddress.getKeyAndMask());
        while (true) {
            LTreeAddress lTreeAddress2 = (LTreeAddress) withKeyAndMask.build();
            if (len <= 1) {
                return xMSSNodeArr[0];
            }
            int i2 = 0;
            while (true) {
                d = len / 2;
                if (i2 >= ((int) Math.floor(d))) {
                    break;
                }
                lTreeAddress2 = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress2.getLayerAddress()).withTreeAddress(lTreeAddress2.getTreeAddress()).withLTreeAddress(lTreeAddress2.getLTreeAddress()).withTreeHeight(lTreeAddress2.getTreeHeight()).withTreeIndex(i2).withKeyAndMask(lTreeAddress2.getKeyAndMask()).build();
                int i3 = i2 * 2;
                xMSSNodeArr[i2] = randomizeHash(wOTSPlus, xMSSNodeArr[i3], xMSSNodeArr[i3 + 1], lTreeAddress2);
                i2++;
            }
            if (len % 2 == 1) {
                xMSSNodeArr[(int) Math.floor(d)] = xMSSNodeArr[len - 1];
            }
            len = (int) Math.ceil(len / 2.0d);
            withKeyAndMask = new LTreeAddress.Builder().withLayerAddress(lTreeAddress2.getLayerAddress()).withTreeAddress(lTreeAddress2.getTreeAddress()).withLTreeAddress(lTreeAddress2.getLTreeAddress()).withTreeHeight(lTreeAddress2.getTreeHeight() + 1).withTreeIndex(lTreeAddress2.getTreeIndex()).withKeyAndMask(lTreeAddress2.getKeyAndMask());
        }
    }

    public static XMSSNode randomizeHash(WOTSPlus wOTSPlus, XMSSNode xMSSNode, XMSSNode xMSSNode2, XMSSAddress xMSSAddress) {
        if (xMSSNode != null) {
            if (xMSSNode2 != null) {
                if (xMSSNode.getHeight() == xMSSNode2.getHeight()) {
                    if (xMSSAddress != null) {
                        byte[] publicSeed = wOTSPlus.getPublicSeed();
                        if (xMSSAddress instanceof LTreeAddress) {
                            LTreeAddress lTreeAddress = (LTreeAddress) xMSSAddress;
                            xMSSAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress.getLayerAddress()).withTreeAddress(lTreeAddress.getTreeAddress()).withLTreeAddress(lTreeAddress.getLTreeAddress()).withTreeHeight(lTreeAddress.getTreeHeight()).withTreeIndex(lTreeAddress.getTreeIndex()).withKeyAndMask(0).build();
                        } else if (xMSSAddress instanceof HashTreeAddress) {
                            HashTreeAddress hashTreeAddress = (HashTreeAddress) xMSSAddress;
                            xMSSAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress()).withTreeAddress(hashTreeAddress.getTreeAddress()).withTreeHeight(hashTreeAddress.getTreeHeight()).withTreeIndex(hashTreeAddress.getTreeIndex()).withKeyAndMask(0).build();
                        }
                        byte[] PRF = wOTSPlus.getKhf().PRF(publicSeed, xMSSAddress.toByteArray());
                        if (xMSSAddress instanceof LTreeAddress) {
                            LTreeAddress lTreeAddress2 = (LTreeAddress) xMSSAddress;
                            xMSSAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress2.getLayerAddress()).withTreeAddress(lTreeAddress2.getTreeAddress()).withLTreeAddress(lTreeAddress2.getLTreeAddress()).withTreeHeight(lTreeAddress2.getTreeHeight()).withTreeIndex(lTreeAddress2.getTreeIndex()).withKeyAndMask(1).build();
                        } else if (xMSSAddress instanceof HashTreeAddress) {
                            HashTreeAddress hashTreeAddress2 = (HashTreeAddress) xMSSAddress;
                            xMSSAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress2.getLayerAddress()).withTreeAddress(hashTreeAddress2.getTreeAddress()).withTreeHeight(hashTreeAddress2.getTreeHeight()).withTreeIndex(hashTreeAddress2.getTreeIndex()).withKeyAndMask(1).build();
                        }
                        byte[] PRF2 = wOTSPlus.getKhf().PRF(publicSeed, xMSSAddress.toByteArray());
                        if (xMSSAddress instanceof LTreeAddress) {
                            LTreeAddress lTreeAddress3 = (LTreeAddress) xMSSAddress;
                            xMSSAddress = (LTreeAddress) new LTreeAddress.Builder().withLayerAddress(lTreeAddress3.getLayerAddress()).withTreeAddress(lTreeAddress3.getTreeAddress()).withLTreeAddress(lTreeAddress3.getLTreeAddress()).withTreeHeight(lTreeAddress3.getTreeHeight()).withTreeIndex(lTreeAddress3.getTreeIndex()).withKeyAndMask(2).build();
                        } else if (xMSSAddress instanceof HashTreeAddress) {
                            HashTreeAddress hashTreeAddress3 = (HashTreeAddress) xMSSAddress;
                            xMSSAddress = (HashTreeAddress) new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress3.getLayerAddress()).withTreeAddress(hashTreeAddress3.getTreeAddress()).withTreeHeight(hashTreeAddress3.getTreeHeight()).withTreeIndex(hashTreeAddress3.getTreeIndex()).withKeyAndMask(2).build();
                        }
                        byte[] PRF3 = wOTSPlus.getKhf().PRF(publicSeed, xMSSAddress.toByteArray());
                        int treeDigestSize = wOTSPlus.getParams().getTreeDigestSize();
                        byte[] bArr = new byte[treeDigestSize * 2];
                        for (int i = 0; i < treeDigestSize; i++) {
                            bArr[i] = (byte) (xMSSNode.getValue()[i] ^ PRF2[i]);
                        }
                        for (int i2 = 0; i2 < treeDigestSize; i2++) {
                            bArr[i2 + treeDigestSize] = (byte) (xMSSNode2.getValue()[i2] ^ PRF3[i2]);
                        }
                        return new XMSSNode(xMSSNode.getHeight(), wOTSPlus.getKhf().H(PRF, bArr));
                    }
                    throw new NullPointerException("address == null");
                }
                throw new IllegalStateException("height of both nodes must be equal");
            }
            throw new NullPointerException("right == null");
        }
        throw new NullPointerException("left == null");
    }
}
