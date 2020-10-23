package com.rank.rank.model;

import com.google.gson.annotations.SerializedName;

public class Partner {

    @SerializedName("companyName")
    private String companyName;

    @SerializedName("brandImg")
    private String brandImg;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBrandImg() {
        return brandImg;
    }

    public void setBrandImg(String brandImg) {
        this.brandImg = brandImg;
    }
}
