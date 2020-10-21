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

import com.rank.rank.R;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private String detail_name;
    private int[] detail_icon_id;
    private String[] detail_name_array;

   public DetailAdapter(String detail_name,int[] detail_icon_id,String[] detail_name_array){
        this.detail_name=detail_name;
        this.detail_icon_id=detail_icon_id;
        this.detail_name_array=detail_name_array;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.detail_img.setImageResource(detail_icon_id[position]);
        holder.detail_txt.setText(detail_name_array[position]);
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
        return detail_name_array.length;
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
