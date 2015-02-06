package edu.washington.akpuri.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;



public class QuestionAsk extends ActionBarActivity {
    int total;
    int correct;
    Question curQuestion;
    String subject;
    Question[] questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_ask);

        Intent topicOverview = getIntent();
        QuestionWrapper wrap = (QuestionWrapper) topicOverview.getSerializableExtra("questionArray");
        questions = wrap.getContents();
        subject = topicOverview.getStringExtra("subject");
        total = topicOverview.getIntExtra("total", 0);
        correct = topicOverview.getIntExtra("correct", 0);
        TextView sub = (TextView) findViewById(R.id.questionAskSubject);
        sub.setText(subject);
        TextView status = (TextView) findViewById(R.id.questionStatus);
        status.setText("Question " + (total + 1) + " of 3");
        curQuestion = questions[total];
        TextView actualQuestion = (TextView) findViewById(R.id.actualQuestion);
        actualQuestion.setText(curQuestion.question);
        RadioButton first = (RadioButton) findViewById(R.id.q1);
        RadioButton second = (RadioButton) findViewById(R.id.q2);
        RadioButton third = (RadioButton) findViewById(R.id.q3);
        RadioButton fourth = (RadioButton) findViewById(R.id.q4);
        first.setText(curQuestion.choices[0]);
        second.setText(curQuestion.choices[1]);
        third.setText(curQuestion.choices[2]);
        fourth.setText(curQuestion.choices[3]);


    }

    //When a user selects a radio button, enable to view of the submit question
    public void qRadio(View v) {
        Button next = (Button) findViewById(R.id.nextQuestion);
        next.setEnabled(true);
    }

    //When the user hits the sumbit button, send the to the question summary page
    public void questionSubmit(View v) {
        RadioGroup radio = (RadioGroup) findViewById(R.id.radioGroup);
        int chosenButtonID = radio.getCheckedRadioButtonId();
        RadioButton chosenButton = (RadioButton) radio.findViewById(chosenButtonID);
        //turn the tag of the radio button into an int
        int idx = Integer.parseInt((String) chosenButton.getTag());
        if (idx == curQuestion.answer) {
            correct++;
        }
        total++;
        Intent questionSummary = new Intent(QuestionAsk.this, QuestionReview.class);
        questionSummary.putExtra("subject", subject);
        questionSummary.putExtra("total", total);
        questionSummary.putExtra("correct", correct);
        QuestionWrapper qwrapper = new QuestionWrapper(questions);
        questionSummary.putExtra("questionArray", qwrapper);
        questionSummary.putExtra("guessIndex", idx);
        QuestionAsk.this.startActivity(questionSummary);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question_ask, menu);
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
