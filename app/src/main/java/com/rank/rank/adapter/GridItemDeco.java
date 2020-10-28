package com.rank.rank.adapter;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GridItemDeco extends RecyclerView.ItemDecoration {


    private final int marginfx;
    private final int centerfx;
    private int flag;

    public GridItemDeco(int marginDp, int centerDp, int dpi, float density, int flag) {
        marginfx = (int) (marginDp * density);
        centerfx = (int) (centerDp * density);
        Log.d("dpdp", "" + marginDp);
        this.flag = flag;
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildLayoutPosition(view);
        if (flag == 0) {
            int index = ((GridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
            if (position >= 1) {
                if (index == 0) {
                    outRect.left = marginfx;
                    outRect.right = centerfx;
                } else if (index == 1) {
                    outRect.left = centerfx;
                    outRect.right = marginfx;
                }
            }
        } else if (flag == 1) {
            if (position >= 1) {
                outRect.left = marginfx;
                outRect.right = marginfx;
            }


        } else if (flag == 2) {
            int index = ((GridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();

            int co = ((GridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanSize();
            Log.d("grid Span", " " + co);
            if (index == 0) {
                outRect.right = centerfx;
            } else if (index == 1) {
                outRect.left = centerfx;
            }
        }


    }
}
