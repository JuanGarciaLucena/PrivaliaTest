package com.juanlucena.privaliatest;

import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import adapters.RecyclerViewAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.Film;
import utils.EndlessRecyclerViewScrollListener;

public class MainActivity extends AppCompatActivity implements MainView{

    @BindView(R.id.progress)ProgressBar progressBar;
    @BindView(R.id.moviesListRecyclerView) RecyclerView moviesListRecyclerView;
    @BindView(R.id.searchEditText) EditText searchEditText;

    private RecyclerViewAdapter adapter;
    private MainPresenter mainPresenter;
    private int currentPage = 1;
    private LinearLayoutManager linearLayoutManager;

    private EndlessRecyclerViewScrollListener scrollListener;
    private List<Film> completeFilmList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        linearLayoutManager = new LinearLayoutManager(this);
        moviesListRecyclerView.setLayoutManager(linearLayoutManager);

        mainPresenter = new MainPresenterImpl(MainActivity.this, new MainInteractorImpl(currentPage, ""));

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                currentPage += 1;
                mainPresenter = new MainPresenterImpl(MainActivity.this, new MainInteractorImpl(currentPage, ""));
                mainPresenter.onResume();
            }
        };
        moviesListRecyclerView.addOnScrollListener(scrollListener);

        searchEditText.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {

                completeFilmList.clear();

                if(s.toString() == null || s.toString().isEmpty()){
                    currentPage = 1;
                    mainPresenter = new MainPresenterImpl(MainActivity.this, new MainInteractorImpl(currentPage, ""));
                    mainPresenter.onResume();
                }else {
                    mainPresenter = new MainPresenterImpl(MainActivity.this, new MainInteractorImpl(currentPage, s.toString()));
                    mainPresenter.searchFilms();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

    }

    @Override protected void onResume() {
        super.onResume();
        mainPresenter.onResume();
    }

    @Override protected void onDestroy() {
        mainPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        moviesListRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        moviesListRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(final List<Film> listFilm) {

        completeFilmList.addAll(listFilm);

        if(currentPage == 1) {
            Drawable dividerDrawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.divider_horizontal_recycler_view);
            adapter = new RecyclerViewAdapter(MainActivity.this, completeFilmList);
            moviesListRecyclerView.setAdapter(adapter);
            moviesListRecyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
            moviesListRecyclerView.setHasFixedSize(true);
        }else{
            Snackbar.make(getWindow().getDecorView().getRootView(), "Loading new elements...", Snackbar.LENGTH_LONG).show();
            adapter.notifyDataSetChanged();
        }


    }
}
