package info.gianlucacosta.easypmd.ide.options;

import java.util.Arrays;

import javax.swing.*;
import net.sourceforge.pmd.RulePriority;

/**
 * ComboBoxModel containing PMD's rule priorities.
 */
class RulePriorityComboBoxModel extends DefaultComboBoxModel<RulePriority> {

    public RulePriorityComboBoxModel() {
        Arrays
                .stream(RulePriority.values())
                .forEach(rulePriority -> {
                    addElement(rulePriority);
                });
    }

}
