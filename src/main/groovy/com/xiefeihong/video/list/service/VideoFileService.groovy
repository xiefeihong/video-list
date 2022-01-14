package com.xiefeihong.video.list.service

import com.xiefeihong.video.list.entity.FolderInfo
import com.xiefeihong.video.list.entity.VideoInfo

interface VideoFileService {

    List<FolderInfo> getFolderInfos()

    List<VideoInfo> getVideoInfos(String folderPath)

}
