package graduation.graduationproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) // db 구축되면 삭제
public class GraduationProjectApplication {

	public static void main(String[] args)  {
		SpringApplication.run(GraduationProjectApplication.class, args);
	}

}
