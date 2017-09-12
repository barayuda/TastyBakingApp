package webid.barayuda.tastybakingapp.util.rest;

import java.util.List;

import webid.barayuda.tastybakingapp.model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by BARAYUDA on 8/27/2017.
 */

public interface APIService {

    @GET(".")
    Call<List<Recipe>> getRecipes();

}
