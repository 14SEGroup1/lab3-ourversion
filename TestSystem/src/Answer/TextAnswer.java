package Answer;

/**
 * Created by Qizixi on 5/29/2016.
 */
public abstract class TextAnswer implements Answer{
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String inputAnswer) {
        this.answer = inputAnswer;
    }

    @Override
    public boolean match(Answer matchAnswer) {
        return answer.equals(matchAnswer.getAnswer());
    }

    @Override
    public abstract String getType();
}
