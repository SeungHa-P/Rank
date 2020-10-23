package com.rank.rank;

import com.rank.rank.model.MainModel;
import com.rank.rank.model.ProjectModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class RankSingleTon {
    private static RankSingleTon instance;


    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Calendar todayYear = Calendar.getInstance();

    private ProjectModel projectModels;
    private MainModel mainModel;

    private int divicEdpi;

    private float density;

    private RankSingleTon() {  }

    public static RankSingleTon getInstance(){
        if(instance == null){
            instance = new RankSingleTon();
        }
        return instance;
    }


    public int getDivicEdpi() {
        return divicEdpi;
    }

    public void setDivicEdpi(int divicEdpi) {
        this.divicEdpi = divicEdpi;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public MainModel getMainModel() {
        return mainModel;
    }

    public void setMainModel(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public ProjectModel getProjectModels() {
        return projectModels;
    }

    public void setProjectModels(ProjectModel projectModels) {
        this.projectModels = projectModels;
    }
}
