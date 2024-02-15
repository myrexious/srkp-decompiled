package org.visp.mbt;

import org.visp.core.VpCameraParameters;
import org.visp.core.VpColVector;
import org.visp.core.VpHomogeneousMatrix;
import org.visp.core.VpImageRGBa;
import org.visp.core.VpImageUChar;
import org.visp.utils.Converters;

/* loaded from: classes4.dex */
public class VpMbGenericTracker {
    public final long nativeObj;

    private static native long VpMbGenericTracker_0();

    private static native long VpMbGenericTracker_1(int i, int i2);

    private static native long VpMbGenericTracker_2(int[] iArr);

    private static native long VpMbGenericTracker_3(String[] strArr, int[] iArr);

    private static native double computeCurrentProjectionError_0(long j, long j2, long j3, long j4);

    private static native double computeCurrentProjectionError_1(long j, long j2, long j3, long j4);

    private static native void delete(long j);

    private static native String[] getCameraNames(long j);

    private static native void getCameraParameters_0(long j, long j2);

    private static native void getCameraParameters_1(long j, long j2, long j3);

    private static native void getCameraParameters_2(long j, String[] strArr, long[] jArr);

    private static native double[][][] getFeaturesForDisplay(long j);

    private static native double getGoodMovingEdgesRatioThreshold_0(long j);

    private static native double[][][] getModelForDisplay(long j, String[] strArr, int[] iArr, int[] iArr2, long[] jArr, long[] jArr2, boolean z);

    private static native int getNbFeaturesDepthDense_0(long j);

    private static native int getNbFeaturesDepthNormal_0(long j);

    private static native int getNbFeaturesEdge_0(long j);

    private static native int getNbFeaturesKlt_0(long j);

    private static native int getNbPoints_0(long j, int i);

    private static native int getNbPoints_1(long j);

    private static native int getNbPolygon_0(long j);

    private static native void getPose_0(long j, long j2);

    private static native void getPose_1(long j, long j2, long j3);

    private static native void getPose_2(long j, String[] strArr, long[] jArr);

    private static native String getReferenceCameraName(long j);

    private static native int getTrackerType_0(long j);

    private static native void initClick_0(long j, long j2, long j3, String str, String str2, boolean z, long j4, long j5);

    private static native void initClick_1(long j, long j2, long j3, String str, String str2);

    private static native void initClick_2(long j, long j2, long j3, String str, String str2, boolean z, long j4, long j5);

    private static native void initClick_3(long j, long j2, long j3, String str, String str2);

    private static native void initFromPoints_0(long j, long j2, long j3, String str, String str2);

    private static native void initFromPoints_1(long j, long j2, long j3, String str, String str2);

    private static native void initFromPose_0(long j, long j2, long j3);

    private static native void initFromPose_1(long j, long j2, long j3, long j4, long j5);

    private static native void initFromPose_2(long j, String[] strArr, long[] jArr, long[] jArr2);

    private static native void init_0(long j, long j2);

    private static native void loadConfigFile_0(long j, String str);

    private static native void loadConfigFile_1(long j, String str, String str2);

    private static native void loadConfigFile_2(long j, String[] strArr, String[] strArr2);

    private static native void loadModel_0(long j, String str, boolean z, long j2);

    private static native void loadModel_1(long j, String str);

    private static native void loadModel_2(long j, String str, String str2, boolean z, long j2, long j3);

    private static native void loadModel_3(long j, String str, String str2);

    private static native void loadModel_4(long j, String[] strArr, String[] strArr2, boolean z, long[] jArr);

    private static native void loadModel_5(long j, String[] strArr, String[] strArr2);

    private static native void reInitModel_0(long j, long j2, long j3, String str, String str2, long j4, long j5, boolean z, long j6, long j7);

    private static native void reInitModel_1(long j, long j2, long j3, String str, String str2, long j4, long j5);

    private static native void reInitModel_2(long j, long j2, String str, long j3, boolean z, long j4);

    private static native void reInitModel_3(long j, long j2, String str, long j3);

    private static native void reInitModel_4(long j, long j2, long j3, String str, String str2, long j4, long j5, boolean z, long j6, long j7);

