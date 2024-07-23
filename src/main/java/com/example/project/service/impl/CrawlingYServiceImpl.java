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

import com.example.project.beans.enums.NewsType;
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
		List<String> platformNumberList = new ArrayList<>();
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
	
//				logger.info("CrawlingServiceImpl::crawlingNaverSearchNewsLink::Url = {}", url);
				
				Connection connection = Jsoup.connect(url)
                        .userAgent(CRAWLING_NEWS_USER_AGENT)
                        .timeout(10000);

				Thread.sleep(1000);
				
				Document searchPageDoc = connection.get();
	
				//일반 기사, 연예 기사, 스포츠 기사 전부 linkElements에 저장
				Elements linkElements = searchPageDoc.select("a[href*=n.news.naver.com], a[href*=m.sports.naver.com], a[href*=m.entertain.naver.com]");
				for (Element link: linkElements){
					if (platformNumberList.size()>=5){
						break;
					}
					String newsUrl = link.attr("href");
					
					//뉴스 타입에 따른 private 로직 분리
					NewsType newsType = this.distinguishType(newsUrl);
					String platformNumber = this.getPlatformNumber(newsUrl, newsType);
					if (platformNumberList.contains(platformNumber)){
						continue;
					}
					platformNumberList.add(platformNumber);
					NewsParam newsParam = new NewsParam();
					newsParam.setMnUrl(newsUrl);
					newsParam.setMtTrend(trend);
					newsParam.setMnType(newsType.getTypeName());
					newsParamList.add(newsParam);
					
				}
				if (platformNumberList.size()>=5){
					break;
				}
			}
			
			for(NewsParam newsParam:newsParamList){
				this.crawlingNaverNews(newsParam);
			}
			
		} catch(Exception e){
			logger.error("CrawlingServiceImpl::crawlingNaverSearchNewsLink::Error = {}", e.getMessage());
			throw e;
		}
	}
	
	private String getPlatformNumber(String url, NewsType newsType) throws Exception{
		
		String platformNumber = null;
		if (newsType==NewsType.ENTERTAINMENT){
			platformNumber = url.split("/")[4];
		}else{
			platformNumber = url.split("/")[5];
		}
		
		return platformNumber;
	}
	
	
	private void crawlingNaverNews(NewsParam newsParam) throws Exception{
		
		try{
							
			Connection connection = Jsoup.connect(newsParam.getMnUrl())
                    .userAgent(CRAWLING_NEWS_USER_AGENT)
                    .timeout(10000);

			Thread.sleep(1000);
			Document newsPageDoc = connection.get();
			String title = null;
			String content = null;
			if (newsParam.getMnType().equals(NewsType.COMMON.getTypeName())){
				title = newsPageDoc.select("h2[id*=title_area]").get(0).text().toString();
				content = newsPageDoc.select("article[id*=dic_area]").get(0).text().toString();
			}else if(newsParam.getMnType().equals(NewsType.SPORT.getTypeName())){
//				title = newsPageDoc.select("title").get(0).text().toString();
				title = newsParam.getMtTrend();
				content = "스포츠 뉴스의 경우 내용 요약이 제공되지 않습니다.";
			}else if(newsParam.getMnType().equals(NewsType.ENTERTAINMENT.getTypeName())){
//				title = newsPageDoc.select("title").get(0).text().toString();
				title = newsParam.getMtTrend();
				content = "연예계 뉴스의 경우 내용 요약이 제공되지 않습니다.";
			}
			newsParam.setHistory(this.getYesterdayDate());
			newsParam.setMnTitle(title);
			newsParam.setMnContent(content);
			
			newsYService.insertNews(newsParam);
			
		}catch(Exception e){
			logger.error("CrawlingServiceImpl::crawlingNaverNews::Error = {}", e.getMessage());
			throw e;
		}
	}
	
	private NewsType distinguishType(String url) throws Exception{
		NewsType type = null;
		try{
			if (url.contains("entertain")){
				type = NewsType.ENTERTAINMENT;
			}else if (url.contains("sports")){
				type = NewsType.SPORT;
			}else{
				type = NewsType.COMMON;
			}
		}catch(Exception e){
			logger.error("CrawlingYServiceImpl::distinguishType::Error = {}", e.getMessage());
			throw e;
		}
		return type;
		
	}
	
}
