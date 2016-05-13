
package ${schema.spPackage};

import android.content.Context;
import android.content.SharedPreferences;
<#if hasSet>
import android.annotation.TargetApi;
import android.os.Build;

import java.util.Set;
</#if>

/**
 * ${rule.clssName}
 */
public class ${rule.clssName} {

    private static class Ins {
        static final ${rule.clssName} sInstance = new Sp();
    }

    public static ${rule.clssName} getInstance() {
        return Ins.sInstance;
    }

    public final static String SETTING_NAME = "${rule.settingName}";

    private SharedPreferences mPreferences;

    private ${rule.clssName}() {

    }

    void init(Context context) {
        mPreferences = context.getSharedPreferences(SETTING_NAME, Context.MODE_PRIVATE);
    }

    public void setA(boolean a) {
        mPreferences.edit()
                .putBoolean("key", a)
                .apply();
    }

    public boolean isA() {
        return mPreferences.getBoolean("key", false);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void setB(Set<String> b){
        mPreferences.edit().putStringSet("key", b).apply();
    }
}
