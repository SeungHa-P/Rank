package com.rank.rank.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rank.rank.FmRankModel;
import com.rank.rank.R;
import com.rank.rank.adapter.RankAdapter;
import com.rank.rank.databinding.FragmentRankBinding;

public class FragmentRank extends Fragment {
    private FragmentRankBinding binding;
    private FmRankModel fmRankModel;

    public FragmentRank(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_rank,container,false);
        fmRankModel= ViewModelProviders.of(this).get(FmRankModel.class);
        binding.setFmVm(fmRankModel);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rankRecy.setLayoutManager(linearLayoutManager);
        RankAdapter adapter = new RankAdapter();

        binding.rankRecy.setAdapter(adapter);


        View view = binding.getRoot();






        return view;
    }
}
