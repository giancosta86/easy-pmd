package info.gianlucacosta.easypmd.ide.options.regexes.predefined;

import info.gianlucacosta.easypmd.ide.options.regexes.RegexTemplate;
import info.gianlucacosta.easypmd.ide.options.regexes.SingleStringParamRegexTemplate;
import org.openide.util.lookup.ServiceProvider;

/**
 * Regex filtering by file name (excluding the extension)
 */
@ServiceProvider(service = RegexTemplate.class)
public class FileNameWithoutExtensionRegex extends SingleStringParamRegexTemplate {

    @Override
    public String getDescription() {
        return "Filter by file name (ignoring any extension)";
    }

    @Override
    protected String getPromptMessage() {
        return "Please, digit the file name to filter (without extension):";
    }

    @Override
    protected String getDefaultValue() {
        return "";
    }

    @Override
    protected String getRegex(String stringParam) {
        return String.format("^(.*/)?%s(\\.[^/]*)*$", stringParam);
    }
}
