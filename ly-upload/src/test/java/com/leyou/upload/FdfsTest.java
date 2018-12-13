package com.leyou.upload;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FdfsTest {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;

    @Test
    public void testUpload() throws FileNotFoundException {
        File file = new File("/Users/jw/Desktop/ping.png");
        //上传且生成缩略图
        StorePath storePath = this.storageClient.uploadFile(new FileInputStream(file),file.length(),"png",null);
        System.out.println(storePath.getFullPath());
        System.out.println(storePath.getPath());


    }


    @Test
    public void testUpLoadAndCreateThumb() throws FileNotFoundException{
        File file = new File("/Users/jw/Desktop/ping.png");
        //上传且生成缩略图
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(new FileInputStream(file),file.length(),"png",null);

        System.out.println(storePath.getFullPath());
        System.out.println(storePath.getPath());

        String path = thumbImageConfig.getThumbImagePath(storePath.getPath());
        System.out.println(path);

    }



}
