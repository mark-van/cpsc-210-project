package model;


// represents a person, thing, or group that was harmed
public class Victim {

    private String name;       // name of victim(group that was victimised)
    private String message;    // message concerning the pain caused to victim


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
