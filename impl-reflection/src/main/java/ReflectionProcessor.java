import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReflectionProcessor<T> {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionProcessor.class);

    public List<Class<T>> findClassesImplementing(final Class<T> interfaceClass,
                                                  final Field fields[]) {
        if (interfaceClass == null) {
            logger.debug("Unknown subclass.");
            return Collections.emptyList();
        }

        if (fields == null) {
            logger.debug("No fields.");
            return Collections.emptyList();
        }

        final List<Class<T>> rVal = new ArrayList<Class<T>>();

        for (Field fld : fields) {
            Class<T> aTarget = (Class<T>) fld.getType();

            if (aTarget != null && !aTarget.equals(interfaceClass)
                    && interfaceClass.isAssignableFrom(aTarget)) {
                rVal.add(aTarget);
            } else if (aTarget == null) {
                continue;
            } else if (aTarget.equals(interfaceClass)) {
                logger.debug("Found the interface definition.");
                continue;
            } else if (!interfaceClass.isAssignableFrom(aTarget)) {
                logger.debug(
                        "Class '" + aTarget.getName() + "' is not a " + interfaceClass.getName());
                continue;
            }
        }

        return rVal;
    }
}
