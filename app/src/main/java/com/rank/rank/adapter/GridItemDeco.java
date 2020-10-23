package com.rank.rank.adapter;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GridItemDeco extends RecyclerView.ItemDecoration {


    private final int marginfx;
    private final int centerfx;
    public GridItemDeco(int marginDp,int centerDp,int dpi, float density) {
        marginfx=(int)(marginDp * density);
        centerfx=(int)(centerDp * density);
        Log.d("dpdp",""+marginDp);
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildLayoutPosition(view);
        int index = ((GridLayoutManager.LayoutParams)view.getLayoutParams()).getSpanIndex();


        if(position >=1) {
            if (index == 0) {
                outRect.left = marginfx;
                outRect.right=centerfx;
            } else {
                outRect.left=centerfx;
                outRect.right = marginfx;            }

        }





    }
}
