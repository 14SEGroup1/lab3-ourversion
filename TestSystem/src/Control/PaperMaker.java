package Control;

import java.util.List;

import Answer.Answer;
import Answer.AnswerFactory;
import Paper.Survey;
import Paper.Test;
import Question.Question;
import Question.QuestionFactory;
import IO.Read;
import IO.ReadQuestion;
import IO.Save;
import IO.SaveQuestion;
/**
 * Created by Qizixi on 6/1/2016.
 */
public class PaperMaker {
	//private List<String>[] pageNameList;//添加by JDW
	//private List<String> recordName;
    Survey currentPaper;
    Save save = new Save();
    Read read = new Read();
    public void makeNewPaper(String type){
        if (type.equals("Survey")){
            currentPaper = new Survey();
        }else{
            currentPaper = new Test();
        }
    }

    public void setPaperName(String name){
        currentPaper.setPaperName(name);
    }

    public void setAuthor(String author){
        currentPaper.setAuthorName(author);
    }

    public void setTimeLimit(String timeStr){
    	try{
        int time = Integer.parseInt(timeStr);
        currentPaper.setTimeLimit(time);
    	}catch(Exception e){
    		System.out.println("Input error!");
    	}
        
    }


    public void addQuestion(String type,String prompt){
        Question currentQuestion = QuestionFactory.produceQuestion(type,prompt);
        currentPaper.addQuestion(currentQuestion);
    }

    public void addQuestion(String type,String prompt,String[] items){
        Question currentQuestion = QuestionFactory.produceQuestion(type,prompt,items);
        currentPaper.addQuestion(currentQuestion);
    }

    public void addQuestion(String type,String prompt,String[] litems,String[] ritems){
        Question currentQuestion = QuestionFactory.produceQuestion(type,prompt,litems,ritems);
        currentPaper.addQuestion(currentQuestion);
    }

    public void addQuestionWithAnswer(String type,String prompt,int score,String answer){
        Question currentQuestion = QuestionFactory.produceQuestion(type,prompt);
        Answer currentAnswer = AnswerFactory.produceAnswer(type,answer);
        currentQuestion = QuestionFactory.addCorrectAnswer(currentQuestion,currentAnswer,score);
        currentPaper.addQuestion(currentQuestion);
    }

    public void addQuestionWithAnswer(String type,String prompt,String[] items,int score,String answer){
        Question currentQuestion = QuestionFactory.produceQuestion(type,prompt,items);
        Answer currentAnswer = AnswerFactory.produceAnswer(type,answer);
        currentQuestion = QuestionFactory.addCorrectAnswer(currentQuestion,currentAnswer,score);
        currentPaper.addQuestion(currentQuestion);
    }

    public void addQuestionWithAnswer(String type,String prompt,String[] litems,String[] ritems,int score,String answer){
        Question currentQuestion = QuestionFactory.produceQuestion(type,prompt,litems,ritems);
        Answer currentAnswer = AnswerFactory.produceAnswer(type,answer);
        currentQuestion = QuestionFactory.addCorrectAnswer(currentQuestion,currentAnswer,score);
        currentPaper.addQuestion(currentQuestion);
    }

    public void savePaper(){
    	save.savePage(currentPaper, "TestWriter");
    }
}
