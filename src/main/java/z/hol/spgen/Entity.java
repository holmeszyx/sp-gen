package z.hol.spgen;

import java.util.Set;

/**
 * 一条配置信息
 * Created by holmes on 16-5-13.
 */
public class Entity {

    /**
     * int
     */
    public static final int INDEX_TYPE_INT = 1;

    /**
     * long
     */
    public static final int INDEX_TYPE_LONG = 2;

    /**
     * boolean
     */
    public static final int INDEX_TYPE_BOOLEAN = 3;

    /**
     * float
     */
    public static final int INDEX_TYPE_FLOAT = 4;

    /**
     * string
     */
    public static final int INDEX_TYPE_STRING = 5;

    /**
     * string_set
     */
    public static final int INDEX_TYPE_STRING_SET = 6;


    private String mKey;
    private int mType;
    private Object mDefValue;

    private String mParamName;

    private String mTypeName;
    private String mTypeMethodName;
    private String mComment;
    private String mConstName;

    private TextIntercept mParamKeyIntercept;
    private TextIntercept mConstKeyIntercept;


    public Entity(String key, TextIntercept paramKeyIntercept) {
        mKey = key;
        mParamKeyIntercept = paramKeyIntercept;
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

    public TextIntercept getParamKeyIntercept() {
        return mParamKeyIntercept;
    }

    /**
     * set the intercept for the param name in getter/setter method
     * @param paramKeyIntercept
     */
    public Entity setParamKeyIntercept(TextIntercept paramKeyIntercept) {
        if (mParamKeyIntercept != paramKeyIntercept) {
            mParamKeyIntercept = paramKeyIntercept;
            mParamName = null;
            getParamName();
        }
        return this;
    }

    public TextIntercept getConstKeyIntercept() {
        return mConstKeyIntercept;
    }

    /**
     * set the intercept for const name of preference name
     * @param constKeyIntercept
     */
    public Entity setConstKeyIntercept(TextIntercept constKeyIntercept) {
        mConstKeyIntercept = constKeyIntercept;
        return this;
    }

    public String getParamName() {
        if (mParamName == null) {
            TextIntercept intercept = mParamKeyIntercept;
            if (intercept == null) {
                intercept = ParamNameIntercept.INSTANCE;
            }
            mParamName = intercept.intercept(mKey);
            if (mParamName == null) {
                throw new IllegalArgumentException("the return for param name should be not null.");
            }
        }
        return mParamName;
    }

    public String getConstName() {
        if (mConstName == null) {
            TextIntercept intercept = mConstKeyIntercept;
            if (intercept == null) {
                intercept = ConstNameIntercept.INSTANCE;
            }
            mConstName = intercept.intercept(mParamName);
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

    /**
     * the suffix for default values.
     * Like the "L" in long value "123L".
     * @return
     */
    public String getDefValueSuffix() {
        switch (mType) {
            case INDEX_TYPE_BOOLEAN:
                return null;
            case INDEX_TYPE_FLOAT:
                return "F";
            case INDEX_TYPE_INT:
                return null;
            case INDEX_TYPE_LONG:
                return "L";
            case INDEX_TYPE_STRING:
                return null;
            case INDEX_TYPE_STRING_SET:
                return null;
        }
        return null;
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
        if (defValue != null) {
            StringBuilder sb = new StringBuilder(defValue.length() + 3);
            sb.append("\"");
            sb.append(defValue);
            sb.append("\"");
            mDefValue = sb.toString();
        }
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
