package Paper;

import Answer.Answer;
import Question.Question;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Qizixi on 5/29/2016.
 */
public class Survey extends Paper {
    protected String pageType = "Survey";
    protected String attender;
    public Survey(){
    	super();
    }
    List<Answer> answerList = new LinkedList<Answer>();

    public void addAnswer(Answer answer){
    	System.out.println("size"+answerList.size());
        answerList.add(answer);
    }

    public Answer getAnswer(int index){
        if(index >= answerList.size()){
            return null;
        }else{
            return answerList.get(index);
        }
    }

    public void setAttender(String name){
        attender = name;
    }

    public String getAttender(){
        return attender;
    }

    @Override
    public String getType() {
        return "Survey";
    }
    public void setPaperType(String type) {
        pageType = "Survey";
        // TODO Auto-generated method stub

    }
    public List<Answer> getAnswerList(){
        return answerList;
    }

    public Iterator<Question> iterator_Q() {
        // TODO Auto-generated method stub
        return new IteratorQuestion();
    }
}
