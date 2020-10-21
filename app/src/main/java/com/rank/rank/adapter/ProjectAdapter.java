package com.rank.rank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rank.rank.R;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {



    private int[] project_Img={R.drawable.project_01,R.drawable.project_02,R.drawable.project_03,R.drawable.project_04,R.drawable.project_05,
            R.drawable.project_06,R.drawable.project_07,R.drawable.project_08};

    private String[] project_Name = {"롯데리조트","현대캐피탈","롯데월드타워","현대카드 블로그","GS SHOP","레이토 코리아","대림성모병원","삼성금 Open Coll..."};
    private String[] project_Day = {"19.10.01","19.09.28","19.09.25","19.08.10","19.08.05","19.07.25","19.08.05","19.07.25"};

    @NonNull
    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_grid_item, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.project_img_view.setImageResource(project_Img[position]);
        holder.project_name_text.setText(project_Name[position]);
        holder.project_day_text.setText(project_Day[position]);
    }

    @Override
    public int getItemCount() {
        return project_Name.length;
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
