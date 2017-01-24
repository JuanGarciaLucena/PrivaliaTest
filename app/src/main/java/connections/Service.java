package connections;

import java.util.List;

import models.Film;
import models.ResultResponse;
import models.SearchResults;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Juan Lucena on 20/01/2017.
 */

public interface Service {
    @GET("list/{page}?api_key=93aea0c77bc168d8bbce3918cefefa45")
    Call<ResultResponse> callFindFilms(@Path("page") int page);

    @GET("search/movie?api_key=93aea0c77bc168d8bbce3918cefefa45&query=thor&page=1")
    Call<SearchResults> callSearchFilms(@Query("query") String query, @Query("page") int page);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
