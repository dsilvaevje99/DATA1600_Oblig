package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable{

    FileChooser fileChooser = new FileChooser();
    Validator validator = new Validator();
    DataCollection collection = new DataCollection();
    PersonRegister register = new PersonRegister();

    String value, query;


    @FXML
    DatePicker birthday;

    @FXML
    TextField firstName, email, celNum, lastName,  searchBar;

    @FXML
    TableView<DataModel> tableView;

    @FXML
    TableColumn<DataModel, String> tableFirstName, tableLastName, tableAge, tableBirthday, tableEmail, tableCelNum;

    @FXML
    ChoiceBox<String> searchCategory;
    private Window window;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.collection.linkTableView(this.tableView);
        startup();
    }

    @FXML
    private void addElementBtnClicked() {

        try {

            if (!validator.validateName(firstName.getText())){
                firstName.setText("Invalid name!");
                throw new IllegalArgumentException("First name can't contain numbers!");
            }
            if (!validator.validateName(lastName.getText())){
                lastName.setText("Invalid name!");
                throw new IllegalArgumentException("Last name can't contain numbers!");
            }

            if(!validator.validateAge(birthday.getValue())){
                birthday.getEditor().setText("Invalid age!");
                throw new IllegalArgumentException("Age is invalid!");
            }

            if (!validator.validateEmail(email.getText())){
                email.setText("Invalid email!");
                throw new IllegalArgumentException("Email is invalid!");
            }

            if (!validator.validateNumber(celNum.getText())){
                celNum.setText("Invalid number!");
                throw new IllegalArgumentException("Telephone number is invalid!");
            }

            register.createPerson(firstName.getText(), lastName.getText(), calculateAge(birthday), birthday.getValue(), email.getText(), celNum.getText());
            if(register != null) {
                resetAllFields();
                refreshList(register.personArrayList);
            }else {
                System.out.println("object is null!");
            }
        }catch (IllegalArgumentException e){
            System.out.println("Exception occured: " + e);
        }
    }

    @FXML
    private void searchBtnClicked(){
        value = searchCategory.getValue();
        query = searchBar.getText();
        filterList(value, query);
    }

    @FXML
    private void saveBtnClicked(){
            try {
                fileChooser.setTitle("Choose File");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Temporary files", "*tmp"));
                FileOutputStream fos = new FileOutputStream(fileChooser.showSaveDialog(window));
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(register.personArrayList);
            }catch (Exception e){
                System.out.println(e);
            }
    }

    @FXML
    public void loadBtnClicked() {
        try {
            fileChooser.setTitle("Choose File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Temporary files", "*tmp"));
            FileInputStream fis = new FileInputStream(fileChooser.showOpenDialog(window));
            ObjectInputStream ois =  new ObjectInputStream(fis);
            register.personArrayList =  (ArrayList<Person>) ois.readObject();
            ois.close();
        } catch (Exception e){
            System.out.println(e);
        }
        refreshList(register.personArrayList);
    }

    @FXML
    public void startup() {
        try {
            FileInputStream fis = new FileInputStream("SampleList.tmp");
            ObjectInputStream ois =  new ObjectInputStream(fis);
            register.personArrayList =  (ArrayList<Person>) ois.readObject();
            ois.close();
        } catch (Exception e){
            System.out.println(e);
        }
        refreshList(register.personArrayList);
    }

    private DataModel createDataModelObjectFromGUI(int index, ArrayList<Person> register) {
        String firstNameData = register.get(index).getfName();
        String lastNameData = register.get(index).getlName();
        String birthdayData = register.get(index).getbDay().toString();
        String emailData = register.get(index).getEmail();
        String celNumData = register.get(index).getCelNum();
        int ageData = register.get(index).getAge();

        try {
            return new DataModel(firstNameData, lastNameData, ageData, birthdayData, emailData, celNumData);
        } catch (IllegalArgumentException e) {
            System.out.println("error in creating new data model: " + e);
            return null;
        }
    }

    private void filterList(String value, String query){

        ArrayList<Person> filteredList = new ArrayList<>();
        try{
            switch (value) {
                case "Name":
                    filteredList = register.personArrayList.stream()
                            .filter(p -> p.getfName().startsWith(query)).collect(Collectors.toCollection(ArrayList::new));
                    break;
                case "Age":
                    int age = Integer.parseInt(query);
                    filteredList = register.personArrayList.stream()
                            .filter(p -> p.getAge() == age ).collect(Collectors.toCollection(ArrayList::new));
                    break;
                case "Email":
                    filteredList = register.personArrayList.stream()
                            .filter(p -> p.getEmail() .contains(query)  ).collect(Collectors.toCollection(ArrayList::new));
                    break;
                case "Cel Number":
                    filteredList = register.personArrayList.stream()
                            .filter(p -> p.getCelNum() .contains(query)  ).collect(Collectors.toCollection(ArrayList::new));
                    break;
            }
            refreshList(filteredList);
        }catch (Exception e){
            searchBar.setText("Invalid query!");
        }
    }

    private void refreshList(ArrayList<Person> register){
        collection.clearList();
        tableView.getItems().clear();
        for (int i = 0; i < register.size() ; i++) {
            DataModel obj = createDataModelObjectFromGUI(i, register);
            collection.addElement(obj);
        }
        tableView.setItems(collection.getList());
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