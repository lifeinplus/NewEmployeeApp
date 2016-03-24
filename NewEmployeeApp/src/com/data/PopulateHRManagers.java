package com.data;

import com.ui.View;

import javax.swing.*;
import java.io.*;

public class PopulateHRManagers {

    private View parent;

    public PopulateHRManagers(View parent) {
        this.parent = parent;
    }

    public void populateHRManagers() {
        try (FileInputStream myFile = new FileInputStream("res//hrManagers.txt");
             InputStreamReader inputStreamReader = new InputStreamReader(myFile, "UTF8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String nextLine;
            boolean eof = false;

            while (!eof) {
                nextLine = bufferedReader.readLine();

                if (nextLine == null) {
                    eof = true;
                } else {
                    parent.setVectorHRManagers(nextLine);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Не удалось заполнить поле \"HR-менеджер\"");
        }
    } // end method populateHRManagers

}
