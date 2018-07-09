package com.example.dima.admobtest.mvp.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dima.admobtest.Interface.NewsService;
import com.example.dima.admobtest.mvp.model.WebSite;
import com.example.dima.admobtest.mvp.view.SourceView;
import com.example.dima.admobtest.remote.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class SourcePresenter extends MvpPresenter<SourceView> {
    private NewsService newsService = Common.getNewsService();

    public void loadSources(boolean isRefreshed,String language) {
        String languageSource = language;
        if (!isRefreshed) {
                getViewState().dialogShow();
                newsService.getSources(languageSource, Common.API_KEY).enqueue(new Callback<WebSite>() {
                    @Override
                    public void onResponse(@NonNull Call<WebSite> call, @NonNull Response<WebSite> response) {
                        getViewState().onLoadResult(response.body().getSources());
                        //Save to cache
                        getViewState().dialogDismiss();
                    }
                    @Override
                    public void onFailure(@NonNull Call<WebSite> call, @NonNull Throwable t) {
                        Log.e("Failure", "Failure" + t.getMessage());
                    }
                });
        } else {
            getViewState().setRefresh(true);
            newsService.getSources(languageSource, Common.API_KEY).enqueue(new Callback<WebSite>() {
                @Override
                public void onResponse(@NonNull Call<WebSite> call, @NonNull Response<WebSite> response) {

                    getViewState().onLoadResult(response.body().getSources());
                    getViewState().setRefresh(false);
                }
                @Override
                public void onFailure(@NonNull Call<WebSite> call, @NonNull Throwable t) {
                    Log.e("Failure", "Failure" + t.getMessage());
                }
            });
        }
    }
}
