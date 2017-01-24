package com.juanlucena.privaliatest;

import java.util.List;

import models.Film;

/**
 * Created by Juan Lucena on 20/01/2017.
 */

public interface MainInteractor{

    interface OnFinishedListener{
        void onFinished(List<Film> filmList);
    }

    void findFilms(OnFinishedListener listener);
    void searchFilms(OnFinishedListener listener);
}
