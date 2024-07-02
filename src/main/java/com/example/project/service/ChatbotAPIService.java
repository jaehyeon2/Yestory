package com.example.project.service;

import com.example.project.beans.enums.RequestType;
import com.example.project.beans.model.ResponseModel;
import com.example.project.beans.param.RequestParam;

public interface ChatbotAPIService {

	public ResponseModel getResponseOfText(RequestParam requestParam, String responseText) throws Exception;
	public ResponseModel getResponseOfTrend(RequestParam requestParam) throws Exception;
	public ResponseModel getResponseOfTrendList(RequestParam requestParam) throws Exception;
	
	public RequestType getRequestType(String requestText) throws Exception;
}
