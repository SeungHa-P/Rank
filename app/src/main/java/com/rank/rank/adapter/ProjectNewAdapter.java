package com.rank.rank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rank.rank.R;

public class ProjectNewAdapter extends RecyclerView.Adapter<ProjectNewAdapter.ViewHolder>{
    private Context context;


    private String[] co_name = {"애드캡슐소프트","디파이","펜타브리드"};
    private int[] head_img_id = {R.drawable.logo_01,R.drawable.logo_02,R.drawable.logo_04};


    private String[] adcap = {"롯데리조트","현대캐피탈","롯데월드타워","현대카드 블로그"};
    private int[] adcapimg={R.drawable.project_01,R.drawable.project_02,R.drawable.project_03,R.drawable.project_04};
    private String[] adcapday={"19.10.01","19.09.28","19.09.25","19.08.10"};


    private String[] dfy = {"GS SHOP","레이노 코리아"};
    private int[] dfyimg = {R.drawable.project_05,R.drawable.project_06};
    private String[] dfyday = {"19.08.05","19.07.25"};


    private String[] penta={"대림성모병원","삼성금융 Open Coll..."};
    private int[] pentaimg={R.drawable.project_07,R.drawable.project_08};
    private String[] pentaday={"19.08.05","19.07.25"};


    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView co_name;
        private ImageView head_img;
        private RecyclerView recyclerView;
        private GridLayoutManager gridLayoutManager;
        private ProjectNewSubAdapter projectNewSubAdapter;
        private ConstraintLayout constraintLayout;
        ViewHolder(View view) {
        super(view);
            constraintLayout = view.findViewById(R.id.list_head);
            co_name=view.findViewById(R.id.head_text);
            head_img = view.findViewById(R.id.head_img);
            recyclerView = view.findViewById(R.id.project_new_recy);
            gridLayoutManager =new GridLayoutManager(context,2);
            recyclerView.setLayoutManager(gridLayoutManager);
            projectNewSubAdapter = new ProjectNewSubAdapter();


        }

    }
    public void setContext(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public ProjectNewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.project_new_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ProjectNewAdapter.ViewHolder holder, int position) {
        holder.head_img.setImageResource(head_img_id[position]);
        holder.co_name.setText(co_name[position]);
        holder.recyclerView.setLayoutManager(holder.gridLayoutManager);
        if (co_name[position].equals("애드캡슐소프트")){
            holder.projectNewSubAdapter.setting(adcap,adcapimg,adcapday);
        }else if (co_name[position].equals("디파이")){
            holder.projectNewSubAdapter.setting(dfy,dfyimg,dfyday);
        }else if (co_name[position].equals("펜타브리드")){
            holder.projectNewSubAdapter.setting(penta,pentaimg,pentaday);
        }
        holder.recyclerView.setAdapter(holder.projectNewSubAdapter);


        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.recyclerView.getVisibility()==View.GONE){
                    holder.recyclerView.setVisibility(View.VISIBLE);
                }else {
                    holder.recyclerView.setVisibility(View.GONE);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return co_name.length;
    }


}
