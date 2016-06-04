package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.jdom.JDOMException;


public class StartFrame extends JFrame{

	public StartFrame(ControlPanel control) {
		//开始页窗口
		setTitle("TestSystem");
		setSize(300, 500);
		setLocationRelativeTo(null); // Center the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		setResizable(false);
		Panel1 panel1 = new Panel1();
		add(panel1);
		setVisible(true);
		for (int i = 0; i < panel1.boxes.length; i++) {
			panel1.boxes[i].addActionListener(new Listener(i,control));
		}
	}
	
	//响应按钮事件类
	class Listener implements ActionListener{
		ControlPanel controlPanel;
		Scanner sc = new Scanner(System.in);
		String prompt;
		String paperName;
		String recordName;
		String[] items, side1, side2;
		int questionType;
		int leftSideChoiceNumber, rightSideChoiceNumber;
		boolean isLeftSide = true;
		int pageOptionNumber;//it is used in ChooseFrame for storing choice of pages
		List<String> instructions;//it is used in this class for conveying instructions to ModifyQuestionFrame
		List<String> questionList;//it is used in AnswerFrame and ChooseFrame
		List<String> itemList;//it is used in ChooseFrame                 //这里有修改
		int indexOfChoice;//it is used in case 16 in InputFrame
		int questionOptionNumber;
		String[] inputTypeStrings = {
				"Please input your name:+Please input pageName:+Please input  time limit:",
				"Enter the prompt for your True/False question:",
				"Enter the prompt for your Choice question:",
				"Please enter your choice number",
				"Enter your choice ",
				"Enter the prompt for you text question:",
				"Enter the prompt for you essay question:",
				"Enter the prompt for you rank question:",
				"Enter the prompt for you map question:",
				"Please enter your left side choice number",
				
				"Please enter your right side choice number",
				"Please input new prompt",
				"Please input new answer",
				"Please input new number",
				"Please input new choice",
				"Please enter your anwser:+Please enter  your score:", //此处有修改
				"Please input the index of the item:",
				"Please input the new item:",
				"Please input question index",
				"Please input your name:",
		};
		//这里有修改
		//it is used in ChooseFrame 
		String[] chooseInstructions = {
				"Choose the page displayed:",
				"Choose page that you want to modify:",
				"Choose page that you want to answer:",
				"Choose question that you want to modify:",
				"Choose item that you want to remove:",
				"Choose item that you want modify:",
				"Choose question type:",
				"Choose the page for outcome:",
				"Choose add question or save page:",
				"Choose the record:"
		};
		List<String> questionTypes = new ArrayList<String>(){
			{
				add("Add a new T/F question");
				add("Add a new multiple choice question");
				add("Add a new short answer question");
				add("Add a new essay question");
				add("Add a new ranking question");
				add("Add a new map question");
			}
		};
		List<String> addQuestionInstructions = new ArrayList<String>(){
			{
				add("Add a new Question");
				add("Save this page");
				add("Give up this page");
			}
		};
 		String type;
		int optionNumber;
		public Listener(int i,ControlPanel controlPanel){  //将编号传入，以便输出
			optionNumber = i;
			this.controlPanel = controlPanel;
		}
		public void actionPerformed(ActionEvent e){  
			switch(optionNumber){
			case 0: this.createSurvey();break;
			case 1: this.createTest();break;
			case 2: this.display("Survey");break;
			case 3: this.display("Test");break;
			case 4: this.modify("Survey");break;   //此处有修改
			case 5:	this.modify("Test");break;
			case 6: this.answer("Survey");break;
			case 7:this.answer("Test");break;
			case 8:try {
					this.displayOutcome("Survey");
				} catch (JDOMException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}break;
			case 9:try {
					this.displayOutcome("Test");
				} catch (JDOMException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}break;
			case 10:System.exit(0);
			}
			
		}
		
		public void displayOutcome(String type) throws JDOMException, IOException{
			dispose();
			this.type = type;
			new ChooseFrame(7, this);
		}
		
		public void answer(String type){
			dispose();
			this.type = type;
			new ChooseFrame(2, this);
		}
		
		public void modify(String type){
			this.type = type;
			this.display(type);
		}
				
		public void display(String type){
			this.type = type;
			if (optionNumber == 2 || optionNumber == 3) {
				new ChooseFrame(0, this);
			} else if (optionNumber == 4 || optionNumber == 5) {        //此处有修改
				new ChooseFrame(1, this);
			}
		}		
		
		public void createTest(){
			controlPanel.maker.makeNewPaper("Test");
			dispose();
			new InputFrame(0, this);
			type = "Test";
		}
		
		public void createSurvey(){
			controlPanel.maker.makeNewPaper("Survey");
			dispose();
			type = "Survey";
			new InputFrame(0, this);
		}

	}
}

class Panel1 extends JPanel{
	JRadioButton[] boxes = new JRadioButton[11];    //此处有修改
	ButtonGroup buttonGroup;
	Panel1(){
		setLayout(new GridLayout(11,1));
		buttonGroup = new ButtonGroup();
		boxes[0] = new JRadioButton("Create a new Survey",false);
		boxes[1] = new JRadioButton("Create a new Test",false);
		boxes[2] = new JRadioButton("Display Survey",false);
		boxes[3] = new JRadioButton("Display a Test",false);
		boxes[4] = new JRadioButton("Modify a Survey",false);
		boxes[5] = new JRadioButton("Modify a Test",false);
		boxes[6] = new JRadioButton("Take a Survey",false);
		boxes[7] = new JRadioButton("Take a Test",false);
		boxes[8] = new JRadioButton("Look survey outcome",false);
		boxes[9] = new JRadioButton("Look test outcome",false);
		boxes[10] = new JRadioButton("Quit",false);
		for (int i = 0; i < boxes.length; i++) {
			buttonGroup.add(boxes[i]);
			add(boxes[i]);
		}
	}
}