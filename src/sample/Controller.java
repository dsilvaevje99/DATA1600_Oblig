package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

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
/*
        //Add all people from file to table column on application launch
        Scanner scanner = new Scanner(register.stringPath);
        while (scanner.hasNextLine()) {
            //A line from the file is saved in a string
            String line = scanner.nextLine();
            //Each word in the string represents an attribute and is separated into an array
            String[] personStringToObject = line.split(",");

            //Each attribute is saved from the array into it's own variable
            String firstNameData = personStringToObject[0];
            String lastNameData = personStringToObject[1];
            String birthdayData = personStringToObject[2];
            String emailData = personStringToObject[3];
            String celNumData = personStringToObject[4];
            int ageData = Integer.parseInt(personStringToObject[5]);

            //A person is constructed from the attributes and added to the GUI table
            DataModel person = new DataModel(firstNameData, lastNameData, ageData, birthdayData, emailData, celNumData);
            if(person != null) {
                collection.addElement(person);
            }
        }
        scanner.close();
*/
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