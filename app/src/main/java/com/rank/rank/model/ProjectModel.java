package com.rank.rank.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectModel {
    @SerializedName("project")
    private List<ProjectDetailModel> project;


    public List<ProjectDetailModel> getProject() {
        return project;
    }

    public void setProject(List<ProjectDetailModel> project) {
        this.project = project;
    }
}
