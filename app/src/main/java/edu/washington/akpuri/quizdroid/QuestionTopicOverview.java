package edu.washington.akpuri.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class QuestionTopicOverview extends ActionBarActivity {
    Question[] questions = new Question[3];
    String subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_topic_overview);

        Intent topic = getIntent();
        subject = topic.getStringExtra("subject");
        TextView header = (TextView) findViewById(R.id.questionTopicSubject);
        header.setText(subject);
        TextView overview = (TextView) findViewById(R.id.subjectOverview);
        Question q1;
        Question q2;
        Question q3;
        if (subject.equals("Math")) {
            overview.setText("Math is a really useful thing to learn but it can be pretty "  +
                    "boring. Basically math is just using numbers and stuff. Also, it can be " +
                    "difficult. Think you got what it takes to answer some really tough questions?");
            //Question(String question, String first, String second, String third, String fourth, int answer)
            q1 = new Question("What is the square root of 16?", "2", "3", "4", "IDK", 2);
            q2 = new Question("4 + 4 is equal to?", "4", "0", "4 + 4", "8", 3);
            q3 = new Question("What is the hardest math class at UW?", "Math 112", "Math 125", "Math 300", "Math 455", 0);
        } else if (subject.equals("Physics")) {
            overview.setText("Physics is like math but even worse. No for real, it sucks. " +
                    "You basically take math and try to solve real world science problems " +
                    "but really it is impossible to do. If you want to, try out these physics " +
                    "questions but I really do not recommend it.");
            q1 = new Question("How fast is really fast?", "High velocity", "Large momentum",
                    "Big inertia", "Good amount of speed", 3);
            q2 = new Question("If a current is going through a coil, how can you tell which" +
                    " direction the magnetic field is?", "You can't silly", "Right hand rule",
                    "Left hand rule", "What is a coil?", 1);
            q3 = new Question("What has the greatest force?", "Earthquake", "Bullet shot by a Shotgun",
                    "Beastquake 2.0", "Beastquake 1.0", 2);
        } else { //Subject == Mavrvel Super Heroes
            overview.setText("Marvel Super Heroes are badasses that save the world from certain" +
                    "doom from really mean badguys that all have large egos and unnecessary" +
                    "greed.");
            q1 = new Question("Who is the most powerful?", "Green Lantern", "Thor", "Iron Man",
                    "The Hulk", 0);
            q2 = new Question("Who has the coolest costume?", "Spiderman", "WonderWoman", "Batman",
                    "The archer guy", 1);
            q3 = new Question("Coolest Lightsaber Color?", "Green - Like every Jedi", "Red - All Sith",
                    "Blue - Good Annie", "Purple - Sanmuel L.", 3);
        }
        questions[0] = q1;
        questions[1] = q2;
        questions[2] = q3;
        final Intent questionAsk = new Intent(QuestionTopicOverview.this, QuestionAsk.class);
        questionAsk.putExtra("subject", subject);
        QuestionWrapper qwrapper = new QuestionWrapper(questions);
        questionAsk.putExtra("questionArray", qwrapper);
        questionAsk.putExtra("total", 0);
        questionAsk.putExtra("correct", 0);
        Button startQuestion;
        startQuestion = (Button) findViewById(R.id.beginQuestion);
        if (startQuestion != null) {
            startQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    QuestionTopicOverview.this.startActivity(questionAsk);
                    QuestionTopicOverview.this.finish();
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question_topic_overview, menu);
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
