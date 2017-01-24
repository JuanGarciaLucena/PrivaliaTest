package com.juanlucena.privaliatest;

import java.util.List;

import models.Film;

/**
 * Created by Juan Lucena on 20/01/2017.
 */

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnFinishedListener{

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(MainView mainView, MainInteractor mainInteractor) {
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }

    @Override
    public void onResume() {
        if (mainView != null) {
        }
        mainInteractor.findFilms(this);
    }

    @Override
    public void searchFilms() {
        if (mainView != null) {
        }
        mainInteractor.searchFilms(this);
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void onFinished(List<Film> filmList) {
        mainView.setItems(filmList);
    }
}
