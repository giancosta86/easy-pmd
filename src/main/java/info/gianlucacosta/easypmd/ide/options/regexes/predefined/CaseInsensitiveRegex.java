package info.gianlucacosta.easypmd.ide.options.regexes.predefined;

import info.gianlucacosta.easypmd.ide.options.regexes.RegexTemplate;
import info.gianlucacosta.easypmd.ide.options.regexes.SingleStringParamRegexTemplate;
import org.openide.util.lookup.ServiceProvider;

/**
 * Case-insensitive regex
 */
@ServiceProvider(service = RegexTemplate.class)
public class CaseInsensitiveRegex extends SingleStringParamRegexTemplate {

    @Override
    public String getDescription() {
        return "Filter by case insensitive regular expression";
    }

    @Override
    protected String getPromptMessage() {
        return "Please, digit a regular expression to process as case insensitive:";
    }

    @Override
    protected String getDefaultValue() {
        return "";
    }

    @Override
    protected String getRegex(String stringParam) {
        return String.format("(?i)%s", stringParam);
    }
}
