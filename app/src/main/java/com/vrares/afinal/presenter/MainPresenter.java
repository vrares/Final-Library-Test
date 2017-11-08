package com.vrares.afinal.presenter;

import com.vrares.afinal.model.ApiHelper;
import com.vrares.afinal.view.MainView;

import javax.inject.Inject;

/**
 * Created by rares.vultur on 11/8/2017.
 */

public class MainPresenter {

    @Inject MainView mainView;
    @Inject ApiHelper apiHelper;

    public void attach(MainView mainView) {
        this.mainView = mainView;
    }

    public void detach() {
        this.mainView = null;
    }

    public void searchForMovie(String movieQuery) {
        apiHelper.searchForMovie(movieQuery);
    }
}
