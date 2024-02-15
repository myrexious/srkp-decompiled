package org.visp.core;

/* loaded from: classes4.dex */
public class VpMatrix {
    public final long nativeObj;

    private static native void AAt_0(long j, long j2);

    private static native long AAt_1(long j);

    private static native void AtA_0(long j, long j2);

    private static native long AtA_1(long j);

    private static native long VpMatrix_0(int i, int i2, double d);

    private static native long VpMatrix_1(int i, int i2);

    private static native long VpMatrix_2(long j);

    private static native long VpMatrix_3(long j, int i, int i2, int i3, int i4);

    private static native long VpMatrix_4();

    private static native void add2Matrices_0(long j, long j2, long j3);

    private static native void add2Matrices_1(long j, long j2, long j3);

    private static native void add2WeightedMatrices_0(long j, double d, long j2, double d2, long j3);

    private static native void clear_0(long j);

    private static native long computeCovarianceMatrixVVS_0(long j, long j2, long j3, long j4);

    private static native long computeCovarianceMatrixVVS_1(long j, long j2, long j3);

    private static native long computeCovarianceMatrix_0(long j, long j2, long j3, long j4);

    private static native long computeCovarianceMatrix_1(long j, long j2, long j3);

    private static native void computeHLM_0(long j, double d, long j2);

    private static native double cond_0(long j, double d);

    private static native double cond_1(long j);

    private static native void createDiagonalMatrix_0(long j, long j2);

    private static native void delete(long j);

    private static native double detByLUEigen3_0(long j);

    private static native double detByLULapack_0(long j);

    private static native double detByLUOpenCV_0(long j);

    private static native double detByLU_0(long j);

    private static native void diag_0(long j, double d);

    private static native void diag_1(long j);

    private static native void diag_2(long j, long j2);

    private static native void eigenValues_0(long j, long j2, long j3);

    private static native long eigenValues_1(long j);

    private static native long expm_0(long j);

    private static native long extract_0(long j, int i, int i2, int i3, int i4);

    private static native void eye_0(long j, int i, int i2);

    private static native void eye_1(long j, int i);

    private static native void eye_2(long j);

    private static native double frobeniusNorm_0(long j);

    private static native long getCol_0(long j, int i, int i2, int i3);

    private static native long getCol_1(long j, int i);

    private static native long getDiag_0(long j);

    private static native int getLapackMatrixMinSize_0();

    private static native long getRow_0(long j, int i, int i2, int i3);

    private static native long getRow_1(long j, int i);

    private static native long hadamard_0(long j, long j2);

    private static native double inducedL2Norm_0(long j);

    private static native double infinityNorm_0(long j);

    private static native void init_0(long j, long j2, int i, int i2, int i3, int i4);

    private static native void insert_0(long j, long j2, int i, int i2);

    private static native void insert_1(long j, long j2, long j3, int i, int i2);

    private static native long insert_2(long j, long j2, int i, int i2);

    private static native long inverseByCholeskyGsl_0(long j);

    private static native long inverseByCholeskyLapack_0(long j);

    private static native long inverseByCholeskyOpenCV_0(long j);

    private static native long inverseByCholesky_0(long j);

    private static native long inverseByLUEigen3_0(long j);

    private static native long inverseByLULapack_0(long j);

    private static native long inverseByLUOpenCV_0(long j);

    private static native long inverseByLU_0(long j);

    private static native long inverseByQRGsl_0(long j);

    private static native long inverseByQRLapack_0(long j);

    private static native long inverseByQR_0(long j);

    private static native long inverseTriangular_0(long j, boolean z);

    private static native long inverseTriangular_1(long j);

    private static native void juxtaposeMatrices_0(long j, long j2, long j3);

    private static native long juxtaposeMatrices_1(long j, long j2);

    private static native int kernel_0(long j, long j2, double d);

    private static native int kernel_1(long j, long j2);

    private static native void kron_0(long j, long j2, long j3);

    private static native void kron_1(long j, long j2, long j3);

    private static native long kron_2(long j, long j2);

    private static native void mult2Matrices_0(long j, long j2, long j3);

    private static native void mult2Matrices_1(long j, long j2, long j3);

    private static native void mult2Matrices_2(long j, long j2, long j3);

    private static native void multMatrixVector_0(long j, long j2, long j3);

    private static native void negateMatrix_0(long j, long j2);

    private static native int nullSpace_0(long j, long j2, double d);

    private static native int nullSpace_1(long j, long j2);

    private static native int nullSpace_2(long j, long j2, int i);

    private static native void printSize_0(long j);

    private static native int pseudoInverseEigen3_0(long j, long j2, double d);

    private static native int pseudoInverseEigen3_1(long j, long j2);

    private static native long pseudoInverseEigen3_10(long j, int i);

    private static native int pseudoInverseEigen3_2(long j, long j2, int i);

    private static native int pseudoInverseEigen3_3(long j, long j2, long j3, double d, long j4, long j5, long j6);

    private static native int pseudoInverseEigen3_4(long j, long j2, long j3, double d);

    private static native int pseudoInverseEigen3_5(long j, long j2, long j3);

