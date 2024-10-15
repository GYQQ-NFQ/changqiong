package com.sky.utils;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Data
@AllArgsConstructor
@Slf4j
public class MinioUtil {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;

    /**
     * 文件上传
     *
     * @param bytes      文件的字节数据
     * @param objectName 文件名
     * @return          文件访问路径
     */
    public String upload(byte[] bytes, String objectName) {
        try {
            // 创建MinioClient实例。
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(accessKey, secretKey)
                    .build();

            // 创建PutObject请求。
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(inputStream, bytes.length, -1)
                            .contentType("application/octet-stream")
                            .build());

            // 文件访问路径规则 https://BucketName.Endpoint/ObjectName
            StringBuilder stringBuilder = new StringBuilder("https://");
            stringBuilder
                    .append(bucketName)
                    .append(".")
                    .append(endpoint)
                    .append("/")
                    .append(objectName);

            log.info("文件上传到: {}", stringBuilder.toString());

            return stringBuilder.toString();
        } catch (MinioException e) {
            log.error("MinioException error occurred: {}", e);
            return null;
        } catch (IOException e) {
            log.error("IOException error occurred: {}", e);
            return null;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("Security error occurred: {}", e);
            return null;
        }
    }
}