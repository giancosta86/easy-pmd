package info.gianlucacosta.easypmd.ide.options.regexes.predefined;

import info.gianlucacosta.easypmd.ide.options.regexes.RegexTemplate;
import info.gianlucacosta.easypmd.ide.options.regexes.SingleStringParamRegexTemplate;
import org.openide.util.lookup.ServiceProvider;

/**
 * Regex filtering by file extension
 */
@ServiceProvider(service = RegexTemplate.class)
public class FileExtensionRegex extends SingleStringParamRegexTemplate {

    @Override
    public String getDescription() {
        return "Filter by file extension";
    }

    @Override
    protected String getPromptMessage() {
        return "Please, digit the file extension for the filter:";
    }

    @Override
    protected String getDefaultValue() {
        return ".java";
    }

    @Override
    protected String getRegex(String stringParam) {
        if (!stringParam.startsWith(".")) {
            stringParam = "." + stringParam;
        }

        return String.format("^.*\\%s$", stringParam);
    }
}