    private static native void reInitModel_5(long j, long j2, long j3, String str, String str2, long j4, long j5);

    private static native void reInitModel_6(long j, long j2, String str, long j3, boolean z, long j4);

    private static native void reInitModel_7(long j, long j2, String str, long j3);

    private static native void resetTracker_0(long j);

    private static native void saveConfigFile_0(long j, String str);

    private static native void setAngleAppear_0(long j, double d, double d2);

    private static native void setAngleAppear_1(long j, double d);

    private static native void setAngleDisappear_0(long j, double d, double d2);

    private static native void setAngleDisappear_1(long j, double d);

    private static native void setCameraParameters_0(long j, long j2, long j3);

    private static native void setCameraParameters_1(long j, long j2);

    private static native void setCameraTransformationMatrix_0(long j, String str, long j2);

    private static native void setClipping_0(long j, int i, int i2);

    private static native void setClipping_1(long j, int i);

    private static native void setDepthDenseFilteringMaxDistance_0(long j, double d);

    private static native void setDepthDenseFilteringMethod_0(long j, int i);

    private static native void setDepthDenseFilteringMinDistance_0(long j, double d);

    private static native void setDepthDenseFilteringOccupancyRatio_0(long j, double d);

    private static native void setDepthDenseSamplingStep_0(long j, int i, int i2);

    private static native void setDepthNormalPclPlaneEstimationMethod_0(long j, int i);

    private static native void setDepthNormalPclPlaneEstimationRansacMaxIter_0(long j, int i);

    private static native void setDepthNormalPclPlaneEstimationRansacThreshold_0(long j, double d);

    private static native void setDepthNormalSamplingStep_0(long j, int i, int i2);

    private static native void setDisplayFeatures_0(long j, boolean z);

    private static native void setFarClippingDistance_0(long j, double d, double d2);

    private static native void setFarClippingDistance_1(long j, double d);

    private static native void setGoodMovingEdgesRatioThreshold_0(long j, double d);

    private static native void setLod_0(long j, boolean z, String str);

    private static native void setLod_1(long j, boolean z);

    private static native void setMinLineLengthThresh_0(long j, double d, String str);

    private static native void setMinLineLengthThresh_1(long j, double d);

    private static native void setMinPolygonAreaThresh_0(long j, double d, String str);

    private static native void setMinPolygonAreaThresh_1(long j, double d);

    private static native void setNearClippingDistance_0(long j, double d, double d2);

    private static native void setNearClippingDistance_1(long j, double d);

    private static native void setOgreShowConfigDialog_0(long j, boolean z);

    private static native void setOgreVisibilityTest_0(long j, boolean z);

    private static native void setPose_0(long j, long j2, long j3, long j4, long j5);

    private static native void setPose_1(long j, long j2, long j3);

    private static native void setPose_2(long j, long j2, long j3, long j4, long j5);

    private static native void setPose_3(long j, long j2, long j3);

    private static native void setProjectionErrorComputation_0(long j, boolean z);

    private static native void setProjectionErrorDisplayArrowLength_0(long j, int i);

    private static native void setProjectionErrorDisplayArrowThickness_0(long j, int i);

    private static native void setProjectionErrorDisplay_0(long j, boolean z);

    private static native void setReferenceCameraName_0(long j, String str);

    private static native void setScanLineVisibilityTest_0(long j, boolean z);

    private static native void setTrackerType_0(long j, int i);

    private static native void setUseDepthDenseTracking_0(long j, String str, boolean z);

    private static native void setUseDepthNormalTracking_0(long j, String str, boolean z);

    private static native void setUseEdgeTracking_0(long j, String str, boolean z);

    private static native void testTracking_0(long j);

    private static native void track_0(long j, long j2);

    private static native void track_1(long j, long j2, long j3);

    private static native void track_2(long j, String[] strArr, long[] jArr, String[] strArr2, long[][] jArr2, int[] iArr, int[] iArr2);

    public VpMbGenericTracker(long j) {
        this.nativeObj = j;
    }

    public long getNativeObjAddr() {
        return this.nativeObj;
    }

