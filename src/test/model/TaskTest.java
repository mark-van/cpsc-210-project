package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TaskTest {
    Task t;

    @Test
    public void testConstructor() {
        //message from google
        t = new Task(new Victim("impoverished", "If you're ordering a tall coffe drink it's costing " +
                "you,\n " + "on average, $3.34 daily, $1,221 annually and $6,105 over 5 years!"), "Give to the " +
                "poor", "https://childrenfirstcanada.org/donate");
        assertEquals("impoverished", t.getVictim().getName());
        assertEquals("If you're ordering a tall coffe drink it's costing " +
                        "you,\n " + "on average, $3.34 daily, $1,221 annually and $6,105 over 5 years!",
                t.getVictim().getMessage());
        assertEquals("Give to the poor", t.getTitle());
        assertEquals("https://childrenfirstcanada.org/donate", t.getUrl());
        }
    @Test
    public void expectCatch() {
        t = new Task(new Victim("environment", "IT'S SO HOT"), "Shut doors, start a compost, " +
                "and switch to more environmentally friendly tansportation", "www.notawebsite.noway");

        assertEquals("https://reflectionsfromaredhead.com/make-the-world-a-better-place/", t.getUrl());
    }
}
