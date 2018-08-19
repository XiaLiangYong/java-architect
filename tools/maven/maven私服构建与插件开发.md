### 概要：
###### 1.	maven 生命周期
###### 2.	Maven自定义插件开发
###### 3.	基于nexus构建企业私服 


###### 一、maven 生命周期
```
知识点概要：
1.	生命周期的概念与意义
2.	maven 三大生命周期与其对应的phase(阶段)
3.	生命周期与插件的关系
4.	生命周期与默认插件的绑定
```
###### 1、生命周期的概念与意义
```
在项目构建时通常会包含清理、编译、测试、打包、验证、部署，文档生成等步骤，maven 统一对其进行了整理抽像成三个生命周期 (lifecycle)及各自对应的多个阶段(phase)。这么做的意义是：
1.	每个阶段都成为了一个扩展点，可以采用不同的方式来实现，提高了扩展性与灵活性。
2.	规范统一了maven 的执行路径。
在执行项目构建阶段时可以采用jar方式构建也可以采用war包方式构建提高了灵活性。我们可以通过命令 mvn ${phase name}直接触发指定阶段的执行如：
	演示 phase 的执行
#执行清理phase
mvn clean
#执行compile phase
mvn compile
# 也可以同时执行 清理加编译
mvn clean comile
```
  ![图片 1.png](https://alibeibei.oss-cn-shanghai.aliyuncs.com/images/maven_lifecircle.png?Expires=1534699078&OSSAccessKeyId=TMP.AQH_I0ABmv95DbtqoPQ7wc2GO5qEavZN2Gtnpa4ep9y8zxmN3EBo0QjbOLF-ADAtAhQFE2mtTBIf_m1aH0f1WzT5cIOP3gIVAL1wTuxmJmcgaM0d27IBw_v6zM18&Signature=mYe9aWyhT0H33GYxnuUynaJ%2F4go%3D
)


###### 2、maven三大生命周期与其对应的phase(阶段)
```
maven 总共包含三大生生命周期
1.	clean Lifecycle ：清理生命周期，用于于清理项目
2.	default Lifecycle：默认生命周期，用于编译、打包、测试、部署等
3.	site Lifecycle 站点文档生成，用于构建站点文档
生命周期(lifecycle)	阶段(phase)	描述(describe)
clean Lifecycle 	pre-clean	预清理
	clean	清理
	post-clean	清理之后
default Lifecycle	validate	验证
	initialize	初始化
	generate-sources	
	process-sources	
	generate-resources	
	process-resources	
	compile	编译
	process-classes	
	generate-test-sources	
	process-test-sources	
	generate-test-resources	
	process-test-resources	
	test-compile	编译测试类
	process-test-classes	
	test	执行测试
	prepare-package	构建前准备
	package	打包构建
	pre-integration-test	
	integration-test	
	post-integration-test	
	verify	验证
	install	上传到本地仓库
	deploy	上传到远程仓库
site Lifecycle	pre-site	准备构建站点
	site	构建站点
	post-site	构建站点之后
	site-deploy	站点部署
三大生命周期其相互独立执行，也可以合在一起执行。但lifecycle 中的phase 是有严格执行的顺序的，比如必须是先执行完compile 才能执行pakcage 动作，此外phase 还有包含逻辑存在，即当你执行一个phase 时 其前面的phase 会自动执行。
	演示phase 执行
# 执行编译
mvn compile
# 执行打包就包含了编译指令的执行
mvn package
```
###### 3、生命周期与插件的关系
```
生命周期的phase组成了项目过建的完整过程，但这些过程具体由谁来实现呢？这就是插件，maven 的核心部分代码量其实很少，其大部分实现都是由插件来完成的。比如：test 阶段就是由 maven-surefire-plugin 实现。在pom.xml 中我们可以设置指定插件目标(gogal)与phase 绑定，当项目构建到达指定phase时 就会触发些插件gogal 的执行。 一个插件有时会实现多个phas比如：maven-compiler-plugin插件分别实现了compile 和testCompile。
 总结：
生命周期的 阶段 可以绑定具体的插件及目标
不同配置下同一个阶段可以对应多个插件和目标
phase==>plugin==>goal(功能)
```
 ![图片 1.png](https://alibeibei.oss-cn-shanghai.aliyuncs.com/images/lifecircle_01.png?Expires=1534699363&OSSAccessKeyId=TMP.AQH_I0ABmv95DbtqoPQ7wc2GO5qEavZN2Gtnpa4ep9y8zxmN3EBo0QjbOLF-ADAtAhQFE2mtTBIf_m1aH0f1WzT5cIOP3gIVAL1wTuxmJmcgaM0d27IBw_v6zM18&Signature=KRfzLdZa5x3jSnx8jv9WvDFO%2FZg%3D
)

 ![图片 1.png](https://alibeibei.oss-cn-shanghai.aliyuncs.com/images/life_circle_02.png?Expires=1534699400&OSSAccessKeyId=TMP.AQH_I0ABmv95DbtqoPQ7wc2GO5qEavZN2Gtnpa4ep9y8zxmN3EBo0QjbOLF-ADAtAhQFE2mtTBIf_m1aH0f1WzT5cIOP3gIVAL1wTuxmJmcgaM0d27IBw_v6zM18&Signature=XIHZUI6d8S59tlRZLxDle1gCY7E%3D
)



