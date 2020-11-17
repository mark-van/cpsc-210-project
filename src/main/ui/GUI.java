package ui;


import model.*;
import model.Victim;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


//much grabbed from LIStDwemo class
//Graphics user interface for atonement program
public class GUI extends JPanel implements ListSelectionListener {

    private JList list;
    private DefaultListModel listModel;
    private static final String JSON_STORE = "./data/taskmanager.json";
    private TaskManager user;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final String addString = "Add Task";
    private static final String failString = "Fail";
    private static final String accomplishString = "Accomplish";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private JButton failButton;
    private JButton accomplishButton;
    private JButton saveButton;
    private JButton loadButton;
    private JTextField victimsName;
    private JTextField victimsMessage;
    private JTextField taskTitle;
    private JTextField taskURL;
    private JLabel victimsNameL;
    private JLabel victimsMessageL;
    private JLabel taskTitleL;
    private JLabel taskURLl;

    // EFFECTS: runs the todolist application
    public GUI() {
        super(new BorderLayout());

        user = new TaskManager();
        listModel = new DefaultListModel();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        //Create the list and put it in a scroll pane.
        list = new JList(listModel);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        buttonHelper(addListener);
        //String name = listModel.getElementAt(list.getSelectedIndex()).toString();

        //fireButton.addActionListener(new FireListener());

        //Create a panel that uses BoxLayout.
        makePane(listScrollPane, addButton);
    }

    //EFFECTS: setups panels for GUI
    public void makePane(JScrollPane listScrollPane, JButton addButton) {
        JPanel buttonPane = new JPanel();
        JPanel taskSetup = new JPanel();
        victimsNameL = new JLabel("Victims Name");
        victimsMessageL = new JLabel("Warning message");
        taskTitleL = new JLabel("Task title");
        taskURLl = new JLabel("Task URL");

        taskSetup.setLayout(new BoxLayout(taskSetup, BoxLayout.Y_AXIS));
        taskSetup.add(victimsNameL);
        taskSetup.add(victimsName);
        taskSetup.add(victimsMessageL);
        taskSetup.add(victimsMessage);
        taskSetup.add(taskTitleL);
        taskSetup.add(taskTitle);
        taskSetup.add(taskURLl);
        taskSetup.add(taskURL);
        taskSetup.add(Box.createVerticalStrut(4));
        taskSetup.add(new JSeparator(SwingConstants.HORIZONTAL));
        taskSetup.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(listScrollPane, BorderLayout.CENTER);
        add(taskSetup, BorderLayout.PAGE_START);

        buttonPaneSetup(listScrollPane, addButton, buttonPane);
    }

