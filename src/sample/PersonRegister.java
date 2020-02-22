package sample;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonRegister {

    ArrayList<Person> personArrayList = new ArrayList<>();
    String stringPath = new File("PersonRegistryFile.txt").getPath();
    Path realPath = Paths.get(stringPath);

    public void createPerson(String fName, String lName, int age, LocalDate bDay, String email, String celNum) {
        Person person = new Person(fName, lName, age, bDay, email, celNum);
        personArrayList.add(person);
        try {
            savePersonToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Person getPerson(int index) {
        String firstNameData = personArrayList.get(index).getfName();
        String lastNameData = personArrayList.get(index).getlName();
        LocalDate birthdayData = personArrayList.get(index).getbDay();
        String emailData = personArrayList.get(index).getEmail();
        String celNumData = personArrayList.get(index).getCelNum();
        int ageData = personArrayList.get(index).getAge();

        Person person = new Person(firstNameData, lastNameData, ageData, birthdayData, emailData, celNumData);
        return person;
    }

    public void savePersonToFile() throws IOException {
        FileWriter fileWriter = null;

        String firstNameData = personArrayList.get(getNewestPersonIndex()).getfName();
        String lastNameData = personArrayList.get(getNewestPersonIndex()).getlName();
        LocalDate birthdayData = personArrayList.get(getNewestPersonIndex()).getbDay();
        String emailData = personArrayList.get(getNewestPersonIndex()).getEmail();
        String celNumData = personArrayList.get(getNewestPersonIndex()).getCelNum();
        int ageData = personArrayList.get(getNewestPersonIndex()).getAge();

        String data = firstNameData + "," + lastNameData + "," + birthdayData + "," + emailData + "," + celNumData + "," + ageData + "\n";
        File file = new File(stringPath);
        fileWriter = new FileWriter(file);
        fileWriter.write(data);
        fileWriter.close();
    }

    public int getNewestPersonIndex() {
        int index = personArrayList.size() - 1;
        return index;
    }
}
