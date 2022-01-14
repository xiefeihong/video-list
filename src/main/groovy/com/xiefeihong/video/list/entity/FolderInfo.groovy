package com.xiefeihong.video.list.entity

import java.time.LocalDateTime

class FolderInfo {

    FolderInfo(String path, LocalDateTime date) {
        this.path = path
        this.date = date
    }

    String path

    LocalDateTime date

}
