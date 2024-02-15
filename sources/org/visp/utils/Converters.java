package org.visp.utils;

import java.util.ArrayList;
import java.util.List;
import org.visp.core.VpCameraParameters;
import org.visp.core.VpColVector;
import org.visp.core.VpHomogeneousMatrix;
import org.visp.core.VpImagePoint;
import org.visp.core.VpImageUChar;

/* loaded from: classes4.dex */
public class Converters {
    public static List<VpColVector> Array_to_vector_vpColVector(long[] jArr) {
        ArrayList arrayList = new ArrayList();
        for (long j : jArr) {
            arrayList.add(new VpColVector(j));
        }
        return arrayList;
    }

    public static List<VpHomogeneousMatrix> Array_to_vector_vpHomogeneousMatrix(long[] jArr) {
        ArrayList arrayList = new ArrayList();
        for (long j : jArr) {
            arrayList.add(new VpHomogeneousMatrix(j));
        }
        return arrayList;
    }

    public static List<List<VpImagePoint>> Array_Array_to_vector_vector_vpImagePoint(long[][] jArr) {
        if (jArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(jArr.length);
        for (int i = 0; i < jArr.length; i++) {
            arrayList.add(i, new ArrayList(jArr[i].length));
            for (int i2 = 0; i2 < jArr[i].length; i2++) {
                ((List) arrayList.get(i)).add(i2, new VpImagePoint(Long.valueOf(jArr[i][i2]).longValue()));
            }
        }
        return arrayList;
    }

    public static long[] vector_vpHomogeneousMatrix_to_Array(List<VpHomogeneousMatrix> list) {
        long[] jArr = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            jArr[i] = list.get(i).nativeObj;
        }
        return jArr;
    }

    public static long[] array_vpImageUChar_to_array_native(VpImageUChar[] vpImageUCharArr) {
        long[] jArr = new long[vpImageUCharArr.length];
        for (int i = 0; i < vpImageUCharArr.length; i++) {
            jArr[i] = vpImageUCharArr[i].nativeObj;
        }
        return jArr;
    }

    public static long[][] matrix_vpColVector_to_matrix_native(VpColVector[][] vpColVectorArr) {
        long[][] jArr = new long[vpColVectorArr.length];
        for (int i = 0; i < vpColVectorArr.length; i++) {
            jArr[i] = new long[vpColVectorArr[i].length];
            int i2 = 0;
            while (true) {
                VpColVector[] vpColVectorArr2 = vpColVectorArr[i];
                if (i2 < vpColVectorArr2.length) {
                    jArr[i][i2] = vpColVectorArr2[i2].nativeObj;
                    i2++;
                }
            }
        }
        return jArr;
    }

    public static long[] array_vpHomogeneousMatrix_to_array_native(VpHomogeneousMatrix[] vpHomogeneousMatrixArr) {
        long[] jArr = new long[vpHomogeneousMatrixArr.length];
        for (int i = 0; i < vpHomogeneousMatrixArr.length; i++) {
            jArr[i] = vpHomogeneousMatrixArr[i].nativeObj;
        }
        return jArr;
    }

    public static long[] array_vpCameraParameters_to_array_native(VpCameraParameters[] vpCameraParametersArr) {
        long[] jArr = new long[vpCameraParametersArr.length];
        for (int i = 0; i < vpCameraParametersArr.length; i++) {
            jArr[i] = vpCameraParametersArr[i].nativeObj;
        }
        return jArr;
    }
}
