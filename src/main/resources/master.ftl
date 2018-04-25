<#ftl encoding='UTF-8'>
<#setting datetime_format="MM/dd/yyyy HH:mm:ss">
<#assign createTime = .now>
package ${schema.spPackage};

import android.content.Context;
<#list rules as rule>
import ${schema.spPackage}.${rule.clssName};
</#list>

// Not recommended to modify manually.
// see https://github.com/holmeszyx/sp-gen
/**
 * To initialize all shared preference generated classes
 * Auto created by SpGenerator. On ${createTime?datetime}
 */
public final class SpMaster {

    public static final int VERSION = ${schema.version};

    /**
     * initialize all.
     * Call it on Application's "onCreate()"
     */
    public static void init(Context context) {
<#list rules as rule>
        ${rule.clssName}.getInstance().init(context);
</#list>
    }

}
