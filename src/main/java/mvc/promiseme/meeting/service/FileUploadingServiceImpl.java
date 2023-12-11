package mvc.promiseme.meeting.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import mvc.promiseme.common.NaverKey;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FileUploadingServiceImpl implements FileUploadingService{

    private final NaverKey naverKey;

    @Override
    public String fileUploadToCloud(MultipartFile voiceFile) {

        final String endPoint = "https://kr.object.ncloudstorage.com";
        final String regionName = "kr-standard";
        final String accessKey = naverKey.getAccessKey();
        final String secretKey = naverKey.getKey();

        // S3 client
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        String bucketName = "promise-me";
        String objectName = UUID.randomUUID().toString();

        try {
            s3.putObject(new PutObjectRequest(bucketName, objectName, voiceFile.getInputStream(), null));
            System.out.format("Object %s has been created.%n", objectName);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectName;
    }
}
