package com.jin.tool.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * 工具类
 */
public class Tools {
    private static final String UNDER_LINE = "_";
    /**
     *  下划线转驼峰
     * @param str
     * @return
     */
    public static String underlineToCamel(String str){
        if(StringUtils.isBlank(str)){
            return "";
        }
        String[] strS = str.split(UNDER_LINE);
        StringBuffer result = new StringBuffer();
        for (String s : strS) {
            result.append(s.substring(0, 1).toUpperCase() + s.substring(1));
        }
        return result.toString();
    }

    /**
     * 获取随机数
     * @param length
     * @return
     */
    public static String getNumRandom(Integer length){
        if(length == null){
            throw new NullPointerException("长度不能为空");
        }
        StringBuffer stringBuffer = new StringBuffer();
        Random random =new Random();
        for (int i =0 ;i <length ;i++){
            stringBuffer.append(random.nextInt(10));
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {

    }
}
