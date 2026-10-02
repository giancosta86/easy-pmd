package info.gianlucacosta.easypmd.ide.options.profiles;

import info.gianlucacosta.easypmd.ide.options.Options;

/**
 * Stores a dedicated set of options.
 */
public interface Profile {

    Options getOptions();

    Profile clone();
}
