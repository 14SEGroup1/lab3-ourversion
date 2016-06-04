package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.jdom.JDOMException;

import IO.Read;
import View.StartFrame.Listener;

public class ChooseFrame extends JFrame{
	List<String> displayedList;
	int chooseRequest;
	public ChooseFrame(int chooseRequest, Listener listener){
		//此处有修改
		switch (chooseRequest) {
		//display page list
		case 0:

			//display page list for modifying  
		case 1:

			//display page list for outcome
		case 7:

			//display page list for answering 
		case 2:
			//displayedList = listener.getControl().getPageName(listener.type);
			if(listener.type.equals("Survey")){
				displayedList = listener.controlPanel.viewer.getSurveyList();
			}else{
				//test
				displayedList = listener.controlPanel.viewer.getTestList();
			}
			break;
			//display question list for modifying
		case 3:
			//displayedList = listener.getControl().displayPage(listener.pageOptionNumber, listener.type);
			listener.controlPanel.modifier.loadPaper(listener.paperName,null);//null for type
			displayedList = listener.controlPanel.modifier.getQuestionList();
			break;
			//display item list for removing
		case 4:
			displayedList = listener.itemList;
			break;
			//display item list for modifying
		case 5:
			displayedList = listener.itemList;
			//display question type for adding
		case 6:
			displayedList = listener.questionTypes;
			break;
			//display add question instructions
		case 8:
			displayedList = listener.addQuestionInstructions;
			break;
			//display records from this paper
		case 9:
			try {
				displayedList = Read.getRecord(listener.paperName, listener.type);
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		setTitle("TestSystem");
		setSize(300, 50 * (displayedList.size() + 2));
		setLocationRelativeTo(null); // Center the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		setResizable(false);
		this.chooseRequest = chooseRequest;
		Panel panel = new Panel(listener);
		add(panel);
		setVisible(true);
		for (int i = 0; i < panel.boxes.length; i++) {
			panel.boxes[i].addActionListener(new ChoiceListener(i, listener));
		}
	}

	class ChoiceListener implements ActionListener{
		Listener listener;
		int optionNumber;
		public ChoiceListener(int i, Listener listener){  //将编号传入，以便输出
			optionNumber = i;
			this.listener = listener;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!displayedList.isEmpty() && chooseRequest != 9)
			    listener.paperName = displayedList.get(optionNumber);
			if(!displayedList.isEmpty() && chooseRequest == 9)
			    listener.recordName = displayedList.get(optionNumber);
			dispose();
			switch (chooseRequest) {
			//display page
			case 0:
				if (displayedList.isEmpty() != true) {
					List<String> question = new LinkedList();
					listener.controlPanel.modifier.loadPaper(listener.paperName, listener.type);
					if(!listener.controlPanel.modifier.getQuestionList().isEmpty()){
					question = listener.controlPanel.modifier.getQuestionList();
					}
					else{
						question.add("There is no Question！");
					}
					new DisplayFrame(question, listener);
				} else {
					new StartFrame(listener.controlPanel);
				}
				break;
				//display page list for modifying
			case 1:
				if (displayedList.isEmpty() != true) {
					this.listener.pageOptionNumber = optionNumber;
					new ChooseFrame(3, listener);
				} else {
					new StartFrame(listener.controlPanel);
				}
				break;
				//display page list for answering
			case 2:
				String pageName = displayedList.get(optionNumber);
				String paperType;
				if(listener.type.equals("Survey")){
					paperType = "Survey";
				}else{
					paperType = "Test";
				}
				listener.controlPanel.filler.loadPaper(pageName,paperType);
				new InputFrame(19, listener);
				break;
				//display question list for modifying
			case 3:
				listener.questionOptionNumber = optionNumber;
				new InputFrame(11, listener);
				break;
				//display question type
			case 6:
				listener.questionType = optionNumber + 1;
				switch(optionNumber){
				case 0: 
					new InputFrame(1, listener);
					break;
				case 1: 
					new InputFrame(2, listener);
					break;
				case 2: 
					new InputFrame(5, listener);
					break;
				case 3: 
					new InputFrame(6, listener);
					break;
				case 4: 
					new InputFrame(7, listener);
					break;
				case 5: 
					new InputFrame(8, listener);
					break;
				}
				break;
				//display page list for outcome
			case 7:
				//				System.out.println(listener.getControl().getOutcome(optionNumber, listener.type));
				//				break;
				//display add question instructions
				if (displayedList.isEmpty() != true) {
					new ChooseFrame(9, listener);
				} else {
					new StartFrame(listener.controlPanel);
				}
				break;
			case 8:
				switch (optionNumber) {
				case 0:
					new ChooseFrame(6, listener);
					break;
				case 1:
					//弹出DisplayFrame，用于提示用户储存成功！
					List<String> tipStrings = new ArrayList<String>();
					tipStrings.add("Save successful!");
					dispose();
					listener.controlPanel.maker.savePaper();
					new DisplayFrame(tipStrings, listener);
					break;
				case 2:
					new StartFrame(listener.controlPanel);
					break;
				}
				break;
				//display records
			case 9:
				List<String> outcomes = Read.getOutcome(listener.paperName, listener.recordName);
				new DisplayFrame(outcomes, listener);
				break;
			}
		}		
	}
	class Panel extends JPanel{
		JRadioButton[] boxes;
		ButtonGroup buttonGroup = new ButtonGroup();;
		Panel(StartFrame.Listener listener){
			add(new JLabel(listener.chooseInstructions[chooseRequest]));
			if (displayedList.isEmpty() != true) {
				boxes = new JRadioButton[displayedList.size()];
				setLayout(new GridLayout(displayedList.size() + 1, 1));
				for (int i = 0; i < boxes.length; i++) {
					boxes[i] = new JRadioButton(displayedList.get(i),false);
					buttonGroup.add(boxes[i]);
					add(boxes[i]);
				}
			} else {
				boxes = new JRadioButton[1];
				setLayout(new GridLayout(2, 1));
				boxes[0] = new JRadioButton("There is no page.", false);
				buttonGroup.add(boxes[0]);
				add(boxes[0]);
			}	
		}
	}
}