    public static VpMbGenericTracker __fromPtr__(long j) {
        return new VpMbGenericTracker(j);
    }

    public double computeCurrentProjectionError(VpImageUChar vpImageUChar, VpHomogeneousMatrix vpHomogeneousMatrix, VpCameraParameters vpCameraParameters) {
        return computeCurrentProjectionError_0(this.nativeObj, vpImageUChar.nativeObj, vpHomogeneousMatrix.nativeObj, vpCameraParameters.nativeObj);
    }

    public double computeCurrentProjectionError(VpImageRGBa vpImageRGBa, VpHomogeneousMatrix vpHomogeneousMatrix, VpCameraParameters vpCameraParameters) {
        return computeCurrentProjectionError_1(this.nativeObj, vpImageRGBa.nativeObj, vpHomogeneousMatrix.nativeObj, vpCameraParameters.nativeObj);
    }

    public double getGoodMovingEdgesRatioThreshold() {
        return getGoodMovingEdgesRatioThreshold_0(this.nativeObj);
    }

    public int getNbFeaturesDepthDense() {
        return getNbFeaturesDepthDense_0(this.nativeObj);
    }

    public int getNbFeaturesDepthNormal() {
        return getNbFeaturesDepthNormal_0(this.nativeObj);
    }

    public int getNbFeaturesEdge() {
        return getNbFeaturesEdge_0(this.nativeObj);
    }

    public int getNbFeaturesKlt() {
        return getNbFeaturesKlt_0(this.nativeObj);
    }

    public int getNbPoints(int i) {
        return getNbPoints_0(this.nativeObj, i);
    }

    public int getNbPoints() {
        return getNbPoints_1(this.nativeObj);
    }

    public int getNbPolygon() {
        return getNbPolygon_0(this.nativeObj);
    }

    public int getTrackerType() {
        return getTrackerType_0(this.nativeObj);
    }

    public void init(VpImageUChar vpImageUChar) {
        init_0(this.nativeObj, vpImageUChar.nativeObj);
    }

    public void initClick(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2, String str, String str2, boolean z, VpHomogeneousMatrix vpHomogeneousMatrix, VpHomogeneousMatrix vpHomogeneousMatrix2) {
        initClick_0(this.nativeObj, vpImageUChar.nativeObj, vpImageUChar2.nativeObj, str, str2, z, vpHomogeneousMatrix.nativeObj, vpHomogeneousMatrix2.nativeObj);
    }

    public void initClick(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2, String str, String str2) {
        initClick_1(this.nativeObj, vpImageUChar.nativeObj, vpImageUChar2.nativeObj, str, str2);
    }

    public void initClick(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2, String str, String str2, boolean z, VpHomogeneousMatrix vpHomogeneousMatrix, VpHomogeneousMatrix vpHomogeneousMatrix2) {
        initClick_2(this.nativeObj, vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj, str, str2, z, vpHomogeneousMatrix.nativeObj, vpHomogeneousMatrix2.nativeObj);
    }

    public void initClick(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2, String str, String str2) {
        initClick_3(this.nativeObj, vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj, str, str2);
    }

    public void initFromPoints(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2, String str, String str2) {
        initFromPoints_0(this.nativeObj, vpImageUChar.nativeObj, vpImageUChar2.nativeObj, str, str2);
    }

    public void initFromPoints(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2, String str, String str2) {
        initFromPoints_1(this.nativeObj, vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj, str, str2);
    }

    public void reInitModel(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2, String str, String str2, VpHomogeneousMatrix vpHomogeneousMatrix, VpHomogeneousMatrix vpHomogeneousMatrix2, boolean z, VpHomogeneousMatrix vpHomogeneousMatrix3, VpHomogeneousMatrix vpHomogeneousMatrix4) {
        reInitModel_0(this.nativeObj, vpImageUChar.nativeObj, vpImageUChar2.nativeObj, str, str2, vpHomogeneousMatrix.nativeObj, vpHomogeneousMatrix2.nativeObj, z, vpHomogeneousMatrix3.nativeObj, vpHomogeneousMatrix4.nativeObj);
    }

