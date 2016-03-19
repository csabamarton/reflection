import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ReflectionProcessor<T> {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionProcessor.class);

    public List<Class<T>> findClassesImplementing(final Class<T> interfaceClass,
                                                  final String packageName) {
        if (interfaceClass == null) {
            logger.debug("Unknown subclass.");

            return Collections.emptyList();
        }

        if (Strings.isNullOrEmpty(packageName)) {
            logger.debug("Null or empty packageName.");

            return Collections.emptyList();
        }

        List<Class<T>> rVal = Lists.newArrayList();

        Collection<Class<T>> allClassInPackage = getClassesOfPackage(packageName);

        if (allClassInPackage.size() == 0) {
            logger.debug("There was no class in the package.");

            return Collections.emptyList();
        }

        for (Class<T> aTarget : allClassInPackage) {
            findImplementations(interfaceClass, rVal, aTarget);
        }

        return rVal;
    }

    public static Collection getClassesOfPackage(String packageName) {

        final ClassLoader loader = Thread.currentThread()
                .getContextClassLoader();

        Collection<Class> classes = Lists.newArrayList();
        try {
            ClassPath classpath = ClassPath.from(loader);
            ImmutableSet<ClassPath.ClassInfo> topLevelClasses = classpath.getTopLevelClasses(packageName);

            classes = Collections2.transform(topLevelClasses, new Function<ClassPath.ClassInfo, Class>() {
                public Class apply(ClassPath.ClassInfo input) {
                    try {
                        return Class.forName(input.getName());
                    } catch (Exception ignored) {
                    }

                    return null;
                }
            });

            for (ClassPath.ClassInfo classInfo : topLevelClasses) {
                System.out.println(classInfo.getSimpleName() + " <==> " + classInfo.getPackageName());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    private void findImplementations(Class<T> interfaceClass, List<Class<T>> rVal, Class<T> aTarget) {
        if (aTarget != null && !aTarget.equals(interfaceClass)
                && interfaceClass.isAssignableFrom(aTarget)) {
            rVal.add(aTarget);
        } else if (aTarget == null) {
            return;
        } else if (aTarget.equals(interfaceClass)) {
            logger.debug("Found the interface definition.");
            return;
        } else if (!interfaceClass.isAssignableFrom(aTarget)) {
            logger.debug(
                    "Class '" + aTarget.getName() + "' is not a " + interfaceClass.getName());
            return;
        }
    }


}
