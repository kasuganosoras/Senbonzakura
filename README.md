# Senbonzakura

这是一个可以禁止指定 Java 类加载的插件，适用于面板服。

使用 javaagent 进行加载，可阻止面板服用户运行指定的插件 / 服务端并输出警告信息

```
java -Xms128M -Xmx1024M -javaagent:ac.jar=hardcore -jar Server.jar
```

请记得锁定用户的启动参数修改权限。

> 本人与 CatServer 作者的误会已经消除，目前此插件已作为其他用途使用。

## 开源协议

此插件使用 MIT 协议开源，任何人可以在遵循开源协议的前提下自由修改，分发以及用于商业使用。
