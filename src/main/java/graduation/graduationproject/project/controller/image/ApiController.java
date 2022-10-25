package graduation.graduationproject.project.controller.image;

import graduation.graduationproject.project.service.image.ApiService;
import graduation.graduationproject.project.service.image.ImageServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pose")
public class ApiController {
//    private final ApiService apiService;
//
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> poseApi(@RequestParam("file") MultipartFile file) throws IOException {
//        //String imageBinary = apiService.convertBinary(file);
//        //File file1 = apiService.multipartToFile(file);
//        ResponseEntity<String> response = apiService.CallApi(file);
//
//        return response;
//    }
}
