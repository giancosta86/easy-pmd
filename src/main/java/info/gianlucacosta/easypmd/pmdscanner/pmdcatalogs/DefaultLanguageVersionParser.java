package info.gianlucacosta.easypmd.pmdscanner.pmdcatalogs;

import net.sourceforge.pmd.lang.Language;
import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.LanguageVersion;
import net.sourceforge.pmd.lang.LanguageVersionHandler;
import org.openide.util.lookup.ServiceProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Default implementation of LanguageVersionParser. It currently only supports
 * Java.
 */
@ServiceProvider(service = LanguageVersionParser.class)
public class DefaultLanguageVersionParser implements LanguageVersionParser {

    private static final Pattern javaVersionPattern = Pattern.compile("^(\\d)\\.(\\d)$");

    @Override
    public LanguageVersion parse(String languageVersionString) {
        Matcher javaVersionMatcher = javaVersionPattern.matcher(languageVersionString);

        if (!javaVersionMatcher.matches()) {
            throw new IllegalArgumentException("Unsupported target Java version");
        }

        String majorVersion = javaVersionMatcher.group(1);
        String minorVersion = javaVersionMatcher.group(2);
        String languageHandlerClassName = String.format("net.sourceforge.pmd.lang.java.Java%s%sHandler", majorVersion, minorVersion);

        try {
            Class<? extends LanguageVersionHandler> languageHandlerClass = (Class<? extends LanguageVersionHandler>) Class.forName(languageHandlerClassName);

            LanguageVersionHandler languageHandler = languageHandlerClass.newInstance();

            Language javaLanguage = LanguageRegistry.getLanguage("Java");

            if (javaLanguage == null) {
                throw new IllegalStateException("Cannot find Java in PMD's language registry");
            }

            return new LanguageVersion(javaLanguage, languageVersionString, languageHandler);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IllegalArgumentException ex) {
            throw new IllegalArgumentException("Unsupported target Java version");
        }
    }
}
