package com.example.yjj.demoproj;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author:YJJ
 * @date:2016/2/19
 * @email:yangjianjun@117go.com
 */
public class GridActivity extends BaseActivity {

    @Bind(R.id.grid)
    RecyclerView grid;
    private GridAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ButterKnife.bind(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setSpanSizeLookup(new SpanSize());
        grid.setLayoutManager(gridLayoutManager);
        grid.addItemDecoration(new ItemDecor1());
        adapter = new GridAdapter();
        grid.setAdapter(adapter);
    }

    private class GridAdapter extends RecyclerView.Adapter<ImageViewHolder> {


        @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case 0:
                    return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_cell, parent, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 13;
        }
    }

    private class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageViewHolder(View itemView) {
            super(itemView);
        }
    }


    private class SpanSize extends GridLayoutManager.SpanSizeLookup {
        @Override
        public int getSpanSize(int position) {
            if (position == 0) return 4;
            return 1;
        }
    }




    private class ItemDecor extends RecyclerView.ItemDecoration {
        private Drawable drawable;

        public ItemDecor() {

            drawable = getDrawable(R.drawable.divider_ver);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int count = parent.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + drawable.getIntrinsicHeight();
                drawable.setBounds(left, top, right, bottom);
                drawable.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
        }
    }


    private class ItemDecor1 extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(4, 4, 4, 4);
        }
    }

}
