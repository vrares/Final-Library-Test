package com.vrares.afinal.model.networking;

import com.vrares.afinal.model.pojo.Movie;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MovieClient {

    String SEARCH_URL = "/3/search/movie";

    //The Get method that has the filters needed for the search as parameters
    @GET(SEARCH_URL)
    Call<Movie> getMovieList(@QueryMap Map<String, String> filters);
}
