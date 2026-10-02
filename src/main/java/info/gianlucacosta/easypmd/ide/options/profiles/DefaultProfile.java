package info.gianlucacosta.easypmd.ide.options.profiles;

import info.gianlucacosta.easypmd.ide.options.Options;

import java.io.Serializable;

/**
 * Default implementation of Profile.
 */
public class DefaultProfile implements Profile, Serializable {

    private final Options options;

    public DefaultProfile(Options options) {
        this.options = options;
    }

    @Override
    public Options getOptions() {
        return options;
    }

    @Override
    public Profile clone() {
        Options clonedOptions = options.clone();

        return new DefaultProfile(clonedOptions);
    }

}
