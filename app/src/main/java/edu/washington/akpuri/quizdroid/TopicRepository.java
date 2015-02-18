package edu.washington.akpuri.quizdroid;

import java.util.ArrayList;

/**
 * Created by Akash on 2/16/2015.
 */
public interface TopicRepository {
    //methods

    public Question getCurQuestion();

    public ArrayList<Question> getAllQuestions();

    //used to get title like MATH
    public String getSubject();

    //
    public String getShortO();

    public String getLongO();

    //used to get actual question string like "What is 4 + 4"
    public String getQuestionString();

    public int getAnswer();

    public ArrayList<String> getChoices();

    //Used
    public void setCurrentTopic(int index);

    //Not Used
    public void setCurrentQuestion(int index);

    //Used
    public String getQuestionChoice(int index);

    //Used
    public String getQuestionAnswer();

    public ArrayList<Topic> getTopics();

}
