import com.google.common.collect.Lists;
import fruits.Fruit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReflectionManager {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionManager.class);

    public static void main(String[] args) {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.showFruitClassNames();
    }

    public List showFruitClassNames() {
        List<String> fruits = Lists.newArrayList();
        ReflectionProcessor reflectionProcessor = new ReflectionProcessor();

        final List<Class<Fruit>> fruitSubTypes = reflectionProcessor
                .findClassesImplementing(Fruit.class,
                        "fruits");
        fillImplementationClassNameList(fruits, fruitSubTypes);

        return fruits;
    }

    private void fillImplementationClassNameList(List<String> fruits, List<Class<Fruit>> fruitSubTypes) {
        if (fruitSubTypes != null) {
            for (Class fruitClass : fruitSubTypes) {
                String fruitName = fruitClass.getSimpleName();
                logger.debug(fruitName);
                fruits.add(fruitName);
            }
        }
    }
}
