package com.rank.rank.manager;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModelClient {

    public static MainModelData mainModelData(Context context){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://ssongh.cafe24.com")
                .build();

        return retrofit.create(MainModelData.class);
    };

}
