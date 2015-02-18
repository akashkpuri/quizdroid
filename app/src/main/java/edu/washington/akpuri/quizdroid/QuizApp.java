package edu.washington.akpuri.quizdroid;

import android.app.Application;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Akash on 2/16/2015.
 */
public class QuizApp extends Application implements TopicRepository{
    private static QuizApp instance;
    private static ArrayList<Topic> topics;
    private static Topic currentTopic;
    private static int currentQuestion;

    public QuizApp() {
        if (instance == null) {
            instance = this;
        }
    }

    public static QuizApp getInstance() {
        if (instance == null) {
            instance = new QuizApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("QuizApp", "QuizApp was successfully created!");

        topics = new ArrayList<Topic>();
        //Set up the math topic with 3 questions
        Topic math = createMath();
        //Set up the physics topic with 3 questions
        Topic physics = createPhysics();
        //Set up the marvel topic with 3 questions
        Topic marvel = createMarvel();

        topics.add(math);
        topics.add(physics);
        topics.add(marvel);
    }

    public Topic createMath() {
        Topic math = new Topic();
        Question mq1 = new Question();
        mq1.setQuestion("What is the square root of 16?");
        ArrayList<String> mq1choices = new ArrayList<String>();
        mq1choices.add("2");
        mq1choices.add("3");
        mq1choices.add("4");
        mq1choices.add("IDK");
        mq1.setChoices(mq1choices);
        mq1.setAnswer(3);
        Question mq2 = new Question();
        mq2.setQuestion("4 + 4 is equal to which of the following?");
        ArrayList<String> mq2choices = new ArrayList<String>();
        mq2choices.add("7");
        mq2choices.add("8");
        mq2choices.add("Eight");
        mq2choices.add("9");
        mq2.setChoices(mq2choices);
        mq2.setAnswer(1);
        Question mq3 = new Question();
        mq3.setQuestion("What is the hardest math class at UW?");
        ArrayList<String> mq3choices = new ArrayList<String>();
        mq3choices.add("Math 125");
        mq3choices.add("Math 369");
        mq3choices.add("Math 450");
        mq3choices.add("Math 112");
        mq3.setChoices(mq3choices);
        mq3.setAnswer(3);
        math.setTitle("Math");
        ArrayList<Question> mathQuestions = new ArrayList<Question>();
        mathQuestions.add(mq1);
        mathQuestions.add(mq2);
        mathQuestions.add(mq3);
        math.setShortO("Math: There are " + mathQuestions.size() + " total questions");
        math.setLongO("Math is a really useful thing to learn but it can be pretty " +
                "boring. Basically math is just using numbers and stuff. Also, " +
                "it can be difficult. Think you got what it takes to answer some " +
                "really tough questions?");
        math.setQuestions(mathQuestions);
        return math;
    }

    public Topic createPhysics() {
        Topic physics = new Topic();
        Question pq1 = new Question();
        pq1.setQuestion("How fast is really fast?");
        ArrayList<String> pq1choices = new ArrayList<String>();
        pq1choices.add("Marshawn Lynch: Stupid Fast");
        pq1choices.add("High Velocity");
        pq1choices.add("Large Inertia");
        pq1choices.add("Big Momentum");
        pq1.setChoices(pq1choices);
        pq1.setAnswer(0);
        Question pq2 = new Question();
        pq2.setQuestion("If a current is going through a coil, how can you tell which" +
                " direction the magnetic field is?");
        ArrayList<String> pq2choices = new ArrayList<String>();
        pq2choices.add("Left-Hand Rule");
        pq2choices.add("Right-Hand Rule");
        pq2choices.add("Both the Above");
        pq2choices.add("You can't use your hands silly");
        pq2.setChoices(pq2choices);
        pq2.setAnswer(1);
        Question pq3 = new Question();
        pq3.setQuestion("Which of these has the largest amount of force?");
        ArrayList<String> pq3choices = new ArrayList<String>();
        pq3choices.add("Tsunami");
        pq3choices.add("BeastQuake 2.0");
        pq3choices.add("Avalanche");
        pq3choices.add("BeastQuake 1.0");
        pq3.setChoices(pq3choices);
        pq3.setAnswer(3);
        ArrayList<Question> physicQuestions = new ArrayList<Question>();
        physicQuestions.add(pq1);
        physicQuestions.add(pq2);
        physicQuestions.add(pq3);
        physics.setShortO("Physics: There are " + physicQuestions.size() + " total questions");
        physics.setLongO("Physics is like math but even worse. No for real, it sucks. " +
                "You basically take math and try to solve real world science " +
                "problems but really it is impossible to do. If you want to, try " +
                "out these physics questions but I really do not recommend it.");
        physics.setQuestions(physicQuestions);
        physics.setTitle("Physics");
        return physics;
    }

    public Topic createMarvel() {
        Topic marvel = new Topic();
        Question marq1 = new Question();
        marq1.setQuestion("Which of the below superheroes is the most powerful?");
        ArrayList<String> marq1choices = new ArrayList<String>();
        marq1choices.add("King Kong");
        marq1choices.add("Green Lantern");
        marq1choices.add("Darth Vader");
        marq1choices.add("SuperMan");
        marq1.setChoices(marq1choices);
        marq1.setAnswer(0);
        Question marq2 = new Question();
        ArrayList<String> marq2choices = new ArrayList<String>();
        marq2.setQuestion("Which of the below superheroes has the coolest costume?");
        marq2choices.add("The Flash");
        marq2choices.add("Batman");
        marq2choices.add("WonderWoman");
        marq2choices.add("SpiderMan");
        marq2.setChoices(marq2choices);
        marq2.setAnswer(2);
        Question marq3 = new Question();
        ArrayList<String> marq3choices = new ArrayList<String>();
        marq3 .setQuestion("What is the absolute best lightsaber color?");
        marq3choices.add("Green - Like Every Jedi Ever");
        marq3choices.add("Red - Like Actually Every Sith Ever");
        marq3choices.add("Blue - Good Annie");
        marq3choices.add("Purple - Samuel L.");
        marq3.setChoices(marq3choices);
        marq3.setAnswer(3);
        ArrayList<Question> marvelQuestions = new ArrayList<Question>();
        marvelQuestions.add(marq1);
        marvelQuestions.add(marq2);
        marvelQuestions.add(marq3);
        marvel.setTitle("Marvel Super Heroes");
        marvel.setShortO("Marvel: There are " + marvelQuestions.size() + " total questions");
        marvel.setLongO("Marvel Super Heroes are badasses that save the world from " +
                "certain doom from really mean badguys that all have large egos " +
                "and unnecessary greed.");
        marvel.setQuestions(marvelQuestions);
        return marvel;
    }

    @Override
    public void setCurrentTopic(int currentTopic) {
        QuizApp.currentTopic = topics.get(currentTopic);
    }

    @Override
    public Question getCurQuestion() {
        return currentTopic.getQuestion(currentQuestion);
    }

    @Override
    public ArrayList<Question> getAllQuestions() {
        return currentTopic.getAllQuestions();
    }

    @Override
    public String getSubject() {
        return currentTopic.getTitle();
    }

    @Override
    public String getShortO() {
        return currentTopic.getShortOverview();
    }

    @Override
    public String getLongO() {
        return currentTopic.getLongOverview();
    }

    @Override
    public String getQuestionString() {
        return currentTopic.getQuestion(currentQuestion).getQuestion();
    }

    @Override
    public int getAnswer() {
        return currentTopic.getQuestion(currentQuestion).getAnswer();
    }

    @Override
    public ArrayList<String> getChoices() {
        return currentTopic.getQuestion(currentQuestion).getChoices();
    }

    @Override
    public void setCurrentQuestion(int index) {
        this.currentQuestion = index;
    }

    @Override
    public String getQuestionChoice(int index) {
        return getCurQuestion().getChoice(index);
    }

    @Override
    public String getQuestionAnswer() { return getCurQuestion().getStringAnswer();}

    @Override
    public ArrayList<Topic> getTopics() {
        return topics;
    }
}
