package Answer;

/**
 * Created by Qizixi on 5/29/2016.
 */
public class AnswerFactory {

    private static Answer getAnswer(String inputType){
        Answer currentAnswer;
        switch (inputType){
            case "decision":
                currentAnswer = new DecideAnswer();
                break;
            case "choice":
                currentAnswer = new ChoiceAnswer();
                break;
            case "essay":
                currentAnswer = new EssayAnswer();
                break;
            case "rank":
                currentAnswer = new RankAnswer();
                break;
            case "map":
                currentAnswer = new MapAnswer();
                break;
            default:
                currentAnswer = null;
                break;
        }
        return currentAnswer;
    }

    public static Answer produceAnswer(String type,String answer){
        Answer result = getAnswer(type);
        if(result!=null){
            result.setAnswer(answer);
        }
        return result;
    }

}