    public void reInitModel(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2, String str, String str2, VpHomogeneousMatrix vpHomogeneousMatrix, VpHomogeneousMatrix vpHomogeneousMatrix2) {
        reInitModel_1(this.nativeObj, vpImageUChar.nativeObj, vpImageUChar2.nativeObj, str, str2, vpHomogeneousMatrix.nativeObj, vpHomogeneousMatrix2.nativeObj);
    }

    public void reInitModel(VpImageUChar vpImageUChar, String str, VpHomogeneousMatrix vpHomogeneousMatrix, boolean z, VpHomogeneousMatrix vpHomogeneousMatrix2) {
        reInitModel_2(this.nativeObj, vpImageUChar.nativeObj, str, vpHomogeneousMatrix.nativeObj, z, vpHomogeneousMatrix2.nativeObj);
    }

    public void reInitModel(VpImageUChar vpImageUChar, String str, VpHomogeneousMatrix vpHomogeneousMatrix) {
        reInitModel_3(this.nativeObj, vpImageUChar.nativeObj, str, vpHomogeneousMatrix.nativeObj);
    }

    public void reInitModel(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2, String str, String str2, VpHomogeneousMatrix vpHomogeneousMatrix, VpHomogeneousMatrix vpHomogeneousMatrix2, boolean z, VpHomogeneousMatrix vpHomogeneousMatrix3, VpHomogeneousMatrix vpHomogeneousMatrix4) {
        reInitModel_4(this.nativeObj, vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj, str, str2, vpHomogeneousMatrix.nativeObj, vpHomogeneousMatrix2.nativeObj, z, vpHomogeneousMatrix3.nativeObj, vpHomogeneousMatrix4.nativeObj);
    }

    public void reInitModel(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2, String str, String str2, VpHomogeneousMatrix vpHomogeneousMatrix, VpHomogeneousMatrix vpHomogeneousMatrix2) {
        reInitModel_5(this.nativeObj, vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj, str, str2, vpHomogeneousMatrix.nativeObj, vpHomogeneousMatrix2.nativeObj);
    }

    public void reInitModel(VpImageRGBa vpImageRGBa, String str, VpHomogeneousMatrix vpHomogeneousMatrix, boolean z, VpHomogeneousMatrix vpHomogeneousMatrix2) {
        reInitModel_6(this.nativeObj, vpImageRGBa.nativeObj, str, vpHomogeneousMatrix.nativeObj, z, vpHomogeneousMatrix2.nativeObj);
    }

    public void reInitModel(VpImageRGBa vpImageRGBa, String str, VpHomogeneousMatrix vpHomogeneousMatrix) {
        reInitModel_7(this.nativeObj, vpImageRGBa.nativeObj, str, vpHomogeneousMatrix.nativeObj);
    }

    public void resetTracker() {
        resetTracker_0(this.nativeObj);
    }

    public void saveConfigFile(String str) {
        saveConfigFile_0(this.nativeObj, str);
    }

    public void setAngleAppear(double d, double d2) {
        setAngleAppear_0(this.nativeObj, d, d2);
    }

    public void setAngleAppear(double d) {
        setAngleAppear_1(this.nativeObj, d);
    }

    public void setAngleDisappear(double d, double d2) {
        setAngleDisappear_0(this.nativeObj, d, d2);
    }

    public void setAngleDisappear(double d) {
        setAngleDisappear_1(this.nativeObj, d);
    }

    public void setCameraParameters(VpCameraParameters vpCameraParameters, VpCameraParameters vpCameraParameters2) {
        setCameraParameters_0(this.nativeObj, vpCameraParameters.nativeObj, vpCameraParameters2.nativeObj);
    }

    public void setCameraParameters(VpCameraParameters vpCameraParameters) {
        setCameraParameters_1(this.nativeObj, vpCameraParameters.nativeObj);
    }

    public void setCameraTransformationMatrix(String str, VpHomogeneousMatrix vpHomogeneousMatrix) {
        setCameraTransformationMatrix_0(this.nativeObj, str, vpHomogeneousMatrix.nativeObj);
    }

    public void setClipping(int i, int i2) {
        setClipping_0(this.nativeObj, i, i2);
    }

