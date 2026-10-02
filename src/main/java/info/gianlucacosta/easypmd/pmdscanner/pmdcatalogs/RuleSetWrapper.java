package info.gianlucacosta.easypmd.pmdscanner.pmdcatalogs;

import net.sourceforge.pmd.RuleSet;

/**
 * Wraps a ruleset, to provide a user-friendly string representation
 */
public class RuleSetWrapper {

    private final RuleSet ruleSet;

    public RuleSetWrapper(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", ruleSet.getName(), ruleSet.getFileName());
    }
}
