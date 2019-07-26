# WEB文件管理系统
> 前几天搞了一个文件管理系统，因为几乎做每一个项目都涉及到上传文件这一步骤，不可能每一个项目都做一个模块处理文件，所以我最近整了一个小文件系统来用，这样子别的项目上传文件的话，直接可以上传到该项目即可。

整体效果，如下：
#### 项目结构
![avatar](https://raw.githubusercontent.com/bgylde/filesystem/master/images/%E9%A1%B9%E7%9B%AE%E7%BB%93%E6%9E%84.png)

#### 首页效果

![avatar](https://github.com/bgylde/filesystem/raw/master/images/%E6%96%87%E4%BB%B6%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F.png)

#### 日志查看

![avatar](https://github.com/bgylde/filesystem/raw/master/images/%E6%97%A5%E5%BF%97%E8%AE%B0%E5%BD%95.png)

#### (1)解决了什么问题？

 - 简化其他项目的文件处理（上传 | 下载）问题，统一处理
 - 个人的小云盘，并且可以提供外链访问下载等

> 这个文件系统主要功能其中包括`上传文件`、`下载文件`、`在线观看`、`删除文件`、`文件检索`、`访问监控`等

#### (2)用到的技术栈  

##### 前端
   - freemarker
   - bootstrap
   - bootstrap-table
   - jquery

##### 后台
   - springboot 2.1.3.RELEASE
   - spring-data-jpa 2.1.3.RELEASE
   - mysql 5.7

##### 搭建
   - tomcat 9.0.1 (or 7.0)
   - maven 3.5.4

##### 其他依赖
   - fastjson 1.2.54


#### (3)未来的期望
暂时的话不想继续完善了，先实习然后学习一段时间先，基本功能差不多，继续添加的功能的话，就是为了用技术而作了，等有时间了会考虑继续迭代吧

- 多用户(用户管理)
- 文件管理(文件签名)
- 权限控制（spring security）
- 分布式文件存储（hadoop HDFS）：正在学习中...

#### (4)哪里下载

* [github](https://github.com/HouYuSource/filesystem.git)

导入注意事项:

 1. sql文件

    修改 application.properties
    ```properties
    spring.jpa.hibernate.ddl-auto=create-drop # 自动创建表，最好只使用create，并且在第二次开启时关闭配置
    ```
	
 2. 修改数据库
	```properties
    spring.datasource.url=jdbc:mysql://localhost/{database}?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC
    ```

##### 交流
博客同步到[SHY BLOG](https://www.shaines.cn)
mail ：for.houyu@qq.com

