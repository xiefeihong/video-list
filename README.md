## video-list

**介绍**
```
播放视频的小网站
```

**软件架构**
```
开发环境：jdk + groovy + gradle
```

**安装说明**
```
./gradlew build
java -jar build/libs/video-list-1.0.jar

可使用以下参数
-Dfile.root={video-path} :视频文件夹的路径（默认为: 当前路径）,第一级为目录,第二级为视频文件
-Dspring.security.user.name={username} :设置的账户名（默认为: admin）
-Dspring.security.user.password={password} :设置的密码（默认为: password）
```

