package com.example.dima.admobtest.mvp.view;

import com.example.dima.admobtest.mvp.model.Article;
import com.example.dima.admobtest.mvp.model.SourceNews;

import java.util.List;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.dima.admobtest.mvp.model.SourceNews;
import java.util.List;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface ArticleView extends MvpView{
    void setRefresh(boolean isRefreshing);
    void dialogShow();
    void dialogDismiss();
    void onLoadResult( List<Article> articles);
}
