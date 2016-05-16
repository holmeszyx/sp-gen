<#setting datetime_format="MM/dd/yyyy HH:mm:ss">
<#assign createTime = .now>
package ${schema.spPackage};

import android.content.Context;
<#list rules as rule>
import ${schema.spPackage}.${rule.clssName};
</#list>

// Not recommended to manually modify.
/**
 * To init all shared preference generated classes
 * Auto created by SpGenerator. On ${createTime?datetime}
 */
public final class SpMaster {

    public static final int VERSION = ${schema.version};

    /**
     * init all.
     * Call it on Application's "onCreate()"
     */
    public static void init(Context context) {
<#list rules as rule>
        ${rule.clssName}.getInstance().init(context);
</#list>
    }

}
