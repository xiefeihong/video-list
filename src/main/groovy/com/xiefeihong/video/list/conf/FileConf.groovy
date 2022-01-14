package com.xiefeihong.video.list.conf

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "file")
class FileConf {

    String root

}
