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

    ArrayList<String> personArrayList = new ArrayList<>();
    String stringPath = new File("PersonRegistryFile.txt").getPath();
    Path realPath = Paths.get(stringPath);

    public void createPerson(String fName, String lName, int age, LocalDate bDay, String email, String celNum) {
        Person person = new Person(fName, lName, age, bDay, email, celNum);
        personArrayList.add(person.toString());
        try {
            savePersonToFile(personArrayList, realPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Person getPerson(int index) {
        String personString = personArrayList.get(index);
        String[] personArray = personString.split(" ");

        String firstNameData = personArray[0];
        String lastNameData = personArray[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthdayData = LocalDate.parse(personArray[2], formatter);
        String emailData = personArray[3];
        String celNumData = personArray[4];
        int ageData = Integer.parseInt(personArray[5]);

        Person person = new Person(firstNameData, lastNameData, ageData, birthdayData, emailData, celNumData);
        return person;
    }

    public void savePersonToFile(ArrayList<String> array, Path path) throws IOException {
        Files.write(path, array.toString().getBytes(), StandardOpenOption.APPEND);
    }

    public void saveFileContentToArray() {
        Scanner scanner = new Scanner(stringPath);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            personArrayList.add(line);
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
            index = personArrayList.size() - 1;
        }
        return index;
    }
}
