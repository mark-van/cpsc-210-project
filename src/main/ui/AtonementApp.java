package ui;

import model.TaskManager;
import model.Victim;
import model.Task;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//This class is based on the code in The TellApp class from the TellApp project
//Atonement application
public class AtonementApp {
    private static final String JSON_STORE = "./data/taskmanager.json";
    private TaskManager user;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the teller application
    public AtonementApp() {
        runAtonement();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runAtonement() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes user's TaskManager
    private void init() {
        user = new TaskManager();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tt -> new task");
        System.out.println("\tc -> complete task");
        System.out.println("\tf -> fail task");
        System.out.println("\ts -> save task manager to file");
        System.out.println("\tv -> view todoList");
        System.out.println("\tl -> load task manager from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("t")) {
            doNewTask();
        } else if (command.equals("c")) {
            doAccomplishTask();
        } else if (command.equals("f")) {
            doFailTask();
        } else if (command.equals("v")) {
            displayTasks();
        } else if (command.equals("s")) {
            saveTaskManager();
        } else if (command.equals("l")) {
            loadTaskManager();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts task addition
    private void doNewTask() {
        Victim selected = selectVictim();
        System.out.println("Enter title of task:");
        String title = input.nextLine();
        System.out.println("Enter url related to task(guide, charity, volunteer page...");
        String url = input.nextLine();
        user.addTask(new Task(selected, title, url));
        System.out.println("Task added!");
    }

    // EFFECTS: creates a victim
    private Victim selectVictim() {
        //additional next line was needed. Don't know why
        input.nextLine();
        System.out.println("Enter victim's name:");
        String name = input.nextLine();
        System.out.println("Enter a devastatingly truthful message to encourage atonement:");
        String message = input.nextLine();
        return new Victim(name, message);
    }

    // MODIFIES: this
    // Effects: conducts the accomplishing of a task
    private void doAccomplishTask() {
        if (!user.getTasks().isEmpty()) {
            displayTasks();
            System.out.println("Enter number of the task you have accomplished");
            user.accomplishTask(input.nextInt());
            System.out.println("task accomplished!");
        } else {
            System.out.println("You have no tasks to accomplish");
        }
    }

    // Effects: displays menu of tasks for user(number and title)
    private void displayTasks() {
        for (int k = 0; k < user.getTasks().size(); k++) {
            System.out.println(user.getListOfTaskIT().get(k));
        }
    }

    // MODIFIES: this
    // Effects: conducts the failure of a task
    private void doFailTask() {
        if (!user.getTasks().isEmpty()) {
            displayTasks();
            System.out.println("Enter number of the task you have failed");
            user.failTask(input.nextInt());
            System.out.println("task failed!");
        } else {
            System.out.println("You have no tasks to fail");
        }
    }

    // EFFECTS: saves the TaskManager to file
    private void saveTaskManager() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("Saved task manager to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads TaskManager from file
    private void loadTaskManager() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded task manager from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}

