package info.gianlucacosta.easypmd.ide.options;

import info.gianlucacosta.easypmd.system.SystemPropertiesService;
import info.gianlucacosta.easypmd.ide.Injector;
import info.gianlucacosta.easypmd.pmdscanner.pmdcatalogs.LanguageVersionParser;
import info.gianlucacosta.easypmd.pmdscanner.pmdcatalogs.StandardRuleSetsCatalog;
import info.gianlucacosta.helios.regex.OsSpecificPathCompositeRegex;
import net.sourceforge.pmd.RulePriority;
import org.openide.util.lookup.ServiceProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Default implementation of OptionsFactory
 */
@ServiceProvider(service = OptionsFactory.class)
public class DefaultOptionsFactory implements OptionsFactory {

    private static final Logger logger = Logger.getLogger(DefaultOptionsFactory.class.getName());
    private static final String fallbackJavaLanguageVersion = "1.8";

    private final StandardRuleSetsCatalog standardRulesetsCatalog;
    private final SystemPropertiesService systemPropertiesService;
    private final LanguageVersionParser languageVersionParser;

    public DefaultOptionsFactory() {
        standardRulesetsCatalog = Injector.lookup(StandardRuleSetsCatalog.class);
        systemPropertiesService = Injector.lookup(SystemPropertiesService.class);
        languageVersionParser = Injector.lookup(LanguageVersionParser.class);
    }

    @Override
    public Options createDefaultOptions() {
        DefaultOptions result = new DefaultOptions();

        String retrievedJavaVersion = systemPropertiesService.getJavaVersion();
        String targetJavaVersion = retrievedJavaVersion != null ? retrievedJavaVersion : fallbackJavaLanguageVersion;

        try {
            languageVersionParser.parse(targetJavaVersion);
        } catch (IllegalArgumentException ex) {
            targetJavaVersion = fallbackJavaLanguageVersion;
        }

        result.setTargetJavaVersion(targetJavaVersion);
        result.setSourceFileEncoding("utf-8");
        result.setSuppressMarker("NOPMD");
        result.setMinimumPriority(RulePriority.MEDIUM);

        result.setAdditionalClassPathUrls(new ArrayList<>());

        final String[] suggestedDefaultRuleSetFileNames = new String[]{
            "rulesets/java/basic.xml",
            "rulesets/java/imports.xml",
            "rulesets/java/unusedcode.xml"
        };

        List<String> defaultRuleSets = new ArrayList<>();

        Arrays
                .stream(suggestedDefaultRuleSetFileNames)
                .forEach(ruleSetFileName -> {
                    if (standardRulesetsCatalog.containsFileName(ruleSetFileName)) {
                        defaultRuleSets.add(ruleSetFileName);
                    } else {
                        logger.warning(() -> String.format(
                                "The standard ruleset '%s' was not found",
                                ruleSetFileName
                        ));
                    }
                });

        result.setRuleSets(defaultRuleSets);

        result.setUseScanMessagesCache(true);

        result.setShowRulePriorityInTasks(true);
        result.setShowAnnotationsInEditor(true);
        result.setShowDescriptionInTasks(true);
        result.setShowRuleInTasks(false);
        result.setShowRuleSetInTasks(false);
        result.setShowAllMessagesInGuardedSections(false);

        result.setPathFilteringOptions(
                new PathFilteringOptions(
                        new OsSpecificPathCompositeRegex("^.*\\.java$"),
                        new OsSpecificPathCompositeRegex()));

        return result;
    }
}
