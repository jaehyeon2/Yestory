package com.example.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.beans.model.YSummaryModel;
import com.example.project.beans.model.YTrendModel;
import com.example.project.beans.model.YestoryModel;
import com.example.project.beans.param.SummaryParam;
import com.example.project.beans.param.TrendParam;
import com.example.project.service.SummaryYService;
import com.example.project.service.TrendYService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TrendYService trendYService;
	
	@Autowired
	private SummaryYService summaryYService;
	
	@GetMapping(value = {"/", "", "/index"})
	public String adminIndex() throws Exception{
		
		return "admin/index";
		
	}
	
	@GetMapping(value = {"trend"})
	public String adminTrend(@Valid TrendParam trendParam, ModelMap model) throws Exception{
		
		YestoryModel yestory = new YestoryModel();
		
		List<YTrendModel> trendList = trendYService.selectTrendList(trendParam);
		if (trendList==null){
			logger.error("AdminController::adminTrend::Error = trendList is null");
		}
		yestory.setTrendList(trendList);
		
		model.addAttribute("model", yestory);
		return "admin/trend";
	}
	
	@GetMapping(value = {"summary"})
	public String adminSummary(@Valid SummaryParam summaryParam, ModelMap model) throws Exception{
		
		YestoryModel yestory = new YestoryModel();
		
		List<YSummaryModel> summaryList = summaryYService.selectSummaryList(summaryParam);
		
		yestory.setSummaryList(summaryList);
		
		model.addAttribute("model", yestory);
		
		return "admin/summary";
	}
}
