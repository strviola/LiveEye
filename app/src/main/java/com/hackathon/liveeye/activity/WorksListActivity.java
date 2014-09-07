package com.hackathon.liveeye.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        final List<WorkTitle> workTitles = GetWorks.getWorksFromDB();
        Log.d(getClass().getSimpleName(), workTitles.toString());

        // show works date-time
        List<String> workDateTimes = new ArrayList<String>();
        for (WorkTitle workTitle : workTitles) {
            workDateTimes.add(workTitle.uploadDate);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                workDateTimes);
        worksList.setAdapter(adapter);

        // set listener on item selected
        worksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: フレーム詳細画面に遷移
                String msg = "Selected Item: ";
                msg += workTitles.get(i).uploadDate + "¥n¥n";
                msg += "frame ID:¥n";
                for (String fid : workTitles.get(i).frameId) {
                    msg += fid + "¥n";
                }

                Log.d("ItemSelected", msg);

                Intent intent = new Intent(WorksListActivity.this, WorkFrameActivity.class);
                intent.putExtra("savedWork", workTitles.get(i));
                startActivity(intent);
            }
        });
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
