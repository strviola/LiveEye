package com.hackathon.liveeye.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.LiveQuery;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;
import com.hackathon.liveeye.R;
import com.hackathon.liveeye.dto.Location;
import com.hackathon.liveeye.dto.WorkTitle;
import com.hackathon.liveeye.io.Common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WorkFrameActivity extends Activity {
    private final String tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_frame);

        // get data from previous activity
        WorkTitle work = (WorkTitle) getIntent().getSerializableExtra("savedWork");
        Log.d(tag, work.toString());

        List<String> frameInfos = new ArrayList<String>();
        Database locations = Common.getDatabase(getApplicationContext(), "frame");
        Database works = Common.getDatabase(getApplicationContext(), "work");

        Query q = locations.createAllDocumentsQuery();
        try {
            QueryEnumerator qe = q.run();
            for (Iterator<QueryRow> row = qe; row.hasNext();) {
                QueryRow r = row.next();
                Log.i("WorkFrameActivity", r.getDocumentId());
            }
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }


        // TODO: あとでちゃんと実装したら消す
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                work.frameId
        );
        ListView idView = (ListView) findViewById(R.id.frameID);
        idView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.work_frame, menu);
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
