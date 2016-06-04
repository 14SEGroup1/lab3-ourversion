package Control;
import java.util.LinkedList;
import java.util.List;

import Paper.Paper;
import Paper.Survey;
import Paper.Test;
import IO.Read;
import IO.Save;
/**
 * Created by Qizixi on 6/1/2016.
 */
public class PaperViewer {
	private static List<Survey>  surveyList = new LinkedList<Survey>();
	private static List<Survey> testList = new LinkedList<Survey>();
	private static List<Survey> surveyRecordList = new LinkedList<Survey>();
	private static List<Survey> testRecordList = new LinkedList<Survey>();
	private static List<String>[] pageNameList;
	private static List<String> surveyNameList = new LinkedList<String>();
	private static List<String> testNameList = new LinkedList<String>();
	private static List<String> surveyNameRecordList = new LinkedList<String>();
	private static List<String> testNameRecordList = new LinkedList<String>();
	private static List<String> recordName;
	private Read read = new Read();
	private Save save = new Save();
	public PaperViewer(){
	    surveyList = read.readPageList()[0];
	    testList = read.readPageList()[1]; 
	}
	public static void addNewSurvey(Survey page){
		surveyList.add(page);
		surveyNameList.add(page.getPaperName());
	}

	public static void addNewTest(Survey page){
		testList.add(page);
	    testNameList.add(page.getPaperName());
	}
	
	public static void addNewSurveyRecord(Survey page){
		surveyRecordList.add(page);
		surveyNameRecordList.add(page.getAttender());
	}

	public static void addNewTestRecord(Survey page){
		testRecordList.add(page);
	    testNameRecordList.add(page.getAttender());
	}

	public static List<String> getSurveyList(){
		List<String> list = new LinkedList<String>();
		if(surveyList.size()==0){
			return list;
		}
		for(int i=0;i<surveyList.size();i++){
			list.add(surveyList.get(i).getPaperName());
		}
		return list;
	}

	public static List<String> getTestList(){
		List<String> list = new LinkedList<String>();
		if(testList.size()==0){
			return list;
		}
		for(int i=0;i<testList.size();i++){
			list.add(testList.get(i).getPaperName());
		}
		return list;
	}
	
	public static List<String> getSurveyRecordList(){
		List<String> list = new LinkedList<String>();
		if(surveyRecordList.size()==0){
			return list;
		}
		for(int i=0;i<surveyRecordList.size();i++){
			list.add(surveyRecordList.get(i).getPaperName());
		}
		return list;
	}

	public static List<String> getTestRecordList(){
		List<String> list = new LinkedList<String>();
		if(testRecordList.size()==0){
			return list;
		}
		for(int i=0;i<testRecordList.size();i++){
			list.add(testRecordList.get(i).getPaperName());
		}
		return list;
	}

	public List<String> showPaperRecord(String name){
		return Read.readRecordInfo(name);
	}
	public List<String> getPageName(int type){
		return pageNameList[type];
	}

	public List<String>[] getPageNameList(){
		return pageNameList;
	}
	
	public static List<String> getSurveyNameList(){
		return surveyNameList;
	}
	
	public static List<String> getTestNameList(){
		return testNameList;
	}
	
	public static List<String> getSurveyNameRecordList(){
		return surveyNameRecordList;
	}
	
	public static List<String> getTestNameRecordList(){
		return testNameRecordList;
	}
	
	public List<String> getRecordList(){
		return recordName;
	}
	public void addPaper(String name){
		
	}
}
