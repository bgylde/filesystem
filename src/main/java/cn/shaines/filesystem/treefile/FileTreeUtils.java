package cn.shaines.filesystem.treefile;

import java.io.File;
import java.util.Stack;

/**
 * Created by wangyan on 2019-07-26
 */
public class FileTreeUtils {

    private static final String TEST_PATH = "/Users/wangyan/Desktop/chromium相关文档";

    private static class TreeNode {
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
    }

    private static class FileTree {
        private TreeNode root = new TreeNode(TEST_PATH, null, null);

        private TreeNode getRoot() {
            return root;
        }

        public void getFileTree(String path, TreeNode treeNode) {
            Stack<TreeNode> stack = new Stack<>();
            treeNode.data = path;
            stack.push(treeNode);

            do {
                TreeNode tempNode = stack.pop();
                File tempFile = new File(tempNode.data);
                File[] files = tempFile.listFiles();
                if (files == null || files.length <= 0) {
                    continue;
                }

                for (File file : files) {
                    TreeNode newNode = new TreeNode(file.getPath(), null, null);
                    if (tempNode.firstChild == null) {
                        tempNode.firstChild = newNode;
                    } else {
                        TreeNode temp = tempNode.firstChild;
                        while(temp.nextSibling != null) temp = temp.nextSibling;
                        temp.nextSibling = newNode;
                    }

                    if (file.isDirectory()) {
                        stack.push(newNode);
                    }
                }
            } while (!stack.empty());
        }

        public void printTree(TreeNode root, int deep) {
            if (root.data != null) {
                for (int i = 0; i < deep; i++)//输出前置空格
                    System.out.print("    ");
                System.out.println(root.getFileName());
            }

            if (root.firstChild != null)
                printTree(root.firstChild, deep + 1);
            if (root.nextSibling != null)
                printTree(root.nextSibling, deep);
        }
    }

    public static void main(String[] args) {
        FileTree test = new FileTree();
        test.getFileTree(TEST_PATH, test.getRoot());
        test.printTree(test.root, 0);
    }
}
