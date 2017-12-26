package com.zqh.str;

import java.util.regex.Pattern;

/**
 * @author zqh
 * @description main
 * @company www.jiweitech.com
 * @date 2017-12-26 15:28
 **/
public class PatternTest1 {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        for(int i=0;i<1000000;i++) {
            test();
        }
        System.out.println("100w短信匹配耗时：" + (System.currentTimeMillis() -l) + "ms");
    }

    private static boolean test() {
        String msg = "尊敬的张三，您好！您的尾号1234卡12月26日16:01POS支出1.00元，余额0.01元。";
        String tempContent = "尊敬的${username}，您好！您的尾号${cardCode}卡${datetime}POS支出${money}元，余额${balance}元。";
        String regex = "\\$\\{[^}]*}";
        String temp = tempContent.replaceAll(regex, ".*");
        boolean matches = Pattern.matches(temp, msg);
        return matches;
    }
}
