package com.rank.rank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.rank.rank.manager.MainModelClient;
import com.rank.rank.manager.MainModelData;
import com.rank.rank.manager.ProjectClient;
import com.rank.rank.manager.ProjectData;
import com.rank.rank.model.MainModel;
import com.rank.rank.model.ProjectModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        RankSingleTon.getInstance().setDivicEdpi(displayMetrics.densityDpi);

        RankSingleTon.getInstance().setDensity(displayMetrics.density);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.INTERNET}, 0);


            } else {

                ProjectData projectData = ProjectClient.projectData(IntroActivity.this);
                projectData.getDirection().enqueue(new Callback<ProjectModel>() {
                    @Override
                    public void onResponse(Call<ProjectModel> call, Response<ProjectModel> response) {

                        Log.d("프로젝트 데이타",""+response.body().getProject().size());
                        ProjectModel projectModel = new ProjectModel();
                        projectModel.setProject(response.body().getProject());
                        RankSingleTon.getInstance().setProjectModels(projectModel);
                    }

                    @Override
                    public void onFailure(Call<ProjectModel> call, Throwable t) {

                    }
                });



                MainModelData mainModelData = MainModelClient.mainModelData(IntroActivity.this);
                mainModelData.getDirection().enqueue(new Callback<MainModel>() {
                    @Override
                    public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                        MainModel mainModel = new MainModel();
                        mainModel.setData(response.body().getData());
                        RankSingleTon.getInstance().setMainModel(mainModel);
                    }

                    @Override
                    public void onFailure(Call<MainModel> call, Throwable t) {

                    }
                });



                startMain();




            }
        }






        }




    void startMain(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }
}