package com.rank.rank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.rank.rank.R;
import com.rank.rank.model.Partner;
import com.rank.rank.model.ProjectDetailModel;
import com.rank.rank.model.ProjectModel;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private String detail_name;
    private int[] detail_icon_id;
    private String[] detail_name_array;

    private List<Partner> partners;
    private List<ProjectDetailModel> projectModels;

    private Context context;

    private String imgUrl = "https://ssongh.cafe24.com/Agency";

    private int itemCount;
   public DetailAdapter(Context context, String detail_name, List<Partner> partners){
        this.context=context;
        this.detail_name=detail_name;
        this.partners = partners;
        itemCount=partners.size();
    }

    public DetailAdapter(Context context, String detail_name, List<ProjectDetailModel> projectModels,String st){
        this.context=context;
        this.detail_name=detail_name;
        this.projectModels = projectModels;
        itemCount=projectModels.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(detail_name.equals("partner")) {
            Glide.with(context).load(imgUrl + partners.get(position).getBrandImg())
                    .placeholder(R.drawable.logo_placeholder)
                    .into(holder.detail_img);
            holder.detail_txt.setText(partners.get(position).getCompanyName());
        }else{
            Glide.with(context).load(imgUrl + projectModels.get(position).getProjectImg())
                    .placeholder(R.drawable.logo_placeholder)
                    .into(holder.detail_img);
            holder.detail_txt.setText(projectModels.get(position).getProjectName());
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType==0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_view_item,parent,false);

        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_view_project, parent, false);

        }
      return new ViewHolder(view);
    }
    @Override
    public int getItemViewType(int position) {
        if(detail_name.equals("partner")){
            return 0;
        }else {
            return 1;
        }
    }
    @Override
    public int getItemCount() {
        return itemCount;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView detail_img;
        private TextView detail_txt;


        ViewHolder(View view){
            super(view);
            detail_img = view.findViewById(R.id.detail_vp_icon);
            detail_txt = view.findViewById(R.id.detail_vp_name);
        }


    }
}
