package com.example.dima.admobtest;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.dima.admobtest.adapter.ListNewsAdapter;
import com.example.dima.admobtest.adapter.ListSourceAdapter;
import com.example.dima.admobtest.mvp.model.Article;
import com.example.dima.admobtest.mvp.model.SourceNews;
import com.example.dima.admobtest.mvp.presenter.ArticlePresenter;
import com.example.dima.admobtest.mvp.presenter.SourcePresenter;
import com.example.dima.admobtest.mvp.view.ArticleView;
import com.example.dima.admobtest.mvp.view.SourceView;

import java.util.List;

import dmax.dialog.SpotsDialog;

public class ContentActivity extends MvpAppCompatActivity implements ArticleView{
    @InjectPresenter
    ArticlePresenter articlePresenter;

    SwipeRefreshLayout swipeRefreshLayout;

    private ListNewsAdapter adapter;
    private RecyclerView lstNews;
    private RecyclerView.LayoutManager layoutManager;
    private SpotsDialog dialog;

    private String source = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        dialog = new SpotsDialog(this);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_news);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                articlePresenter.loadNews(source,true);
            }
        });
        if(getIntent() != null)
        {
            source = getIntent().getStringExtra("source");
            if(!source.isEmpty())
            {
                articlePresenter.loadNews(source,false);
            }
        }

        lstNews = findViewById(R.id.lst_news);
        lstNews.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        lstNews.setLayoutManager(layoutManager);

    }

    @Override
    public void setRefresh(boolean isRefreshing) {
        swipeRefreshLayout.setRefreshing(isRefreshing);
    }

    @Override
    public void dialogShow() {
        dialog.show();
    }

    @Override
    public void dialogDismiss() {
        dialog.dismiss();
    }

    @Override
    public void onLoadResult(List<Article> articles) {
        adapter = new ListNewsAdapter(articles,this);
        lstNews.setAdapter(adapter);
    }
}
