package z.hol.spgen;

import java.util.ArrayList;
import java.util.List;

/**
 * A set to the defined sharedPreferences which want to be generated.
 * Created by holmes on 16-5-13.
 */
public class Schema {

    private final String mSpPackage;
    private final int mVersion;

    private List<Rule> mRules;
    private String mRuleClassSuffix;

    /**
     * Like a container of rules
     *
     * @param spPackage the package name which all rules under the schema are belong to it
     * @param version   version code
     */
    public Schema(String spPackage, int version) {
        mSpPackage = spPackage;
        mVersion = version;
        mRules = new ArrayList<Rule>(4);
        mRuleClassSuffix = "Sp";
    }

    public Rule addRule(String clssName, String settingName) {
        if (isUseClassSuffix() && !clssName.endsWith(mRuleClassSuffix)) {
            clssName = clssName + mRuleClassSuffix;
        }
        Rule r = new Rule(clssName, settingName);
        mRules.add(r);
        return r;
    }

    public String getSpPackage() {
        return mSpPackage;
    }

    public int getVersion() {
        return mVersion;
    }

    public List<Rule> getRules() {
        return mRules;
    }

    /**
     * Rule class name suffix.
     * if don't need it, set suffix to null.
     * notice: If you set suffix to null, you must call it before addRule.
     *
     * @param ruleClassSuffix The suffix of each generated rule class
     */
    public void setRuleClassSuffix(String ruleClassSuffix) {
        mRuleClassSuffix = ruleClassSuffix;
    }

    public boolean isUseClassSuffix() {
        return mRuleClassSuffix != null && mRuleClassSuffix.length() > 0;
    }
}
