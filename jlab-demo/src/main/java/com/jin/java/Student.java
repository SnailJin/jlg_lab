package com.jin.java;

import sun.misc.BASE64Decoder;

import java.io.IOException;

public class Student {

   private int code;
  public static  void main(String[] args){
      BASE64Decoder base64Encoder = new sun.misc.BASE64Decoder();
      String classStr = null;
      try {
          classStr = new String(base64Encoder.decodeBuffer("aHR0cDovL3d3dy55emFwcGxlLmNvbTo4MDkwL1lUZmVlZGJhY2svOTI4OTI5NjQ0LzEwMDQvQkU5%0ANTQzMEYtM0E3NC00QjUyLTk0MEQtODFDQUM5M0NFRTEx"),"UTF-8");
      } catch (IOException e) {
          e.printStackTrace();
      }
      System.out.println(classStr);

  }

    private static void testsets() {
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public Student test(){
      Student st = new Student();
      return st;
  }

}
