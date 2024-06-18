package com.example.project.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.beans.param.NewsParam;
import com.example.project.service.BasicService;
import com.example.project.service.CrawlingYService;
import com.example.project.service.NewsYService;

@Service
public class CrawlingYServiceImpl extends BasicService implements CrawlingYService{
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private String CRAWLING_NEWS_USER_AGENT;
	@Autowired
	private String CRAWLING_NEWS_URL;
	@Autowired
	private String CRAWLING_NEWS_URL_PAGE;
	@Autowired
	private String CRAWLING_NEWS_URL_START;
	@Autowired
	private String CRAWLING_NEWS_URL_END;
	
	@Autowired
	private NewsYService newsYService;
	
	
	@Override
	public void crawlingNaverNewsList(String trend) throws Exception {

		List<NewsParam> newsParamList = new ArrayList<>();
		String yesterdayString = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
		String todayString = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
		try{
			
			for (int pageNum=1; pageNum<=10; pageNum++){
				String url = new StringBuilder()
						.append(CRAWLING_NEWS_URL).append(trend)
						.append(CRAWLING_NEWS_URL_PAGE).append(pageNum)
						.append(CRAWLING_NEWS_URL_START).append(yesterdayString)
						.append(CRAWLING_NEWS_URL_END).append(todayString)
						.toString();
	
				logger.info("CrawlingServiceImpl::crawlingNaverSearchNewsLink::Url = {}", url);
				
				Connection connection = Jsoup.connect(url)
                        .userAgent(CRAWLING_NEWS_USER_AGENT)
                        .timeout(10000);

				Thread.sleep(1000);
				
				Document searchPageDoc = connection.get();
	
				Elements linkElements = searchPageDoc.select("a[href*=n.news.naver.com]");
				for (Element link: linkElements){
					if (newsParamList.size()>=5){
						break;
					}
					String newsUrl = link.attr("href");
					NewsParam newsParam = new NewsParam();
					newsParam.setMnUrl(newsUrl);
					newsParam.setMtTrend(trend);
					newsParamList.add(newsParam);
					
				}
				if (newsParamList.size()>=5){
					break;
				}
			}
			logger.info("newsParamListSize = {}", newsParamList.size());
			
			
			for(NewsParam newsParam:newsParamList){
				
				this.crawlingNaverNews(newsParam);
			}
			
		} catch(Exception e){
			logger.error("CrawlingServiceImpl::crawlingNaverSearchNewsLink::Error = {}", e.getMessage());
			throw e;
		}
	}
	
	
	private void crawlingNaverNews(NewsParam newsParam) throws Exception{
		
		try{

			logger.info("CrawlingServiceImpl::crawlingNaverSearchNewsLink::Url = {}", newsParam.getMnUrl());
			
			Connection connection = Jsoup.connect(newsParam.getMnUrl())
                    .userAgent(CRAWLING_NEWS_USER_AGENT)
                    .timeout(10000);

			Thread.sleep(1000);
			Document newsPageDoc = connection.get();
			
			String title = newsPageDoc.select("h2[id*=title_area]").get(0).text().toString();
			String content = newsPageDoc.select("article[id*=dic_area]").get(0).text().toString();
			
			newsParam.setHistory(this.getYesterdayDate());
			newsParam.setMnTitle(title);
			newsParam.setMnContent(content);
			
			newsYService.insertNews(newsParam);
			
		}catch(Exception e){
			logger.error("CrawlingServiceImpl::crawlingNaverNews::Error = {}", e.getMessage());
			throw e;
		}
	}
	
}
