package com.example.dima.admobtest.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.dima.admobtest.mvp.model.Article;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ArticleView extends MvpView{
    void setRefresh(boolean isRefreshing);
    void dialogShow();
    void dialogDismiss();
    void onLoadResult( List<Article> articles);
}
