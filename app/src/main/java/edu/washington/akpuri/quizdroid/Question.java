package edu.washington.akpuri.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    public int answer;
    public String question;
    public String[] choices = new String[4];

    public Question(String question, String first, String second, String third, String fourth, int answer) {
        this.question = question;
        choices[0] = first;
        choices[1] = second;
        choices[2] = third;
        choices[3] = fourth;
        this.answer = answer;
    }

    public String get(int index) {
        return choices[index];
    }
}
