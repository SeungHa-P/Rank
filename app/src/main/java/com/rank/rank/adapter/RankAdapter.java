package com.rank.rank.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nex3z.flowlayout.FlowLayout;
import com.rank.rank.DetailActivity;
import com.rank.rank.R;
import com.rank.rank.RankSingleTon;
import com.rank.rank.listener.OnItemClick;
import com.rank.rank.model.MainModel;
import com.rank.rank.model.Partner;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String imgURL = "https://ssongh.cafe24.com/Agency";
    private Context context;
    private boolean toweny;

//    private int[] imglogo = {R.drawable.logo_01,R.drawable.logo_02,R.drawable.logo_03,R.drawable.logo_04};
//    private String[] compNmae = {"애드캡슐소프트","디파이","플립커뮤니케이션즈","펜타브리드"};
//    private String[] string_1 = {"#웹 에이전시","#IT컨설팅","#홈페이지","#모바일","#공기업전문"};
//    private String[] string_2 = {"#웹 에이전시","#IT컨설팅","#홈페이지","#모바일","#시스템운영"};

    private OnItemClick onItemClick;
    private Intent intent;
    private MainModel mainModel = RankSingleTon.getInstance().getMainModel();
    private ArrayList<Boolean> visibilityList;

    public RankAdapter() {
        visibilityList = new ArrayList<>();
        for (int i = 0; i < mainModel.getData().size(); i++) {
            visibilityList.add(false);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();


        View view;
        RecyclerView.ViewHolder viewHolder;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_list_header, parent, false);
            viewHolder = new HeaderViewHolder(view);
        } else if (viewType == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_list_item2, parent, false);
            viewHolder = new ItemViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_list_item, parent, false);
            viewHolder = new ItemViewHolder(view);
        }


        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        //   String date_string[] = mainModel.getData().get(position).getFounded().split("-");

        if (position == 0) {
            return 0;
        } else if (position > 0) {
            String date_string[] = mainModel.getData().get(position - 1).getFounded().split("-");
            if (RankSingleTon.getInstance().getToweny(date_string[0], date_string[1], date_string[2])) {
                toweny = true;
                return 1;
            } else {
                toweny = false;
                return 2;
            }
        }


        return 2;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        Log.d("RecyclerPosition", "" + position);
        if (holder instanceof HeaderViewHolder) {
            final HeaderViewHolder holder1 = (HeaderViewHolder) holder;
            holder1.date.setText(RankSingleTon.getInstance().getToday(0) + " 기준");
        } else if (holder instanceof ItemViewHolder) {

            final ItemViewHolder holder1 = (ItemViewHolder) holder;
            holder1.hashcontanier.removeAllViews();


            holder1.number.setText(mainModel.getData().get(position - 1).getCompanyCd() + "");

            Glide.with(context).load(imgURL
                    + mainModel.getData().get(position - 1).getCompanyLogo()).placeholder(R.drawable.logo_placeholder).into(holder1.imageView);

            holder1.compName.setText(mainModel.getData().get(position - 1).getCompanyName());

            if (toweny) {
                Glide.with(context).load(R.drawable.sticker_01)
                        .placeholder(R.drawable.logo_placeholder)
                        .into(holder1.sticker_01);
                Glide.with(context).load(R.drawable.sticker_02)
                        .placeholder(R.drawable.logo_placeholder)
                        .into(holder1.sticker_02);
            }


            View view;
            for (int i = 0; i < mainModel.getData().get(position - 1).getMainbussiness().size(); i++) {
                view = holder1.inflater.inflate(R.layout.hash_item, null, false);
                TextView textView = view.findViewById(R.id.hash);
//                hashname.add(mainModel.getData().get(position).getMainbussiness().get(i));
                textView.setText("#" + mainModel.getData().get(position - 1).getMainbussiness().get(i));

                holder1.hashcontanier.addView(view);

            }


            List<Partner> partner = new ArrayList<Partner>();
            partner = mainModel.getData().get(position - 1).getPartner();
            for (int i = 0; i < holder1.imgclient.length; i++) {
                Glide.with(context)
                        .load(imgURL + partner.get(i).getBrandImg())
                        .placeholder(R.drawable.logo_placeholder)
                        .fitCenter()
                        .error(R.drawable.logo_01)
                        .into(holder1.imgclient[i]);
            }


            if (visibilityList.get(position - 1)) {
                holder1.alphaItem.setVisibility(View.VISIBLE);
            } else {
                holder1.alphaItem.setVisibility(View.GONE);
            }

            holder1.alphaBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (visibilityList.get(position - 1)) {
                        visibilityList.set(position - 1, false);
                        holder1.alphaItem.setVisibility(View.GONE);
                    } else {
                        visibilityList.set(position - 1, true);
                        holder1.alphaItem.setVisibility(View.VISIBLE);
                    }

                }
            });

            holder1.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("position", position-1);

                    context.startActivity(intent);
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return mainModel.getData().size() + 1;
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView date;

        HeaderViewHolder(View headview) {
            super(headview);
            date = headview.findViewById(R.id.text_new);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView number;
        private TextView compName;
        private ImageView imageView;
        private ImageView sticker_01;
        private ImageView sticker_02;
        private ConstraintLayout constraintLayout;
        private int[] alphaImgId = {R.id.img_client01, R.id.img_client02, R.id.img_client03};
        private ConstraintLayout alphaBtn;
        private ConstraintLayout alphaItem;
        private ImageView[] imgclient = new ImageView[alphaImgId.length];
        private FlowLayout hashcontanier;
        private LayoutInflater inflater;

        ItemViewHolder(View view) {
            super(view);
            alphaBtn = view.findViewById(R.id.alphaBtn);
            alphaItem = view.findViewById(R.id.partner);
            constraintLayout = view.findViewById(R.id.item_back);
            sticker_01 = view.findViewById(R.id.sticker_01);
            sticker_02 = view.findViewById(R.id.sticker_02);
            compName = view.findViewById(R.id.comp_name);
            number = view.findViewById(R.id.number);
            imageView = view.findViewById(R.id.comp_logo);
            hashcontanier = view.findViewById(R.id.hash_container);
            inflater = LayoutInflater.from(context);


            for (int i = 0; i < alphaImgId.length; i++) {
                imgclient[i] = view.findViewById(alphaImgId[i]);
            }

        }
    }
}
