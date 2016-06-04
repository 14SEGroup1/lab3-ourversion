package Question;

/**
 * Created by Qizixi on 5/29/2016.
 */
public class ChoiceQuestion extends ItemQuestion{

    @Override
    public String getQuestion() {
        String items = "";
        int i = 0;
        for(String temp:Items){
            items = items + i++ + "." + temp +" ";
        }
        return "Choice: "+prompt+" "+items;
    }

    @Override
    public String getQuestionType() {
        return "choice";
    }
}