###### 4、生命周期与插件的默认绑定
```
在我们的项目当中并没有配置 maven-compiler-plugin 插件,但当我们执行compile 阶段时一样能够执行编译操作，原因是maven 默认为指定阶段绑定了插件实现。列如下以下两个操作在一定程度上是等价的。
	演示
# 
mvn compile
#直接执行compile插件目标
mvn org.apache.maven.plugins:maven-compiler-plugin:3.1:compile

lifecycle phase 的默认绑定见下表：。

 clean Lifecycle 默认绑定
<phases>
  <phase>pre-clean</phase>
  <phase>clean</phase>
  <phase>post-clean</phase>
</phases>
<default-phases>
  <clean>
    org.apache.maven.plugins:maven-clean-plugin:2.5:clean
  </clean>
</default-phases>

site Lifecycle 默认绑定
<phases>
  <phase>pre-site</phase>
  <phase>site</phase>
  <phase>post-site</phase>
  <phase>site-deploy</phase>
</phases>
<default-phases>
  <site>
    org.apache.maven.plugins:maven-site-plugin:3.3:site
  </site>
  <site-deploy>
    org.apache.maven.plugins:maven-site-plugin:3.3:deploy
  </site-deploy>
</default-phases>


Default Lifecycle JAR默认绑定
注：不同的项目类型 其默认绑定是不同的，这里只指列举了packaging 为jar 的默认绑定，全部的默认绑定参见：https://maven.apache.org/ref/3.5.4/maven-core/default-bindings.html#。
<phases>
  <process-resources>
    org.apache.maven.plugins:maven-resources-plugin:2.6:resources
  </process-resources>
  <compile>
    org.apache.maven.plugins:maven-compiler-plugin:3.1:compile
  </compile>
  <process-test-resources>
    org.apache.maven.plugins:maven-resources-plugin:2.6:testResources
  </process-test-resources>
  <test-compile>
    org.apache.maven.plugins:maven-compiler-plugin:3.1:testCompile
  </test-compile>
  <test>
    org.apache.maven.plugins:maven-surefire-plugin:2.12.4:test
  </test>
  <package>
    org.apache.maven.plugins:maven-jar-plugin:2.4:jar
  </package>
  <install>
    org.apache.maven.plugins:maven-install-plugin:2.4:install
  </install>
  <deploy>
    org.apache.maven.plugins:maven-deploy-plugin:2.7:deploy
  </deploy>
</phases>
```

### 二、maven 自定义插件开发
```
知识点：
1.	插件的相关概念
2.	常用插件的使用
3.	开发一个自定义插件
```
###### 1、maven 插件相关概念
```
插件坐标定位：
插件与普通jar 包一样包含 一组件坐标定位属性即：
groupId、artifactId、version，当使用该插件时会从本地仓库中搜索，如果没有即从远程仓库下载
<!-- 唯一定位到dependency 插件 -->
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-dependency-plugin</artifactId>
<version>2.10</version>


插件执行 execution：
execution 配置包含一组指示插件如何执行的属性：
id ： 执行器命名
phase：在什么阶段执行？
goals：执行一组什么目标或功能？
configuration：执行目标所需的配置文件？

	演示一个插件的配置与使用
# 将插件依赖拷贝到指定目录
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-dependency-plugin</artifactId>
    <version>3.1.1</version>
    <executions>
        <execution>
            <id>copy-dependencies</id>
            <phase>package</phase>
            <goals>
                <goal>copy-dependencies</goal>
            </goals>
            <configuration>              <outputDirectory>${project.build.directory}/alternateLocation</outputDirectory>
                <overWriteReleases>false</overWriteReleases>
                <overWriteSnapshots>true</overWriteSnapshots>
                <excludeTransitive>true</excludeTransitive>
            </configuration>
        </execution>
    </executions>
</plugin>


```
###### 2、常用插件的使用
```
除了通过配置的方式使用插件以外，Maven也提供了通过命令直接调用插件目标其命令格式如下：
mvn groupId:artifactId:version:goal -D{参数名}

	演示通过命令执行插件
# 展示pom的依赖关系树
mvn org.apache.maven.plugins:maven-dependency-plugin:2.10:tree
# 也可以直接简化版的命令，但前提必须是maven 官方插件
mvn dependency:tree

其它常用插件：
# 查看pom 文件的最终配置 
mvn help:effective-pom
# 原型项目生成
archetype:generate
#快速创建一个WEB程序
mvn archetype:generate -DgroupId=tuling -DartifactId=simple-webbapp -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
#快速创建一个java 项目
mvn archetype:generate -DgroupId=tuling -DartifactId=simple-java -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
###### 3、开发一个自定义插件
```
实现步骤：
	创建maven 插件项目
	设定packaging 为maven-plugin
	添加插件依赖
	编写插件实现逻辑
	打包构建插件
