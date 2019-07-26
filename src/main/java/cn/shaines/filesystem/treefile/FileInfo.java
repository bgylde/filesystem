package cn.shaines.filesystem.treefile;

import java.io.File;

/**
 * Created by wangyan on 2019-07-26
 */
public final class FileInfo {

    private final String absFilePath;
    private final String fileName;

    private final boolean isFile;

    private final FileInfo parentFileInfo;

    public FileInfo(String absPath, FileInfo parentFileInfo) {
        File file = new File(absPath);
        this.absFilePath = file.getAbsolutePath();
        this.fileName = file.getName();
        this.isFile = file.isFile();
        this.parentFileInfo = parentFileInfo;
    }

    public String getAbsFilePath() {
        return absFilePath;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isFile() {
        return isFile;
    }

    public FileInfo getParentFileInfo() {
        return parentFileInfo;
    }
}
