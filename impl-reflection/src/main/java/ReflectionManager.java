import com.google.common.collect.Lists;
import fruits.AbstractFruit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReflectionManager {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionManager.class);

    public static void main(String[] args){
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.showFruitClassNames();
    }

    public List showFruitClassNames()
    {
        ReflectionProcessor reflectionProcessor = new ReflectionProcessor();

        List<String> fruits = Lists.newArrayList();

        final List<Class<AbstractFruit>> fruitSubTypes = reflectionProcessor
                .findClassesImplementing(AbstractFruit.class,
                        FruitProvider.class.getDeclaredFields());
        if (fruitSubTypes != null) {
            for (Class fruitClass : fruitSubTypes) {
                String fruitName = fruitClass.getSimpleName();
                logger.debug(fruitName);
                fruits.add(fruitName);
            }
        }

        return fruits;
    }
}
