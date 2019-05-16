# Spring Cloud Example

	Spring Cloud为开发人员提供了快速构建分布式系统中一些常见模式的工具（例如配置管理，服务发现，断路器，智能路由，微代理，控制总线，一次性令牌，全局锁定，领导选举，分布式会话，集群状态）。 分布式系统的协调导致锅炉板模式，使用Spring Cloud开发人员可以快速站起来实现这些模式的服务和应用程序。 它们适用于任何分布式环境，包括开发人员自己的笔记本电脑，裸机数据中心和Cloud Foundry等托管平台。

	这个例子是学习实践。

## 服务注册和发现

Consul 最好，服务发现，openfeign + ribbon

流控：Sentinel vs Hystrix

## 配置中心

Consul

## 网关

X-Forwarded-* 追加/覆盖
删除 HTTP 头

## Request Response

如何蓝绿发布：
/actuator/health

## 异常
405 Method Not Allowed,如 POST 的 get 请求

1、网关异常，服务找不到503，如何处理？
2、网关异常，网关 controller 异常？




