package info.gianlucacosta.easypmd.ide.options.regexes.predefined;

import info.gianlucacosta.easypmd.ide.options.regexes.RegexTemplate;
import info.gianlucacosta.easypmd.ide.options.regexes.SingleStringParamRegexTemplate;
import org.openide.util.lookup.ServiceProvider;

/**
 * Regex filtering by file name (including the extension)
 */
@ServiceProvider(service = RegexTemplate.class)
public class FileNameRegex extends SingleStringParamRegexTemplate {

    @Override
    public String getDescription() {
        return "Filter by file name (including the extension)";
    }

    @Override
    protected String getPromptMessage() {
        return "Please, digit the file name to filter (with extension):";
    }

    @Override
    protected String getDefaultValue() {
        return "";
    }

    @Override
    protected String getRegex(String stringParam) {
        return String.format("^(.*/)?%s$", stringParam);
    }
}
