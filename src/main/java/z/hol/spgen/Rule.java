package z.hol.spgen;

import java.util.ArrayList;
import java.util.List;

/**
 * SharedPreferences规则, 对应一个配置文件
 * Created by holmes on 16-5-13.
 */
public class Rule {

    public String clssName;
    public String settingName;

    private List<Entity> mEntities;
    private String mComment;

    private boolean mCanClear = false;

    /**
     * SharedPreferences
     * @param clssName 操作SharedPreferences的类(不包括包名)
     * @param settingName SharedPreferences的文件名
     */
    public Rule(String clssName, String settingName) {
        this.clssName = clssName;
        this.settingName = settingName;
        mEntities = new ArrayList<Entity>(16);
    }

    public Entity addEntity(String name) {
        Entity entity = new Entity(name);
        mEntities.add(entity);
        return entity;
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
     * @return false as default;
     */
    public boolean isCanClear() {
        return mCanClear;
    }

    /**
     * Whether we need to add "clear" method to clear all data saved in sp.
     * @param canClear
     */
    public void setCanClear(boolean canClear) {
        mCanClear = canClear;
    }

}
