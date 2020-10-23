package com.rank.rank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rank.rank.R;
import com.rank.rank.RankSingleTon;
import com.rank.rank.model.MainModel;
import com.rank.rank.model.Partner;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private String imgURL = "https://ssongh.cafe24.com/Agency";
    private Context context;

//    private int[] imglogo = {R.drawable.logo_01,R.drawable.logo_02,R.drawable.logo_03,R.drawable.logo_04};
//    private String[] compNmae = {"애드캡슐소프트","디파이","플립커뮤니케이션즈","펜타브리드"};
//    private String[] string_1 = {"#웹 에이전시","#IT컨설팅","#홈페이지","#모바일","#공기업전문"};
//    private String[] string_2 = {"#웹 에이전시","#IT컨설팅","#홈페이지","#모바일","#시스템운영"};


    private MainModel mainModel = RankSingleTon.getInstance().getMainModel();



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();


        View view;
        RecyclerView.ViewHolder viewHolder;
        if(viewType ==0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_list_header,parent,false);
            viewHolder = new HeaderViewHolder(view);
        }else if (viewType == 1){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_list_item2,parent,false);
            viewHolder = new ItemViewHolder(view);
        }else{
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_list_item,parent,false);
             viewHolder = new ItemViewHolder(view);
        }


        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if(position ==0 ){
            return 0;
        }else{
            return 1;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder) {
           final ItemViewHolder holder1 = (ItemViewHolder) holder;

           holder1.number.setText(mainModel.getData().get(position-1).getCompanyCd()+"");

            Glide.with(context).load(imgURL
            +mainModel.getData().get(position-1).getCompanyLogo()).placeholder(R.drawable.logo_placeholder).into(holder1.imageView);

            holder1.compName.setText(mainModel.getData().get(position-1).getCompanyName());

            List<Partner> partner = new ArrayList<Partner>();
            partner=mainModel.getData().get(position-1).getPartner();
            for(int i=0;i<holder1.imgclient.length;i++){
                Glide.with(context)
                        .load(imgURL+partner.get(i).getBrandImg())
                        .placeholder(R.drawable.logo_placeholder)
                        .fitCenter()
                        .error(R.drawable.logo_01)
                        .into(holder1.imgclient[i]);
            }

            holder1.alphaBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder1.alphaItem.getVisibility() == View.GONE) {
                        holder1.alphaItem.setVisibility(View.VISIBLE);
                    } else {
                        holder1.alphaItem.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mainModel.getData().size()+1;
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder{

        HeaderViewHolder(View headview){
            super(headview);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView number;
        private TextView compName;
        private ImageView imageView;
        private ImageView sticker_01;
        private ImageView sticker_02;
        private ConstraintLayout constraintLayout;
        private int[] hashID = {R.id.hash_1,R.id.hash_2,R.id.hash_3,R.id.hash_4,R.id.hash_5};
        private int[] alphaImgId = {R.id.img_client01,R.id.img_client02,R.id.img_client03};
        private TextView[] hash = new TextView[hashID.length];
        private ConstraintLayout alphaBtn;
        private ConstraintLayout alphaItem;
        private ImageView[] imgclient=new ImageView[alphaImgId.length];
        ItemViewHolder(View view){
            super(view);
            alphaBtn=view.findViewById(R.id.alphaBtn);
            alphaItem=view.findViewById(R.id.partner);
            constraintLayout=view.findViewById(R.id.item_back);
            sticker_01=view.findViewById(R.id.sticker_01);
            sticker_02=view.findViewById(R.id.sticker_02);
            compName=view.findViewById(R.id.comp_name);
            number=view.findViewById(R.id.number);
            imageView = view.findViewById(R.id.comp_logo);


            for(int i=0;i<hashID.length;i++){
                hash[i] = view.findViewById(hashID[i]);
            }

            for(int i=0;i<alphaImgId.length;i++){
                imgclient[i] = view.findViewById(alphaImgId[i]);
            }

        }
    }
}
