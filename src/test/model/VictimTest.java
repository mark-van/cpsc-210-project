package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VictimTest {
    Victim v1;
    Victim v2;
    @Test
    public void testConstructor(){
        v1 = new Victim("environment", "IT'S SO HOT");
        assertEquals("environment", v1.getName());
        assertEquals("IT'S SO HOT", v1.getMessage());

        v2 = new Victim("hungry", "For the average Canadian household that amounts to 140 kilograms of\n " +
                "wasted food per year – at a cost of more than $1,100 per year!");
        assertEquals("hungry", v2.getName());
        assertEquals("For the average Canadian household that amounts to 140 kilograms of\n " +
                "wasted food per year – at a cost of more than $1,100 per year!", v2.getMessage());

    }




}
