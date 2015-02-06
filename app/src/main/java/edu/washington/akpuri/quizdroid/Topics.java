package edu.washington.akpuri.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Topics extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        Button math = (Button) findViewById(R.id.mathButton);
        Button physics = (Button) findViewById(R.id.physicsButton);
        Button marvel = (Button) findViewById(R.id.marvelButton);
        final Intent overview = new Intent(Topics.this, QuestionTopicOverview.class);
        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overview.putExtra("subject", "Math");
                Topics.this.startActivity(overview);
            }
        });
        physics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overview.putExtra("subject", "Physics");
                Topics.this.startActivity(overview);
            }
        });
        marvel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overview.putExtra("subject", "Marvel Super Heroes");
                Topics.this.startActivity(overview);
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
