package com.object.utils;

import com.object.config.FileConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件集
 */
public class FilesUtils {

    private static final List<String> list = new ArrayList<>();

    /**
     * 初始化参数
     */
    private static void init() {
        if (list.size() > 0) list.clear();
        selectFilePaths(FileConfig.PATH, FileConfig.headImgUrl);
        selectFilePaths(FileConfig.FILEPATH, FileConfig.headFileUrl);
    }

    /**
     * 获取文件集
     *
     * @return 文件集合
     */
    public static List<String> fileList() {
        init();
        return list;
    }

    private static void selectFilePaths(String path, String headUrl) {
        File file = new File(path);
        File[] files = file.listFiles();
        if (files == null || files.length < 1) return;
        for (File file1 : files) {
            if (file1.getName().equals("file.png"))continue;
            String filePath = headUrl + file1.getName();
            list.add(filePath);
        }
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名和路径
     * @return true 为删除成功
     */
    public static boolean deleteFileName(String fileName) {
        boolean flag = false;
        String rootPath = FileConfig.rootPath;
        File file = new File(rootPath + fileName);
        if (file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    public static void main(String[] args) {
        List<String> strings = fileList();
        strings.forEach(System.out::println);
        List<String> strings1 = fileList();
        strings1.forEach(System.out::println);
        System.out.println(deleteFileName("/file/1.txt"));
    }
}
