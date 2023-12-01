跨域资源共享CORS

通过右键cors.html在浏览器中访问，点击`跨域访问`按钮。
不知道为什么，按照视频的配置依然没有解决跨域问题。
```yaml
spring:
  cloud:
    gateway:
      globalcors:
        cors-configurations:
          '[/**]':
            allowedOrigins: "*"
            allowedMethods:
              - GET
              - POST
```

