package com.xiefeihong.video.list.web.listener

import javax.servlet.ServletRequest
import javax.servlet.ServletRequestEvent
import javax.servlet.ServletRequestListener 

class WebAppListener implements ServletRequestListener {

    @Override
    void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest request = servletRequestEvent.getServletRequest()
        String path = servletRequestEvent.getServletContext().getContextPath()
        String basePath = "${request.scheme}://${request.serverName}:${request.serverPort}${path}/"
        request.setAttribute("baseUrl", basePath)
        def videoUrl = "${basePath}video/"
        request.setAttribute("videoUrl", videoUrl)
    }

}

