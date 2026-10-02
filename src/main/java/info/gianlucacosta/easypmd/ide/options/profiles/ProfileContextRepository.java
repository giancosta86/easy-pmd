package info.gianlucacosta.easypmd.ide.options.profiles;

/**
 * Loads and saves ProfileContext objects.
 */
public interface ProfileContextRepository {

    ProfileContext getProfileContext();

    void saveProfileContext(ProfileContext profileContext);
}
