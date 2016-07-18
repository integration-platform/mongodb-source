package org.stavros.interplat.mongodb;

import org.stavros.interplat.source.DefaultSource;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public abstract class MongoDBSource extends DefaultSource {

	protected MongoDBSource(String host, int port, String databaseName, String collectionName) {
		setHost(host);
		setPort(port);
		setDatabaseName(databaseName);
		setCollectionName(collectionName);
	}
	
	private String host;
	public void setHost(String host) {
		this.host = host;
	}
	public String getHost() {
		return this.host;
	}
	
	private int port;
	public void setPort(int port) {
		this.port = port;
	}
	public int getPort() {
		return this.port;
	}
	
	private String databaseName;
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public String getDatabaseName() {
		return this.databaseName;
	}
	
	private String collectionName;
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	public String getCollectionName() {
		return this.collectionName;
	}
	
	private MongoClient mongoClient;
	public MongoClient getMongoClient() {
		return this.mongoClient;
	}
	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}
	
	private MongoCollection collection;
	public void setCollection(MongoCollection collection) {
		this.collection = collection;
	}
	public MongoCollection getCollection() {
		return this.collection;
	}

	@Override
	public void connect() {
		setMongoClient(new MongoClient(getHost(), getPort()));
		MongoDatabase db = mongoClient.getDatabase(getDatabaseName());
		setCollection(db.getCollection(getCollectionName()));
	}
	
	@Override
	public void close() {
		getMongoClient().close();
	}

}
