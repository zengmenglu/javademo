# Restful API and configuration file parse

1.Restful

restful接口注解如下：
 ```
@RestController
```
类名前加上该注解，则spring框架会自动监听类里面定义的restful API接口.默认端口号8080.

 
定义restful接口有两种方法：
```
 @RequestMapping(value = "/hello", method = RequestMethod.GET)

 @GetMapping("/hello")
```


2.配置文件：
* 同一目录下，如果多个配置文件中配置项名称冲突，则优先识别默认的application.properties中的配置。
* yaml文件后缀可以是.yaml,也可以是.yml
* yaml不支持配置项名称大写开头。