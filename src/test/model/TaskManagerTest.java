package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskManagerTest {
    private TaskManager tm1;
    private Task t1;
    private Task t2;
    private Task t3;

    @BeforeEach
    public void runBefore(){
        tm1 = new TaskManager();
        t1 = new Task(new Victim("impoverished", "If you're ordering a tall coffe drink it's costing " +
                "you,\n " + "on average, $3.34 daily, $1,221 annually and $6,105 over 5 years!"), "Give to the " +
                "poor", "https://childrenfirstcanada.org/donate");
        t2 = new Task(new Victim("environment", "IT'S SO HOT"), "Shut doors, start a compost, " +
                "and switch to more environmentally friendly tansportation", "https://www.wheelsforwishes.org" +
                "/news/live-a-more-eco-friendly-lifestyle/");
        t3 = new Task(new Victim("hungry", "For the average Canadian household that amounts to 140 kilograms of\n " +
                "wasted food per year – at a cost of more than $1,100 per year!"), "Feed the hungry", "http" +
                "s://www.savethechildren.org/us/what-we-do/emergency-response/helping-starving-african-children");
    }
    @Test
    public void testAddTask(){
        assertTrue(tm1.getTasks().isEmpty());
        tm1.addTask(t1);
        assertEquals(tm1.getTasks().size(), 1);
        tm1.addTask(t2);
        assertEquals(tm1.getTasks().get(1).getTitle(), "Shut doors, start a compost, " +
                "and switch to more environmentally friendly tansportation");
        tm1.addTask(t3);
        //Do I need to look through every single path to check if correct?
        assertEquals(tm1.getTasks().get(2).getUrl(),"http" +
                "s://www.savethechildren.org/us/what-we-do/emergency-response/helping-starving-african-children");
    }

    @Test
    public void testFailTask(){
        //add tasks
        tm1.addTask(t1);
        tm1.addTask(t2);
        tm1.addTask(t3);

        tm1.failTask(1);
        // check if failed task is in failed list
        assertEquals("Give to the " + "poor", tm1.getFailed().get(0).getTitle());
        // check if first task is removed from task list
        assertEquals("Shut doors, start a compost, " +
                "and switch to more environmentally friendly tansportation", tm1.getTasks().get(0).getTitle());
        assertEquals(2, tm1.getTasks().size());
    }

    @Test
    public void testAccomplishTask(){
        //add tasks
        tm1.addTask(t1);
        tm1.addTask(t2);
        tm1.addTask(t3);

        tm1.accomplishTask(3);
        //check if accomplished task has been added to accomplished list
        assertEquals("https://www.savethechildren.org/us/what-we-do/emergency-response/helping-starving-" +
                "african-children", tm1.getAaccomplishments().get(0).getUrl());
        //add a second accomplishment
        tm1.accomplishTask(1);
        //check if accomplished task is second in accomplished list
        assertEquals("Give to the poor", tm1.getAaccomplishments().get(1).getTitle());
        //check that two items have removed from task list
        assertEquals(1, tm1.getTasks().size());
        //check that the correct tasks were removed
        assertEquals("Shut doors, start a compost, " +
                "and switch to more environmentally friendly tansportation", tm1.getTasks().get(0).getTitle());
    }

    @Test
    public void TestGetListOfTaskIT(){
        //add tasks
        tm1.addTask(t1);
        tm1.addTask(t2);
        tm1.addTask(t3);


        List<String> lt1 = tm1.getListOfTaskIT();
        //check that second element in list is formated correctly
        assertEquals("2. Shut doors, start a compost, " +
                "and switch to more environmentally friendly tansportation", lt1.get(1));
    }




}