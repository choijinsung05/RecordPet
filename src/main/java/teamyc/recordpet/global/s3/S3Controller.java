package teamyc.recordpet.global.s3;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import teamyc.recordpet.global.exception.CustomResponse;

@RestController
@RequestMapping("/api/v1/s3")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public CustomResponse<String> upload(@RequestParam("file") MultipartFile file) {

        String fileUrl = s3Service.uploadImage(file, "uploads");
        return CustomResponse.success(fileUrl);
    }
}
