package teamyc.recordpet.global.s3;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import teamyc.recordpet.global.exception.GlobalException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static teamyc.recordpet.global.exception.ResultCode.IMAGE_UPLOAD_FAIL;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${image.resize.width}")
    private int targetWidth;
    @Value("${image.resize.height}")
    private int targetHeight;
    @Value("${image.resize.quality}")
    private double quality;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadImage(MultipartFile file, String folderName) {
        try {

            Path originalTempFile = Path.of(System.getProperty("java.io.tmpdir"), file.getOriginalFilename());
            file.transferTo(originalTempFile);

            Path resizedTempFile = Files.createTempFile("optimized", file.getOriginalFilename());

            Thumbnails.of(originalTempFile.toFile())
                    .size(targetWidth, targetHeight)
                    .outputQuality(quality)
                    .toFile(resizedTempFile.toFile());

            String fileName = folderName + "/" + file.getOriginalFilename() + "_" + UUID.randomUUID();

            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .build(),
                    resizedTempFile
            );

            Files.delete(originalTempFile);
            Files.delete(resizedTempFile);

            return getPublicUrl(fileName);
        } catch (IOException e) {
            throw new GlobalException(IMAGE_UPLOAD_FAIL);
        }
    }

    private String getPublicUrl(String fileName) {
        return String.format("https://%s.s3.amazonaws.com/%s", bucketName, fileName);
    }
}