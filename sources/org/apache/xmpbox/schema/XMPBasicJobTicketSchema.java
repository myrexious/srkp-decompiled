package org.apache.xmpbox.schema;

import java.util.ArrayList;
import java.util.List;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.type.AbstractField;
import org.apache.xmpbox.type.ArrayProperty;
import org.apache.xmpbox.type.BadFieldValueException;
import org.apache.xmpbox.type.Cardinality;
import org.apache.xmpbox.type.JobType;
import org.apache.xmpbox.type.PropertyType;
import org.apache.xmpbox.type.StructuredType;
import org.apache.xmpbox.type.Types;

@StructuredType(namespace = "http://ns.adobe.com/xap/1.0/bj/", preferedPrefix = "xmpBJ")
/* loaded from: classes2.dex */
public class XMPBasicJobTicketSchema extends XMPSchema {
    @PropertyType(card = Cardinality.Bag, type = Types.Job)
    public static final String JOB_REF = "JobRef";
    private ArrayProperty bagJobs;

    public XMPBasicJobTicketSchema(XMPMetadata xMPMetadata) {
        this(xMPMetadata, null);
    }

    public XMPBasicJobTicketSchema(XMPMetadata xMPMetadata, String str) {
        super(xMPMetadata, str);
    }

    public void addJob(String str, String str2, String str3) {
        addJob(str, str2, str3, null);
    }

    public void addJob(String str, String str2, String str3, String str4) {
        JobType jobType = new JobType(getMetadata(), str4);
        jobType.setId(str);
        jobType.setName(str2);
        jobType.setUrl(str3);
        addJob(jobType);
    }

    public void addJob(JobType jobType) {
        String namespacePrefix = getNamespacePrefix(jobType.getNamespace());
        if (namespacePrefix != null) {
            jobType.setPrefix(namespacePrefix);
        } else {
            addNamespace(jobType.getNamespace(), jobType.getPrefix());
        }
        if (this.bagJobs == null) {
            ArrayProperty createArrayProperty = createArrayProperty(JOB_REF, Cardinality.Bag);
            this.bagJobs = createArrayProperty;
            addProperty(createArrayProperty);
        }
        this.bagJobs.getContainer().addProperty(jobType);
    }

    public List<JobType> getJobs() throws BadFieldValueException {
        List<AbstractField> unqualifiedArrayList = getUnqualifiedArrayList(JOB_REF);
        if (unqualifiedArrayList != null) {
            ArrayList arrayList = new ArrayList();
            for (AbstractField abstractField : unqualifiedArrayList) {
                if (abstractField instanceof JobType) {
                    arrayList.add((JobType) abstractField);
                } else {
                    throw new BadFieldValueException("Job expected and " + abstractField.getClass().getName() + " found.");
                }
            }
            return arrayList;
        }
        return null;
    }
}
