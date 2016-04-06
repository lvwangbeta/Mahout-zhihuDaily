package com.lvwang.mahout;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.google.gson.Gson;

public class Spider {
	
	public static String STOP_DATE = "20130519";
	
	public static void main(String[] args) {
		
		URLResource resource = new URLResource();
		QueryHelper queryHelper = new QueryHelper();
		
		Calendar calendar = new GregorianCalendar(2014, 4, 13, 0, 0, 0);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");		
		
		while(!format.format(calendar.getTime()).equals(STOP_DATE)){
			System.out.println(format.format(calendar.getTime()));
			String str = resource.get("http://news.at.zhihu.com/api/4/news/before/" + format.format(calendar.getTime()));
			Gson gson = new Gson();
			Daily daily = gson.fromJson(str, Daily.class);
			System.out.println(daily);
			for(int i=0; i<daily.getStories().size(); i++) {
				int id = daily.getStories().get(i).getId();
				String url = "http://news-at.zhihu.com/api/4/news/" + id;
				String newsStr = resource.get(url);
				SingleNews news = gson.fromJson(newsStr, SingleNews.class);
				news.setNews_date(format.format(calendar.getTime()));
				queryHelper.save(news);
			}
			
			calendar.add(Calendar.DAY_OF_MONTH, -1);
		}
		
		//
		
		//System.out.println(news.getBody());
		queryHelper.close();
		resource.close();		
		
	}

}
