package persistance;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import model.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TaskManager wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTaskManager() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTaskManager.json");
        try {
            TaskManager wr = reader.read();
            assertEquals("Evil user", wr.getName());
            assertEquals(0, wr.getTasks().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTaskManager() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralTaskManager.json");
        try {
            TaskManager wr = reader.read();
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
            fail("Couldn't read from file");
        }
    }
}
