package com.rank.rank.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.rank.rank.DetailActivity;
import com.rank.rank.MainActivity;
import com.rank.rank.R;
import com.rank.rank.databinding.ActivityMainBinding;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    private   Intent intent;


    private int[] imglogo = {R.drawable.logo_01, R.drawable.logo_02, R.drawable.logo_03, R.drawable.logo_04};
    private String[] compNmae = {"애드캡슐소프트", "디파이", "플립커뮤니케이션즈", "펜타브리드"};
    private String[] string_1 = {"#웹 에이전시", "#IT컨설팅", "#홈페이지", "#모바일", "#공기업전문"};
    private String[] string_2 = {"#웹 에이전시", "#IT컨설팅", "#홈페이지", "#모바일", "#시스템운영"};

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_list_item2, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_list_item, parent, false);
        }


        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (compNmae[position].equals("애드캡슐소프트") || compNmae[position].equals("디파이")) {
            return 0;
        }
        return 1;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.imageView.setImageResource(imglogo[position]);

        holder.compName.setText(compNmae[position]);
        if (!holder.compName.getText().equals("애드캡슐소프트")) {
            if (holder.compName.getText().equals("디파이")) {
                holder.sticker_01.setImageResource(R.drawable.sticker_01);
            }
            for (int i = 0; i < holder.hash.length; i++) {
                holder.hash[i].setText(string_2[i]);
            }

        } else {
            holder.sticker_01.setImageResource(R.drawable.sticker_01);
            holder.sticker_02.setImageResource(R.drawable.sticker_02);

            for (int i = 0; i < holder.hash.length; i++) {
                holder.hash[i].setText(string_1[i]);
            }
        }

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent = new Intent(v.getContext(),DetailActivity.class);
                v.getContext().startActivity(intent);

            }
        });
        holder.alphaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.alphaItem.getVisibility() == View.GONE) {
                    holder.alphaItem.setVisibility(View.VISIBLE);
                } else {
                    holder.alphaItem.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imglogo.length;
    }

    public DrawerAdapter(Context context){
        this.context = context;

    }
    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView compName;
        private ImageView imageView;
        private ImageView sticker_01;
        private ImageView sticker_02;
        private ConstraintLayout constraintLayout;
        private int[] hashID = {R.id.hash_1, R.id.hash_2, R.id.hash_3, R.id.hash_4, R.id.hash_5};
        private TextView[] hash = new TextView[hashID.length];
        private ConstraintLayout alphaBtn;
        private ConstraintLayout alphaItem;


        ViewHolder(View view) {
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


        }
    }
}

