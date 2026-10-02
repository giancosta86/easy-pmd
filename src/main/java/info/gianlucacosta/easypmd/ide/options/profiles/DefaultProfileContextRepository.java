package info.gianlucacosta.easypmd.ide.options.profiles;

import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import info.gianlucacosta.easypmd.ide.Injector;
import org.openide.util.lookup.ServiceProvider;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;
import java.nio.file.Path;
import info.gianlucacosta.easypmd.ide.PathService;
import info.gianlucacosta.easypmd.util.Throwables;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.nio.file.Files;

/**
 * Default implementation of ProfileContextRepository.
 */
@ServiceProvider(service = ProfileContextRepository.class)
public class DefaultProfileContextRepository implements ProfileContextRepository {

    private static final Logger logger = Logger.getLogger(DefaultProfileContextFactory.class.getName());

    private static final String PROFILES_FILE_NAME = "Profiles.xml";

    private final EasyPmdXStream xmlStream = new EasyPmdXStream();

    private final Path profilesPath;

    private ProfileContext profileContext;

    public DefaultProfileContextRepository() {
        PathService pathService = Injector.lookup(PathService.class);
        profilesPath = pathService.getRootPath().resolve(PROFILES_FILE_NAME);

        try (ObjectInputStream profileContextInputStream = xmlStream.createObjectInputStream(
                new BufferedInputStream(
                        Files.newInputStream(profilesPath)
                )
        )) {
            profileContext = (ProfileContext) profileContextInputStream.readObject();
        } catch (Exception ex) {
            logger.warning(
                    () -> String.format(
                            "Exception while loading the profiles: %s",
                            Throwables.toStringWithStackTrace(ex)
                    )
            );

            ProfileContextFactory profileContextFactory = Injector.lookup(ProfileContextFactory.class);
            profileContext = profileContextFactory.createDefaultProfileContext();
        }
    }

    @Override
    public synchronized ProfileContext getProfileContext() {
        return profileContext;
    }

    @Override
    public synchronized void saveProfileContext(ProfileContext profileContext) {
        this.profileContext = profileContext;

        try (ObjectOutputStream profileContextOutputStream = xmlStream.createObjectOutputStream(
                new PrettyPrintWriter(
                        new OutputStreamWriter(
                                new BufferedOutputStream(
                                        Files.newOutputStream(profilesPath)
                                )
                        )
                )
        )) {
            profileContextOutputStream.writeObject(profileContext);
        } catch (Exception ex) {
            logger.severe(
                    () -> String.format(
                            "Error while saving the options: %s",
                            Throwables.toStringWithStackTrace(ex)
                    )
            );
        }
    }
}
