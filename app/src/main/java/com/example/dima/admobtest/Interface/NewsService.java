package com.example.dima.admobtest.Interface;

import com.example.dima.admobtest.mvp.model.News;
import com.example.dima.admobtest.mvp.model.WebSite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NewsService {
    @GET("sources?")
    Call<WebSite> getSources(
            @Query("language") String language,
            @Query("apiKey") String apiKey
    );

    @GET("top-headlines")
    Call<News> getHeadlines(
            @Query("sources") String sources,
            @Query("apiKey") String apiKey
    );
}
