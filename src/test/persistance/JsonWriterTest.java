package persistance;

import model.Task;
import model.TaskManager;
import model.Victim;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Influenced greatly by Json demo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            TaskManager wr = new TaskManager();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTaskManager() {
        try {
            TaskManager wr = new TaskManager();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTaskManager.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTaskManager.json");
            wr = reader.read();
            assertEquals("Evil user", wr.getName());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTaskManager() {
        try {
            TaskManager wr = new TaskManager();
            wr.addTask(new Task(new Victim("name1", "no please. weird"), "weird", "www.help.com"));
            wr.addTask(new Task(new Victim("impoverished", "If you're ordering a tall coffe drink it's costing " +
                    "you,\n " + "on average, $3.34 daily, $1,221 annually and $6,105 over 5 years!"), "Give to the " +
                    "poor", "https://childrenfirstcanada.org/donate"));
            wr.addTask(new Task(new Victim("environment", "IT'S SO HOT"), "Shut doors, start a compost, " +
                    "and switch to more environmentally friendly tansportation", "https://www.wheelsforwishes.org" +
                    "/news/live-a-more-eco-friendly-lifestyle/"));
            wr.addTask(new Task(new Victim("hungry", "For the average Canadian household that amounts to 140 kilograms of\n " +
                    "wasted food per year – at a cost of more than $1,100 per year!"), "Feed the hungry", "http" +
                    "s://www.savethechildren.org/us/what-we-do/emergency-response/helping-starving-african-children"));
            wr.failTask(2);
            wr.accomplishTask(2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTaskManager.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTaskManager.json");
            wr = reader.read();
            assertEquals("Evil user", wr.getName());
            List<Task> tasks = wr.getTasks();
            List<Task> accomplishments = wr.getAaccomplishments();
            List<Task> failed = wr.getFailed();
            assertEquals(2, wr.getTasks().size());
            assertEquals(1, wr.getAaccomplishments().size());
            assertEquals(1, wr.getFailed().size());
            checkTask(new Victim("name1", "no please. weird"), "weird", "www.help.com", tasks.get(0));
            checkTask(new Victim("impoverished", "If you're ordering a tall coffe drink it's costing " +
                    "you,\n " + "on average, $3.34 daily, $1,221 annually and $6,105 over 5 years!"), "Give to the " +
                    "poor", "https://childrenfirstcanada.org/donate", failed.get(0));
            checkTask(new Victim("environment", "IT'S SO HOT"), "Shut doors, start a compost, " +
                    "and switch to more environmentally friendly tansportation", "https://www.wheelsforwishes.org" +
                    "/news/live-a-more-eco-friendly-lifestyle/", accomplishments.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
