package org.apache.commons.imaging.formats.tiff.write;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.tiff.constants.GpsTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffConstants;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.internal.Debug;
import org.apache.commons.lang3.SystemProperties;

/* loaded from: classes2.dex */
public final class TiffOutputSet {
    private static final String NEWLINE = System.getProperty(SystemProperties.LINE_SEPARATOR);
    public final ByteOrder byteOrder;
    private final List<TiffOutputDirectory> directories;

    public TiffOutputSet() {
        this(TiffConstants.DEFAULT_TIFF_BYTE_ORDER);
    }

    public TiffOutputSet(ByteOrder byteOrder) {
        this.directories = new ArrayList();
        this.byteOrder = byteOrder;
    }

    public List<TiffOutputItem> getOutputItems(TiffOutputSummary tiffOutputSummary) throws ImageWriteException {
        ArrayList arrayList = new ArrayList();
        for (TiffOutputDirectory tiffOutputDirectory : this.directories) {
            arrayList.addAll(tiffOutputDirectory.getOutputItems(tiffOutputSummary));
        }
        return arrayList;
    }

    public void addDirectory(TiffOutputDirectory tiffOutputDirectory) throws ImageWriteException {
        if (findDirectory(tiffOutputDirectory.type) != null) {
            throw new ImageWriteException("Output set already contains a directory of that type.");
        }
        this.directories.add(tiffOutputDirectory);
    }

    public List<TiffOutputDirectory> getDirectories() {
        return new ArrayList(this.directories);
    }

    public TiffOutputDirectory getRootDirectory() {
        return findDirectory(0);
    }

    public TiffOutputDirectory getExifDirectory() {
        return findDirectory(-2);
    }

    public TiffOutputDirectory getOrCreateRootDirectory() throws ImageWriteException {
        TiffOutputDirectory findDirectory = findDirectory(0);
        return findDirectory != null ? findDirectory : addRootDirectory();
    }

    public TiffOutputDirectory getOrCreateExifDirectory() throws ImageWriteException {
        getOrCreateRootDirectory();
        TiffOutputDirectory findDirectory = findDirectory(-2);
        return findDirectory != null ? findDirectory : addExifDirectory();
    }

    public TiffOutputDirectory getOrCreateGPSDirectory() throws ImageWriteException {
        getOrCreateExifDirectory();
        TiffOutputDirectory findDirectory = findDirectory(-3);
        return findDirectory != null ? findDirectory : addGPSDirectory();
    }

    public TiffOutputDirectory getGPSDirectory() {
        return findDirectory(-3);
    }

    public TiffOutputDirectory getInteroperabilityDirectory() {
        return findDirectory(-4);
    }

    public TiffOutputDirectory findDirectory(int i) {
        for (TiffOutputDirectory tiffOutputDirectory : this.directories) {
            if (tiffOutputDirectory.type == i) {
                return tiffOutputDirectory;
            }
        }
        return null;
    }

    public void setGPSInDegrees(double d, double d2) throws ImageWriteException {
        TiffOutputDirectory orCreateGPSDirectory = getOrCreateGPSDirectory();
        orCreateGPSDirectory.removeField(GpsTagConstants.GPS_TAG_GPS_VERSION_ID);
        orCreateGPSDirectory.add(GpsTagConstants.GPS_TAG_GPS_VERSION_ID, GpsTagConstants.gpsVersion());
        String str = d < 0.0d ? "W" : "E";
        double abs = Math.abs(d);
        String str2 = d2 < 0.0d ? "S" : "N";
        double abs2 = Math.abs(d2);
        orCreateGPSDirectory.removeField(GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF);
        orCreateGPSDirectory.add(GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF, str);
        orCreateGPSDirectory.removeField(GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF);
        orCreateGPSDirectory.add(GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF, str2);
        double d3 = (abs % 1.0d) * 60.0d;
        orCreateGPSDirectory.removeField(GpsTagConstants.GPS_TAG_GPS_LONGITUDE);
        orCreateGPSDirectory.add(GpsTagConstants.GPS_TAG_GPS_LONGITUDE, RationalNumber.valueOf((long) abs), RationalNumber.valueOf((long) d3), RationalNumber.valueOf((d3 % 1.0d) * 60.0d));
        double d4 = (abs2 % 1.0d) * 60.0d;
        orCreateGPSDirectory.removeField(GpsTagConstants.GPS_TAG_GPS_LATITUDE);
        orCreateGPSDirectory.add(GpsTagConstants.GPS_TAG_GPS_LATITUDE, RationalNumber.valueOf((long) abs2), RationalNumber.valueOf((long) d4), RationalNumber.valueOf((d4 % 1.0d) * 60.0d));
    }

    public void removeField(TagInfo tagInfo) {
        removeField(tagInfo.tag);
    }

    public void removeField(int i) {
        for (TiffOutputDirectory tiffOutputDirectory : this.directories) {
            tiffOutputDirectory.removeField(i);
        }
    }

    public TiffOutputField findField(TagInfo tagInfo) {
        return findField(tagInfo.tag);
    }

    public TiffOutputField findField(int i) {
        for (TiffOutputDirectory tiffOutputDirectory : this.directories) {
            TiffOutputField findField = tiffOutputDirectory.findField(i);
            if (findField != null) {
                return findField;
            }
        }
        return null;
    }

    public TiffOutputDirectory addRootDirectory() throws ImageWriteException {
        TiffOutputDirectory tiffOutputDirectory = new TiffOutputDirectory(0, this.byteOrder);
        addDirectory(tiffOutputDirectory);
        return tiffOutputDirectory;
    }

    public TiffOutputDirectory addExifDirectory() throws ImageWriteException {
        TiffOutputDirectory tiffOutputDirectory = new TiffOutputDirectory(-2, this.byteOrder);
        addDirectory(tiffOutputDirectory);
        return tiffOutputDirectory;
    }

    public TiffOutputDirectory addGPSDirectory() throws ImageWriteException {
        TiffOutputDirectory tiffOutputDirectory = new TiffOutputDirectory(-3, this.byteOrder);
        addDirectory(tiffOutputDirectory);
        return tiffOutputDirectory;
    }

    public TiffOutputDirectory addInteroperabilityDirectory() throws ImageWriteException {
        getOrCreateExifDirectory();
        TiffOutputDirectory tiffOutputDirectory = new TiffOutputDirectory(-4, this.byteOrder);
        addDirectory(tiffOutputDirectory);
        return tiffOutputDirectory;
    }

    public String toString() {
        return toString(null);
    }

    public String toString(String str) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder(39);
        sb.append(str);
        sb.append("TiffOutputSet {");
        String str2 = NEWLINE;
        sb.append(str2);
        sb.append(str);
        sb.append("byteOrder: ");
        sb.append(this.byteOrder);
        sb.append(str2);
        for (int i = 0; i < this.directories.size(); i++) {
            TiffOutputDirectory tiffOutputDirectory = this.directories.get(i);
            sb.append(String.format("%s\tdirectory %d: %s (%d)%n", str, Integer.valueOf(i), tiffOutputDirectory.description(), Integer.valueOf(tiffOutputDirectory.type)));
            for (TiffOutputField tiffOutputField : tiffOutputDirectory.getFields()) {
                sb.append(str);
                sb.append("\t\tfield ").append(i).append(": ").append(tiffOutputField.tagInfo);
                sb.append(NEWLINE);
            }
        }
        sb.append(str);
        sb.append('}');
        sb.append(NEWLINE);
        return sb.toString();
    }

    public void dump() {
        Debug.debug(toString());
    }
}
