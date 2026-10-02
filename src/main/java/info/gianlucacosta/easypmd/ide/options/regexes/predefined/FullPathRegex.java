package info.gianlucacosta.easypmd.ide.options.regexes.predefined;

import info.gianlucacosta.easypmd.ide.options.regexes.RegexTemplate;
import info.gianlucacosta.easypmd.ide.options.regexes.SingleStringParamRegexTemplate;
import org.openide.util.lookup.ServiceProvider;

/**
 * Regex filtering by full path
 */
@ServiceProvider(service = RegexTemplate.class)
public class FullPathRegex extends SingleStringParamRegexTemplate {

    @Override
    public String getDescription() {
        return "Filter by full path";
    }

    @Override
    protected String getPromptMessage() {
        return "Please, digit the full path for the filter:";
    }

    @Override
    protected String getDefaultValue() {
        return "";
    }

    @Override
    protected String getRegex(String stringParam) {
        return String.format("^%s$", stringParam);
    }
}
