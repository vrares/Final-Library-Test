package com.vrares.afinal.model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rares.vultur on 11/8/2017.
 */

public interface MovieService {

    String API_KEY = "3923714031c68cb13bffea56cc6a9430";
    String BASE_URL = "https://api.themoviedb.org/";
    String SEARCH_URL = "3/search/movie?api_key=" + API_KEY + "&query=";


}
