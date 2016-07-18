package org.stavros.interplat.mongodb.collection;

public class MongoPojo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1257301445624947773L;
	
	private Long id;
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}
	
	private String name;
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	private String code;
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return this.code;
	}

}
