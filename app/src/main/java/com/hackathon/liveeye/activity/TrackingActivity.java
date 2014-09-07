package com.hackathon.liveeye.activity;

import com.hackathon.liveeye.R;
import com.hackathon.liveeye.service.TrackingService;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TrackingActivity extends Activity {

    final static String TAG = TrackingActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tracking, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tracking, container, false);
            Button stop = (Button) rootView.findViewById(R.id.stop);
            stop.setOnClickListener(this);
            this.scheduleService();
            return rootView;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // 計測stop
                case R.id.stop:
                    this.cancelService();
                    break;
            }
        }

        private void scheduleService() {
            Log.d(TAG, "scheduleService()");
            Context context = getActivity().getBaseContext();

            Intent intent = new Intent(context, TrackingService.class);
            PendingIntent pendingIntent = PendingIntent.getService(
                    context,
                    -1,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            // 5秒毎計測
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis(),
                    5000,
                    pendingIntent);
        }

        private void cancelService() {
            Log.d(TAG, "cancelService()");
            Context context = getActivity().getBaseContext();

            Intent intent = new Intent(context, TrackingService.class);
            PendingIntent pendingIntent = PendingIntent.getService(
                    context,
                    -1,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }
    }
}
