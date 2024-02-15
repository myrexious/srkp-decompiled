package org.informatika.sirekap.support.templatematching;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.opencv.core.Point;

/* compiled from: ImageDetectionProperties.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010R\u000e\u0010\r\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0015\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0017\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\u0014\u0010\u0018\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0013R\u0014\u0010\u0019\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0013R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/support/templatematching/ImageDetectionProperties;", "", "previewWidth", "", "previewHeight", "resultWidth", "resultHeight", "previewArea", "resultArea", "topLeftPoint", "Lorg/opencv/core/Point;", "topRightPoint", "bottomRightPoint", "bottomLeftPoint", "deviceAcceleration", "", "(DDDDDDLorg/opencv/core/Point;Lorg/opencv/core/Point;Lorg/opencv/core/Point;Lorg/opencv/core/Point;F)V", "isAnyEdgeDistorted", "", "()Z", "isCameraMoving", "isDetectedAreaAboveLimit", "isDetectedHeightAboveLimit", "isDetectedWidthAboveLimit", "isLeftEdgeDistorted", "isRightEdgeDistorted", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ImageDetectionProperties {
    private static final float ACCELERATION_MOVEMENT_THRESH = 1.0f;
    private static final int ANGLE_DISTORTION_THRESH = 200;
    private static final double AREA_LIMIT_UPPER_THRESHOLD = 0.98d;
    public static final Companion Companion = new Companion(null);
    private final Point bottomLeftPoint;
    private final Point bottomRightPoint;
    private final float deviceAcceleration;
    private final double previewArea;
    private final double previewHeight;
    private final double previewWidth;
    private final double resultArea;
    private final double resultHeight;
    private final double resultWidth;
    private final Point topLeftPoint;
    private final Point topRightPoint;

    public ImageDetectionProperties(double d, double d2, double d3, double d4, double d5, double d6, Point topLeftPoint, Point topRightPoint, Point bottomRightPoint, Point bottomLeftPoint, float f) {
        Intrinsics.checkNotNullParameter(topLeftPoint, "topLeftPoint");
        Intrinsics.checkNotNullParameter(topRightPoint, "topRightPoint");
        Intrinsics.checkNotNullParameter(bottomRightPoint, "bottomRightPoint");
        Intrinsics.checkNotNullParameter(bottomLeftPoint, "bottomLeftPoint");
        this.previewWidth = d;
        this.previewHeight = d2;
        this.resultWidth = d3;
        this.resultHeight = d4;
        this.previewArea = d5;
        this.resultArea = d6;
        this.topLeftPoint = topLeftPoint;
        this.topRightPoint = topRightPoint;
        this.bottomRightPoint = bottomRightPoint;
        this.bottomLeftPoint = bottomLeftPoint;
        this.deviceAcceleration = f;
    }

    public final boolean isDetectedAreaAboveLimit() {
        return this.resultArea > this.previewArea * AREA_LIMIT_UPPER_THRESHOLD;
    }

    public final boolean isDetectedWidthAboveLimit() {
        return this.resultWidth / this.previewWidth > AREA_LIMIT_UPPER_THRESHOLD;
    }

    public final boolean isDetectedHeightAboveLimit() {
        return this.resultHeight / this.previewHeight > AREA_LIMIT_UPPER_THRESHOLD;
    }

    public final boolean isCameraMoving() {
        return this.deviceAcceleration > 1.0f;
    }

    private final boolean isRightEdgeDistorted() {
        return Math.abs(this.topRightPoint.y - this.bottomRightPoint.y) > 200.0d;
    }

    private final boolean isLeftEdgeDistorted() {
        return Math.abs(this.topLeftPoint.y - this.bottomLeftPoint.y) > 200.0d;
    }

    public final boolean isAnyEdgeDistorted() {
        return isRightEdgeDistorted() || isLeftEdgeDistorted();
    }

    /* compiled from: ImageDetectionProperties.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/support/templatematching/ImageDetectionProperties$Companion;", "", "()V", "ACCELERATION_MOVEMENT_THRESH", "", "ANGLE_DISTORTION_THRESH", "", "AREA_LIMIT_UPPER_THRESHOLD", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
