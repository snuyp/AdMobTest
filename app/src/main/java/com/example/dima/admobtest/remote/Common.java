package com.example.dima.admobtest.remote;

import com.example.dima.admobtest.Interface.NewsService;

public class Common {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    public static final String API_KEY = "5e4c61bd768b476a9fc0e5c7e6b71eac";

    public static NewsService getNewsService()
    {
        return  NewsClient.getClient(BASE_URL).create(NewsService.class);
    }
}
