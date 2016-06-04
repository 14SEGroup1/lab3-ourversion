package Control;
import Answer.Answer;
import Paper.Test;
import Paper.Survey;
import Question.Question;
import Answer.AnswerFactory;
import IO.Read;
import IO.ReadQuestion;
import IO.Save;
import IO.SaveQuestion;
import java.awt.*;
import java.util.*;
import java.util.List;

public class PaperModifier {
	Save save = new Save();
	Read read = new Read();
	private Survey currentPaper;

	public void loadPaper(String papername,String type){
		currentPaper = (Survey)read.readPage(papername);
	}

	public void savePaper(){
		save.savePage(currentPaper, "TestWriter");
	}

	public List<String> getQuestionList(){
		return currentPaper.getQuestionList();
	}

	public void modifyQuestion(int index,String prompt){
		Question currentQuestion = currentPaper.getQuestion(index);
		currentQuestion.setPrompt(prompt);
		currentPaper.setQuestion(index,currentQuestion);
		savePaper();
		return;
	}


}
