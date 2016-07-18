package org.stavros.interplat.mongodb.collection.query;

public class DefaultMongoDBCollectionQuery<T> implements MongoDBCollectionQuery {
	
	private Class<T> clazz;
	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}
	public Class<T> getClazz() {
		return this.clazz;
	}

}
