package com.xiefeihong.video.list.web

import com.xiefeihong.video.list.handle.HttpHandle
import com.xiefeihong.video.list.service.VideoFileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping('/video')
class VideoController {

    @Autowired
    HttpHandle httpHandle

    @Autowired
    VideoFileService videoFileService

    @GetMapping('/')
    String index(Model model){
        def folders = videoFileService.getFolderInfos()
        model.addAttribute('folderInfos', folders)
        'index'
    }

    @GetMapping('/{page}')
    String list(@PathVariable String page, Model model){
        def videos = videoFileService.getVideoInfos(page)
        model.addAttribute('videoInfos', videos)
        'list'
    }

    @RequestMapping('/{folder}/{filename}')
    void play(HttpServletRequest request, HttpServletResponse response,
              @PathVariable String folder, @PathVariable String filename) {
        httpHandle.downloadByServer(request, response, "${folder}/${filename}")
    }

}