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
import org.springframework.stereotype.Service;

import com.example.project.beans.model.MNewsModel;
import com.example.project.beans.param.NewsParam;
import com.example.project.service.CrawlingService;

@Service
public class CrawlingServiceImpl implements CrawlingService{
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void crawlingNaverSearchNewsLink(String keyword) throws Exception {

		List<NewsParam> newsList = new ArrayList<>();
		String yesterdayString = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
		String todayString = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
		try{
			for (int pageNum=1; pageNum<=10; pageNum++){
				StringBuilder sbUrl = new StringBuilder();
				sbUrl.append("https://search.naver.com/search.naver?where=news&sm=tab_pge&query=").append(keyword)
					.append("&start=").append(pageNum)
					.append("&ds=").append(yesterdayString)
					.append("&de=").append(todayString);
				String url = sbUrl.toString();
	
				logger.info("CrawlingServiceImpl::crawlingNaverSearchNewsLink::Url = {}", url);
				
				Connection connection = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                        .timeout(10000);

				Thread.sleep(1000);
				
				Document searchPageDoc = connection.get();
	
				Elements linkElements = searchPageDoc.select("a[href*=n.news.naver.com]");
				for (Element link: linkElements){
					String newsUrl = link.attr("href");
					if (newsList.size()>=5){
						break;
					}
					NewsParam newsParam = new NewsParam();
					newsParam.setnUrl(newsUrl);
					newsParam.setnKeyword(keyword);
					this.crawlingNaverNews(newsParam);
					newsList.add(newsParam);
					logger.info("keyword = {}, newsUrl = {}", keyword, newsUrl);
					
				}
				if (newsList.size()>=5){
					break;
				}
			}
		} catch(Exception e){
			logger.error("CrawlingServiceImpl::crawlingNaverSearchNewsLink::Error = {}", e.getMessage());
		}
		
	}
	
	@Override
	public MNewsModel crawlingNaverNews(NewsParam newsParam) throws Exception{
		
		MNewsModel news = new MNewsModel();
		
		try{

			logger.info("CrawlingServiceImpl::crawlingNaverSearchNewsLink::Url = {}", newsParam.getnUrl());
			
			Connection connection = Jsoup.connect(newsParam.getnUrl())
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .timeout(10000);

			Thread.sleep(1000);
			Document newsPageDoc = connection.get();
			
			Element titleElement = newsPageDoc.select("h2[id*=title_area]").get(0);
			Element contentElement = newsPageDoc.select("article[id*=dic_area]").get(0);
			
			logger.info("titleElements = {}", titleElement.text());
			logger.info("contentElements = {}", contentElement.text());
			
			
		}catch(Exception e){
			logger.error("CrawlingServiceImpl::crawlingNaverNews::Error = {}", e.getMessage());
		}
		
		
		return news;
	}
}
