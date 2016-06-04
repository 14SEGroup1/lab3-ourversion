package Question;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Qizixi on 5/29/2016.
 */
public abstract class ItemQuestion extends Question{

    protected List<String> Items = new LinkedList<String>();
    protected int currentItem = 0;

    public void addItem(String inputItem) {
        Items.add(inputItem);
    }

    public String getItem(){
        return Items.get((currentItem++)%Items.size());
    }

    public void changeItem(int index,String newItem){
        Items.set(index,newItem);
    }

    public void deleteItem(int index){
        Items.remove(index);
    }

    public List<String> getAllItems(){
        return Items;
    }

    public void addItemList(String[] inputStrings){
        for(String current:inputStrings){
            addItem(current);
        }
    }

}
