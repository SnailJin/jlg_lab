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
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Hyperlink;
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
public class writeExcelForSQLtable {
	/**
	 * 获取表sql
	 * @return
	 * @throws Exception
	 */
	public  JSONArray getSql() throws Exception {
		JSONArray resultList = new JSONArray();
		JSONArray tableList = new JSONArray();
		String tableName = "";
		String PG_SQL  = "SELECT c.table_name ,c.column_name, data_type,IS_NULLABLE,pgd.description FROM pg_catalog.pg_statio_all_tables as st "
				+ "  INNER join information_schema.columns c on    (c.table_schema=st.schemaname and c.table_name=st.relname)    "
				+ "left  join pg_catalog.pg_description pgd on (pgd.objsubid=c.ordinal_position and   pgd.objoid=st.relid)   ORDER BY  c.table_name;";
		
		String MYSQL_SQL="select table_name,column_name, data_type,IS_NULLABLE,column_comment  from INFORMATION_SCHEMA.COLUMNS  ORDER BY table_name";
		//运行sql
		ResultSet ret  = JDBC.executeQuery(PG_SQL);
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
		ret.close();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tableName", tableList.getJSONArray(0).getString(4));
		jsonObject.put("message", tableList);
		resultList.add(jsonObject);
		
		System.out.println("表格数量"+resultList.size());
		return resultList;
	}

	/**
	 * 目录
	 */
	private  void getDirectory(HSSFCellStyle head2Style ,HSSFCellStyle bodyStyle ,HSSFSheet sheet,List<String> tableNameList){
		int rowNum = 0;
		String[] titleArray = { "表名", "备注" };
		HSSFRow headRow = sheet.createRow(rowNum++);
		for (int m = 0; m < titleArray.length ; m++) {
			HSSFCell cell = this.createCell(m,headRow,head2Style,titleArray[m]);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			sheet.setColumnWidth(m, 10000);
		}
		for(String tableName : tableNameList){
			 headRow = sheet.createRow(rowNum++);
			 HSSFCell cell = this.createCell(0,headRow,bodyStyle,tableName);
			 Hyperlink hyperlink = new HSSFHyperlink(Hyperlink.LINK_DOCUMENT);  
			 hyperlink.setAddress("#Sheet"+(rowNum-1)+"!A1"); 
			 cell.setHyperlink(hyperlink);
		}
	}
	
	public void createExcel(String path) throws Exception {
		int num = 1;
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//获取table sql
		JSONArray resultList = this.getSql();
		
		//设置样式
		//头样式
		HSSFFont headFont = workbook.createFont();
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setFontHeightInPoints((short)14);
		HSSFCellStyle headStyle = workbook.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中    
		headStyle.setFont(headFont);
		//主体样式
		HSSFCellStyle bodyStyle = workbook.createCellStyle();
		HSSFFont bodyFont = workbook.createFont();
		bodyFont.setFontName("宋体");    
		bodyFont.setFontHeightInPoints((short) 11);//设置字体大小 
		bodyStyle=addBorder(bodyStyle);
		bodyStyle.setFont(bodyFont);
		//2号头样式
		HSSFFont head2Font =workbook.createFont();
		HSSFCellStyle head2Style = workbook.createCellStyle();
		head2Font.setFontName("宋体");    
		head2Font.setFontHeightInPoints((short) 11);//设置字体大小 
		head2Font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		head2Style=addBorder(head2Style);
		head2Style.setFont(head2Font);
		HSSFSheet directorySheet = workbook.createSheet("目录");
		List<String> tableNameList =new ArrayList<String>();
		for(int i =0 ;i<resultList.size();i++){
			JSONObject table = (JSONObject) resultList.get(i);
			if(!isVaildTable(table.getString("tableName"))){
				continue;
			}
			System.out.println("sheet num:"+num++);
			HSSFSheet sheet = workbook.createSheet();
			sheet.setActive(true);
			int rowNum=0;
			String[] titleArray = { "字段名", "字段类型", "是否为空", "备注" };
			int colSize = titleArray.length-1;
			// 创建第一栏
			HSSFRow headRow = sheet.createRow(rowNum++);
			HSSFCell cell = this.createCell(0,headRow,headStyle,table.getString("tableName"));
			tableNameList.add(table.getString("tableName"));
			// 单元格合并
			// 四个参数分别是：起始行，起始列，结束行，结束列
			CellRangeAddress cra=new CellRangeAddress(0, 0, 0, 3);
			sheet.addMergedRegion(cra);
			
			
			// 创建第二栏
			headRow = sheet.createRow(rowNum++);
			for (int m = 0; m < titleArray.length ; m++) {
				cell = this.createCell(m,headRow,head2Style,titleArray[m]);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				sheet.setColumnWidth(m, 6000);
			}
			
			//主体数据
			JSONArray tableArray = table.getJSONArray("message");
			for(Object row : tableArray){
					int j=0;
					headRow = sheet.createRow(rowNum++);
					int m = ((JSONArray)row).size()-2;
					// 填写数据
					cell.setCellStyle(bodyStyle);
					for(Object col : (JSONArray)row){
						cell = this.createCell(j,headRow,bodyStyle,(String)col);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						sheet.setColumnWidth(j, 6000);
						if(m == j)
							break;
						j++;
				}
			}
			
		}
		getDirectory(head2Style, bodyStyle, directorySheet, tableNameList);
		
		//输出excel
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(path);
			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public boolean isVaildTable(String tableName){
		Pattern pattern = Pattern.compile("^[a-z-A-Z-0-9_]+_[0-9]+$");
		if(pattern.matcher(tableName).matches()){
			return false;
		}else{
//			if(tableName.indexOf("tbl_log_ios_flash_repair20") != -1){
//				return false;
//			}
			return true;
		}
	}
	
	private HSSFCellStyle  addBorder(HSSFCellStyle style){
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框    
		return  style;
	}
	/**
	 * 创建表格
	 * @param index
	 * @param row
	 * @param Style
	 * @param content
	 * @return
	 */
	private  HSSFCell createCell(int index,HSSFRow row,HSSFCellStyle Style,String content){
		HSSFCell cell = row.createCell(index);
		cell.setCellValue(content);
		cell.setCellStyle(Style);
		return cell;
	}
	

	public static void main(String[] args) throws Exception {
		writeExcelForSQLtable tset = new writeExcelForSQLtable();
//		System.out.println(tset.isVaildTable("bl_ios_app_update_status_monitor"));
		tset.createExcel("G:/test/魔品日志库.xls");
		System.out.println("生成成功");
	}
}
