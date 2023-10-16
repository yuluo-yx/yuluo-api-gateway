此模块的主要作用是 spring boot starter 相关的东西，让 api-gateway-core 注册到 api-gateway-engine

本模块最大的目的是搭建起用于封装网关算力服务的 api-gateway-core 系统为目的，提供网关服务注册与发现能力
之所以开发这个模块是希望把巩公用的算力进行统一的管理，如果没有这样的东西，就需要将每一个 spring boot 服务都做这样的处理，
成本很大。

core 模块是网关的算力服务，center 是 gateway 的注册中心，为了连接 core 和 center 模块，中间需要一个 engin 网关引擎，用于启动网关的算力服务

由于启动网关的算力服务还需要一些功能的整合，来包装网管算力到注册中心的链接，所以需要整合到 assist 模块中，是一个 spring boot starter，起包装和连接作用。
