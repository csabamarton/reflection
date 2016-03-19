import com.google.common.collect.Lists;
import org.junit.Test;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class ReflectionManagerTest {

    ReflectionManager reflectionManager = null;

    @Test
    public void testWhichProviderFieldIsAFruit(){
        reflectionManager = new ReflectionManager();

        List<String> fruits = Lists.newArrayList();
        fruits = reflectionManager.showFruitClassNames();
        assertThat(fruits).isNotEmpty();
    }

}