package com.xiefeihong.video.list.entity

import java.time.LocalDateTime

class VideoInfo {

    VideoInfo(String path, LocalDateTime date, int size) {
        this.path = path
        this.date = date
        this.size = size
    }

    String path

    LocalDateTime date

    int size

}
