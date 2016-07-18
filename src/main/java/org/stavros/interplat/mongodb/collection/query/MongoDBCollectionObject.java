package org.stavros.interplat.mongodb.collection.query;

public class MongoDBCollectionObject extends DefaultMongoDBCollectionQuery {
	
	private Object object;
	public void setObject(Object object) {
		this.object = object;
	}
	public Object getObject() {
		return this.object;
	}
	
	public MongoDBCollectionObject(Object object, Class clazz) {
		setObject(object);
		setClazz(clazz);
	}

}
