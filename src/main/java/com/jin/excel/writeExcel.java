package com.jin.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jin.httpclient.HttpClientUtil;
import com.jin.java.JDBC;

/**
 * 写excel
 * 
 * @author liguang.jin
 *
 */
public class writeExcel {
	public static JSONArray getSql() throws Exception {
		JDBC.getConnection();

//		List<List<String>> resultList = new ArrayList<List<String>>();
		JSONArray resultList = new JSONArray();
		JSONArray tableList = new JSONArray();
		String tableName = "";
		String sql = "SELECT c.table_name ,c.column_name, data_type,IS_NULLABLE,pgd.description  FROM pg_catalog.pg_statio_all_tables as st    inner join pg_catalog.pg_description pgd on (pgd.objoid=st.relid)    inner join information_schema.columns c on (pgd.objsubid=c.ordinal_position      and  c.table_schema=st.schemaname and c.table_name=st.relname) ORDER BY c.table_name";
		ResultSet ret = null;
		JDBC.pst = JDBC.conn_1.prepareStatement(sql);// 准备执行语句
		ret = JDBC.pst.executeQuery();
		while (ret.next()) {
			if(tableName.equals("")){
				tableName = ret.getString("table_name");
			}
			if( !tableName.equals(ret.getString("table_name"))){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("tableName", tableName);
				jsonObject.put("message", tableList);
				resultList.add(jsonObject);
				tableName = ret.getString("table_name");
				tableList = new JSONArray();
			}
			JSONArray row = new JSONArray();
			row.add(ret.getString("column_name"));
			row.add(ret.getString("data_type"));
			row.add(ret.getString("IS_NULLABLE"));
			row.add(ret.getString("description"));
			row.add(ret.getString("table_name"));
			tableList.add(row);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tableName", tableList.getJSONArray(0).getString(4));
		jsonObject.put("message", tableList);
		resultList.add(jsonObject);
		
		System.out.println(resultList.size());
		return resultList;
	}

	public static void createExcel() throws Exception {
		int num = 1;
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 获取List size作为excel行数
		int rowCount = 20;
		
		JSONArray resultList = getSql();
		for(int i =0 ;i<resultList.size();i++){
			JSONObject table = (JSONObject) resultList.get(i);
			System.out.println("sheet num:"+num++);
			HSSFSheet sheet = workbook.createSheet();
			sheet.setActive(true);
			int z=0;
			// 创建第一栏
			HSSFRow headRow = sheet.createRow(0);
			// 单元格合并
			// 四个参数分别是：起始行，起始列，结束行，结束列
			HSSFCell cell = headRow.createCell(z++);
			HSSFFont font = workbook.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints((short)14);
			cell.setCellValue(table.getString("tableName"));
			CellRangeAddress cra=new CellRangeAddress(0, 0, 0, 3);
			sheet.addMergedRegion(cra);
			HSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中    
			style.setFont(font);
			cell.setCellStyle(style);
			
			
			// 创建第二栏
			headRow = sheet.createRow(z++);
			String[] titleArray = { "字段名", "字段类型", "是否为空", "备注" };
			for (int m = 0; m < titleArray.length ; m++) {
				cell = headRow.createCell(m);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				sheet.setColumnWidth(m, 6000);
				style = workbook.createCellStyle();
				font = workbook.createFont();
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font.setFontName("宋体");    
				font.setFontHeightInPoints((short) 11);//设置字体大小 
				style=addBorder(style);
				style.setFont(font);
				// 填写数据
				cell.setCellStyle(style);
				cell.setCellValue(titleArray[m]);

			}
			
			style = workbook.createCellStyle();
			font = workbook.createFont();
			font.setFontName("宋体");    
			font.setFontHeightInPoints((short) 11);//设置字体大小 
			style=addBorder(style);
			style.setFont(font);
			JSONArray tableArray = table.getJSONArray("message");
			for(Object row : tableArray){
					int j=0;
					headRow = sheet.createRow(z++);
					int m = ((JSONArray)row).size()-2;
					// 填写数据
					cell.setCellStyle(style);
					for(Object col : (JSONArray)row){
						cell = headRow.createCell(j);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						sheet.setColumnWidth(j, 6000);
						cell.setCellStyle(style);
						cell.setCellValue((String)col);
						if(m == j)
							break;
						j++;
				}
			}
			
		}
		
		
		
		
		
		
		
		
		FileOutputStream os = null;
		try {
			os = new FileOutputStream("G:/test/excel.xls");
			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("success");

	}
	
	private static HSSFCellStyle  addBorder(HSSFCellStyle style){
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框    
		return  style;
	}

	public static void main(String[] args) throws Exception {
		createExcel();
	}
}
