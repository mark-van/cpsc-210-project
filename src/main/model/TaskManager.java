package model;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private List<Task> accomplishments;
    private List<Task> failed;

    // EFFECTS: constructs an empty list of tasks, accomplishments, and failed
    public TaskManager() {
        tasks = new ArrayList<>();
        accomplishments = new ArrayList<>();
        failed = new ArrayList<>();
    }

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
