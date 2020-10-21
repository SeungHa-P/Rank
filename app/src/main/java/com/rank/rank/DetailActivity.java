package com.rank.rank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.rank.rank.adapter.DetailAdapter;
import com.rank.rank.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    private String[] partner_name={"현대카드","아모레퍼시픽","롯데백화점","아시아나항공"};
    private String[] project_name={"롯데리조트","현대캐피탈","롯데월드타워"};
    private int[] partner_icon_id={R.drawable.client_detail_01,R.drawable.client_detail_02,R.drawable.client_detail_03,R.drawable.client_detail_04};
    private int[] project_icon_id={R.drawable.project_01,R.drawable.project_02,R.drawable.project_03};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_detail);
        binding.setActivity(this);



        binding.imgSticker01.setImageResource(R.drawable.sticker_01);
        binding.imgSticker02.setImageResource(R.drawable.sticker_02);

        binding.vpPartner.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        DetailAdapter detailAdapter = new DetailAdapter("partner",partner_icon_id,partner_name);

        binding.vpPartner.setAdapter(detailAdapter);



        binding.vpProject.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        DetailAdapter detailAdapter2 = new DetailAdapter("project",project_icon_id,project_name);

        binding.vpProject.setAdapter(detailAdapter2);







    }


    public void onBack(View view){
        finish();
    }


    public void onAlpha(View view){
        if(binding.comContentTxt.getMaxLines()!=100) {
            binding.comContentTxt.setMaxLines(100);
            binding.btnAlpha.setText("접기");
            binding.gradient.setVisibility(View.INVISIBLE);
        }else{
            binding.comContentTxt.setMaxLines(6);
            binding.btnAlpha.setText("더보기");
            binding.gradient.setVisibility(View.VISIBLE);
        }
    }


}