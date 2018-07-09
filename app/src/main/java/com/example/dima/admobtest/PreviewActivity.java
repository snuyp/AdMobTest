package com.example.dima.admobtest;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.dima.admobtest.adapter.ListSourceAdapter;
import com.example.dima.admobtest.mvp.model.SourceNews;
import com.example.dima.admobtest.mvp.presenter.SourcePresenter;
import com.example.dima.admobtest.mvp.view.SourceView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.List;

import dmax.dialog.SpotsDialog;

public class PreviewActivity extends MvpAppCompatActivity implements SourceView {
    @InjectPresenter
    SourcePresenter sourcePresenter;

    SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView listWebsite;
    private RecyclerView.LayoutManager layoutManager;
    private ListSourceAdapter adapter;
    private SpotsDialog dialog;
    private AdView mAdView;
    private String language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        language = getIntent().getStringExtra("language");

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-4362588175476230/2982507325");
       // adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        listWebsite = findViewById(R.id.list_source);
        listWebsite.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        listWebsite.setLayoutManager(layoutManager);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sourcePresenter.loadSources(true,language);
            }
        });
        dialog = new SpotsDialog(this);
        sourcePresenter.loadSources(true,language);

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
    public void onLoadResult(List<SourceNews> sources) {
        adapter = new ListSourceAdapter(this, sources);
        listWebsite.setAdapter(adapter);
    }
}
