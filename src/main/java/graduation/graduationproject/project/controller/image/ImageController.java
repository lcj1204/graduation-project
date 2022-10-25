package graduation.graduationproject.project.controller.image;

import graduation.graduationproject.project.dmain.image.Image;
import graduation.graduationproject.project.dmain.image.Person;
import graduation.graduationproject.project.repository.image.ImageRepository;
import graduation.graduationproject.project.service.image.ApiService;
import graduation.graduationproject.project.service.image.ImageAiService;
import graduation.graduationproject.project.service.image.ImageService;
import graduation.graduationproject.project.service.image.ImageServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@CrossOrigin
//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ImageController {
    @Autowired
    private ImageServiceImpl imageServiceImpl;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ApiService apiService;

    //테스트용 홈페이지
    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping(value = "/upload3", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes
    ) throws Exception {
        Image image = imageServiceImpl.addImage(file);

        redirectAttributes.addAttribute("fileId", image.getId());

        return "redirect:/v1/upload/{fileId}";
        //return new UrlResource("file:" + imageServiceImpl.getFullPath(image.getId()));
    }

    @GetMapping("/upload/{fileId}")
    public ResponseEntity<?> downloadImage(@PathVariable Integer fileId) throws MalformedURLException {

        Image image = imageRepository.findById(fileId).orElseThrow();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", image.getFile_content_type());

        UrlResource resource = new UrlResource("file:" + imageServiceImpl.getFullPath(fileId));

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

//    @PostMapping(value = "/upload2", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> upload2(@RequestParam("file") MultipartFile file) throws Exception {
        @PostMapping(value = "/upload2", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<Map<String, Object>> upload2(@RequestParam("file") MultipartFile file) throws Exception {

        // 카카오 브레인 포즈 api 서비스
        //ResponseEntity<String> response = apiService.CallApi(file);

        // 서버에 이미지 저장
        Image image = imageServiceImpl.addImage(file);
        UrlResource resource = new UrlResource("file:" + imageServiceImpl.getFullPath(image.getId()));

        Map<String, Object> result = new HashMap<>();
        //result.put("Person", response);
        result.put("Image", resource);

        //return response;
        return ResponseEntity.ok()
                .body(result);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload3(
            @RequestParam("file") MultipartFile file
    ) throws Exception {
        Image image = imageServiceImpl.addImage(file);

        HttpHeaders headers = new HttpHeaders();
        //headers.add("Content-Type", image.getFile_content_type());
        headers.setContentType(MediaType.parseMediaType(image.getFile_content_type()));

        UrlResource resource = new UrlResource("file:" + imageServiceImpl.getFullPath(image.getId()));

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
