package service;

import java.util.List;

import model.RecipeModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("recipe/{id}")
    Call <RecipeModel> getRecipe(@Path("id") int Id);

    @GET("recipe")
    Call <List<RecipeModel>> getAllRecipe();

}
