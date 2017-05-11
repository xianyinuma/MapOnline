# MapOnline
SOTA2017 Project

### app简介 
生活，立于足迹。
新一代的地图社交。
### 主要功能
- **注册、登录** 如下图所示，输入用户名与密码即可完成登录，注册也只需用户名与密码验证<br>
![图片不见了！！！呜呜呜](https://github.com/xianyinuma/MapOnline/blob/master/docs/p4.jpg)
- **上传、删除图片** 用户可以拍照或从相册选择照片上传，经过微软识别API，会反馈一些图片信息给用户，用户可以修改返回信息（也可不修改）然后确认上传，上传的图片将会以坐标Marker的形式展示在地图上<br>
![图片不见了！！！呜呜呜](https://github.com/xianyinuma/MapOnline/blob/master/docs/p3.jpg)
- **在地球上展示图片** 如下图所示，地图上显示多个坐标Marker，用户可以点击Marker来查看自己以前发的图片。<br>
![图片不见了！！！呜呜呜](https://github.com/xianyinuma/MapOnline/blob/master/docs/p1.jpg)
- **关注、取关好友** 用户可以随意关注或取消关注好友，好友关注的形式类似微博，查看他发的地图动态~
- **查看好友的状态信息**<br>
![图片不见了！！！呜呜呜](https://github.com/xianyinuma/MapOnline/blob/master/docs/p2.jpg)
![图片不见了！！！呜呜呜](https://github.com/xianyinuma/MapOnline/blob/master/docs/p5.jpg)

- **智能分析图片，编辑信息**
- **定位获取天气**

***

### git目录简介
- code\front_end: 前端代码。可直接下载部署
- code\backend: 后端代码。真实后端部署在阿里云上，IP为118.89.184.85，端口8080。
- code\WebService: JAX-WS封装的SOAP webservice代码。同样也部署在阿里云上。
- docs:简要的需求文档、展示ppt、展示的图片
- builds：封装好的apk以及ios工程包


### 技术栈 
- 前端：[ionic](http://ionicframework.com/)，基于cordova与[angular2](https://angular.cn/)(typescript，无力吐槽这个JS的方言...)框架。web端的开发，可极其方便地移植到移动端（包括但不限于ios与android）。cordova良好的API封装让我们很方便的调用手机移动端的原生功能（比如相机、定位等）
- 后端：[spring boot框架](http://projects.spring.io/spring-boot/)。专业的后端框架让我们省去了很多的配置文件，并且集成了嵌入式的web服务器。尤其是持久层的jpa技术，非常方便的操作数据库，让我们聚焦在控制层的逻辑开发。
- WebService：JAX-WS，RPC-oriented实现对[Microsoft Cognitive API](https://azure.microsoft.com/zh-cn/services/cognitive-services/)与[墨迹天气API](http://www.moji.com/tob/)的封装。JAX-WS对WebService格式方面的简化，并且使用我们比较熟悉的java语言，让我们封装进行地比较顺利。

### 问题与解决
- 由于angular2的http通信存在**跨域问题（Access-Control-Allow-Origin）**，让我们在前后端通信方面花了大量的时间进行了低效率debug。最后经过大量的资料查询，了解到需要在后端增加跨域的允许，最终才将这个问题解决。
- angular2中http通信的**post**方法无法传输**json格式的数据**，最终用普通的string格式（x-www-form-urlencoded）来代替（我们也很绝望...）
- *注*：ionic项目在不同的电脑（不同系统）进行迁移的时候，需要重装node_module，这也是我们遇到的问题之一，希望TA在看我们的代码的时候也记得。

----
#### 联系我们
email：<14307110274@fudan.edu.cn>
