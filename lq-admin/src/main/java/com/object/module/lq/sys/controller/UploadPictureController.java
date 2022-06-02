package com.object.module.lq.sys.controller;

import com.object.utils.ImageUtil;
import com.object.utils.Q;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/upload")
public class UploadPictureController {


    @PostMapping("/img")
    public Q saveImg(@RequestParam("file") MultipartFile file) {
        String fileName = ImageUtil.PhotoCompression(file);
        return fileName!=null?Q.ok().put("fileName", fileName):Q.error();
    }

}
