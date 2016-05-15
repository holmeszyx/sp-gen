package z.hol.spgen;

import java.util.Set;

/**
 * 一条配置信息
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


    private String mKey;
    private int mType;
    private Object mDefValue;

    private String mParamName;

    private String mTypeName;
    private String mTypeMethodName;
    private String mComment;
    private String mConstName;

    public Entity(String key) {
        mKey = key;
        getParamName();
    }

    public String getComment() {
        return mComment;
    }

    public Entity setComment(String comment) {
        mComment = comment;
        return this;
    }

    public String getKey() {
        return mKey;
    }

    public String getParamName() {
        if (mParamName == null) {
            // 去掉_以及-, 并将下个字母的小字转大小
            final String name = mKey;
            int length = name.length();
            StringBuilder sb = new StringBuilder(length);
            boolean nextCap = false;
            for (int i = 0; i < length; i ++) {
                char c = name.charAt(i);
                if (c == '_' || c == '-') {
                    nextCap = true;
                    continue;
                }
                if (sb.length() > 0 && nextCap && c >= 'a' && c <= 'z') {
                    c = Character.toUpperCase(c);
                    nextCap = false;
                }
                sb.append(c);
            }
            mParamName = sb.toString();
        }
        return mParamName;
    }

    public String getConstName() {
        if (mConstName == null) {
            final String name = mParamName;
            int length = name.length();
            StringBuilder sb = new StringBuilder(length + 8);
            for (int i = 0; i < length; i ++) {
                char c = name.charAt(i);
                if (c >= 'A' && c <= 'Z') {
                    c = Character.toLowerCase(c);
                    sb.append("_");
                }
                sb.append(c);
            }
            mConstName = sb.toString();
        }
        return mConstName;
    }

    public int getType() {
        return mType;
    }

    public Entity asInt() {
        mType = INDEX_TYPE_INT;
        mTypeName = "int";
        mTypeMethodName = "int";
        return this;
    }

    public Entity asLong() {
        mType = INDEX_TYPE_LONG;
        mTypeName = "long";
        mTypeMethodName = "long";
        return this;
    }

    public Entity asFloat() {
        mType = INDEX_TYPE_FLOAT;
        mTypeName = "float";
        mTypeMethodName = "float";
        return this;
    }

    public Entity asBoolean() {
        mType = INDEX_TYPE_BOOLEAN;
        mTypeName = "boolean";
        mTypeMethodName = "boolean";
        return this;
    }

    public Entity asString() {
        mType = INDEX_TYPE_STRING;
        mTypeName = "String";
        mTypeMethodName = "String";
        return this;
    }

    public Entity asStringSet() {
        mType = INDEX_TYPE_STRING_SET;
        mTypeName = "Set<String>";
        mTypeMethodName = "StringSet";
        return this;
    }

    public Object getDefValue() {
        if (mDefValue == null) {
            switch (mType) {
                case INDEX_TYPE_BOOLEAN:
                    mDefValue = "false";
                    break;
                case INDEX_TYPE_FLOAT:
                    mDefValue = 0F;
                    break;
                case INDEX_TYPE_INT:
                    mDefValue = 0;
                    break;
                case INDEX_TYPE_LONG:
                    mDefValue = 0L;
                    break;
                case INDEX_TYPE_STRING:
                    mDefValue = "null";
                    break;
                case INDEX_TYPE_STRING_SET:
                    mDefValue = "null";
                    break;
            }
        }
        return mDefValue;
    }

    public Entity defaultValue(int defValue) {
        mDefValue = defValue;
        return this;
    }

    public Entity defaultValue(float defValue) {
        mDefValue = defValue;
        return this;
    }

    public Entity defaultValue(long defValue) {
        mDefValue = defValue;
        return this;
    }

    public Entity defaultValue(boolean defValue) {
        mDefValue = defValue;
        return this;
    }

    public Entity defaultValue(String defValue) {
        mDefValue = defValue;
        return this;
    }

    public Entity defaultValue(Set<String> defValue) {
        mDefValue = defValue;
        return this;
    }

    public String getTypeName() {
        return mTypeName;
    }

    public String getTypeMethodName() {
        return mTypeMethodName;
    }
}
