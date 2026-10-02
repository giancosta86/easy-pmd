package info.gianlucacosta.easypmd.ide;

import java.nio.file.Path;

/**
 * Provides project-wide path information
 */
public interface PathService {

    /**
     * The root of the plugin directory within the user's home directory
     */
    Path getRootPath();

    /**
     * The directory containing cache information
     */
    Path getCachePath();
}
