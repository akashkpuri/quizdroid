package edu.washington.akpuri.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class QuestionReview extends ActionBarActivity {
    String subject;
    int total;
    int correct;
    Question[] questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_review);

        Intent questionAsk = getIntent();
        total= questionAsk.getIntExtra("total", 0);
        correct = questionAsk.getIntExtra("correct", 0);
        subject = questionAsk.getStringExtra("subject");
        int guessIndex = questionAsk.getIntExtra("guessIndex", 0);
        QuestionWrapper wrap = (QuestionWrapper) questionAsk.getSerializableExtra("questionArray");
        questions = wrap.getContents();
        Question prevQuestion = questions[total - 1];
        TextView sub = (TextView) findViewById(R.id.reviewSubject);
        sub.setText(subject);
        TextView reviewOverall = (TextView) findViewById(R.id.reviewOverall);
        reviewOverall.setText("You have answered " + correct +
                              " out of " + total + " questions correctly");
        String guessed = prevQuestion.choices[guessIndex];
        String actual = prevQuestion.choices[prevQuestion.answer];
        TextView reviewGuess = (TextView) findViewById(R.id.reviewGuess);
        reviewGuess.setText("You chose: " + guessed);
        TextView reviewActual = (TextView) findViewById(R.id.reviewActual);
        reviewActual.setText("The answer was: " + actual);


        Button reviewNext = (Button) findViewById(R.id.reviewNext);
        if (total == 3) {
            reviewNext.setText("Finish");
            reviewNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent restart = new Intent(QuestionReview.this, Topics.class);
                    startActivity(restart);
                    QuestionReview.this.finish();
                }
            });
        } else {
            reviewNext.setText("Next Question");
            reviewNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextQuestion = new Intent(QuestionReview.this, QuestionAsk.class);
                    nextQuestion.putExtra("subject", subject);
                    nextQuestion.putExtra("total", total);
                    nextQuestion.putExtra("correct", correct);
                    QuestionWrapper qwrapper = new QuestionWrapper(questions);
                    nextQuestion.putExtra("questionArray", qwrapper);
                    QuestionReview.this.startActivity(nextQuestion);
                    QuestionReview.this.finish();
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question_review, menu);
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
