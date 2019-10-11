package com.example.galleryproject.ui.timeLine;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galleryproject.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

import xyz.sangcomz.stickytimelineview.RecyclerSectionItemDecoration;
import xyz.sangcomz.stickytimelineview.TimeLineRecyclerView;
import xyz.sangcomz.stickytimelineview.model.SectionInfo;

public class TimeLineFragment extends Fragment {

    private TimeLineViewModel timeLineViewModel;
    private TimeLineRecyclerView timeLineRecyclerView;
    private TimeLineRecyclerViewAdapter adapter;

    private ArrayList<MemoData> dataset;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        timeLineViewModel =
                ViewModelProviders.of(this).get(TimeLineViewModel.class);
        View root = inflater.inflate(R.layout.fragment_timeline, container, false);

        timeLineRecyclerView = root.findViewById(R.id.timeLineRecyclerView);
        timeLineRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        dataset = new ArrayList<MemoData>();
        dataset.add(new MemoData(R.drawable.autumn, "2019.10.07"));
        dataset.add(new MemoData(R.drawable.autumn, "2019.10.03"));
        dataset.add(new MemoData(R.drawable.autumn, "2019.09.27"));
        dataset.add(new MemoData(R.drawable.autumn, "2019.09.25"));
        dataset.add(new MemoData(R.drawable.autumn, "2019.09.24"));

        adapter = new TimeLineRecyclerViewAdapter(dataset);
        timeLineRecyclerView.addItemDecoration(getSectionCallback(dataset));
        
        timeLineRecyclerView.setAdapter(adapter);
//        TextView textView = root.findViewById(R.id.text_timeline);
//        timeLineViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final ArrayList<MemoData> dataset){
        return new RecyclerSectionItemDecoration.SectionCallback() {
            @Nullable
            @Override
            public SectionInfo getSectionHeader(int position) {
                MemoData memoData = dataset.get(position);
                Drawable dot = getContext().getResources().getDrawable(R.drawable.dot);
                return new SectionInfo(memoData.getMemo(), "memo", dot);
            }
            @Override
            public boolean isSection(int position) {
                return !dataset.get(position).getMemo()
                        .equals(dataset.get(position-1).getMemo());
            }
        };
    }
}