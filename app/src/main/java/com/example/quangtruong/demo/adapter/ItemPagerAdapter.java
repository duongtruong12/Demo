package com.example.quangtruong.demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.quangtruong.demo.R;

public class ItemPagerAdapter extends PagerAdapter {

    protected View content;
    protected Toolbar toolbar;
    private LayoutInflater mLayoutInflater;
    private final int[] mItems;

    private mBottomAction mListener;

    public interface mBottomAction {
        void toDismiss();
    }


    public ItemPagerAdapter(Context context, int[] items, mBottomAction mBottomAction) {
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mItems = items;
        this.mListener = mBottomAction;
    }

    @Override
    public int getCount() {
        return mItems.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
        toolbar = (Toolbar) itemView.findViewById(R.id.pager_toolbar);
        content = (View) itemView.findViewById(R.id.pagercontent);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.toDismiss();
            }
        });
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    public void onExpanded(Context context) {
        content.setBackgroundColor(context.getResources().getColor(R.color.background_movie));
        notifyDataSetChanged();
    }

    public void onCollapsed(Context context) {
        content.setBackground(context.getResources().getDrawable(R.drawable.bg_item_recycler));
        notifyDataSetChanged();
    }

    public void onDraggin(Context context) {
        content.setBackgroundColor(context.getResources().getColor(R.color.background_movie));
        notifyDataSetChanged();
    }
}
