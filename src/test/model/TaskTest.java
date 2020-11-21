package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TaskTest {
    Task t1;
    Task t2;

    @Test
    public void testConstructor() {
        //message from google
        t1 = new Task(new Victim("impoverished", "If you're ordering a tall coffe drink it's costing " +
                "you,\n " + "on average, $3.34 daily, $1,221 annually and $6,105 over 5 years!"), "Give to the " +
                "poor", "https://childrenfirstcanada.org/donate");
        assertEquals("impoverished", t1.getVictim().getName());
        assertEquals("If you're ordering a tall coffe drink it's costing " +
                        "you,\n " + "on average, $3.34 daily, $1,221 annually and $6,105 over 5 years!",
                t1.getVictim().getMessage());
        assertEquals("Give to the poor", t1.getTitle());
        assertEquals("https://childrenfirstcanada.org/donate", t1.getUrl());

        try{
            t1.checkURL();
        }catch (InvalidUrlException e){
            fail("you should not be here");
        }
        t2 = new Task(new Victim("environment", "IT'S SO HOT"), "Shut doors, start a compost, " +
                "and switch to more environmentally friendly tansportation", "www.notawebsite.noway");
        try{
            t2.checkURL();
            fail("you should not be here");
        }catch (InvalidUrlException e){
            //good jon if you got here
        }
    }
}
