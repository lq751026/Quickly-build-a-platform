package com.object;

import com.wf.captcha.ArithmeticCaptcha;

import java.net.UnknownHostException;

public class Test {



    public static void main(String[] args) throws UnknownHostException {
         String data="icon-chinese-fill\n" +
                 "icon-english-fill\n" +
                 "icon-face-frown-fill\n" +
                 "icon-face-meh-fill\n" +
                 "icon-face-smile-fill\n" +
                 "icon-moon-fill\n" +
                 "icon-pen-fill\n" +
                 "icon-sun-fill\n" +
                 "icon-apps\n" +
                 "icon-archive\n" +
                 "icon-bar-chart\n" +
                 "icon-book\n" +
                 "icon-bookmark\n" +
                 "icon-branch\n" +
                 "icon-bug\n" +
                 "icon-bulb\n" +
                 "icon-calendar-clock\n" +
                 "icon-calendar\n" +
                 "icon-camera\n" +
                 "icon-cloud\n" +
                 "icon-command\n" +
                 "icon-common\n" +
                 "icon-compass\n" +
                 "icon-computer\n" +
                 "icon-copyright\n" +
                 "icon-dashboard\n" +
                 "icon-desktop\n" +
                 "icon-dice\n" +
                 "icon-drag-dot-vertical\n" +
                 "icon-drag-dot\n" +
                 "icon-drive-file\n" +
                 "icon-ear\n" +
                 "icon-email\n" +
                 "icon-empty\n" +
                 "icon-experiment\n" +
                 "icon-file-audio\n" +
                 "icon-file-image\n" +
                 "icon-file-pdf\n" +
                 "icon-file-video\n" +
                 "icon-file\n" +
                 "icon-fire\n" +
                 "icon-folder-add\n" +
                 "icon-folder-delete\n" +
                 "icon-folder\n" +
                 "icon-gift\n" +
                 "icon-idcard\n" +
                 "icon-image-close\n" +
                 "icon-image\n" +
                 "icon-interaction\n" +
                 "icon-language\n" +
                 "icon-layers\n" +
                 "icon-layout\n" +
                 "icon-loading\n" +
                 "icon-location\n" +
                 "icon-lock\n" +
                 "icon-loop\n" +
                 "icon-man\n" +
                 "icon-menu\n" +
                 "icon-mind-mapping\n" +
                 "icon-mobile\n" +
                 "icon-moon\n" +
                 "icon-mosaic\n" +
                 "icon-nav\n" +
                 "icon-notification-close\n" +
                 "icon-notification\n" +
                 "icon-palette\n" +
                 "icon-pen\n" +
                 "icon-phone\n" +
                 "icon-printer\n" +
                 "icon-public\n" +
                 "icon-pushpin\n" +
                 "icon-qrcode\n" +
                 "icon-relation\n" +
                 "icon-robot-add\n" +
                 "icon-robot\n" +
                 "icon-safe\n" +
                 "icon-schedule\n" +
                 "icon-shake\n" +
                 "icon-skin\n" +
                 "icon-stamp\n" +
                 "icon-storage\n" +
                 "icon-subscribe-add\n" +
                 "icon-subscribe\n" +
                 "icon-subscribed\n" +
                 "icon-sun\n" +
                 "icon-tag\n" +
                 "icon-tags\n" +
                 "icon-thunderbolt\n" +
                 "icon-tool\n" +
                 "icon-trophy\n" +
                 "icon-unlock\n" +
                 "icon-user-add\n" +
                 "icon-user-group\n" +
                 "icon-user\n" +
                 "icon-video-camera\n" +
                 "icon-wifi\n" +
                 "icon-woman\n";
        String[] split = data.split("\n");
        String print="";
        for (String ioc : split) {
            print+="    <a-option >\n" +
                    "        <"+ioc+" />\n" +
                    "       "+ioc+"\n" +
                    "      </a-option>\n\n";
        }
        System.out.println(print);
    }
}
