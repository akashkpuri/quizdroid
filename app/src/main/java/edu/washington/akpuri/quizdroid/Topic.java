package edu.washington.akpuri.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Akash on 2/16/2015.
 * Custom Domain object representing a question topic
 * POJO
 */
public class Topic implements Serializable{
    private String title;
    private String shortOverview;
    private String longOverview;
    private ArrayList<Question> questions;

    public Topic(){}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortO(String shortOverview) {
        this.shortOverview = shortOverview;
    }

    public void setLongO(String longOverview) {
        this.longOverview = longOverview;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getQuestionAmount() {
        return questions.size();
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public ArrayList<Question> getAllQuestions() {
        return questions;
    }

    public String getTitle() { return title;}

    public String getShortOverview() { return shortOverview;}

    public String getLongOverview() { return longOverview;}
}
