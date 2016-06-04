package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ChoiceInputFrame extends JFrame{
	JButton comfirmButton = new JButton("OK");
	JTextField[] textAreas;
	String answer;
	int score;
	String[] items;
	public ChoiceInputFrame(int choiceNumber, StartFrame.Listener listener) {
		setTitle("TestSystem");
		setSize(300, 100 * choiceNumber);
		setLocationRelativeTo(null); // Center the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		setResizable(false);
		textAreas = new JTextField[choiceNumber];
		items = new String[choiceNumber];
		Panel panel = new Panel(choiceNumber, listener);
		add(panel);
		setVisible(true);
		comfirmButton.addActionListener(new ButtonListener(listener));
	}
	
	class ButtonListener implements ActionListener{
		StartFrame.Listener listener;
		public ButtonListener(StartFrame.Listener listener) {
			this.listener = listener;
		}
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == comfirmButton) {
				dispose();
				//将textAreas里的内容存到items中
				for (int i = 0; i < items.length; i++) {
					items[i] = new String();
					items[i] = textAreas[i].getText();
				}
				if (this.listener.questionType != 6) {
					this.listener.items = new String[items.length];
					this.listener.items = items;
					if(listener.type == "Test"){
						new InputFrame(15, this.listener);
						return;
					}
					if (this.listener.questionType == 2) {
						this.listener.controlPanel.maker.addQuestion("choice",this.listener.prompt, items);
					} else if (this.listener.questionType == 5) {
						this.listener.controlPanel.maker.addQuestion("rank",this.listener.prompt, items);
					}
					new ChooseFrame(8, this.listener);
				} else {
					if (this.listener.isLeftSide == true) {
						this.listener.side1 = new String[items.length];
						this.listener.side1 = items;
						this.listener.isLeftSide = false;
						new InputFrame(10, this.listener);
					} else {
						this.listener.side2 = new String[items.length];
						this.listener.side2 = items;
						this.listener.isLeftSide = true;
						if(listener.type == "Test"){
							new InputFrame(15, this.listener);
							return;
						}
						this.listener.controlPanel.maker.addQuestion("map",this.listener.prompt, this.listener.side1, this.listener.side2);
						new ChooseFrame(8, this.listener);
					}
				}
			}
		}
	}
	
	class Panel extends JPanel{
		Panel(int choiceNumber, StartFrame.Listener listener){
			for (int i = 0; i < choiceNumber; i++) {
				add(new JLabel(listener.inputTypeStrings[4] + i));
				textAreas[i] = new JTextField(10);
				add(textAreas[i]);
			}
			add(comfirmButton);
		}
	}
}
