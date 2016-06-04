package Control;

import Answer.Answer;
import Paper.Test;
import Answer.AnswerFactory;
import Paper.Survey;
import Question.Question;
import IO.Read;
import IO.ReadQuestion;
import IO.Save;
import IO.SaveQuestion;
import java.util.List;

/**
 * Created by Qizixi on 6/1/2016.
 */
public class PaperFiller{
	Save save = new Save();
    Read read = new Read();
    Survey currentPaper;
    int questionIndex = 0;
    String type;

    public void loadPaper(String papername,String type){
    	currentPaper = read.readPage(papername);
        this.type = type;
    	//load currentpaper from io
    }

    public void setPaperAttender(String name){
        currentPaper.setAttender(name);
    }

    public List<String> getQuestionList(){
        return currentPaper.getQuestionList();
    }

    public void answerQuestion(String answer){
        Question currentQuestion = currentPaper.getQuestion(questionIndex++);
        Answer currentAnswer = AnswerFactory.produceAnswer(currentQuestion.getQuestionType(),answer);
        currentPaper.addAnswer(currentAnswer);
    }

    public boolean hasNextQuestion(){
        if (questionIndex<currentPaper.getQuestionNum()){
            return true;
        }else{
            return false;
        }
    }

    public void savePaper(){
        save.savePage(currentPaper, "Tester",type);
    }

}
