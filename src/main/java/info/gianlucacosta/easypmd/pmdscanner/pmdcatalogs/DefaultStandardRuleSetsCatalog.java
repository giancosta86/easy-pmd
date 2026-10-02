package info.gianlucacosta.easypmd.pmdscanner.pmdcatalogs;

import net.sourceforge.pmd.RuleSet;
import net.sourceforge.pmd.RuleSetFactory;
import net.sourceforge.pmd.RuleSetNotFoundException;
import org.openide.util.lookup.ServiceProvider;

import java.util.*;

/**
 * Default implementation of StandardRuleSetsCatalog
 */
@ServiceProvider(service = StandardRuleSetsCatalog.class)
public class DefaultStandardRuleSetsCatalog implements StandardRuleSetsCatalog {

    private final List<RuleSetWrapper> wrappers = new ArrayList<>();

    public DefaultStandardRuleSetsCatalog() {
        RuleSetFactory ruleSetFactory = new RuleSetFactory();

        try {
            Iterator<RuleSet> ruleSetsIterator = ruleSetFactory.getRegisteredRuleSets();

            while (ruleSetsIterator.hasNext()) {
                RuleSet ruleSet = ruleSetsIterator.next();
                wrappers.add(new RuleSetWrapper(ruleSet));
            }
        } catch (RuleSetNotFoundException ex) {
            throw new RuntimeException("Error while initializing the list of PMD's standard rule sets", ex);
        }
    }

    @Override
    public Collection<RuleSetWrapper> getRuleSetWrappers() {
        return Collections.unmodifiableCollection(wrappers);
    }

    @Override
    public boolean containsFileName(String ruleSetFileName) {
        return wrappers
                .stream()
                .parallel()
                .anyMatch(wrapper
                        -> wrapper.getRuleSet().getFileName().equals(ruleSetFileName)
                );
    }
}
