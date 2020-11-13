# Ribbon with Eureka

Ribbon 是一种客户端的负载均衡工具。

说明：

* eureka：本例采用eureka作为服务发现工具。可视化界面 http://localhost:8761/ ,用户名密码：zml/zml
* serviceB: 客户端，集成Ribbon负载均衡策略，本例为随机策略。 端口号：9500
* serviceA, serviceA2: 服务提供端，提供相同的服务，只有端口号不同。为serviceB提供服务。端口号：8088,8089

使用：

调用serviceB的restful API，例如： ```localhost:9500/sayHello?name=AA```, serviceB会根据均衡策略从serviceA和serviceA2中选择一个来为自己提供服务。