package com.jin.zookeeper.curator;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;


public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path ="/jlg";
		CuratorFramework client = CuratorFatory.getInstance();
		try {
			// 创建一个节点并且添加数据test[creatingParentsIfNeeded 表示如果父节点还没有创建先先创建父节点]
//			client.create().creatingParentsIfNeeded().forPath(path+"/test/t", "test".getBytes());
			//删除对应节点
			//client.delete().deletingChildrenIfNeeded().forPath(path+"/test");
//			if(client.checkExists().forPath(path+"/test/test") != null){
//				//修改
//				client.setData().forPath(path+"/test/test", "hello world!".getBytes());
//				//查询
//				System.out.println(new String(client.getData().forPath(path+"/test/test")));
//				//获取子节点
//				List<String> chidrenList = client.getChildren().forPath(path+"/test");
//				System.out.println(chidrenList.toString());
//				//事务
//				client.inTransaction().create().forPath("/a/path", "some data".getBytes())
//	                .and().setData().forPath("/another/path", "other data".getBytes())
//	                .and().delete().forPath("/yet/another/path")
//	                .and().commit();
//			}else{
//				System.out.println("没有对应节点");
//			}
			client.getChildren().forPath("/mopote");
			System.out.println(new String(client.getData().forPath("/mopote/configuration/mpt-yun")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