    //EFFECTS: sets up button plane
    public void buttonPaneSetup(JScrollPane listScrollPane, JButton addButton, JPanel buttonPane) {
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(failButton);
        buttonPane.add(accomplishButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(addButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(loadButton);
        buttonPane.add(saveButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(buttonPane, BorderLayout.PAGE_END);
    }

    //EFFECTS: instantiate and setup global buttons
    public void buttonHelper(AddListener addListener) {
        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(new SaveListener());

        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new LoadListener());

        failButton = new JButton(failString);
        failButton.setActionCommand(failString);
        failButton.addActionListener(new FailListener());

        accomplishButton = new JButton(accomplishString);
        accomplishButton.setActionCommand(accomplishString);
        accomplishButton.addActionListener(new AccomplishListener());

        buttonHelper2(addListener);
    }

    //EFFECTS: instantiate and setup global buttons
    public void buttonHelper2(AddListener addListener) {
        victimsName = new JTextField(10);
        victimsName.addActionListener(addListener);
        victimsName.getDocument().addDocumentListener(addListener);

        victimsMessage = new JTextField(10);
        victimsMessage.addActionListener(addListener);
        victimsMessage.getDocument().addDocumentListener(addListener);

        taskTitle = new JTextField(10);
        taskTitle.addActionListener(addListener);
        taskTitle.getDocument().addDocumentListener(addListener);

        taskURL = new JTextField(10);
        taskTitle.addActionListener(addListener);
        taskTitle.getDocument().addDocumentListener(addListener);
    }

    // listener for save button
    class SaveListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: loads TaskManager from file
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(user);
                jsonWriter.close();
                // System.out.println("Saved task manager to " + JSON_STORE);
            } catch (FileNotFoundException efnfe) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    //listener for load button
    class LoadListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: loads TaskManager from file
        public void actionPerformed(ActionEvent e) {
            try {
                user = jsonReader.read();
                List<Task> loadedTasks = user.getTasks();
                for (Task t : loadedTasks) {
                    listModel.addElement(t.getTitle());
                }
                //listModel.addElement(taTitle);
                //System.out.println("Loaded task manager from " + JSON_STORE);
            } catch (IOException io) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    //listener for accomplish button
    class AccomplishListener implements ActionListener {

        // MODIFIES: this
        // Effects: conducts the accomplishing of a task
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();
            listModel.remove(index);
            user.accomplishTask(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                failButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    //listener for fail button
    class FailListener implements ActionListener {

        // MODIFIES: this
        // Effects: conducts the failure of a task
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();
            listModel.remove(index);
            user.failTask(index);

            ImageIcon icon = createImageIcon("images/middle.gif", "a pretty but meaningless splat");


            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                failButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    //https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
    //grabbed code from recommended oracle tutorial project
    //EFFECTS: creates an image with a message
    public ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    //This listener is shared by the text field and the add task button.
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        //MODIFIES: this
        //EFFECTS: adds task to tasks list
        public void actionPerformed(ActionEvent e) {
            String vicName = victimsName.getText();
            String vicMessage = victimsMessage.getText();
            String taTitle = taskTitle.getText();
            String taURL = taskURL.getText();

            //User didn't fill in a field or repeated title
            if (vicName.equals("") || vicMessage.equals("") || taTitle.equals("")
                    || taURL.equals("") || alreadyInList(taTitle)) {
                Toolkit.getDefaultToolkit().beep();  //noise cool!!!

                if (taTitle.equals("") || alreadyInList(taTitle)) {
                    taskTitle.requestFocusInWindow();
                    taskTitle.selectAll();
                }

                return;
            } else {
                user.addTask(new Task(new Victim(vicName, vicMessage), taTitle, taURL));
            }
            int index = list.getLastVisibleIndex(); //????

            //If we just wanted to add to the end, we'd do this:
            listModel.addElement(taTitle);

            //Reset the text fields.
            resetField();
            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }


        //EFFECTS: helper function that resets fields
        public void resetField() {
            victimsName.requestFocusInWindow();
            victimsName.setText("");
            victimsMessage.requestFocusInWindow();
            victimsMessage.setText("");
            taskTitle.requestFocusInWindow();
            taskTitle.setText("");
            taskURL.requestFocusInWindow();
            taskURL.setText("");
        }

        //This method tests for string equality. You could certainly
        //get more sophisticated about the algorithm.  For example,
        //you might want to ignore white space and capitalization.
        //EFFECTS: tests for string equality
        protected boolean alreadyInList(String taskTitle) {
            return listModel.contains(taskTitle);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        //EFFECTS: turn button of if button is not already enabled
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        //EFFECTS: turns button off if text field is empty
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //This method is required by listener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                failButton.setEnabled(false);
                accomplishButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                failButton.setEnabled(true);
                accomplishButton.setEnabled(true);
            }
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    //shows GUI
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new GUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    //just a main function to run our program with GUI
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    /*
                if (vicName.equals("")) {
                    victimsName.requestFocusInWindow();
                    victimsName.selectAll();
                } else if (vicMessage.equals("")) {
                    victimsMessage.requestFocusInWindow();
                    victimsMessage.selectAll();
                } else if (taTitle.equals("")) {
                    taskTitle.requestFocusInWindow();
                    taskTitle.selectAll();
                } else {
                    taskURL.requestFocusInWindow();
                    taskURL.selectAll();
                }
                */

}
