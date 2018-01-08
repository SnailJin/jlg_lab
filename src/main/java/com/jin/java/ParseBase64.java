/**  
 * Project Name:jlg_lab  
 * File Name:ParseBase64.java  
 * Package Name:com.jin.java  
 * Date:2017年11月15日下午8:40:42  
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.  
 *  
 */

package com.jin.java;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.jin.util.Base64Utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * ClassName:ParseBase64 Function: TODO ADD FUNCTION. Reason: TODO ADD REASON.
 * Date: 2017年11月15日 下午8:40:42
 * 
 * @author liguang.jin
 * @version
 * @see
 */
public class ParseBase64 {

	/**
	 * @Description: 根据图片地址转换为base64编码字符串
	 * @Author:
	 * @CreateTime:
	 * @return
	 */
	public static String getImageStr(String imgFile) {

		InputStream inputStream = null;
		byte[] data = null;
		try {
			inputStream = new FileInputStream(imgFile);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 加密
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	/**
	 * @Description: 将base64编码字符串转换为图片
	 * @Author:
	 * @CreateTime:
	 * @param imgStr
	 *            base64编码字符串
	 * @param path
	 *            图片路径-具体到文件
	 * @return
	 */
	public static boolean generateImage(String imgStr, String path) {

		if (imgStr == null)
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// 解密
			byte[] b = decoder.decodeBuffer(imgStr);
			
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			InputStream input = new ByteArrayInputStream(b);
			String suffix = "";
//            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
//                suffix = ".jpg";
//            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
//                suffix = ".ico";
//            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
//                suffix = ".gif";
//            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
//                suffix = ".png";
//            }else{
//                throw new Exception("上传图片格式不合法");
//            }
//			OutputStream out = new FileOutputStream(path);
//			out.write(b);
//			out.flush();
//			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) throws Exception {

		Base64Utils baseUtil = new Base64Utils();
		String str = baseUtil.encodeFile("G:\\Download\\11.png");
		String base64 = "data:image/jpeg;base64,"+str;
          String dataPrix = "";
          String data = "";

          if(base64 == null || "".equals(base64)){
              throw new Exception("上传失败，上传图片数据为空");
          }else{
              String [] d = base64.split("base64,");
              if(d != null && d.length == 2){
                  dataPrix = d[0];
                  data = d[1];
              }else{
                  throw new Exception("上传失败，数据不合法");
              }
          }

          String suffix = "";
          if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
              suffix = ".jpg";
          } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
              suffix = ".ico";
          } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
              suffix = ".gif";
          } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
              suffix = ".png";
          }else{
              throw new Exception("上传图片格式不合法");
          }
          System.out.println(suffix);
          baseUtil.decodeToFile("G:\\Download\\1123232.png", data);
		System.out.println(str);
		System.out.println(base64.substring(0, 100));
//		generateImage(str, "G:\\Download\\1123232.png");
	}
}
