package graduation.graduationproject.project.service.image;

import graduation.graduationproject.project.dmain.image.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageAiService {
    public Image responseImage(MultipartFile file) throws IOException;
}
