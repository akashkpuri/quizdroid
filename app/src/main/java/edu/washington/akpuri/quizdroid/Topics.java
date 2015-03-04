package edu.washington.akpuri.quizdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;


public class Topics extends ActionBarActivity {
    private Intent downloadIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        final QuizApp quiz = QuizApp.getInstance();
        ArrayList<Topic> topics = quiz.getTopics();
        ListView topicList = (ListView) findViewById(R.id.listView);
        ListAdapter customAdapter = new TopicArrayAdapter(this, R.id.list_item, topics);
        topicList.setAdapter(customAdapter);
        final Intent frags = new Intent(Topics.this, FragmentManager.class);
        startDownload();
        topicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                quiz.setCurrentTopic(position);
                if (view instanceof RelativeLayout) {
                    //Log.i("ListView", "Succesfully found the custom Layout");

                    RelativeLayout topicItem = (RelativeLayout) view;
                    //Log.i("ListView", "Position = " + position);
                    startActivity(frags);
                }
            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.i("ActionBar", "the action bar got inflated");
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.i("ActionBar", "Inside of onOptionsItemSelected");
        //noinspection SimplifiableIfStatement
        if (id == R.id.preferences) {
            Intent setPreferences = new Intent(getApplicationContext(), Preferences.class);
            startActivityForResult(setPreferences, 1);
            Log.i("ActionBar", "The id is right");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("onActivityResult", "Right before download has started");
        startDownload();
    }

    private void startDownload() {
        Log.i("ActionBar", "Inside of startDownlaod()");
        SharedPreferences userPref = PreferenceManager.getDefaultSharedPreferences(this);
        int interval = Integer.parseInt(userPref.getString("prefInterval", "5"));
        Log.i("ActionBar", "Interval = " + interval);
        if (interval == 0) {
            interval = 5;
        }
        if (interval < 0) {
            interval = interval * -1;
        }
        interval = interval * 1000;
        downloadIntent = new Intent(Topics.this, DownloadManager.class);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //Destroy the previous download intent if there is one being broadcasted
        if ((PendingIntent.getBroadcast(Topics.this, 0, downloadIntent,
                PendingIntent.FLAG_NO_CREATE)) != null){
            PendingIntent pendingIntent = PendingIntent.getBroadcast(Topics.this, 0, downloadIntent, 0);
            manager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Topics.this, 0, downloadIntent, 0);
        if (pendingIntent == null) {
            Log.i("ActionBar", "Pending Intent is null, this is a problem");
        }
        Log.i("ActionBar", "Right before the manager sets the pending intent");
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent downloadIntent = new Intent(Topics.this, DownloadManager.class);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if ((PendingIntent.getBroadcast(Topics.this, 0, downloadIntent,
                PendingIntent.FLAG_NO_CREATE)) != null){
            PendingIntent pendingIntent = PendingIntent.getBroadcast(Topics.this, 0, downloadIntent, 0);
            manager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }
}
