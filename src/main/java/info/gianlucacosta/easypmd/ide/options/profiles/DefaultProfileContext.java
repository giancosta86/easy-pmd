package info.gianlucacosta.easypmd.ide.options.profiles;

import info.gianlucacosta.easypmd.ide.options.Options;

import java.io.Serializable;

/**
 * Default implementation of ProfileContext.
 */
public class DefaultProfileContext implements ProfileContext, Serializable {

    private final ProfileMap profileMap;
    private final String activeProfileName;

    public DefaultProfileContext(ProfileMap profileMap, String activeProfileName) {
        this.profileMap = profileMap;
        this.activeProfileName = activeProfileName;
    }

    @Override
    public ProfileMap getProfiles() {
        return profileMap;
    }

    @Override
    public String getActiveProfileName() {
        return activeProfileName;
    }

    @Override
    public Options getActiveOptions() {
        return profileMap.getProfile(activeProfileName).getOptions();
    }
}
