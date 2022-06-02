package com.object.utils;

import java.io.File;

/**
 * 文件
 */
public class MyFile {


    /**
     * 文件进行删除
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        isFile(file);
    }

    private static void isFile(File file) {
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (!file1.isFile()) {
                isFile(file1);
            } else {
                file1.delete();
            }
        }
    }
}
