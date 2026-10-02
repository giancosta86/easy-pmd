package info.gianlucacosta.easypmd.ide.options;

import info.gianlucacosta.helios.regex.CompositeRegex;

import java.io.Serializable;

/**
 * Options related to path filtering
 */
public class PathFilteringOptions implements Serializable {

    private final CompositeRegex includedPathCompositeRegex;
    private final CompositeRegex excludedPathCompositeRegex;

    public PathFilteringOptions(CompositeRegex includedPathCompositeRegex, CompositeRegex excludedPathCompositeRegex) {
        this.includedPathCompositeRegex = includedPathCompositeRegex;
        this.excludedPathCompositeRegex = excludedPathCompositeRegex;
    }

    public CompositeRegex getExcludedPathCompositeRegex() {
        return excludedPathCompositeRegex;
    }

    public CompositeRegex getIncludedPathCompositeRegex() {
        return includedPathCompositeRegex;
    }

    private boolean isScanOnlySomePaths() {
        return !includedPathCompositeRegex.getSubRegexes().isEmpty();
    }

    private boolean isExcludeSomePaths() {
        return !excludedPathCompositeRegex.getSubRegexes().isEmpty();
    }

    public boolean isPathValid(String path) {
        if (isScanOnlySomePaths()) {
            if (!includedPathCompositeRegex.matches(path)) {
                return false;
            }
        }

        if (isExcludeSomePaths()) {
            if (excludedPathCompositeRegex.matches(path)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PathFilteringOptions)) {
            return false;
        }

        PathFilteringOptions other = (PathFilteringOptions) obj;

        return includedPathCompositeRegex.equals(other.includedPathCompositeRegex)
                && excludedPathCompositeRegex.equals(other.excludedPathCompositeRegex);
    }

    @Override
    public int hashCode() {
        return includedPathCompositeRegex.hashCode() + excludedPathCompositeRegex.hashCode();
    }
}
