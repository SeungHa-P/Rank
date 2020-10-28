package com.rank.rank.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rank.rank.RankSingleTon;

public class DetailItemDeco extends RecyclerView.ItemDecoration {
    private int firstMargin;
    private int itemMargin;


    public DetailItemDeco(Context context, int fistmarginDp,int itemmarginDp){

        firstMargin=(int)(fistmarginDp * RankSingleTon.getInstance().getDensity());
        itemMargin=(int)(itemmarginDp * RankSingleTon.getInstance().getDensity());
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();


        if (position == 0){
            outRect.left = firstMargin;
            outRect.right = itemMargin;

        }else{
            outRect.right= itemMargin;
        }

    }
}
