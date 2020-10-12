package model;

public class Victim {

    private String name;
    private String message;

    //EFFECTS: name of victim is set to given name and message of victim is set to given message
    public Victim(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }


}
