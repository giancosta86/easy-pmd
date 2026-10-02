package info.gianlucacosta.easypmd;

import info.gianlucacosta.helios.product.ProductInfoService;
import info.gianlucacosta.helios.product.PropertyProductInfoService;
import info.gianlucacosta.helios.properties.XmlProperties;
import org.openide.util.lookup.ServiceProvider;

import java.io.IOException;

/**
 * Reads the plugin information from a property file
 */
@ServiceProvider(service = ProductInfoService.class)
public class PropertyPluginInfoService extends PropertyProductInfoService {

    public PropertyPluginInfoService() throws IOException {
        super(new XmlProperties("/info/gianlucacosta/easypmd/Plugin.properties.xml"));
    }

}
