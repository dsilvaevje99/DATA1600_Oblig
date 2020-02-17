package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    DataCollection collection = new DataCollection();
    PersonRegister register = new PersonRegister();


    @FXML
    DatePicker birthday;

    @FXML
    TextField firstName, email, celNum, lastName;

    @FXML
    TableView tableView;

    @FXML
    TableColumn<DataModel, String> tableFirstName, tableLastName, tableAge, tableBirthday, tableEmail, tableCelNum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collection.linkTableView(tableView);
    }

    @FXML
    private void addElementBtnClicked() {
        register.createPerson(firstName.getText(), lastName.getText(), calculateAge(birthday), birthday.getValue(), email.getText(), celNum.getText());
        DataModel obj = createDataModelObjectFromGUI(register.getNewestPersonIndex());

        if(obj != null) {
            resetAllFields();
            collection.addElement(obj);
        }
    }

    private DataModel createDataModelObjectFromGUI(int index) {
        String firstNameData = register.getPerson(index).getfName();
        String lastNameData = register.getPerson(index).getlName();
        String birthdayData = register.getPerson(index).getbDay().toString();
        String emailData = register.getPerson(index).getEmail();
        String celNumData = register.getPerson(index).getCelNum();
        int ageData = register.getPerson(index).getAge();
        try {
            return new DataModel(firstNameData, lastNameData, ageData, birthdayData, emailData, celNumData);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private void resetAllFields() {
        firstName.setText("");
        lastName.setText("");
        birthday.getEditor().clear();
        email.setText("");
        celNum.setText("");
    }

    private int calculateAge(DatePicker birthday) {
        LocalDate now = LocalDate.now();
        Period diff = Period.between(birthday.getValue(), now);
        return diff.getYears();
    }

}