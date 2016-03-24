package com.ui;

import com.controller.Controller;
import com.data.PopulateHRManagers;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.*;
import java.util.Vector;

public class View extends JFrame {

    private final Controller controller;

    private JPanel panel;
    private JLabel labelEmployeeName;
    private JLabel labelWorkPlace;
    private JLabel labelChief;
    private JLabel labelHRManager;
    private JLabel labelLogin;
    private JLabel labelPassword;
    private JTextField textFieldEmployeeName;
    private JTextField textFieldWorkPlace;
    private JTextField textFieldChief;
    private JTextField textFieldLogin;
    private JTextField textFieldPassword;
    private Vector vectorHRManagers;
    private JComboBox comboBoxHRManagers;
    private JButton buttonClear;
    private JButton buttonSave;
    private JButton buttonSend;
    private ImageIcon iconClear;
    private ImageIcon iconSave;
    private ImageIcon iconSend;


    //    Constructor creates the components
    //    and adds they to the frame using of GridBagLayout
    public View() {
        super("New Employee");

        Controller controller = new Controller(this);
        this.controller = controller;

//        Create panel and set the layout manager
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

//        Create the components
        labelEmployeeName = new JLabel("Имя сотрудника:");
        labelWorkPlace = new JLabel("Рабочее место:");
        labelChief = new JLabel("Руководитель:");
        labelHRManager = new JLabel("HR-менеджер:");
        labelLogin = new JLabel("Логин:");
        labelPassword = new JLabel("Пароль:");
        textFieldEmployeeName = new JTextField("", 18);
        textFieldWorkPlace = new JTextField("", 18);
        textFieldChief = new JTextField("", 18);
        textFieldLogin = new JTextField("", 18);
        textFieldPassword = new JTextField("", 18);
        vectorHRManagers = new Vector(50);
        comboBoxHRManagers = new JComboBox(vectorHRManagers);
        buttonClear = new JButton("");
        buttonSave = new JButton("");
        buttonSend = new JButton("");
        iconClear = new ImageIcon("res/img/clear32.png");
        iconSave = new ImageIcon("res/img/save32.png");
        iconSend = new ImageIcon("res/img/send32.png");

        buttonClear.setIcon(iconClear);
        buttonSave.setIcon(iconSave);
        buttonSend.setIcon(iconSend);

        buttonClear.setActionCommand(Controller.CLEAR);
        buttonSave.setActionCommand(Controller.SAVE);
        buttonSend.setActionCommand(Controller.SEND);

        // Set the listener for the buttons
        buttonClear.addActionListener(controller);
        buttonSave.addActionListener(controller);
        buttonSend.addActionListener(controller);

        new DropTarget(panel, controller);
        new DropTarget(textFieldEmployeeName, controller);

        Insets lblInsets = new Insets(8,10,0,5);
        panel.add(labelEmployeeName, new GridBagConstraints(0,0, 1,1, 1,1, GridBagConstraints.EAST,
                GridBagConstraints.NONE, lblInsets, 0,0));
        panel.add(labelWorkPlace, new GridBagConstraints(0,1, 1,1, 1,1, GridBagConstraints.EAST,
                GridBagConstraints.NONE, lblInsets, 0,0));
        panel.add(labelChief, new GridBagConstraints(0,2, 1,1, 1,1, GridBagConstraints.EAST,
                GridBagConstraints.NONE, lblInsets, 0,0));
        panel.add(labelHRManager, new GridBagConstraints(0,3, 1,1, 1,1, GridBagConstraints.EAST,
                GridBagConstraints.NONE, lblInsets, 0,0));
        panel.add(labelLogin, new GridBagConstraints(0,4, 1,1, 1,1, GridBagConstraints.EAST,
                GridBagConstraints.NONE, lblInsets, 0,0));
        panel.add(labelPassword, new GridBagConstraints(0,5, 1,1, 1,1, GridBagConstraints.EAST,
                GridBagConstraints.NONE, lblInsets, 0,0));

        Insets txtInsets = new Insets(7,6,0,10);
        panel.add(textFieldEmployeeName, new GridBagConstraints(1,0, 2,1, 9,1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, txtInsets, 0,4));
        panel.add(textFieldWorkPlace, new GridBagConstraints(1,1, 2,1, 1,1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, txtInsets, 0,4));
        panel.add(textFieldChief, new GridBagConstraints(1,2, 2,1, 1,1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, txtInsets, 0,4));
        panel.add(textFieldLogin, new GridBagConstraints(1,4, 2,1, 1,1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, txtInsets, 0,4));
        panel.add(textFieldPassword, new GridBagConstraints(1,5, 2,1, 1,1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, txtInsets, 0,4));

        panel.add(comboBoxHRManagers, new GridBagConstraints(1,3, 2,1, 1,1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(7,6,0,11), 0,0));

        panel.add(buttonClear, new GridBagConstraints(0,6, 3,1, 1,1, GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(6,11,5,168), 4,0));
        panel.add(buttonSave, new GridBagConstraints(0,6, 3,1, 1,1, GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(6,0,6,89), 4,0));
        panel.add(buttonSend, new GridBagConstraints(0,6, 3,1, 1,1, GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(6,0,5,11), 4,0));

//        Setting font fot the buttons
        Font fontCorbel = new Font("Corbel", Font.PLAIN, 14);
        labelEmployeeName.setFont(fontCorbel);
        labelWorkPlace.setFont(fontCorbel);
        labelChief.setFont(fontCorbel);
        labelHRManager.setFont(fontCorbel);
        labelLogin.setFont(fontCorbel);
        labelPassword.setFont(fontCorbel);
        textFieldEmployeeName.setFont(fontCorbel);
        textFieldWorkPlace.setFont(fontCorbel);
        textFieldChief.setFont(fontCorbel);
        textFieldLogin.setFont(fontCorbel);
        textFieldPassword.setFont(fontCorbel);
        comboBoxHRManagers.setFont(fontCorbel);

//        Setting padding inside text fields
        Insets insetsMargin = new Insets(0, 4, 0, 4);
        textFieldEmployeeName.setMargin(insetsMargin);
        textFieldWorkPlace.setMargin(insetsMargin);
        textFieldChief.setMargin(insetsMargin);
        textFieldLogin.setMargin(insetsMargin);
        textFieldPassword.setMargin(insetsMargin);

//        Setting padding inside combo box
        ((JLabel) comboBoxHRManagers.getRenderer()).setBorder(BorderFactory.createEmptyBorder(1, 5, 0, 0));

//        Define, instantiate and register a WindowAdapter
//        to process windowClosing Event of this frame
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

//        Close the frame by pressing the Esc
        KeyStroke escKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

//        Filling the list of mailboxes from a file
        PopulateHRManagers populate = new PopulateHRManagers(this);
        populate.populateHRManagers();
        comboBoxHRManagers.setSelectedIndex(0);

//        Set the frame content
        setContentPane(panel);
        setJMenuBar(menuBar());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // open frame in the center of the screen
        setVisible(true);
    } // end constructor View()

