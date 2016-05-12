package z.hol.spgen;

/**
 * Created by holmes on 16-5-13.
 */
public class Schema {

    private final String mSpPackage;
    private final int mVersion;

    public Schema(String spPackage, int version) {
        mSpPackage = spPackage;
        mVersion = version;
    }

    public Rule addRule(String clszName, String settingName){
        return new Rule();
    }
}
