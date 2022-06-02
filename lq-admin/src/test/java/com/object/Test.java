package com.object;

import com.wf.captcha.ArithmeticCaptcha;

import java.net.UnknownHostException;

public class Test {



    public static void main(String[] args) throws UnknownHostException {

        ArithmeticCaptcha captcha1= new ArithmeticCaptcha(120, 40);
        captcha1.setLen(2);
        // 获取运算的结果
        String result = captcha1.text();
        System.out.println("result: "+result);
     }
}
