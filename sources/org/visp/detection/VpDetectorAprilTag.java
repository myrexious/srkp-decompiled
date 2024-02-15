package org.visp.detection;

import java.util.List;
import org.visp.core.VpCameraParameters;
import org.visp.core.VpHomogeneousMatrix;
import org.visp.core.VpImagePoint;
import org.visp.core.VpImageUChar;
import org.visp.utils.Converters;

/* loaded from: classes4.dex */
public class VpDetectorAprilTag {
    public final long nativeObj;

    private static native long VpDetectorAprilTag_0();

    private static native void delete(long j);

    private static native long[] detect_0(long j, long j2, double d, long j3);

    private static native boolean detect_1(long j, long j2);

    private static native int getPoseEstimationMethod(long j);

    private static native boolean getPose_0(long j, long j2, double d, long j3, long j4, long j5, double d2, double d3);

    private static native boolean getPose_1(long j, long j2, double d, long j3, long j4);

    private static native Object[] getTagsCorners(long j);

    private static native int[] getTagsId(long j);

    private static native void setAprilTagDecodeSharpening_0(long j, double d);

    private static native void setAprilTagFamily(long j, int i);

    private static native void setAprilTagNbThreads_0(long j, int i);

    private static native void setAprilTagPoseEstimationMethod(long j, int i);

    private static native void setAprilTagQuadDecimate_0(long j, float f);

    private static native void setAprilTagQuadSigma_0(long j, float f);

    private static native void setAprilTagRefineDecode_0(long j, boolean z);

    private static native void setAprilTagRefineEdges_0(long j, boolean z);

    private static native void setAprilTagRefinePose_0(long j, boolean z);

    private static native void setDisplayTag_0(long j, boolean z, int i);

    private static native void setDisplayTag_1(long j, boolean z);

    private static native void setZAlignedWithCameraAxis_0(long j, boolean z);

    public VpDetectorAprilTag(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static VpDetectorAprilTag __fromPtr__(long j) {
        return new VpDetectorAprilTag(j);
    }

    public boolean getPose(long j, double d, VpCameraParameters vpCameraParameters, VpHomogeneousMatrix vpHomogeneousMatrix, VpHomogeneousMatrix vpHomogeneousMatrix2, double d2, double d3) {
        return getPose_0(this.nativeObj, j, d, vpCameraParameters.nativeObj, vpHomogeneousMatrix.nativeObj, vpHomogeneousMatrix2.nativeObj, d2, d3);
    }

    public boolean getPose(long j, double d, VpCameraParameters vpCameraParameters, VpHomogeneousMatrix vpHomogeneousMatrix) {
        return getPose_1(this.nativeObj, j, d, vpCameraParameters.nativeObj, vpHomogeneousMatrix.nativeObj);
    }

    public void setAprilTagDecodeSharpening(double d) {
        setAprilTagDecodeSharpening_0(this.nativeObj, d);
    }

    public void setAprilTagNbThreads(int i) {
        setAprilTagNbThreads_0(this.nativeObj, i);
    }

    public void setAprilTagQuadDecimate(float f) {
        setAprilTagQuadDecimate_0(this.nativeObj, f);
    }

    public void setAprilTagQuadSigma(float f) {
        setAprilTagQuadSigma_0(this.nativeObj, f);
    }

    public void setAprilTagRefineDecode(boolean z) {
        setAprilTagRefineDecode_0(this.nativeObj, z);
    }

    public void setAprilTagRefineEdges(boolean z) {
        setAprilTagRefineEdges_0(this.nativeObj, z);
    }

    public void setAprilTagRefinePose(boolean z) {
        setAprilTagRefinePose_0(this.nativeObj, z);
    }

    public void setDisplayTag(boolean z, int i) {
        setDisplayTag_0(this.nativeObj, z, i);
    }

    public void setDisplayTag(boolean z) {
        setDisplayTag_1(this.nativeObj, z);
    }

    public void setZAlignedWithCameraAxis(boolean z) {
        setZAlignedWithCameraAxis_0(this.nativeObj, z);
    }

    public List<VpHomogeneousMatrix> detect(VpImageUChar vpImageUChar, double d, VpCameraParameters vpCameraParameters) {
        return Converters.Array_to_vector_vpHomogeneousMatrix(detect_0(this.nativeObj, vpImageUChar.nativeObj, d, vpCameraParameters.nativeObj));
    }

    public boolean detect(VpImageUChar vpImageUChar) {
        return detect_1(this.nativeObj, vpImageUChar.nativeObj);
    }

    public int getPoseEstimationMethod() {
        return getPoseEstimationMethod(this.nativeObj);
    }

    public void setAprilTagPoseEstimationMethod(int i) {
        setAprilTagPoseEstimationMethod(this.nativeObj, i);
    }

    public void setAprilTagFamily(int i) {
        setAprilTagFamily(this.nativeObj, i);
    }

    public List<List<VpImagePoint>> getTagsCorners() {
        Object[] tagsCorners = getTagsCorners(this.nativeObj);
        long[][] jArr = new long[tagsCorners.length];
        for (int i = 0; i < tagsCorners.length; i++) {
            Object obj = tagsCorners[i];
            if (obj instanceof long[]) {
                jArr[i] = (long[]) obj;
            }
        }
        return Converters.Array_Array_to_vector_vector_vpImagePoint(jArr);
    }

    public int[] getTagsId() {
        return getTagsId(this.nativeObj);
    }

    public VpDetectorAprilTag() {
        this.nativeObj = VpDetectorAprilTag_0();
    }

    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
