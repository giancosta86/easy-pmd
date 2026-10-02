package info.gianlucacosta.easypmd.ide.options.profiles;

import info.gianlucacosta.easypmd.ide.options.Options;

/**
 * The overall context of profiles and current active profile.
 */
public interface ProfileContext {

    ProfileMap getProfiles();

    String getActiveProfileName();

    Options getActiveOptions();
}
