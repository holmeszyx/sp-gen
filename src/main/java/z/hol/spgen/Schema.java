package z.hol.spgen;

import java.util.ArrayList;
import java.util.List;

/**
 * 一组SharedPreferences的集合.
 * 对应一个或多个 Rule
 * Created by holmes on 16-5-13.
 */
public class Schema {

    private final String mSpPackage;
    private final int mVersion;

    private List<Rule> mRules;
    private String mRuleClassSuffix;

    /**
     * 组
     * @param spPackage 组所用的包名
     * @param version 版本
     */
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
