package info.gianlucacosta.easypmd.system;

import java.nio.file.Path;

/**
 * Provides system properties
 */
public interface SystemPropertiesService {

    String getJavaVersion();

    Path getUserHomeDirectory();
}
