package com.example.project.service.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.project.beans.model.YnewsModel;
import com.example.project.beans.param.YnewsParam;
import com.example.project.dao.master.MNewsDao;
import com.example.project.service.BasicService;
import com.example.project.service.CrawlingYService;

@Service
public class CrawlingYServiceImpl extends BasicService implements CrawlingYService{
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void crawlingNaverSearchNews(String keyword) throws Exception {

		List<YnewsParam> newsParamList = new ArrayList<>();
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
					if (newsParamList.size()>=5){
						break;
					}
					YnewsParam newsParam = new YnewsParam();
					newsParam.setnUrl(newsUrl);
					newsParam.setnKeyword(keyword);
					this.crawlingNaverNews(newsParam);
					newsParamList.add(newsParam);
					logger.info("keyword = {}, newsUrl = {}", keyword, newsUrl);
					
				}
				if (newsParamList.size()>=5){
					break;
				}
			}
			
			for(YnewsParam newsParam:newsParamList){
				this.crawlingNaverNews(newsParam);
			}
			
		} catch(Exception e){
			logger.error("CrawlingServiceImpl::crawlingNaverSearchNewsLink::Error = {}", e.getMessage());
		}
		
		
	}
	
	private void crawlingNaverNews(YnewsParam newsParam) throws Exception{
		
		try{

			logger.info("CrawlingServiceImpl::crawlingNaverSearchNewsLink::Url = {}", newsParam.getnUrl());
			
			Connection connection = Jsoup.connect(newsParam.getnUrl())
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .timeout(10000);

			Thread.sleep(1000);
			Document newsPageDoc = connection.get();
			
			String title = newsPageDoc.select("h2[id*=title_area]").get(0).toString();
			String content = newsPageDoc.select("article[id*=dic_area]").get(0).toString();
			
			newsParam.setDate(this.getYesterdayDate());
			newsParam.setnTitle(title);
			newsParam.setnContent(content);
			
			this.insertNaverNews(newsParam);
			
		}catch(Exception e){
			logger.error("CrawlingServiceImpl::crawlingNaverNews::Error = {}", e.getMessage());
		}
	}
	
	private boolean insertNaverNews(YnewsParam newsParam) throws Exception{
		
		Map<String, String> map = new HashMap<>();
		
		try{
			map.put("", newsParam.getnTitle());
			map.put("", newsParam.getnContent());
			map.put("", newsParam.getnKeyword());
			map.put("", newsParam.getDate());
			
			int result = mDbDao.getMapper(MNewsDao.class).insertNews(newsParam);
			if (result<1){
				return false;
			}
			
		}catch(Exception e){
			logger.error("CrawlingServiceImpl::insertNaverNews::Error = {}", e.getMessage());
			return false;
		}
		return true;
		
	}
}
