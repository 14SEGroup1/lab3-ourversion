package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.StartFrame.Listener;

public class InputFrame extends JFrame{
	JButton confirmButton = new JButton("OK");
	JTextField[] textFields;
	int inputIndex;
	int choiceNumber;
	public InputFrame(int inputRequest, Listener listener) {
		//输入窗口
		setTitle("TestSystem");
		setSize(300, 200);
		setLocationRelativeTo(null); // Center the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		setResizable(false);	
		Panel2 panel2 = new Panel2(inputRequest, listener);
		add(panel2);
		inputIndex = inputRequest;
		setVisible(true);
		confirmButton.addActionListener(new ButtonListener(listener));
	}

	class ButtonListener implements ActionListener{
		Listener listener;
		public ButtonListener(StartFrame.Listener listener) {
			this.listener = listener;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == confirmButton) {
				dispose();
				switch (inputIndex) {
				//add question
				case 0:
					this.listener.controlPanel.maker.setAuthor(textFields[0].getText());
                    this.listener.controlPanel.maker.setPaperName(textFields[1].getText());
                    this.listener.controlPanel.maker.setTimeLimit(textFields[2].getText());
					new ChooseFrame(8, listener);
					break;
				//T/F question
				case 1:
					if(this.listener.type == "Test"){
						this.listener.prompt = textFields[0].getText();
						new InputFrame(15, listener);
						break;
					}
					this.listener.controlPanel.maker.addQuestion("decision",textFields[0].getText());
					new ChooseFrame(8, listener);
					break;
				//multiple choice question
				case 2:
					this.listener.prompt = textFields[0].getText();
					new InputFrame(3, this.listener);
					break;
				//get choice number
				case 3:
					choiceNumber = Integer.valueOf(textFields[0].getText());
					new ChoiceInputFrame(choiceNumber, this.listener);
					break;
				//text question
				case 5:
					if(this.listener.type == "Test"){
						this.listener.prompt = textFields[0].getText();
						new InputFrame(15, this.listener);
						break;
					}
					this.listener.controlPanel.maker.addQuestion("short essay",textFields[0].getText());
					new ChooseFrame(8, listener);
					break;
				//essay question
				case 6:
					this.listener.controlPanel.maker.addQuestion("essay",textFields[0].getText());
					new ChooseFrame(8, listener);
					break;
				//rank question
				case 7:
					this.listener.prompt = textFields[0].getText();
					new InputFrame(3, this.listener);
					break;
				//map question
				case 8:
					this.listener.prompt = textFields[0].getText();
					new InputFrame(9, this.listener);
					break;
				//map side1
				case 9:
					this.listener.leftSideChoiceNumber = Integer.valueOf(textFields[0].getText());
					new ChoiceInputFrame(this.listener.leftSideChoiceNumber, this.listener);
					break;
				//map side2
				case 10:
					this.listener.rightSideChoiceNumber = Integer.valueOf(textFields[0].getText());
					new ChoiceInputFrame(this.listener.rightSideChoiceNumber, this.listener);
					break;
				//new prompt
				case 11:
					String prompt = textFields[0].getText();
                    this.listener.controlPanel.modifier.modifyQuestion(listener.questionOptionNumber, prompt);
                    List<String> displayMessage = new ArrayList<>();
                    displayMessage.add("Modify successfully!");
                    new DisplayFrame(displayMessage,listener);
					break;
				//enter answer and score
				case 15:
					String answer = textFields[0].getText();
					int score = Integer.valueOf(textFields[1].getText());
					
					switch (this.listener.questionType) {
					case 1:
						this.listener.controlPanel.maker.addQuestionWithAnswer("decision",this.listener.prompt, score, answer);
						new ChooseFrame(8, this.listener);
						break;
					case 2:
                        this.listener.controlPanel.maker.addQuestionWithAnswer("choice",this.listener.prompt, this.listener.items, score, answer);
						new ChooseFrame(8, this.listener);
						break;
					case 3:
                        this.listener.controlPanel.maker.addQuestionWithAnswer("short essay",this.listener.prompt, score, answer);
						new ChooseFrame(8, this.listener);
						break;
					case 4:
						new ChooseFrame(8, this.listener);
						break;
					case 5:
                        this.listener.controlPanel.maker.addQuestionWithAnswer("rank",this.listener.prompt, this.listener.items, score, answer);
						new ChooseFrame(8, this.listener);
						break;
					case 6:
                        this.listener.controlPanel.maker.addQuestionWithAnswer("map",this.listener.prompt, this.listener.side1,
								this.listener.side2, score, answer);
						new ChooseFrame(8, this.listener);
						break;
					}
					break;
                //input name for answering
				case 19:
					String personName = textFields[0].getText();
					listener.controlPanel.filler.setPaperAttender(personName);
					listener.questionList = listener.controlPanel.filler.getQuestionList();
					new AnswerFrame(listener);
				}
			}
		}
	}
	
	class Panel2 extends JPanel{
		Panel2(int inputRequest, StartFrame.Listener listener){
			String[] texts = listener.inputTypeStrings[inputRequest].split("\\+");
			textFields = new JTextField[texts.length];
			for (int i = 0; i < texts.length; i++) {
				add(new JLabel(texts[i]));
				textFields[i] = new JTextField(10);
				add(textFields[i]);
			}
			add(confirmButton);
		}
	}
}

