package edu.washington.akpuri.quizdroid;

import android.content.Intent;
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

        topicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                quiz.setCurrentTopic(position);
                if (view instanceof RelativeLayout) {
                    Log.i("ListView", "Succesfully found the custom Layout");

                    RelativeLayout topicItem = (RelativeLayout) view;
                    Log.i("ListView", "Position = " + position);
                    startActivity(frags);
                }
            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
