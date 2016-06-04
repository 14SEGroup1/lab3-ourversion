package Question;

/**
 * Created by Qizixi on 5/29/2016.
 */
public class ShortEssayQuestion extends Question{

    @Override
    public String getQuestion() {
        return "Short essay: "+prompt+"\n";
    }

    @Override
    public String getQuestionType() {
        return "essay";
    }
}
