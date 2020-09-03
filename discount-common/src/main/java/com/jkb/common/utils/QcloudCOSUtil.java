package com.jkb.common.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.DeleteObjectsRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangliang on 2019/7/7
 */
@Slf4j
@Component
public class QcloudCOSUtil {

    @Value("${ali.oss.imgFiledir}")
    private String imgFiledir;

    @Value("${ali.oss.videoFiledir}")
    private String videoFiledir;

    @Value("${ali.oss.secretKey}")
    private String secretKey;

    @Value("${ali.oss.secretId}")
    private String secretId;

    @Value("${ali.oss.bucketName}")
    private String bucketName;

    @Value("${ali.oss.regionName}")
    private String regionName;

    private static ThreadLocal<COSClient> threadLocal = new ThreadLocal<>();

    private void init(){
        COSCredentials cred = new BasicCOSCredentials(secretId,secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(regionName));
        threadLocal.set(new COSClient(cred, clientConfig));
    }

    public String upload(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()){
            init();
            //缩放比例
            File tempFile = File.createTempFile("temp",".png");
            IOUtils.copy(inputStream,new FileOutputStream(tempFile));
//            ImgUtil.scale(inputStream,new FileOutputStream(tempFile),0.5f);

            //获取后缀
            String extName = FileUtil.extName(file.getOriginalFilename());
            String dateStr = DateUtil.today();
            String NewFileName = cn.hutool.core.util.IdUtil.simpleUUID() + "." + extName;
            String key;
            // 设置文件路径和名称
            if (ObjectUtil.equal("mp4", extName)) {
                key = videoFiledir + "/" + dateStr + "/" + NewFileName;
            } else {
                key = imgFiledir + "/" + dateStr + "/" + NewFileName;
            }

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, tempFile);
            COSClient cosClient = threadLocal.get();
            cosClient.putObject(putObjectRequest);

            Date expiration = DateUtil.offset(new Date(), DateField.YEAR,10);
            URL url = cosClient.generatePresignedUrl(bucketName, key, expiration);
            return url.toString();
        } catch (IOException e) {
            log.error("文件上传失败:{}",e.getMessage());
            e.printStackTrace();
        }finally {
            shutdown();
        }
        return null;
    }


    public void delete(List<String> keys) {

        if (ObjectUtil.isEmpty(keys)) {
            return;
        }

        init();

        COSClient cosClient = threadLocal.get();
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);
        List<DeleteObjectsRequest.KeyVersion> allKey = new ArrayList<>();
        for (String key : keys) {
            DeleteObjectsRequest.KeyVersion keyVersion = new DeleteObjectsRequest.KeyVersion(key);
            allKey.add(keyVersion);
        }

        deleteObjectsRequest.setKeys(allKey);
        cosClient.deleteObjects(deleteObjectsRequest);

        shutdown();
    }

    private void shutdown(){
        COSClient cosClient = threadLocal.get();
        if (cosClient != null) {
            cosClient.shutdown();
        }
    }

}
