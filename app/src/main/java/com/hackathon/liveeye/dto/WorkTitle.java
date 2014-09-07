package com.hackathon.liveeye.dto;

import com.hackathon.liveeye.activity.WorksListActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by SuzukiRyota on 2014/09/07.
 */
public class WorkTitle {
    public String workId;
    public Calendar uploadDate;

    public WorkTitle(String workId, Calendar uploadDate) {
        this.workId = workId;
        this.uploadDate = uploadDate;
    }

    public String getDateString() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return f.format(this.uploadDate);
    }
}
