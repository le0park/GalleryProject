package com.example.galleryproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TopCalendarLayout extends LinearLayout {
    private TextView yearTextView;
    private TextView monthTextView;
    private Button showCalendarButton;

    private OnExpandListener listener;

    public TopCalendarLayout(Context context) {
        super(context);
        init(context);
    }

    public TopCalendarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.fragment_top_calendar, this, true);

        yearTextView = (TextView) findViewById(R.id.yearTextView);
        monthTextView = (TextView) findViewById(R.id.monthTextView);
        showCalendarButton = (Button) findViewById(R.id.showCalendarButton);
        showCalendarButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onExpand();
            }
        });
    }

    public void setOnExpandListener(OnExpandListener listener){
        this.listener = listener;
    }
}
