package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// classes JSON updates are heavily influenced by the JasonSerializationDemo
// Represents a task manager having a tasks, accomplishments, and failed lists
public class TaskManager implements Writable {
    private String name;                   // Users given name
    private List<Task> tasks;              // list of current tasks
    private List<Task> accomplishments;    // list of completed tasks
    private List<Task> failed;             // list of failed tasks

    //give untittled name initially???
    // EFFECTS: constructs an empty list of tasks, accomplishments, and failed
    public TaskManager() {
        tasks = new ArrayList<>();
        accomplishments = new ArrayList<>();
        failed = new ArrayList<>();
        name = "Evil user";
    }

    //MODIFIES: this
    //EFFECTS: adds task to tasks list
    public void addTask(Task task) {
        tasks.add(task);
    }

    //MODIFIES: this
    //EFFECTS: moves task in index number-1 from list to failed. If no task is at number-1 index then do nothing.
    public void failTask(int number) {
        failed.add(tasks.remove(number - 1));
    }

    //MODIFIES: this
    //EFFECTS: moves task in index number-1 from list to failed. If no task is at number-1 index then do nothing.
    public void accomplishTask(int number) {
        accomplishments.add(tasks.remove(number - 1));
    }

    //EFFECTS: returns list of strings containing the index number + 1 and the title for each task on the tasks list
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

    public String getName() {
        return name;
    }

    //Based on JSON demo
    //Declare toJason class required for use of interface
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("TaskManager:", "user");
        json.put("tasks", tasksToJson(tasks));
        json.put("accomplishments", tasksToJson(accomplishments));
        json.put("failed", tasksToJson(failed));
        return json;
    }

    //Based on JASON  demo
    // EFFECTS: returns tasks in this TaskManager as a JSON array
    private JSONArray tasksToJson(List<Task> tjason) {
        JSONArray jsonArray = new JSONArray();

        for (Task t : tjason) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: adds thingy to this workroom
    public void addTasks(Task task, String type) {
        if (type.equals("tasks")) {
            tasks.add(task);
        } else if (type.equals("accomplishments")) {
            accomplishments.add(task);
        } else {
            failed.add(task);
        }
    }
}

