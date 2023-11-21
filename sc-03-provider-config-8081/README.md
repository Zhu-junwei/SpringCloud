使用注册中心的配置

depart-provider-dev.yaml
```yaml
app:
  version: 1.0.0
server:
  port: 8081
spring:
  datasource:
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/sc?serverTimezone=Asia/Shanghai&useSSL=false&characterEncoding=utf-8&rewriteBatchedStatements=true
  application:
    name: depart-provider # 微服务名称
  config:
    import:
      - optional:nacos:${spring.application.name}.${spring.cloud.nacos.config.file-extension}
  cloud:
    nacos:
      config:
        file-extension: yaml
        server-addr: nacos-local:8848 # nacos注册中心地址
        username: nacos # 用户名密码
        password: nacos
      discovery:
        server-addr: nacos-local:8848 # nacos注册中心地址
        username: nacos # 用户名密码
        password: nacos

mybatis-plus:
  # 别名包扫描路径
  type-aliases-package: com.zjw.domain
  global-config:
    db-config:
      # 设置id字段为自增长
      id-type: auto
      logic-delete-field: deleted #逻辑删除的字段
      logic-delete-value: 1 # 已经逻辑删除的记录该字段值
      logic-not-delete-value: 0 # 未被逻辑删除的记录该字段值
  configuration:
    # mybatis二级缓存，默认为true，开启
    cache-enabled: false
    # 是否开启自动驼峰命名规则，默认为true，开启
    map-underscore-to-camel-case: true
    # 设置控制台日志打印，默认不显示SQL语句
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    # 枚举字段加上了@EnumValue注解后默认使用的是MybatisEnumTypeHandler，否则默认为mybatis EnumTypeHandler
    default-enum-type-handler: com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler
  # Mapper 所对应的 XML 文件位置，默认为“classpath*:/mapper/**/*.xml”
  mapper-locations:
    - classpath*:/mapper/**/*.xml


```