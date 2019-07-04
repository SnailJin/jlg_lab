package com.jin.tool.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Utils {


    /**
     *  判断不为空
     * @param object
     * @return
     */
    public static Boolean isNotNull(Object object){
        Boolean flag = false;
        if(object instanceof java.util.List){
            if(object != null && !((List) object).isEmpty() ){
                flag = true;
            }
        }else if(object instanceof java.util.Set){
            if(object != null && !((Set) object).isEmpty() ){
                flag = true;
            }
        }else if(object instanceof java.lang.String){
            if(((String) object).trim().length()>0 ){
                flag = true;
            }
        }else if(object instanceof java.util.Map){
            if(!((Map) object).isEmpty()){
                flag = true;
            }
        }else{
            if(object != null){
                flag = true;
            }
        }
        return flag;
    }
    public static Boolean isNull(Object object){
        return !isNotNull(object);
    }
	
	  /**
     * 判断object是否为基本类型
     * @param object
     * @return
     */
    public static boolean isBaseType(Object object) {
        Class className = object.getClass();
        if (className.equals(java.lang.Integer.class) ||
                className.equals(java.lang.Byte.class) ||
                className.equals(java.lang.Long.class) ||
                className.equals(java.lang.Double.class) ||
                className.equals(java.lang.Float.class) ||
                className.equals(java.lang.Character.class) ||
                className.equals(java.lang.Short.class) ||
                className.equals(java.lang.Boolean.class)) {
            return true;
        }
        return false;
    }



    public static void main(String[] args) {
        int i = 1;
        Boolean flag = true;
        Utils utils = null;
        List list = new ArrayList();
        System.out.println( Utils.isNotNull(i));
        System.out.println( Utils.isNotNull(flag));
        System.out.println( Utils.isNotNull(utils));
        System.out.println( Utils.isNotNull(list));
    }
}