插件 pom 配置
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <groupId>tuling</groupId>
    <version>1.0.SNAPSHOT</version>
    <artifactId>tuling-maven-plugin</artifactId>
    <packaging>maven-plugin</packaging>
    <dependencies>
        <dependency>
            <groupId>org.apache.maven</groupId>
            <artifactId>maven-plugin-api</artifactId>
            <version>3.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugin-tools</groupId>
            <artifactId>maven-plugin-annotations</artifactId>
            <version>3.4</version>
        </dependency>
    </dependencies>
</project>

插件实现类：
package com.tuling.maven; 

import javafx.beans.DefaultProperty;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author Tommy
 *         Created by Tommy on 2018/8/8
 **/
@Mojo(name = "luban")
public class LubanPlugin extends AbstractMojo {
    @Parameter
    String sex;

    @Parameter
    String describe;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info(String.format("luban sex=%s describe=%s",sex,describe));
    }
}
```


### 三、nexus 私服搭建与核心功能
```
知识点概要:
1.	私服的使用场景
2.	nexus 下载安装
3.	nexus 仓库介绍
4.	本地远程仓库配置
5.	发布项目至nexus 远程仓库
6.	关于SNAPSHOT(快照)与RELEASE(释放) 版本说明
```
###### 1、私服使用场景
```
私服使用场景如下：
1、公司不能连接公网，可以用一个私服务来统一连接
2、公司内部jar 组件的共享
```
###### 2、nexus 下载安装
```
nexus 下载地址：
https://sonatype-download.global.ssl.fastly.net/nexus/oss/nexus-2.14.5-02-bundle.tar.gz

解压并设置环境变量
#解压
shell>tar -zxvf nexus-2.14.5-02-bundle.tar.gz
#在环境变量当中设置启动用户
shell> vim /etc/profile
#添加profile文件。安全起见不建议使用root用户，如果使用其它用户需要加相应权限
export RUN_AS_USER=root
配置启动参数：
shell> vi ${nexusBase}/conf/nexus.properties
 #端口号
 application-port=9999
启动与停止nexus
#启动
shell>  ${nexusBase}/bin/nexus start
#停止
shell>  ${nexusBase}/bin/nexus stop
登录nexus 界面
地址：http://{ip}:9999/nexus/
用户名:admin
密码：admin123
```
###### 3、nexus 仓库介绍
```
3rd party：第三方仓库
Apache Snapshots：apache 快照仓库
Central: maven 中央仓库
Releases：私有发布版本仓库
Snapshots：私有 快照版本仓库
```
###### 4 、本地远程仓库配置
```
 在pom 中配置远程仓库

<repositories>
    <repository>
        <id>nexus-public</id>
        <name>my nexus repository</name>
<url>http://192.168.0.147:9999/nexus/content/groups/public/</url>
    </repository>
</repositories>
或者在settings.xml 文件中配置远程仓库镜像 效果一样，但作用范围广了
<mirror>        
  <id>nexus-aliyun</id>
        <mirrorOf>*</mirrorOf>
        <name>Nexus aliyun</name>
  <url>http://192.168.0.147:9999/nexus/content/groups/public/</url>
</mirror> 
```
###### 5、发布项目至nexus 远程仓库
```
配置仓库地址
<distributionManagement>
    <repository>
        <id>nexus-release</id>
        <name>nexus release</name>
        <url>http://192.168.0.147:9999/nexus/content/repositories/releases/</url>
    </repository>
    <snapshotRepository>
        <id>nexus-snapshot</id>
        <name>nexus snapshot</name>
        <url>http://192.168.0.147:9999/nexus/content/repositories/snapshots/</url>
    </snapshotRepository>
</distributionManagement>

设置 setting.xml 中设置server
<server>
      <id>nexus-snapshot</id>
      <username>deployment</username>
      <password>deployment123</password>
    </server>
<server>
      <id>nexus-release</id>
      <username>deployment</username>
      <password>deployment123</password>
    </server>

执行deploy 命令
mvn deploy
```