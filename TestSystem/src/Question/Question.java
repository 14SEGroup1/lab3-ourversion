package Question;

import Answer.Answer;
import Answer.AnswerFactory;
/**
 * Created by Qizixi on 5/29/2016.
 */
public abstract class Question {

    protected String prompt;
    protected Answer correctAnswer;
    protected int score;

    public abstract String getQuestion();
    public abstract String getQuestionType();

    public void setPrompt(String prompt){
        this.prompt = prompt;
    }

    public String getPrompt(){
        return prompt;
    }

    public void setCorrectAnswer(Answer correctAnswer){
        this.correctAnswer = correctAnswer;
    }

    public boolean matchAnswer(Answer inputAnswer){
        return correctAnswer.match(inputAnswer);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    public Answer getAnswer(){
        return correctAnswer;
    }

    public void setAnswer(String input){
        Answer answer = AnswerFactory.produceAnswer(this.getQuestionType(),input);
        setCorrectAnswer(answer);
    }
}
