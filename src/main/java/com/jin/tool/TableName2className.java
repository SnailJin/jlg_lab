package com.jin.tool;

public class TableName2className {
	public static void main(String[] args) {
		String tableName = "tbl_external_product_info_price_ref";
		String[] s = tableName.split("_");
		StringBuffer result = new StringBuffer();
		for (String str : s) {
			result.append(str.substring(0, 1).toUpperCase() + str.substring(1));
		}
		System.out.println(result.toString());
	}
}
