package org.apache.commons.io.monitor;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.NameFileComparator;

/* loaded from: classes2.dex */
public class FileAlterationObserver implements Serializable {
    private static final long serialVersionUID = 1185122225658782848L;
    private final Comparator<File> comparator;
    private final FileFilter fileFilter;
    private final List<FileAlterationListener> listeners;
    private final FileEntry rootEntry;

    public void destroy() throws Exception {
    }

    public FileAlterationObserver(String str) {
        this(new File(str));
    }

    public FileAlterationObserver(String str, FileFilter fileFilter) {
        this(new File(str), fileFilter);
    }

    public FileAlterationObserver(String str, FileFilter fileFilter, IOCase iOCase) {
        this(new File(str), fileFilter, iOCase);
    }

    public FileAlterationObserver(File file) {
        this(file, (FileFilter) null);
    }

    public FileAlterationObserver(File file, FileFilter fileFilter) {
        this(file, fileFilter, (IOCase) null);
    }

    public FileAlterationObserver(File file, FileFilter fileFilter, IOCase iOCase) {
        this(new FileEntry(file), fileFilter, iOCase);
    }

    protected FileAlterationObserver(FileEntry fileEntry, FileFilter fileFilter, IOCase iOCase) {
        this.listeners = new CopyOnWriteArrayList();
        if (fileEntry == null) {
            throw new IllegalArgumentException("Root entry is missing");
        }
        if (fileEntry.getFile() == null) {
            throw new IllegalArgumentException("Root directory is missing");
        }
        this.rootEntry = fileEntry;
        this.fileFilter = fileFilter;
        if (iOCase == null || iOCase.equals(IOCase.SYSTEM)) {
            this.comparator = NameFileComparator.NAME_SYSTEM_COMPARATOR;
        } else if (iOCase.equals(IOCase.INSENSITIVE)) {
            this.comparator = NameFileComparator.NAME_INSENSITIVE_COMPARATOR;
        } else {
            this.comparator = NameFileComparator.NAME_COMPARATOR;
        }
    }

    public File getDirectory() {
        return this.rootEntry.getFile();
    }

    public FileFilter getFileFilter() {
        return this.fileFilter;
    }

    public void addListener(FileAlterationListener fileAlterationListener) {
        if (fileAlterationListener != null) {
            this.listeners.add(fileAlterationListener);
        }
    }

    public void removeListener(FileAlterationListener fileAlterationListener) {
        if (fileAlterationListener != null) {
            do {
            } while (this.listeners.remove(fileAlterationListener));
        }
    }

    public Iterable<FileAlterationListener> getListeners() {
        return this.listeners;
    }

    public void initialize() throws Exception {
        FileEntry fileEntry = this.rootEntry;
        fileEntry.refresh(fileEntry.getFile());
        this.rootEntry.setChildren(doListFiles(this.rootEntry.getFile(), this.rootEntry));
    }

    public void checkAndNotify() {
        for (FileAlterationListener fileAlterationListener : this.listeners) {
            fileAlterationListener.onStart(this);
        }
        File file = this.rootEntry.getFile();
        if (file.exists()) {
            FileEntry fileEntry = this.rootEntry;
            checkAndNotify(fileEntry, fileEntry.getChildren(), listFiles(file));
        } else if (this.rootEntry.isExists()) {
            FileEntry fileEntry2 = this.rootEntry;
            checkAndNotify(fileEntry2, fileEntry2.getChildren(), FileUtils.EMPTY_FILE_ARRAY);
        }
        for (FileAlterationListener fileAlterationListener2 : this.listeners) {
            fileAlterationListener2.onStop(this);
        }
    }

