package cn.shaines.filesystem.treefile;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyan on 2019-07-26
 */
public final class FileInfo {

    @JSONField(ordinal = 1)
    private final boolean isFile;
    @JSONField(ordinal = 2)
    private final String fileName;
    @JSONField(ordinal = 3)
    private final String absPath;
    @JSONField(ordinal = 4)
    private List<FileInfo> children = new ArrayList<FileInfo>();

    public FileInfo(TreeNode treeNode) {
        File file = new File(treeNode.getData());
        this.absPath = file.getAbsolutePath();
        this.fileName = file.getName();
        this.isFile = file.isFile();

        TreeNode temp = treeNode.getFirstChild();
        while (temp != null) {
            children.add(new FileInfo(temp));
            temp = temp.getNextSibling();
        }
    }

    public String getAbsPath() {
        return absPath;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isFile() {
        return isFile;
    }

    public List<FileInfo> getChildren() {
        return children;
    }

    public void setChildren(List<FileInfo> children) {
        this.children = children;
    }
}
