package com.rank.rank.model;

import androidx.viewpager.widget.PagerAdapter;

import com.google.gson.annotations.SerializedName;

public class ProjectDetailModel {
    @SerializedName("projectName")
    private String projectName;

    @SerializedName("projectImg")
    private String projectImg;

    @SerializedName("type")
    private String type;

    @SerializedName("date")
    private String date;

    @SerializedName("companyCd")
    private int companyCd;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectImg() {
        return projectImg;
    }

    public void setProjectImg(String projectImg) {
        this.projectImg = projectImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(int companyCd) {
        this.companyCd = companyCd;
    }
}
