package Control;

import Answer.Answer;
import Paper.Survey;
import Paper.Test;
import Question.QuestionFactory;
import Answer.AnswerFactory;
import Question.Question;
import View.ControlPanel;
import View.StartFrame;

/**
 * Created by Qizixi on 5/29/2016.
 */
public class TestEntry {

    public static void main(String[] args){
        ControlPanel newPanel = new ControlPanel();
        new StartFrame(newPanel);
        return;
    }
}
