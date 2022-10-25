package graduation.graduationproject.project;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

        factory.setReadTimeout(15000); // 15ch
        factory.setConnectTimeout(5000); // 5초

        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(50) // 최대 커넥션 수
                .setMaxConnPerRoute(20) // 가가 호스트당 커넥션 풀에 생성가능한 커넥션 수
                .build();

        factory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate;
        // 출처 : https://e2e2e2.tistory.com/15#%EA%B0%9C%EB%B0%9C-%ED%99%98%EA%B2%BD
    }
}
