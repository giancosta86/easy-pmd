package info.gianlucacosta.easypmd.ide.options.regexes;

/**
 * A template used to create a regular expression
 */
public abstract class RegexTemplate implements Comparable<RegexTemplate> {

    public abstract String getDescription();

    public abstract String getRegex();

    @Override
    public int compareTo(RegexTemplate other) {
        return getDescription().compareTo(other.getDescription());
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
