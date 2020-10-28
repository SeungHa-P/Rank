package com.rank.rank.adapter;

import android.content.Context;
import android.graphics.Typeface;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rank.rank.R;
import com.rank.rank.RankSingleTon;
import com.rank.rank.listener.OnItemClick;

public class ProjectNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    private OnItemClick click;


    public ProjectNewAdapter(Context context, OnItemClick click) {
        this.context = context;
        this.click = click;
    }

//
//    private String[] co_name = {"애드캡슐소프트","디파이","펜타브리드"};
//    private int[] head_img_id = {R.drawable.logo_01,R.drawable.logo_02,R.drawable.logo_04};
//
//
//    private String[] adcap = {"롯데리조트","현대캐피탈","롯데월드타워","현대카드 블로그"};
//    private int[] adcapimg={R.drawable.project_01,R.drawable.project_02,R.drawable.project_03,R.drawable.project_04};
//    private String[] adcapday={"19.10.01","19.09.28","19.09.25","19.08.10"};
//
//
//    private String[] dfy = {"GS SHOP","레이노 코리아"};
//    private int[] dfyimg = {R.drawable.project_05,R.drawable.project_06};
//    private String[] dfyday = {"19.08.05","19.07.25"};
//
//
//    private String[] penta={"대림성모병원","삼성금융 Open Coll..."};
//    private int[] pentaimg={R.drawable.project_07,R.drawable.project_08};
//    private String[] pentaday={"19.08.05","19.07.25"};

    class HeadViewHolder extends RecyclerView.ViewHolder {


        private String imgURL = "https://ssongh.cafe24.com/Agency";
        private TextView filter1;
        private TextView filter2;
        public TextView projectcount;


        HeadViewHolder(View view) {
            super(view);

            projectcount = view.findViewById(R.id.project_count_txt);
            filter1 = view.findViewById(R.id.fillter);
            filter2 = view.findViewById(R.id.fillter2);


        }

    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView co_name;
        private ImageView head_img;
        private RecyclerView recyclerView;
        private GridLayoutManager gridLayoutManager;
        private ConstraintLayout constraintLayout;

        ItemViewHolder(View view) {
            super(view);
            constraintLayout = view.findViewById(R.id.list_head);
            co_name = view.findViewById(R.id.head_text);
            head_img = view.findViewById(R.id.head_img);
            recyclerView = view.findViewById(R.id.project_new_recy);
            gridLayoutManager = new GridLayoutManager(context, 2);
            recyclerView.setLayoutManager(gridLayoutManager);

            recyclerView.addItemDecoration(new GridItemDeco(0, 7, RankSingleTon.getInstance().getDivicEdpi(), RankSingleTon.getInstance().getDensity(), 2));

        }

    }

    public void setContext(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_header, parent, false);
            viewHolder = new HeadViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_new_item, parent, false);
            viewHolder = new ItemViewHolder(view);
        }

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadViewHolder) {
            final HeadViewHolder vh = (HeadViewHolder) holder;
            String first = "총";
            String last = "개의 프로젝트";
            vh.projectcount.setText(first + " " + RankSingleTon.getInstance().getProjectModels().getProject().size() + last);


            String headText = vh.projectcount.getText().toString();
            SpannableString spannableString = new SpannableString(headText);


            int start = headText.indexOf(first) + 1;
            int end = headText.indexOf(last);
            Log.d("stringline", "총길이 : " + headText.length()
                    + "\n시작점 : " + start + "\n도착점 : " + end);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            vh.projectcount.setText(spannableString);

            vh.filter1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "최신순", Toast.LENGTH_SHORT).show();
                    click.onClick("최신순");
                }
            });

            vh.filter2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "추천순", Toast.LENGTH_SHORT).show();
                    click.onClick("추천순");
                }
            });

        } else if (holder instanceof ItemViewHolder) {
            final ItemViewHolder vh = (ItemViewHolder) holder;
            String imgURL = "https://ssongh.cafe24.com/Agency";
            Glide.with(context).load(imgURL + RankSingleTon.getInstance().getMainModel()
                    .getData().get(position - 1).getCompanyLogo()).into(vh.head_img);

            vh.co_name.setText(RankSingleTon.getInstance().getMainModel().getData()
                    .get(position - 1).getCompanyName());

            ProjectNewSubAdapter projectNewSubAdapter = new ProjectNewSubAdapter(context,
                    RankSingleTon.getInstance().getMainModel().getData().get(position - 1).getCompanyCd()
            );


            vh.recyclerView.setAdapter(projectNewSubAdapter);

            vh.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (vh.recyclerView.getVisibility() == View.GONE) {
                        vh.recyclerView.setVisibility(View.VISIBLE);
                    } else {
                        vh.recyclerView.setVisibility(View.GONE);
                    }
                }
            });
        }

//        holder.head_img.setImageResource(head_img_id[position]);
//        holder.co_name.setText(co_name[position]);
//        holder.recyclerView.setLayoutManager(holder.gridLayoutManager);
//        if (co_name[position].equals("애드캡슐소프트")){
//            holder.projectNewSubAdapter.setting(adcap,adcapimg,adcapday);
//        }else if (co_name[position].equals("디파이")){
//            holder.projectNewSubAdapter.setting(dfy,dfyimg,dfyday);
//        }else if (co_name[position].equals("펜타브리드")){
//            holder.projectNewSubAdapter.setting(penta,pentaimg,pentaday);
//        }
//        holder.recyclerView.setAdapter(holder.projectNewSubAdapter);
//
//
//        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (holder.recyclerView.getVisibility()==View.GONE){
//                    holder.recyclerView.setVisibility(View.VISIBLE);
//                }else {
//                    holder.recyclerView.setVisibility(View.GONE);
//                }
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return RankSingleTon.getInstance().getMainModel().getData().size() + 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