    public void setClipping(int i) {
        setClipping_1(this.nativeObj, i);
    }

    public void setDepthDenseFilteringMaxDistance(double d) {
        setDepthDenseFilteringMaxDistance_0(this.nativeObj, d);
    }

    public void setDepthDenseFilteringMethod(int i) {
        setDepthDenseFilteringMethod_0(this.nativeObj, i);
    }

    public void setDepthDenseFilteringMinDistance(double d) {
        setDepthDenseFilteringMinDistance_0(this.nativeObj, d);
    }

    public void setDepthDenseFilteringOccupancyRatio(double d) {
        setDepthDenseFilteringOccupancyRatio_0(this.nativeObj, d);
    }

    public void setDepthDenseSamplingStep(int i, int i2) {
        setDepthDenseSamplingStep_0(this.nativeObj, i, i2);
    }

    public void setDepthNormalPclPlaneEstimationMethod(int i) {
        setDepthNormalPclPlaneEstimationMethod_0(this.nativeObj, i);
    }

    public void setDepthNormalPclPlaneEstimationRansacMaxIter(int i) {
        setDepthNormalPclPlaneEstimationRansacMaxIter_0(this.nativeObj, i);
    }

    public void setDepthNormalPclPlaneEstimationRansacThreshold(double d) {
        setDepthNormalPclPlaneEstimationRansacThreshold_0(this.nativeObj, d);
    }

    public void setDepthNormalSamplingStep(int i, int i2) {
        setDepthNormalSamplingStep_0(this.nativeObj, i, i2);
    }

    public void setDisplayFeatures(boolean z) {
        setDisplayFeatures_0(this.nativeObj, z);
    }

    public void setFarClippingDistance(double d, double d2) {
        setFarClippingDistance_0(this.nativeObj, d, d2);
    }

    public void setFarClippingDistance(double d) {
        setFarClippingDistance_1(this.nativeObj, d);
    }

    public void setGoodMovingEdgesRatioThreshold(double d) {
        setGoodMovingEdgesRatioThreshold_0(this.nativeObj, d);
    }

    public void setLod(boolean z, String str) {
        setLod_0(this.nativeObj, z, str);
    }

    public void setLod(boolean z) {
        setLod_1(this.nativeObj, z);
    }

    public void setMinLineLengthThresh(double d, String str) {
        setMinLineLengthThresh_0(this.nativeObj, d, str);
    }

    public void setMinLineLengthThresh(double d) {
        setMinLineLengthThresh_1(this.nativeObj, d);
    }

    public void setMinPolygonAreaThresh(double d, String str) {
        setMinPolygonAreaThresh_0(this.nativeObj, d, str);
    }

    public void setMinPolygonAreaThresh(double d) {
        setMinPolygonAreaThresh_1(this.nativeObj, d);
    }

    public void setNearClippingDistance(double d, double d2) {
        setNearClippingDistance_0(this.nativeObj, d, d2);
    }

    public void setNearClippingDistance(double d) {
        setNearClippingDistance_1(this.nativeObj, d);
    }

    public void setOgreShowConfigDialog(boolean z) {
        setOgreShowConfigDialog_0(this.nativeObj, z);
    }

    public void setOgreVisibilityTest(boolean z) {
        setOgreVisibilityTest_0(this.nativeObj, z);
    }

    public void setPose(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2, VpHomogeneousMatrix vpHomogeneousMatrix, VpHomogeneousMatrix vpHomogeneousMatrix2) {
        setPose_0(this.nativeObj, vpImageUChar.nativeObj, vpImageUChar2.nativeObj, vpHomogeneousMatrix.nativeObj, vpHomogeneousMatrix2.nativeObj);
    }

    public void setPose(VpImageUChar vpImageUChar, VpHomogeneousMatrix vpHomogeneousMatrix) {
        setPose_1(this.nativeObj, vpImageUChar.nativeObj, vpHomogeneousMatrix.nativeObj);
    }

