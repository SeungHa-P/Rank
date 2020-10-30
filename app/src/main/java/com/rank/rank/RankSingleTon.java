package com.rank.rank;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Base64;
import android.util.Log;

import com.rank.rank.model.MainModel;
import com.rank.rank.model.ProjectModel;

import java.security.MessageDigest;
import java.security.Signature;
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

    public String getToday(int choice){
        String date;
        switch (choice){
            case 0:
                date=todayYear.get(todayYear.YEAR)+"."+(todayYear.get(todayYear.MONTH)+1) + "."+todayYear.get(todayYear.DATE);
                return date;
            case 1:
                date=todayYear.get(todayYear.YEAR)+"-"+(todayYear.get(todayYear.MONTH)+1) + "-"+todayYear.get(todayYear.DATE);
                return date;
//            case 2:
//                Calendar calendar = Calendar.getInstance();
//                calendar.add(calendar.YEAR,-20);
//                date=calendar.get(calendar.YEAR)+"-"+(calendar.get(calendar.MONTH)+1) + "-"+calendar.get(calendar.DATE);
//                return date;
            default:
                date=todayYear.get(todayYear.YEAR)+"-"+(todayYear.get(todayYear.MONTH)+1) + "-"+todayYear.get(todayYear.DATE);
                return date;
        }

    }
    public boolean getToweny(String year,String month,String day){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 =Calendar.getInstance();
        c1.set(todayYear.get(todayYear.YEAR)-20,(todayYear.get(todayYear.MONTH))+1,todayYear.get(todayYear.DATE));
        c2.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
        int x = c1.compareTo(c2);
        if(x>0){
            return true;
        }else{
            return false;
        }

    }



}
