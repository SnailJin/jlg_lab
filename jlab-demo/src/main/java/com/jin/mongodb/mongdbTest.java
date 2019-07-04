package com.jin.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class mongdbTest {

	private final static String  url = "172.16.10.102";
	private final static int  port = 27017;
	
	public static void main(String[] args) {
		// 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient( url , port );
      
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");  
        System.out.println("Connect to database successfully");
        int num = 100000000;
        int j=1;
        List<Document> mongoList = new  ArrayList<Document>(1000);
        for(int i = 16669226 ;i<num ;i++){
        	 Document document = new Document("id", i);
        	 document.append("name", "name_"+i);
        	 document.append("test1", "test1_"+i);
        	 document.append("test2", "test2_"+i);
        	 document.append("test3", "test3_"+i);
        	 document.append("test4", "test4_"+i);
        	 document.append("test5", "test5_"+i);
        	 document.append("test6", "test6_"+i);
        	 mongoList.add(document);
        	 if(j>=100){
        		 mongoDatabase.getCollection("test").insertMany(mongoList);
        		 mongoList.clear();
        		 j=1;
        		 System.out.println("上行:"+i);
        	 }
        	 j++;
        	
        }
       
        System.out.println("文档插入成功");  
	}
}
