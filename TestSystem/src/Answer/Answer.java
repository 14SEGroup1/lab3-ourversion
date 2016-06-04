package Answer;

public interface Answer {
	public String getAnswer();
	public void setAnswer(String answer);
	public String getType();
	public boolean match(Answer answer);
}
