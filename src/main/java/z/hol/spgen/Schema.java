package z.hol.spgen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by holmes on 16-5-13.
 */
public class Schema {

    private final String mSpPackage;
    private final int mVersion;

    private List<Rule> mRules;

    public Schema(String spPackage, int version) {
        mSpPackage = spPackage;
        mVersion = version;
        mRules = new ArrayList<Rule>(4);
    }

    public Rule addRule(String clssName, String settingName){
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
}
