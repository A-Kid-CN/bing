### 目录结构

#### 日志

**规范**：`/usr/local/log/项目名称/log名称.log`

所有log都存放在`/usr/local/log`路径下，例如

`nohup java -jar bing-web.jar  >>/usr/local/logs/bing/bing.log &`

代表不挂断运行，并将log日志打到bing.log文件夹下

### nohup(no hang up)

作用：不挂断地运行命令。

语法：`nohup Command [Arg...][&]`

在缺省情况下，所有输出被重定向到名为`nohup.out`的文件中，目录在当前目录用户或当前文件夹下。https://github.com/suxinying/gitbook.git

#### nohup和&的区别

`&`：指在后台运行

`nohup`：不挂断地运行，并没有后台运行的功能。就是指，用nohup运行命令可以使命令永久的执行下去，和用户终端没有关系，例如我们断开SSH连接都不会影响他的运行，注意了nohup没有后台运行的意思；&才是后台运行

综上：`nohup COMMAND &`


###Jenkins路径
path：/usr/share/jenkins

启动命令：nohup java -jar jenkins.war –httpPort=8888 &
也可以：
jenkins启动

#service jenkins start

重启

#service jenkins restart
停止

#service jenkins stop
谷歌服务器的jenkins地址：