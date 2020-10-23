package com.rank.rank.manager;

import com.rank.rank.model.ProjectModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProjectData {
    @GET("/Agency/json/projects.json")
    Call<ProjectModel> getDirection();
}
