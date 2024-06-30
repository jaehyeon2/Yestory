package com.example.project.beans.enums;

public enum NewsType {
	COMMON("common"),
	ENTERTAINMENT("entertainment"),
	SPORT("sport"),
	;
	
	private String typeName;
	
	private NewsType(String typeName){
		this.typeName = typeName;
	}
	
	public String getTypeName(){
		return typeName;
	}
}
