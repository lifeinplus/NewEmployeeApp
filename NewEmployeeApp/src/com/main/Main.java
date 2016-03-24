package com.main;

import com.data.CreatedEmail;
import com.ui.View;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Отключаем жирные шрифты в приложении
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                View view = new View();

                CreatedEmail createdEmail;

                try (FileInputStream file = new FileInputStream("res\\createdemail.ser");
                     ObjectInputStream object = new ObjectInputStream(file)) {
                    createdEmail = (CreatedEmail) object.readObject();

                    view.setTextFieldEmployeeName(createdEmail.getEmployeeName());
                    view.setTextFieldWorkPlace(createdEmail.getWorkPlace());
                    view.setTextFieldChief(createdEmail.getChief());
                    view.setComboBoxHRManagers(createdEmail.getHrManager());
                    view.setTextFieldLogin(createdEmail.getLogin());
                    view.setTextFieldPassword(createdEmail.getPassword());
                } catch (FileNotFoundException fnf) {
                    view.clear();
                } catch (ClassNotFoundException cnf) {
                    cnf.printStackTrace();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        });
    } // end main()
}