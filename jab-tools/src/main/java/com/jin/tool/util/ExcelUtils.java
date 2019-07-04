package com.jin.tool.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: Excel工具类
 * @author: liujinliang
 */

@Slf4j
public class ExcelUtils {

    /**
     * 2003 版本 最大支持65536 行
     */
    public static final String EXCEL_FILE_2003 = "2003";

    /**
     * 2007 版本以上 最大支持1048576行
     */
    public static final String EXCEL_FILE_2007 = "2007";

    /**
     * 数字正则表达式
     */
    private static final Pattern PATTERN = Pattern.compile("^//d+(//.//d+)?$");

    /**
     * 导出Excel文件
     *
     * @param tableName
     * @param cellTitle
     * @param cellData
     * @param out
     */
    public static void export(Workbook workbook, String tableName, List<String> cellTitle, List<?> cellData, OutputStream out) {
        try {
            // 第一步，创建一个webbook，对应一个Excel文件

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            Sheet sheet = workbook.createSheet(tableName);

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            Row row = sheet.createRow(0);

            // 第四步，创建单元格，并设置值表头 设置表头居中
            CellStyle style = workbook.createCellStyle();

            // 创建一个居中格式
            style.setAlignment(HorizontalAlignment.CENTER);

            // 生成一个字体
            Font font = workbook.createFont();

            // 设置字体
            font.setFontName("宋体");

            // 字体加粗
            font.setBold(true);
            font.setFontHeightInPoints((short) 14);

            // 样式中添加字体
            style.setFont(font);

            // 第五步设置标题行
            Cell cell;
            for (int i = 0; i < cellTitle.size(); i++) {
                cell = row.createCell(i);
                cell.setCellValue(cellTitle.get(i));
                cell.setCellStyle(style);
            }

            // 第六步写入数据
            for (int i = 0; i < cellData.size(); i++) {
                row = sheet.createRow(i + 1);
                List<?> modles = getObjectValueToList(cellData.get(i), new ArrayList());
                for (int j = 0; j < cellTitle.size(); j++) {

                    // 拿到属性值
                    Object value = modles.get(j);

                    // 根据属性值类型设置对应的Excel值
                    if (value instanceof Integer) {
                        row.createCell(j).setCellValue((Integer) value);
                    } else if (value instanceof Float) {
                        row.createCell(j).setCellValue((Float) value);
                    } else if (value instanceof Double) {
                        row.createCell(j).setCellValue((Double) value);
                    } else if (value instanceof Long) {
                        row.createCell(j).setCellValue((Long) value);
                    } else if (value instanceof Boolean) {
                        if ((Boolean) value) {
                            row.createCell(j).setCellValue("是");
                        } else {
                            row.createCell(j).setCellValue("否");
                        }
                    } else if (value instanceof Date) {
                        row.createCell(j).setCellValue(DateTools.format((Date) value));
                    } else {
                        // 其它数据类型
                        if (value != null) {
                            Matcher matcher = PATTERN.matcher(value.toString());
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                row.createCell(j).setCellValue(Double.parseDouble(value.toString()));
                            } else {
                                row.createCell(j).setCellValue(value.toString());
                            }
                        }
                    }
                }
            }

            // 第七步 写出文件
            workbook.write(out);
        } catch (Exception e) {
//            log.error("创建Excel并写入数据异常", e);
            throw new RuntimeException("创建Excel并写入数据异常", e);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 获取对象属性值
     *
     * @param object     对象
     * @param parameters 属性值列表
     * @return
     */
    public static List<Object> getObjectValueToList(Object object, List<Object> parameters) {
        try {
            // 拿到该类
            Class<?> clz = object.getClass();

            // 获取实体类的所有属性，返回Field数组
            Field[] field = clz.getDeclaredFields();

            for (Field key : field) {

                // 获取属性的名字
                String name = key.getName();

                // 将属性的首字符大写，方便构造get，set方法
                name = name.substring(0, 1).toUpperCase() + name.substring(1);

                // 获取属性的类型
                String type = key.getGenericType().toString();

                // 获取属性方法
                Method method = object.getClass().getMethod("get" + name);

                // 执行Get方法获取属性值
                parameters.add(method.invoke(object, new Object[]{}));
            }
        } catch (Exception e) {
            log.error("获取参数列表属性值异常", e);
            throw new RuntimeException("获取参数列表属性值异常", e);
        }
        return parameters;
    }
}