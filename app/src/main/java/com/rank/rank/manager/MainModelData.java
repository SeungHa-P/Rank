package com.rank.rank.manager;

import com.rank.rank.model.MainModel;
import com.rank.rank.model.ProjectModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MainModelData {
    @GET("/Agency/json/main.json")
    Call<MainModel> getDirection();
}
