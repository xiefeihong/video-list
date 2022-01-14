package com.xiefeihong.video.list.handle

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
@Component
class HttpHandle {

    @Autowired
    com.xiefeihong.video.list.conf.FileConf fileConf

    @Autowired
    FileHandle fileHandle

    void downloadByServer(HttpServletRequest request, HttpServletResponse response, String filepath){
        def filename = "${fileConf.root}${filepath}"
        def file = new RandomAccessFile(filename, "r")
        def headerStr = request.getHeader("Range")
        def startIndex = 0
        def endIndex = file.length() - 1
        if (headerStr != null && headerStr.contains("bytes=") && headerStr.contains("-")) {
            def rangeStr = headerStr.split('=')[1]
            def (startByte2, endByte2) = rangeStr.split("-", 2)
            if(startByte2 != ''){
                startIndex = startByte2 as BigInteger
            }
            if(endByte2 != ''){
                endIndex = endByte2 as BigInteger
            }
        }
        log.info("range: ${headerStr}; start: ${startIndex}; end: ${endIndex}")
        def contentLength = endIndex - startIndex + 1
        String contentType = request.getServletContext().getMimeType(filename)
        response.setHeader("Accept-Ranges", "bytes")
        response.setStatus(response.SC_PARTIAL_CONTENT)
        response.setHeader("Content-Type", contentType)
        //inline表示浏览器直接使用 attachment
        response.setHeader("Content-Disposition", "inline;filename=${System.currentTimeMillis()}.${filename.split('\\.')[-1]}")
        response.setHeader("Content-Length", "${contentLength}")
        //Content-Range，格式为 [要下载的开始位置]-[结束位置]/[文件总大小]
        response.setHeader("Content-Range", "bytes ${startIndex}-${endIndex}/${file.length()}")
        def os = response.outputStream
        fileHandle.rangeFileToOutputStream(file, os, startIndex as BigInteger, endIndex as BigInteger)
    }

}