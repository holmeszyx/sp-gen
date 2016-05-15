package z.hol.spgen;

import java.util.ArrayList;
import java.util.List;

/**
 * 规则, 对应一个配置文件
 * Created by holmes on 16-5-13.
 */
public class Rule {

    public String clssName;
    public String settingName;

    private List<Entity> mEntities;
    private String mComment;

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
}
