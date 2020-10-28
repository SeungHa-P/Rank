package com.rank.rank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.tabs.TabLayout;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.rank.rank.adapter.DrawerAdapter;
import com.rank.rank.adapter.MainAdapter;
import com.rank.rank.databinding.ActivityMainBinding;
import com.rank.rank.listener.OnItemClick;
import com.rank.rank.model.Partner;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnItemClick {
        private ActivityMainBinding binding;
        private MainViewModel mvM;

        private boolean drawerFlag = false;

        private TextView[] title;
        private int titleposition;
        private View constraintLayout;
        private DrawerLayout drawerLayout;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(drawerFlag) {
            drawerClose();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(this,"프로젝트 데이터 갯수"+RankSingleTon.getInstance().getProjectModels().getProject().size()
                +"\n메인 데이터 갯수"+RankSingleTon.getInstance().getMainModel().getData().size(),Toast.LENGTH_SHORT).show();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        mvM = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setMvM(mvM);
        binding.setActivity(this);



        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(mainAdapter);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        calendar.set(2000,10,27);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2020,10,27);


        int s = calendar.compareTo(calendar1);

        if(s > 0){
            Toast.makeText(this,"더크다",Toast.LENGTH_SHORT).show();
        }else if(s<0){
            Toast.makeText(this,"더작다",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"같다",Toast.LENGTH_SHORT).show();
        }



        title = new TextView[3];
        title[0]=binding.titleRank;
        title[1]=binding.titleProject;
        title[2]=binding.titleCustom;




        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {

                title[titleposition].setTextColor(Color.parseColor("#15000000"));
                title[position].setTextColor(Color.parseColor("#000000"));
                titleposition=position;



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        

        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!drawerFlag) {
                    binding.drwerlayout.openDrawer(binding.incdraw.drawer);
                    drawerFlag=true;
                }

            }
        });
        binding.drwerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                drawerFlag=false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.incdraw.rankRecy.setLayoutManager(linearLayoutManager);
        DrawerAdapter adapter = new DrawerAdapter(this,this);

        binding.incdraw.rankRecy.setAdapter(adapter);


    }

    public View createTab(String tabName){
        View tabView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tab,null);
        TextView textView = (TextView) tabView.findViewById(R.id.tabitem);
        textView.setText(tabName);
        return tabView;
    }

    public void drawerClose(){

            binding.drwerlayout.closeDrawer(binding.incdraw.drawer);
            drawerFlag=false;

    }

    @Override
    public void onClick() {
        drawerClose();
    }

    public void title(View view){
        switch (view.getId()){
            case R.id.title_rank:
                binding.viewPager.setCurrentItem(0);
                break;
            case R.id.title_project:
                binding.viewPager.setCurrentItem(1);
                break;
            case R.id.title_custom:
                binding.viewPager.setCurrentItem(2);
                break;
        }

    }




}
