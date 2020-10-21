package com.rank.rank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rank.rank.R;

public class ProjectNewSubAdapter extends RecyclerView.Adapter<ProjectNewSubAdapter.ViewHolder> {


    public int[] projectimg;
    public String[] projectname;
    public String[] projectday;

    @NonNull
    @Override
    public ProjectNewSubAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_grid_item,parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProjectNewSubAdapter.ViewHolder holder, int position) {
        holder.projectImg.setImageResource(projectimg[position]);
        holder.projectName.setText(projectname[position]);
        holder.projectDay.setText(projectday[position]);
    }


    @Override
    public int getItemCount() {
        return projectname.length;
    }


    public void setting(String[] projectname,int[] projectimg,String[] projectday){
        this.projectname = projectname;
        this.projectimg = projectimg;
        this.projectday = projectday;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView projectImg;
        private TextView projectName;
        private TextView projectDay;


        ViewHolder(View view)
        {
            super(view);
            projectName = view.findViewById(R.id.project_Name);
            projectImg = view.findViewById(R.id.project_Img);
            projectDay = view.findViewById(R.id.project_day);


        }
    }
}