    private static native int pseudoInverseEigen3_6(long j, long j2, long j3, int i, long j4, long j5, long j6);

    private static native int pseudoInverseEigen3_7(long j, long j2, long j3, int i);

    private static native long pseudoInverseEigen3_8(long j, double d);

    private static native long pseudoInverseEigen3_9(long j);

    private static native int pseudoInverseGsl_0(long j, long j2, double d);

    private static native int pseudoInverseGsl_1(long j, long j2);

    private static native int pseudoInverseGsl_2(long j, long j2, long j3, double d, long j4, long j5, long j6);

    private static native int pseudoInverseGsl_3(long j, long j2, long j3, double d);

    private static native int pseudoInverseGsl_4(long j, long j2, long j3);

    private static native long pseudoInverseGsl_5(long j, double d);

    private static native long pseudoInverseGsl_6(long j);

    private static native int pseudoInverseLapack_0(long j, long j2, double d);

    private static native int pseudoInverseLapack_1(long j, long j2);

    private static native long pseudoInverseLapack_10(long j, int i);

    private static native int pseudoInverseLapack_2(long j, long j2, int i);

    private static native int pseudoInverseLapack_3(long j, long j2, long j3, double d, long j4, long j5, long j6);

    private static native int pseudoInverseLapack_4(long j, long j2, long j3, double d);

    private static native int pseudoInverseLapack_5(long j, long j2, long j3);

    private static native int pseudoInverseLapack_6(long j, long j2, long j3, int i, long j4, long j5, long j6);

    private static native int pseudoInverseLapack_7(long j, long j2, long j3, int i);

    private static native long pseudoInverseLapack_8(long j, double d);

    private static native long pseudoInverseLapack_9(long j);

    private static native int pseudoInverseOpenCV_0(long j, long j2, double d);

    private static native int pseudoInverseOpenCV_1(long j, long j2);

    private static native long pseudoInverseOpenCV_10(long j, int i);

    private static native int pseudoInverseOpenCV_2(long j, long j2, int i);

    private static native int pseudoInverseOpenCV_3(long j, long j2, long j3, double d, long j4, long j5, long j6);

    private static native int pseudoInverseOpenCV_4(long j, long j2, long j3, double d);

    private static native int pseudoInverseOpenCV_5(long j, long j2, long j3);

    private static native int pseudoInverseOpenCV_6(long j, long j2, long j3, int i, long j4, long j5, long j6);

    private static native int pseudoInverseOpenCV_7(long j, long j2, long j3, int i);

    private static native long pseudoInverseOpenCV_8(long j, double d);

    private static native long pseudoInverseOpenCV_9(long j);

    private static native int pseudoInverse_0(long j, long j2, double d);

    private static native int pseudoInverse_1(long j, long j2);

    private static native long pseudoInverse_10(long j, double d);

    private static native long pseudoInverse_11(long j);

    private static native long pseudoInverse_12(long j, int i);

    private static native int pseudoInverse_2(long j, long j2, int i);

    private static native int pseudoInverse_3(long j, long j2, long j3, double d, long j4, long j5, long j6);

    private static native int pseudoInverse_4(long j, long j2, long j3, double d, long j4, long j5);

    private static native int pseudoInverse_5(long j, long j2, long j3, double d);

    private static native int pseudoInverse_6(long j, long j2, long j3);

    private static native int pseudoInverse_7(long j, long j2, long j3, int i, long j4, long j5, long j6);

    private static native int pseudoInverse_8(long j, long j2, long j3, int i, long j4, long j5);

    private static native int pseudoInverse_9(long j, long j2, long j3, int i);

    private static native int qrPivot_0(long j, long j2, long j3, long j4, boolean z, boolean z2, double d);

    private static native int qrPivot_1(long j, long j2, long j3, long j4);

    private static native int qr_0(long j, long j2, long j3, boolean z, boolean z2, double d);

    private static native int qr_1(long j, long j2, long j3);

    private static native void setLapackMatrixMinSize_0(int i);

    private static native void solveByQR_0(long j, long j2, long j3);

    private static native long solveByQR_1(long j, long j2);

    private static native void solveBySVD_0(long j, long j2, long j3);

    private static native long solveBySVD_1(long j, long j2);

    private static native void stackColumns_0(long j, long j2);

    private static native long stackColumns_1(long j);

    private static native void stackRows_0(long j, long j2);

    private static native long stackRows_1(long j);

    private static native void stack_0(long j, long j2);

    private static native void stack_1(long j, long j2, long j3);

    private static native void stack_2(long j, long j2, long j3);

    private static native void stack_3(long j, long j2, long j3);

    private static native void stack_4(long j, long j2);

    private static native void stack_5(long j, long j2);

    private static native long stack_6(long j, long j2);

    private static native long stack_7(long j, long j2);

    private static native long stack_8(long j, long j2);

    private static native void sub2Matrices_0(long j, long j2, long j3);

    private static native void sub2Matrices_1(long j, long j2, long j3);

    private static native double sumSquare_0(long j);

    private static native double sum_0(long j);

    private static native void svdEigen3_0(long j, long j2, long j3);

