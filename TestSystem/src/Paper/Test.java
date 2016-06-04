package Paper;
import java.util.LinkedList;
import java.util.List;

import Answer.Answer;
import Question.Question;
public class Test extends Survey{
    private int totalScore;
    protected String pageType = "Test";
    List<Answer> answerList = new LinkedList<Answer>();
    public Test(){
    	super();
    }
    public void setTotalScore(){
        totalScore = 0;
        int questionCount = questionList.size();
        int answerCount = answerList.size();
        if(questionCount!=answerCount){
            return;
        }
        for(int i=0;i<questionCount;i++){
            Question currentQuestion = questionList.get(i);
            Answer currentAnswer = answerList.get(i);
            if (currentQuestion.matchAnswer(currentAnswer)){
                totalScore += currentQuestion.getScore();
            }
        }
    }
    public void setTotalScore(int totalscore){
    	totalScore = totalscore;
    }

    public int getTotalScore(){
        return totalScore;
    }

    @Override
    public String getType() {
        return "Test";
    }
	public void setPapaerType(String type){
		pageType = "Test";
	}
}
