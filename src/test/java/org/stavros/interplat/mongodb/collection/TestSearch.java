package org.stavros.interplat.mongodb.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.stavros.interplat.model.Model;
import org.stavros.interplat.model.Record;
import org.stavros.interplat.mongodb.collection.query.MongoDBCollectionObject;
import org.stavros.interplat.mongodb.utils.Utils;
import org.stavros.interplat.source.GenericException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TestSearch {
	
	private String HOST = "127.0.0.1";
	private int PORT = 27017;
	private String DATABASE_NAME = "testdb";
	private String COLLECTION_NAME = "test";
	
	@Before
	public void setUp() {
		MongoClient mongoClient = new MongoClient(HOST, PORT);
		MongoDatabase db = mongoClient.getDatabase(DATABASE_NAME);
		MongoCollection<Document> mongoCollection = db.getCollection(COLLECTION_NAME);
		if (mongoCollection == null) {
			db.createCollection(COLLECTION_NAME);
		}
		
		MongoCollection<Document> collection = db.getCollection(COLLECTION_NAME);
		
		try {
			MongoPojo temp = new MongoPojo();
			temp.setId(1L);
			collection.deleteMany(Utils.getDocument(temp));
			temp.setId(2L);
			collection.deleteMany(Utils.getDocument(temp));
			temp.setId(3L);
			collection.deleteMany(Utils.getDocument(temp));
			
			MongoPojo mongoPojo1 = new MongoPojo();
			mongoPojo1.setId(1L);
			mongoPojo1.setName("testName1");
			mongoPojo1.setCode("test1");
			Document dbo1 = Utils.getDocument(mongoPojo1);
			collection.insertOne(dbo1);
			
			MongoPojo mongoPojo2 = new MongoPojo();
			mongoPojo2.setId(2L);
			mongoPojo2.setName("testName2");
			mongoPojo2.setCode("test2");
			Document dbo2 = Utils.getDocument(mongoPojo2);
			collection.insertOne(dbo2);
			
			MongoPojo mongoPojo3 = new MongoPojo();
			mongoPojo3.setId(3L);
			mongoPojo3.setName("testName3");
			mongoPojo3.setCode("test3");
			Document dbo3 = Utils.getDocument(mongoPojo3);
			collection.insertOne(dbo3);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		mongoClient.close();
	}
	
	@Test
	public void testObject() {
		MongoDBCollection mongoDbCollection = new MongoDBCollection(HOST, PORT, DATABASE_NAME, COLLECTION_NAME);
		MongoPojo object = new MongoPojo();
		object.setCode("test1");
		MongoDBCollectionObject mdbco = new MongoDBCollectionObject(object, MongoPojo.class);
		
		try {
			Model model = mongoDbCollection.getData(mdbco);
			
			for (Record record: model) {
				Object obj = (Object)record.get("data");
				if (obj instanceof MongoPojo) {
					MongoPojo mp = (MongoPojo)obj;
					System.out.println(mp.toString());
				}
				else {
					Document doc = (Document)obj;
					System.out.println(doc.keySet());
					System.out.println(doc.get("id") + ", " + doc.get("code") + ", " + doc.get("name"));
				}
			}
			
			assertEquals(1, model.size());
			
			Record record = model.get(0);
			MongoPojo mongoPojo = (MongoPojo)record.get("data");
			
			assertEquals(Long.valueOf(1), mongoPojo.getId());
			assertEquals("testName1", mongoPojo.getName());
			assertEquals("test1", mongoPojo.getCode());
		}
		catch(GenericException e) {
			Exception temp = e;
			while (temp instanceof GenericException) {
				temp = ((GenericException)temp).getException();
			}
			temp.printStackTrace(System.out);
			assertNull(e.getException().getMessage(), e);
		}
		
	}
	
	@Test
	public void testId() {
		
	}

}
