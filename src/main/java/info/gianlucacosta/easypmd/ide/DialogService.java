package info.gianlucacosta.easypmd.ide;

import info.gianlucacosta.helios.application.io.CommonQuestionOutcome;

/**
 * Can show different types of dialogs
 */
public interface DialogService {

    void showInfo(String message);

    void showWarning(String message);

    void showError(String message);

    String askForString(String message);

    String askForString(String message, String defaultValue);

    CommonQuestionOutcome askYesNoQuestion(String message);
}
