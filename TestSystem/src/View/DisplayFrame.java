package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayFrame extends JFrame{
	JButton confirmButton = new JButton("OK");
	List<String> question;
	StartFrame.Listener listener;
	public DisplayFrame(List<String> question, StartFrame.Listener listener) {
		setTitle("TestSystem");
		setSize(300, 50 * question.size() + 50);
		setLocationRelativeTo(null); // Center the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		setResizable(false);	
		this.question = question;
		this.listener = listener;
		Panel panel = new Panel();
		add(panel);
		setVisible(true);
		confirmButton.addActionListener(new ButtonListener());
	}
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new StartFrame(listener.controlPanel);
		}
		
	}
	class Panel extends JPanel{
		Panel(){
			setLayout(new GridLayout(question.size() + 1, 1));
			for (int i = 0; i < question.size(); i++) {
				add(new JLabel(question.get(i)));
			}
			if(question.isEmpty()){
				add(new JLabel("There is no items to be display!"));
			}
			add(confirmButton);
		}
	}
}

