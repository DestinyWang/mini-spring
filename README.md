# 概括
本项目是在阅读 Spring 源码的过程中, 尝试通过 `TDD(测试驱动)` 来还原 Spring(`3.2.9.RELEASE`)如下几个核心抽象概念的设计过程:
1. BeanFactory
2. ApplicationContext
3. 基于 `XML` 的 IoC
4. 基于 `@Annotation` 的 IoC
5. AOP

# 分支介绍
为了能够较为清晰的表现 Spring 设计的演进过程, 项目使用不同分支来体现.

- `master`: 最终的稳定版本
- `branch-ctx`: BeanFactory 和 ApplicationContext 的最初设计

# 文档目录
- [BeanFactory 设计](https://github.com/DestinyWang/mini-spring/blob/master/doc/bean-factory.md)
