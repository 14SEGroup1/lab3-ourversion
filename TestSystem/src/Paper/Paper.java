package Paper;

import Answer.Answer;
import Question.Question;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Qizixi on 5/29/2016.
 */
public abstract class Paper {
    protected String paperAuthor;
    protected String paperName;
    protected int questionIndex = 0;
    protected int timeLimit = 0;
    //protected List<Answer> answerList = new LinkedList<Answer>();
    public void setTimeLimit(int time){
        timeLimit = time;
    }

    public int getTimeLimit(){
        return timeLimit;
    }

    List<Question> questionList = new LinkedList<Question>();

    public int getQuestionNum(){
        return questionList.size();
    }

    public void setPaperName(String paperName){
        this.paperName = paperName;
    }

    public String getPaperName(){
        return this.paperName;
    }

    public void setAuthorName(String pageAuthor){
        this.paperAuthor = pageAuthor;
    }

    public String getAurtherName(){
        return this.paperAuthor;
    }

    public void addQuestion(Question question){
        questionList.add(question);
    }

    public Question getQuestion(int index){
        if(index >= questionList.size()){
            return null;
        }else{
            return questionList.get(index);
        }
    }

    public List<String> getQuestionList(){
    	List<String> result = new LinkedList<String>();
        for(int i=0;i<questionList.size();i++){
            result.add(questionList.get(i).getQuestion());
        }
        return result;
    }
    public List<Question> get_QuestionList(){
    	return questionList;
    }

    public abstract String getType();

    public void setQuestion(int index,Question inputQuestion){
        questionList.set(index,inputQuestion);
    }

    public boolean hasNextQuestion(){
        if (questionIndex<questionList.size()){
            return true;
        }else {
            return false;
        }
    }

    public Question getNextQuestion(){
        return questionList.get(questionIndex++);
    }

    

    public Iterator<Answer> iterator_A() {
        // TODO Auto-generated method stub
        return new IteratorAnswer();
    }


}
