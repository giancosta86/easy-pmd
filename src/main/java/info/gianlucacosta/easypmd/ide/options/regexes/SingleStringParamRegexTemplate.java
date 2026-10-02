package info.gianlucacosta.easypmd.ide.options.regexes;

import info.gianlucacosta.easypmd.ide.DialogService;
import info.gianlucacosta.easypmd.ide.Injector;

/**
 * Regex template requiring just a single string parameter
 */
public abstract class SingleStringParamRegexTemplate extends RegexTemplate {

    private final DialogService dialogService;

    public SingleStringParamRegexTemplate() {
        dialogService = Injector.lookup(DialogService.class);
    }

    protected abstract String getPromptMessage();

    protected abstract String getDefaultValue();

    protected abstract String getRegex(String stringParam);

    @Override
    public String getRegex() {
        while (true) {
            String stringParam = dialogService.askForString(getPromptMessage(), getDefaultValue());

            if (stringParam == null) {
                return null;
            }

            stringParam = stringParam.trim();
            if (stringParam.isEmpty()) {
                continue;
            }

            try {
                return getRegex(stringParam);
            } catch (RuntimeException ex) {
                dialogService.showWarning(ex.getMessage());
            }
        }
    }
}
