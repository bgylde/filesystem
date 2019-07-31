package cn.shaines.filesystem.treefile;

import java.io.File;

/**
 * Created by wangyan on 2019-07-29
 */
public class TreeNode {
    private String data;
    private TreeNode firstChild;
    private TreeNode nextSibling;

    public TreeNode(String data, TreeNode first, TreeNode next) {
        if (data.endsWith(File.separator)) {
            data = data.substring(0, data.length() - 1);
        }

        this.data = data;
        this.firstChild = first;
        this.nextSibling = next;
    }

    public String getFileName() {
        if (data != null && data.trim().length() > 0) {
            int index = data.lastIndexOf(File.separatorChar);
            if (index > 0) {
                return data.substring(index + 1);
            }
        }

        return data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TreeNode getFirstChild() {
        return firstChild;
    }

    public void setFirstChild(TreeNode firstChild) {
        this.firstChild = firstChild;
    }

    public TreeNode getNextSibling() {
        return nextSibling;
    }

    public void setNextSibling(TreeNode nextSibling) {
        this.nextSibling = nextSibling;
    }
}
