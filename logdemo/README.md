# 日志demo

## 准备

使用了Apache 提供的第三方库 Commons Logging. 需要将jar包下载下载放在源码同一目录下。

jar包下载： http://commons.apache.org/logging/

## 编译&运行

编译和运行时需要指定CLASSPATH。

编译：
```
javac -cp commons-logging-1.2.jar Main.java
```

运行：
```
java -cp .;commons-logging-1.2.jar Main
```