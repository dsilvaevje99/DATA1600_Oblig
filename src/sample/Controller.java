package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    DatePicker birthday;

    @FXML
    TextField FirstName, age, email, celNum, lastName;

    @FXML
    TableColumn<DataModel, String> tableFirstName, tableLastName, tableAge, tableBirthday, tableEmail, tableCelNum;




}