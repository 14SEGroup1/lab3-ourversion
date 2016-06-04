package Question;

/**
 * Created by Qizixi on 5/29/2016.
 */
public class EssayQuestion extends Question{

    @Override
    public String getQuestion() {
        return "Essay: "+prompt+"\n";
    }

    @Override
    public String getQuestionType() {
        return "essay";
    }
}
