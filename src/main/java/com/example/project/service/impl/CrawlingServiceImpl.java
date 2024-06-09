package com.example.project.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.project.beans.param.NewsParam;
import com.example.project.service.CrawlingService;

@Service
public class CrawlingServiceImpl implements CrawlingService{
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<NewsParam> crawlingNaverSearchNewsLink(String keyword) throws Exception {

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
				
				Document searchPageDoc = Jsoup.connect(url).get();
	
				Elements linkElements = searchPageDoc.select("a[href*=n.news.naver.com]");
				for (Element link: linkElements){
					String newsUrl = link.attr("href");
					if (newsList.size()>=5){
						break;
					}
					NewsParam news = new NewsParam();
					news.setnUrl(newsUrl);
					news.setnKeyword(keyword);
					newsList.add(news);
					logger.info("keyword = {}, newsUrl = {}", keyword, newsUrl);
				}
				if (newsList.size()>=5){
					break;
				}
			}
		} catch(Exception e){
			logger.error("CrawlingServiceImpl::crawlingNaverSearchNewsLink::Error = {}", e.getMessage());
		}
		
		return newsList;
	}
}
