package info.gianlucacosta.easypmd.pmdscanner.pmdcatalogs;

import java.util.Collection;

/**
 * Catalog of standard PMD rulesets
 */
public interface StandardRuleSetsCatalog {

    boolean containsFileName(String ruleSetFileName);

    Collection<RuleSetWrapper> getRuleSetWrappers();
}
