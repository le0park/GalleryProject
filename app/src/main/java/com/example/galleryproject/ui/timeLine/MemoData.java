package com.example.galleryproject.ui.timeLine;

import java.util.ArrayList;

public class MemoData {
    String path;
    int resId;
    String memo;

    public MemoData(int resId, String memo) {
        this.resId = resId;
        this.memo = memo;
    }

    public int getresId() {
        return resId;
    }

    public void setresId(int resId) {
        this.resId = resId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
