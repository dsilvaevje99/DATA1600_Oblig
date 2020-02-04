package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DataModel {
    private SimpleStringProperty fNameData;
    private SimpleStringProperty lNameData;
    private SimpleIntegerProperty ageData;
    private SimpleStringProperty birthdayData;
    private SimpleStringProperty emailData;
    private SimpleStringProperty celNumData;

    public DataModel(String fNameData, String lNameData, int ageData, String birthdayData, String emailData, String celNumData) {
        if(ageData < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        } else if(ageData > 130) {
            throw new IllegalArgumentException("Age cannot be above 130.");
        }

        this.fNameData = new SimpleStringProperty(fNameData);
        this.lNameData = new SimpleStringProperty(lNameData);
        this.ageData = new SimpleIntegerProperty(ageData);
        this.birthdayData = new SimpleStringProperty(birthdayData);
        this.emailData = new SimpleStringProperty(emailData);
        this.celNumData = new SimpleStringProperty(celNumData);
    }

    public String getfNameData() {
        return fNameData.getValue();
    }
    public String getlNameData() {
        return lNameData.getValue();
    }
    public int ageData() {
        return ageData.getValue();
    }
    public String getbirthdayData() {
        return birthdayData.getValue();
    }
    public String getemailData() {
        return emailData.getValue();
    }
    public String getcelNumData() {
        return celNumData.getValue();
    }

    public void setfNameData(String fNameData) { this.fNameData.set(fNameData); }
    public void setlNameData(String lNameData) { this.lNameData.set(lNameData); }
    public void setAgeData(int ageData) { this.ageData.set(ageData); }
    public void setBirthdayData(String birthdayData) { this.birthdayData.set(birthdayData); }
    public void setEmailData(String emailData) { this.emailData.set(emailData); }
    public void setCelNumData(String celNumData) { this.celNumData.set(celNumData); }
}
