package org.stavros.interplat.mongodb.collection;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.bson.Document;
import org.stavros.converter.pojo.Converter;
import org.stavros.interplat.model.Model;
import org.stavros.interplat.model.Record;
import org.stavros.interplat.mongodb.collection.query.MongoDBCollectionObject;
import org.stavros.interplat.mongodb.collection.query.MongoDBCollectionQuery;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class Engine {
	
	public <T> Model process(MongoCollection<Document> collection, MongoDBCollectionQuery mongoQuery) throws InvocationTargetException, IllegalAccessException, IllegalArgumentException, IntrospectionException, InstantiationException {
		MongoDBCollectionObject mdbo = (MongoDBCollectionObject)mongoQuery;
		
		Document searchQuery = new Document(Converter.getMap(mdbo.getObject()));
		FindIterable<Document> fi = collection.find(searchQuery);
		
		Long i=0L;
		
		Model model = new Model();
		MongoCursor<Document> cursor = fi.iterator();
		while (cursor.hasNext()) {
			Document document = cursor.next();
			T object = (T)mdbo.getClazz().newInstance();
			org.stavros.converter.map.Converter.getPojo(document, object);
			
			Record record = new Record();
			record.put("data", object);
			model.add(record);
		}
		
		return model;
	}

}
