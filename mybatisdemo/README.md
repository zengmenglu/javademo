# Sql连接

## Mybatis V.S. Hybernate

1）sql 优化方面
* Hibernate 不需要编写大量的 SQL，就可以完全映射，提供了日志、缓存、级联（级联比 MyBatis 强大）等特性，
此外还提供 HQL（Hibernate Query Language）对 POJO 进行操作。但会多消耗性能。
* MyBatis 手动编写 SQL，支持动态 SQL、处理列表、动态生成表名、支持存储过程。工作量相对大些。

2）开发方面
* MyBatis 是一个半自动映射的框架，因为 MyBatis 需要手动匹配 POJO、SQL 和映射关系。
* Hibernate 是一个全表映射的框架，只需提供 POJO 和映射关系即可。

3）Hibernate 优势
* Hibernate 的 DAO 层开发比 MyBatis 简单，Mybatis 需要维护 SQL 和结果映射。
* Hibernate 对对象的维护和缓存要比 MyBatis 好，对增删改查的对象的维护要方便。
* Hibernate 数据库移植性很好，MyBatis 的数据库移植性不好，不同的数据库需要写不同 SQL。
* Hibernate 有更好的二级缓存机制，可以使用第三方缓存。MyBatis 本身提供的缓存机制不佳。

4）Mybatis优势
* MyBatis 可以进行更为细致的 SQL 优化，可以减少查询字段。
* MyBatis 容易掌握，而 Hibernate 门槛较高。

总的来说，MyBatis 是一个小巧、方便、高效、简单、直接、半自动化的持久层框架，Hibernate 是一个强大、方便、高效、复杂、间接、全自动化的持久层框架。

所以对于性能要求不太苛刻的系统，比如管理系统、ERP 等推荐使用 Hibernate，而对于性能要求高、响应快、灵活的系统则推荐使用 MyBatis。

## Mybatis

发布包下载：https://github.com/mybatis/mybatis-3/releases 
内含Mybatis使用手册。

### 开发过程：

需要有如下配置文件：
* sql参数配置+mybatis连接配置，详见 mysql.properties和mybatis-config.xml文件。
* mapping文件:主要是sql语句解析，详见Employeer.xml文件。

注：

* JDBC连接MYsql时会出现中文或时区报错问题，连接URL应加上字符集与时区信息，如下：

```
url=jdbc:mysql://localhost:3306/testdb?charset=utf8&serverTimezone=UTC
```

* xml文件以```<```符号开头且前面不能有空格或空行。