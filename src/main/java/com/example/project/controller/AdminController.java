package com.example.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.beans.model.YTrendModel;
import com.example.project.beans.param.TrendParam;
import com.example.project.service.TrendYService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TrendYService trendYService;
	
	@GetMapping(value = {"/", "", "/index"})
	public String adminIndex() throws Exception{
		
		
		
		return "admin/index";
		
	}
	
	@GetMapping(value = {"trend"})
	public String adminTrend(@Valid TrendParam trendParam, ModelMap model) throws Exception{
		
		List<YTrendModel> trendList = null;
		
		if (trendParam.getHistory()!=null){
			logger.info("history is null");
		}
		
		trendList = trendYService.selectTrendList(trendParam);
		
		model.addAttribute("model", trendList);
		return "admin/trend";
	}

}
