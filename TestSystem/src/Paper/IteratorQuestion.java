package Paper;

import Control.PaperModifier;
import Question.Question;

public class IteratorQuestion  implements Iterator<Question>{
	int questionIndex;

	public boolean hasNext(Paper page) {
		// TODO Auto-generated method stub
		if(page.getQuestionList().size() > questionIndex)
			return true;
		return false;
	}

	@Override
	public Question next() {
		// TODO Auto-generated method stub
		return null;//questionList.get(questionIndex++);
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

}
