# SHIT-SSM-ESHOP

一个充满了 Java <del>臭味</del>的 基于 Spring + Mybatis 的 非常简单 但却 异常安全 的 电子商城

**MAKE THAT DAMN SIMPLE BUT SUPER SECURE SHOP GREAT AGAIN!**

## 开发要求

开发相关：

API 列表：参看 Swagger

开发要求：

- 统一使用 LF 换行和 IDEA，保存为 UTF-8 (no BOM)！
- PR 合并，多分支 开发，Commit 前一定先 Pull！出现任何相关的问题请私聊。
- 禁止使用 CSDN&Baidu（包括但不限于百度搜索、百度知道、百度经验），可以使用 SegmentFault/Myblogs/Jianshu/Stack Exchanges 全家桶/Google.
- 提问前阅读 https://github.com/ryanhanwu/How-To-Ask-Questions-The-Smart-Way/blob/main/README-zh_CN.md
- 严格逻辑，禁止完全依赖前端校验，用户输入完全不可信！
- 用户输入禁止任何形式的后端直接字符串拼接和前端直接引用，务必清洗/重命名/指定独立路径另机存放！

认证方式： Authorization Header using Bearer Token with JWT

明确指定验证方式，禁止使用弱密钥！

用户请求字符串必须排序后添加时间戳再签名，服务端时间一定要 开启 NTP 时间同步！
