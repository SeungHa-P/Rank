package com.rank.rank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rank.rank.adapter.ProjectAdapter;
import com.rank.rank.adapter.ProjectNewAdapter;
import com.rank.rank.databinding.FragmentProjectBinding;

public class FragmentProject extends Fragment {
    private FragmentProjectBinding binding;

    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    public FragmentProject(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_project,container,false);
        View view=binding.getRoot();

        gridLayoutManager = new GridLayoutManager(getContext(),2);
        linearLayoutManager = new LinearLayoutManager(getContext());
        contextAdapter("추천순");



        binding.fillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contextAdapter("최신순");
            }
        });
        binding.fillter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contextAdapter("추천순");
            }
        });
        return view;
    }


    public void contextAdapter(String filter){
        if("추천순".equals(filter)) {

            binding.projectRecy.setLayoutManager(gridLayoutManager);
            ProjectAdapter projectAdapter = new ProjectAdapter();
            binding.projectRecy.setAdapter(projectAdapter);
        }else if ("최신순".equals(filter)){
            binding.projectRecy.setLayoutManager(linearLayoutManager);
            ProjectNewAdapter projectAdapter = new ProjectNewAdapter();
            projectAdapter.setContext(getContext());
            binding.projectRecy.setAdapter(projectAdapter);
        }

    }

}
