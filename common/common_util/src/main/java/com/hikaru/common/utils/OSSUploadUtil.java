package com.hikaru.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.hikaru.common.result.R;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OSSUploadUtil {
    /**
     * OSS文件上传的工具方法
     * @return
     */
    public static R<String> uploadFileToOss(String endPoint,
                                            String accessKeyId,
                                            String accessKeySecret,
                                            String bucketName,
                                            String bucketDomain,
                                            InputStream inputStream,
                                            String originalName
    ) {
        // 创建OssClient实体
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        // 根据日期生成文件夹
        String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
        // UUID
        String fileMainName = UUID.randomUUID().toString().replace("-", "");
        // 文件扩展名
        String extensionName = originalName.substring(originalName.lastIndexOf("."));
        // 拼接目录名、生成的文件主体名以及文件扩展名
        String objectName = folderName + "/" + fileMainName + extensionName;

        // 上传文件到OSS
        try {
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);
            ResponseMessage responseMessage = putObjectResult.getResponse();

            if(responseMessage == null) {
                String ossAccessFilePath = "https://" + bucketDomain + "/" + objectName;

                return R.successWithData(ossAccessFilePath);
            } else {
                int statusCode = responseMessage.getStatusCode();
                String errorResponseAsString = responseMessage.getErrorResponseAsString();

                return R.fail(statusCode + ":" + errorResponseAsString);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return R.fail(e.getMessage());
        } finally {
            if(ossClient != null) {
                ossClient.shutdown();
            }
        }
    }


    @Test
    public void test() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("src/main/resources/123.png");

        //String endPoint,
        //String accessKeyId,
        //String accessKeySecret,
        //String bucketName,
        //String bucketDomain,
        //InputStream inputStream,
        //String originalName

        R<String> r = uploadFileToOss("oss-cn-hangzhou.aliyuncs.com",
                "LTAI5tJ2eedCbj1UDB1PrHRb",
                "bfpAVjGSKyCHjuobkKU5TGskaxEXOX",
                "hikaru0530",
                "hikaru0530.oss-cn-hangzhou.aliyuncs.com",
                fis,
                "123.png");
        System.out.println(r.getData());
    }
}
