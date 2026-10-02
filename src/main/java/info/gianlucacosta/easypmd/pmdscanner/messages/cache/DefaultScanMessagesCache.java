package info.gianlucacosta.easypmd.pmdscanner.messages.cache;

import info.gianlucacosta.easypmd.ide.PathService;
import info.gianlucacosta.easypmd.ide.Injector;
import org.openide.util.lookup.ServiceProvider;

/**
 * Default cache service
 */
@ServiceProvider(service = ScanMessagesCache.class)
public class DefaultScanMessagesCache extends DualLayerCache {

    public DefaultScanMessagesCache() {
        super(new HsqlDbStorage(
                Injector.lookup(PathService.class).getCachePath()
        ));
    }
}
