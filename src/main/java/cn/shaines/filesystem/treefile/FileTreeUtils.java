package cn.shaines.filesystem.treefile;

import com.alibaba.fastjson.JSON;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

/**
 * Created by wangyan on 2019-07-26
 */
public class FileTreeUtils {

    private static final String TEST_PATH = "/Users/wangyan/Desktop/chromium相关文档";

    public static void getFileTree(String path, TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        treeNode.setData(path);
        stack.push(treeNode);

        do {
            TreeNode tempNode = stack.pop();
            File tempFile = new File(tempNode.getData());
            File[] files = tempFile.listFiles();
            if (files == null || files.length <= 0) {
                continue;
            }

            for (File file : files) {
                TreeNode newNode = new TreeNode(file.getPath(), null, null);
                if (tempNode.getFirstChild() == null) {
                    tempNode.setFirstChild(newNode);
                } else {
                    TreeNode temp = tempNode.getFirstChild();
                    while(temp.getNextSibling() != null) temp = temp.getNextSibling();
                    temp.setNextSibling(newNode);
                }

                if (file.isDirectory()) {
                    stack.push(newNode);
                }
            }
        } while (!stack.empty());
    }

    private static void printTree(TreeNode root, int deep) {
        if (root.getData() != null) {
            for (int i = 0; i < deep; i++)//输出前置空格
                System.out.print("    ");
            System.out.println(root.getFileName());
        }

        if (root.getFirstChild() != null)
            printTree(root.getFirstChild(), deep + 1);
        if (root.getNextSibling() != null)
            printTree(root.getNextSibling(), deep);
    }

    public static void uploadFile(File file, byte[] bytes) throws IOException {
        if (file.exists()) {
            throw new IOException("File [" + file.getPath() + "] exists!");
        } else {
            FileUtils.writeByteArrayToFile(file, bytes);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(TEST_PATH, null, null);
        FileTreeUtils.getFileTree(TEST_PATH, root);
        FileInfo fileInfo = new FileInfo(root);
        String json = JSON.toJSONString(fileInfo);
        System.out.println(json);
        FileTreeUtils.printTree(root, 0);
    }
}
