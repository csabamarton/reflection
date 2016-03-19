import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.google.common.truth.Truth.assert_;

public class ReflectionProcessorTest {

    List<String> fruits;
    ReflectionManager reflectionManager;

    @Test
    public void scanAPackage() throws IOException, ClassNotFoundException {
        reflectionManager = new ReflectionManager();
        reflectionManager.showFruitClassNames();

        fruits = reflectionManager.showFruitClassNames();

        assert_().withFailureMessage("There is no fruits funded via package reflection").that(fruits).isNotEmpty();
    }
}