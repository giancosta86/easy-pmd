package info.gianlucacosta.easypmd.ide;

import info.gianlucacosta.helios.application.io.CommonQuestionOutcome;
import info.gianlucacosta.helios.product.ProductInfoService;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.lookup.ServiceProvider;

import javax.swing.*;

/**
 * Default implementation of DialogService
 */
@ServiceProvider(service = DialogService.class)
public class DefaultDialogService implements DialogService {

    private final ProductInfoService pluginInfoService;

    public DefaultDialogService() {
        pluginInfoService = Injector.lookup(ProductInfoService.class);
    }

    private void showMessageBox(String message, int kind) {
        NotifyDescriptor messageDescriptor = new NotifyDescriptor.Message(message, kind);
        messageDescriptor.setTitle(pluginInfoService.getName());
        DialogDisplayer.getDefault().notifyLater(messageDescriptor);
    }

    @Override
    public void showInfo(String message) {
        showMessageBox(message, NotifyDescriptor.INFORMATION_MESSAGE);
    }

    @Override
    public void showWarning(String message) {
        showMessageBox(message, NotifyDescriptor.WARNING_MESSAGE);
    }

    @Override
    public void showError(String message) {
        showMessageBox(message, NotifyDescriptor.ERROR_MESSAGE);
    }

    @Override
    public String askForString(String message) {
        return JOptionPane.showInputDialog(null, message, pluginInfoService.getName(), JOptionPane.INFORMATION_MESSAGE | JOptionPane.OK_CANCEL_OPTION);
    }

    @Override
    public String askForString(String message, String defaultValue) {
        return JOptionPane.showInputDialog(null, message, defaultValue);
    }

    @Override
    public CommonQuestionOutcome askYesNoQuestion(String message) {
        int dialogResult = JOptionPane.showConfirmDialog(null, message, pluginInfoService.getName(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        switch (dialogResult) {
            case JOptionPane.YES_OPTION:
                return CommonQuestionOutcome.YES;

            case JOptionPane.NO_OPTION:
                return CommonQuestionOutcome.NO;

            default:
                throw new IllegalStateException();
        }
    }
}