    private static native void svdGsl_0(long j, long j2, long j3);

    private static native void svdLapack_0(long j, long j2, long j3);

    private static native void svdOpenCV_0(long j, long j2, long j3);

    private static native void svd_0(long j, long j2, long j3);

    private static native long t_0(long j);

    private static native String toString(long j);

    private static native void transpose_0(long j, long j2);

    private static native long transpose_1(long j);

    public VpMatrix(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static VpMatrix __fromPtr__(long j) {
        return new VpMatrix(j);
    }

    public VpMatrix(int i, int i2, double d) {
        this.nativeObj = VpMatrix_0(i, i2, d);
    }

    public VpMatrix(int i, int i2) {
        this.nativeObj = VpMatrix_1(i, i2);
    }

    public VpMatrix(VpMatrix vpMatrix) {
        this.nativeObj = VpMatrix_2(vpMatrix.nativeObj);
    }

    public VpMatrix(VpMatrix vpMatrix, int i, int i2, int i3, int i4) {
        this.nativeObj = VpMatrix_3(vpMatrix.nativeObj, i, i2, i3, i4);
    }

    public VpMatrix() {
        this.nativeObj = VpMatrix_4();
    }

    public double cond(double d) {
        return cond_0(this.nativeObj, d);
    }

    public double cond() {
        return cond_1(this.nativeObj);
    }

    public double detByLU() {
        return detByLU_0(this.nativeObj);
    }

    public double detByLUEigen3() {
        return detByLUEigen3_0(this.nativeObj);
    }

    public double detByLULapack() {
        return detByLULapack_0(this.nativeObj);
    }

    public double detByLUOpenCV() {
        return detByLUOpenCV_0(this.nativeObj);
    }

    public double frobeniusNorm() {
        return frobeniusNorm_0(this.nativeObj);
    }

    public double inducedL2Norm() {
        return inducedL2Norm_0(this.nativeObj);
    }

    public double infinityNorm() {
        return infinityNorm_0(this.nativeObj);
    }

    public double sum() {
        return sum_0(this.nativeObj);
    }

    public double sumSquare() {
        return sumSquare_0(this.nativeObj);
    }

    public static int getLapackMatrixMinSize() {
        return getLapackMatrixMinSize_0();
    }

    public int kernel(VpMatrix vpMatrix, double d) {
        return kernel_0(this.nativeObj, vpMatrix.nativeObj, d);
    }

    public int kernel(VpMatrix vpMatrix) {
        return kernel_1(this.nativeObj, vpMatrix.nativeObj);
    }

    public int nullSpace(VpMatrix vpMatrix, double d) {
        return nullSpace_0(this.nativeObj, vpMatrix.nativeObj, d);
    }

    public int nullSpace(VpMatrix vpMatrix) {
        return nullSpace_1(this.nativeObj, vpMatrix.nativeObj);
    }

    public int nullSpace(VpMatrix vpMatrix, int i) {
        return nullSpace_2(this.nativeObj, vpMatrix.nativeObj, i);
    }

    public int pseudoInverse(VpMatrix vpMatrix, double d) {
        return pseudoInverse_0(this.nativeObj, vpMatrix.nativeObj, d);
    }

    public int pseudoInverse(VpMatrix vpMatrix) {
        return pseudoInverse_1(this.nativeObj, vpMatrix.nativeObj);
    }

    public int pseudoInverse(VpMatrix vpMatrix, int i) {
        return pseudoInverse_2(this.nativeObj, vpMatrix.nativeObj, i);
    }

    public int pseudoInverse(VpMatrix vpMatrix, VpColVector vpColVector, double d, VpMatrix vpMatrix2, VpMatrix vpMatrix3, VpMatrix vpMatrix4) {
        return pseudoInverse_3(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, d, vpMatrix2.nativeObj, vpMatrix3.nativeObj, vpMatrix4.nativeObj);
    }

    public int pseudoInverse(VpMatrix vpMatrix, VpColVector vpColVector, double d, VpMatrix vpMatrix2, VpMatrix vpMatrix3) {
        return pseudoInverse_4(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, d, vpMatrix2.nativeObj, vpMatrix3.nativeObj);
    }

    public int pseudoInverse(VpMatrix vpMatrix, VpColVector vpColVector, double d) {
        return pseudoInverse_5(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, d);
    }

    public int pseudoInverse(VpMatrix vpMatrix, VpColVector vpColVector) {
        return pseudoInverse_6(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj);
    }

    public int pseudoInverse(VpMatrix vpMatrix, VpColVector vpColVector, int i, VpMatrix vpMatrix2, VpMatrix vpMatrix3, VpMatrix vpMatrix4) {
        return pseudoInverse_7(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, i, vpMatrix2.nativeObj, vpMatrix3.nativeObj, vpMatrix4.nativeObj);
    }

    public int pseudoInverse(VpMatrix vpMatrix, VpColVector vpColVector, int i, VpMatrix vpMatrix2, VpMatrix vpMatrix3) {
        return pseudoInverse_8(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, i, vpMatrix2.nativeObj, vpMatrix3.nativeObj);
    }

    public int pseudoInverse(VpMatrix vpMatrix, VpColVector vpColVector, int i) {
        return pseudoInverse_9(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, i);
    }

    public int pseudoInverseEigen3(VpMatrix vpMatrix, double d) {
        return pseudoInverseEigen3_0(this.nativeObj, vpMatrix.nativeObj, d);
    }

    public int pseudoInverseEigen3(VpMatrix vpMatrix) {
        return pseudoInverseEigen3_1(this.nativeObj, vpMatrix.nativeObj);
    }

    public int pseudoInverseEigen3(VpMatrix vpMatrix, int i) {
        return pseudoInverseEigen3_2(this.nativeObj, vpMatrix.nativeObj, i);
    }

    public int pseudoInverseEigen3(VpMatrix vpMatrix, VpColVector vpColVector, double d, VpMatrix vpMatrix2, VpMatrix vpMatrix3, VpMatrix vpMatrix4) {
        return pseudoInverseEigen3_3(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, d, vpMatrix2.nativeObj, vpMatrix3.nativeObj, vpMatrix4.nativeObj);
    }

    public int pseudoInverseEigen3(VpMatrix vpMatrix, VpColVector vpColVector, double d) {
        return pseudoInverseEigen3_4(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, d);
    }

    public int pseudoInverseEigen3(VpMatrix vpMatrix, VpColVector vpColVector) {
        return pseudoInverseEigen3_5(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj);
    }

    public int pseudoInverseEigen3(VpMatrix vpMatrix, VpColVector vpColVector, int i, VpMatrix vpMatrix2, VpMatrix vpMatrix3, VpMatrix vpMatrix4) {
        return pseudoInverseEigen3_6(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, i, vpMatrix2.nativeObj, vpMatrix3.nativeObj, vpMatrix4.nativeObj);
    }

    public int pseudoInverseEigen3(VpMatrix vpMatrix, VpColVector vpColVector, int i) {
        return pseudoInverseEigen3_7(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, i);
    }

    @Deprecated
    public int pseudoInverseGsl(VpMatrix vpMatrix, double d) {
        return pseudoInverseGsl_0(this.nativeObj, vpMatrix.nativeObj, d);
    }

    @Deprecated
    public int pseudoInverseGsl(VpMatrix vpMatrix) {
        return pseudoInverseGsl_1(this.nativeObj, vpMatrix.nativeObj);
    }

    @Deprecated
    public int pseudoInverseGsl(VpMatrix vpMatrix, VpColVector vpColVector, double d, VpMatrix vpMatrix2, VpMatrix vpMatrix3, VpMatrix vpMatrix4) {
        return pseudoInverseGsl_2(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, d, vpMatrix2.nativeObj, vpMatrix3.nativeObj, vpMatrix4.nativeObj);
    }

    @Deprecated
    public int pseudoInverseGsl(VpMatrix vpMatrix, VpColVector vpColVector, double d) {
        return pseudoInverseGsl_3(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, d);
    }

    @Deprecated
    public int pseudoInverseGsl(VpMatrix vpMatrix, VpColVector vpColVector) {
        return pseudoInverseGsl_4(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj);
    }

    public int pseudoInverseLapack(VpMatrix vpMatrix, double d) {
        return pseudoInverseLapack_0(this.nativeObj, vpMatrix.nativeObj, d);
    }

    public int pseudoInverseLapack(VpMatrix vpMatrix) {
        return pseudoInverseLapack_1(this.nativeObj, vpMatrix.nativeObj);
    }

    public int pseudoInverseLapack(VpMatrix vpMatrix, int i) {
        return pseudoInverseLapack_2(this.nativeObj, vpMatrix.nativeObj, i);
    }

    public int pseudoInverseLapack(VpMatrix vpMatrix, VpColVector vpColVector, double d, VpMatrix vpMatrix2, VpMatrix vpMatrix3, VpMatrix vpMatrix4) {
        return pseudoInverseLapack_3(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, d, vpMatrix2.nativeObj, vpMatrix3.nativeObj, vpMatrix4.nativeObj);
    }

    public int pseudoInverseLapack(VpMatrix vpMatrix, VpColVector vpColVector, double d) {
        return pseudoInverseLapack_4(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, d);
    }

    public int pseudoInverseLapack(VpMatrix vpMatrix, VpColVector vpColVector) {
        return pseudoInverseLapack_5(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj);
    }

    public int pseudoInverseLapack(VpMatrix vpMatrix, VpColVector vpColVector, int i, VpMatrix vpMatrix2, VpMatrix vpMatrix3, VpMatrix vpMatrix4) {
        return pseudoInverseLapack_6(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, i, vpMatrix2.nativeObj, vpMatrix3.nativeObj, vpMatrix4.nativeObj);
    }

    public int pseudoInverseLapack(VpMatrix vpMatrix, VpColVector vpColVector, int i) {
        return pseudoInverseLapack_7(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, i);
    }

    public int pseudoInverseOpenCV(VpMatrix vpMatrix, double d) {
        return pseudoInverseOpenCV_0(this.nativeObj, vpMatrix.nativeObj, d);
    }

    public int pseudoInverseOpenCV(VpMatrix vpMatrix) {
        return pseudoInverseOpenCV_1(this.nativeObj, vpMatrix.nativeObj);
    }

    public int pseudoInverseOpenCV(VpMatrix vpMatrix, int i) {
        return pseudoInverseOpenCV_2(this.nativeObj, vpMatrix.nativeObj, i);
    }

    public int pseudoInverseOpenCV(VpMatrix vpMatrix, VpColVector vpColVector, double d, VpMatrix vpMatrix2, VpMatrix vpMatrix3, VpMatrix vpMatrix4) {
        return pseudoInverseOpenCV_3(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, d, vpMatrix2.nativeObj, vpMatrix3.nativeObj, vpMatrix4.nativeObj);
    }

    public int pseudoInverseOpenCV(VpMatrix vpMatrix, VpColVector vpColVector, double d) {
        return pseudoInverseOpenCV_4(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, d);
    }

    public int pseudoInverseOpenCV(VpMatrix vpMatrix, VpColVector vpColVector) {
        return pseudoInverseOpenCV_5(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj);
    }

    public int pseudoInverseOpenCV(VpMatrix vpMatrix, VpColVector vpColVector, int i, VpMatrix vpMatrix2, VpMatrix vpMatrix3, VpMatrix vpMatrix4) {
        return pseudoInverseOpenCV_6(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, i, vpMatrix2.nativeObj, vpMatrix3.nativeObj, vpMatrix4.nativeObj);
    }

    public int pseudoInverseOpenCV(VpMatrix vpMatrix, VpColVector vpColVector, int i) {
        return pseudoInverseOpenCV_7(this.nativeObj, vpMatrix.nativeObj, vpColVector.nativeObj, i);
    }

    public int qr(VpMatrix vpMatrix, VpMatrix vpMatrix2, boolean z, boolean z2, double d) {
        return qr_0(this.nativeObj, vpMatrix.nativeObj, vpMatrix2.nativeObj, z, z2, d);
    }

    public int qr(VpMatrix vpMatrix, VpMatrix vpMatrix2) {
        return qr_1(this.nativeObj, vpMatrix.nativeObj, vpMatrix2.nativeObj);
    }

    public int qrPivot(VpMatrix vpMatrix, VpMatrix vpMatrix2, VpMatrix vpMatrix3, boolean z, boolean z2, double d) {
        return qrPivot_0(this.nativeObj, vpMatrix.nativeObj, vpMatrix2.nativeObj, vpMatrix3.nativeObj, z, z2, d);
    }

    public int qrPivot(VpMatrix vpMatrix, VpMatrix vpMatrix2, VpMatrix vpMatrix3) {
        return qrPivot_1(this.nativeObj, vpMatrix.nativeObj, vpMatrix2.nativeObj, vpMatrix3.nativeObj);
    }

    public void AAt(VpMatrix vpMatrix) {
        AAt_0(this.nativeObj, vpMatrix.nativeObj);
    }

    public void AtA(VpMatrix vpMatrix) {
        AtA_0(this.nativeObj, vpMatrix.nativeObj);
    }

    public static void add2Matrices(VpColVector vpColVector, VpColVector vpColVector2, VpColVector vpColVector3) {
        add2Matrices_0(vpColVector.nativeObj, vpColVector2.nativeObj, vpColVector3.nativeObj);
    }

    public static void add2Matrices(VpMatrix vpMatrix, VpMatrix vpMatrix2, VpMatrix vpMatrix3) {
        add2Matrices_1(vpMatrix.nativeObj, vpMatrix2.nativeObj, vpMatrix3.nativeObj);
    }

    public static void add2WeightedMatrices(VpMatrix vpMatrix, double d, VpMatrix vpMatrix2, double d2, VpMatrix vpMatrix3) {
        add2WeightedMatrices_0(vpMatrix.nativeObj, d, vpMatrix2.nativeObj, d2, vpMatrix3.nativeObj);
    }

    public void clear() {
        clear_0(this.nativeObj);
    }

    public static void computeHLM(VpMatrix vpMatrix, double d, VpMatrix vpMatrix2) {
        computeHLM_0(vpMatrix.nativeObj, d, vpMatrix2.nativeObj);
    }

    public static void createDiagonalMatrix(VpColVector vpColVector, VpMatrix vpMatrix) {
        createDiagonalMatrix_0(vpColVector.nativeObj, vpMatrix.nativeObj);
    }

    public void diag(double d) {
        diag_0(this.nativeObj, d);
    }

    public void diag() {
        diag_1(this.nativeObj);
    }

    public void diag(VpColVector vpColVector) {
        diag_2(this.nativeObj, vpColVector.nativeObj);
    }

    public void eigenValues(VpColVector vpColVector, VpMatrix vpMatrix) {
        eigenValues_0(this.nativeObj, vpColVector.nativeObj, vpMatrix.nativeObj);
    }

    public void eye(int i, int i2) {
        eye_0(this.nativeObj, i, i2);
    }

    public void eye(int i) {
        eye_1(this.nativeObj, i);
    }

    public void eye() {
        eye_2(this.nativeObj);
    }

    public void init(VpMatrix vpMatrix, int i, int i2, int i3, int i4) {
        init_0(this.nativeObj, vpMatrix.nativeObj, i, i2, i3, i4);
    }

    public void insert(VpMatrix vpMatrix, int i, int i2) {
        insert_0(this.nativeObj, vpMatrix.nativeObj, i, i2);
    }

    public static void insert(VpMatrix vpMatrix, VpMatrix vpMatrix2, VpMatrix vpMatrix3, int i, int i2) {
        insert_1(vpMatrix.nativeObj, vpMatrix2.nativeObj, vpMatrix3.nativeObj, i, i2);
    }

    public static void juxtaposeMatrices(VpMatrix vpMatrix, VpMatrix vpMatrix2, VpMatrix vpMatrix3) {
        juxtaposeMatrices_0(vpMatrix.nativeObj, vpMatrix2.nativeObj, vpMatrix3.nativeObj);
    }

    public static void kron(VpMatrix vpMatrix, VpMatrix vpMatrix2, VpMatrix vpMatrix3) {
        kron_0(vpMatrix.nativeObj, vpMatrix2.nativeObj, vpMatrix3.nativeObj);
    }

    public void kron(VpMatrix vpMatrix, VpMatrix vpMatrix2) {
        kron_1(this.nativeObj, vpMatrix.nativeObj, vpMatrix2.nativeObj);
    }

    public static void mult2Matrices(VpMatrix vpMatrix, VpColVector vpColVector, VpColVector vpColVector2) {
        mult2Matrices_0(vpMatrix.nativeObj, vpColVector.nativeObj, vpColVector2.nativeObj);
    }

    public static void mult2Matrices(VpMatrix vpMatrix, VpMatrix vpMatrix2, VpHomogeneousMatrix vpHomogeneousMatrix) {
        mult2Matrices_1(vpMatrix.nativeObj, vpMatrix2.nativeObj, vpHomogeneousMatrix.nativeObj);
    }

    public static void mult2Matrices(VpMatrix vpMatrix, VpMatrix vpMatrix2, VpMatrix vpMatrix3) {
        mult2Matrices_2(vpMatrix.nativeObj, vpMatrix2.nativeObj, vpMatrix3.nativeObj);
    }

    public static void multMatrixVector(VpMatrix vpMatrix, VpColVector vpColVector, VpColVector vpColVector2) {
        multMatrixVector_0(vpMatrix.nativeObj, vpColVector.nativeObj, vpColVector2.nativeObj);
    }

    public static void negateMatrix(VpMatrix vpMatrix, VpMatrix vpMatrix2) {
        negateMatrix_0(vpMatrix.nativeObj, vpMatrix2.nativeObj);
    }

    public void printSize() {
        printSize_0(this.nativeObj);
    }

    public static void setLapackMatrixMinSize(int i) {
        setLapackMatrixMinSize_0(i);
    }

    public void solveByQR(VpColVector vpColVector, VpColVector vpColVector2) {
        solveByQR_0(this.nativeObj, vpColVector.nativeObj, vpColVector2.nativeObj);
    }

    public void solveBySVD(VpColVector vpColVector, VpColVector vpColVector2) {
        solveBySVD_0(this.nativeObj, vpColVector.nativeObj, vpColVector2.nativeObj);
    }

    public void stack(VpColVector vpColVector) {
        stack_0(this.nativeObj, vpColVector.nativeObj);
    }

    public static void stack(VpMatrix vpMatrix, VpColVector vpColVector, VpMatrix vpMatrix2) {
        stack_1(vpMatrix.nativeObj, vpColVector.nativeObj, vpMatrix2.nativeObj);
    }

    public static void stack(VpMatrix vpMatrix, VpMatrix vpMatrix2, VpMatrix vpMatrix3) {
        stack_2(vpMatrix.nativeObj, vpMatrix2.nativeObj, vpMatrix3.nativeObj);
    }

    public static void stack(VpMatrix vpMatrix, VpRowVector vpRowVector, VpMatrix vpMatrix2) {
        stack_3(vpMatrix.nativeObj, vpRowVector.nativeObj, vpMatrix2.nativeObj);
    }

    public void stack(VpMatrix vpMatrix) {
        stack_4(this.nativeObj, vpMatrix.nativeObj);
    }

    public void stack(VpRowVector vpRowVector) {
        stack_5(this.nativeObj, vpRowVector.nativeObj);
    }

    public void stackColumns(VpColVector vpColVector) {
        stackColumns_0(this.nativeObj, vpColVector.nativeObj);
    }

    public void stackRows(VpRowVector vpRowVector) {
        stackRows_0(this.nativeObj, vpRowVector.nativeObj);
    }

    public static void sub2Matrices(VpColVector vpColVector, VpColVector vpColVector2, VpColVector vpColVector3) {
        sub2Matrices_0(vpColVector.nativeObj, vpColVector2.nativeObj, vpColVector3.nativeObj);
    }

    public static void sub2Matrices(VpMatrix vpMatrix, VpMatrix vpMatrix2, VpMatrix vpMatrix3) {
        sub2Matrices_1(vpMatrix.nativeObj, vpMatrix2.nativeObj, vpMatrix3.nativeObj);
    }

    public void svd(VpColVector vpColVector, VpMatrix vpMatrix) {
        svd_0(this.nativeObj, vpColVector.nativeObj, vpMatrix.nativeObj);
    }

    public void svdEigen3(VpColVector vpColVector, VpMatrix vpMatrix) {
        svdEigen3_0(this.nativeObj, vpColVector.nativeObj, vpMatrix.nativeObj);
    }

    @Deprecated
    public void svdGsl(VpColVector vpColVector, VpMatrix vpMatrix) {
        svdGsl_0(this.nativeObj, vpColVector.nativeObj, vpMatrix.nativeObj);
    }

    public void svdLapack(VpColVector vpColVector, VpMatrix vpMatrix) {
        svdLapack_0(this.nativeObj, vpColVector.nativeObj, vpMatrix.nativeObj);
    }

    public void svdOpenCV(VpColVector vpColVector, VpMatrix vpMatrix) {
        svdOpenCV_0(this.nativeObj, vpColVector.nativeObj, vpMatrix.nativeObj);
    }

    public void transpose(VpMatrix vpMatrix) {
        transpose_0(this.nativeObj, vpMatrix.nativeObj);
    }

    public VpColVector eigenValues() {
        return new VpColVector(eigenValues_1(this.nativeObj));
    }

    public VpColVector getCol(int i, int i2, int i3) {
        return new VpColVector(getCol_0(this.nativeObj, i, i2, i3));
    }

    public VpColVector getCol(int i) {
        return new VpColVector(getCol_1(this.nativeObj, i));
    }

    public VpColVector getDiag() {
        return new VpColVector(getDiag_0(this.nativeObj));
    }

    public VpColVector solveByQR(VpColVector vpColVector) {
        return new VpColVector(solveByQR_1(this.nativeObj, vpColVector.nativeObj));
    }

    public VpColVector solveBySVD(VpColVector vpColVector) {
        return new VpColVector(solveBySVD_1(this.nativeObj, vpColVector.nativeObj));
    }

    public VpColVector stackColumns() {
        return new VpColVector(stackColumns_1(this.nativeObj));
    }

    public VpMatrix AAt() {
        return new VpMatrix(AAt_1(this.nativeObj));
    }

    public VpMatrix AtA() {
        return new VpMatrix(AtA_1(this.nativeObj));
    }

    public static VpMatrix computeCovarianceMatrix(VpMatrix vpMatrix, VpColVector vpColVector, VpColVector vpColVector2, VpMatrix vpMatrix2) {
        return new VpMatrix(computeCovarianceMatrix_0(vpMatrix.nativeObj, vpColVector.nativeObj, vpColVector2.nativeObj, vpMatrix2.nativeObj));
    }

    public static VpMatrix computeCovarianceMatrix(VpMatrix vpMatrix, VpColVector vpColVector, VpColVector vpColVector2) {
        return new VpMatrix(computeCovarianceMatrix_1(vpMatrix.nativeObj, vpColVector.nativeObj, vpColVector2.nativeObj));
    }

    public static VpMatrix computeCovarianceMatrixVVS(VpHomogeneousMatrix vpHomogeneousMatrix, VpColVector vpColVector, VpMatrix vpMatrix, VpMatrix vpMatrix2) {
        return new VpMatrix(computeCovarianceMatrixVVS_0(vpHomogeneousMatrix.nativeObj, vpColVector.nativeObj, vpMatrix.nativeObj, vpMatrix2.nativeObj));
    }

    public static VpMatrix computeCovarianceMatrixVVS(VpHomogeneousMatrix vpHomogeneousMatrix, VpColVector vpColVector, VpMatrix vpMatrix) {
        return new VpMatrix(computeCovarianceMatrixVVS_1(vpHomogeneousMatrix.nativeObj, vpColVector.nativeObj, vpMatrix.nativeObj));
    }

    public VpMatrix expm() {
        return new VpMatrix(expm_0(this.nativeObj));
    }

    public VpMatrix extract(int i, int i2, int i3, int i4) {
        return new VpMatrix(extract_0(this.nativeObj, i, i2, i3, i4));
    }

    public VpMatrix hadamard(VpMatrix vpMatrix) {
        return new VpMatrix(hadamard_0(this.nativeObj, vpMatrix.nativeObj));
    }

    public static VpMatrix insert(VpMatrix vpMatrix, VpMatrix vpMatrix2, int i, int i2) {
        return new VpMatrix(insert_2(vpMatrix.nativeObj, vpMatrix2.nativeObj, i, i2));
    }

    public VpMatrix inverseByCholesky() {
        return new VpMatrix(inverseByCholesky_0(this.nativeObj));
    }

    @Deprecated
    public VpMatrix inverseByCholeskyGsl() {
        return new VpMatrix(inverseByCholeskyGsl_0(this.nativeObj));
    }

    public VpMatrix inverseByCholeskyLapack() {
        return new VpMatrix(inverseByCholeskyLapack_0(this.nativeObj));
    }

    public VpMatrix inverseByCholeskyOpenCV() {
        return new VpMatrix(inverseByCholeskyOpenCV_0(this.nativeObj));
    }

    public VpMatrix inverseByLU() {
        return new VpMatrix(inverseByLU_0(this.nativeObj));
    }

    public VpMatrix inverseByLUEigen3() {
        return new VpMatrix(inverseByLUEigen3_0(this.nativeObj));
    }

    public VpMatrix inverseByLULapack() {
        return new VpMatrix(inverseByLULapack_0(this.nativeObj));
    }

    public VpMatrix inverseByLUOpenCV() {
        return new VpMatrix(inverseByLUOpenCV_0(this.nativeObj));
    }

    public VpMatrix inverseByQR() {
        return new VpMatrix(inverseByQR_0(this.nativeObj));
    }

    @Deprecated
    public VpMatrix inverseByQRGsl() {
        return new VpMatrix(inverseByQRGsl_0(this.nativeObj));
    }

    public VpMatrix inverseByQRLapack() {
        return new VpMatrix(inverseByQRLapack_0(this.nativeObj));
    }

    public VpMatrix inverseTriangular(boolean z) {
        return new VpMatrix(inverseTriangular_0(this.nativeObj, z));
    }

    public VpMatrix inverseTriangular() {
        return new VpMatrix(inverseTriangular_1(this.nativeObj));
    }

    public static VpMatrix juxtaposeMatrices(VpMatrix vpMatrix, VpMatrix vpMatrix2) {
        return new VpMatrix(juxtaposeMatrices_1(vpMatrix.nativeObj, vpMatrix2.nativeObj));
    }

    public VpMatrix kron(VpMatrix vpMatrix) {
        return new VpMatrix(kron_2(this.nativeObj, vpMatrix.nativeObj));
    }

    public VpMatrix pseudoInverse(double d) {
        return new VpMatrix(pseudoInverse_10(this.nativeObj, d));
    }

    public VpMatrix pseudoInverse() {
        return new VpMatrix(pseudoInverse_11(this.nativeObj));
    }

    public VpMatrix pseudoInverse(int i) {
        return new VpMatrix(pseudoInverse_12(this.nativeObj, i));
    }

    public VpMatrix pseudoInverseEigen3(double d) {
        return new VpMatrix(pseudoInverseEigen3_8(this.nativeObj, d));
    }

    public VpMatrix pseudoInverseEigen3() {
        return new VpMatrix(pseudoInverseEigen3_9(this.nativeObj));
    }

    public VpMatrix pseudoInverseEigen3(int i) {
        return new VpMatrix(pseudoInverseEigen3_10(this.nativeObj, i));
    }

    @Deprecated
    public VpMatrix pseudoInverseGsl(double d) {
        return new VpMatrix(pseudoInverseGsl_5(this.nativeObj, d));
    }

    @Deprecated
    public VpMatrix pseudoInverseGsl() {
        return new VpMatrix(pseudoInverseGsl_6(this.nativeObj));
    }

    public VpMatrix pseudoInverseLapack(double d) {
        return new VpMatrix(pseudoInverseLapack_8(this.nativeObj, d));
    }

    public VpMatrix pseudoInverseLapack() {
        return new VpMatrix(pseudoInverseLapack_9(this.nativeObj));
    }

    public VpMatrix pseudoInverseLapack(int i) {
        return new VpMatrix(pseudoInverseLapack_10(this.nativeObj, i));
    }

    public VpMatrix pseudoInverseOpenCV(double d) {
        return new VpMatrix(pseudoInverseOpenCV_8(this.nativeObj, d));
    }

    public VpMatrix pseudoInverseOpenCV() {
        return new VpMatrix(pseudoInverseOpenCV_9(this.nativeObj));
    }

    public VpMatrix pseudoInverseOpenCV(int i) {
        return new VpMatrix(pseudoInverseOpenCV_10(this.nativeObj, i));
    }

    public static VpMatrix stack(VpMatrix vpMatrix, VpColVector vpColVector) {
        return new VpMatrix(stack_6(vpMatrix.nativeObj, vpColVector.nativeObj));
    }

    public static VpMatrix stack(VpMatrix vpMatrix, VpMatrix vpMatrix2) {
        return new VpMatrix(stack_7(vpMatrix.nativeObj, vpMatrix2.nativeObj));
    }

    public static VpMatrix stack(VpMatrix vpMatrix, VpRowVector vpRowVector) {
        return new VpMatrix(stack_8(vpMatrix.nativeObj, vpRowVector.nativeObj));
    }

    public VpMatrix t() {
        return new VpMatrix(t_0(this.nativeObj));
    }

    public VpMatrix transpose() {
        return new VpMatrix(transpose_1(this.nativeObj));
    }

    public VpRowVector getRow(int i, int i2, int i3) {
        return new VpRowVector(getRow_0(this.nativeObj, i, i2, i3));
    }

    public VpRowVector getRow(int i) {
        return new VpRowVector(getRow_1(this.nativeObj, i));
    }

    public VpRowVector stackRows() {
        return new VpRowVector(stackRows_1(this.nativeObj));
    }

    public String toString() {
        return toString(this.nativeObj);
    }

    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
