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
    private static Question[] questions;
    private static int totalQuestions;
    private static int correctQuestions;
    private static String guessed;
    private static String actual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_manager);
        Intent getSubject = getIntent();
        subject = getSubject.getStringExtra("subject");
        questions = new Question[3];
        Question q1;
        Question q2;
        Question q3;

        if (subject.equals("Math")) {
            //Question(String question, String first, String second, String third, String fourth, int answer)
            q1 = new Question("What is the square root of 16?", "2", "3", "4", "IDK", 2);
            q2 = new Question("4 + 4 is equal to?", "4", "0", "4 + 4", "8", 3);
            q3 = new Question("What is the hardest math class at UW?", "Math 112", "Math 125", "Math 300", "Math 455", 0);
        } else if (subject.equals("Physics")) {
            q1 = new Question("How fast is really fast?", "High velocity", "Large momentum",
                    "Big inertia", "Good amount of speed", 3);
            q2 = new Question("If a current is going through a coil, how can you tell which" +
                    " direction the magnetic field is?", "You can't silly", "Right hand rule",
                    "Left hand rule", "What is a coil?", 1);
            q3 = new Question("What has the greatest force?", "Earthquake", "Bullet shot by a Shotgun",
                    "Beastquake 2.0", "Beastquake 1.0", 2);
        } else { //Subject == Mavrvel Super Heroes
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
            String overview;
            if (subject.equals("Math")) {
                overview = "Math is a really useful thing to learn but it can be pretty " +
                        "boring. Basically math is just using numbers and stuff. Also, " +
                        "it can be difficult. Think you got what it takes to answer some " +
                        "really tough questions?";
            } else if(subject.equals("Physics")) {
                overview = "Physics is like math but even worse. No for real, it sucks. " +
                        "You basically take math and try to solve real world science " +
                        "problems but really it is impossible to do. If you want to, try " +
                        "out these physics questions but I really do not recommend it.";
            } else { // subject == "Marvel Super Heroes"
                overview = "Marvel Super Heroes are badasses that save the world from " +
                        "certain doom from really mean badguys that all have large egos " +
                        "and unnecessary greed.";
            }
            TextView title = (TextView) rootView.findViewById(R.id.questionTopicSubject);
            title.setText(subject);
            TextView subjectOverview = (TextView) rootView.findViewById(R.id.subjectOverview);
            subjectOverview.setText(overview);
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
            TextView status = (TextView) rootView.findViewById(R.id.questionStatus);
            status.setText("Question " + (totalQuestions + 1) + " of 3");
            final Question curQuestion = questions[totalQuestions];
            TextView actualQuestion = (TextView) rootView.findViewById(R.id.actualQuestion);
            actualQuestion.setText(curQuestion.question);
            RadioButton first = (RadioButton) rootView.findViewById(R.id.q1);
            RadioButton second = (RadioButton) rootView.findViewById(R.id.q2);
            RadioButton third = (RadioButton) rootView.findViewById(R.id.q3);
            RadioButton fourth = (RadioButton) rootView.findViewById(R.id.q4);
            first.setText(curQuestion.choices[0]);
            second.setText(curQuestion.choices[1]);
            third.setText(curQuestion.choices[2]);
            fourth.setText(curQuestion.choices[3]);
            Button submit = (Button) rootView.findViewById(R.id.nextQuestion);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioGroup radio = (RadioGroup) rootView.findViewById(R.id.radioGroup);
                    int chosenButtonID = radio.getCheckedRadioButtonId();
                    RadioButton chosenButton = (RadioButton) radio.findViewById(chosenButtonID);
                    //turn the tag of the radio button into an int
                    int idx = Integer.parseInt((String) chosenButton.getTag());
                    guessed = curQuestion.choices[idx];
                    actual = curQuestion.choices[curQuestion.answer];
                    if (idx == curQuestion.answer) {
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
