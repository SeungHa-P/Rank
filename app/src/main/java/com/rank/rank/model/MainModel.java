package com.rank.rank.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainModel {
    @SerializedName("data")
    private List<MainDetailModel> data;

    public List<MainDetailModel> getData() {
        return data;
    }

    public void setData(List<MainDetailModel> data) {
        this.data = data;
    }
}
