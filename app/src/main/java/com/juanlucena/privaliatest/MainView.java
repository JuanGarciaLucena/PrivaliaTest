package com.juanlucena.privaliatest;

import java.util.List;

import models.Film;

/**
 * Created by Juan Lucena on 20/01/2017.
 */

public interface MainView {

    void showProgress();
    void hideProgress();
    void setItems(List<Film> listFilm);
}
