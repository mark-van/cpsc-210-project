package ui;

import model.TaskManager;
import model.Victim;
import model.Task;

import java.util.Scanner;

//This class is based on the code in The TellApp class from the TellApp project
//Atonement application
public class AtonementApp {
    private TaskManager user;
    private Scanner input;

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
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tt -> new task");
        System.out.println("\tc -> complete task");
        System.out.println("\tf -> fail task");
        System.out.println("\tv -> view todoList");
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
            user.accomplishTask(input.nextInt());
            System.out.println("task failed!");
        } else {
            System.out.println("You have no tasks to fail");
        }
    }
    /*
    private void doViewTodoList() {
        displayTasks();
    }
    */


}

