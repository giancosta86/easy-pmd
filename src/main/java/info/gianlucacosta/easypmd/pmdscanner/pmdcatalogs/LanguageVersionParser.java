package info.gianlucacosta.easypmd.pmdscanner.pmdcatalogs;

import net.sourceforge.pmd.lang.LanguageVersion;

/**
 * Creates a LanguageVersion by parsing a version string
 */
public interface LanguageVersionParser {

    LanguageVersion parse(String versionString);
}
