package IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import Control.PaperViewer;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import Answer.Answer;
import Paper.Iterator;
import Paper.Paper;
import Paper.Paper;
import Paper.Test;
import Question.Question;
import Paper.Survey;
import Paper.Test;

public class Save {
	SAXBuilder builder = new SAXBuilder();
	public void saveInfo(List<String>[] pageName){
		Element root = new Element("totalInfo");
		for(int i=0; i<pageName[0].size(); i++){
			Element page = new Element("pageName");
			page.addContent(pageName[0].get(i));
			page.setAttribute("type", "survey");
			root.addContent(page);
		}
		for(int i=0; i<pageName[1].size(); i++){
			Element page = new Element("pageName");
			page.addContent(pageName[1].get(i));
			page.setAttribute("type", "test");
			root.addContent(page);
		}
		Document doc=new Document(root);  
		try {
			File file = new File("xml/pageInfo.xml");
			if(!file.exists()){
				file.mkdirs();
			}
			FileOutputStream out=new FileOutputStream("xml/pageInfo.xml");
			XMLOutputter outputter = new XMLOutputter();  
			Format f = Format.getPrettyFormat();  
			outputter.setFormat(f);  
			outputter.output(doc, out);  
			out.close();  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public static void saveInfo(List<String> name, String type){
		Element root = new Element("totalInfo");
		if(type.equals("Survey")){
			for(int i=0; i<name.size(); i++){
				Element page = new Element("pageName");
				page.addContent(name.get(i));
				page.setAttribute("type", "survey");
				root.addContent(page);
			}
		}
		else if(type.equals("Test")){
			for(int i=0; i<name.size(); i++){
				Element page = new Element("pageName");
				page.addContent(name.get(i));
				page.setAttribute("type", "survey");
				root.addContent(page);
			}
		}
		Document doc=new Document(root);  
		try {
			FileOutputStream out=null;
			if(type.equals("Test")){
				out = new FileOutputStream("xml/TestInfo.xml");
			}
			else if(type.equals("Survey")){
				out = new FileOutputStream("xml/SurveyInfo.xml");
			}
			else{
				//System.out.println("error");
			}

			XMLOutputter outputter = new XMLOutputter();  
			Format f = Format.getPrettyFormat();  
			outputter.setFormat(f);  
			outputter.output(doc, out);  
			out.close();  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


	public static void savePage(Survey page , String pageForWhom , String type){
		if(pageForWhom.equals("TestWriter")){
			switch(type){
			case "Survey":{
				PaperViewer.addNewSurvey(page);
				saveInfo(PaperViewer.getSurveyNameList() , "Survey");
				break;
			}
			case "Test":{
				PaperViewer.addNewTest(page);
				saveInfo(PaperViewer.getTestNameList() , "Test");
				break;
			}
			}
			Element root = new Element("Page");
			root.setAttribute("type", page.getType());
			root.addContent(new Element("pageName").setText(page.getPaperName()));
			if(page.getType().equals("Test")){
				root.addContent(new Element("score").setText(((Test)page).getTotalScore()+""));
			}
			root.addContent(new Element("limitTIme").setText(page.getTimeLimit()+""));
			Element questions = new Element("questions");
			SaveQuestion SQ=new SaveQuestion();
			questions = SQ.Save(page, root);
			root.addContent(questions);
			Document doc=new Document(root);  
			try {
				FileOutputStream out=new FileOutputStream("xml/"+page.getPaperName()+".xml");
				XMLOutputter outputter = new XMLOutputter();  
				Format f = Format.getPrettyFormat();  
				outputter.setFormat(f);  
				outputter.output(doc, out);  
				out.close();  
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		
		else if(pageForWhom.equals("Tester")){
			//List<String> recordName = Read.readRecordInfo(page.getPaperName());
			//recordName.add(page.getPaperName()+"-"+page.getAttender());
			if(type.equals("Survey")){
				PaperViewer.addNewSurveyRecord(page);
				saveReordInfoList(page.getPaperName(),PaperViewer.getSurveyNameRecordList());	
			}
			else if(type.equals("Test")){
				PaperViewer.addNewTestRecord(page);
				saveReordInfoList(page.getPaperName(),PaperViewer.getTestNameRecordList());
			}
			else{
				//System.out.println("error");
			}
			
			saveRecord(page.getPaperName()+"-"+page.getAttender(), page , type);
		}
		
		
		else{
			//System.out.print("error name");
		}
	}

	public static void savePage(Survey page , String pageForWhom){
		if(pageForWhom.equals("TestWriter")){
			switch(page.getType()){
			case "Survey":{
				PaperViewer.addNewSurvey(page);
				saveInfo(PaperViewer.getSurveyNameList() , "Survey");
				break;
			}
			case "Test":{
				PaperViewer.addNewTest(page);
				saveInfo(PaperViewer.getTestNameList() , "Test");
				break;
			}
			}
			Element root = new Element("Page");
			root.setAttribute("type", page.getType());
			root.addContent(new Element("pageName").setText(page.getPaperName()));
			if(page.getType().equals("Test")){
				root.addContent(new Element("score").setText(((Test)page).getTotalScore()+""));
			}
			root.addContent(new Element("limitTIme").setText(page.getTimeLimit()+""));
			Element questions = new Element("questions");
			SaveQuestion SQ=new SaveQuestion();
			questions = SQ.Save(page, root);
			root.addContent(questions);
			Document doc=new Document(root);  
			try {
				FileOutputStream out=new FileOutputStream("xml/"+page.getPaperName()+".xml");
				XMLOutputter outputter = new XMLOutputter();  
				Format f = Format.getPrettyFormat();  
				outputter.setFormat(f);  
				outputter.output(doc, out);  
				out.close();  
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		
		else if(pageForWhom.equals("Tester")){
			//List<String> recordName = Read.readRecordInfo(page.getPaperName());
			//recordName.add(page.getPaperName()+"-"+page.getAttender());
			//saveReordInfoList(page.getPaperName(), page.getAttender() );
			saveRecord(page.getPaperName()+"-"+page.getAttender(), page);
		}
		
		
		else{
			//System.out.print("error name");
		}
	}
	
	
	public static void saveReordInfoList(String pageName, List<String> recordName){
		Element root = new Element("Records");
		for(int i=0; i<recordName.size(); i++){
			Element record = new Element("record");
			record.setText(recordName.get(i));
			root.addContent(record);
	  }
		Document doc=new Document(root);  
		try {
			File file = new File("xml/record/"+pageName+"-recordInfo.xml");
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream out=new FileOutputStream(file);
			XMLOutputter outputter = new XMLOutputter();  
			Format f = Format.getPrettyFormat();  
			outputter.setFormat(f);  
			outputter.output(doc, out);  
			out.close();  
			out.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	public static void saveReordInfo(String pageName, List<String> recordName){
		Element root = new Element("Records");
		for(int i=0; i<recordName.size(); i++){
			Element record = new Element("record");
			record.setText(recordName.get(i));
			root.addContent(record);
		}
		Document doc=new Document(root);  
		try {
			File file = new File("xml/record/"+pageName+"-recordInfo.xml");
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream out=new FileOutputStream(file);
			XMLOutputter outputter = new XMLOutputter();  
			Format f = Format.getPrettyFormat();  
			outputter.setFormat(f);  
			outputter.output(doc, out);  
			out.close();  
			out.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	public static void saveRecord(String recordName, Survey record ,String PaperType){
		Element root = new Element("Record");
		Element personName = new Element("PersonName");
		personName.setText(record.getAttender());
		root.addContent(personName);
		Element type = new Element("type");
		if(PaperType.equals("Test")){
			type.setText("Test");
			root.addContent(type);
	}
		else{
			type.setText("Survey");
			root.addContent(type);
		}
		Element answers = new Element("answers");
		List<Answer> records = record.getAnswerList();
		for(int i = 0 ; i < records.size() ; i++){
			Answer answer = records.get(i);
			Element answerElement = new Element("answer");
			answerElement.setAttribute("type", answer.getType()+"");
			answerElement.setText(answer.getAnswer());
			answers.addContent(answerElement);
		}
		root.addContent(answers);
		Document doc=new Document(root);  
		try {
			File file = new File("xml/record");
			if(!file.exists()){
				file.mkdirs();
			}
			File files = new File("xml/record/"+recordName+".xml");
			if(!files.exists()){
				files.createNewFile();
			}
			FileOutputStream out=new FileOutputStream("xml/record/"+recordName+".xml");
			XMLOutputter outputter = new XMLOutputter();  
			Format f = Format.getPrettyFormat();  
			outputter.setFormat(f);  
			outputter.output(doc, out);  
			out.close();  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void saveRecord(String recordName, Survey record){
		Element root = new Element("Record");
		Element personName = new Element("PersonName");
		personName.setText(record.getAttender());
		root.addContent(personName);
		Element type = new Element("type");
		if(record.getType().equals("Test")){
			type.setText("Test");
			root.addContent(type);
		}
		else{
			type.setText("Survey");
			root.addContent(type);
		}
		Element answers = new Element("answers");
		Iterator<Answer> iterator = record.iterator_A();
		while(iterator.hasNext()){
			Answer answer = iterator.next();
			Element answerElement = new Element("answer");
			answerElement.setAttribute("type", answer.getType()+"");
			answerElement.setText(answer.getAnswer());
			answers.addContent(answerElement);
		}
		root.addContent(answers);
		Document doc=new Document(root);  
		try {
			File file = new File("xml/record");
			if(!file.exists()){
				file.mkdirs();
			}
			File files = new File("xml/record/"+recordName+".xml");
			if(!files.exists()){
				files.createNewFile();
			}
			FileOutputStream out=new FileOutputStream("xml/record/"+recordName+".xml");
			XMLOutputter outputter = new XMLOutputter();  
			Format f = Format.getPrettyFormat();  
			outputter.setFormat(f);  
			outputter.output(doc, out);  
			out.close();  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}