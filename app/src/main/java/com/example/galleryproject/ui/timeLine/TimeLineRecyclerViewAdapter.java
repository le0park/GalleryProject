package com.example.galleryproject.ui.timeLine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galleryproject.R;
import com.example.galleryproject.ui.all.AllRecyclerViewAdapter;

import java.util.ArrayList;

import xyz.sangcomz.stickytimelineview.TimeLineRecyclerView;

public class TimeLineRecyclerViewAdapter extends RecyclerView.Adapter<TimeLineRecyclerViewAdapter.ViewHolder> {
    private ArrayList<MemoData> memoDatas;

    public TimeLineRecyclerViewAdapter(ArrayList<MemoData> memoDatas) {
        this.memoDatas = memoDatas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView timeLineImage1;
        public TextView timeLineMemo;

        ViewHolder(View itemView) {
            super(itemView) ;
            timeLineImage1 = (ImageView)itemView.findViewById(R.id.timeLineImage1);
            timeLineMemo = (TextView)itemView.findViewById(R.id.timeLineMemo);
            // 뷰 객체에 대한 참조. (hold strong reference)
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.memo_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.timeLineImage1.setImageResource(memoDatas.get(position).getresId());
        holder.timeLineMemo.setText(memoDatas.get(position).getMemo());
    }

    @Override
    public int getItemCount() {
        return memoDatas.size();
    }
}
