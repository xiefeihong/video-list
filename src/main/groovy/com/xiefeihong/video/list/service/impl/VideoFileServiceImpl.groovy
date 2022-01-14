package com.xiefeihong.video.list.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Service
class VideoFileServiceImpl implements com.xiefeihong.video.list.service.VideoFileService{

    @Autowired
    com.xiefeihong.video.list.conf.FileConf fileConf

    @Override
    List<com.xiefeihong.video.list.entity.FolderInfo> getFolderInfos() {
        def folder = new File(fileConf.root)
        def folderInfos = []
        if(folder.isDirectory()){
            folder.listFiles().sort().each { file ->
                def instant = Instant.ofEpochMilli(file.lastModified())
                def localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                def folderInfo = new com.xiefeihong.video.list.entity.FolderInfo(file.name, localDateTime)
                folderInfos << folderInfo
            }
        }
        folderInfos
    }

    @Override
    List<com.xiefeihong.video.list.entity.VideoInfo> getVideoInfos(String folderPath) {
        def folder = new File("${fileConf.root}${folderPath}")
        def videoInfos = []
        if(folder.isDirectory()){
            folder.listFiles().sort().each { file ->
                def instant = Instant.ofEpochMilli(file.lastModified())
                def localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                def videoInfo = new com.xiefeihong.video.list.entity.VideoInfo(file.name, localDateTime, file.length() / 1024 / 1024 as int)
                videoInfos << videoInfo
            }
        }
        videoInfos
    }

}
