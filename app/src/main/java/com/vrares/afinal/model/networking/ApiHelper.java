package com.vrares.afinal.model.networking;

import android.util.Log;

import com.vrares.afinal.model.pojo.Movie;
import com.vrares.afinal.model.pojo.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class ApiHelper {

    private static final String BASE_URL = "https://api.themoviedb.org/";

    private PresenterView presenterView;

    //Create an instance for Retrofit using the BASE URL of the API
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void searchForMovie(String movieQuery, PresenterView presenterView) {
        this.presenterView = presenterView;
        //Create a Movie Client interface that deals with the Networking calls
        MovieClient movieClient = getRetrofitInstance().create(MovieClient.class);

        //Create a Map that has the filters needed in the url such as the api key and the query.
        //Using the Map values and the base url, the networking url will look something like this:
        // https://api.themoviedb.org/3/search/movie?query=it&api_key=3923714031c68cb13bffea56cc6a9430
        Map<String, String> data = new HashMap<>();
        data.put("api_key", "3923714031c68cb13bffea56cc6a9430");
        data.put("query", movieQuery);

        Call<Movie> call = movieClient.getMovieList(data);
        //Because the network call will be executed async, we use the enqueue() method to execute
        //operations in the background thread
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                //If the call is successful, execute this code
                if (response.isSuccessful()) {
                    //Store the results into an ArrayList and then pass it via the callback method
                    ArrayList<Result> resultList = response.body().getResults();
                    ApiHelper.this.presenterView.onMovieListRetrieved(resultList);
                } else {
                    Log.d("Error", "error");
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                //If the call failed, execute this code
                Log.d("Error", "error");
            }
        });
    }

    //Callback Interface
    public interface PresenterView {
        void onMovieListRetrieved(ArrayList<Result> resultList);
    }
}
