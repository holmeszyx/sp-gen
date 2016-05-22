package z.hol.spgen;

import java.io.IOException;

/**
 * Created by holmes on 5/13/16.
 */
public class TestMain {

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
        r2.setComment("应用设置");
        r2.addEntity("is_first_launch").asBoolean().defaultValue(false);
        r2.addEntity("last_login_timestamp").asLong();

        SpGenerator generator = new SpGenerator();
        generator.generateAll(schema, "src-gen/");

        Schema yue = new Schema("fm.yue.android.setting", 1);
        Rule yueR = yue.addRule("Setting", "yue_setting");
        yueR.addEntity("token").asString().setComment("登录后的token");
        yueR.addEntity("token_refreshed").asLong().setComment("token刷新的时间戳");
        yueR.addEntity("user_id").asLong().defaultValue(-1L).setComment("用户id， -1就是没有登录");
        yueR.addEntity("user_name").asString().setComment("登录了的用户名");
        yueR.addEntity("user_avatar").asString().setComment("登录后的用户头像");
        yueR.addEntity("last_user_profile").asString().setComment("最后一次用户的信息json");
        yueR.addEntity("night_mode").asBoolean().defaultValue(false).setComment("是否使用夜间模式");
        generator.generateAll(yue, "src-gen/");


    }

}
