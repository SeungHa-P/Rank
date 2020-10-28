package com.rank.rank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rank.rank.adapter.DetailAdapter;
import com.rank.rank.adapter.DetailItemDeco;
import com.rank.rank.databinding.ActivityDetailBinding;
import com.rank.rank.model.MainModel;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private int position;
    private String imgUrl = "https://ssongh.cafe24.com/Agency";

    private String[] partner_name={"현대카드","아모레퍼시픽","롯데백화점","아시아나항공"};
    private String[] project_name={"롯데리조트","현대캐피탈","롯데월드타워"};
    private int[] partner_icon_id={R.drawable.client_detail_01,R.drawable.client_detail_02,R.drawable.client_detail_03,R.drawable.client_detail_04};
    private int[] project_icon_id={R.drawable.project_01,R.drawable.project_02,R.drawable.project_03};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_detail);
        binding.setActivity(this);

        MainModel models = RankSingleTon.getInstance().getMainModel();


        Intent intent=getIntent();
        position = intent.getExtras().getInt("position");

        Log.d("Detail data position","  "+position);

        String date_String[] = RankSingleTon.getInstance().getMainModel().getData().get(position)
                .getFounded().split("-");

        if (RankSingleTon.getInstance().getToweny(date_String[0],date_String[1],date_String[2])) {
            binding.fmDetail.imgSticker01.setImageResource(R.drawable.sticker_01);
            binding.fmDetail.imgSticker02.setImageResource(R.drawable.sticker_02);
        }


        Glide.with(this)
                .load(imgUrl+models.getData().get(position).getCompanyLogo())
                .placeholder(R.drawable.logo_placeholder)
                .into(binding.fmDetail.comImg);

        binding.fmDetail.comName.setText(models.getData().get(position).getCompanyName());
        binding.fmDetail.comNameDetail.setText(models.getData().get(position).getMainText());
        binding.fmDetail.emplCount.setText(""+models.getData().get(position).getEmployees()+"명");
        binding.fmDetail.comScale.setText(models.getData().get(position).getClassification());
        String date[] = models.getData().get(position).getFounded().split("-");
        binding.fmDetail.makeCompDate.setText(date[0]+"."+date[1]+"."+date[2]);
        binding.fmDetail.comAddress.setText(models.getData().get(position).getAddress());

        String bussi=models.getData().get(position).getMainbussiness().get(0);
        for (int i=1;i< models.getData().get(position).getMainbussiness().size();i++){
            bussi+=","+models.getData().get(position).getMainbussiness().get(i);
        }
        binding.fmDetail.comBusiness.setText(bussi);

        binding.fmDetail.comContentTxt.setText(models.getData().get(position).getIntroduction());






        binding.fmDetail.vpPartner.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        DetailAdapter detailAdapter = new DetailAdapter(this,"partner",models.getData().get(position).getPartner());
        binding.fmDetail.vpPartner.addItemDecoration(new DetailItemDeco(this,25,12));
        binding.fmDetail.vpPartner.setAdapter(detailAdapter);



        binding.fmDetail.vpProject.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        DetailAdapter detailAdapter2 = new DetailAdapter(this,"project",models.getData().get(position).getProject(),"project");
        binding.fmDetail.vpProject.addItemDecoration(new DetailItemDeco(this,25,10));
        binding.fmDetail.vpProject.setAdapter(detailAdapter2);




        binding.phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailActivity.this,"전화연결",Toast.LENGTH_SHORT).show();
            }
        });
        binding.emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailActivity.this,"이메일",Toast.LENGTH_SHORT).show();
            }
        });


    }

        public void onAlpha(View view){
        if(binding.fmDetail.comContentTxt.getMaxLines()!=100) {
            binding.fmDetail.comContentTxt.setMaxLines(100);
            binding.fmDetail.btnAlpha.setText("접기");
            binding.fmDetail.gradient.setVisibility(View.INVISIBLE);
        }else{
            binding.fmDetail.comContentTxt.setMaxLines(6);
            binding.fmDetail.btnAlpha.setText("더보기");
            binding.fmDetail.gradient.setVisibility(View.VISIBLE);
        }
    }
    public void onBack(View view){
        finish();
    }



}