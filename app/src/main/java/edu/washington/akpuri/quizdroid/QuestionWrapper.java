package edu.washington.akpuri.quizdroid;

import java.io.Serializable;

/**
 * Created by Akash on 2/3/2015.
 */
public class QuestionWrapper implements Serializable {

    private Question[] QuestionContents;

    public QuestionWrapper(Question[] questions) {
        QuestionContents = questions;
    }

    public Question[] getContents() {
        return QuestionContents;
    }
}
