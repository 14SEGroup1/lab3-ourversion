package IO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import Answer.Answer;
import Answer.ChoiceAnswer;
import Answer.DecideAnswer;
import Answer.EssayAnswer;
import Answer.MapAnswer;
import Answer.RankAnswer;
import Answer.TextAnswer;
import Paper.Iterator;
import Paper.Paper;
import Paper.Survey;
import Paper.Test;
import Question.Question;
public class Read {
	static SAXBuilder builder = new SAXBuilder();
	public List<String>[] readInfo(){
		InputStream file;
		Element root = null;
		try {
			file = new FileInputStream("xml/pageInfo.xml");
			Document document = builder.build(file);//获得文档对象
			root = document.getRootElement();//获得根节点
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Element> pageList = root.getChildren("pageName");
		List<String>[] pageName = new List[2];
		pageName[0] = new LinkedList<String>();
		pageName[1] = new LinkedList<String>();
		for(int i=0; i<pageList.size(); i++){
			if(pageList.get(i).getAttributeValue("type").equals("test")){
				pageName[1].add(pageList.get(i).getText());
			}else{
				pageName[0].add(pageList.get(i).getText());
			}
		}
		return pageName;
	}
	public static Survey readPage(String pageName){
		InputStream file;
		Element root = null;
		try {
			file = new FileInputStream("xml/"+pageName+".xml");
			Document document = builder.build(file);//获得文档对象
			root = document.getRootElement();//获得根节点
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Survey page;
		if(root.getAttribute("type").getValue().equals("test")){
			Test test = new Test();
			test.setTotalScore(Integer.parseInt(root.getChildText("score")));
			int limitTime = Integer.parseInt(root.getChildText("limitTime"));
			test.setTimeLimit(limitTime);
			ReadQuestion RQ = new ReadQuestion();
			test = (Test)RQ.ReadQuestion(test , root);
			test.setPapaerType("Test");
			return test;
		}else{
			page = new Survey();
		}
		page.setPaperName(pageName);
		ReadQuestion RQ = new ReadQuestion();
		page = RQ.ReadQuestion(page , root);
		return page;
	}

	public static List<Survey>[] readPageList(){
		List<Survey> surveys= new LinkedList();
		List<Survey> tests= new LinkedList();
		List<Survey>[] list = new LinkedList[2];
		InputStream file;
		File f;
		Element root = null;
		try {
			f = new File("xml/SurveyInfo.xml");
			if(!f.exists()){
				f.createNewFile();
				return list;
			}
			file = new FileInputStream("xml/SurveyInfo.xml");
			Document document = builder.build(file);//获得文档对象
			root = document.getRootElement();//获得根节点
			List<Element> pageList = root.getChildren("pageName");
			for(int i=0; i<pageList.size(); i++){
				surveys.add(readPage(pageList.get(i).getText()));
			}
			file.close();
			f = new File("xml/TestInfo.xml");
			if(!f.exists()){
				f.createNewFile();
				return list;
			}
			file = new FileInputStream("xml/TestInfo.xml");
			document = builder.build(file);//获得文档对象
			root = document.getRootElement();//获得根节点
			pageList = root.getChildren("pageName");
			for(int i=0; i<pageList.size(); i++){
				tests.add(readPage(pageList.get(i).getText()));
			}
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		list[0] = surveys;
		list[1] = tests;
		return list;
	}

	public static List<String> readRecordInfo(String pageName){
		InputStream file;
		Element root = null;
		try {
			File recordFile = new File("xml/record/"+pageName+"-recordInfo.xml");
			if(!recordFile.exists()){
				return new LinkedList<String>();
			}
			file = new FileInputStream("xml/record/"+pageName+"-recordInfo.xml");

			Document document = builder.build(file);//获得文档对象
			root = document.getRootElement();//获得根节点

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Element> pageList = root.getChildren("record");
		List<String> records = new LinkedList<String>();
		for(int i=0; i<pageList.size(); i++){
			records.add(pageList.get(i).getText()); 
		}
		return records;
	}

	public Paper readRecord(String recordName){
		InputStream file;
		Element root = null;
		try {
			file = new FileInputStream("xml/record/"+recordName+".xml");
			Document document = builder.build(file);//获得文档对象
			root = document.getRootElement();//获得根节点

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Survey record = null;
		String typeOfRecord = root.getChildText("type");
		if(typeOfRecord.equals("Test")){
			Test recordTest = new Test();
			recordTest.setAttender(root.getChildText("PersonName"));
			recordTest.setTotalScore(Integer.parseInt(root.getChildText("score")));
			record = recordTest;
		}
		else{
			Survey recordSurvey = new Survey();
			recordSurvey.setAttender(root.getChildText("PersonName"));
			record = recordSurvey;
		}
		//record.setsetPersonName(root.getChildText("PersonName"));

		Element answers = root.getChild("answers");
		List<Element> answerList = answers.getChildren();
		for(int i=0; i<answerList.size(); i++){
			Element answer = answerList.get(i);
			String type = answer.getAttributeValue("type");
			switch(type){
			case "decision": DecideAnswer decide = new DecideAnswer();
			decide.setAnswer(answer.getText());
			record.addAnswer(decide);
			break;
			case "choice": ChoiceAnswer choice = new ChoiceAnswer();
			choice.setAnswer(answer.getText());
			record.addAnswer(choice);
			break;
			case "essay": EssayAnswer text = new EssayAnswer();
			text.setAnswer(answer.getText());
			record.addAnswer(text);
			break;
			case "rank": RankAnswer rank = new RankAnswer();
			rank.setAnswer(answer.getText());
			record.addAnswer(rank);
			break;
			case "map": MapAnswer map = new MapAnswer();
			map.setAnswer(answer.getText());
			record.addAnswer(map);
			break;
			}
		}
		return record;
	}
	public static List<String> getRecord(String paperName, String type) throws JDOMException, IOException{
		List<String> surveys= new LinkedList();
		List<String> tests= new LinkedList();
		if(type.equals("Survey")){
			File f = new File("xml/record/"+paperName+"-recordInfo.xml");
			if(!f.exists()){
                surveys.add("No record find!");
                return surveys;
			}
			FileInputStream file;
			file = new FileInputStream("xml/record/"+paperName+"-recordInfo.xml");
			Document document = builder.build(file);//获得文档对象
			Element root = document.getRootElement();//获得根节点
			List<Element> pageList = root.getChildren("record");
			for(int i=0; i<pageList.size(); i++){
				surveys.add(pageList.get(i).getText());
			}
			file.close();
			return surveys;
		}
		else{
			File f = new File("xml/record/"+paperName+"-recordInfo.xml");
			if(!f.exists()){
                surveys.add("No record find!");
                return surveys;
			}
			FileInputStream file = new FileInputStream("xml/record/"+paperName+"-recordInfo.xml");
			Document document = builder.build(file);//获得文档对象
			Element root = document.getRootElement();//获得根节点
			List<Element> pageList = root.getChildren("record");
			for(int i=0; i<pageList.size(); i++){
				tests.add(pageList.get(0).getText());
			}
			file.close();
			return tests;
		}
		//return null;
	}



	public static List<String> getOutcome(String paperName, String recordName){
		List<String> rt = new LinkedList<String>();
		InputStream file;
		Element root = null;
		try {
			file = new FileInputStream("xml/record/"+paperName + "-" + recordName+".xml");
			Document document = builder.build(file);//获得文档对象
			root = document.getRootElement();//获得根节点
			Element answers = root.getChild("answers");
			List<Element> answer = answers.getChildren("answer");
			for(int i = 0 ; i < answer.size() ; i++){
				rt.add(answer.get(i).getText());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt;
	}
}
