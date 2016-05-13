package z.hol.spgen;

import java.util.Set;

/**
 * 一个实体
 * Created by holmes on 16-5-13.
 */
public class Entity {

    /** int */
    public static final int INDEX_TYPE_INT = 1;

    /** long */
    public static final int INDEX_TYPE_LONG = 2;

    /** boolean */
    public static final int INDEX_TYPE_BOOLEAN = 3;

    /** float */
    public static final int INDEX_TYPE_FLOAT = 4;

    /** string */
    public static final int INDEX_TYPE_STRING = 5;

    /** string_set */
    public static final int INDEX_TYPE_STRING_SET = 6;


    private String mName;
    private int mType;
    private Object mDefValue;

    public Entity(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public int getType() {
        return mType;
    }

    public void asInt() {
        mType = INDEX_TYPE_INT;
    }

    public void asLong() {
        mType = INDEX_TYPE_LONG;
    }

    public void asFloat() {
        mType = INDEX_TYPE_FLOAT;
    }

    public void asBoolean() {
        mType = INDEX_TYPE_BOOLEAN;
    }

    public void asString() {
        mType = INDEX_TYPE_STRING;
    }

    public void asStrignSet() {
        mType = INDEX_TYPE_STRING_SET;
    }

    public void defaultValue(int defValue) {
        mDefValue = defValue;
    }

    public void defaultValue(float defValue) {
        mDefValue = defValue;
    }

    public void defaultValue(long defValue) {
        mDefValue = defValue;
    }

    public void defaultValue(boolean defValue) {
        mDefValue = defValue;
    }

    public void defaultValue(String defValue) {
        mDefValue = defValue;
    }

    public void defaultValue(Set<String> defValue) {
        mDefValue = defValue;
    }

}
