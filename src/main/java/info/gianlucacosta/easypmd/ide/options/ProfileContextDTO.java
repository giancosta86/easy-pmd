package info.gianlucacosta.easypmd.ide.options;

import info.gianlucacosta.easypmd.ide.options.profiles.ProfileContext;

/**
 * Context DTO returned by EasyPmdPanel
 */
public class ProfileContextDTO {

    private ProfileContext profileContext;

    public ProfileContext getProfileContext() {
        return profileContext;
    }

    public void setProfileContext(ProfileContext profileContext) {
        this.profileContext = profileContext;
    }
}
