package com.progtechuc.moviedb.repositories;

import androidx.lifecycle.MutableLiveData;

import com.progtechuc.moviedb.helper.Const;
import com.progtechuc.moviedb.model.Genre;
import com.progtechuc.moviedb.model.Movies;
import com.progtechuc.moviedb.model.NowPlaying;
import com.progtechuc.moviedb.model.UpComing;
import com.progtechuc.moviedb.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static MovieRepository repository;

    private MovieRepository(){}

    public static MovieRepository getInstance(){
        if(repository == null){
            repository = new MovieRepository();
        }
        return repository;
    }

    public MutableLiveData<Movies> getMovieData(String movieid){
        final MutableLiveData<Movies> result = new MutableLiveData<>();

        ApiService.endpoint().getMovieById(movieid, Const.API_KEY).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<NowPlaying> getNowPlayingData(){
        final MutableLiveData<NowPlaying> result = new MutableLiveData<>();

        ApiService.endpoint().getNowPlaying(Const.API_KEY).enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<UpComing> getNowUpComingData(){
        final MutableLiveData<UpComing> result = new MutableLiveData<>();

        ApiService.endpoint().getUpComing(Const.API_KEY).enqueue(new Callback<UpComing>() {
            @Override
            public void onResponse(Call<UpComing> call, Response<UpComing> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UpComing> call, Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<Genre> getGenre(){
        final MutableLiveData<Genre> result = new MutableLiveData<>();

        ApiService.endpoint().getGenre(Const.API_KEY).enqueue(new Callback<Genre>() {
            @Override
            public void onResponse(Call<Genre> call, Response<Genre> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Genre> call, Throwable t) {

            }
        });

        return result;
    }

}
