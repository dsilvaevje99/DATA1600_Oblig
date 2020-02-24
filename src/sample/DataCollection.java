package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.ArrayList;


public class DataCollection {

    private ObservableList<DataModel> list = FXCollections.observableArrayList();

    public void linkTableView(TableView<DataModel> tv) {
        tv.setItems(list);
    }

    public void addElement(DataModel obj) {
        list.add(obj);
    }

    public void clearList(){
        list.clear();
    }

    public ObservableList<DataModel> getList() {
        return list;
    }


}
