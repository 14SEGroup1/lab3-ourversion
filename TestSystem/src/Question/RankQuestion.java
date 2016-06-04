package Question;

/**
 * Created by Qizixi on 5/29/2016.
 */
public class RankQuestion extends ItemQuestion{

    @Override
    public String getQuestion() {
        String items = "";
        int i = 0;
        for(String temp:Items){
            items = items + i++ + "." + temp +" ";
        }
        return "Rank: "+prompt+" "+items;
    }

    @Override
    public String getQuestionType() {
        return "rank";
    }
}
