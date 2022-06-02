package com.object.utils;

import cn.hutool.core.img.Img;
import cn.hutool.core.io.FileUtil;
import com.object.config.FileConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/***
 * 图片工具类
 */
public class ImageUtil {
    private static String host = "";


    static {
        try {
            final InetAddress localHost = InetAddress.getLocalHost();
            final String address = localHost.getHostAddress();
            host = address;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片压缩
     *
     * @return
     */
    public static String PhotoCompression(MultipartFile file) {
        String replace = UUID.randomUUID().toString().replace("-", "");

        try {
            File file1 = FileUtil.file(FileConfig.PATH + replace + ".png");
            if (!file1.exists()) {
                file1.mkdirs();
            }
            Img.from(file.getInputStream())
                    .setQuality(0.8)//压缩比率
                    .write(file1);
        } catch (IOException e) {
            return null;
        }
        return   "/img/" + replace + ".png";
    }

    /**
     * 更新图片的最新的ip路径
     *
     * @param fileName
     * @return
     */
    public static String PictureUpdateIpAddress(String fileName) {
        final String subString = fileName.substring(fileName.indexOf(":"));
        return subString;
    }


    public static void main(String[] args) throws FileNotFoundException {
        /*    System.out.println(PhotoCompression(new FileInputStream("C:\\Users\\李庆\\Desktop\\学习中的\\后台框架\\后台\\src\\main\\resources\\static\\img\\p1.jpg"), "p2"));
         */
        String path = "192.168.48.116:null\\object\\img\\p2";
        //final String subString = path.substring(path.indexOf(":"));
        System.out.println(PictureUpdateIpAddress(path));

    }

    /**
     * 文件上传
     *
     * @param file
     * @param port
     * @return
     */
    public static String JobFileUpload(MultipartFile file, String port) {
        String filename = file.getOriginalFilename();
        int i = filename.indexOf(".zip");
        if (i == -1) {
            return null;
        }
        String name = UUID.randomUUID().toString().replace("-", "");
        String FILEPATHs = FileConfig.FILEPATH + File.separator + name + ".zip";
        File file1 = new File(FILEPATHs);
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        try {
            file.transferTo(file1);
            return host + ":" + port + "/object" + "/file/" + name + ".zip";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
