package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdfparser.FDFParser;
import com.tom_roush.pdfbox.pdfwriter.COSWriter;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* loaded from: classes3.dex */
public class FDFDocument implements Closeable {
    private COSDocument document;

    public FDFDocument() {
        COSDocument cOSDocument = new COSDocument();
        this.document = cOSDocument;
        cOSDocument.setVersion(1.2f);
        this.document.setTrailer(new COSDictionary());
        setCatalog(new FDFCatalog());
    }

    public FDFDocument(COSDocument cOSDocument) {
        this.document = cOSDocument;
    }

    public FDFDocument(Document document) throws IOException {
        this();
        Element documentElement = document.getDocumentElement();
        if (!documentElement.getNodeName().equals("xfdf")) {
            throw new IOException("Error while importing xfdf document, root should be 'xfdf' and not '" + documentElement.getNodeName() + OperatorName.SHOW_TEXT_LINE);
        }
        setCatalog(new FDFCatalog(documentElement));
    }

    public void writeXML(Writer writer) throws IOException {
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        writer.write("<xfdf xmlns=\"http://ns.adobe.com/xfdf/\" xml:space=\"preserve\">\n");
        getCatalog().writeXML(writer);
        writer.write("</xfdf>\n");
    }

    public COSDocument getDocument() {
        return this.document;
    }

    public FDFCatalog getCatalog() {
        COSDictionary cOSDictionary = this.document.getTrailer().getCOSDictionary(COSName.ROOT);
        if (cOSDictionary == null) {
            FDFCatalog fDFCatalog = new FDFCatalog();
            setCatalog(fDFCatalog);
            return fDFCatalog;
        }
        return new FDFCatalog(cOSDictionary);
    }

    public void setCatalog(FDFCatalog fDFCatalog) {
        this.document.getTrailer().setItem(COSName.ROOT, fDFCatalog);
    }

    public static FDFDocument load(String str) throws IOException {
        FDFParser fDFParser = new FDFParser(str);
        fDFParser.parse();
        return new FDFDocument(fDFParser.getDocument());
    }

    public static FDFDocument load(File file) throws IOException {
        FDFParser fDFParser = new FDFParser(file);
        fDFParser.parse();
        return new FDFDocument(fDFParser.getDocument());
    }

    public static FDFDocument load(InputStream inputStream) throws IOException {
        FDFParser fDFParser = new FDFParser(inputStream);
        fDFParser.parse();
        return new FDFDocument(fDFParser.getDocument());
    }

    public static FDFDocument loadXFDF(String str) throws IOException {
        return loadXFDF(new BufferedInputStream(new FileInputStream(str)));
    }

    public static FDFDocument loadXFDF(File file) throws IOException {
        return loadXFDF(new BufferedInputStream(new FileInputStream(file)));
    }

    public static FDFDocument loadXFDF(InputStream inputStream) throws IOException {
        return new FDFDocument(com.tom_roush.pdfbox.util.XMLUtil.parse(inputStream));
    }

    public void save(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        save(fileOutputStream);
        fileOutputStream.close();
    }

    public void save(String str) throws IOException {
        save(new File(str));
    }

    public void save(OutputStream outputStream) throws IOException {
        COSWriter cOSWriter;
        COSWriter cOSWriter2 = null;
        try {
            cOSWriter = new COSWriter(outputStream);
        } catch (Throwable th) {
            th = th;
        }
        try {
            cOSWriter.write(this);
            cOSWriter.close();
            cOSWriter.close();
        } catch (Throwable th2) {
            th = th2;
            cOSWriter2 = cOSWriter;
            if (cOSWriter2 != null) {
                cOSWriter2.close();
            }
            throw th;
        }
    }

    public void saveXFDF(File file) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        saveXFDF(bufferedWriter);
        bufferedWriter.close();
    }

    public void saveXFDF(String str) throws IOException {
        saveXFDF(new File(str));
    }

    public void saveXFDF(Writer writer) throws IOException {
        try {
            writeXML(writer);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.document.close();
    }
}
