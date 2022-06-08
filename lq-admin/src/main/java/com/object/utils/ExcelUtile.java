package com.object.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.extension.service.IService;


import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

public class ExcelUtile<T> {

    public Q imports(String fileNmae, IService service, Class tClass) {
         AtomicInteger count= new AtomicInteger();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileNmae, tClass, new PageReadListener<T>(dataList -> {
            service.saveBatch(dataList);
            count.set(count.get()+dataList.size());
        })).sheet().doRead();
        return Q.ok().put("data", "保存记录数"+count);
    }

    public Q imports(InputStream inputStream, IService service, Class tClass) {
        AtomicInteger count= new AtomicInteger();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(inputStream, tClass, new PageReadListener<T>(dataList -> {
            service.saveBatch(dataList);
            count.set(count.get()+dataList.size());
        })).sheet().doRead();
        return Q.ok().put("data", "保存记录数"+count);
    }
}
