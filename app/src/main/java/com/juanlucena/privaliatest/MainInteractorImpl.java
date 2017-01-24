package com.juanlucena.privaliatest;

import connections.Service;
import models.ResultResponse;
import models.SearchResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Juan Lucena on 20/01/2017.
 */

public class MainInteractorImpl implements MainInteractor{

    private int numPage;
    private String filmTitle;

    public MainInteractorImpl(int numPage, String filmTitle){
        this.numPage = numPage;
        this.filmTitle = filmTitle;
    }

    Service apiService = Service.retrofit.create(Service.class);

    @Override
    public void findFilms(final OnFinishedListener listener) {

        final Call<ResultResponse> call = apiService.callFindFilms(numPage);

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
               listener.onFinished(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void searchFilms(final OnFinishedListener listener) {

        Call<SearchResults> call = apiService.callSearchFilms(filmTitle, numPage);

        call.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                listener.onFinished(response.body().getResults());
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
            }
        });
    }
}
