package info.gianlucacosta.easypmd.ide;

import info.gianlucacosta.easypmd.PropertyPluginInfoService;
import info.gianlucacosta.easypmd.system.SystemPropertiesService;
import java.nio.file.Path;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = PathService.class)
public class DefaultPathService implements PathService {

    private final SystemPropertiesService systemPropertiesService;
    private final PropertyPluginInfoService pluginInfoService;
    private final Path rootPath;
    private final Path cachePath;

    public DefaultPathService() {
        systemPropertiesService = Injector.lookup(SystemPropertiesService.class);
        pluginInfoService = Injector.lookup(PropertyPluginInfoService.class);

        String majorVersion = pluginInfoService.getVersion().split("\\.")[0];
        String rootDirName = String.format(".EasyPmd_%s", majorVersion);

        rootPath = systemPropertiesService.getUserHomeDirectory().resolve(rootDirName);
        cachePath = rootPath.resolve("cache");
    }

    @Override
    public Path getRootPath() {
        return rootPath;
    }

    @Override
    public Path getCachePath() {
        return cachePath;
    }
}
