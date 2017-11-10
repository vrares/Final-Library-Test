package com.vrares.afinal.view.result;

import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.vrares.afinal.R;
import com.vrares.afinal.model.pojo.Result;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity {

    //Using this annotation, we tell the Henson that we want to pass an ArrayList<Result> object
    //After each @InjectExtra, we need to rebuild the project.
    @InjectExtra ArrayList<Result> movieList;

    @BindView(R.id.rv_movies) RecyclerView rvMovies;
    @BindView(R.id.btn_yes) Button btnYes;
    @BindView(R.id.btn_no) Button btnNo;

    private ResultAdapter resultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        //Inject this activity with Dart
        Dart.inject(this);
        initView();
    }

    private void initView() {
        //Build the RecyclerView
        resultAdapter = new ResultAdapter(movieList);
        rvMovies.setAdapter(resultAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvMovies.setLayoutManager(linearLayoutManager);
        resultAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_yes)
    public void operationSuccessful() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You are a genius. Treat yourself with some beer");
        builder.setPositiveButton("Thank you!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        })
        .show();
    }

    @OnClick(R.id.btn_no)
    public void operationFailed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You noob. Fuck off and try again");
        builder.setPositiveButton("Fuck you too", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        })
        .show();
    }
}
