package info.gianlucacosta.easypmd.ide.options;

import info.gianlucacosta.easypmd.pmdscanner.pmdcatalogs.RuleSetWrapper;
import info.gianlucacosta.easypmd.pmdscanner.pmdcatalogs.StandardRuleSetsCatalog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Collection;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.sourceforge.pmd.RuleSet;

/**
 * Panel showing a list of available standard rule sets and allowing multiple
 * selection
 */
public final class StandardRuleSetsInputPanel extends JPanel {

    private final JList<RuleSetWrapper> inputList;

    public StandardRuleSetsInputPanel(StandardRuleSetsCatalog standardRuleSetsCatalog) {
        Collection<RuleSetWrapper> standardRuleSetWrappers = standardRuleSetsCatalog.getRuleSetWrappers();

        setLayout(new BorderLayout());

        JLabel inputLabel = new JLabel("Choose a standard rule set:");
        inputLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 7, 0));
        add(inputLabel, BorderLayout.NORTH);

        DefaultListModel<RuleSetWrapper> inputModel = new DefaultListModel<>();
        standardRuleSetWrappers.forEach(ruleSetWrapper -> inputModel.addElement(ruleSetWrapper));

        inputList = new JList<>(inputModel);

        JScrollPane inputScroll = new JScrollPane(inputList);
        add(inputScroll, BorderLayout.CENTER);

        setPreferredSize(new Dimension(450, 200));
    }

    public Stream<RuleSet> getSelectedRuleSets() {
        return inputList.getSelectedValuesList()
                .stream()
                .map(ruleSetWrapper -> ruleSetWrapper.getRuleSet());
    }
}
