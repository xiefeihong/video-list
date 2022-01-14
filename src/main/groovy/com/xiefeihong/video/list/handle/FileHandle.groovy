package com.xiefeihong.video.list.handle

import org.springframework.stereotype.Component

@Component
class FileHandle {

    void rangeFileToOutputStream(RandomAccessFile file, OutputStream os, BigInteger startIndex, BigInteger endIndex){
        def bos
        //已处理数据的长度
        def complete = 0
        //未处理数据的长度
        def remainde = endIndex - startIndex + 1
        try {
            bos = new BufferedOutputStream(os)
            def blockSize = 1024 * 4
            def bytes = new byte[blockSize]
            def len = 0
            file.seek(startIndex.longValue())
            if(remainde < blockSize){
                blockSize = remainde
            }
            while (remainde > 0 &&
                    (len = file.read(bytes, 0, blockSize as int)) != -1) {
                bos.write(bytes, 0, len)
                complete += len
                if(remainde > blockSize){
                    remainde -= len
                } else {
                    blockSize = remainde
                    remainde = 0
                }
            }
        } catch (e) {
            print('>')
        } finally {
            file.close()
            bos.close()
            os.close()
        }
    }

}
