package com.tom_roush.pdfbox.pdmodel;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.font.PDFontFactory;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;
import com.tom_roush.pdfbox.pdmodel.graphics.pattern.PDAbstractPattern;
import com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public final class PDResources implements COSObjectable {
    private final ResourceCache cache;
    private final Map<COSName, SoftReference<PDFont>> directFontCache;
    private final COSDictionary resources;

    public PDResources() {
        this.directFontCache = new HashMap();
        this.resources = new COSDictionary();
        this.cache = null;
    }

    public PDResources(COSDictionary cOSDictionary) {
        this.directFontCache = new HashMap();
        if (cOSDictionary == null) {
            throw new IllegalArgumentException("resourceDictionary is null");
        }
        this.resources = cOSDictionary;
        this.cache = null;
    }

    public PDResources(COSDictionary cOSDictionary, ResourceCache resourceCache) {
        this.directFontCache = new HashMap();
        if (cOSDictionary == null) {
            throw new IllegalArgumentException("resourceDictionary is null");
        }
        this.resources = cOSDictionary;
        this.cache = resourceCache;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.resources;
    }

    public PDFont getFont(COSName cOSName) throws IOException {
        SoftReference<PDFont> softReference;
        PDFont pDFont;
        COSObject indirect = getIndirect(COSName.FONT, cOSName);
        ResourceCache resourceCache = this.cache;
        if (resourceCache != null && indirect != null) {
            PDFont font = resourceCache.getFont(indirect);
            if (font != null) {
                return font;
            }
        } else if (indirect == null && (softReference = this.directFontCache.get(cOSName)) != null && (pDFont = softReference.get()) != null) {
            return pDFont;
        }
        COSBase cOSBase = get(COSName.FONT, cOSName);
        PDFont createFont = cOSBase instanceof COSDictionary ? PDFontFactory.createFont((COSDictionary) cOSBase, this.cache) : null;
        ResourceCache resourceCache2 = this.cache;
        if (resourceCache2 != null && indirect != null) {
            resourceCache2.put(indirect, createFont);
        } else if (indirect == null) {
            this.directFontCache.put(cOSName, new SoftReference<>(createFont));
        }
        return createFont;
    }

    public PDColorSpace getColorSpace(COSName cOSName) throws IOException {
        return getColorSpace(cOSName, false);
    }

    public PDColorSpace getColorSpace(COSName cOSName, boolean z) throws IOException {
        PDColorSpace create;
        PDColorSpace colorSpace;
        COSObject indirect = getIndirect(COSName.COLORSPACE, cOSName);
        ResourceCache resourceCache = this.cache;
        if (resourceCache == null || indirect == null || (colorSpace = resourceCache.getColorSpace(indirect)) == null) {
            COSBase cOSBase = get(COSName.COLORSPACE, cOSName);
            if (cOSBase != null) {
                create = PDColorSpace.create(cOSBase, this, z);
            } else {
                create = PDColorSpace.create(cOSName, this, z);
            }
            ResourceCache resourceCache2 = this.cache;
            if (resourceCache2 != null && indirect != null) {
                resourceCache2.put(indirect, create);
            }
            return create;
        }
        return colorSpace;
    }

    public boolean hasColorSpace(COSName cOSName) {
        return get(COSName.COLORSPACE, cOSName) != null;
    }

    public PDExtendedGraphicsState getExtGState(COSName cOSName) {
        PDExtendedGraphicsState extGState;
        COSObject indirect = getIndirect(COSName.EXT_G_STATE, cOSName);
        ResourceCache resourceCache = this.cache;
        if (resourceCache == null || indirect == null || (extGState = resourceCache.getExtGState(indirect)) == null) {
            COSBase cOSBase = get(COSName.EXT_G_STATE, cOSName);
            PDExtendedGraphicsState pDExtendedGraphicsState = cOSBase instanceof COSDictionary ? new PDExtendedGraphicsState((COSDictionary) cOSBase) : null;
            ResourceCache resourceCache2 = this.cache;
            if (resourceCache2 != null && indirect != null) {
                resourceCache2.put(indirect, pDExtendedGraphicsState);
            }
            return pDExtendedGraphicsState;
        }
        return extGState;
    }

    public PDShading getShading(COSName cOSName) throws IOException {
        PDShading shading;
        COSObject indirect = getIndirect(COSName.SHADING, cOSName);
        ResourceCache resourceCache = this.cache;
        if (resourceCache == null || indirect == null || (shading = resourceCache.getShading(indirect)) == null) {
            COSBase cOSBase = get(COSName.SHADING, cOSName);
            PDShading create = cOSBase instanceof COSDictionary ? PDShading.create((COSDictionary) cOSBase) : null;
            ResourceCache resourceCache2 = this.cache;
            if (resourceCache2 != null && indirect != null) {
                resourceCache2.put(indirect, create);
            }
            return create;
        }
        return shading;
    }

    public PDAbstractPattern getPattern(COSName cOSName) throws IOException {
        PDAbstractPattern pattern;
        COSObject indirect = getIndirect(COSName.PATTERN, cOSName);
        ResourceCache resourceCache = this.cache;
        if (resourceCache == null || indirect == null || (pattern = resourceCache.getPattern(indirect)) == null) {
            COSBase cOSBase = get(COSName.PATTERN, cOSName);
            PDAbstractPattern create = cOSBase instanceof COSDictionary ? PDAbstractPattern.create((COSDictionary) cOSBase, getResourceCache()) : null;
            ResourceCache resourceCache2 = this.cache;
            if (resourceCache2 != null && indirect != null) {
                resourceCache2.put(indirect, create);
            }
            return create;
        }
        return pattern;
    }

    public PDPropertyList getProperties(COSName cOSName) {
        PDPropertyList properties;
        COSObject indirect = getIndirect(COSName.PROPERTIES, cOSName);
        ResourceCache resourceCache = this.cache;
        if (resourceCache == null || indirect == null || (properties = resourceCache.getProperties(indirect)) == null) {
            COSBase cOSBase = get(COSName.PROPERTIES, cOSName);
            PDPropertyList create = cOSBase instanceof COSDictionary ? PDPropertyList.create((COSDictionary) cOSBase) : null;
            ResourceCache resourceCache2 = this.cache;
            if (resourceCache2 != null && indirect != null) {
                resourceCache2.put(indirect, create);
            }
            return create;
        }
        return properties;
    }

    public boolean isImageXObject(COSName cOSName) {
        COSBase cOSBase = get(COSName.XOBJECT, cOSName);
        if (cOSBase == null) {
            return false;
        }
        if (cOSBase instanceof COSObject) {
            cOSBase = ((COSObject) cOSBase).getObject();
        }
        if (cOSBase instanceof COSStream) {
            return COSName.IMAGE.equals(((COSStream) cOSBase).getCOSName(COSName.SUBTYPE));
        }
        return false;
    }

    public PDXObject getXObject(COSName cOSName) throws IOException {
        PDXObject createXObject;
        PDXObject xObject;
        COSObject indirect = getIndirect(COSName.XOBJECT, cOSName);
        ResourceCache resourceCache = this.cache;
        if (resourceCache == null || indirect == null || (xObject = resourceCache.getXObject(indirect)) == null) {
            COSBase cOSBase = get(COSName.XOBJECT, cOSName);
            if (cOSBase == null) {
                createXObject = null;
            } else if (cOSBase instanceof COSObject) {
                createXObject = PDXObject.createXObject(((COSObject) cOSBase).getObject(), this);
            } else {
                createXObject = PDXObject.createXObject(cOSBase, this);
            }
            if (this.cache != null && indirect != null && isAllowedCache(createXObject)) {
                this.cache.put(indirect, createXObject);
            }
            return createXObject;
        }
        return xObject;
    }

    private boolean isAllowedCache(PDXObject pDXObject) {
        if (pDXObject instanceof PDImageXObject) {
            COSBase dictionaryObject = pDXObject.getCOSObject().getDictionaryObject(COSName.COLORSPACE);
            if (dictionaryObject instanceof COSName) {
                COSName cOSName = (COSName) dictionaryObject;
                if (cOSName.equals(COSName.DEVICECMYK) && hasColorSpace(COSName.DEFAULT_CMYK)) {
                    return false;
                }
                if (cOSName.equals(COSName.DEVICERGB) && hasColorSpace(COSName.DEFAULT_RGB)) {
                    return false;
                }
                return ((cOSName.equals(COSName.DEVICEGRAY) && hasColorSpace(COSName.DEFAULT_GRAY)) || hasColorSpace(cOSName)) ? false : true;
            }
            return true;
        }
        return true;
    }

    private COSObject getIndirect(COSName cOSName, COSName cOSName2) {
        COSDictionary cOSDictionary = this.resources.getCOSDictionary(cOSName);
        if (cOSDictionary == null) {
            return null;
        }
        COSBase item = cOSDictionary.getItem(cOSName2);
        if (item instanceof COSObject) {
            return (COSObject) item;
        }
        return null;
    }

    private COSBase get(COSName cOSName, COSName cOSName2) {
        COSDictionary cOSDictionary = this.resources.getCOSDictionary(cOSName);
        if (cOSDictionary == null) {
            return null;
        }
        return cOSDictionary.getDictionaryObject(cOSName2);
    }

    public Iterable<COSName> getColorSpaceNames() {
        return getNames(COSName.COLORSPACE);
    }

    public Iterable<COSName> getXObjectNames() {
        return getNames(COSName.XOBJECT);
    }

    public Iterable<COSName> getFontNames() {
        return getNames(COSName.FONT);
    }

    public Iterable<COSName> getPropertiesNames() {
        return getNames(COSName.PROPERTIES);
    }

    public Iterable<COSName> getShadingNames() {
        return getNames(COSName.SHADING);
    }

    public Iterable<COSName> getPatternNames() {
        return getNames(COSName.PATTERN);
    }

    public Iterable<COSName> getExtGStateNames() {
        return getNames(COSName.EXT_G_STATE);
    }

    private Iterable<COSName> getNames(COSName cOSName) {
        COSDictionary cOSDictionary = this.resources.getCOSDictionary(cOSName);
        if (cOSDictionary == null) {
            return Collections.emptySet();
        }
        return cOSDictionary.keySet();
    }

    public COSName add(PDFont pDFont) {
        return add(COSName.FONT, "F", pDFont);
    }

    public COSName add(PDColorSpace pDColorSpace) {
        return add(COSName.COLORSPACE, OperatorName.NON_STROKING_COLORSPACE, pDColorSpace);
    }

    public COSName add(PDExtendedGraphicsState pDExtendedGraphicsState) {
        return add(COSName.EXT_G_STATE, OperatorName.SET_GRAPHICS_STATE_PARAMS, pDExtendedGraphicsState);
    }

    public COSName add(PDShading pDShading) {
        return add(COSName.SHADING, OperatorName.SHADING_FILL, pDShading);
    }

    public COSName add(PDAbstractPattern pDAbstractPattern) {
        return add(COSName.PATTERN, "p", pDAbstractPattern);
    }

    public COSName add(PDPropertyList pDPropertyList) {
        if (pDPropertyList instanceof PDOptionalContentGroup) {
            return add(COSName.PROPERTIES, "oc", pDPropertyList);
        }
        return add(COSName.PROPERTIES, "Prop", pDPropertyList);
    }

    public COSName add(PDImageXObject pDImageXObject) {
        return add(COSName.XOBJECT, "Im", pDImageXObject);
    }

    public COSName add(PDFormXObject pDFormXObject) {
        return add(COSName.XOBJECT, StandardStructureTypes.FORM, pDFormXObject);
    }

    public COSName add(PDXObject pDXObject, String str) {
        return add(COSName.XOBJECT, str, pDXObject);
    }

    private COSName add(COSName cOSName, String str, COSObjectable cOSObjectable) {
        COSDictionary cOSDictionary = this.resources.getCOSDictionary(cOSName);
        if (cOSDictionary != null && cOSDictionary.containsValue(cOSObjectable.getCOSObject())) {
            return cOSDictionary.getKeyForValue(cOSObjectable.getCOSObject());
        }
        if (cOSDictionary != null && COSName.FONT.equals(cOSName)) {
            for (Map.Entry<COSName, COSBase> entry : cOSDictionary.entrySet()) {
                if ((entry.getValue() instanceof COSObject) && cOSObjectable.getCOSObject() == ((COSObject) entry.getValue()).getObject()) {
                    return entry.getKey();
                }
            }
        }
        COSName createKey = createKey(cOSName, str);
        put(cOSName, createKey, cOSObjectable);
        return createKey;
    }

    private COSName createKey(COSName cOSName, String str) {
        String str2;
        COSDictionary cOSDictionary = this.resources.getCOSDictionary(cOSName);
        if (cOSDictionary == null) {
            return COSName.getPDFName(str + 1);
        }
        int size = cOSDictionary.keySet().size();
        do {
            size++;
            str2 = str + size;
        } while (cOSDictionary.containsKey(str2));
        return COSName.getPDFName(str2);
    }

    private void put(COSName cOSName, COSName cOSName2, COSObjectable cOSObjectable) {
        COSDictionary cOSDictionary = this.resources.getCOSDictionary(cOSName);
        if (cOSDictionary == null) {
            cOSDictionary = new COSDictionary();
            this.resources.setItem(cOSName, (COSBase) cOSDictionary);
        }
        cOSDictionary.setItem(cOSName2, cOSObjectable);
    }

    public void put(COSName cOSName, PDFont pDFont) {
        put(COSName.FONT, cOSName, pDFont);
    }

    public void put(COSName cOSName, PDColorSpace pDColorSpace) {
        put(COSName.COLORSPACE, cOSName, pDColorSpace);
    }

    public void put(COSName cOSName, PDExtendedGraphicsState pDExtendedGraphicsState) {
        put(COSName.EXT_G_STATE, cOSName, pDExtendedGraphicsState);
    }

    public void put(COSName cOSName, PDShading pDShading) {
        put(COSName.SHADING, cOSName, pDShading);
    }

    public void put(COSName cOSName, PDAbstractPattern pDAbstractPattern) {
        put(COSName.PATTERN, cOSName, pDAbstractPattern);
    }

    public void put(COSName cOSName, PDPropertyList pDPropertyList) {
        put(COSName.PROPERTIES, cOSName, pDPropertyList);
    }

    public void put(COSName cOSName, PDXObject pDXObject) {
        put(COSName.XOBJECT, cOSName, pDXObject);
    }

    public ResourceCache getResourceCache() {
        return this.cache;
    }
}
