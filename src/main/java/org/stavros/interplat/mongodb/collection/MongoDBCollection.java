package org.stavros.interplat.mongodb.collection;

import org.stavros.interplat.model.Model;
import org.stavros.interplat.mongodb.MongoDBSource;
import org.stavros.interplat.mongodb.collection.query.MongoDBCollectionQuery;
import org.stavros.interplat.source.GenericException;
import org.stavros.interplat.source.GenericQuery;

public class MongoDBCollection<T> extends MongoDBSource {
	
	MongoDBCollection(String host, int port, String databaseName, String collectionName) {
		super(host, port, databaseName, collectionName);
	}

	@Override
	public <T> Model filter(GenericQuery query) throws GenericException {
		MongoDBCollectionQuery mongoQuery = (MongoDBCollectionQuery)query;
		
		Engine engine = new Engine();
		Model model = null;
		
		try {
			model = engine.<T>process(getCollection(), mongoQuery);
		}
		catch(Exception e) {
			throw new GenericException(e);
		}
		
		return model;
	}

}
