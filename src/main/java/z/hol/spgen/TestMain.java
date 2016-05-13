package z.hol.spgen;

import java.io.IOException;

/**
 * Created by holmes on 5/13/16.
 */
public class TestMain {

    public static void main(String[] args) throws IOException {
        Schema schema = new Schema("a.z.g", 1);
        Rule r = schema.addRule("GoSp", "go_sp");
        r.addEntity("key1").asInt();
        r.addEntity("key2_a").asString();
        r.addEntity("key3_b").asBoolean();

        SpGenerator generator = new SpGenerator();
        generator.generateAll(schema, "src-gen/");

    }

}
