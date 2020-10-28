package com.rank.rank.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableResource;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.rank.rank.R;
import com.rank.rank.RankSingleTon;
import com.rank.rank.listener.OnItemClick;
import com.rank.rank.model.ProjectModel;

public class ProjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String imgUrl = "https://ssongh.cafe24.com/Agency";
    public OnItemClick click;
    public Context context;
    private ProjectModel projectModel = RankSingleTon.getInstance().getProjectModels();
    public ProjectAdapter(Context context){
        this.context=context;


    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder;
        if(viewType == 0) {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_header,parent,false);
            viewHolder = new HeaderViewHolder(view);
        }else if (viewType ==1){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_grid_item, parent, false);
            viewHolder = new ViewHolder(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_grid_item, parent, false);
            viewHolder = new ViewHolder(view);
        }
        return viewHolder;
    }


    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        }else{

            return 1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder){
            final HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            String first = "총";
            String last = "개의 프로젝트";
            headerViewHolder.projectcount.setText(first+" "+RankSingleTon.getInstance().getProjectModels().getProject().size()+last);


            String headText = headerViewHolder.projectcount.getText().toString();
            SpannableString spannableString = new SpannableString(headText);


            int start = headText.indexOf(first)+1;
            int end = headText.indexOf(last);
            Log.d("stringline", "총길이 : "+headText.length()
            +"\n시작점 : "+start+"\n도착점 : "+end);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            headerViewHolder.projectcount.setText(spannableString);

            headerViewHolder.filter1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"최신순",Toast.LENGTH_SHORT).show();
                }
            });

            headerViewHolder.filter2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"추천순",Toast.LENGTH_SHORT).show();
                }
            });


        }
        if(holder instanceof ViewHolder) {
            final ViewHolder viewHolder = (ViewHolder) holder;
            Glide.with(context)
                    .load(imgUrl+projectModel.getProject().get(position-1).getProjectImg())
                    .apply(new RequestOptions().transform(new CenterInside(),new GranularRoundedCorners((int)(3*RankSingleTon.getInstance().getDensity()),(int)(3*RankSingleTon.getInstance().getDensity()),0,0)))
//                    .apply(new RequestOptions().transform(new RoundedCorners((int)(3*RankSingleTon.getInstance().getDensity())))) //전체 코
                    .placeholder(R.drawable.logo_placeholder)
                    .into(viewHolder.project_img_view);

            viewHolder.project_name_text.setText(projectModel.getProject().get(position -1).getProjectName());
//            int companyCd = projectModel.getProject().get(position-1).getCompanyCd();
//            for(int i=0;i<RankSingleTon.getInstance().getMainModel().getData().size();i++){
//                if(RankSingleTon.getInstance().getMainModel().getData().get(i).getCompanyCd() == companyCd){
//                    viewHolder.project_name_text.setText(RankSingleTon.getInstance().getMainModel().getData().get(i).getCompanyName());
//                    break;
//               }else{
//                    continue;
//                }
//            }
            viewHolder.project_day_text.setText(RankSingleTon.getInstance().getProjectModels().getProject().get(position-1).getDate());
        }

    }

    @Override
    public int getItemCount() {
        return projectModel.getProject().size() + 1;
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder{
        public TextView projectcount;
        private TextView filter1;
        private TextView filter2;
        HeaderViewHolder(View view){
            super(view);

            projectcount = view.findViewById(R.id.project_count_txt);
            filter1=view.findViewById(R.id.fillter);
            filter2=view.findViewById(R.id.fillter2);


        }

    }



    class ViewHolder extends RecyclerView.ViewHolder{



        public ImageView project_img_view;
        public TextView project_name_text;
        public TextView project_day_text;
        ViewHolder(View view){
            super(view);
            project_img_view=view.findViewById(R.id.project_Img);
            project_name_text=view.findViewById(R.id.project_Name);
            project_day_text=view.findViewById(R.id.project_day);



        }
    }
}
