package info.gianlucacosta.easypmd.system;

import org.openide.util.lookup.ServiceProvider;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Default implementation of SystemPropertiesService
 */
@ServiceProvider(service = SystemPropertiesService.class)
public class DefaultSystemPropertiesService implements SystemPropertiesService {

    public final String javaVersion;
    public final Path userHomeDir;

    public DefaultSystemPropertiesService() {
        javaVersion = findJavaVersion();

        userHomeDir = findUserHomeDir();
    }

    @Override
    public String getJavaVersion() {
        return javaVersion;
    }

    @Override
    public Path getUserHomeDirectory() {
        return userHomeDir;
    }

    private static String findJavaVersion() {
        Pattern versionPattern = Pattern.compile("(\\d\\.\\d).*");

        String javaVersionProperty = System.getProperty("java.version");
        Matcher versionMatcher = versionPattern.matcher(javaVersionProperty);

        if (versionMatcher.matches()) {
            return versionMatcher.group(1);
        } else {
            throw new RuntimeException("Cannot detected Java version");
        }
    }

    private static Path findUserHomeDir() {
        String userHomeString = System.getProperty("user.home");

        if (userHomeString == null) {
            throw new RuntimeException("Cannot detect the user's home directory");
        }

        return Paths.get(userHomeString);
    }
}
