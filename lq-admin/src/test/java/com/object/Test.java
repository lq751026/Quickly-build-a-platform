package com.object;

import com.alibaba.excel.EasyExcel;
import com.object.module.lq.sys.entity.ImportTUserEntity;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;


public class Test {



    public static void main(String[] args) throws UnknownHostException {
     //Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.63 Safari/537.36 Edg/102.0.1245.33
        String data="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.63 Safari/537.36 Edg/102.0.1245.33";
        String substring = data.substring(data.indexOf("(")+1, data.indexOf(")"));
        String pser = substring.substring(0, substring.indexOf(";"));
        System.out.println(pser);
    }
}
