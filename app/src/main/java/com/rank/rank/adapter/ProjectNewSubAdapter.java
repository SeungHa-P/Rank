package com.rank.rank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.rank.rank.R;
import com.rank.rank.RankSingleTon;
import com.rank.rank.model.ProjectDetailModel;

import java.util.ArrayList;
import java.util.List;

public class ProjectNewSubAdapter extends RecyclerView.Adapter<ProjectNewSubAdapter.ViewHolder> {

    private String imgURL = "https://ssongh.cafe24.com/Agency";
    private Context context;
    private int companyCD;
    private int count;
    private ArrayList<Boolean> visibilityList;

    private List<ProjectDetailModel> projectDetailModel;

    public ProjectNewSubAdapter(Context context, int companyCD) {
        this.context = context;
        this.companyCD = companyCD;
        for (int i = 0; i < RankSingleTon.getInstance().getMainModel().getData().size(); i++) {
            if (RankSingleTon.getInstance().getMainModel().getData().get(i).getCompanyCd() == companyCD) {
                this.projectDetailModel = RankSingleTon.getInstance().getMainModel().getData().get(i)
                        .getProject();
                break;
            } else {
                continue;
            }
        }
    }

    @NonNull
    @Override
    public ProjectNewSubAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_grid_item, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProjectNewSubAdapter.ViewHolder holder, int position) {
        holder.projectDay.setText(projectDetailModel.get(position).getDate());
        Glide.with(context).load(imgURL + projectDetailModel.get(position).getProjectImg())
                .apply(new RequestOptions().transform(new CenterInside(), new GranularRoundedCorners((int) (3 * RankSingleTon.getInstance().getDensity()), (int) (3 * RankSingleTon.getInstance().getDensity()), 0, 0)))
                .placeholder(R.drawable.logo_placeholder).into(holder.projectImg);
        holder.projectName.setText(projectDetailModel.get(position).getProjectName());
    }


    @Override
    public int getItemCount() {
        return projectDetailModel.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView projectImg;
        private TextView projectName;
        private TextView projectDay;


        ViewHolder(View view) {
            super(view);
            projectName = view.findViewById(R.id.project_Name);
            projectImg = view.findViewById(R.id.project_Img);
            projectDay = view.findViewById(R.id.project_day);


        }
    }
}
