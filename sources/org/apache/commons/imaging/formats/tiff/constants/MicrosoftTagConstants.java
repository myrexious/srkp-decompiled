package org.apache.commons.imaging.formats.tiff.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShort;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoXpString;
import org.apache.xmpbox.schema.XMPBasicSchema;

/* loaded from: classes2.dex */
public final class MicrosoftTagConstants {
    public static final List<TagInfo> ALL_MICROSOFT_TAGS;
    public static final TagInfoShort EXIF_TAG_RATING;
    public static final TagInfoShort EXIF_TAG_RATING_PERCENT;
    public static final TagInfoXpString EXIF_TAG_XPAUTHOR;
    public static final TagInfoXpString EXIF_TAG_XPCOMMENT;
    public static final TagInfoXpString EXIF_TAG_XPKEYWORDS;
    public static final TagInfoXpString EXIF_TAG_XPSUBJECT;
    public static final TagInfoXpString EXIF_TAG_XPTITLE;

    static {
        TagInfoShort tagInfoShort = new TagInfoShort(XMPBasicSchema.RATING, 18246, TiffDirectoryType.EXIF_DIRECTORY_IFD0);
        EXIF_TAG_RATING = tagInfoShort;
        TagInfoShort tagInfoShort2 = new TagInfoShort("RatingPercent", 18249, TiffDirectoryType.EXIF_DIRECTORY_IFD0);
        EXIF_TAG_RATING_PERCENT = tagInfoShort2;
        TagInfoXpString tagInfoXpString = new TagInfoXpString("XPTitle", 40091, TiffDirectoryType.EXIF_DIRECTORY_IFD0);
        EXIF_TAG_XPTITLE = tagInfoXpString;
        TagInfoXpString tagInfoXpString2 = new TagInfoXpString("XPComment", 40092, TiffDirectoryType.EXIF_DIRECTORY_IFD0);
        EXIF_TAG_XPCOMMENT = tagInfoXpString2;
        TagInfoXpString tagInfoXpString3 = new TagInfoXpString("XPAuthor", 40093, TiffDirectoryType.EXIF_DIRECTORY_IFD0);
        EXIF_TAG_XPAUTHOR = tagInfoXpString3;
        TagInfoXpString tagInfoXpString4 = new TagInfoXpString("XPKeywords", 40094, TiffDirectoryType.EXIF_DIRECTORY_IFD0);
        EXIF_TAG_XPKEYWORDS = tagInfoXpString4;
        TagInfoXpString tagInfoXpString5 = new TagInfoXpString("XPSubject", 40095, TiffDirectoryType.EXIF_DIRECTORY_IFD0);
        EXIF_TAG_XPSUBJECT = tagInfoXpString5;
        ALL_MICROSOFT_TAGS = Collections.unmodifiableList(Arrays.asList(tagInfoShort, tagInfoShort2, tagInfoXpString, tagInfoXpString2, tagInfoXpString3, tagInfoXpString4, tagInfoXpString5));
    }

    private MicrosoftTagConstants() {
    }
}
