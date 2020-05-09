package com.jin.tool.img;

import com.jin.tool.http.HttpUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 給图片添加图片
 *
 * @author liqiang
 *
 */
@Slf4j
@Data
public class WaterMarkUtils {
    /**
     * 透明度
     */
    private float alpha ;
    /**
     * iocn的x坐标
     */
    private Integer xIcon;
    /**
     * iocn的y坐标
     */
    private Integer yIcon;
    /**
     * 水印图片旋转角度
     */
    private Integer degree;

    /**
     * @param args
     */
    public static void main(String[] args) {
        String srcImgPath = "F:\\test\\images\\qcCode.png";
        String iconPath = "F:\\test\\images\\56645648.jpg";
        String targerPath = "F:\\test\\images\\qcCode_1.png";
//        String targerPath2 = "C:/Users/liqiang/Desktop/图片/qlq2.jpeg";
        // 给图片添加水印
        WaterMarkUtils waterMarkUtils = new WaterMarkUtils();
        waterMarkUtils.setAlpha(1f);
        waterMarkUtils.setXIcon(225);
        waterMarkUtils.setYIcon(660);
        waterMarkUtils.markImageByIcon(iconPath, srcImgPath, targerPath);
//        // 给图片添加水印,水印旋转-45
//        WaterMarkUtils.markImageByIcon(iconPath, srcImgPath, targerPath2, -45);

    }


    /**
     * 给图片添加水印、可设置水印图片旋转角度
     *
     * @param iconPath
     *            水印图片路径
     * @param srcImgPath
     *            源图片路径
     * @param targerPath
     *            目标图片路径
     */
    public void markImageByIcon(String iconPath, String srcImgPath, String targerPath) {
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(iconPath);

            // 得到Image对象。
            Image img = imgIcon.getImage();
            this.markImageByIcon(img,srcImgPath,targerPath);

    }
   /**
           * 给图片添加水印、可设置水印图片旋转角度
     *
             * @param iconImg
     *            水印图片img
     * @param srcImgPath
     *            源图片路径

     */
    public BufferedImage markImageByIcon(Image iconImg, String srcImgPath) throws IOException {
            InputStream inputStream = HttpUtils.getImageStream(srcImgPath);
            Image srcImg = ImageIO.read(inputStream);
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
                    BufferedImage.TYPE_INT_RGB);

            // 得到画笔对象
            // Graphics g= buffImg.getGraphics();
            Graphics2D g = buffImg.createGraphics();

            // 设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,
                    0, null);

            if (null != degree) {
                // 设置水印旋转
                g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
            }

            if(this.alpha !=1f){
//                float alpha = 0.5f; // 透明度
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            }


            // 表示水印图片的位置
            g.drawImage(iconImg, this.xIcon, this.yIcon, null);

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

            g.dispose();

            return buffImg;
    }

    /**
     * 给图片添加水印、可设置水印图片旋转角度
     *
     * @param iconImg
     *            水印图片img
     * @param srcImgPath
     *            源图片路径
     * @param targerPath
     *            目标图片路径

     */
    public void markImageByIcon(Image iconImg, String srcImgPath, String targerPath) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(targerPath);

            // 生成图片
            ImageIO.write(markImageByIcon(iconImg,srcImgPath), "JPG", os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

