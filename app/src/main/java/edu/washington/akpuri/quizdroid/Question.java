package edu.washington.akpuri.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;

//My custom domain object representing a Quiz
public class Question implements Serializable {
    public int answer;
    public String question;
    public ArrayList<String> choices;

    public Question(){
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice(int index) {
        return choices.get(index);
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public String getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }

    public String getStringAnswer() { return choices.get(answer);}
}
