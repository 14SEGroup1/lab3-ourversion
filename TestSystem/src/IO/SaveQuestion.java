package IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import Answer.Answer;
import Paper.Paper;
import Paper.Paper;
import Paper.Test;
import Question.DecideQuestion;
import Question.ItemQuestion;
import Question.MapQuestion;
import Question.ChoiceQuestion;
import Question.EssayQuestion;
import Question.RankQuestion;
import Question.ShortEssayQuestion;
import Question.Question;

public class SaveQuestion {
	public SaveQuestion(){
	}
	public Element Save(Paper page , Element root){
		String type = page.getType();
		List<Question> questionList = page.get_QuestionList();
		if(type.equals("Test")){
			Test pagetest = (Test)page;
			List<Answer> answerList = pagetest.getAnswerList();
		}
		Element questions = new Element("questions");
		for(int i=0; i<questionList.size(); i++){
			Question question = questionList.get(i);
			Element qe = null;
			switch(question.getQuestionType()){
				case "decision": qe = this.saveDecisionQuestion(question);break;
				case "choice": qe = this.saveItemQuestion(question);break;
				case "essay": qe = this.saveEssayQuestion(question);break;
				case "rank": qe = this.saveItemQuestion(question);break;
				case "map": qe = this.saveMapQuestion(question);break;
				case "short essay": qe = this.saveShortEssayQuestion(question);break;
			}
			questions.addContent(qe);
		}
		return questions;
	}
	public Element saveDecisionQuestion(Question question){
		DecideQuestion decisideQuestion = (DecideQuestion)question;
		Element ret = new Element("question");
		ret.setAttribute("type", question.getQuestionType()+"");
		ret.setAttribute("isScore", "1");
		int anwser = 1;
		if(decisideQuestion.getAnswer() == null)
			anwser = 0;
		ret.setAttribute("answer", anwser+"");
		Element prompt = new Element("prompt");
		prompt.setText(question.getPrompt());
		ret.addContent(prompt);
		if(decisideQuestion.getAnswer() != null){
			Answer an = decisideQuestion.getAnswer();
			Element answerElement = new Element("answer");
			answerElement.setText(an.getAnswer());
			ret.addContent(answerElement);
		}
		return ret;
	}

	public Element saveShortEssayQuestion(Question question){
		ShortEssayQuestion shortEssayQuestion = (ShortEssayQuestion)question;
		Element ret = new Element("question");
		ret.setAttribute("type", question.getQuestionType()+"");
		ret.setAttribute("isScore", "1");
		int anwser = 1;
		if(shortEssayQuestion.getAnswer() == null)
			anwser = 0;
		ret.setAttribute("answer", anwser+"");
		Element prompt = new Element("prompt");
		prompt.setText(question.getPrompt());
		ret.addContent(prompt);
		if(shortEssayQuestion.getAnswer() != null){
			Answer an = shortEssayQuestion.getAnswer();
			Element answerElement = new Element("answer");
			answerElement.setText(an.getAnswer());
			ret.addContent(answerElement);
		}

		ret.addContent(new Element("score").setText(question.getScore()+""));
		return ret;
	}

	public Element saveItemQuestion(Question question){
		ItemQuestion item = (ItemQuestion) question;
		Element ret = new Element("question");
		ret.setAttribute("type", question.getQuestionType()+"");
		ret.setAttribute("isScore", "1");
		int anwser = 1;
		if(item.getAnswer() == null)
			anwser = 0;
		ret.setAttribute("answer", anwser+"");
		Element prompt = new Element("prompt");
		prompt.setText(question.getPrompt());
		ret.addContent(prompt);
		List<String> items = item.getAllItems();
		Element itemElement = new Element("items");
		for(int j=0; j<items.size(); j++){
			itemElement.addContent(new Element("item").setText(items.get(j)));
		}
		ret.addContent(itemElement);
		if(item.getAnswer() != null){
			Answer an = item.getAnswer();
			Element answerElement = new Element("answer");
			answerElement.setText(an.getAnswer());
			ret.addContent(answerElement);
		}

		ret.addContent(new Element("score").setText(question.getScore()+""));
		return ret;
	}

	public Element saveMapQuestion(Question question){
		MapQuestion map = (MapQuestion)question;
		Element ret =new Element("question");
		ret.setAttribute("type", question.getQuestionType()+"");
		ret.setAttribute("isScore", "1");
		int anwser = 1;
		if(map.getAnswer() == null)
			anwser = 0;
		ret.setAttribute("answer", anwser+"");
		Element prompt = new Element("prompt");
		prompt.setText(question.getPrompt());
		ret.addContent(prompt);
		List<String> side1 = map.getLeftItem();
		Element item1 = new Element("side1");
		for(int j=0; j<side1.size(); j++){
			item1.addContent(new Element("left").setText(side1.get(j)));
		}
		ret.addContent(item1);
		List<String> side2 = map.getRightItem();
		Element item2 = new Element("side2");
		for(int j=0; j<side2.size(); j++){
			item2.addContent(new Element("right").setText(side2.get(j)));
		}
		ret.addContent(item2);
		if(map.getAnswer() != null){
			Answer an = map.getAnswer();
			Element answerElement = new Element("answer");
			answerElement.setText(an.getAnswer());
			ret.addContent(answerElement);
		}

		ret.addContent(new Element("score").setText(question.getScore()+""));
		return ret;
	}

	public Element saveEssayQuestion(Question question){
		Element ret =new Element("question");
		ret.setAttribute("type", question.getQuestionType()+"");
		ret.setAttribute("isScore", "0");
		ret.setAttribute("answer", "0");
		Element prompt = new Element("prompt");
		prompt.setText(question.getPrompt());
		ret.addContent(prompt);
		return ret;
	}
}
