package Question;

/**
 * Created by Qizixi on 5/29/2016.
 */
public class DecideQuestion extends Question{

    @Override
    public String getQuestion() {
        return "T/F: "+prompt+"\n"+"T. right\nF. false";
    }

    @Override
    public String getQuestionType() {
        return "decision";
    }
}
