package com.tom_roush.pdfbox.contentstream.operator.markedcontent;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import java.util.List;

/* loaded from: classes3.dex */
public class BeginMarkedContentSequenceWithProperties extends OperatorProcessor {
    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return OperatorName.BEGIN_MARKED_CONTENT_SEQ;
    }

    @Override // com.tom_roush.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        COSName cOSName = null;
        COSDictionary cOSDictionary = null;
        for (COSBase cOSBase : list) {
            if (cOSBase instanceof COSName) {
                cOSName = (COSName) cOSBase;
            } else if (cOSBase instanceof COSDictionary) {
                cOSDictionary = (COSDictionary) cOSBase;
            }
        }
        this.context.beginMarkedContentSequence(cOSName, cOSDictionary);
    }
}