    private void checkAndNotify(FileEntry fileEntry, FileEntry[] fileEntryArr, File[] fileArr) {
        FileEntry[] fileEntryArr2 = fileArr.length > 0 ? new FileEntry[fileArr.length] : FileEntry.EMPTY_ENTRIES;
        int i = 0;
        for (FileEntry fileEntry2 : fileEntryArr) {
            while (i < fileArr.length && this.comparator.compare(fileEntry2.getFile(), fileArr[i]) > 0) {
                FileEntry createFileEntry = createFileEntry(fileEntry, fileArr[i]);
                fileEntryArr2[i] = createFileEntry;
                doCreate(createFileEntry);
                i++;
            }
            if (i < fileArr.length && this.comparator.compare(fileEntry2.getFile(), fileArr[i]) == 0) {
                doMatch(fileEntry2, fileArr[i]);
                checkAndNotify(fileEntry2, fileEntry2.getChildren(), listFiles(fileArr[i]));
                fileEntryArr2[i] = fileEntry2;
                i++;
            } else {
                checkAndNotify(fileEntry2, fileEntry2.getChildren(), FileUtils.EMPTY_FILE_ARRAY);
                doDelete(fileEntry2);
            }
        }
        while (i < fileArr.length) {
            FileEntry createFileEntry2 = createFileEntry(fileEntry, fileArr[i]);
            fileEntryArr2[i] = createFileEntry2;
            doCreate(createFileEntry2);
            i++;
        }
        fileEntry.setChildren(fileEntryArr2);
    }

    private FileEntry createFileEntry(FileEntry fileEntry, File file) {
        FileEntry newChildInstance = fileEntry.newChildInstance(file);
        newChildInstance.refresh(file);
        newChildInstance.setChildren(doListFiles(file, newChildInstance));
        return newChildInstance;
    }

    private FileEntry[] doListFiles(File file, FileEntry fileEntry) {
        File[] listFiles = listFiles(file);
        FileEntry[] fileEntryArr = listFiles.length > 0 ? new FileEntry[listFiles.length] : FileEntry.EMPTY_ENTRIES;
        for (int i = 0; i < listFiles.length; i++) {
            fileEntryArr[i] = createFileEntry(fileEntry, listFiles[i]);
        }
        return fileEntryArr;
    }

    private void doCreate(FileEntry fileEntry) {
        for (FileAlterationListener fileAlterationListener : this.listeners) {
            if (fileEntry.isDirectory()) {
                fileAlterationListener.onDirectoryCreate(fileEntry.getFile());
            } else {
                fileAlterationListener.onFileCreate(fileEntry.getFile());
            }
        }
        for (FileEntry fileEntry2 : fileEntry.getChildren()) {
            doCreate(fileEntry2);
        }
    }

    private void doMatch(FileEntry fileEntry, File file) {
        if (fileEntry.refresh(file)) {
            for (FileAlterationListener fileAlterationListener : this.listeners) {
                if (fileEntry.isDirectory()) {
                    fileAlterationListener.onDirectoryChange(file);
                } else {
                    fileAlterationListener.onFileChange(file);
                }
            }
        }
    }

    private void doDelete(FileEntry fileEntry) {
        for (FileAlterationListener fileAlterationListener : this.listeners) {
            if (fileEntry.isDirectory()) {
                fileAlterationListener.onDirectoryDelete(fileEntry.getFile());
            } else {
                fileAlterationListener.onFileDelete(fileEntry.getFile());
            }
        }
    }

    private File[] listFiles(File file) {
        File[] fileArr;
        if (file.isDirectory()) {
            FileFilter fileFilter = this.fileFilter;
            fileArr = fileFilter == null ? file.listFiles() : file.listFiles(fileFilter);
        } else {
            fileArr = null;
        }
        if (fileArr == null) {
            fileArr = FileUtils.EMPTY_FILE_ARRAY;
        }
        Comparator<File> comparator = this.comparator;
        if (comparator != null && fileArr.length > 1) {
            Arrays.sort(fileArr, comparator);
        }
        return fileArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("[file='");
        sb.append(getDirectory().getPath());
        sb.append('\'');
        if (this.fileFilter != null) {
            sb.append(", ");
            sb.append(this.fileFilter.toString());
        }
        sb.append(", listeners=");
        sb.append(this.listeners.size());
        sb.append("]");
        return sb.toString();
    }
}