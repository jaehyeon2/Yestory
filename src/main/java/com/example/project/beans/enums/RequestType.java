package com.example.project.beans.enums;

public enum RequestType {
	TREND("trend"),
	TREND_DETAIL("trend_detail"),
	UNSPECIFIED("unspecified"),
	UNKNOWN("unknown"),
	ERROR("error"),
	;
	
	private String typeName;
	
	private RequestType(String typeName){
		this.typeName = typeName;
	}
	
	public String getTypeName(){
		return typeName;
	}
}
