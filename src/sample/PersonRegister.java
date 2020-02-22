package sample;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonRegister {

    ArrayList<Person> personArrayList = new ArrayList<>();
    String stringPath = new File("PersonRegistryFile.txt").getPath();
    Path realPath = Paths.get(stringPath);

    public void createPerson(String fName, String lName, int age, LocalDate bDay, String email, String celNum) {
        Person person = new Person(fName, lName, age, bDay, email, celNum);
        personArrayList.add(person);
        try {
            savePersonToFile(personArrayList, realPath);
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

    public void savePersonToFile(ArrayList<Person> array, Path path) throws IOException {
        Files.write(path, array.toString().getBytes(), StandardOpenOption.APPEND);
    }

    public void saveFileContentToArray() {
        Scanner scanner = new Scanner(stringPath);
        while (scanner.hasNextLine()) {
            //A line from the file is saved in a string
            String line = scanner.nextLine();
            //Each word in the string represents an attribute and is separated into an array
            String[] personStringToObject = line.split(",");

            //Each attribute is saved from the array into it's own variable
            String firstNameData = personStringToObject[0];
            String lastNameData = personStringToObject[1];
            //Formatter is needed to save birthday as LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthdayData = LocalDate.parse(personStringToObject[2], formatter);
            String emailData = personStringToObject[3];
            String celNumData = personStringToObject[4];
            int ageData = Integer.parseInt(personStringToObject[5]);

            //A person is constructed from the attributes
            Person person = new Person(firstNameData, lastNameData, ageData, birthdayData, emailData, celNumData);
            personArrayList.add(person);
        }
    }

    public int getNewestPersonIndex() {
        saveFileContentToArray();
        int index;
        if(personArrayList.size() == 0) {
            //If array is empty, method will return an index of 0...
            index = 0;
        } else {
            //otherwise it will return the highest index in the array.
            index = personArrayList.size() - 2;
        }
        return index;
    }
}
