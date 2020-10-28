package com.rank.rank.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rank.rank.DetailActivity;
import com.rank.rank.MainActivity;
import com.rank.rank.R;
import com.rank.rank.RankSingleTon;
import com.rank.rank.databinding.ActivityMainBinding;
import com.rank.rank.listener.OnItemClick;

import java.util.ArrayList;


public class DrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private   Intent intent;
    private boolean toweny;
    private String imgURL = "https://ssongh.cafe24.com/Agency";

    private OnItemClick onItemClick;
    private Context context;
    private ArrayList<Boolean> visibilityList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_list_item2, parent, false);
            viewHolder = new ItemViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_list_item, parent, false);
            viewHolder = new ItemViewHolder(view);
        }


        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
          String date_string[] = RankSingleTon.getInstance().getMainModel().getData().get(position).getFounded().split("-");
        if (RankSingleTon.getInstance().getToweny(date_string[0], date_string[1], date_string[2])) {
            toweny = true;
            return 0;
        }else{
            toweny=false;
            return 1;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
                final ItemViewHolder viewHolder = (ItemViewHolder) holder;

            Glide.with(context).load(imgURL+RankSingleTon.getInstance().getMainModel().getData()
                .get(position).getCompanyLogo())
                    .placeholder(R.drawable.logo_placeholder)
                    .into(viewHolder.imageView);


            if(toweny){
                Glide.with(context).load(R.drawable.sticker_01)
                        .placeholder(R.drawable.logo_placeholder)
                        .into(viewHolder.sticker_01);
                Glide.with(context).load(R.drawable.sticker_02)
                        .placeholder(R.drawable.logo_placeholder)
                        .into(viewHolder.sticker_02);
            }

                viewHolder.compName.setText(
                        RankSingleTon.getInstance().getMainModel().getData().get(position)
                        .getCompanyName());


                for(int i=0;i<RankSingleTon.getInstance().getMainModel().getData().get(position).getPartner().size();i++){
                    Glide.with(context).load(imgURL+RankSingleTon.getInstance().getMainModel().getData().get(position)
                    .getPartner().get(i).getBrandImg())
                            .placeholder(R.drawable.logo_placeholder)
                            .into(viewHolder.partner[i]);
                }


            if(visibilityList.get(position)){
                viewHolder.alphaItem.setVisibility(View.VISIBLE);
            }else{
                viewHolder.alphaItem.setVisibility(View.GONE);
            }

            viewHolder.alphaBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(visibilityList.get(position)){
                        visibilityList.set(position, false);
                        viewHolder.alphaItem.setVisibility(View.GONE);
                    }else{
                        visibilityList.set(position, true);
                        viewHolder.alphaItem.setVisibility(View.VISIBLE);
                    }

                }
            });





            viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick.onClick();
                        intent = new Intent(context,DetailActivity.class);
                        intent.putExtra("position",position);

                        context.startActivity(intent);
                    }
                });

        }



    }

    @Override
    public int getItemCount() {
        return RankSingleTon.getInstance().getMainModel().getData().size();
    }

    public DrawerAdapter(Context context, OnItemClick listner){
        this.context = context;
        onItemClick = listner;
        visibilityList=new ArrayList<>();
        for(int i=0; i<RankSingleTon.getInstance().getMainModel().getData().size();i++) {
            visibilityList.add(false);
        }
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView compName;
        private ImageView imageView;
        private ImageView sticker_01;
        private ImageView sticker_02;
        private ConstraintLayout constraintLayout;
        private int[] hashID = {R.id.hash_1, R.id.hash_2, R.id.hash_3, R.id.hash_4, R.id.hash_5};
        private TextView[] hash = new TextView[hashID.length];
        private ConstraintLayout alphaBtn;
        private ConstraintLayout alphaItem;

        private int[] partnerId = {R.id.img_client01,R.id.img_client02,R.id.img_client03};
        private ImageView[] partner = new ImageView[partnerId.length];

        ItemViewHolder(View view) {
            super(view);
            alphaBtn = view.findViewById(R.id.alphaBtn);
            alphaItem = view.findViewById(R.id.partner);
            constraintLayout = view.findViewById(R.id.item_back);
            sticker_01 = view.findViewById(R.id.sticker_01);
            sticker_02 = view.findViewById(R.id.sticker_02);
            compName = view.findViewById(R.id.comp_name);

            imageView = view.findViewById(R.id.comp_logo);
            for (int i = 0; i < hashID.length; i++) {
                hash[i] = view.findViewById(hashID[i]);
            }
            for (int i=0;i<partnerId.length;i++){
                partner[i] = view.findViewById(partnerId[i]);
            }

        }
    }

    public void setClear(){

        for(int i=0; i<RankSingleTon.getInstance().getMainModel().getData().size();i++) {
            visibilityList.set(i,false);
        }
        notifyDataSetChanged();
    }

}

