<#setting datetime_format="MM/dd/yyyy HH:mm:ss">
<#assign createTime = .now>
package ${schema.spPackage};

import android.content.Context;
import android.content.SharedPreferences;
<#if hasSet>
import android.annotation.TargetApi;
import android.os.Build;

import java.util.Set;
</#if>

// Not recommended to manually modify.
/**
 * <#if rule.comment??>${rule.comment}<#else>${rule.clssName}</#if>
 * Auto created by SpGenerator. On ${createTime?datetime}
 */
public class ${rule.clssName} {

    public static final int VERSION = ${schema.version};

    private static class Ins {
        static final ${rule.clssName} sInstance = new ${rule.clssName}();
    }

    public static ${rule.clssName} getInstance() {
        return Ins.sInstance;
    }

    public final static String SETTING_NAME = "${rule.settingName}";

    public interface Keys {
    <#list rule.entities as entity>
        /** <#if entity.comment??>${entity.comment}<#else>${entity.key}</#if>  */
        String ${entity.constName?upper_case} = "${entity.key}";
    </#list>
    }

    private SharedPreferences mPreferences;

    private ${rule.clssName}() {

    }

    void init(Context context) {
        mPreferences = context.getSharedPreferences(SETTING_NAME, Context.MODE_PRIVATE);
    }

<#list rule.entities as entity>
    /**
     * <#if entity.comment??>Set ${entity.comment}<#else>Set ${entity.key}</#if>
     */
    public void set${entity.paramName?cap_first}(${entity.typeName} ${entity.paramName}) {
        mPreferences.edit().put${entity.typeMethodName?cap_first}("${entity.key}", ${entity.paramName})
                            .apply();
    }

    <#if entity.type != 3>
    /**
     * <#if entity.comment??>${entity.comment}<#else>Get ${entity.key}</#if>
     */
    <#if entity.type == 6>
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    </#if>
    public ${entity.typeName} get${entity.paramName?cap_first}() {
    <#else>
    /**
     * <#if entity.comment??>${entity.comment}<#else>Get ${entity.key}</#if>
     */
    public ${entity.typeName} is${entity.paramName?cap_first}() {
    </#if>
        return mPreferences.get${entity.typeMethodName?cap_first}("${entity.key}", ${entity.defValue?string});
    }

</#list>
}
