package info.gianlucacosta.easypmd.ide.options.profiles;

import com.thoughtworks.xstream.XStream;

/**
 * XStream for EasyPmd
 */
public class EasyPmdXStream extends XStream {

    public EasyPmdXStream() {
        alias(DefaultProfileContext.class.getSimpleName(), DefaultProfileContext.class);
        alias(DefaultProfileMap.class.getSimpleName(), DefaultProfileMap.class);
        alias(DefaultProfile.class.getSimpleName(), DefaultProfile.class);

        aliasPackage("regex", "info.gianlucacosta.helios.regex");
        aliasPackage("helios", "info.gianlucacosta.helios");
        aliasPackage("easypmd", "info.gianlucacosta.easypmd");
    }
}
