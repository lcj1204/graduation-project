package graduation.graduationproject.project.service.image;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.mysql.cj.xdevapi.JsonArray;
import graduation.graduationproject.project.dmain.image.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Map;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
@Component
public class ApiService {
    private final RestTemplate restTemplate;
    private final String url = "https://cv-api.kakaobrain.com/pose";
    private final String key = "5c8ef89167f2dbcf6376c48f412b7825";

    public ResponseEntity<String> CallApi(MultipartFile file) throws IOException {

        // 헤더 구성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + key);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        /*
         * 왜인진 모르겠지만 이걸 안하면 HttpMessageConversionException 이 발생함.
         * 참고 : https://ivvve.github.io/2019/07/03/java/Spring/resttemplate-invalid-definition-exception/
         */
        ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        // 바디 구성
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileResource);
        log.info("body -> {}", body);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        log.info("HttpEntity<> request -> {}", request);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        log.info("ResponseEntity<> reponse -> {}", response);

        return response;
    }
}
