package z.hol.spgen;

import java.util.ArrayList;
import java.util.List;

/**
 * 整体的一次生成
 * Created by holmes on 16-5-13.
 */
public class Schema {

    private final String mSpPackage;
    private final int mVersion;

    private List<Rule> mRules;
    private String mRuleClassSuffix;

    public Schema(String spPackage, int version) {
        mSpPackage = spPackage;
        mVersion = version;
        mRules = new ArrayList<Rule>(4);
        mRuleClassSuffix = "Sp";
    }

    public Rule addRule(String clssName, String settingName){
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

    public boolean isUseClassSuffix() {
        return mRuleClassSuffix != null && mRuleClassSuffix.length() > 0;
    }
}
