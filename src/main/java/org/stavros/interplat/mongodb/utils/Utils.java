package org.stavros.interplat.mongodb.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import org.bson.Document;

public class Utils {
	
	public static <T> Document getDocument(T obj) throws InvocationTargetException, IntrospectionException, IllegalAccessException, IllegalArgumentException {
		Document doc = new Document();
		for (PropertyDescriptor pd : Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors()) {
			Object value = pd.getReadMethod().invoke(obj);
			if (pd.getReadMethod() != null
					&& !"class".equals(pd.getName())
					&& value != null) {
				doc.put(pd.getName(), value);
			}
		}
		System.out.println("document printout: " + doc);
		return doc;
	}
	
	public static <T> void getObject(Document document, T obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		for (PropertyDescriptor pd : Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors()) {
			String name = pd.getName();
			Object value = document.get(name);
			
			if (pd.getWriteMethod() != null
					&& !"class".equals(name)
					&& value != null) {
				pd.getWriteMethod().invoke(obj, value);
			}
		}
		System.out.println("object printout: " + obj);
	}

}
