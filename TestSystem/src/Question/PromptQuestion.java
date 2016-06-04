package Question;

import Answer.Answer;

public abstract class PromptQuestion extends Question{
	public PromptQuestion(int type) {
		super();
	}
	public abstract Answer getAnswer();
	public abstract void setAnswer(String answer);
}
