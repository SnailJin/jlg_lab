package com.jin.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Zookeeper的节点创建模式：
 *
 * PERSISTENT：持久化
 * PERSISTENT_SEQUENTIAL：持久化并且带序列号
 * EPHEMERAL：临时
 * EPHEMERAL_SEQUENTIAL：临时并且带序列号
 *
 *
 */
public class Test2 {
   static String path ="/test";
    static CuratorFramework client = CuratorFatory.getInstance();
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
        String pathStr = path+"/num";

        /**
         * 监听数据节点的变化情况
         */
        final NodeCache nodeCache = new NodeCache(client, pathStr, false);
        nodeCache.start(true);
        nodeCache.getListenable().addListener(
                new NodeCacheListener() {
                    @Override
                    public void nodeChanged() throws Exception {
                        System.out.println("Node data is changed, new data: " +
                                new String(nodeCache.getCurrentData().getData()));
                    }
                }
        );



        final PathChildrenCache childrenCache = new PathChildrenCache(client, pathStr, false);
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener(
                new PathChildrenCacheListener() {
                    @Override
                    public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                        switch (event.getType()) {
                            case CHILD_ADDED:
                                System.out.println("CHILD_ADDED: " + event.getData().getPath());
                                break;
                            case CHILD_REMOVED:
                                System.out.println("CHILD_REMOVED: " + event.getData().getPath());
                                break;
                            case CHILD_UPDATED:
                                System.out.println("CHILD_UPDATED: " + event.getData().getPath());
                                break;
                            default:
                                break;
                        }

                    }
                }
        );
       // client.create().creatingParentsIfNeeded().forPath(pathStr+"/24", "test".getBytes());
        client.setData().forPath(pathStr,"223".getBytes());
       Thread.sleep(10 * 1000);
	}

	public static void acid(){
        try {
            //创建一个节点并且添加数据test[creatingParentsIfNeeded 表示如果父节点还没有创建先先创建父节点]
            String pathStr = path+"/tmp/4";
            Stat stat = client.checkExists().forPath(pathStr);
            if(stat ==null){
                // client.create().creatingParentsIfNeeded().forPath(pathStr, "test".getBytes());
                client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(pathStr, "test".getBytes());

            }
            List<String> children = client.getChildren().forPath(path+"/num");
            for(String tmp : children){
                System.out.println(tmp);
            }
            //删除对应节点
//			client.delete().deletingChildrenIfNeeded().forPath(path+"/test");
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
