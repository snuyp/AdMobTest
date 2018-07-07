package com.example.dima.admobtest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dima.admobtest.Interface.NewsService;
import com.example.dima.admobtest.mvp.model.News;
import com.example.dima.admobtest.mvp.view.ArticleView;
import com.example.dima.admobtest.remote.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class ArticlePresenter  extends MvpPresenter<ArticleView> {
    private NewsService service = Common.getNewsService();

    public void loadNews(String source, boolean isRefreshed) {
        if(!isRefreshed)
        {
            getViewState().dialogShow();
            service.getHeadlines(source, Common.API_KEY)
                    .enqueue(new Callback<News>() {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                            getViewState().dialogDismiss();
                            if(response.body()!= null)
                            {
                                getViewState().onLoadResult(response.body().getArticles());
                            }

                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    });
        }
        getViewState().setRefresh(false);
    }
}
