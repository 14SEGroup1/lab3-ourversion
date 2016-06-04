package Question;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Qizixi on 5/29/2016.
 */
public class MapQuestion extends Question{

    protected List<String> LeftItems = new LinkedList<String>();
    protected List<String> RightItems = new LinkedList<String>();

    private void addItem(String inputItem,List<String>Items) {
        Items.add(inputItem);
    }

    public void addLeftItem(String inputItem){
        addItem(inputItem,LeftItems);
    }

    public void addRightItem(String inputItem){
        addItem(inputItem,RightItems);
    }

    private void changeItem(int index,String newItem,List<String>Items){
        Items.set(index,newItem);
    }

    public void changeLeftItem(int index,String newItem){
        changeItem(index,newItem,LeftItems);
    }

    public void changeRightItem(int index,String newItem){
        changeItem(index,newItem,RightItems);
    }

    /*public void deleteItem(int index){
        Items.remove(index);
    }

    public String[] getAllItems(){
        return Items.toArray(new String[Items.size()]);
    }*/

    public void addLeftItemList(String[] inputStrings){
        for(String current:inputStrings){
            addLeftItem(current);
        }
    }

    public void addRightItemList(String[] inputStrings){
        for(String current:inputStrings){
            addRightItem(current);
        }
    }

    @Override
    public String getQuestion() {
        String items = "";
        int i = 0;
        for(String temp:LeftItems){
            items = items + i++ + "." + temp +" ";
        }
        for(String temp:RightItems){
            items = items + i++ + "." + temp +" ";
        }
        return "Map: "+prompt+" "+items;
    }

    @Override
    public String getQuestionType() {
        return "map";
    }

    public List<String> getLeftItem() {
        // TODO Auto-generated method stub
        return LeftItems;
    }
    public List<String> getRightItem() {
        // TODO Auto-generated method stub
        return RightItems;
    }

}