    private JMenuBar menuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        JMenu menuEdit = new JMenu("Edit");
        JMenu menuHelp = new JMenu("Help");

        menuFile.setMnemonic(KeyEvent.VK_F);
        menuEdit.setMnemonic(KeyEvent.VK_E);
        menuHelp.setMnemonic(KeyEvent.VK_H);

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);

        JMenuItem open = new JMenuItem("Open", KeyEvent.VK_O);
        JMenuItem save = new JMenuItem("Save", KeyEvent.VK_S);
        JMenuItem send = new JMenuItem("Send", KeyEvent.VK_N);
        JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_Q);
        JMenuItem clear = new JMenuItem("Clear", KeyEvent.VK_C);
        JMenuItem about = new JMenuItem("About", KeyEvent.VK_A);

        open.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.ALT_DOWN_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.ALT_DOWN_MASK));
        send.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.ALT_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.ALT_DOWN_MASK));
        clear.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.ALT_DOWN_MASK));

        menuFile.add(open);
        menuFile.add(save);
        menuFile.add(send);
        menuFile.add(exit);
        menuEdit.add(clear);
        menuHelp.add(about);

        open.setActionCommand(Controller.OPEN);
        save.setActionCommand(Controller.SAVE);
        send.setActionCommand(Controller.SEND);
        exit.setActionCommand(Controller.EXIT);
        clear.setActionCommand(Controller.CLEAR);
        about.setActionCommand(Controller.ABOUT);

        open.addActionListener(controller);
        save.addActionListener(controller);
        send.addActionListener(controller);
        exit.addActionListener(controller);
        clear.addActionListener(controller);
        about.addActionListener(controller);

        return menuBar;
    } // end menuBar()


    public String getTextFieldEmployeeName() {
        return textFieldEmployeeName.getText();
    }

    public void setTextFieldEmployeeName(String textFieldEmployeeName) {
        this.textFieldEmployeeName.setText(textFieldEmployeeName);
    }

    public String getTextFieldWorkPlace() {
        return textFieldWorkPlace.getText();
    }

    public void setTextFieldWorkPlace(String textFieldWorkPlace) {
        this.textFieldWorkPlace.setText(textFieldWorkPlace);
    }

    public String getTextFieldChief() {
        return textFieldChief.getText();
    }

    public void setTextFieldChief(String textFieldChief) {
        this.textFieldChief.setText(textFieldChief);
    }

    public String getTextFieldLogin() {
        return textFieldLogin.getText();
    }

    public void setTextFieldLogin(String textFieldLogin) {
        this.textFieldLogin.setText(textFieldLogin);
    }

    public String getTextFieldPassword() {
        return textFieldPassword.getText();
    }

    public void setTextFieldPassword(String textFieldPassword) {
        this.textFieldPassword.setText(textFieldPassword);
    }

    public void setComboBoxHRManagers(int i) {
        this.comboBoxHRManagers.setSelectedIndex(i);
    }

    public void setVectorHRManagers(String vectorHRManagers) {
        this.vectorHRManagers.add(vectorHRManagers);
    }

    public String getComboBoxHRManagers() {
        return (String) comboBoxHRManagers.getSelectedItem();
    }

    public void setComboBoxHRManagers(String hrManager) {
        comboBoxHRManagers.setSelectedItem(hrManager);
    }

    public void clear() {
        setTextFieldEmployeeName("");
        setTextFieldWorkPlace("");
        setTextFieldChief("");
        setComboBoxHRManagers(0);
        setTextFieldLogin("");
        setTextFieldPassword("");
    }

}