    public void setPose(VpImageRGBa vpImageRGBa, VpImageRGBa vpImageRGBa2, VpHomogeneousMatrix vpHomogeneousMatrix, VpHomogeneousMatrix vpHomogeneousMatrix2) {
        setPose_2(this.nativeObj, vpImageRGBa.nativeObj, vpImageRGBa2.nativeObj, vpHomogeneousMatrix.nativeObj, vpHomogeneousMatrix2.nativeObj);
    }

    public void setPose(VpImageRGBa vpImageRGBa, VpHomogeneousMatrix vpHomogeneousMatrix) {
        setPose_3(this.nativeObj, vpImageRGBa.nativeObj, vpHomogeneousMatrix.nativeObj);
    }

    public void setProjectionErrorComputation(boolean z) {
        setProjectionErrorComputation_0(this.nativeObj, z);
    }

    public void setProjectionErrorDisplay(boolean z) {
        setProjectionErrorDisplay_0(this.nativeObj, z);
    }

    public void setProjectionErrorDisplayArrowLength(int i) {
        setProjectionErrorDisplayArrowLength_0(this.nativeObj, i);
    }

    public void setProjectionErrorDisplayArrowThickness(int i) {
        setProjectionErrorDisplayArrowThickness_0(this.nativeObj, i);
    }

    public void setReferenceCameraName(String str) {
        setReferenceCameraName_0(this.nativeObj, str);
    }

    public void setScanLineVisibilityTest(boolean z) {
        setScanLineVisibilityTest_0(this.nativeObj, z);
    }

    public void setTrackerType(int i) {
        setTrackerType_0(this.nativeObj, i);
    }

    public void setUseDepthDenseTracking(String str, boolean z) {
        setUseDepthDenseTracking_0(this.nativeObj, str, z);
    }

    public void setUseDepthNormalTracking(String str, boolean z) {
        setUseDepthNormalTracking_0(this.nativeObj, str, z);
    }

    public void setUseEdgeTracking(String str, boolean z) {
        setUseEdgeTracking_0(this.nativeObj, str, z);
    }

    public void testTracking() {
        testTracking_0(this.nativeObj);
    }

    public VpMbGenericTracker() {
        this.nativeObj = VpMbGenericTracker_0();
    }

    public VpMbGenericTracker(int i, int i2) {
        this.nativeObj = VpMbGenericTracker_1(i, i2);
    }

    public VpMbGenericTracker(int[] iArr) {
        this.nativeObj = VpMbGenericTracker_2(iArr);
    }

    public VpMbGenericTracker(String[] strArr, int[] iArr) {
        this.nativeObj = VpMbGenericTracker_3(strArr, iArr);
    }

    public void getCameraParameters(VpCameraParameters vpCameraParameters) {
        getCameraParameters_0(this.nativeObj, vpCameraParameters.nativeObj);
    }

    public void getCameraParameters(VpCameraParameters vpCameraParameters, VpCameraParameters vpCameraParameters2) {
        getCameraParameters_1(this.nativeObj, vpCameraParameters.nativeObj, vpCameraParameters2.nativeObj);
    }

    public void getCameraParameters(String[] strArr, VpCameraParameters[] vpCameraParametersArr) {
        getCameraParameters_2(this.nativeObj, strArr, Converters.array_vpCameraParameters_to_array_native(vpCameraParametersArr));
    }

    public void getPose(VpHomogeneousMatrix vpHomogeneousMatrix) {
        getPose_0(this.nativeObj, vpHomogeneousMatrix.nativeObj);
    }

    public void getPose(VpHomogeneousMatrix vpHomogeneousMatrix, VpHomogeneousMatrix vpHomogeneousMatrix2) {
        getPose_1(this.nativeObj, vpHomogeneousMatrix.nativeObj, vpHomogeneousMatrix2.nativeObj);
    }

    public void getPose(String[] strArr, VpHomogeneousMatrix[] vpHomogeneousMatrixArr) {
        getPose_2(this.nativeObj, strArr, Converters.array_vpHomogeneousMatrix_to_array_native(vpHomogeneousMatrixArr));
    }

    public void initFromPose(VpImageUChar vpImageUChar, VpHomogeneousMatrix vpHomogeneousMatrix) {
        initFromPose_0(this.nativeObj, vpImageUChar.nativeObj, vpHomogeneousMatrix.nativeObj);
    }

