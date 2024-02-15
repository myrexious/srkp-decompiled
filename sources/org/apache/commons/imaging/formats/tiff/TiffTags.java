package org.apache.commons.imaging.formats.tiff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.apache.commons.imaging.formats.tiff.constants.AdobePageMaker6TagConstants;
import org.apache.commons.imaging.formats.tiff.constants.AdobePhotoshopTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.AliasSketchbookProTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.DcfTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.DngTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.GdalLibraryTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.GeoTiffTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.GpsTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.HylaFaxTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.MicrosoftHdPhotoTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.MicrosoftTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.MolecularDynamicsGelTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.OceScanjobTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.Rfc2301TagConstants;
import org.apache.commons.imaging.formats.tiff.constants.Tiff4TagConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.apache.commons.imaging.formats.tiff.constants.TiffEpTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.WangTagConstants;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;

/* loaded from: classes2.dex */
public final class TiffTags {
    private static final List<TagInfo> ALL_TAGS;
    private static final Map<Integer, List<TagInfo>> ALL_TAG_MAP;
    private static final Map<Integer, Integer> TAG_COUNTS;

    private TiffTags() {
    }

    static {
        List<TagInfo> makeMergedTagList = makeMergedTagList();
        ALL_TAGS = makeMergedTagList;
        ALL_TAG_MAP = makeTagMap(makeMergedTagList);
        TAG_COUNTS = countTags(makeMergedTagList);
    }

    private static List<TagInfo> makeMergedTagList() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(AdobePageMaker6TagConstants.ALL_ADOBE_PAGEMAKER_6_TAGS);
        arrayList.addAll(AdobePhotoshopTagConstants.ALL_ADOBE_PHOTOSHOP_TAGS);
        arrayList.addAll(AliasSketchbookProTagConstants.ALL_ALIAS_SKETCHBOOK_PRO_TAGS);
        arrayList.addAll(DcfTagConstants.ALL_DCF_TAGS);
        arrayList.addAll(DngTagConstants.ALL_DNG_TAGS);
        arrayList.addAll(ExifTagConstants.ALL_EXIF_TAGS);
        arrayList.addAll(GeoTiffTagConstants.ALL_GEO_TIFF_TAGS);
        arrayList.addAll(GdalLibraryTagConstants.ALL_GDAL_LIBRARY_TAGS);
        arrayList.addAll(GpsTagConstants.ALL_GPS_TAGS);
        arrayList.addAll(HylaFaxTagConstants.ALL_HYLAFAX_TAGS);
        arrayList.addAll(MicrosoftTagConstants.ALL_MICROSOFT_TAGS);
        arrayList.addAll(MicrosoftHdPhotoTagConstants.ALL_MICROSOFT_HD_PHOTO_TAGS);
        arrayList.addAll(MolecularDynamicsGelTagConstants.ALL_MOLECULAR_DYNAMICS_GEL_TAGS);
        arrayList.addAll(OceScanjobTagConstants.ALL_OCE_SCANJOB_TAGS);
        arrayList.addAll(Rfc2301TagConstants.ALL_RFC_2301_TAGS);
        arrayList.addAll(Tiff4TagConstants.ALL_TIFF_4_TAGS);
        arrayList.addAll(TiffEpTagConstants.ALL_TIFF_EP_TAGS);
        arrayList.addAll(TiffTagConstants.ALL_TIFF_TAGS);
        arrayList.addAll(WangTagConstants.ALL_WANG_TAGS);
        return Collections.unmodifiableList(arrayList);
    }

    private static Map<Integer, List<TagInfo>> makeTagMap(List<TagInfo> list) {
        HashMap hashMap = new HashMap();
        for (TagInfo tagInfo : list) {
            ((List) hashMap.computeIfAbsent(Integer.valueOf(tagInfo.tag), new Function() { // from class: org.apache.commons.imaging.formats.tiff.TiffTags$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TiffTags.lambda$makeTagMap$0((Integer) obj);
                }
            })).add(tagInfo);
        }
        return hashMap;
    }

    public static /* synthetic */ List lambda$makeTagMap$0(Integer num) {
        return new ArrayList();
    }

    private static Map<Integer, Integer> countTags(List<TagInfo> list) {
        HashMap hashMap = new HashMap();
        for (TagInfo tagInfo : list) {
            hashMap.merge(Integer.valueOf(tagInfo.tag), 1, new BiFunction() { // from class: org.apache.commons.imaging.formats.tiff.TiffTags$$ExternalSyntheticLambda0
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    int sum;
                    sum = Integer.sum(((Integer) obj).intValue(), ((Integer) obj2).intValue());
                    return Integer.valueOf(sum);
                }
            });
        }
        return hashMap;
    }

    public static Integer getTagCount(int i) {
        return TAG_COUNTS.get(Integer.valueOf(i));
    }

    public static TagInfo getTag(int i, int i2) {
        List<TagInfo> list = ALL_TAG_MAP.get(Integer.valueOf(i2));
        if (list == null) {
            return TiffTagConstants.TIFF_TAG_UNKNOWN;
        }
        return getTag(i, list);
    }

    private static TagInfo getTag(int i, List<TagInfo> list) {
        if (list.isEmpty()) {
            return null;
        }
        for (TagInfo tagInfo : list) {
            if (tagInfo.directoryType != TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN && i == tagInfo.directoryType.directoryType) {
                return tagInfo;
            }
        }
        for (TagInfo tagInfo2 : list) {
            if (tagInfo2.directoryType != TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN) {
                if (i >= 0 && tagInfo2.directoryType.isImageDirectory()) {
                    return tagInfo2;
                }
                if (i < 0 && !tagInfo2.directoryType.isImageDirectory()) {
                    return tagInfo2;
                }
            }
        }
        for (TagInfo tagInfo3 : list) {
            if (tagInfo3.directoryType == TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN) {
                return tagInfo3;
            }
        }
        return TiffTagConstants.TIFF_TAG_UNKNOWN;
    }
}
