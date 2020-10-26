package persistence;

import org.json.JSONObject;

//based on writable interface in Jason demo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

