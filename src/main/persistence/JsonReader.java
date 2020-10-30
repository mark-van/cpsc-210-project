package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//heavily influenced by json demo
// Represents a reader that reads TaskManager from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads TaskManger from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TaskManager read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTaskManager(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses taskManger from JSON object and returns it
    private TaskManager parseTaskManager(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        TaskManager tm = new TaskManager();
        addTasks(tm, jsonObject, "tasks");
        addTasks(tm, jsonObject, "accomplishments");
        addTasks(tm, jsonObject, "failed");
        return tm;
    }

    // MODIFIES: tm
    // EFFECTS: parses tasks from JSON object and adds them to TaskManager
    private void addTasks(TaskManager tm, JSONObject jsonObject, String type) {
        JSONArray jsonArray = jsonObject.getJSONArray(type);
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(tm, nextTask, type);
        }
    }

    // MODIFIES: tm
    // EFFECTS: parses tasks from JSON object and adds it to TaskManager
    private void addTask(TaskManager tm, JSONObject jsonObject, String type) {
        String title = jsonObject.getString("title");
        String url = jsonObject.getString("url");
        Victim victim = getVictim(jsonObject);
        Task task = new Task(victim, title, url);
        tm.addTasks(task, type);
    }

    // EFFECTS: parses from JSON object and makes victim
    public Victim getVictim(JSONObject jsonObject) {
        String name = jsonObject.getString("victim name");
        String message = jsonObject.getString("victim message");
        return new Victim(name, message);
    }
}
