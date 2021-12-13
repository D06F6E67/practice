# mybatis 《mybatis从入门到精通》
## 1.通过ssh链接数据库
### 1.1依赖
    <dependency>
            <groupId>com.jcraft</groupId>
            <artifactId>jsch</artifactId>
            <version>0.1.55</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
        </dependency>
### 1.2 编写连接类 具体代码ssh文件夹下
### 1.3 配置mysql链接参数，需要注意的是端口是连接类中自己定义的端口
### 1.4 编写逻辑 [参考链接](https://www.jianshu.com/p/4ec1b70c0ada?tdsourcetag=s_pcqq_aiomsg)
![img.png](img.png)
### 1.5 注意细节 
    1.须在Application中添加 @ServletComponentScan 注解否则无法连接
    2.配置的本地跳转端口不能被占用
    3.如遇到连接8小时断开的问题需要添加连接池配置 
        #解决mysql 8小时的断开问题
        spring.druid.validationQuery=SELECT 'x'

