# 配置文件重要信息加密

### 1.配置文件必须是 application.properties否则会报错
### 2.xml中引入
    2.1 依赖
        <dependency>
            <groupId>com.github.ulisesbocchio</groupId>
            <artifactId>jasypt-spring-boot-starter</artifactId>
            <version>3.0.3</version>
        </dependency>
    2.2 插件
        <plugin>
            <groupId>com.github.ulisesbocchio</groupId>
            <artifactId>jasypt-maven-plugin</artifactId>
            <version>3.0.3</version>
        </plugin>
### 3.配置文件中需要加密的使用DEC() 如：passwd=DEC(123)
### 4.在配置文件中添加jasypt.encryptor.password=lee #加密密钥
### 5.mvn jasypt:encrypt -Djasypt.encryptor.password=lee
