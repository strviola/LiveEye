package com.hackathon.liveeye.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hackathon.liveeye.R;
import com.hackathon.liveeye.dto.WorkTitle;
import com.hackathon.liveeye.io.GetWorks;

import java.util.ArrayList;
import java.util.List;

public class WorksListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_works_list);

        // get list view
        ListView worksList = (ListView) findViewById(R.id.worksList);

        // get works data to print
        List<WorkTitle> workTitles = GetWorks.getWorksFromDB();
        Log.d(getClass().getSimpleName(), workTitles.toString());

        // set works view
        List<String> workDateTimes = new ArrayList<String>();
        for (WorkTitle workTitle : workTitles) {
            workDateTimes.add(workTitle.getDateString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                workDateTimes);
        worksList.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.works_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
