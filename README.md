sp-gen 即是 "Shared Preference Generator"。想法来源于[GreenDao](https://github.com/greenrobot/greenDAO)

用于生成Android中直接调用Shared preference的代码。
主要目的:

1. 解放懒人。不用再人肉写一些繁琐配置接口代码。
2. 明确定义每条数据的类型，配合IDE避免通用类型增加记忆负担，并消除人为的类型错误。
3. 简单明了的定义文件, 方便管理。

## 使用

### 一、创建一个基于gradle java项目

在相应目录下新建一个可执行的java 类。

### 二、配置Gralde :

加入依赖

`compile 'z.hol.spgen:sp-gen:1.0.3'`

引入jcenter仓库

```
repositories {
    jcenter()
}
```

最后通过gradle `application` 插件， 指定可执行的java类。

完整gralde可见example.

```groovy
apply plugin: 'java'
apply plugin:'application'

sourceCompatibility = 1.5

repositories {
    jcenter()
}

dependencies {
    compile 'z.hol.spgen:sp-gen:1.0.3'
}

mainClassName = "z.hol.spgen.example.GenExample"
```

### 三、编写sp定义规则

在可执行的java类的main函数中按需加入规则代码。

规则包含3部分。

#### Schema 
用于定义要生成的sp所在的包名
    
#### Rule
一类sp的集合， 用于指定sp操作集合的的类名和存储share preference的(xml)文件名

如果需要添加一个清空此Share preference的接口，在Rule上设置`Rule.setCanClear(true)`.
这样会生成一个clear方法，用于清空数据。

#### Entity
一条数据项.用于指定share preference中的一条数据对应的key名称，类型，默认值，相关注释。

对应关系. 
`Schema` -> N个`Rule`
`Rule` -> N个`Entity`

最后指定输出目录，定义通过`Schema`来生成相关的代码文件.

```java

public static void main(String[] args) throws IOException {
    Schema schema = new Schema("com.test.setting", 1);
    
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

```

### 四、执行

在gradle项目目录下执行
```
gralde :run
```

例如，执行example代码：

```bash
# 在gradle项目的根目录下
gralde :example:run
```


### 五、引入到Android项目中

将生成好的代码，直接引入(copy)到Android项目就可以了。

每个`Schema`会在其指定的包名下生成一个`SpMaster`和若干个其Rule中所指定的类型名(代有Sp后缀)的java文件。

其中SpMaster用于初始化所有的Sp操作规则类（Rule中定义的哪些）。需要放在Application中执行一次。

如：

```java
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        SpMaster.init(this);
    }
    
}
```
初始化后，就可以在任意地方来直接操作所定义的Share Perference了。

如调用例子中所生成的代码:

```java

pubilc class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // do something...
        TextView name;
        // ...
        
        if (SettingSp.getInstance().isFirstLaunch) {
            //do something...
            SettingSp.getInstance().setFirstLaunch(false);
        } else {
            UserSp us = UserSp.getInstance();
            if (!TextUtils.isEmpty(us.getName())) {
                name.setText(us.getName());
            } else {
                us.setName("foooo");
            }
        }
    }
}
```

### License

sp-gen 基于开源协议 Apache License 2.0