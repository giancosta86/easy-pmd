package info.gianlucacosta.easypmd.ide.options.regexes.predefined;

import info.gianlucacosta.easypmd.ide.options.regexes.RegexTemplate;
import info.gianlucacosta.easypmd.ide.options.regexes.SingleStringParamRegexTemplate;
import org.openide.util.lookup.ServiceProvider;

/**
 * Regex filtering by parent directory
 */
@ServiceProvider(service = RegexTemplate.class)
public class ParentDirectoryRegex extends SingleStringParamRegexTemplate {

    @Override
    public String getDescription() {
        return "Filter by parent directory";
    }

    @Override
    protected String getDefaultValue() {
        return "Simple directory or composite path (e.g: parent1/parent2/parent3)";
    }

    @Override
    protected String getPromptMessage() {
        return "Please, digit the parent directory pattern:";
    }

    @Override
    protected String getRegex(String stringParam) {
        return String.format("^(.*/)?%s/[^/]+$", stringParam);
    }
}
