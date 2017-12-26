package com.zqh.str;

import java.util.*;

/**
 * @author zqh
 * @description
 * @company www.jiweitech.com
 * @date 2017-12-26 17:37
 **/
public class StringMatcher {
    private static ArrayList<String> strList = new ArrayList<String>(5);
    private static final String REGEX = "\\$\\{[^}]*}";
    private static Iterator<String> iterator;

    public StringMatcher(String templateContent) {
        String[] splits = templateContent.split(REGEX, -1);
        Collections.addAll(strList, splits);
        iterator = strList.iterator();
    }

    /*private boolean match(String msg) {
        String flag = null;
        int isHead = 0;

        int listLen = strList.size();
        for (int i = 0; i < listLen; i++) {
            String next = strList.get(i);
            int index = msg.indexOf(next);

            if (index == -1) {
                return false;
            }

            isHead++;
            if (isHead == 1 && !"".equals(next) && index != 0) {
                return false;
            }

            msg = msg.substring(index + next.length());
            flag = next;
        }

        return "".equals(flag) || "".equals(msg);
    }*/

    private boolean match(String msg) {
        String flag = null;
        int isHead = 0;
        while (iterator.hasNext()) {
            String next = iterator.next();
            int index = msg.indexOf(next);
            if (index == -1) {
                return false;
            }

            isHead++;
            if (isHead == 1 && !"".equals(next) && index != 0) {
                return false;
            }

            msg = msg.substring(index + next.length());
            flag = next;
        }

        return "".equals(flag) || "".equals(msg);
    }

    public static void main(String[] args) {
        Map<String, StringMatcher> matcherMap = new HashMap<String, StringMatcher>();
        StringMatcher matcher = new StringMatcher("尊敬的${username}，您好！您的尾号${cardCode}卡${datetime}POS支出${money}元，余额${balance}元。");
        matcherMap.put("1", matcher);

        long l = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            Iterator<String> ite = matcherMap.keySet().iterator();
            while (ite.hasNext()) {
                StringMatcher m = matcherMap.get(ite.next());
                boolean match = m.match("尊敬的张三，您好！您的尾号1234卡12月26日16:01POS支出1.00元，余额0.01元。");
                //System.out.println(match);
            }
        }

        System.out.println("100w短信匹配耗时：" + (System.currentTimeMillis() -l) + "ms");
    }
}
