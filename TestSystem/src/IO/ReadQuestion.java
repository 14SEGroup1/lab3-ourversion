package IO;
import java.util.List;
import org.jdom.Element;
import Answer.ChoiceAnswer;
import Answer.DecideAnswer;
import Answer.EssayAnswer;
import Answer.RankAnswer;
import Paper.Paper;
import Paper.Survey;
import Paper.Paper;
import Question.ChoiceQuestion;
import Question.DecideQuestion;
import Question.EssayQuestion;
import Question.MapQuestion;
import Question.Question;
import Question.RankQuestion;
import Question.ShortEssayQuestion;

public class ReadQuestion {
	public ReadQuestion(){
	}
	public Survey ReadQuestion(Survey page , Element root){
		Element questions =  root.getChild("questions");
		List<Element> questionList = questions.getChildren();
		for(int i=0; i<questionList.size(); i++){
			Element question = questionList.get(i);
			String type = question.getAttributeValue("type");
			Question q = null;
			switch(type){
				case "decision": q= this.readDecideQuestion(question);
					break;
				case "choice": q =  this.readChoiceQuestion(question);
					break;
				case "essay": q =  this.readEssayQuestion(question);
					break;
				case "rank": q =  this.readRankQuestion(question);
					break;
				case "map": q = this.readMapQuestion(question);
					break;
				case "short essay": q =  this.readTextAnswer(question);
					break;
			}
			page.addQuestion(q);
		}
		return page;
	}
	public Question readDecideQuestion(Element question){
		DecideQuestion decide = new DecideQuestion();
		decide.setPrompt(question.getChildText("prompt"));
		try{
		decide.setScore(Integer.parseInt(question.getChildText("score")));
		}catch(Exception e){
			//System.out.println("error no score");
		}
		if(question.getAttributeValue("answer").equals("1")){
			decide.setAnswer(question.getChildText("answer"));
		}
		return decide;
	}

	public Question readChoiceQuestion(Element question){
		ChoiceQuestion choice = new ChoiceQuestion();
		choice.setPrompt(question.getChildText("prompt"));
		choice.setScore(Integer.parseInt(question.getChildText("score")));
		List<Element> items = question.getChild("items").getChildren();
		for(int i=0; i<items.size(); i++){
			Element item = items.get(i);
			choice.addItem(item.getText());
		}
		if(question.getAttributeValue("answer").equals("1")){
			choice.setAnswer(question.getChildText("answer"));
		}
		return choice;
	}

	public Question readTextAnswer(Element question){
		ShortEssayQuestion text = new ShortEssayQuestion();
		text.setPrompt(question.getChildText("prompt"));
		text.setScore(Integer.parseInt(question.getChildText("score")));
		if(question.getAttributeValue("answer").equals("1")){
			text.setAnswer(question.getChildText("answer"));
		}
		return text;
	}

	public Question readEssayQuestion(Element question){
		EssayQuestion essay = new EssayQuestion();
		essay.setPrompt(question.getChildText("prompt"));
		return essay;
	}

	public Question readRankQuestion(Element question){
		RankQuestion rank = new RankQuestion();
		rank.setPrompt(question.getChildText("prompt"));
		rank.setScore(Integer.parseInt(question.getChildText("score")));
		List<Element> items = question.getChild("items").getChildren();
		for(int i=0; i<items.size(); i++){
			rank.addItem(items.get(i).getText());
		}
		if(question.getAttributeValue("answer").equals("1")){
			rank.setAnswer(question.getChildText("answer"));
		}
		return rank;
	}

	public Question readMapQuestion(Element question){
		MapQuestion map = new MapQuestion();
		map.setPrompt(question.getChildText("prompt"));
		Element side1 = question.getChild("side1");
		List<Element> sideList1 = side1.getChildren();
		for(int j=0; j<sideList1.size(); j++){
			map.addLeftItem(sideList1.get(j).getText());
		}
		Element side2 = question.getChild("side2");
		List<Element> sideList2 = side2.getChildren();
		for(int j=0; j<sideList2.size(); j++){
			map.addRightItem(sideList2.get(j).getText());
		}
		if(question.getAttributeValue("isScore").equals("1")){
			map.setScore(Integer.parseInt(question.getChildText("score")));
		}
		if(question.getAttributeValue("answer").equals("1")){
			map.setAnswer(question.getChildText("answer"));
		}
		return map;


	}

}
