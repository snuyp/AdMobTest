package com.example.dima.admobtest;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

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
    private InterstitialAd mInterstitialAd;
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

        mInterstitialAd = new InterstitialAd(this);
        //for example
       // mInterstitialAd.setAdUnitId("ca-app-pub-4362588175476230/1808084502");
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-4362588175476230/2982507325");
        // adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
        super.onBackPressed();
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
