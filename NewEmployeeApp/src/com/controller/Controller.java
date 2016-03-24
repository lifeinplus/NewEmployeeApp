package com.controller;

import com.data.CreatedEmail;
import com.ui.View;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class Controller implements ActionListener, DropTargetListener {

    public static final String OPEN = "OPEN";
    public static final String SAVE = "SAVE";
    public static final String SEND = "SEND";
    public static final String EXIT = "EXIT";
    public static final String CLEAR = "CLEAR";
    public static final String ABOUT = "ABOUT";

    private View parent;

    public Controller(View parent) {
        this.parent = parent;
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser("C:\\");
        int ret = fileChooser.showDialog(null, "Open");

        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            parseFile(file);
        }
    } // end openFile()

    private void parseFile(File file) {
        parent.clear();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList nodeList = document.getElementsByTagName("my:New_Employee");

            for (int i = 0; i < nodeList.getLength(); i++) {
                org.w3c.dom.Element element;
                String lastName, firstName, workStart, workPlace, chief, hrManager;

                org.w3c.dom.Node node = nodeList.item(i);

                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    element = (org.w3c.dom.Element) node;

                    lastName = element.getElementsByTagName("my:LastNameR").item(0).getTextContent();
                    firstName = element.getElementsByTagName("my:NameR").item(0).getTextContent();
                    workStart = element.getElementsByTagName("my:WorkStart").item(0).getTextContent();
                    workPlace = element.getElementsByTagName("my:LocationSD").item(0).getTextContent();
                    chief = element.getElementsByTagName("my:LeaderSD").item(0).getTextContent();
                    hrManager = element.getElementsByTagName("my:InitiatorSD").item(0).getTextContent();

                    parent.setTextFieldEmployeeName(lastName + " " + firstName + " " + workStart);
                    parent.setTextFieldWorkPlace(workPlace);
                    parent.setTextFieldChief(chief);

                    switch (hrManager) {
                        case "0000000812":
                            parent.setComboBoxHRManagers(3);
                            break;
                        case "0000001530":
                            parent.setComboBoxHRManagers(1);
                            break;
                        case "0000005857":
                            parent.setComboBoxHRManagers(0);
                            break;
                        case "1000002228":
                            parent.setComboBoxHRManagers(2);
                            break;
                    }
                } // end if
            }
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void saveData() {
        CreatedEmail createdEmail = new CreatedEmail();

        createdEmail.setEmployeeName(parent.getTextFieldEmployeeName());
        createdEmail.setWorkPlace(parent.getTextFieldWorkPlace());
        createdEmail.setChief(parent.getTextFieldChief());
        createdEmail.setHrManager(parent.getComboBoxHRManagers());
        createdEmail.setLogin(parent.getTextFieldLogin());
        createdEmail.setPassword(parent.getTextFieldPassword());

        try (FileOutputStream file = new FileOutputStream("res\\createdemail.ser");
             ObjectOutputStream object = new ObjectOutputStream(file)) {
            object.writeObject(createdEmail);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void createEmail() {
        try (FileOutputStream file = new FileOutputStream("res//createdEmail.txt");
             BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(file, "UTF8"))) {

            String separator = System.getProperty("line.separator");

            buffWriter.write("Кому: " + parent.getComboBoxHRManagers() + ";" + separator);
            buffWriter.write("Копия: " + parent.getTextFieldChief() + "; helpdesk@ocs.ru;" + separator + separator);
            buffWriter.write("Новый сотрудник: " + parent.getTextFieldEmployeeName() + separator + separator);
            buffWriter.write("Рабочее место подготовлено для сотрудника." + separator);
            buffWriter.write("Кабинет: " + parent.getTextFieldWorkPlace() + separator);
            buffWriter.write("Логин: " + parent.getTextFieldLogin() + separator);
            buffWriter.write("Пароль: " + parent.getTextFieldPassword() + separator + separator);
            buffWriter.write("При необходимости сменить пароль, выполните следующие действия:"
                    + separator
                    + "\u0009- находясь в системе, нажмите Ctrl+Alt+Delete и выберите команду \"Сменить пароль\";"
                    + separator
                    + "\u0009- введите старый пароль, новый пароль и повторите ввод нового пароля.");
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "Не удалось внести данные в файл \"createdEmail.txt\"");
        }

        try {
            // Open the file "createdEmail.txt" with Notepad
            Process process = Runtime.getRuntime().exec("cmd /c notepad.exe res//createdEmail.txt");
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "Не удалось открыть файл с результатом.");
        }
    } // end createEmail()

    private void closeApp() {
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case OPEN:
                openFile();
                break;

            case SAVE:
                saveData();
                break;

            case SEND:
                createEmail();
                break;

            case EXIT:
                closeApp();
                break;

            case CLEAR:
                parent.clear();
                break;

            default:
                break;
        }
    } // end actionPerformed()

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        // Accept copy drops
        dtde.acceptDrop(DnDConstants.ACTION_COPY);

        // Get the transfer which can provide the dropped item data
        Transferable transferable = dtde.getTransferable();

        // Get the data formats of the dropped item
        DataFlavor[] flavors = transferable.getTransferDataFlavors();

        // Loop through the flavors
        for (DataFlavor flavor : flavors) {

            try {

                // If the drop items are files
                if (flavor.isFlavorJavaFileListType()) {

                    // Get all of the dropped files
                    List<File> files = (List) transferable.getTransferData(flavor);

                    // Loop them through
                    for (File file : files) {
                        parseFile(file);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Inform that the drop is complete
        dtde.dropComplete(true);
    }

}