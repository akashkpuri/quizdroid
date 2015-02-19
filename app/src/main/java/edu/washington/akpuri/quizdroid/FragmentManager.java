package edu.washington.akpuri.quizdroid;

//import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.support.v4.app.FragmentTransaction;

public class FragmentManager extends ActionBarActivity {
    private static String subject;
    private static int totalQuestions;
    private static int correctQuestions;
    private static String guessed;
    private static String actual;
    private static QuizApp quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_manager);
        quiz = QuizApp.getInstance();
        quiz.setCurrentQuestion(0);
        subject = quiz.getSubject();
        totalQuestions = 0;
        correctQuestions = 0;
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom)
                    .add(R.id.container, new SubjectOverview())
                    .commit();
        }


    }

    //When a user selects a radio button, enable to view of the submit question
    public void qRadio(View v) {
        Button next = (Button) findViewById(R.id.nextQuestion);
        next.setEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fragment_manager, menu);
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

    /**
     * Fragment representing the overview of the subject. Tells
     * the user how many questions there will be and what the
     * subject is a all about.
     */
    public static class SubjectOverview extends Fragment {

        public SubjectOverview() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.subject_overview, container, false);
            TextView title = (TextView) rootView.findViewById(R.id.questionTopicSubject);
            title.setText(subject);
            TextView subjectOverview = (TextView) rootView.findViewById(R.id.subjectOverview);
            subjectOverview.setText(quiz.getLongO());
            Button beginQuestion = (Button) rootView.findViewById(R.id.beginQuestion);
            beginQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.abc_slide_in_top,R.anim.abc_slide_out_bottom);
                    ft.replace(R.id.container, new AskQuestion());
                    //ft.addToBackStack();
                    ft.commit();
                }
            });
            return rootView;
        }
    }

    public static class AskQuestion extends Fragment {
        public AskQuestion() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.ask_question, container, false);
            TextView sub = (TextView) rootView.findViewById(R.id.questionAskSubject);
            sub.setText(subject);
            quiz.setCurrentQuestion(totalQuestions);
            TextView status = (TextView) rootView.findViewById(R.id.questionStatus);
            status.setText(quiz.getShortO());
            final Question curQuestion = quiz.getCurQuestion();
            TextView actualQuestion = (TextView) rootView.findViewById(R.id.actualQuestion);
            actualQuestion.setText(quiz.getQuestionString());
            RadioButton first = (RadioButton) rootView.findViewById(R.id.q1);
            RadioButton second = (RadioButton) rootView.findViewById(R.id.q2);
            RadioButton third = (RadioButton) rootView.findViewById(R.id.q3);
            RadioButton fourth = (RadioButton) rootView.findViewById(R.id.q4);
            first.setText(quiz.getQuestionChoice(0));
            second.setText(quiz.getQuestionChoice(1));
            third.setText(quiz.getQuestionChoice(2));
            fourth.setText(quiz.getQuestionChoice(3));
            Button submit = (Button) rootView.findViewById(R.id.nextQuestion);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioGroup radio = (RadioGroup) rootView.findViewById(R.id.radioGroup);
                    int chosenButtonID = radio.getCheckedRadioButtonId();
                    RadioButton chosenButton = (RadioButton) radio.findViewById(chosenButtonID);
                    //turn the tag of the radio button into an int
                    int idx = Integer.parseInt((String) chosenButton.getTag());
                    guessed = quiz.getQuestionChoice(idx);
                    actual = quiz.getQuestionAnswer();
                    if (idx == quiz.getAnswer()) {
                        correctQuestions++;
                    }
                    totalQuestions++;
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.abc_slide_in_top,R.anim.abc_slide_out_bottom);
                    ft.replace(R.id.container, new ReviewQuestion());
                    //ft.addToBackStack();
                    ft.commit();
                }
            });
            return rootView;
        }
    }

    public static class ReviewQuestion extends Fragment {
        public ReviewQuestion() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.review_question, container, false);

            TextView reviewOverall = (TextView) rootView.findViewById(R.id.reviewOverall);
            reviewOverall.setText("You have answered " + correctQuestions +
                    " out of " + totalQuestions + " questions correctly");
            TextView reviewGuess = (TextView) rootView.findViewById(R.id.reviewGuess);
            reviewGuess.setText("You chose: " + guessed);
            TextView reviewActual = (TextView) rootView.findViewById(R.id.reviewActual);
            reviewActual.setText("The answer was: " + actual);
            Button reviewNext = (Button) rootView.findViewById(R.id.reviewNext);
            if (totalQuestions == 3) {
                reviewNext.setText("Finish");
                reviewNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent restart = new Intent(getActivity(), Topics.class);
                        startActivity(restart);
                    }
                });
            } else {
                reviewNext.setText("Next Question");
                reviewNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top);
                        ft.replace(R.id.container, new AskQuestion());
                        //ft.addToBackStack();
                        ft.commit();
                    }
                });
            }
            return rootView;
        }
    }
}
