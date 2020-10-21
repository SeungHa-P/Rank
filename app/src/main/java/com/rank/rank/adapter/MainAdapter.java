package com.rank.rank.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rank.rank.FragmentCustom;
import com.rank.rank.FragmentProject;
import com.rank.rank.FragmentRank;

public class MainAdapter extends FragmentPagerAdapter {


    public MainAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentRank fragmentRank = new FragmentRank();
                return fragmentRank;
            case 1:
                FragmentProject fragmentProject = new FragmentProject();
                return fragmentProject;
            case 2:
                FragmentCustom fragmentCustom =new FragmentCustom();
                return fragmentCustom;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }



}
