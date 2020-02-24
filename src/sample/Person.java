package sample;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    private String fName;
    private String lName;
    private int age;
    private LocalDate bDay;
    private String email;
    private String celNum;

    public Person() {
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.bDay = bDay;
        this.email = email;
        this.celNum = celNum;
    }

    public Person(String fName, String lName, int age, LocalDate bDay, String email, String celNum) {
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.bDay = bDay;
        this.email = email;
        this.celNum = celNum;
    }


    public String getfName() {
        return fName;
    }

    public String getlName() { return lName; }

    public int getAge() {
        return age;
    }

    public LocalDate getbDay() {
        return bDay;
    }

    public String getEmail() {
        return email;
    }

    public String getCelNum() {
        return celNum;
    }



    public void setfName(String fName) { this.fName = fName; }

    public void setlName(String lName) { this.lName = lName; }

    public void setAge(int age) { this.age = age; }

    public void setbDay(LocalDate bDay) { this.bDay = bDay; }

    public void setEmail(String email) { this.email = email; }

    public void setCelNum(String celNum) { this.celNum = celNum; }

    @Override
    public String toString() {
        return "Person{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", age=" + age +
                ", bDay=" + bDay +
                ", email='" + email + '\'' +
                ", celNum='" + celNum + '\'' +
                '}';
    }
}
