package Question;

import Answer.Answer;

/**
 * Created by Qizixi on 5/29/2016.
 */
public class QuestionFactory {

    private static Question getQuestion(String inputType) {
        Question currentQuestion;
        switch (inputType) {
            case "decision":
                currentQuestion = new DecideQuestion();
                break;
            case "choice":
                currentQuestion = new ChoiceQuestion();
                break;
            case "essay":
                currentQuestion = new EssayQuestion();
                break;
            case "rank":
                currentQuestion = new RankQuestion();
                break;
            case "map":
                currentQuestion = new MapQuestion();
                break;
            case "short essay":
                currentQuestion = new ShortEssayQuestion();
                break;
            default:
                currentQuestion = null;
                break;
        }
        return currentQuestion;
    }

    public static Question addCorrectAnswer(Question question,Answer answer,int score){
        question.setCorrectAnswer(answer);
        question.setScore(score);
        return question;
    }

    public static Question produceQuestion(String type, String prompt) {
        Question result;
        result = getQuestion(type);
        result.setPrompt(prompt);
        result.setScore(0);
        return result;
    }

    public static Question produceQuestion(String type, String prompt,String[] items) {
        Question result = produceQuestion(type,prompt);
        ((ItemQuestion)result).addItemList(items);
        return result;
    }

    public static Question produceQuestion(String type, String prompt,String[] litems,String[] ritems) {
        Question result = produceQuestion(type,prompt);
        ((MapQuestion)result).addLeftItemList(litems);
        ((MapQuestion)result).addRightItemList(ritems);
        return result;
    }

}
