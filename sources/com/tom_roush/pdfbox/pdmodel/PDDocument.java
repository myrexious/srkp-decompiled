package com.tom_roush.pdfbox.pdmodel;

import android.util.Log;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.io.MemoryUsageSetting;
import com.tom_roush.pdfbox.io.RandomAccessBuffer;
import com.tom_roush.pdfbox.io.RandomAccessBufferedFileInputStream;
import com.tom_roush.pdfbox.io.RandomAccessRead;
import com.tom_roush.pdfbox.io.ScratchFile;
import com.tom_roush.pdfbox.pdfparser.PDFParser;
import com.tom_roush.pdfbox.pdfwriter.COSWriter;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.encryption.AccessPermission;
import com.tom_roush.pdfbox.pdmodel.encryption.PDEncryption;
import com.tom_roush.pdfbox.pdmodel.encryption.ProtectionPolicy;
import com.tom_roush.pdfbox.pdmodel.encryption.SecurityHandler;
import com.tom_roush.pdfbox.pdmodel.encryption.SecurityHandlerFactory;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.ExternalSigningSupport;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.SigningSupport;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDAcroForm;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDField;
import com.tom_roush.pdfbox.pdmodel.interactive.form.PDSignatureField;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class PDDocument implements Closeable {
    private static final int[] RESERVE_BYTE_RANGE = {0, 1000000000, 1000000000, 1000000000};
    private AccessPermission accessPermission;
    private boolean allSecurityToBeRemoved;
    private final COSDocument document;
    private PDDocumentCatalog documentCatalog;
    private Long documentId;
    private PDDocumentInformation documentInformation;
    private PDEncryption encryption;
    private final Set<TrueTypeFont> fontsToClose;
    private final Set<PDFont> fontsToSubset;
    private final RandomAccessRead pdfSource;
    private ResourceCache resourceCache;
    private SignatureInterface signInterface;
    private boolean signatureAdded;
    private SigningSupport signingSupport;

    static {
        PDDeviceRGB.INSTANCE.toRGB(new float[]{1.0f, 1.0f, 1.0f, 1.0f});
        try {
            COSNumber.get("0");
            COSNumber.get("1");
        } catch (IOException unused) {
        }
    }

    public PDDocument() {
        this(MemoryUsageSetting.setupMainMemoryOnly());
    }

    public PDDocument(MemoryUsageSetting memoryUsageSetting) {
        ScratchFile scratchFile;
        this.fontsToSubset = new HashSet();
        this.fontsToClose = new HashSet();
        this.resourceCache = new DefaultResourceCache();
        this.signatureAdded = false;
        try {
            scratchFile = new ScratchFile(memoryUsageSetting);
        } catch (IOException e) {
            Log.w("PdfBox-Android", "Error initializing scratch file: " + e.getMessage() + ". Fall back to main memory usage only.");
            try {
                scratchFile = new ScratchFile(MemoryUsageSetting.setupMainMemoryOnly());
            } catch (IOException unused) {
                scratchFile = null;
            }
        }
        COSDocument cOSDocument = new COSDocument(scratchFile);
        this.document = cOSDocument;
        this.pdfSource = null;
        COSDictionary cOSDictionary = new COSDictionary();
        cOSDocument.setTrailer(cOSDictionary);
        COSDictionary cOSDictionary2 = new COSDictionary();
        cOSDictionary.setItem(COSName.ROOT, (COSBase) cOSDictionary2);
        cOSDictionary2.setItem(COSName.TYPE, (COSBase) COSName.CATALOG);
        cOSDictionary2.setItem(COSName.VERSION, (COSBase) COSName.getPDFName("1.4"));
        COSDictionary cOSDictionary3 = new COSDictionary();
        cOSDictionary2.setItem(COSName.PAGES, (COSBase) cOSDictionary3);
        cOSDictionary3.setItem(COSName.TYPE, (COSBase) COSName.PAGES);
        cOSDictionary3.setItem(COSName.KIDS, (COSBase) new COSArray());
        cOSDictionary3.setItem(COSName.COUNT, (COSBase) COSInteger.ZERO);
    }

    public PDDocument(COSDocument cOSDocument) {
        this(cOSDocument, null);
    }

    public PDDocument(COSDocument cOSDocument, RandomAccessRead randomAccessRead) {
        this(cOSDocument, randomAccessRead, null);
    }

    public PDDocument(COSDocument cOSDocument, RandomAccessRead randomAccessRead, AccessPermission accessPermission) {
        this.fontsToSubset = new HashSet();
        this.fontsToClose = new HashSet();
        this.resourceCache = new DefaultResourceCache();
        this.signatureAdded = false;
        this.document = cOSDocument;
        this.pdfSource = randomAccessRead;
        this.accessPermission = accessPermission;
    }

    public void addPage(PDPage pDPage) {
        getPages().add(pDPage);
    }

    public void addSignature(PDSignature pDSignature) throws IOException {
        addSignature(pDSignature, new SignatureOptions());
    }

    public void addSignature(PDSignature pDSignature, SignatureOptions signatureOptions) throws IOException {
        addSignature(pDSignature, null, signatureOptions);
    }

    public void addSignature(PDSignature pDSignature, SignatureInterface signatureInterface) throws IOException {
        addSignature(pDSignature, signatureInterface, new SignatureOptions());
    }

    public void addSignature(PDSignature pDSignature, SignatureInterface signatureInterface, SignatureOptions signatureOptions) throws IOException {
        PDAnnotationWidget pDAnnotationWidget;
        if (this.signatureAdded) {
            throw new IllegalStateException("Only one signature may be added in a document");
        }
        this.signatureAdded = true;
        int preferredSignatureSize = signatureOptions.getPreferredSignatureSize();
        if (preferredSignatureSize > 0) {
            pDSignature.setContents(new byte[preferredSignatureSize]);
        } else {
            pDSignature.setContents(new byte[SignatureOptions.DEFAULT_SIGNATURE_SIZE]);
        }
        pDSignature.setByteRange(RESERVE_BYTE_RANGE);
        this.signInterface = signatureInterface;
        PDPageTree pages = getPages();
        int count = pages.getCount();
        if (count == 0) {
            throw new IllegalStateException("Cannot sign an empty document");
        }
        PDPage pDPage = pages.get(Math.min(Math.max(signatureOptions.getPage(), 0), count - 1));
        PDDocumentCatalog documentCatalog = getDocumentCatalog();
        PDSignatureField pDSignatureField = null;
        PDAcroForm acroForm = documentCatalog.getAcroForm(null);
        documentCatalog.getCOSObject().setNeedToBeUpdated(true);
        if (acroForm == null) {
            acroForm = new PDAcroForm(this);
            documentCatalog.setAcroForm(acroForm);
        } else {
            acroForm.getCOSObject().setNeedToBeUpdated(true);
        }
        COSBase dictionaryObject = acroForm.getCOSObject().getDictionaryObject(COSName.FIELDS);
        if (dictionaryObject instanceof COSArray) {
            ((COSArray) dictionaryObject).setNeedToBeUpdated(true);
            pDSignatureField = findSignatureField(acroForm.getFieldIterator(), pDSignature);
        } else {
            acroForm.getCOSObject().setItem(COSName.FIELDS, new COSArray());
        }
        if (pDSignatureField == null) {
            pDSignatureField = new PDSignatureField(acroForm);
            pDSignatureField.setValue(pDSignature);
            pDAnnotationWidget = pDSignatureField.getWidgets().get(0);
            pDAnnotationWidget.setPage(pDPage);
        } else {
            pDSignature.getCOSObject().setNeedToBeUpdated(true);
            pDAnnotationWidget = pDSignatureField.getWidgets().get(0);
        }
        pDAnnotationWidget.setPrinted(true);
        List<PDField> fields = acroForm.getFields();
        acroForm.getCOSObject().setDirect(true);
        acroForm.setSignaturesExist(true);
        acroForm.setAppendOnly(true);
        boolean checkSignatureField = checkSignatureField(acroForm.getFieldIterator(), pDSignatureField);
        if (checkSignatureField) {
            pDSignatureField.getCOSObject().setNeedToBeUpdated(true);
        } else {
            fields.add(pDSignatureField);
        }
        COSDocument visualSignature = signatureOptions.getVisualSignature();
        if (visualSignature == null) {
            prepareNonVisibleSignature(pDAnnotationWidget);
            return;
        }
        prepareVisibleSignature(pDAnnotationWidget, acroForm, visualSignature);
        List<PDAnnotation> annotations = pDPage.getAnnotations();
        pDPage.setAnnotations(annotations);
        if (!checkSignatureField || !(annotations instanceof COSArrayList) || !(fields instanceof COSArrayList) || !((COSArrayList) annotations).toList().equals(((COSArrayList) fields).toList())) {
            if (checkSignatureAnnotation(annotations, pDAnnotationWidget)) {
                pDAnnotationWidget.getCOSObject().setNeedToBeUpdated(true);
            } else {
                annotations.add(pDAnnotationWidget);
            }
        }
        pDPage.getCOSObject().setNeedToBeUpdated(true);
    }

    private PDSignatureField findSignatureField(Iterator<PDField> it, PDSignature pDSignature) {
        PDSignatureField pDSignatureField;
        PDSignature signature;
        while (it.hasNext()) {
            PDField next = it.next();
            if ((next instanceof PDSignatureField) && (signature = (pDSignatureField = (PDSignatureField) next).getSignature()) != null && signature.getCOSObject().equals(pDSignature.getCOSObject())) {
                return pDSignatureField;
            }
        }
        return null;
    }

    private boolean checkSignatureField(Iterator<PDField> it, PDSignatureField pDSignatureField) {
        while (it.hasNext()) {
            PDField next = it.next();
            if ((next instanceof PDSignatureField) && next.getCOSObject().equals(pDSignatureField.getCOSObject())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkSignatureAnnotation(List<PDAnnotation> list, PDAnnotationWidget pDAnnotationWidget) {
        for (PDAnnotation pDAnnotation : list) {
            if (pDAnnotation.getCOSObject().equals(pDAnnotationWidget.getCOSObject())) {
                return true;
            }
        }
        return false;
    }

    private void prepareVisibleSignature(PDAnnotationWidget pDAnnotationWidget, PDAcroForm pDAcroForm, COSDocument cOSDocument) {
        boolean z = true;
        boolean z2 = true;
        for (COSObject cOSObject : cOSDocument.getObjects()) {
            if (!z && !z2) {
                break;
            }
            COSBase object = cOSObject.getObject();
            if (object instanceof COSDictionary) {
                COSDictionary cOSDictionary = (COSDictionary) object;
                COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.TYPE);
                if (z && COSName.ANNOT.equals(dictionaryObject)) {
                    assignSignatureRectangle(pDAnnotationWidget, cOSDictionary);
                    z = false;
                }
                COSBase dictionaryObject2 = cOSDictionary.getDictionaryObject(COSName.FT);
                COSBase dictionaryObject3 = cOSDictionary.getDictionaryObject(COSName.AP);
                if (z2 && COSName.SIG.equals(dictionaryObject2) && (dictionaryObject3 instanceof COSDictionary)) {
                    assignAppearanceDictionary(pDAnnotationWidget, (COSDictionary) dictionaryObject3);
                    assignAcroFormDefaultResource(pDAcroForm, cOSDictionary);
                    z2 = false;
                }
            }
        }
        if (z || z2) {
            throw new IllegalArgumentException("Template is missing required objects");
        }
    }

    private void assignSignatureRectangle(PDAnnotationWidget pDAnnotationWidget, COSDictionary cOSDictionary) {
        PDRectangle rectangle = pDAnnotationWidget.getRectangle();
        if (rectangle == null || rectangle.getCOSArray().size() != 4) {
            pDAnnotationWidget.setRectangle(new PDRectangle((COSArray) cOSDictionary.getDictionaryObject(COSName.RECT)));
        }
    }

    private void assignAppearanceDictionary(PDAnnotationWidget pDAnnotationWidget, COSDictionary cOSDictionary) {
        PDAppearanceDictionary pDAppearanceDictionary = new PDAppearanceDictionary(cOSDictionary);
        cOSDictionary.setDirect(true);
        pDAnnotationWidget.setAppearance(pDAppearanceDictionary);
    }

    private void assignAcroFormDefaultResource(PDAcroForm pDAcroForm, COSDictionary cOSDictionary) {
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.DR);
        if (dictionaryObject instanceof COSDictionary) {
            COSDictionary cOSDictionary2 = (COSDictionary) dictionaryObject;
            PDResources defaultResources = pDAcroForm.getDefaultResources();
            if (defaultResources == null) {
                pDAcroForm.getCOSObject().setItem(COSName.DR, (COSBase) cOSDictionary2);
                cOSDictionary2.setDirect(true);
                cOSDictionary2.setNeedToBeUpdated(true);
                return;
            }
            COSDictionary cOSObject = defaultResources.getCOSObject();
            COSBase item = cOSDictionary2.getItem(COSName.XOBJECT);
            COSBase item2 = cOSObject.getItem(COSName.XOBJECT);
            if ((item instanceof COSDictionary) && (item2 instanceof COSDictionary)) {
                ((COSDictionary) item2).addAll((COSDictionary) item);
                cOSObject.setNeedToBeUpdated(true);
            }
        }
    }

    private void prepareNonVisibleSignature(PDAnnotationWidget pDAnnotationWidget) {
        pDAnnotationWidget.setRectangle(new PDRectangle());
        PDAppearanceDictionary pDAppearanceDictionary = new PDAppearanceDictionary();
        PDAppearanceStream pDAppearanceStream = new PDAppearanceStream(this);
        pDAppearanceStream.setBBox(new PDRectangle());
        pDAppearanceDictionary.setNormalAppearance(pDAppearanceStream);
        pDAnnotationWidget.setAppearance(pDAppearanceDictionary);
    }

    @Deprecated
    public void addSignatureField(List<PDSignatureField> list, SignatureInterface signatureInterface, SignatureOptions signatureOptions) throws IOException {
        PDDocumentCatalog documentCatalog = getDocumentCatalog();
        documentCatalog.getCOSObject().setNeedToBeUpdated(true);
        PDAcroForm acroForm = documentCatalog.getAcroForm(null);
        if (acroForm == null) {
            acroForm = new PDAcroForm(this);
            documentCatalog.setAcroForm(acroForm);
        }
        COSDictionary cOSObject = acroForm.getCOSObject();
        cOSObject.setDirect(true);
        cOSObject.setNeedToBeUpdated(true);
        if (!acroForm.isSignaturesExist()) {
            acroForm.setSignaturesExist(true);
        }
        List<PDField> fields = acroForm.getFields();
        for (PDSignatureField pDSignatureField : list) {
            pDSignatureField.getCOSObject().setNeedToBeUpdated(true);
            if (checkSignatureField(acroForm.getFieldIterator(), pDSignatureField)) {
                pDSignatureField.getCOSObject().setNeedToBeUpdated(true);
            } else {
                fields.add(pDSignatureField);
            }
            if (pDSignatureField.getSignature() != null) {
                pDSignatureField.getCOSObject().setNeedToBeUpdated(true);
                addSignature(pDSignatureField.getSignature(), signatureInterface, signatureOptions);
            }
        }
    }

    public void removePage(PDPage pDPage) {
        getPages().remove(pDPage);
    }

    public void removePage(int i) {
        getPages().remove(i);
    }

    public PDPage importPage(PDPage pDPage) throws IOException {
        PDPage pDPage2 = new PDPage(new COSDictionary(pDPage.getCOSObject()), this.resourceCache);
        pDPage2.setContents(new PDStream(this, pDPage.getContents(), COSName.FLATE_DECODE));
        addPage(pDPage2);
        pDPage2.setCropBox(new PDRectangle(pDPage.getCropBox().getCOSArray()));
        pDPage2.setMediaBox(new PDRectangle(pDPage.getMediaBox().getCOSArray()));
        pDPage2.setRotation(pDPage.getRotation());
        if (pDPage.getResources() != null && !pDPage.getCOSObject().containsKey(COSName.RESOURCES)) {
            Log.w("PdfBox-Android", "inherited resources of source document are not imported to destination page");
            Log.w("PdfBox-Android", "call importedPage.setResources(page.getResources()) to do this");
        }
        return pDPage2;
    }

    public COSDocument getDocument() {
        return this.document;
    }

    public PDDocumentInformation getDocumentInformation() {
        if (this.documentInformation == null) {
            COSDictionary trailer = this.document.getTrailer();
            COSDictionary cOSDictionary = trailer.getCOSDictionary(COSName.INFO);
            if (cOSDictionary == null) {
                cOSDictionary = new COSDictionary();
                trailer.setItem(COSName.INFO, (COSBase) cOSDictionary);
            }
            this.documentInformation = new PDDocumentInformation(cOSDictionary);
        }
        return this.documentInformation;
    }

    public void setDocumentInformation(PDDocumentInformation pDDocumentInformation) {
        this.documentInformation = pDDocumentInformation;
        this.document.getTrailer().setItem(COSName.INFO, (COSBase) pDDocumentInformation.getCOSObject());
    }

    public PDDocumentCatalog getDocumentCatalog() {
        if (this.documentCatalog == null) {
            COSBase dictionaryObject = this.document.getTrailer().getDictionaryObject(COSName.ROOT);
            if (dictionaryObject instanceof COSDictionary) {
                this.documentCatalog = new PDDocumentCatalog(this, (COSDictionary) dictionaryObject);
            } else {
                this.documentCatalog = new PDDocumentCatalog(this);
            }
        }
        return this.documentCatalog;
    }

    public boolean isEncrypted() {
        return this.document.isEncrypted();
    }

    public PDEncryption getEncryption() {
        if (this.encryption == null && isEncrypted()) {
            this.encryption = new PDEncryption(this.document.getEncryptionDictionary());
        }
        return this.encryption;
    }

    public void setEncryptionDictionary(PDEncryption pDEncryption) throws IOException {
        this.encryption = pDEncryption;
    }

    public PDSignature getLastSignatureDictionary() throws IOException {
        List<PDSignature> signatureDictionaries = getSignatureDictionaries();
        int size = signatureDictionaries.size();
        if (size > 0) {
            return signatureDictionaries.get(size - 1);
        }
        return null;
    }

    public List<PDSignatureField> getSignatureFields() throws IOException {
        ArrayList arrayList = new ArrayList();
        PDAcroForm acroForm = getDocumentCatalog().getAcroForm(null);
        if (acroForm != null) {
            Iterator<PDField> it = acroForm.getFieldTree().iterator();
            while (it.hasNext()) {
                PDField next = it.next();
                if (next instanceof PDSignatureField) {
                    arrayList.add((PDSignatureField) next);
                }
            }
        }
        return arrayList;
    }

    public List<PDSignature> getSignatureDictionaries() throws IOException {
        ArrayList arrayList = new ArrayList();
        for (PDSignatureField pDSignatureField : getSignatureFields()) {
            COSBase dictionaryObject = pDSignatureField.getCOSObject().getDictionaryObject(COSName.V);
            if (dictionaryObject != null) {
                arrayList.add(new PDSignature((COSDictionary) dictionaryObject));
            }
        }
        return arrayList;
    }

    public void registerTrueTypeFontForClosing(TrueTypeFont trueTypeFont) {
        this.fontsToClose.add(trueTypeFont);
    }

    public Set<PDFont> getFontsToSubset() {
        return this.fontsToSubset;
    }

    public static PDDocument load(File file) throws IOException {
        return load(file, "", MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static PDDocument load(File file, MemoryUsageSetting memoryUsageSetting) throws IOException {
        return load(file, "", (InputStream) null, (String) null, memoryUsageSetting);
    }

    public static PDDocument load(File file, String str) throws IOException {
        return load(file, str, (InputStream) null, (String) null, MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static PDDocument load(File file, String str, MemoryUsageSetting memoryUsageSetting) throws IOException {
        return load(file, str, (InputStream) null, (String) null, memoryUsageSetting);
    }

    public static PDDocument load(File file, String str, InputStream inputStream, String str2) throws IOException {
        return load(file, str, inputStream, str2, MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static PDDocument load(File file, String str, InputStream inputStream, String str2, MemoryUsageSetting memoryUsageSetting) throws IOException {
        RandomAccessBufferedFileInputStream randomAccessBufferedFileInputStream = new RandomAccessBufferedFileInputStream(file);
        try {
            return load(randomAccessBufferedFileInputStream, str, inputStream, str2, memoryUsageSetting);
        } catch (IOException e) {
            IOUtils.closeQuietly(randomAccessBufferedFileInputStream);
            throw e;
        }
    }

    private static PDDocument load(RandomAccessBufferedFileInputStream randomAccessBufferedFileInputStream, String str, InputStream inputStream, String str2, MemoryUsageSetting memoryUsageSetting) throws IOException {
        ScratchFile scratchFile = new ScratchFile(memoryUsageSetting);
        try {
            PDFParser pDFParser = new PDFParser(randomAccessBufferedFileInputStream, str, inputStream, str2, scratchFile);
            pDFParser.parse();
            return pDFParser.getPDDocument();
        } catch (IOException e) {
            IOUtils.closeQuietly(scratchFile);
            throw e;
        }
    }

    public static PDDocument load(InputStream inputStream) throws IOException {
        return load(inputStream, "", (InputStream) null, (String) null, MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static PDDocument load(InputStream inputStream, MemoryUsageSetting memoryUsageSetting) throws IOException {
        return load(inputStream, "", (InputStream) null, (String) null, memoryUsageSetting);
    }

    public static PDDocument load(InputStream inputStream, String str) throws IOException {
        return load(inputStream, str, (InputStream) null, (String) null, MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static PDDocument load(InputStream inputStream, String str, InputStream inputStream2, String str2) throws IOException {
        return load(inputStream, str, inputStream2, str2, MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static PDDocument load(InputStream inputStream, String str, MemoryUsageSetting memoryUsageSetting) throws IOException {
        return load(inputStream, str, (InputStream) null, (String) null, memoryUsageSetting);
    }

    public static PDDocument load(InputStream inputStream, String str, InputStream inputStream2, String str2, MemoryUsageSetting memoryUsageSetting) throws IOException {
        ScratchFile scratchFile = new ScratchFile(memoryUsageSetting);
        try {
            PDFParser pDFParser = new PDFParser(scratchFile.createBuffer(inputStream), str, inputStream2, str2, scratchFile);
            pDFParser.parse();
            return pDFParser.getPDDocument();
        } catch (IOException e) {
            IOUtils.closeQuietly(scratchFile);
            throw e;
        }
    }

    public static PDDocument load(byte[] bArr) throws IOException {
        return load(bArr, "");
    }

    public static PDDocument load(byte[] bArr, String str) throws IOException {
        return load(bArr, str, (InputStream) null, (String) null);
    }

    public static PDDocument load(byte[] bArr, String str, InputStream inputStream, String str2) throws IOException {
        return load(bArr, str, inputStream, str2, MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static PDDocument load(byte[] bArr, String str, InputStream inputStream, String str2, MemoryUsageSetting memoryUsageSetting) throws IOException {
        PDFParser pDFParser = new PDFParser(new RandomAccessBuffer(bArr), str, inputStream, str2, new ScratchFile(memoryUsageSetting));
        pDFParser.parse();
        return pDFParser.getPDDocument();
    }

    public void save(String str) throws IOException {
        save(new File(str));
    }

    public void save(File file) throws IOException {
        save(new BufferedOutputStream(new FileOutputStream(file)));
    }

    public void save(OutputStream outputStream) throws IOException {
        if (this.document.isClosed()) {
            throw new IOException("Cannot save a document which has been closed");
        }
        for (PDFont pDFont : this.fontsToSubset) {
            pDFont.subset();
        }
        this.fontsToSubset.clear();
        COSWriter cOSWriter = new COSWriter(outputStream);
        try {
            cOSWriter.write(this);
        } finally {
            cOSWriter.close();
        }
    }

    public void saveIncremental(OutputStream outputStream) throws IOException {
        COSWriter cOSWriter = null;
        try {
            if (this.pdfSource == null) {
                throw new IllegalStateException("document was not loaded from a file or a stream");
            }
            COSWriter cOSWriter2 = new COSWriter(outputStream, this.pdfSource);
            try {
                cOSWriter2.write(this, this.signInterface);
                cOSWriter2.close();
            } catch (Throwable th) {
                th = th;
                cOSWriter = cOSWriter2;
                if (cOSWriter != null) {
                    cOSWriter.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public void saveIncremental(OutputStream outputStream, Set<COSDictionary> set) throws IOException {
        COSWriter cOSWriter;
        if (this.pdfSource == null) {
            throw new IllegalStateException("document was not loaded from a file or a stream");
        }
        COSWriter cOSWriter2 = null;
        try {
            cOSWriter = new COSWriter(outputStream, this.pdfSource, set);
        } catch (Throwable th) {
            th = th;
        }
        try {
            cOSWriter.write(this, this.signInterface);
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

    public ExternalSigningSupport saveIncrementalForExternalSigning(OutputStream outputStream) throws IOException {
        if (this.pdfSource == null) {
            throw new IllegalStateException("document was not loaded from a file or a stream");
        }
        Iterator<PDSignature> it = getSignatureDictionaries().iterator();
        PDSignature pDSignature = null;
        while (it.hasNext()) {
            pDSignature = it.next();
            if (pDSignature.getCOSObject().isNeedToBeUpdated()) {
                break;
            }
        }
        if (!Arrays.equals(pDSignature.getByteRange(), RESERVE_BYTE_RANGE)) {
            throw new IllegalStateException("signature reserve byte range has been changed after addSignature(), please set the byte range that existed after addSignature()");
        }
        COSWriter cOSWriter = new COSWriter(outputStream, this.pdfSource);
        cOSWriter.write(this);
        SigningSupport signingSupport = new SigningSupport(cOSWriter);
        this.signingSupport = signingSupport;
        return signingSupport;
    }

    public PDPage getPage(int i) {
        return getDocumentCatalog().getPages().get(i);
    }

    public PDPageTree getPages() {
        return getDocumentCatalog().getPages();
    }

    public int getNumberOfPages() {
        return getDocumentCatalog().getPages().getCount();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.document.isClosed()) {
            return;
        }
        SigningSupport signingSupport = this.signingSupport;
        IOException closeAndLogException = IOUtils.closeAndLogException(this.document, "COSDocument", signingSupport != null ? IOUtils.closeAndLogException(signingSupport, "SigningSupport", null) : null);
        RandomAccessRead randomAccessRead = this.pdfSource;
        if (randomAccessRead != null) {
            closeAndLogException = IOUtils.closeAndLogException(randomAccessRead, "RandomAccessRead pdfSource", closeAndLogException);
        }
        for (TrueTypeFont trueTypeFont : this.fontsToClose) {
            closeAndLogException = IOUtils.closeAndLogException(trueTypeFont, "TrueTypeFont", closeAndLogException);
        }
        if (closeAndLogException != null) {
            throw closeAndLogException;
        }
    }

    public void protect(ProtectionPolicy protectionPolicy) throws IOException {
        if (isAllSecurityToBeRemoved()) {
            Log.w("PdfBox-Android", "do not call setAllSecurityToBeRemoved(true) before calling protect(), as protect() implies setAllSecurityToBeRemoved(false)");
            setAllSecurityToBeRemoved(false);
        }
        if (!isEncrypted()) {
            this.encryption = new PDEncryption();
        }
        SecurityHandler newSecurityHandlerForPolicy = SecurityHandlerFactory.INSTANCE.newSecurityHandlerForPolicy(protectionPolicy);
        if (newSecurityHandlerForPolicy == null) {
            throw new IOException("No security handler for policy " + protectionPolicy);
        }
        getEncryption().setSecurityHandler(newSecurityHandlerForPolicy);
    }

    public AccessPermission getCurrentAccessPermission() {
        if (this.accessPermission == null) {
            this.accessPermission = AccessPermission.getOwnerAccessPermission();
        }
        return this.accessPermission;
    }

    public boolean isAllSecurityToBeRemoved() {
        return this.allSecurityToBeRemoved;
    }

    public void setAllSecurityToBeRemoved(boolean z) {
        this.allSecurityToBeRemoved = z;
    }

    public Long getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(Long l) {
        this.documentId = l;
    }

    public float getVersion() {
        float parseFloat;
        float version = getDocument().getVersion();
        if (version >= 1.4f) {
            String version2 = getDocumentCatalog().getVersion();
            if (version2 != null) {
                try {
                    parseFloat = Float.parseFloat(version2);
                } catch (NumberFormatException e) {
                    Log.e("PdfBox-Android", "Can't extract the version number of the document catalog.", e);
                }
                return Math.max(parseFloat, version);
            }
            parseFloat = -1.0f;
            return Math.max(parseFloat, version);
        }
        return version;
    }

    public void setVersion(float f) {
        float version = getVersion();
        if (f == version) {
            return;
        }
        if (f < version) {
            Log.e("PdfBox-Android", "It's not allowed to downgrade the version of a pdf.");
        } else if (getDocument().getVersion() >= 1.4f) {
            getDocumentCatalog().setVersion(Float.toString(f));
        } else {
            getDocument().setVersion(f);
        }
    }

    public ResourceCache getResourceCache() {
        return this.resourceCache;
    }

    public void setResourceCache(ResourceCache resourceCache) {
        this.resourceCache = resourceCache;
    }
}
