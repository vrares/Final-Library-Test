package com.vrares.afinal.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.vrares.afinal.R;
import com.vrares.afinal.presenter.MainPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.et_movie_search) EditText etMovieSearch;
    @BindView(R.id.btn_search) Button btnSearch;
    @Inject MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attach(this);
    }

    @Override
    protected void onStop() {
        mainPresenter.detach();
        super.onStop();
    }

    @OnClick(R.id.btn_search)
    public void searchForMovie() {
        String movieQuery = etMovieSearch.getText().toString();
        mainPresenter.searchForMovie(movieQuery);
    }
}
