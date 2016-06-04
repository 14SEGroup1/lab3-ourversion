package Answer;

/**
 * Created by Qizixi on 5/29/2016.
 */
public abstract class ListAnswer implements Answer{

    private String[] answer;

    @Override
    public void setAnswer(String inputAnswer) {
        answer = inputAnswer.split(" ");
    }

    @Override
    public String getAnswer(){
        StringBuilder sb = new StringBuilder();
        for(String iString:answer){
            sb.append(iString);
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public abstract String getType();

    @Override
    public boolean match(Answer matchAnswer) {
        String inputString = matchAnswer.getAnswer();
        String[] matchList = inputString.split(" ");
        if(matchList.length!=answer.length){
            return false;
        }
        for(int i=0;i<answer.length;i++){
            if (answer[i]!=matchList[i]){
                return false;
            }
        }
        return true;
    }
}
