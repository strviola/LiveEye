package com.hackathon.liveeye.io;

import com.hackathon.liveeye.dto.WorkTitle;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by SuzukiRyota on 2014/09/07.
 */
public class GetWorks {
    public static List<WorkTitle> getWorksFromDB() {
        // TODO: ちゃんとDBからデータ取って来て返す
        Calendar now = Calendar.getInstance();
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_YEAR, -1);
        return Arrays.asList(
                new WorkTitle("1", now),
                new WorkTitle("2", yesterday));
    }
}
