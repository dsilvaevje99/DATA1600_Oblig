package sample;

import org.xml.sax.ErrorHandler;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonRegister implements Serializable {
    ErrorHandler error;
    ArrayList<Person> personArrayList = new ArrayList<>();

    public void createPerson(String fName, String lName, int age, LocalDate bDay, String email, String celNum) {
        Person person = new Person(fName, lName, age, bDay, email, celNum);
        personArrayList.add(person);
    }
}