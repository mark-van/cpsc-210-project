package persistance;

import model.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


//follows same structure as
public class JsonTest {
    protected void checkTask(Victim victim, String title, String url, Task task) {
        assertEquals(task.getVictim().getName(), victim.getName());
        assertEquals(task.getVictim().getMessage(),victim.getMessage());
        assertEquals(task.getUrl(), url);
        assertEquals(task.getTitle(), title);
    }
}