    public void initFromPose(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2, VpHomogeneousMatrix vpHomogeneousMatrix, VpHomogeneousMatrix vpHomogeneousMatrix2) {
        initFromPose_1(this.nativeObj, vpImageUChar.nativeObj, vpImageUChar2.nativeObj, vpHomogeneousMatrix.nativeObj, vpHomogeneousMatrix2.nativeObj);
    }

    public void initFromPose(String[] strArr, VpImageUChar[] vpImageUCharArr, VpHomogeneousMatrix[] vpHomogeneousMatrixArr) {
        initFromPose_2(this.nativeObj, strArr, Converters.array_vpImageUChar_to_array_native(vpImageUCharArr), Converters.array_vpHomogeneousMatrix_to_array_native(vpHomogeneousMatrixArr));
    }

    public void loadConfigFile(String str) {
        loadConfigFile_0(this.nativeObj, str);
    }

    public void loadConfigFile(String str, String str2) {
        loadConfigFile_1(this.nativeObj, str, str2);
    }

    public void loadConfigFile(String[] strArr, String[] strArr2) {
        loadConfigFile_2(this.nativeObj, strArr, strArr2);
    }

    public void loadModel(String str, boolean z, VpHomogeneousMatrix vpHomogeneousMatrix) {
        loadModel_0(this.nativeObj, str, z, vpHomogeneousMatrix.nativeObj);
    }

    public void loadModel(String str) {
        loadModel_1(this.nativeObj, str);
    }

    public void loadModel(String str, String str2, boolean z, VpHomogeneousMatrix vpHomogeneousMatrix, VpHomogeneousMatrix vpHomogeneousMatrix2) {
        loadModel_2(this.nativeObj, str, str2, z, vpHomogeneousMatrix.nativeObj, vpHomogeneousMatrix2.nativeObj);
    }

    public void loadModel(String str, String str2) {
        loadModel_3(this.nativeObj, str, str2);
    }

    public void loadModel(String[] strArr, String[] strArr2, boolean z, VpHomogeneousMatrix[] vpHomogeneousMatrixArr) {
        loadModel_4(this.nativeObj, strArr, strArr2, z, Converters.array_vpHomogeneousMatrix_to_array_native(vpHomogeneousMatrixArr));
    }

    public void loadModel(String[] strArr, String[] strArr2) {
        loadModel_5(this.nativeObj, strArr, strArr2);
    }

    public void track(VpImageUChar vpImageUChar) {
        track_0(this.nativeObj, vpImageUChar.nativeObj);
    }

    public void track(VpImageUChar vpImageUChar, VpImageUChar vpImageUChar2) {
        track_1(this.nativeObj, vpImageUChar.nativeObj, vpImageUChar2.nativeObj);
    }

    public void track(String[] strArr, VpImageUChar[] vpImageUCharArr, String[] strArr2, VpColVector[][] vpColVectorArr, int[] iArr, int[] iArr2) {
        track_2(this.nativeObj, strArr, Converters.array_vpImageUChar_to_array_native(vpImageUCharArr), strArr2, Converters.matrix_vpColVector_to_matrix_native(vpColVectorArr), iArr, iArr2);
    }

    public double[][][] getModelForDisplay(String[] strArr, int[] iArr, int[] iArr2, VpHomogeneousMatrix[] vpHomogeneousMatrixArr, VpCameraParameters[] vpCameraParametersArr, boolean z) {
        return getModelForDisplay(this.nativeObj, strArr, iArr, iArr2, Converters.array_vpHomogeneousMatrix_to_array_native(vpHomogeneousMatrixArr), Converters.array_vpCameraParameters_to_array_native(vpCameraParametersArr), z);
    }

    public double[][][] getFeaturesForDisplay() {
        return getFeaturesForDisplay(this.nativeObj);
    }

    public String[] getCameraNames() {
        return getCameraNames(this.nativeObj);
    }

    public String getReferenceCameraName() {
        return getReferenceCameraName(this.nativeObj);
    }

    protected void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
