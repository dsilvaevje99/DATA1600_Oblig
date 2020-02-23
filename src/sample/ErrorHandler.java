package sample;

import javax.swing.*;

public class ErrorHandler {
    public static void alert(String title, String message)
    {
        //User friendly error alert:
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
