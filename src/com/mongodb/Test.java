package com.mongodb;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mongo mongo = new Mongo("127.0.0.1",27017);
		DB db=mongo.getDB("lvban");
		DBCollection coll = db.getCollection("runoob");
		BasicDBObject query = new BasicDBObject();
		query.put("uid", 1);
		query.put("test", 2);
		DBCursor cur = coll.find(query);
		while (cur.hasNext()) {
			System.out.println(cur.next());

			}

	}

}
