package com.vrares.afinal.presenter;

import com.vrares.afinal.model.networking.ApiHelper;
import com.vrares.afinal.model.pojo.Result;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainPresenter implements ApiHelper.PresenterView {

    @Inject
    ApiHelper apiHelper;

    private MainView mainView;

    public void attach(MainView mainView) {
        this.mainView = mainView;
    }

    public void detach() {
        this.mainView = null;
    }

    public void searchForMovie(String movieQuery) {
        apiHelper.searchForMovie(movieQuery, this);
    }


    @Override
    public void onMovieListRetrieved(ArrayList<Result> resultList) {
        mainView.onMovieListRetrieved(resultList);
    }

    //Callback interface
    public interface MainView {
        void onMovieListRetrieved(ArrayList<Result> resultList);
    }
}
