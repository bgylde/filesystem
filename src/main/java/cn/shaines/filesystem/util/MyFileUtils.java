package cn.shaines.filesystem.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author houyu
 * @createTime 2019/3/9 23:07
 */
public class MyFileUtils {

    private static final String FILE_DIRECTORY = "./upload";

    public MyFileUtils() {
        initFile();
    }

    private void initFile() {
        File file = new File(FILE_DIRECTORY);
        if (!file.exists() && !file.mkdirs()) {
            throw new RuntimeException("Creating director [" + file.getAbsolutePath() + "] fail.");
        }
    }

    public int deleteFiles(String... files) {
        int successCount = 0;
        for (String fileName : files) {
            String filePath = FILE_DIRECTORY + File.separator + fileName;
            File file = new File(filePath);
            if (file.exists() && file.delete()) {
                successCount++;
            }
        }

        return successCount;
    }

    public byte[] findByName(String name) throws IOException {
        String pathPath = FILE_DIRECTORY + File.separator + name;
        File file = new File(pathPath);
        return FileUtils.readFileToByteArray(file);
    }

    public void uploadFile(String name, byte[] bytes) throws IOException {
        String pathPath = FILE_DIRECTORY + File.separator + name;
        File file = new File(pathPath);
        if (file.exists()) {
            throw new IOException("File [" + pathPath + "] exists!");
        } else {
            FileUtils.writeByteArrayToFile(file, bytes);
        }
    }
}
