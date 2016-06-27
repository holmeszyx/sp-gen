package z.hol.spgen;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成
 * Created by holmes on 5/13/16.
 */
public class SpGenerator {

    private Template mTemplateMaster;
    private Template mTemplateSp;

    public SpGenerator() throws IOException {
        Configuration config = new Configuration(Configuration.VERSION_2_3_23);
        config.setClassForTemplateLoading(this.getClass(), "/");

        mTemplateSp = config.getTemplate("sp.ftl");
        mTemplateMaster = config.getTemplate("master.ftl");

    }

    /**
     * 生成代码
     * @param schema 规则集
     * @param pathDir 代码目录
     */
    public void generateAll(Schema schema, String pathDir) {
        File outDir = new File(pathDir);

        List<Rule> ruleList = schema.getRules();

        HashMap<String, Object> root = new HashMap<String, Object>(16);
        root.put("schema", schema);
        root.put("rules", ruleList);
        generate(schema, mTemplateMaster, root, outDir, "SpMaster");


        List<Rule> rules = ruleList;
        for (Rule rule : rules) {
            List<Entity> entities = rule.getEntities();
            generate(schema, mTemplateSp, rule, entities, outDir);
        }

    }

    private void generate(Schema schema, Template template, Rule rule, List<Entity> entities, File outDir) {
        HashMap<String, Object> root = new HashMap<String, Object>(16);
        root.put("schema", schema);
        root.put("rule", rule);
        boolean hasSet = false;
        for (Entity entity : entities) {
            if (entity.getType() == Entity.INDEX_TYPE_STRING_SET) {
                hasSet = true;
                break;
            }
        }
        root.put("hasSet", hasSet);

        File outFile = toJavaFilename(outDir, schema.getSpPackage(), rule.getClssName());
        outFile.getParentFile().mkdirs();

        try {
            Writer outWriter = new FileWriter(outFile);
            try {
                template.process(root, outWriter);
                outWriter.flush();
                System.out.println("create file " + outFile.getName());
            } finally {
                outWriter.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    private void generate(Schema schema, Template template, Map<?, ?> rootObj, File outDir, String className) {

        File outFile = toJavaFilename(outDir, schema.getSpPackage(), className);
        outFile.getParentFile().mkdirs();

        try {
            Writer outWriter = new FileWriter(outFile);
            try {
                template.process(rootObj, outWriter);
                outWriter.flush();
                System.out.println("create file " + outFile.getName());
            } finally {
                outWriter.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    protected File toJavaFilename(File outDir, String packageName, String clssName) {
        String packageSubPath = packageName.replace('.', '/');
        File packagePath = new File(outDir, packageSubPath);
        File file = new File(packagePath, clssName + ".java");
        return file;
    }


}
