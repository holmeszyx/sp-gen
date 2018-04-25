package z.hol.spgen;

import java.util.ArrayList;
import java.util.List;

/**
 * SharedPreferences Rule, each one map to a config file.
 * Created by holmes on 16-5-13.
 */
public class Rule {

    public String clssName;
    public String settingName;

    private List<Entity> mEntities;
    private String mComment;

    private boolean mCanClear = false;

    private TextIntercept mParamKeyIntercept = ParamNameIntercept.INSTANCE;
    private TextIntercept mConstKeyIntercept = ConstNameIntercept.INSTANCE;

    /**
     * SharedPreferences
     *
     * @param clssName    the class name for the controller of SharedPreferences (exclude package name)
     * @param settingName the file name to save SharedPreferences
     */
    public Rule(String clssName, String settingName) {
        this.clssName = clssName;
        this.settingName = settingName;
        mEntities = new ArrayList<Entity>(16);
    }

    public Entity addEntity(String name) {
        Entity entity = new Entity(name, mParamKeyIntercept);
        entity.setConstKeyIntercept(mConstKeyIntercept);
        mEntities.add(entity);
        return entity;
    }

    /**
     * the default parameter intercept for all entity in this rule
     */
    public void setParamKeyIntercept(TextIntercept paramKeyIntercept) {
        mParamKeyIntercept = paramKeyIntercept;
    }

    /**
     * the default const intercept for all entity in this rule
     */
    public void setConstKeyIntercept(TextIntercept constKeyIntercept) {
        mConstKeyIntercept = constKeyIntercept;
    }

    public String getClssName() {
        return clssName;
    }

    public String getSettingName() {
        return settingName;
    }

    public List<Entity> getEntities() {
        return mEntities;
    }

    public String getComment() {
        return mComment;
    }

    public Rule setComment(String comment) {
        mComment = comment;
        return this;
    }

    /**
     * Whether we need to add "clear" method to clear all data saved in sp.
     *
     * @return false as default;
     */
    public boolean isCanClear() {
        return mCanClear;
    }

    /**
     * Whether we need to add "clear" method to clear all data saved in sp.
     */
    public void setCanClear(boolean canClear) {
        mCanClear = canClear;
    }

}
