package com.example.galleryproject;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BottomCalendarLayout extends FrameLayout {
    private RecyclerView monthRecyclerView;
    private RecyclerView.Adapter adapter;
    private boolean isExpanded;

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
        inflater.inflate(R.layout.fragment_month_calendar, this, true);

        monthRecyclerView = (RecyclerView) findViewById(R.id.monthRecyclerView);
        monthRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        ArrayList<String> months = new ArrayList<String>();
        for(int i=1;i<=12;i++)
            months.add(i+"월");

        adapter = new Adapter(months);
        monthRecyclerView.setAdapter(adapter);

        monthRecyclerView.setVisibility(GONE);
        isExpanded = false;
    }

    public void setVisibility(){
        Log.e("ORIGINAL__VISIBLITY", " " + monthRecyclerView.getVisibility());
        if(isExpanded) {
            monthRecyclerView.setVisibility(GONE);
            isExpanded = false;
        }
        else {
            monthRecyclerView.setVisibility(VISIBLE);
            isExpanded = true;
        }
        Log.e("NEW__VISIBLITY", " " + monthRecyclerView.getVisibility());
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
