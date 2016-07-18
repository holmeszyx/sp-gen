package z.hol.spgen.example;

import z.hol.spgen.Rule;
import z.hol.spgen.Schema;
import z.hol.spgen.SpGenerator;

import java.io.IOException;

/**
 * Example
 * Created by holmes on 5/13/16.
 */
public class GenExample {

    public static void main(String[] args) throws IOException {
        Schema schema = new Schema("a.z.g", 1);
        Rule r = schema.addRule("User", "user_setting");
        r.setComment("用户设置");
        r.addEntity("count").asInt().defaultValue(-1).setComment("刷新次数");
        r.addEntity("tokens").asString().setComment("login token");
        r.addEntity("name").asString().defaultValue("");
        r.addEntity("need_refresh").asBoolean().defaultValue(true);
        r.addEntity("avatar_url").asBoolean();
        r.addEntity("tags").asStringSet();

        Rule r2 = schema.addRule("Setting", "app_setting");
        r2.setCanClear(true);
        r2.setComment("应用设置");
        r2.addEntity("first_launch").asBoolean().defaultValue(false);
        r2.addEntity("last_login_timestamp").asLong();
        r2.addEntity("price").asFloat().defaultValue(12.3F);

        SpGenerator generator = new SpGenerator();
        generator.generateAll(schema, "src-gen/");

    }

}
