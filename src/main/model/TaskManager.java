package model;

import java.util.ArrayList;
import java.util.List;

// Represents a task manager having a tasks, accomplishments, and failed lists
public class TaskManager {
    private List<Task> tasks;              // list of current tasks
    private List<Task> accomplishments;    // list of completed tasks
    private List<Task> failed;             // list of failed tasks

    // EFFECTS: constructs an empty list of tasks, accomplishments, and failed
    public TaskManager() {
        tasks = new ArrayList<>();
        accomplishments = new ArrayList<>();
        failed = new ArrayList<>();
    }

    //MODIFIES: this
    //Effects: adds task to tasks list
    public void addTask(Task task) {
        tasks.add(task);
    }

    //MODIFIES: this
    //Effects: moves task in index number-1 from list to failed. If no task is at number-1 index then do nothing.
    public void failTask(int number) {
        failed.add(tasks.remove(number - 1));
    }

    //MODIFIES: this
    //Effects: moves task in index number-1 from list to failed. If no task is at number-1 index then do nothing.
    public void accomplishTask(int number) {
        accomplishments.add(tasks.remove(number - 1));
    }

    //Effects: returns list of strings containing the index number + 1 and the title for each task on the tasks list
    public List<String> getListOfTaskIT() {
        List<String> list = new ArrayList<>();
        String it;
        int num;
        for (int k = 0; k < tasks.size(); k++) {
            it = Integer.toString(k + 1) + ". " + tasks.get(k).getTitle();
            list.add(it);
        }
        return list;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> getAaccomplishments() {
        return accomplishments;
    }

    public List<Task> getFailed() {
        return failed;
    }
}
