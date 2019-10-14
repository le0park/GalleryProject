package com.example.galleryproject;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BottomCalendarLayout extends LinearLayout {
    private RecyclerView monthRecyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayout bottom_LinearLayout;
    private boolean isExpanded;

    private Animation slidingDownAnim;
    private Animation slidingUpAnim;

    public BottomCalendarLayout(Context context) {
        super(context);
        init(context);
    }

    public BottomCalendarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bottom_calendar, this, true);
        bottom_LinearLayout = (LinearLayout) findViewById(R.id.bottom_LinearLayout);

        monthRecyclerView = (RecyclerView) findViewById(R.id.monthRecyclerView);
        monthRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

        ArrayList<String> months = new ArrayList<String>();
        for(int i=1;i<10;i++)
            months.add(" " + i);
        months.add("10");
        months.add("11");
        months.add("12");
        adapter = new Adapter(months);
        monthRecyclerView.setAdapter(adapter);

        bottom_LinearLayout.setVisibility(GONE);
        isExpanded = false;

        slidingDownAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_calendar_down_flow);
        slidingUpAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_calendar_up_flow);

        SlidingCalendarAnimation animListener = new SlidingCalendarAnimation();
        slidingDownAnim.setAnimationListener(animListener);
        slidingUpAnim.setAnimationListener(animListener);
    }

    public void setVisibility(){
        Log.e("VISIBILITY", "CHANGE" + isExpanded);
        if(isExpanded) {
//            bottom_LinearLayout.setVisibility(GONE);
//            isExpanded = false;

            bottom_LinearLayout.startAnimation(slidingUpAnim);
        }
        else {
//            bottom_LinearLayout.setVisibility(VISIBLE);
//            isExpanded = true;
            bottom_LinearLayout.setVisibility(VISIBLE);
            bottom_LinearLayout.startAnimation(slidingDownAnim);

        }
    }

    private class SlidingCalendarAnimation implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isExpanded) {
                bottom_LinearLayout.setVisibility(GONE);
                isExpanded = false;
            }
            else {
                isExpanded = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
        ArrayList<String> months;

        public Adapter(ArrayList<String> months) {
            this.months = months;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView calenderItem = null;

            ViewHolder(View itemView) {
                super(itemView) ;
                calenderItem = (TextView) itemView.findViewById(R.id.calendarItem);
            }
        }

        @NonNull
        @Override
        public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.calender_item, parent, false);

            // set the view's size, margins, paddings and layout parameters

            Adapter.ViewHolder vh = new Adapter.ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
            String month = months.get(position);
            holder.calenderItem.setText(month);
        }

        @Override
        public int getItemCount() {
            return months.size();
        }
    }

}
