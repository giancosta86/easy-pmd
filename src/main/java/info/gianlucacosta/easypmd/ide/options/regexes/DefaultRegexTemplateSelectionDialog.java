package info.gianlucacosta.easypmd.ide.options.regexes;

import info.gianlucacosta.easypmd.ide.Injector;
import info.gianlucacosta.helios.product.ProductInfoService;
import org.openide.util.lookup.ServiceProvider;

import javax.swing.*;
import java.util.Collection;

/**
 * Default implementation of RegexTemplateSelectionDialog
 */
@ServiceProvider(service = RegexTemplateSelectionDialog.class)
public class DefaultRegexTemplateSelectionDialog implements RegexTemplateSelectionDialog {

    private static final RegexTemplate[] regexTemplates;

    static {
        Collection<? extends RegexTemplate> foundRegexTemplates = Injector.lookupAll(RegexTemplate.class);

        regexTemplates = foundRegexTemplates
                .stream()
                .sorted()
                .toArray(RegexTemplate[]::new);
    }

    private final ProductInfoService pluginInfoService;

    public DefaultRegexTemplateSelectionDialog() {
        pluginInfoService = Injector.lookup(ProductInfoService.class);
    }

    @Override
    public RegexTemplate askForRegexTemplate() {
        RegexTemplate chosenTemplate = (RegexTemplate) JOptionPane.showInputDialog(
                null,
                "Please, choose a predefined regular expression:",
                pluginInfoService.getName(),
                JOptionPane.OK_CANCEL_OPTION | JOptionPane.PLAIN_MESSAGE,
                null,
                regexTemplates,
                null);
        return chosenTemplate;
    }
}
