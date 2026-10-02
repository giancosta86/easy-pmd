package info.gianlucacosta.easypmd.ide.options.profiles;

import info.gianlucacosta.easypmd.ide.Injector;
import info.gianlucacosta.easypmd.ide.options.Options;
import info.gianlucacosta.easypmd.ide.options.OptionsFactory;
import org.openide.util.lookup.ServiceProvider;

/**
 * Default implementation of ProfileContextFactory.
 */
@ServiceProvider(service = ProfileContextFactory.class)
public class DefaultProfileContextFactory implements ProfileContextFactory {

    private static final String defaultProfileName = "Default profile";
    private final OptionsFactory optionsFactory;

    public DefaultProfileContextFactory() {
        this.optionsFactory = Injector.lookup(OptionsFactory.class);
    }

    @Override
    public ProfileContext createDefaultProfileContext() {
        Options defaultOptions = optionsFactory.createDefaultOptions();
        Profile defaultProfile = new DefaultProfile(defaultOptions);

        ProfileMap defaultProfiles = new DefaultProfileMap();

        try {
            defaultProfiles.setProfile(defaultProfileName, defaultProfile);
        } catch (ProfileException ex) {
            throw new RuntimeException(ex);
        }

        return new DefaultProfileContext(defaultProfiles, defaultProfileName);
    }

}
