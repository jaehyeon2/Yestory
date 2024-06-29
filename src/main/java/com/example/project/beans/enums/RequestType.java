package com.example.project.beans.enums;

public enum RequestType {
	TREND("트렌드"),
	TREND_DETAIL("트렌드 디테일"),
	UNSPECIFIED("미정의")
	;
	
	private String typeName;
	
	private RequestType(String typeName){
		this.typeName = typeName;
	}
	
	public String getTypeName(){
		return typeName;
	}
}
