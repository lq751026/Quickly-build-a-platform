package com.object.module.lq.sys.controller;

import com.object.utils.FilesUtils;
import com.object.utils.Q;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/file")
@RestController
public class FilesController {


    @GetMapping("/list")
    public Q list() {
        return Q.ok().q("data", FilesUtils.fileList());
    }

    @GetMapping("/delete")
    public Q deleteFileName(String fileName) {
        return FilesUtils.deleteFileName(fileName) ? Q.ok() : Q.error();
    }


}
