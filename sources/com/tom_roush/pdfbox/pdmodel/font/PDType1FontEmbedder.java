package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.fontbox.afm.FontMetrics;
import com.tom_roush.fontbox.encoding.BuiltInEncoding;
import com.tom_roush.fontbox.pfb.PfbParser;
import com.tom_roush.fontbox.type1.Type1Font;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.GlyphList;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Type1Encoding;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class PDType1FontEmbedder {
    private final Encoding fontEncoding;
    private final Type1Font type1;

    public PDType1FontEmbedder(PDDocument pDDocument, COSDictionary cOSDictionary, InputStream inputStream, Encoding encoding) throws IOException {
        cOSDictionary.setItem(COSName.SUBTYPE, COSName.TYPE1);
        byte[] byteArray = IOUtils.toByteArray(inputStream);
        PfbParser pfbParser = new PfbParser(byteArray);
        Type1Font createWithPFB = Type1Font.createWithPFB(byteArray);
        this.type1 = createWithPFB;
        if (encoding == null) {
            this.fontEncoding = Type1Encoding.fromFontBox(createWithPFB.getEncoding());
        } else {
            this.fontEncoding = encoding;
        }
        PDFontDescriptor buildFontDescriptor = buildFontDescriptor(createWithPFB);
        PDStream pDStream = new PDStream(pDDocument, pfbParser.getInputStream(), COSName.FLATE_DECODE);
        pDStream.getCOSObject().setInt("Length", pfbParser.size());
        int i = 0;
        while (i < pfbParser.getLengths().length) {
            int i2 = i + 1;
            pDStream.getCOSObject().setInt("Length" + i2, pfbParser.getLengths()[i]);
            i = i2;
        }
        buildFontDescriptor.setFontFile(pDStream);
        cOSDictionary.setItem(COSName.FONT_DESC, buildFontDescriptor);
        cOSDictionary.setName(COSName.BASE_FONT, this.type1.getName());
        ArrayList arrayList = new ArrayList(256);
        for (int i3 = 0; i3 <= 255; i3++) {
            arrayList.add(Integer.valueOf(Math.round(this.type1.getWidth(this.fontEncoding.getName(i3)))));
        }
        cOSDictionary.setInt(COSName.FIRST_CHAR, 0);
        cOSDictionary.setInt(COSName.LAST_CHAR, 255);
        cOSDictionary.setItem(COSName.WIDTHS, COSArrayList.converterToCOSArray(arrayList));
        cOSDictionary.setItem(COSName.ENCODING, encoding);
    }

    static PDFontDescriptor buildFontDescriptor(Type1Font type1Font) {
        boolean z = type1Font.getEncoding() instanceof BuiltInEncoding;
        BoundingBox fontBBox = type1Font.getFontBBox();
        PDFontDescriptor pDFontDescriptor = new PDFontDescriptor();
        pDFontDescriptor.setFontName(type1Font.getName());
        pDFontDescriptor.setFontFamily(type1Font.getFamilyName());
        pDFontDescriptor.setNonSymbolic(!z);
        pDFontDescriptor.setSymbolic(z);
        pDFontDescriptor.setFontBoundingBox(new PDRectangle(fontBBox));
        pDFontDescriptor.setItalicAngle(type1Font.getItalicAngle());
        pDFontDescriptor.setAscent(fontBBox.getUpperRightY());
        pDFontDescriptor.setDescent(fontBBox.getLowerLeftY());
        pDFontDescriptor.setCapHeight(type1Font.getBlueValues().get(2).floatValue());
        pDFontDescriptor.setStemV(0.0f);
        return pDFontDescriptor;
    }

    public static PDFontDescriptor buildFontDescriptor(FontMetrics fontMetrics) {
        boolean equals = fontMetrics.getEncodingScheme().equals("FontSpecific");
        PDFontDescriptor pDFontDescriptor = new PDFontDescriptor();
        pDFontDescriptor.setFontName(fontMetrics.getFontName());
        pDFontDescriptor.setFontFamily(fontMetrics.getFamilyName());
        pDFontDescriptor.setNonSymbolic(!equals);
        pDFontDescriptor.setSymbolic(equals);
        pDFontDescriptor.setFontBoundingBox(new PDRectangle(fontMetrics.getFontBBox()));
        pDFontDescriptor.setItalicAngle(fontMetrics.getItalicAngle());
        pDFontDescriptor.setAscent(fontMetrics.getAscender());
        pDFontDescriptor.setDescent(fontMetrics.getDescender());
        pDFontDescriptor.setCapHeight(fontMetrics.getCapHeight());
        pDFontDescriptor.setXHeight(fontMetrics.getXHeight());
        pDFontDescriptor.setAverageWidth(fontMetrics.getAverageCharacterWidth());
        pDFontDescriptor.setCharacterSet(fontMetrics.getCharacterSet());
        pDFontDescriptor.setStemV(0.0f);
        return pDFontDescriptor;
    }

    public Encoding getFontEncoding() {
        return this.fontEncoding;
    }

    public GlyphList getGlyphList() {
        return GlyphList.getAdobeGlyphList();
    }

    public Type1Font getType1Font() {
        return this.type1;
    }
}
