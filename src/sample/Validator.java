package sample;

import java.time.LocalDate;
import java.time.Period;

public class Validator {

    public boolean validateName(String name){
        return name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");
    }

    public boolean validateEmail(String email){
        return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

    public boolean validateNumber(String number){
        return number.matches("^[\\d]{6,10}|(?:\\d{3}-){2}\\d{4}|(\\+)\\d{6,12}");
    }

    public boolean validateAge(LocalDate date){
        LocalDate now = LocalDate.now();
        Period diff = Period.between(date, now);

        if(date.isAfter(now)){
            return false;
        }else if (diff.getYears() > 130) {
            return false;
        }else {
            return true;
        }
    }
}