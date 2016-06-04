package View;

import java.awt.GridLayout;
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

public class AnswerFrame extends JFrame{
	JTextField[] textAreas;
	JButton confirmButton = new JButton("OK");
    public AnswerFrame (Listener listener) {
    	setTitle("TestSystem");
		setSize(600, 100 + listener.questionList.size() * 50);
		setLocationRelativeTo(null); // Center the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		setResizable(false);
		textAreas = new JTextField[listener.questionList.size()];
		Panel panel = new Panel(listener);
		add(panel);
		setVisible(true);
		confirmButton.addActionListener(new ButtonListener(listener));
    }
    
    class ButtonListener implements ActionListener {
    	StartFrame.Listener listener;
		public ButtonListener(Listener listener) {
			this.listener = listener;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			for (int i = 0; i < textAreas.length; i++) {
				//昨天写到这儿了，对于循环有点担忧
				String answerText = textAreas[i].getText();
				if (listener.controlPanel.filler.hasNextQuestion()) {
					listener.controlPanel.filler.answerQuestion(answerText);
				}
			}
			listener.controlPanel.filler.savePaper();
			List<String> displayedMessage = new ArrayList<String>();
			displayedMessage.add("Ok, it's all!");
			new DisplayFrame(displayedMessage, listener);
		}
    }
    
    class Panel extends JPanel {
    	public Panel(Listener listener) {
			setLayout(new GridLayout(listener.questionList.size() + 2,1));
			JLabel timeLabel = new JLabel();
			add(timeLabel);
			add(new JLabel());
			new Thread(new MyThread(timeLabel, listener)).start();
			for (int i = 0; i < listener.questionList.size(); i++) {
				add(new JLabel(listener.questionList.get(i)));
				textAreas[i] = new JTextField(10);
				add(textAreas[i]);
			}
			add(confirmButton);
		}
    }

	class MyThread implements Runnable {
		JLabel label;
		int time = 100;
		Listener listener;
		public MyThread(JLabel label, Listener listener) {
			this.label = label;
			this.listener = listener;
		}
		public void run() {
			while (time > 0) {
				time--;
				label.setText("The left time is: " + time + "");
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			dispose();
			new StartFrame(listener.controlPanel);
		}
	};
}
