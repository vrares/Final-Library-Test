package com.vrares.afinal.view.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vrares.afinal.MyApplication;
import com.vrares.afinal.R;
import com.vrares.afinal.model.pojo.Result;
import com.vrares.afinal.presenter.MainPresenter;
import com.vrares.afinal.view.result.Henson;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import toothpick.Scope;
import toothpick.Toothpick;

public class MainActivity extends AppCompatActivity{

    private final ViewContractImplementation viewContract = new ViewContractImplementation();

    //Bind the Views using Butterknife
    @BindView(R.id.et_movie_search) EditText etMovieSearch;
    @BindView(R.id.btn_search) Button btnSearch;
    @BindView(R.id.tv_info) TextView tvInfo;
    @BindView(R.id.pb_loading) ProgressBar progressBar;

    //Inject the presenter using Toothpick
    @Inject MainPresenter mainPresenter;

    private Scope scope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This method bind the activity to the views
        ButterKnife.bind(this);
        //Open the scopes
        scope = Toothpick.openScopes(MyApplication.getInstance(), this);
        Toothpick.inject(this, scope);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attach(viewContract);
    }

    @Override
    protected void onStop() {
        mainPresenter.detach();
        super.onStop();
    }

    @OnClick(R.id.btn_search)
    public void searchForMovie() {
        String movieQuery = etMovieSearch.getText().toString();
        tvInfo.setVisibility(View.GONE);
        etMovieSearch.setVisibility(View.GONE);
        btnSearch.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        //Get the query from the EditText and call the method that search for movies.
        mainPresenter.searchForMovie(movieQuery);
    }

    private class ViewContractImplementation implements MainPresenter.MainView {

        @Override
        public void onMovieListRetrieved(ArrayList<Result> resultList) {
            //Using the Dart and Henson Library, we will pass the list of movies to the ResultActivity
            //We create a Henson object, insert the objects we want to pass, we build it and then
            //start the activity
            Intent intent = Henson.with(getApplicationContext())
                    .gotoResultActivity()
                    .movieList(resultList)
                    .build();
            startActivity(intent);
        }
    }
}
