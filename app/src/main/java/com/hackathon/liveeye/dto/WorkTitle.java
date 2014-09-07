package com.hackathon.liveeye.dto;

import com.hackathon.liveeye.activity.WorksListActivity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by SuzukiRyota on 2014/09/07.
 */
public class WorkTitle implements Serializable {
    public String workId;
    public String uploadDate;
    public List<String> frameId;

    public WorkTitle(String workId, String uploadDate) {
        this.workId = workId;
        this.uploadDate = uploadDate;
        this.frameId = new ArrayList<String>();
    }

    public WorkTitle addFrameId(String frameId) {
        this.frameId.add(frameId);
        return this;
    }

    @Override
    public String toString() {
        return "Work at " + uploadDate;
    }
}
