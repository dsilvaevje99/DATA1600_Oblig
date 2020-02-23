package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable{

    EmailValidator validator = new EmailValidator();
    DataCollection collection = new DataCollection();
    PersonRegister register = new PersonRegister();

    String value, query;


    @FXML
    DatePicker birthday;

    @FXML
    TextField firstName, email, celNum, lastName, fileNameBar, searchBar;

    @FXML
    TableView<DataModel> tableView;

    @FXML
    TableColumn<DataModel, String> tableFirstName, tableLastName, tableAge, tableBirthday, tableEmail, tableCelNum;

    @FXML
    ChoiceBox<String> searchCategory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.collection.linkTableView(this.tableView);
    }

    @FXML
    private void addElementBtnClicked() {

        try {

            if (containsNumber(firstName.getText())){
                firstName.setText("Can't contain numbers!");
                throw new IllegalArgumentException("First name can't contain numbers!");
            }
            if (containsNumber(lastName.getText())){
                lastName.setText("Can't contain numbers!");
                throw new IllegalArgumentException("Last name can't contain numbers!");
            }

            if (!validator.validateEmail(email.getText())){
                email.setText("Invalid email!");
                throw new IllegalArgumentException("Email is invalid!");
            }

            if (containsLetter(celNum.getText())){
                celNum.setText("Can't contain letters!");
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
        try{
            switch (value) {
                case "Name":
                    ArrayList<Person> nameList = register.personArrayList.stream()
                            .filter(p -> p.getfName().startsWith(query)).collect(Collectors.toCollection(ArrayList::new));
                    refreshList(nameList);
                    break;
                case "Age":
                    int age = Integer.parseInt(query);
                    ArrayList<Person> ageList = register.personArrayList.stream()
                            .filter(p -> p.getAge() == age ).collect(Collectors.toCollection(ArrayList::new));
                    refreshList(ageList);
                    break;
                case "Email":
                    ArrayList<Person> emailList = register.personArrayList.stream()
                            .filter(p -> p.getEmail() .startsWith(query)  ).collect(Collectors.toCollection(ArrayList::new));
                    refreshList(emailList);
                    break;
                case "Cel Number":
                    ArrayList<Person> numList = register.personArrayList.stream()
                            .filter(p -> p.getCelNum() .startsWith(query)  ).collect(Collectors.toCollection(ArrayList::new));
                    refreshList(numList);
                    break;
            }
        }catch (Exception e){
            searchBar.setText("Invalid query!");
        }

    }

    @FXML
    private void saveBtnClicked(){
        String filename = fileNameBar.getText();
        if (filename.equals("")){
            fileNameBar.setText("Invalid filename!");
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(filename + ".tmp");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(register.personArrayList);
            }catch (Exception e){
                System.out.println("bro");
            }
        }
    }

    @FXML
    public void loadBtnClicked() {
        try {
            String filename = fileNameBar.getText();
            FileInputStream fis = new FileInputStream(filename + ".tmp");
            ObjectInputStream ois =  new ObjectInputStream(fis);
            register.personArrayList =  (ArrayList<Person>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e){
            fileNameBar.setText("File not found!");
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

    private boolean containsNumber(String string){
        char[] chars = string.toCharArray();
        for(char c : chars){
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }

    private boolean containsLetter(String string){
        char[] chars = string.toCharArray();
        for(char c : chars){
            if(Character.isLetter(c)){
                return true;
            }
        }
        return false;
    }


}