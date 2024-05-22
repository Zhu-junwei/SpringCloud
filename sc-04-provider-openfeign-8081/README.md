生产者，提供服务，注册到注册中心nacos，消费者使用openfeign进行消费

# openfeign简介

> openfeign是springcloud提供的一个用于简化restful调用的工具，通过注解的方式来调用服务，不需要写httpclient的代码，只需要写接口即可，非常方便。

# 使用

1. 添加依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```
2. 添加注解

```java
@SpringBootApplication
@EnableFeignClients
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
```

3. 添加接口

```java
@FeignClient(value = "depart-provider", path = "/depart")
public interface IDepartService {

    /**
     * 保存部门
     */
    @PostMapping("/save")
    boolean save(@RequestBody Depart depart);

    /**
     * 删除部门
     */
    @DeleteMapping("/delete/{id}")
    boolean remove(@PathVariable Long id);

    /**
     * 更新部门
     */
    @PutMapping("/update")
    boolean update(@RequestBody Depart depart);

    /**
     * 根据id查询部门
     */
    @GetMapping("/get/{id}")
    Depart get(@PathVariable Long id);

    /**
     * 查询所有部门
     */
    @GetMapping("/list")
    List<Depart> list();

    @GetMapping("/discovery")
    List<String> discovery();
}
```