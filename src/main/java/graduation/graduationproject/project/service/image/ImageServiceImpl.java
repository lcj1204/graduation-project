package graduation.graduationproject.project.service.image;

import graduation.graduationproject.project.dmain.image.Image;
import graduation.graduationproject.project.repository.image.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;


@Slf4j
@Service
public class ImageServiceImpl{
    private final ImageRepository imageRepository;
    private final FileHandler fileHandler;
    @Value("${file.dir}")
    private String fileDir;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        this.fileHandler = new FileHandler();
    }

    public Image addImage(
            MultipartFile file
    ) throws Exception{
        //파일을 저장하고 그 imageRepository 에 대한 Image 객체 를 가지고 있는다.
        Image image = fileHandler.parseFileInfo(file);

        //파일에 대해 DB에 저장하고 가지고 있을 것
        return imageRepository.save(image);
    }

    public String getFullPath(Integer imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow();
        log.info(image.getStored_file_path());
        return fileDir + image.getStored_file_path();
    }
}
